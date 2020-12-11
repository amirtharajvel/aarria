package com.aarria.retail.web.dto.response;

import java.util.List;

import com.aarria.retail.web.dto.request.CheckoutCartDto;

public class CheckoutResponseDto {

	private Integer totalQuantity;
	private List<CheckoutCartDto> items;
	private String couponCodeAppliedMessage;

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public List<CheckoutCartDto> getItems() {
		return items;
	}

	public void setItems(List<CheckoutCartDto> items) {
		this.items = items;
	}

	public String getCouponCodeAppliedMessage() {
		return couponCodeAppliedMessage;
	}

	public void setCouponCodeAppliedMessage(String couponCodeAppliedMessage) {
		this.couponCodeAppliedMessage = couponCodeAppliedMessage;
	}

}
