package com.aarria.retail.web.dto.response;

public class PincodeCheckDto {

	// "Your expected delivery date is .." in case of success
	private String expectedDeliveryDate;

	// "Your expected delivery date" or "Sorry.."
	private String message;

	// "fail" or "success"
	private String status;

	public PincodeCheckDto(String expectedDeliveryDate, String message, String status) {
		super();
		this.expectedDeliveryDate = expectedDeliveryDate;
		this.message = message;
		this.status = status;
	}

	public String getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(String expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PincodeCheckDto [expectedDeliveryDate=" + expectedDeliveryDate + ", message=" + message + ", status="
				+ status + "]";
	}

}
