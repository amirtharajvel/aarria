package com.aarria.retail.web.rest.controller;

import static com.aarria.retail.core.util.View.CHECKOUT;
import static com.aarria.retail.core.util.View.LOGIN;
import static com.aarria.retail.core.util.View.ORDER_FAILURE;
import static com.aarria.retail.core.util.View.REDIRECT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.aarria.retail.core.domain.Address;
import com.aarria.retail.core.domain.Order;
import com.aarria.retail.core.domain.Pincode;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.service.MessageService;
import com.aarria.retail.core.service.OrderService;
import com.aarria.retail.core.service.PaymentService;
import com.aarria.retail.core.service.PincodeService;
import com.aarria.retail.core.service.UserService;
import com.aarria.retail.core.service.ValidationService;
import com.aarria.retail.core.util.Application;
import com.aarria.retail.core.util.Enum.PaymentMethod;
import com.aarria.retail.core.util.Enum.PlaceOrderFailReason;
import com.aarria.retail.core.util.Util;
import com.aarria.retail.core.util.View;
import com.aarria.retail.web.dto.request.PaymentDto;
import com.aarria.retail.web.dto.response.PaymentSuccessDTO;
import com.aarria.retail.web.dto.response.PrepareOrderResultDto;

@RestController
public class PaymentController {

	private static final String PAYMENT_FAILED = "The payment gateway is too busy or experienced another temporary problem, and cannot process the transaction at the moment. If money is deducted from your bank account, don't worry.. your money is safe.<br/><br/> Please <a href='/aboutus#contact_us'>contact us</a> we'll work with our payment gateway partner to reimburse your amount. <br/><br/>You can still <a href='/checkout'>retry</a> the order";

	private static final String REDIRECT_TO_SUCCESS = "redirect:/redirectToSuccess";

	private static final String REDIRECT_TO_FAILURE = "redirect:/redirectToFailure";

	private static final String PAYMENT_COUNT = "paymentCount";

	private static final int SUBMIT_COUNT_ONE = 1;

	private static Logger LOGGER = LogManager.getLogger(PaymentController.class);

	private static final String CCAV_REQUEST_HANDLER = "ccavRequestHandler";
	private static final String CCAV_RESPONSE_HANDLER = "ccavResponseHandler";
	private static final String DATA_FROM_HTM = "dataFrom";
	private static final String SUCCESS = "success";

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private ValidationService validationService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private PincodeService pincodeService;

	@RequestMapping(path = "/paymentResponse", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView receivePaymentResponse(HttpServletRequest request, HttpSession session) throws IOException {

		ModelAndView view = new ModelAndView(REDIRECT_TO_FAILURE);

		try {

			String payResponse = request.getParameter("encResp");

			if (payResponse == null) {
				return orderFailure("payResponse is null", view, PAYMENT_FAILED, session);
			}

			if (session == null) {
				return orderFailure("no session is present", view, PAYMENT_FAILED, session);
			}

			LOGGER.info("session id after posting to payment gateway" + session.getId());

			Object paymentCount = session.getAttribute(PAYMENT_COUNT);

			Integer count = null;
			if (paymentCount == null) {
				return orderFailure("paymentCount is null for ", view, PAYMENT_FAILED, session);
			}

			count = (Integer) paymentCount;

			PaymentDto dto = paymentService.parsePaymentResponse(payResponse, count);

			session.setAttribute(PAYMENT_COUNT, ++count);

			if (dto == null) {
				return orderFailure("dto is null", view, PAYMENT_FAILED, session);
			}

			// TODO verify
			if (dto.getMerchant_param2() == null
					|| !(session.getId().toLowerCase().equals(dto.getMerchant_param2().toLowerCase()))) {
				return orderFailure("session.getId().toLowerCase().equals(dto.getMerchant_param2())", view,
						PAYMENT_FAILED, session);
			}

			String userId = dto.getMerchant_param1();

			if (userId == null || userId.trim().equals("")) {
				return orderFailure("userId is null" + userId, view, PAYMENT_FAILED, session);
			}

			LOGGER.info("finding user for userId " + userId);
			User user = userService.findOne(Long.valueOf(userId));

			if (user == null) {
				return orderFailure("user is null " + userId, view, PAYMENT_FAILED, session);
			}

			session.setAttribute("userId", userId);

			PaymentSuccessDTO paymentSuccessDTO = orderService.getPaymentSuccessDTO(dto.getOrder_id(), user);
			if (SUCCESS.toLowerCase().equals(dto.getOrder_status().toLowerCase())) {
				session.setAttribute("order", paymentSuccessDTO);
				orderService.confirmNONCODOrderAfterPaymentIsMade(user, dto.getOrder_id(), request);
				view.setViewName(REDIRECT_TO_SUCCESS);
				return view;
			} else {
				return orderFailure("FAIL message from PG " + dto, view, PAYMENT_FAILED, session);
			}

		} catch (Exception e) {
			LOGGER.error("Payment Failed ", e);
			String trace = Util.retrieveStackTraceFromException(e);
			LOGGER.info("Payment failed info message = " + trace);
			messageService.sendEmailAndSmsToAdmin(trace, "Payment failed!");
			return orderFailure("in the exception block " + trace, view, PAYMENT_FAILED, session);
		}

	}

	private ModelAndView orderFailure(String logMessage, ModelAndView view, String userMessage, HttpSession session) {
		LOGGER.error("Payment failed at " + logMessage);
		messageService.sendEmailAndSmsToAdmin(logMessage, "Payment failed!");
		session.setAttribute("errorMessage", userMessage);
		return view;
	}

	@RequestMapping(value = "/placeOrder", method = RequestMethod.GET)
	public ModelAndView placeOrder(HttpServletRequest request, HttpSession session, HttpServletResponse response,
			@RequestParam("paymentMode") Integer paymentMode, RedirectAttributes redirect) throws IOException {

		User user = validationService.findUserFromSession(session);

		ModelAndView view = new ModelAndView(REDIRECT + CHECKOUT);

		if (user == null) {
			session.invalidate();
			view.setViewName(View.REDIRECT + LOGIN);
			return view;
		}

		PrepareOrderResultDto result = orderService.prepareOrder(paymentMode, user);

		if (result == null || isProductNotAvailable(result)) {
			if (result == null) {
				result = new PrepareOrderResultDto();
				result.setMessage(
						"Due to technical difficulty we are unable to process your order. Please check back later.");
			}

			redirect.addFlashAttribute("message", result.getMessage());
			return view;
		}

		Order order = result.getOrder();

		if (order == null || isExceptionOccurred(result)) {
			view.setViewName(ORDER_FAILURE);
			view.addObject("errorMessage", result.getMessage());
			return view;
		}

		if (order.getPayment() == null && paymentMode.equals(PaymentMethod.NON_COD.ordinal())) {
			throw new RuntimeException("Sorry. We could not process your current payment due to technical difficulty");
		}

		if (paymentMode.equals(PaymentMethod.COD.ordinal())) {
			return confirmCODOrder(session, user, order, view, request);
		}
		// } else {
		// orderService.confirmNONCODOrderAfterPaymentIsMade(user, order.getOrderId(),
		// request);
		// PaymentSuccessDTO paymentSuccessDTO =
		// orderService.getPaymentSuccessDTO(order.getOrderId(), user);
		// session.setAttribute("order", paymentSuccessDTO);
		// view.setViewName(REDIRECT_TO_SUCCESS);
		// return view;
		// }

		view.setViewName(CCAV_REQUEST_HANDLER);

		session.setAttribute(PAYMENT_COUNT, SUBMIT_COUNT_ONE);
		LOGGER.info("session id before posting to payment gateway " + session.getId());
		request.setAttribute("paymentParameters",
				getPaymentParams(order, user.getId() + "", session.getId(), response, SUBMIT_COUNT_ONE));

		return view;

	}

	private Map<String, String> getPaymentParams(Order order, String userId, String sessionId,
			HttpServletResponse response, int i) {

		Map<String, String> map = new TreeMap<>();
		map.put("merchant_param1", userId);
		map.put("merchant_param2", sessionId);
		map.put("merchant_param3", i + "");
		map.put("TID", "1489080740342");
		map.put("merchant_id", "125761");
		map.put("order_id", order.getOrderId());
		map.put("amount", order.getPayment().getAmountPaid() + "");
		map.put("currency", "INR");

		Address address = order.getDelivery().getBillingAddress();

		Pincode pincodeFromDatabase = pincodeService.findByPincode(address.getPincode() + "");

		addBillingParams(order, map, pincodeFromDatabase, address);

		addShippingParams(order, map, pincodeFromDatabase, address);

		String encodeRedirectURL = response
				.encodeRedirectURL(Application.APP_NAME + "/paymentResponse;JSESSIONID=" + sessionId);
		map.put("redirect_url", encodeRedirectURL);
		LOGGER.info("encodeRedirectURL" + encodeRedirectURL);

		return map;

	}

	private void addBillingParams(Order order, Map<String, String> map, Pincode pincodeFromDatabase, Address address) {

		String state = "Unavailable";
		String city = "Unavailable";

		if (pincodeFromDatabase != null) {
			state = pincodeFromDatabase.getStatename();
			city = pincodeFromDatabase.getDistrictname();
		}

		map.put("billing_country", "India");
		map.put("billing_name", address.getName());
		map.put("billing_address", address.getAddress());
		map.put("billing_city", city);
		map.put("billing_state", state);
		map.put("billing_zip", address.getPincode() + "");
		map.put("billing_tel", address.getMobile() + "");
		map.put("billing_email", address.getEmail() + "");
	}

	private void addShippingParams(Order order, Map<String, String> map, Pincode pincodeFromDatabase, Address address) {

		String state = "Unavailable";
		String city = "Unavailable";

		if (pincodeFromDatabase != null) {
			state = pincodeFromDatabase.getStatename();
			city = pincodeFromDatabase.getDistrictname();
		}

		map.put("delivery_country", "India");
		map.put("delivery_name", address.getName());
		map.put("delivery_address", address.getAddress());
		map.put("delivery_city", city);
		map.put("delivery_state", state);
		map.put("delivery_zip", address.getPincode() + "");
		map.put("delivery_tel", address.getMobile() + "");
	}

	private boolean isExceptionOccurred(PrepareOrderResultDto result) {
		return result.getReason() != null && result.getReason().equals(PlaceOrderFailReason.TECHNICAL_FAILURE.name());
	}

	private boolean isProductNotAvailable(PrepareOrderResultDto result) {
		return result.getReason() != null
				&& result.getReason().equals(PlaceOrderFailReason.PRODUCT_COUNT_MISMATCH.name());
	}

	private ModelAndView confirmCODOrder(HttpSession session, User user, Order order, ModelAndView view,
			HttpServletRequest request) {
		LOGGER.info("confirmOrderAfterPaymentIsMade for user " + user + " for orderId " + order.getOrderId());
		orderService.confirmCODOrder(user, order.getOrderId());
		view.setViewName(REDIRECT_TO_SUCCESS);
		PaymentSuccessDTO paymentSuccessDTO = orderService.getPaymentSuccessDTO(order.getOrderId(), user);
		session.setAttribute("order", paymentSuccessDTO);
		Util.backupDatabase(request);
		return view;
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public ModelAndView success(HttpSession session, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("ordersuccess");
		return view;
	}

	@RequestMapping(value = "/redirectToSuccess", method = RequestMethod.GET)
	public ModelAndView redirectToSuccess(HttpSession session, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("redirectToSuccess");
		return view;
	}

	@RequestMapping(value = "/secondRedirectToSuccess", method = RequestMethod.GET)
	public ModelAndView secondRedirectToSuccess(HttpSession session, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("secondRedirectToSuccess");
		return view;
	}

	@RequestMapping(value = "/redirectToFailure", method = RequestMethod.GET)
	public ModelAndView redirectToFailure(HttpSession session, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("redirectToFailure");
		return view;
	}

	@RequestMapping(value = "/secondRedirectToFailure", method = RequestMethod.GET)
	public ModelAndView secondRedirectToFailure(HttpSession session, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("secondRedirectToFailure");
		return view;
	}

	@RequestMapping(value = "/orderfailure", method = RequestMethod.GET)
	public ModelAndView failure(HttpSession session, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("orderfailure");
		return view;
	}

	@RequestMapping(value = "/pay")
	public RedirectView payRequest() {
		return redirectToPaymentGateway("redirect:/payRequest");

	}

	@RequestMapping(value = "/payResponse")
	public ModelAndView pay() {
		ModelAndView modelAndView = new ModelAndView(CCAV_RESPONSE_HANDLER);
		return modelAndView;
	}

	@RequestMapping(value = "/pay/data")
	public ModelAndView payDataFrom() {
		ModelAndView modelAndView = new ModelAndView(DATA_FROM_HTM);
		return modelAndView;
	}

	@RequestMapping(path = "/getParams", method = RequestMethod.POST)
	public void params(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String[]> map = request.getParameterMap();

		List<Object> l = new ArrayList<>();
		for (Map.Entry<String, String[]> m : map.entrySet()) {
			l.add(m);
		}
	}

	public static RedirectView redirectToPaymentGateway(String url) {
		RedirectView redirectView = new RedirectView(url);
		redirectView.setExposeModelAttributes(true);
		return redirectView;
	}

}
