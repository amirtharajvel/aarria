package com.aarria.retail.web.dto.response;

import java.util.List;

public class FilterDto {

	private Integer minPrice;

	private Integer maxPrice;

	private List<RefinerDto> refiners;

	public FilterDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FilterDto(Integer minPrice, Integer maxPrice, List<RefinerDto> refiners) {
		super();
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.refiners = refiners;
	}

	public Integer getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}

	public Integer getMaxPrice() {
		if (maxPrice >= 1000000) {
			maxPrice = 0;
		}
		return maxPrice;
	}

	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}

	public List<RefinerDto> getRefiners() {
		return refiners;
	}

	public void setRefiners(List<RefinerDto> refiners) {
		this.refiners = refiners;
	}
}
