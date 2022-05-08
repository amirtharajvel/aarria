package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.service.OfferService;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

    // offer codes
    private static final String SPECIAL = "SPECIAL";

    @Override
    public Double getPriceAfterCouponCodeApplied(String code, Double price) {

        if (code != null) {
            try {
                return (price - Double.valueOf(code.replace(SPECIAL, "")));

            } catch (Exception e) {
                System.out.println("Invalid coupon code " + e);
            }
        }

        return price;
    }

    @Override
    public double availOfferForWholeOrder(double total, int totalQuantity) {
        return calculateTotalAfterOffer(total, totalQuantity);
    }

    @Override
    public double calculateSavings(double total, int totalQuantity) {
        double totalSavings = total;

        return totalSavings;
    }

    @Override
    public String isValidCouponCode(boolean isValid) {

        String isValidCoupon = "Invalid Coupon code";

        if (isValid) {
            isValidCoupon = "Hurray.. Coupon code applied!!";
        }

        return isValidCoupon;
    }

    // private methods
    private static double calculateTotalAfterOffer(double total, int totalQuantity) {

        double totalAfterOffer = total;

        return totalAfterOffer;
    }

    public static void main() {

    }

}
