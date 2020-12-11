package com.aarria.retail.web.dto.request;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LoginDto {

	@NotBlank
	@Size(min = 1, max = 10)
	private String mobile;

	@Size(min = 1, max = 25)
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
		return "LoginDto [mobile=" + mobile + ", password=" + password + ", cartItems=" + cartItems + "]";
	}
	
}
