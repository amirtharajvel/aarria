package com.aarria.retail.web.dto.response;

import com.aarria.retail.core.domain.Product;

import java.util.List;

public class ProductsWithAttributesDto {

	private Long count;
	private List<Product> products;
	private Double minPrice;
	private Double maxPrice;
	
	public ProductsWithAttributesDto() {
		super();
	}

	public ProductsWithAttributesDto(Long count, List<Product> products, Double minPrice, Double maxPrice) {
		super();
		this.count = count;
		this.products = products;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	
}
