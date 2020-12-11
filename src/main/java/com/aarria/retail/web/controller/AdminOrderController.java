package com.aarria.retail.web.controller;

import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.service.*;
import com.aarria.retail.core.util.View;
import com.aarria.retail.persistence.repository.datatables.PaginationCriteria;
import com.aarria.retail.persistence.repository.datatables.TablePage;
import com.aarria.retail.persistence.repository.datatables.TablePaginationException;
import com.aarria.retail.persistence.repository.datatables.TablePaginator;
import com.aarria.retail.web.dto.request.*;
import com.aarria.retail.web.dto.response.FetchProductImagesDto;
import com.aarria.retail.web.dto.response.RefundDetailsDto;
import com.aarria.retail.web.dto.response.ViewOrderDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.aarria.retail.core.util.AdminView.*;

@Controller
public class AdminOrderController {

	@Autowired
	private AdminOrderService adminOrderService;

	@Autowired
	private TablePaginator paginator;

	@Autowired
	private OrderService orderService;

	@Autowired
	private WalletService walletService;

	@Autowired
	private ValidationService validationService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductImageService productColorsService;

	@Autowired
	private MiscellaneousService miscellaneousService;

	@RequestMapping(value = "/admin")
	public ModelAndView showAdminPage(HttpSession session) {
		ModelAndView view = new ModelAndView(ADMIN);

		User user = validationService.findAdminUserFromSession(session);

		if (user == null) {
			view.setViewName(View.HOME);
			return view;
		}

		view.addObject("wallets", walletService.findAll());
		view.addObject("soldoutProducts", orderService.findExpiredOrdersAndSoldProducts());

		// miscellaneousService.images();

		return view;
	}

	@RequestMapping(value = "/arrangeReturn", method = RequestMethod.POST)
	public ModelAndView arrangeReturn(ApproveReturnDto dto, HttpSession session) {
		ModelAndView view = new ModelAndView("redirect:" + VIEW_ORDER_DETAILS_ADMIN + dto.getOrderIdPrimaryKey());

		User user = validationService.findAdminUserFromSession(session);

		if (user == null) {
			view.setViewName(View.HOME);
			return view;
		}

		orderService.arrangeReturn(dto);

		return view;
	}

	@RequestMapping(value = "/approveReturn", method = RequestMethod.POST)
	public ModelAndView approveReturn(ApproveReturnDto dto, HttpSession session) {
		ModelAndView view = new ModelAndView("redirect:" + VIEW_ORDER_DETAILS_ADMIN + dto.getOrderIdPrimaryKey());

		User user = validationService.findAdminUserFromSession(session);

		if (user == null) {
			view.setViewName(View.HOME);
			return view;
		}

		orderService.approveReturn(dto);

		return view;
	}

	@RequestMapping(value = "/refund", method = RequestMethod.POST)
	public ModelAndView refundOrder(RefundDto dto, HttpSession session) {
		ModelAndView view = new ModelAndView("redirect:" + VIEW_ORDER_DETAILS_ADMIN + dto.getOrderIdPrimaryKey());

		User user = validationService.findAdminUserFromSession(session);

		if (user == null) {
			view.setViewName(View.HOME);
			return view;
		}

		orderService.refundOrder(dto);

		return view;
	}

	@RequestMapping(value = "/orders")
	public ModelAndView orders(HttpSession session) {
		ModelAndView view = new ModelAndView(APPROVE_ORDERS);

		User user = validationService.findAdminUserFromSession(session);

		if (user == null) {
			view.setViewName(View.HOME);
			return view;
		}

		List<RefundDetailsDto> dtos = orderService.getCancelRefunds();
		view.addObject("refunds", dtos);

		return view;
	}

	@RequestMapping(value = "/addAdditionalImages", method = RequestMethod.POST)
	public ModelAndView addProductAdditionalImages(HttpSession session, AddAdditionalImagesDto imagesDto)
			throws IOException {
		ModelAndView view = new ModelAndView(ADMIN);

		User user = validationService.findAdminUserFromSession(session);

		if (user == null) {
			view.setViewName(View.HOME);
			return view;
		}

		productService.addAdditionalImages(imagesDto.getProductId(), imagesDto);
		FetchProductImagesDto dto = productService.getProductImages(imagesDto.getProductId());

		if (dto == null) {
			view.addObject("isProductNotPresent", true);
		} else {
			view.addObject("productId", imagesDto.getProductId());
			view.addObject("images", dto);
		}
		return view;
	}

	@RequestMapping(value = "/addPreviewAndSmallImage", method = RequestMethod.POST)
	public ModelAndView addPreviewImage(HttpSession session, AddPreviewAndSmallImage imagesDto) throws IOException {
		ModelAndView view = new ModelAndView(ADMIN);

		User user = validationService.findAdminUserFromSession(session);

		if (user == null) {
			view.setViewName(View.HOME);
			return view;
		}

		productService.addPreviewImage(imagesDto);
		FetchProductImagesDto dto = productService.getProductImages(imagesDto.getProductId());

		if (dto == null) {
			view.addObject("isProductNotPresent", true);
		} else {
			view.addObject("productId", imagesDto.getProductId());
			view.addObject("images", dto);
		}
		return view;
	}

	@RequestMapping(value = "/deleteProductAdditionalImage")
	public ModelAndView deleteProductAdditionalImage(HttpSession session,
			@RequestParam("productAdditionalImageId") Long productAdditionalImageId,
			@RequestParam("productId") Long productId) throws Exception {
		ModelAndView view = new ModelAndView(ADMIN);

		User user = validationService.findAdminUserFromSession(session);

		if (user == null) {
			view.setViewName(View.HOME);
			return view;
		}

		productColorsService.deleteImageByProductAdditionalImageId(productAdditionalImageId);

		FetchProductImagesDto dto = productService.getProductImages(productId);

		if (dto == null) {
			view.addObject("isProductNotPresent", true);
		} else {
			view.addObject("productId", productId);
			view.addObject("images", dto);
		}
		return view;
	}

	@RequestMapping(value = "/getProductImages")
	public ModelAndView viewProductImages(HttpSession session, @RequestParam("id") Long productId) {
		ModelAndView view = new ModelAndView(ADMIN);

		User user = validationService.findAdminUserFromSession(session);

		if (user == null) {
			view.setViewName(View.HOME);
			return view;
		}

		FetchProductImagesDto dto = productService.getProductImages(productId);

		if (dto == null) {
			view.addObject("isProductNotPresent", true);
		} else {
			view.addObject("productId", productId);
			view.addObject("images", dto);
		}
		return view;
	}

	@RequestMapping(value = "/vieworderdetailsasadmin/{id}")
	public ModelAndView viewOrderDetails(HttpSession session, @PathVariable("id") Long orderIdPrimaryKey) {
		ModelAndView view = new ModelAndView(VIEW_ORDER_DETAILS);

		ViewOrderDetailsDto dto = adminOrderService.createDto(orderIdPrimaryKey);

		User user = validationService.findAdminUserFromSession(session);

		if (user == null || dto == null) {
			view.setViewName(View.HOME);
			return view;
		}

		view.addObject("orderIdPrimaryKey", orderIdPrimaryKey);
		view.addObject("order", dto);

		return view;
	}

	@RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
	public ModelAndView updateOrder(HttpSession session, UpdateOrderDetailsDto dto) {
		ModelAndView view = new ModelAndView("redirect:" + VIEW_ORDER_DETAILS_ADMIN + dto.getOrderId());

		User user = validationService.findAdminUserFromSession(session);
		if (user == null) {
			view.setViewName(View.HOME);
			return view;
		}

		adminOrderService.updateOrderDetails(dto, user);
		return view;
	}

	@RequestMapping(value = "/approveordersdata", method = RequestMethod.POST)
	public @ResponseBody TablePage getUsersData(@RequestBody PaginationCriteria treq) {

		try {
			return paginator.getPage(treq);
		} catch (TablePaginationException e) {
			return null;
		}
	}
}
