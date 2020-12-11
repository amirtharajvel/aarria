package com.aarria.retail.core.util.business;

public class ShippingCharges {

	public static Double findShippingPriceSlab(Double weightInGrams) {

		Double charge = null;
		if (weightInGrams == null) {
			return 40d;
		}

		if (weightInGrams <= 50) {
			charge = 35d;
		} else if (weightInGrams > 50 && weightInGrams <= 200) {
			charge = 70d;
		} else if (weightInGrams > 200 && weightInGrams <= 500) {
			charge = 90d;
		} else if (weightInGrams > 500 && weightInGrams <= 1000) {
			charge = 140d;
		} else if (weightInGrams > 1000 && weightInGrams <= 1500) {
			charge = 190d;
		} else if (weightInGrams > 1500 && weightInGrams <= 2000) {
			charge = 240d;
		} else if (weightInGrams > 2000 && weightInGrams <= 2500) {
			charge = 290d;
		} else if (weightInGrams > 2500 && weightInGrams <= 3000) {
			charge = 340d;
		} else if (weightInGrams > 3000 && weightInGrams <= 3500) {
			charge = 390d;
		} else if (weightInGrams > 3500 && weightInGrams <= 4000) {
			charge = 440d;
		} else if (weightInGrams > 4000 && weightInGrams <= 4500) {
			charge = 490d;
		} else if (weightInGrams > 4500 && weightInGrams <= 5000) {
			charge = 540d;
		} else if (weightInGrams > 5000 && weightInGrams <= 5500) {
			charge = 590d;
		} else if (weightInGrams > 5500 && weightInGrams <= 6000) {
			charge = 640d;
		} else if (weightInGrams > 6000 && weightInGrams <= 6500) {
			charge = 690d;
		} else if (weightInGrams > 6500 && weightInGrams <= 7000) {
			charge = 740d;
		} else if (weightInGrams > 7000 && weightInGrams <= 7500) {
			charge = 790d;
		} else if (weightInGrams > 7500 && weightInGrams <= 8000) {
			charge = 840d;
		} else if (weightInGrams > 8000 && weightInGrams <= 8500) {
			charge = 890d;
		} else if (weightInGrams > 8500 && weightInGrams <= 9000) {
			charge = 940d;
		} else if (weightInGrams > 9000 && weightInGrams <= 9500) {
			charge = 990d;
		} else if (weightInGrams > 9500 && weightInGrams <= 10000) {
			charge = 1040d;
		}

		return charge;
	}

}