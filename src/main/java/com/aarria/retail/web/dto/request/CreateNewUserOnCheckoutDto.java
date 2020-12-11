package com.aarria.retail.web.dto.request;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CreateNewUserOnCheckoutDto {

	private String mobile;

	private String password;

	private String cartItems;

	public List<AddToCartItemsDto> getCartItemObject(Gson gson) {
		return gson.fromJson(cartItems, new TypeToken<List<AddToCartItemsDto>>() {
		}.getType());
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCartItems() {
		return cartItems;
	}

	public void setCartItems(String cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public String toString() {
		return "CreateNewUserOnCheckoutDto [mobile=" + mobile + ", password=" + password + ", cartItems=" + cartItems
				+ "]";
	}
}
