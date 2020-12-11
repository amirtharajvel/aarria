package com.aarria.retail.web.dto.response;

import com.aarria.retail.core.domain.Product;

public class CreateHomePageDto {

	private String image;

	private Product product;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
