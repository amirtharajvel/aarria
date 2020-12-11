package com.aarria.retail.web.dto.request;

import javax.validation.constraints.Size;

public class CancelOrderDto {

	private Long orderIdPrimaryKey;
	// myorders or vieworderdetails
	private String fromPage;
	private Integer returnCashMethod;
	private Long selectedAccountForRefund;

	@Size(max = 100, min = 10)
	private String comments;

	public CancelOrderDto() {
		super();
	}

	public CancelOrderDto(Long orderIdPrimaryKey, String fromPage, Integer returnCashMethod,
			Long selectedAccountForRefund, String comments) {
		super();
		this.orderIdPrimaryKey = orderIdPrimaryKey;
		this.fromPage = fromPage;
		this.returnCashMethod = returnCashMethod;
		this.selectedAccountForRefund = selectedAccountForRefund;
		this.comments = comments;
	}

	public String getFromPage() {
		return fromPage;
	}

	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}

	public Integer getReturnCashMethod() {
		return returnCashMethod;
	}

	public void setReturnCashMethod(Integer returnCashMethod) {
		this.returnCashMethod = returnCashMethod;
	}

	public Long getOrderIdPrimaryKey() {
		return orderIdPrimaryKey;
	}

	public void setOrderIdPrimaryKey(Long orderIdPrimaryKey) {
		this.orderIdPrimaryKey = orderIdPrimaryKey;
	}

	public Long getSelectedAccountForRefund() {
		return selectedAccountForRefund;
	}

	public void setSelectedAccountForRefund(Long selectedAccountForRefund) {
		this.selectedAccountForRefund = selectedAccountForRefund;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "CancelOrderDto [orderIdPrimaryKey=" + orderIdPrimaryKey + ", fromPage=" + fromPage
				+ ", returnCashMethod=" + returnCashMethod + ", selectedAccountForRefund=" + selectedAccountForRefund
				+ ", comments=" + comments + "]";
	}

}
