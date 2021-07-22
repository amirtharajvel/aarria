package com.aarria.retail.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aarria.retail.core.domain.Item;
import com.aarria.retail.core.domain.Order;
import com.aarria.retail.core.domain.ProductImage;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.service.AdminOrderService;
import com.aarria.retail.core.service.BankAccountService;
import com.aarria.retail.core.service.MessageService;
import com.aarria.retail.core.service.OrderService;
import com.aarria.retail.core.service.ProductImageService;
import com.aarria.retail.core.service.UserService;
import com.aarria.retail.core.util.Constants;
import com.aarria.retail.core.util.Enum.ItemStatus;
import com.aarria.retail.core.util.Enum.OrderStatus;
import com.aarria.retail.core.util.Enum.ReturnCashMethod;
import com.aarria.retail.core.util.Util;
import com.aarria.retail.web.dto.request.CancelOrderDto;
import com.aarria.retail.web.dto.request.UpdateOrderDetailsDto;
import com.aarria.retail.web.dto.response.BankAccountDetailsDto;
import com.aarria.retail.web.dto.response.ViewDeliveryDto;
import com.aarria.retail.web.dto.response.ViewOrderDetailsDto;
import com.aarria.retail.web.dto.response.ViewOrderItemDetailsDto;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductImageService productColorsService;

	@Autowired
	private BankAccountService bankAccountService;

	@Autowired
	private MessageService messageService;

	@Override
	public ViewOrderDetailsDto createDto(Long orderIdPrimaryKey) {
		Order order = orderService.findOneWithItems(orderIdPrimaryKey);

		if (order == null) {
			return null;
		}

		User user = userService.findOne(order.getUser());
		ViewOrderDetailsDto dto = create(order);

		if (user != null) {
			List<BankAccountDetailsDto> bankAccounts = bankAccountService.getAllBankAccountsForUser(user);
			if (order.getSelectedAccountForRefund() != null && CollectionUtils.isNotEmpty(bankAccounts)) {
				bankAccounts.forEach(b -> {
					if (b.getIdentifier().equals(order.getSelectedAccountForRefund())) {
						dto.setSelectedBankAccountForRefund(b);
					}
				});
			}
			dto.setBankAccounts(bankAccounts);
		}

		return dto;
	}

	private ViewOrderDetailsDto create(Order order) {
		ViewOrderDetailsDto dto = new ViewOrderDetailsDto();

		User user = userService.findOne(order.getUser());
		dto.setMobile(user.getMobile());

		String orderedDate = null;
		if (order.getOrderedDate() != null) {
			orderedDate = new SimpleDateFormat("E, dd MMM YYYY HH:mm:ss Z").format(order.getOrderedDate());
		}

		dto.setId(order.getId());
		dto.setOrderId(order.getOrderId());
		dto.setOrderedDate(orderedDate);
		dto.setStatus(OrderStatus.getString(order.getStatus()));
		dto.setShowReturnPickup(Util.isShowReturnPickupForAdmin(order));
		dto.setShowRefundButton(isShowRefundButton(order));
		dto.setIsReturnPickupArranged(order.getIsReturnPickupArranged());
		dto.setShowArrangeReturnButton(isShowArrangeReturnButton(order));

		if (order.getIsCashOnDelivery() != null) {
			dto.setIsCashOnDelivery(order.getIsCashOnDelivery());
		}

		dto.setDelivery(ViewDeliveryDto.createDtoFromDelivery(order.getDelivery()));
		getItems(order, dto);

		return dto;

	}

	private Boolean isShowArrangeReturnButton(Order order) {

		if (order.getStatus().equals(OrderStatus.RETURN_PICKUP_REQUESTED.ordinal())
				|| order.getIsAnyOfTheItemIsReturned() != null && order.getIsAnyOfTheItemIsReturned().equals(true)
						&& !isShowRefundButton(order)) {
			return true;
		}

		return false;
	}

	private Boolean isShowRefundButton(Order order) {

		if (order.getStatus().equals(OrderStatus.REFUND_INITIATED.ordinal())) {
			return true;
		}

		if (CollectionUtils.isNotEmpty(order.getOrderedItems())) {
			for (Item o : order.getOrderedItems()) {
				if (o.getStatus().equals(ItemStatus.REFUND_INITIATED.ordinal())) {
					return true;
				}
			}
		}

		return false;
	}

	private void getItems(Order order, ViewOrderDetailsDto dto) {
		double totalOrderAmount = 0.0d;
		double totalAmountToBeRefunded = 0d;
		for (Item item : order.getOrderedItems()) {
			Set<ProductImage> images = productColorsService.getAllProductColorsByProduct(item.getProduct());
			item.getProduct().setColorImages(images);

			if (Util.isItemPurchased(item)) {
				totalOrderAmount += item.getTotalSoldPrice();
			}

			if (Util.getCancelRefundStatusOrdinalsForItems().contains(item.getStatus())) {
				totalAmountToBeRefunded += item.getTotalSoldPrice();
			}

			if (Util.getReturnStatusForItems().contains(item.getStatus())) {
				dto.setIsAnyOfTheItemIsReturned(true);
			}
		}

		if (Util.getCancelRefundStatusOrdinalsForOrders().contains(order.getStatus())) {
			totalAmountToBeRefunded = totalOrderAmount;
		}

		Integer returnCash = Util.getReturnCashMethod(order);
		String returnCashMethod = returnCash == null ? null : ReturnCashMethod.getString(returnCash);

		dto.setReturnCashMethod(returnCashMethod);
		dto.setTotalRefundableAmount(totalAmountToBeRefunded);
		dto.setItems(ViewOrderItemDetailsDto.createFromOrder(order));
		dto.setTotalOrderAmount(totalOrderAmount);
	}

	@Override
	public void updateOrderDetails(UpdateOrderDetailsDto dto, User user) {
		Order order = orderService.findOneWithItems(dto.getOrderId());
		order = UpdateOrderDetailsDto.createOrderAndDelivery(dto, order);

		user = order.getUser();

		if (order.getStatus().equals(OrderStatus.CANCELLED_WHILE_PACKAGING.ordinal())) {
			CancelOrderDto cancelDto = new CancelOrderDto(order.getId(), Constants.VIEW_ORDER_DETAILS,
					dto.getReturnCashMethod(), null, "Cancelled by admin");

			messageService.sendEmailToAdmin(order, user.getMobile());
			orderService.cancelOrder(cancelDto);
			return;

		}

		if (order.getStatus().equals(OrderStatus.DELIVERED.ordinal())) {
			messageService.sendSms( order,
					user.getMobile(), false);
		}

		if (order.getStatus().equals(OrderStatus.DISPATCHED.ordinal())) {
			messageService.sendSms(order,
					user.getMobile(), false);
		}

		orderService.save(order);

	}

}
