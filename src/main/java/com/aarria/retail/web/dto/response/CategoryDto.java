package com.aarria.retail.web.dto.response;

import com.aarria.retail.core.domain.Category;

import java.util.List;
import java.util.Set;

public class CategoryDto {

	private Long id;

	private String name;

	private Long parentCategoryId;

	private List<ProductDto> products;

	private List<CategoryDto> children;

	private Integer totalCategoryCount;

	private List<BreadcrumbDto> breadcrumps;

	private Set<Long> childrenIds;
	
	private Category category;

	public CategoryDto(Long id, String name, Category parentCategory) {
		this.id = id;
		this.name = name;

		if (parentCategory != null) {
			this.parentCategoryId = parentCategory.getId();
		}
	}

	// public void loadRefiners(Category category) {
	//
	// if (category != null && category.getAttributes() != null) {
	// List<Attribute> attributes = category.getAttributes();
	//
	// List<RefinerDto> refiners = new ArrayList<>();
	//
	// attributes
	// .forEach(attribute -> {
	//
	// String refiner = attribute.getRefiner();
	// RefinerDto dto = new RefinerDto();
	// dto.setName(refiner);
	//
	// AttributeDto attributeDto = new AttributeDto();
	// attributeDto.setId(attribute.getId());
	// attributeDto.setName(attribute.getName());
	//
	// if (refiners.contains(dto)) {
	// RefinerDto refinerDto = refiners.get(refiners
	// .indexOf(dto));
	// refinerDto.getAttributes().add(attributeDto);
	//
	// } else {
	// List<AttributeDto> attributeDtos = new ArrayList<>();
	// attributeDtos.add(attributeDto);
	//
	// dto.setAttributes(attributeDtos);
	// refiners.add(dto);
	// }
	// });
	//
	// this.refiners = refiners;
	// }
	// }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}

	public Long getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public List<CategoryDto> getChildren() {
		return children;
	}

	public void setChildren(List<CategoryDto> children) {
		this.children = children;
	}

	public Integer getTotalCategoryCount() {
		return totalCategoryCount;
	}

	public void setTotalCategoryCount(Integer totalCategoryCount) {
		this.totalCategoryCount = totalCategoryCount;
	}

	public List<BreadcrumbDto> getBreadcrumps() {
		return breadcrumps;
	}

	public void setBreadcrumps(List<BreadcrumbDto> breadcrumps) {
		this.breadcrumps = breadcrumps;
	}

	public Set<Long> getChildrenIds() {
		return childrenIds;
	}

	public void setChildrenIds(Set<Long> childrenIds) {
		this.childrenIds = childrenIds;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
