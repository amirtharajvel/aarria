package com.aarria.retail.core.util.business;

import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.util.Enum.PriceBreakup;

public class ProductPrice {

	private static final double GST_FOR_CLOTHING = 0.05d;
	private static final double GST_FOR_SHIPPING = 0.18d;
	private static final int PACKAGING_CHARGES = 20;
	private static final String DIVIDER = "-";

	public static Integer getFinalPrice(Product product) {
		addProfit(product);
		addShippingCharge(product);
		addGSTForShippingCharge(product);
		addGST(product);
		addPackaging(product);
		addPaymentGatewayCommission(product);

		if (product.getPrice() == null) {
			throw new RuntimeException("Why product.getPrice is null while calculating price?");
		}

		Long outputPrice = Math.round(product.getPrice());
		return outputPrice.intValue();
	}

	private static void addPackaging(Product product) {
		product.setPrice(product.getPrice() + PACKAGING_CHARGES);
		product.setPriceBreakup(product.getPriceBreakup() + PACKAGING_CHARGES + "" + PriceBreakup.PACK + DIVIDER);
	}

	private static void addProfit(Product product) {
		product.setPrice(product.getOriginalPrice() + product.getProfitToBeAdded());
		product.setPriceBreakup(product.getProfitToBeAdded() + "" + PriceBreakup.PROFIT + DIVIDER);
	}

	private static void addShippingCharge(Product product) {
		Double shippingCharge = ShippingCharges.findShippingPriceSlab(product.getWeight());
		product.setPrice(product.getPrice() + shippingCharge);
		product.setPriceBreakup(product.getPriceBreakup() + PriceBreakup.SHIP + "(" + product.getPrice() + ")"
				+ shippingCharge + DIVIDER);
	}

	private static void addGSTForShippingCharge(Product product) {
		Double shippingCharge = ShippingCharges.findShippingPriceSlab(product.getWeight());
		Double shippingChargeGST = shippingCharge * GST_FOR_SHIPPING;
		product.setPriceBreakup(product.getPriceBreakup() + PriceBreakup.SHIPGST + "(" + shippingCharge + ")"
				+ shippingChargeGST + DIVIDER);
		product.setPrice(product.getPrice() + shippingChargeGST);

	}

	private static void addGST(Product product) {

		double gstPercentage = GST_FOR_CLOTHING;
		if (product.getIgstRate() != null && product.getIgstRate() > 0.05) {
			gstPercentage = product.getIgstRate();
		}

		Double shippingCharge = ShippingCharges.findShippingPriceSlab(product.getWeight());
		Double shippingChargeGST = shippingCharge * gstPercentage;

		Double totalDeduction = shippingCharge + shippingChargeGST;

		Double paymentGatewayCommission = 0.03d;

		// see if after the GST the product price crosses 1000 rs then calculate
		// for 12 percentage
		Double igst = product.getPrice() * gstPercentage * paymentGatewayCommission;
		double gstToBeCalculated = product.getPrice() - totalDeduction;
		if (igst > 1000) {
			igst = gstToBeCalculated * 0.12d;
		} else {
			igst = gstToBeCalculated * gstPercentage;
		}

		product.setPriceBreakup(
				product.getPriceBreakup() + PriceBreakup.GST + "(" + gstToBeCalculated + ")" + igst + DIVIDER);
		product.setPrice(product.getPrice() + igst);

	}

	private static void addPaymentGatewayCommission(Product product) {
		Double paymentGatewayCommission = product.getPrice() * 0.03d;
		product.setPriceBreakup(product.getPriceBreakup() + PriceBreakup.PAY + "(" + product.getPrice() + ")"
				+ paymentGatewayCommission + DIVIDER);
		product.setPrice(product.getPrice() + paymentGatewayCommission);

	}

	public static void main(String[] args) {
		Product product = new Product();
		product.setOriginalPrice(1145d);
		product.setWeight(1400d);
		product.setProfitToBeAdded(350d);

		// addProfit(product);// 30
		// addShippingCharge(product);// 90
		// addGSTForShippingCharge(product);// 4.5
		// addPaymentGatewayCommission(product);//
		// addGST(product);
		//
		System.out.println(ProductPrice.getFinalPrice(product));
		System.out.println(product.getPrice());
		System.out.println(product.getPriceBreakup());

	}
}
