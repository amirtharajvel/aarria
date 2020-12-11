package com.aarria.retail.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aarria.retail.core.domain.Address;
import com.aarria.retail.core.domain.Delivery;
import com.aarria.retail.core.domain.Item;
import com.aarria.retail.core.domain.Order;
import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.service.BankAccountService;
import com.aarria.retail.core.service.InvoiceService;
import com.aarria.retail.core.service.OrderService;
import com.aarria.retail.core.service.ValidationService;
import com.aarria.retail.core.util.AdminView;
import com.aarria.retail.core.util.Constants;
import com.aarria.retail.core.util.Enum;
import com.aarria.retail.core.util.Enum.OrderStatus;
import com.aarria.retail.core.util.Util;
import com.aarria.retail.core.util.View;
import com.aarria.retail.web.dto.request.CancelOrderDto;
import com.aarria.retail.web.dto.request.OrderDetailDto;
import com.aarria.retail.web.dto.request.RemoveItemFromOrderDto;
import com.aarria.retail.web.dto.request.ReturnOrderSubmitDto;
import com.aarria.retail.web.dto.response.AddressDto;
import com.aarria.retail.web.dto.response.BankAccountDetailsDto;
import com.aarria.retail.web.dto.response.MyOrderDto;
import com.aarria.retail.web.dto.response.MyOrderItemsDto;
import com.aarria.retail.web.dto.response.MyOrdersDeliveryDto;
import com.aarria.retail.web.dto.response.OrderDetailOutputDto;
import com.aarria.retail.web.dto.response.PrintInvoiceDto;
import com.aarria.retail.web.dto.response.ReturnOrderDetailsDto;
import com.aarria.retail.web.dto.response.ViewInvoiceDto;

@Controller
public class OrderController {

	private static Logger LOGGER = LogManager.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private BankAccountService bankAccountService;

	@Autowired
	private ValidationService validationService;

	@Autowired
	private InvoiceService invoiceService;

	@RequestMapping(value = "/placereturnorder", method = RequestMethod.POST)
	public ModelAndView submitReturnOrder(HttpSession session, ReturnOrderSubmitDto dto) {
		ModelAndView view = new ModelAndView(View.RETURN_ORDER);

		User user = validationService.findUserFromSession(session);

		if (user == null || dto == null || dto.getOrderId() == null || dto.getPickupAddress() == null) {
			view.setViewName(View.LOGOUT);
			return view;
		}

		orderService.placeReturnOrder(dto, user);
		view.addObject("orderPlaced", "success");
		return view;

	}

	@RequestMapping(value = "/returnorder")
	public ModelAndView returnOrder(@RequestParam("orderId") String orderId, HttpSession session) {
		ModelAndView view = new ModelAndView("returnorder");

		User user = validationService.findUserFromSession(session);

		if (user == null) {
			view.setViewName(View.LOGOUT);
			return view;
		}

		ReturnOrderDetailsDto dto = orderService.getReturnOrderDetails(orderId, user);

		if (dto == null) {
			view.setViewName("redirect:" + View.MY_ORDERS);
			return view;
		}

		view.addObject("order", dto);

		return view;
	}

	@RequestMapping(value = "/cancelOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView cancelOrder(HttpSession session, @Valid CancelOrderDto cancelOrderDto) {
		ModelAndView view = new ModelAndView();

		User user = validationService.findUserFromSession(session);

		if (user == null || cancelOrderDto == null || cancelOrderDto.getOrderIdPrimaryKey() == null) {
			view.setViewName(View.LOGOUT);
			return view;
		}

		orderService.cancelOrder(cancelOrderDto);

		view.setViewName("redirect:" + View.MY_ORDERS);

		getAllOrders(view, user.getId());

		return view;
	}

	@RequestMapping(value = "/removeItemFromOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView removeItem(HttpSession session, RemoveItemFromOrderDto dto) {
		ModelAndView view = new ModelAndView();

		User user = validationService.findUserFromSession(session);

		if (user == null || dto == null || dto.getItemId() == null || dto.getOrderIdPrimaryKey() == null) {
			view.setViewName(View.LOGOUT);
			return view;
		}

		orderService.removeItemFromOrder(dto);

		view.setViewName("redirect:" + dto.getFromPage());

		getAllOrders(view, user.getId());

		return view;
	}

	@RequestMapping("/myorders")
	public ModelAndView getOrders(HttpSession session) {

		ModelAndView view = new ModelAndView();

		User user = validationService.findUserFromSession(session);

		if (user == null) {
			view.setViewName(View.LOGOUT);
			return view;
		}

		view.setViewName(View.MY_ORDERS);

		Long userId = Long.parseLong(session.getAttribute("userId").toString());

		try {
			getAllOrders(view, userId);

			List<BankAccountDetailsDto> dtos = bankAccountService.getAllBankAccountsForUser(user);

			if (CollectionUtils.isNotEmpty(dtos)) {
				view.addObject("bankAccounts", dtos);
			}
		} catch (Exception e) {
			LOGGER.info("Problem occurred while creating orders ", e);
		}
		return view;
	}

	@RequestMapping("/getorderdetails")
	public ModelAndView getOrderDetails(HttpSession session, OrderDetailDto dto) {
		ModelAndView view = new ModelAndView(AdminView.ADMIN);

		User user = validationService.findUserFromSession(session);

		if (user == null) {
			view.setViewName(View.LOGOUT);
			return view;
		}

		List<OrderDetailOutputDto> dtos = orderService.getOrders(dto);
		view.addObject("orders", dtos);
		return view;
	}

	@RequestMapping("/viewinvoice")
	public ModelAndView viewInvoice(@RequestParam("orderId") String orderId, HttpSession session) {
		ModelAndView view = new ModelAndView(View.INVOICE);

		User user = validationService.findUserFromSession(session);

		if (user == null) {
			view.setViewName(View.LOGOUT);
			return view;
		}

		ViewInvoiceDto dto = invoiceService.generateInvoiceForUser(orderId, user);
		if (dto == null) {
			view.setViewName("/");
			return view;
		}

		view.addObject("invoice", dto);
		return view;
	}

	@RequestMapping("/printorderinvoice")
	public ModelAndView printInvoice(String orderId, HttpSession session) {
		ModelAndView view = new ModelAndView(View.PRINT_INVOICE);

		LOGGER.info("printing invoice for orderId " + orderId);

		User user = validationService.findAdminUserFromSession(session);

		if (user == null) {
			view.setViewName(View.LOGOUT);
			return view;
		}

		PrintInvoiceDto dto = invoiceService.generateInvoiceForTransport(orderId, user);

		LOGGER.info("Done printing invoice for orderId " + dto);

		if (dto == null) {
			view.setViewName("/");
			return view;
		}

		LOGGER.info("Adding Object for orderId " + dto);
		
		view.addObject("invoice", dto);
		
		return view;
	}

	private void getAllOrders(ModelAndView view, Long userId) {
		List<Order> orders = orderService.getOrders(userId);

		List<Order> openOrders = new ArrayList<>();
		List<Order> deliveredOrders = new ArrayList<>();
		List<Order> cancelledOrders = new ArrayList<>();

		if (orders != null) {

			orders.forEach(order -> {
				if (isOpenOrder(order)) {
					openOrders.add(order);
				} else if (isDeliveredOrder(order)) {
					deliveredOrders.add(order);
				} else if (isCancelledOrder(order)) {
					cancelledOrders.add(order);
				}
			});

			if (openOrders != null && !openOrders.isEmpty()) {
				view.addObject("openOrders", createDtos(openOrders));
			}
			if (deliveredOrders != null && !deliveredOrders.isEmpty()) {
				view.addObject("deliveredOrders", createDtos(deliveredOrders));
			}
			if (cancelledOrders != null && !cancelledOrders.isEmpty()) {
				view.addObject("cancelledOrders", createDtos(cancelledOrders));
			}
		}
	}

	private boolean isOpenOrder(Order order) {
		return ((order.getStatus() != null && order.getStatus().intValue() == Enum.OrderStatus.OPEN.ordinal()
				|| order.getStatus().intValue() == Enum.OrderStatus.BEING_PACKED.ordinal()
				|| order.getStatus().intValue() == Enum.OrderStatus.DISPATCHED.ordinal()) && isPaymentNotDone(order));
	}

	private boolean isDeliveredOrder(Order order) {

		if (order.getIsDelivered() != null && order.getIsDelivered() == true) {
			return true;
		}

		return ((order.getStatus() != null && (order.getStatus().intValue() == Enum.OrderStatus.DELIVERED.ordinal()
				|| order.getStatus().intValue() == OrderStatus.RETURN_PICKUP_REQUESTED.ordinal()))
				&& (isPaymentNotDone(order) && !order.getStatus().equals(OrderStatus.REFUND_INITIATED.ordinal())));
	}

	private boolean isCancelledOrder(Order order) {
		return (order.getStatus() != null
				&& (order.getStatus().intValue() == Enum.OrderStatus.CANCELLED_WITHIN_30_MINS.ordinal()
						|| order.getStatus().intValue() == Enum.OrderStatus.CANCELLED_WHILE_PACKAGING.ordinal()
						|| order.getStatus().intValue() == Enum.OrderStatus.REFUND_INITIATED.ordinal()
						|| order.getStatus().intValue() == Enum.OrderStatus.REFUNDED.ordinal())
				&& isPaymentNotDone(order));
	}

	private boolean isPaymentNotDone(Order order) {
		return !order.getStatus().equals(OrderStatus.NON_COD_PAYMENT_NOT_DONE.ordinal());
	}

	private List<MyOrderDto> createDtos(List<Order> orders) {

		List<MyOrderDto> orderDtos = new ArrayList<>();

		orders.forEach(order -> {

			MyOrderDto orderDto = getMyOrderDto(order);

			if (orderDto != null && order.getOrderedItems() != null) {

				List<MyOrderItemsDto> itemsDtos = new ArrayList<>();

				Double totalOrderPrice = 0.0;

				for (Item orderedItem : order.getOrderedItems()) {

					Product product = orderedItem.getProduct();

					if (product != null) {

						MyOrderItemsDto itemsDto = new MyOrderItemsDto(orderedItem.getId(), product.getListingImage(),
								product.getName(), orderedItem.getUnitSoldPrice(), orderedItem.getTotalSoldPrice(),
								orderedItem.getQuantity(), null, product.getIsReturnable(),
								order.getDelivery().getDeliveredDate(), orderedItem.getOrderItemId(),
								orderedItem.getStatus(), product.getId(), orderedItem.getSize(),
								orderedItem.getReturnCashMethod() + "", product.getPid());
						itemsDtos.add(itemsDto);
						if (orderedItem.getTotalSoldPrice() != null && Util.isItemPurchased(orderedItem)) {
							totalOrderPrice += orderedItem.getTotalSoldPrice();
						}
					}
				}

				orderDto.setItems(itemsDtos);
				orderDtos.add(orderDto);
				orderDto.setTotalOrderPrice(order.getTotalOrderAmount());
				// this line should not be removed or order changed
				orderDto.setReturnTimeLimit(Util.checkReturnDate(order.getDelivery().getDeliveredDate(),
						order.getStatus(), orderDto.getIsAnyOfTheItemIsReturned()));
				orderDto.setShowReturnPickup(Util.isShowReturnPickup(order));
				orderDto.setIsReturnPickupRequested(Util.isReturnPickupRequested(order));
				orderDto.setIsReturnPickupArranged(order.getIsReturnPickupArranged());
			}
		});

		return orderDtos;
	}

	private MyOrderDto getMyOrderDto(Order order) {

		if (order == null) {
			return null;
		}

		Delivery delivery = order.getDelivery();

		if (delivery == null) {
			throw new IllegalStateException("Delivery must be eager fetched but not.");
		}

		AddressDto shippingAddressDto = null;
		Address shipAddress = delivery.getShippingAddress();
		if (shipAddress != null) {
			shippingAddressDto = AddressDto.createDtoFromAddress(shipAddress);
		}

		AddressDto billingAddressDto = null;
		Address billingAddress = delivery.getShippingAddress();
		if (billingAddress != null) {
			billingAddressDto = AddressDto.createDtoFromAddress(billingAddress);
		}

		String orderedDate = null;
		if (order.getOrderedDate() != null) {
			orderedDate = new SimpleDateFormat(Constants.DATE_WITH_TIME).format(order.getOrderedDate());
		}

		String expectedDeliveredDate = null;
		if (order.getDelivery().getExpectedDeliveryDate() != null) {
			expectedDeliveredDate = new SimpleDateFormat(Constants.DATE_WITH_TIME)
					.format(order.getDelivery().getExpectedDeliveryDate());
		}

		String deliveredDate = null;
		if (order.getDelivery().getDeliveredDate() != null) {
			deliveredDate = new SimpleDateFormat(Constants.DATE_WITH_TIME)
					.format(order.getDelivery().getDeliveredDate());
		}

		String returnedDate = null;
		if (order.getReturnedDate() != null) {
			returnedDate = new SimpleDateFormat(Constants.DATE_WITH_TIME).format(order.getReturnedDate());
		}

		MyOrdersDeliveryDto orderDeliveryDetails = MyOrdersDeliveryDto.create(delivery);

		MyOrderDto orderDto = new MyOrderDto(order.getId(), order.getOrderId(), delivery.getReceivedPerson(),
				orderedDate, retrieveOrderStatus(order), shippingAddressDto, billingAddressDto, deliveredDate, null,
				orderDeliveryDetails, order.getIsCashOnDelivery(), order.getReturnCashMethod() + "",
				expectedDeliveredDate, order.getReturnPickupAddress(), order.getPickupDateAndTime(), returnedDate,
				order.getIsAnyOfTheItemIsReturned(), order.getIsReturnedItemReceived(), order.getWalletAmountUsed(),
				getTotalAmountToPay());

		return orderDto;
	}

	private Double getTotalAmountToPay() {
		return null;
	}

	private String retrieveOrderStatus(Order order) {
		long orderedMillis = order.getOrderedDate().getTime();
		long diffMillis = System.currentTimeMillis() - orderedMillis;

		Integer status = order.getStatus();

		if (TimeUnit.MILLISECONDS.toMinutes(diffMillis) > 30 && status.equals(OrderStatus.OPEN.ordinal())) {
			status = OrderStatus.BEING_PACKED.ordinal();
		}

		return Enum.OrderStatus.getString(status);
	}

}
