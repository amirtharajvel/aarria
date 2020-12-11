package com.aarria.retail.web.dto.request;

import com.aarria.retail.core.util.Application;
import com.aarria.retail.core.util.Util.Sort;

import java.util.List;
import java.util.Set;

public class GetProductsInputDto {

	private Set<Long> categories;

	private List<Long> attributes;

	private String sort;

	private Integer minPrice;

	private Integer maxPrice;

	private Sort internalSort;

	private Integer page;

	private Integer excludeSoldOut = Application.INCLUDE_SOLDOUT;

	public boolean isValidSort() {

		for (Sort s : Sort.values()) {
			System.out.println(s.getSort() + "|and|" + this.sort);
			if (s.getSort().equals(this.sort)) {
				System.out.println("equals " + this.sort);
				this.internalSort = s;
				return true;
			}
		}

		return false;
	}

	public void isValidPrices() {

		if (minPrice != null && maxPrice != null && maxPrice < minPrice) {
			maxPrice = null;
			minPrice = null;
		}
	}

	public Set<Long> getCategories() {
		return categories;
	}

	public void setCategories(Set<Long> categories) {
		this.categories = categories;
	}

	public List<Long> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Long> attributes) {
		this.attributes = attributes;
	}

	public Integer getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}

	public Integer getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Sort getInternalSort() {
		return internalSort;
	}

	public void setInternalSort(Sort internalSort) {
		this.internalSort = internalSort;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getExcludeSoldOut() {
		return excludeSoldOut;
	}

	public void setExcludeSoldOut(Integer excludeSoldOut) {
		this.excludeSoldOut = excludeSoldOut;
	}

}
