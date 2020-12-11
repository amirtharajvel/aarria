package com.aarria.retail.core.service;

import com.aarria.retail.core.domain.Category;
import com.aarria.retail.web.dto.response.CategoryDto;

import java.util.List;

public interface CategoryService {

	final static Boolean ACTIVE = true;

	public List<Category> findAll();

	public Category findActiveCategories(Long id);

	public List<Category> findByProductId(Long productId);

	CategoryDto findCategoryDto(Long id);

	void evictCategoryCache();
}
