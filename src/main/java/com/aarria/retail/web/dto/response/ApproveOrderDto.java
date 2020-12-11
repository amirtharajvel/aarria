package com.aarria.retail.web.dto.response;

import java.util.List;

public class ApproveOrderDto {

	private String orderId;

	private String receivedPerson;

	private String orderedDate;

	private String orderStatus;

	private AddressDto shippingAddress;

	private AddressDto billingAddress;

	private List<MyOrderItemsDto> items;

	private String deliveredDate;

	private Double totalOrderPrice;
	
	private MyOrdersDeliveryDto delivery;
	
	public ApproveOrderDto(String orderId, String receivedPerson,
			String orderedDate, String orderStatus, AddressDto shippingAddress,
			AddressDto billingAddress, String deliveredDate, Double totalOrderPrice, MyOrdersDeliveryDto delivery) {
		super();
		this.orderId = orderId;
		this.receivedPerson = receivedPerson;
		this.orderedDate = orderedDate;
		this.orderStatus = orderStatus;
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.deliveredDate = deliveredDate;
		this.totalOrderPrice = totalOrderPrice;
		this.delivery = delivery;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getReceivedPerson() {
		return receivedPerson;
	}

	public void setReceivedPerson(String receivedPerson) {
		this.receivedPerson = receivedPerson;
	}

	public String getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(String orderedDate) {
		this.orderedDate = orderedDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<MyOrderItemsDto> getItems() {
		return items;
	}

	public void setItems(List<MyOrderItemsDto> items) {
		this.items = items;
	}

	public AddressDto getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(AddressDto shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public AddressDto getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(AddressDto billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(String deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	public Double getTotalOrderPrice() {
		return totalOrderPrice;
	}

	public void setTotalOrderPrice(Double totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}

	public MyOrdersDeliveryDto getDelivery() {
		return delivery;
	}

	public void setDelivery(MyOrdersDeliveryDto delivery) {
		this.delivery = delivery;
	}

	@Override
	public String toString() {
		return "MyOrderDto [orderId=" + orderId + ", receivedPerson=" + receivedPerson + ", orderedDate=" + orderedDate
				+ ", orderStatus=" + orderStatus + ", shippingAddress=" + shippingAddress + ", billingAddress="
				+ billingAddress + ", items=" + items + ", deliveredDate=" + deliveredDate + ", totalOrderPrice="
				+ totalOrderPrice + ", delivery=" + delivery + "]";
	}
	
}
