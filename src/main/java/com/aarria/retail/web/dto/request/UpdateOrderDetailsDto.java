package com.aarria.retail.web.dto.request;

import com.aarria.retail.core.domain.Delivery;
import com.aarria.retail.core.domain.Order;
import com.aarria.retail.core.util.Enum.OrderStatus;

import java.util.Date;

public class UpdateOrderDetailsDto {

	private String orderStatus;
	private String trackingName;
	private String trackingId;
	private String shipper;
	private Double shippingCharge;
	// It's the primary key
	private Long orderId;
	private Integer returnCashMethod;
	private Long selectedAccountForRefund;

	public static Order createOrderAndDelivery(UpdateOrderDetailsDto dto, Order order) {
		order.setStatus(OrderStatus.valueOf(dto.getOrderStatus()).ordinal());

		if (dto.getOrderStatus().equals(OrderStatus.DELIVERED.name())) {
			order.setIsDelivered(true);
		}

		if (dto.getSelectedAccountForRefund() != null) {
			order.setSelectedAccountForRefund(Long.valueOf(dto.getSelectedAccountForRefund()));
		}

		if (dto.getReturnCashMethod() != null) {
			order.setReturnCashMethod(dto.getReturnCashMethod());
		}

		Delivery delivery = order.getDelivery();
		delivery.setTrackingId(dto.getTrackingId());
		delivery.setShipper(dto.getShipper());
		delivery.setShippingCharge(dto.getShippingCharge());
		delivery.setShippingDate(calculateShippingDate());
		delivery.setTrackingName(dto.getTrackingName());

		if (order.getStatus().equals(OrderStatus.DELIVERED.ordinal())) {
			delivery.setDeliveredDate(new Date());
		}

		return order;
	}

	private static Date calculateShippingDate() {
		return new Date(); // TODO update as per shipper
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public Double getShippingCharge() {
		return shippingCharge;
	}

	public void setShippingCharge(Double shippingCharge) {
		this.shippingCharge = shippingCharge;
	}

	public Integer getReturnCashMethod() {
		return returnCashMethod;
	}

	public void setReturnCashMethod(Integer returnCashMethod) {
		this.returnCashMethod = returnCashMethod;
	}

	public String getTrackingName() {
		return trackingName;
	}

	public void setTrackingName(String trackingName) {
		this.trackingName = trackingName;
	}

	public Long getSelectedAccountForRefund() {
		return selectedAccountForRefund;
	}

	public void setSelectedAccountForRefund(Long selectedAccountForRefund) {
		this.selectedAccountForRefund = selectedAccountForRefund;
	}

	@Override
	public String toString() {
		return "UpdateOrderDetailsDto [orderStatus=" + orderStatus + ", trackingName=" + trackingName + ", trackingId="
				+ trackingId + ", shipper=" + shipper + ", shippingCharge=" + shippingCharge + ", orderId=" + orderId
				+ ", returnCashMethod=" + returnCashMethod + ", selectedAccountForRefund=" + selectedAccountForRefund
				+ "]";
	}

}
