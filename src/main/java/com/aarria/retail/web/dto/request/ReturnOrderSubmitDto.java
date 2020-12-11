package com.aarria.retail.web.dto.request;

import java.util.List;

public class ReturnOrderSubmitDto {
	private String orderId;
	private List<Long> itemsToReturn;
	private String pickupAddress;
	private String pickupTime;
	private String pickupDate;
	private Integer returnCashMethod;
	private Long selectedAccountForRefund;

	public List<Long> getItemsToReturn() {
		return itemsToReturn;
	}

	public void setItemsToReturn(List<Long> itemsToReturn) {
		this.itemsToReturn = itemsToReturn;
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

	public Integer getReturnCashMethod() {
		return returnCashMethod;
	}

	public void setReturnCashMethod(Integer returnCashMethod) {
		this.returnCashMethod = returnCashMethod;
	}

	public Long getSelectedAccountForRefund() {
		return selectedAccountForRefund;
	}

	public void setSelectedAccountForRefund(Long selectedAccountForRefund) {
		this.selectedAccountForRefund = selectedAccountForRefund;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(String pickupDate) {
		this.pickupDate = pickupDate;
	}

	@Override
	public String toString() {
		return "ReturnOrderSubmitDto [orderId=" + orderId + ", itemsToReturn=" + itemsToReturn + ", pickupAddress="
				+ pickupAddress + ", pickupTime=" + pickupTime + ", pickupDate=" + pickupDate + ", returnCashMethod="
				+ returnCashMethod + ", selectedAccountForRefund=" + selectedAccountForRefund + "]";
	}

}
