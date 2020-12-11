package com.aarria.retail.core.service;

import com.aarria.retail.core.domain.Attribute;
import com.aarria.retail.core.domain.Product;

import java.util.List;
import java.util.Set;

public interface AttributeService {

	public Attribute save(Attribute attribute);
	
	public List<String> getAllSizes();

	public List<Attribute> findAttributesByProduct(Product product);

	List<Attribute> findByValueAndRefinerAndCategory(String refiner, String value, Set<Long> categories);
	
	Set<Attribute> findByCategory(Set<Long> categoryIds);
	
	Set<Attribute> findByIds(List<Long> ids);
}
