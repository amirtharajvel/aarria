package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.domain.Attribute;
import com.aarria.retail.core.domain.Category;
import com.aarria.retail.core.service.CategoryService;
import com.aarria.retail.core.util.Util;
import com.aarria.retail.persistence.repository.CategoryRepository;
import com.aarria.retail.web.dto.response.CategoryDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	private Logger LOGGER = LogManager.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findActiveCategories(Long id) {

		LOGGER.info("findActiveCategories for id " + id);
		return categoryRepository.findByActive(ACTIVE, id);
	}

	@Override
	@CacheEvict(allEntries = true, value = "activeCategories")
	public void evictCategoryCache() {
		evictRefiners();
	}

	@CacheEvict(value = "refiners", allEntries = true)
	private void evictRefiners() {

	}

	@Override
	@Cacheable("activeCategories")
	public CategoryDto findCategoryDto(Long id) {

		LOGGER.info("Active Catgories Cache method called for category id " + id);

		Category category = this.findActiveCategories(id);

		CategoryDto categoriesDto = null;

		if (category != null) {
			categoriesDto = Util.createChildrenCategory(category);
			categoriesDto.setBreadcrumps(Util.getParents(category));
			categoriesDto.setCategory(category);
		}

		return categoriesDto;
	}

	public void saveRefiner() {
		Attribute attribute = new Attribute();

		List<Attribute> attributes = new ArrayList<>();
		attributes.add(attribute);

		Category category = new Category();
		category.setId(6L);
		category.setName("Women's Clothing");
		category.setActive(true);

		Category parentCategory = categoryRepository.findById(4L).get();

		category.setParentCategory(parentCategory);

		// category.setAttributes(attributes);

		categoryRepository.save(category);

	}

	@Override
	public List<Category> findByProductId(Long productId) {
		return categoryRepository.findByProductId(ACTIVE, productId);
	}
}
