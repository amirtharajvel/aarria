package com.aarria.retail.core.service;

public interface OfferService {

	double availOfferForWholeOrder(double total, int totalQuantity);

	double calculateSavings(double total, int totalQuantity);

	String isValidCouponCode(boolean couponCode);
	
	Double getPriceAfterCouponCodeApplied(String code, Double price);
}
