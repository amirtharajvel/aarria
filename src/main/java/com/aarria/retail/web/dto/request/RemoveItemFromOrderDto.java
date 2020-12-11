package com.aarria.retail.web.dto.request;

public class RemoveItemFromOrderDto {

	private Long orderIdPrimaryKey;
	private Long itemId;
	private String fromPage;
	private Integer returnCashMethod;
	private Long selectedAccountForRefund;
	
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

	public Long getSelectedAccountForRefund() {
		return selectedAccountForRefund;
	}

	public void setSelectedAccountForRefund(Long selectedAccountForRefund) {
		this.selectedAccountForRefund = selectedAccountForRefund;
	}

	@Override
	public String toString() {
		return "RemoveItemFromOrderDto [orderIdPrimaryKey=" + orderIdPrimaryKey + ", itemId=" + itemId + ", fromPage="
				+ fromPage + ", returnCashMethod=" + returnCashMethod + ", selectedAccountForRefund="
				+ selectedAccountForRefund + "]";
	}

}
