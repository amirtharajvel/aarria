package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.service.OfferService;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

	// offer codes
	private static final String FALLS_200 = "FALLS200";
	private static final String BIGTREAT = "BIGTREAT";

	@Override
	public String getCouponCode(String code) {
		if (code != null) {
			if (code.equals(FALLS_200)) {
				return FALLS_200;
			} else if (code.equals(BIGTREAT)) {
				return BIGTREAT;
			}
		}

		return null;
	}

	@Override
	public double availOfferForWholeOrder(double total, int totalQuantity) {
		return calculateTotalAfterOffer(total, totalQuantity);
	}

	@Override
	public double calculateSavings(double total, int totalQuantity) {
		double totalSavings = total;
		if (total > 1999) {
			totalSavings = savings500(total, totalQuantity);
		} else {
			totalSavings = savings200And50(total, totalQuantity);
		}

		return totalSavings;
	}

	@Override
	public String isValidCouponCode(double total, String couponCode) {

		String isValidCoupon = "Invalid Coupon code";
		if (total > 1999
				&& (couponCode.trim().equalsIgnoreCase(BIGTREAT) || couponCode.trim().equalsIgnoreCase(FALLS_200))) {
			isValidCoupon = "Hurray.. Coupon code applied!!";
		} else if (total < 2000 && couponCode.trim().equalsIgnoreCase(FALLS_200)) {
			isValidCoupon = "Hurray.. Coupon code applied!! Buy Any 2 to get Rs.200 Off. Buy above Rs.2000 to get Rs.500 off";
		} else if (couponCode.trim().equalsIgnoreCase(BIGTREAT)) {
			isValidCoupon = "Please purchase above Rs.2000 to avail this offer. Apply FALLS200 to avail other discounts.";
		}

		return isValidCoupon;
	}

	// private methods
	private static double calculateTotalAfterOffer(double total, int totalQuantity) {

		double totalAfterOffer = total;
		if (total > 1999) {
			totalAfterOffer = offer2000(total, totalQuantity);
		} else {
			totalAfterOffer = offer200And50(total, totalQuantity);
		}

		return totalAfterOffer;
	}

	private static double offer200And50(double total, int totalQuantity) {
		int howmanytwosets = totalQuantity / 2;
		double totalAfterOffer = total - (howmanytwosets * 200);

		if (totalQuantity % 2 != 0) {
			totalAfterOffer = totalAfterOffer - 50;
		}
		return totalAfterOffer;
	}

	private static double offer2000(double total, int totalQuantity) {
		return total - 500;
	}

	// calculate savings
	private double savings200And50(double total, int totalQuantity) {
		return totalQuantity % 2 == 0 ? ((totalQuantity / 2) * 200) : (((totalQuantity / 2) * 200) + 50);
	}

	private double savings500(double total, int totalQuantity) {
		return 500;
	}

	public static void main() {

	}

}
