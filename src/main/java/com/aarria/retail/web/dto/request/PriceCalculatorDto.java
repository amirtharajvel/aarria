package com.aarria.retail.web.dto.request;

import com.aarria.retail.core.domain.Product;

public class PriceCalculatorDto {

	private Double originalPrice = 0d;
	private Double weight = 0d;
	private Double profitToBeAdded = 0d;

	public Product createProduct() {
		Product p = new Product();

		originalPrice = originalPrice == null ? 0d : originalPrice;
		profitToBeAdded = profitToBeAdded == null ? 0d : profitToBeAdded;
		weight = weight == null ? 0d : weight;

		p.setOriginalPrice(originalPrice);
		p.setWeight(weight);
		p.setProfitToBeAdded(profitToBeAdded);

		return p;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getProfitToBeAdded() {
		return profitToBeAdded;
	}

	public void setProfitToBeAdded(Double profitToBeAdded) {
		this.profitToBeAdded = profitToBeAdded;
	}

}
