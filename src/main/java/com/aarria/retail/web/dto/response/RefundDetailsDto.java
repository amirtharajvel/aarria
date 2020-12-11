package com.aarria.retail.web.dto.response;

public class RefundDetailsDto {

	private String orderNo;
	private String orderIdPrimaryKey;
	private String orderedDate;
	private Double totalAmount;
	private String orderStatus;
	
	public RefundDetailsDto(String orderNo, String orderIdPrimaryKey, String orderedDate, Double totalAmount, String orderStatus) {
		super();
		this.orderNo = orderNo;
		this.orderIdPrimaryKey = orderIdPrimaryKey;
		this.orderedDate = orderedDate;
		this.totalAmount = totalAmount;
		this.orderStatus = orderStatus;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderIdPrimaryKey() {
		return orderIdPrimaryKey;
	}

	public void setOrderIdPrimaryKey(String orderIdPrimaryKey) {
		this.orderIdPrimaryKey = orderIdPrimaryKey;
	}

	public String getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(String orderedDate) {
		this.orderedDate = orderedDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}
