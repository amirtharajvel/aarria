package com.aarria.retail.web.dto.request;

import java.util.List;

public class AddToCartDto {

	private String addToCartType; // buyNow or Checkout

	private List<AddToCartItemsDto> items;

	public String getAddToCartType() {
		return addToCartType;
	}

	public void setAddToCartType(String addToCartType) {
		this.addToCartType = addToCartType;
	}

	public List<AddToCartItemsDto> getItems() {
		return items;
	}

	public void setItems(List<AddToCartItemsDto> items) {
		this.items = items;
	}
}
