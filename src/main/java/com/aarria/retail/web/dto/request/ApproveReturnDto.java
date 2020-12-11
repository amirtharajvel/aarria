package com.aarria.retail.web.dto.request;

public class ApproveReturnDto {

	private Long orderIdPrimaryKey;

	private String returnTrackingId;

	private String returnCourierServiceName;
	
	private Double returnShippingCharge;
	
	private Boolean isReturnShippingNotAvailable = false;

	private String returnPickupDateAndTime;
	
	private String returnPickupAddress;
	
	public Long getOrderIdPrimaryKey() {
		return orderIdPrimaryKey;
	}

	public void setOrderIdPrimaryKey(Long orderIdPrimaryKey) {
		this.orderIdPrimaryKey = orderIdPrimaryKey;
	}

	public String getReturnTrackingId() {
		return returnTrackingId;
	}

	public void setReturnTrackingId(String returnTrackingId) {
		this.returnTrackingId = returnTrackingId;
	}

	public String getReturnCourierServiceName() {
		return returnCourierServiceName;
	}

	public void setReturnCourierServiceName(String returnCourierServiceName) {
		this.returnCourierServiceName = returnCourierServiceName;
	}

	public Double getReturnShippingCharge() {
		return returnShippingCharge;
	}

	public void setReturnShippingCharge(Double returnShippingCharge) {
		this.returnShippingCharge = returnShippingCharge;
	}

	public Boolean getIsReturnShippingNotAvailable() {
		return isReturnShippingNotAvailable;
	}

	public void setIsReturnShippingNotAvailable(Boolean isReturnShippingNotAvailable) {
		this.isReturnShippingNotAvailable = isReturnShippingNotAvailable;
	}

	public String getReturnPickupDateAndTime() {
		return returnPickupDateAndTime;
	}

	public void setReturnPickupDateAndTime(String returnPickupDateAndTime) {
		this.returnPickupDateAndTime = returnPickupDateAndTime;
	}

	public String getReturnPickupAddress() {
		return returnPickupAddress;
	}

	public void setReturnPickupAddress(String returnPickupAddress) {
		this.returnPickupAddress = returnPickupAddress;
	}

}
