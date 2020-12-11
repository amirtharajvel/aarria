package com.aarria.retail.web.dto.request;

public class ReturnInputDto {

	private Long orderIdPrimaryKey;
	private Long itemId;
	// myorders or vieworderdetails
	private String fromPage;
	private Integer returnCashMethod;
	private String comment;

	public Long getOrderIdPrimaryKey() {
		return orderIdPrimaryKey;
	}

	public void setOrderIdPrimaryKey(Long orderIdPrimaryKey) {
		this.orderIdPrimaryKey = orderIdPrimaryKey;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "ReturnInputDto [orderIdPrimaryKey=" + orderIdPrimaryKey + ", fromPage=" + fromPage
				+ ", returnCashMethod=" + returnCashMethod + ", comment=" + comment + "]";
	}

}
