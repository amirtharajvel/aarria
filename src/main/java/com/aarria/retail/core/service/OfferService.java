package com.aarria.retail.core.service;

public interface OfferService {

	public double availOfferForWholeOrder(double total, int totalQuantity);

	double calculateSavings(double total, int totalQuantity);

	String isValidCouponCode(double total, String couponCode);
	
	String getCouponCode(String code);
}
