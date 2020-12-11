package com.aarria.retail.web.dto.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class CheckoutDto {

	private String mobile;

	private String password;

	private String cartItems;

	private String couponCode;

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

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	@Override
	public String toString() {
		return "CheckoutDto [mobile=" + mobile + ", password=" + password + ", cartItems=" + cartItems + ", couponCode="
				+ couponCode + "]";
	}

}
