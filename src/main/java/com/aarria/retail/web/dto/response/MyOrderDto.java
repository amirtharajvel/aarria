package com.aarria.retail.web.dto.response;

import java.util.List;

public class MyOrderDto {

	private Long orderIdPrimaryKey;

	private String orderId;

	private String receivedPerson;

	private String orderedDate;

	private String orderStatus;

	private AddressDto shippingAddress;

	private AddressDto billingAddress;

	private List<MyOrderItemsDto> items;

	private String deliveredDate;

	private String expectedDeliveredDate;

	private Double totalOrderPrice;

	private MyOrdersDeliveryDto delivery;

	private Boolean isCashOnDelivery;

	private String returnCashMethod;

	private String returnTimeLimit;

	private String pickupAddress;

	private String pickupTime;
	
	private String pickupCourier;
	
	private String pickupCourierTrackingId;

	private Boolean isAnyOfTheItemIsReturned;

	private Boolean isReturnedItemReceived;

	private String returnedDate;

	private Boolean showReturnPickup;
	
	private Boolean isReturnPickupRequested;
	
	private Boolean isReturnPickupArranged;
	
	private Double walletAmountUsed;
	
	private Double totalAmountToPay;
	
	public MyOrderDto(Long orderIdPrimaryKey, String orderId, String receivedPerson, String orderedDate,
			String orderStatus, AddressDto shippingAddress, AddressDto billingAddress, String deliveredDate,
			Double totalOrderPrice, MyOrdersDeliveryDto delivery, Boolean isCashOnDelivery, String returnCashMethod,
			String expectedDeliveredDate, String pickupAddress, String pickupTime, String returnedDate,
			Boolean isAnyOfTheItemIsReturned, Boolean isReturnedItemReceived, Double walletAmountUsed, Double totalAmountToPay) {
		super();
		this.orderIdPrimaryKey = orderIdPrimaryKey;
		this.orderId = orderId;
		this.receivedPerson = receivedPerson;
		this.orderedDate = orderedDate;
		this.orderStatus = orderStatus;
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.deliveredDate = deliveredDate;
		this.totalOrderPrice = totalOrderPrice;
		this.delivery = delivery;
		this.isCashOnDelivery = isCashOnDelivery;
		this.returnCashMethod = returnCashMethod;
		this.expectedDeliveredDate = expectedDeliveredDate;
		this.pickupAddress = pickupAddress;
		this.pickupTime = pickupTime;
		this.returnedDate = returnedDate;
		this.isAnyOfTheItemIsReturned = isAnyOfTheItemIsReturned;
		this.isReturnedItemReceived = isReturnedItemReceived;
		this.walletAmountUsed = walletAmountUsed;
		this.totalAmountToPay = totalAmountToPay;
		
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

	public Boolean getIsCashOnDelivery() {
		return isCashOnDelivery;
	}

	public void setIsCashOnDelivery(Boolean isCashOnDelivery) {
		this.isCashOnDelivery = isCashOnDelivery;
	}

	public Long getOrderIdPrimaryKey() {
		return orderIdPrimaryKey;
	}

	public void setOrderIdPrimaryKey(Long orderIdPrimaryKey) {
		this.orderIdPrimaryKey = orderIdPrimaryKey;
	}

	public String getReturnCashMethod() {
		return returnCashMethod;
	}

	public void setReturnCashMethod(String returnCashMethod) {
		this.returnCashMethod = returnCashMethod;
	}

	public String getExpectedDeliveredDate() {
		return expectedDeliveredDate;
	}

	public void setExpectedDeliveredDate(String expectedDeliveredDate) {
		this.expectedDeliveredDate = expectedDeliveredDate;
	}

	public String getReturnTimeLimit() {
		return returnTimeLimit;
	}

	public void setReturnTimeLimit(String returnTimeLimit) {
		this.returnTimeLimit = returnTimeLimit;
	}

	public String getPickupAddress() {
		return pickupAddress;
	}

	public void setPickupAddress(String pickupAddress) {
		this.pickupAddress = pickupAddress;
	}

	public String getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}

	public Boolean getIsAnyOfTheItemIsReturned() {
		return isAnyOfTheItemIsReturned;
	}

	public void setIsAnyOfTheItemIsReturned(Boolean isAnyOfTheItemIsReturned) {
		this.isAnyOfTheItemIsReturned = isAnyOfTheItemIsReturned;
	}

	public String getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(String returnedDate) {
		this.returnedDate = returnedDate;
	}

	public Boolean getIsReturnedItemReceived() {
		return isReturnedItemReceived;
	}

	public void setIsReturnedItemReceived(Boolean isReturnedItemReceived) {
		this.isReturnedItemReceived = isReturnedItemReceived;
	}

	public Boolean getShowReturnPickup() {
		return showReturnPickup;
	}

	public void setShowReturnPickup(Boolean showReturnPickup) {
		this.showReturnPickup = showReturnPickup;
	}

	public String getPickupCourier() {
		return pickupCourier;
	}

	public void setPickupCourier(String pickupCourier) {
		this.pickupCourier = pickupCourier;
	}

	public String getPickupCourierTrackingId() {
		return pickupCourierTrackingId;
	}

	public void setPickupCourierTrackingId(String pickupCourierTrackingId) {
		this.pickupCourierTrackingId = pickupCourierTrackingId;
	}

	public Boolean getIsReturnPickupRequested() {
		return isReturnPickupRequested;
	}

	public void setIsReturnPickupRequested(Boolean isReturnPickupRequested) {
		this.isReturnPickupRequested = isReturnPickupRequested;
	}

	public Boolean getIsReturnPickupArranged() {
		return isReturnPickupArranged;
	}

	public void setIsReturnPickupArranged(Boolean isReturnPickupArranged) {
		this.isReturnPickupArranged = isReturnPickupArranged;
	}

	public Double getWalletAmountUsed() {
		return walletAmountUsed;
	}

	public void setWalletAmountUsed(Double walletAmountUsed) {
		this.walletAmountUsed = walletAmountUsed;
	}

	public Double getTotalAmountToPay() {
		return totalAmountToPay;
	}

	public void setTotalAmountToPay(Double totalAmountToPay) {
		this.totalAmountToPay = totalAmountToPay;
	}

	@Override
	public String toString() {
		return "MyOrderDto [orderId=" + orderId + ", receivedPerson=" + receivedPerson + ", orderedDate=" + orderedDate
				+ ", orderStatus=" + orderStatus + ", shippingAddress=" + shippingAddress + ", billingAddress="
				+ billingAddress + ", items=" + items + ", deliveredDate=" + deliveredDate + ", totalOrderPrice="
				+ totalOrderPrice + ", delivery=" + delivery + "]";
	}

}
