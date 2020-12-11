package com.aarria.retail.core.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.aarria.retail.core.domain.Order;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.web.dto.request.ApproveReturnDto;
import com.aarria.retail.web.dto.request.CancelOrderDto;
import com.aarria.retail.web.dto.request.OrderDetailDto;
import com.aarria.retail.web.dto.request.RefundDto;
import com.aarria.retail.web.dto.request.RemoveItemFromOrderDto;
import com.aarria.retail.web.dto.request.ReturnOrderSubmitDto;
import com.aarria.retail.web.dto.response.OrderDetailOutputDto;
import com.aarria.retail.web.dto.response.PaymentSuccessDTO;
import com.aarria.retail.web.dto.response.PrepareOrderResultDto;
import com.aarria.retail.web.dto.response.RefundDetailsDto;
import com.aarria.retail.web.dto.response.ReturnOrderDetailsDto;
import com.aarria.retail.web.dto.response.ReturnOrderSubmitResultDto;
import com.aarria.retail.web.dto.response.SoldoutProductDto;

public interface OrderService {

	List<Order> getOrders(Long userId, Integer status);

	List<Order> getOrders(Long userId);

	Order findByOrderNo(String orderNo);

	PaymentSuccessDTO getPaymentSuccessDTO(String orderNo, User user);

	boolean cancelOrder(CancelOrderDto cancelOrderDto);

	Order findOneWithItems(Long id);

	Order findOneById(Long id);

	Order save(Order order);

	List<OrderDetailOutputDto> getOrders(OrderDetailDto dto);

	boolean removeItemFromOrder(RemoveItemFromOrderDto removeItemFromOrderDto);

	List<RefundDetailsDto> getCancelRefunds();

	String refundOrder(RefundDto dto);

	ReturnOrderDetailsDto getReturnOrderDetails(String orderId, User user);

	ReturnOrderSubmitResultDto placeReturnOrder(ReturnOrderSubmitDto dto, User user);

	void approveReturn(ApproveReturnDto dto);

	void confirmCODOrder(User user, String orderId);

	PrepareOrderResultDto prepareOrder(Integer paymentMode, User user);

	void confirmNONCODOrderAfterPaymentIsMade(User user, String orderId, HttpServletRequest request);

	void arrangeReturn(ApproveReturnDto dto);

	Order findOneWithItems(String orderId, User user);

	List<SoldoutProductDto> findExpiredOrdersAndSoldProducts();
	
	
}
