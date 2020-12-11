package com.aarria.retail.web.dto.request;

public class RefundDto {

	private Long orderIdPrimaryKey;
	private Double totalAmountToBeRefundedForOrder;
	private String refundReceiptNumber;
	
	public RefundDto() {
		super();
	}
	
	public RefundDto(Long orderIdPrimaryKey, Double totalAmountToBeRefundedForOrder, String refundReceiptNumber) {
		super();
		this.orderIdPrimaryKey = orderIdPrimaryKey;
		this.totalAmountToBeRefundedForOrder = totalAmountToBeRefundedForOrder;
		this.refundReceiptNumber = refundReceiptNumber;
	}

	public Long getOrderIdPrimaryKey() {
		return orderIdPrimaryKey;
	}

	public void setOrderIdPrimaryKey(Long orderIdPrimaryKey) {
		this.orderIdPrimaryKey = orderIdPrimaryKey;
	}

	public Double getTotalAmountToBeRefundedForOrder() {
		return totalAmountToBeRefundedForOrder;
	}

	public void setTotalAmountToBeRefundedForOrder(Double totalAmountToBerefundedForOrder) {
		this.totalAmountToBeRefundedForOrder = totalAmountToBerefundedForOrder;
	}

	public String getRefundReceiptNumber() {
		return refundReceiptNumber;
	}

	public void setRefundReceiptNumber(String refundReceiptNumber) {
		this.refundReceiptNumber = refundReceiptNumber;
	}

	@Override
	public String toString() {
		return "RefundDto [orderIdPrimaryKey=" + orderIdPrimaryKey + ", totalAmount=" + totalAmountToBeRefundedForOrder
				+ "]";
	}

}
