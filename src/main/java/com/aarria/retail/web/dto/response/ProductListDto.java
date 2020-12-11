package com.aarria.retail.web.dto.response;

import com.aarria.retail.core.domain.Product;

import java.util.List;

public class ProductListDto {

	private long pageCount;
	private List<Product> products;
	private Double minPrice;
	private Double maxPrice;
	private int resultCount;

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Double getMinPrice() {
		minPrice = minPrice == null ? 0d : minPrice;
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		maxPrice = maxPrice == null ? 0d : maxPrice;
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

}
