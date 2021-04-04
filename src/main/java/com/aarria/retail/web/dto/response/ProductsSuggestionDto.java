package com.aarria.retail.web.dto.response;

import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.util.Util;

public class ProductsSuggestionDto {

	private Long productId;
	private String pid;
	private String image;
	private Double price;
	private String name;
	private Double actualPrice;

	public ProductsSuggestionDto(Product product) {
		super();
		this.productId = product.getId();
		this.pid = product.getPid();
		this.image = Util.generateImageLocation(product.getListingImage());
		this.price = product.getPrice();

		this.name = Util.getShortenedName(product.getName(), 25);
		this.actualPrice = product.getActualPrice();
	}

	public Long getProductId() {
		return productId;
	}

	public String getPid() {
		return pid;
	}

	public String getImage() {
		return image;
	}

	public Double getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public Double getActualPrice() {
		return actualPrice;
	}

}
