package com.aarria.retail.web.dto.response;

import java.util.List;

public class MenuDto {

	public MenuDto(List<CategoryDto> categoryDto) {
		super();
		this.categoryDto = categoryDto;
	}

	private List<CategoryDto> categoryDto;

	public List<CategoryDto> getCategoryDto() {
		return categoryDto;
	}

	public void setCategoryDto(List<CategoryDto> categoryDto) {
		this.categoryDto = categoryDto;
	}
}
