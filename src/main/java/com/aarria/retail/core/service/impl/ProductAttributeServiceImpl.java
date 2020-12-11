package com.aarria.retail.core.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aarria.retail.core.domain.ProductAttribute;
import com.aarria.retail.core.service.ProductAttributeService;
import com.aarria.retail.persistence.repository.ProductAttributeRepository;

@Service
public class ProductAttributeServiceImpl implements ProductAttributeService {

	@Autowired
	private ProductAttributeRepository productAttributeRepository;

	@Override
	public ProductAttribute save(ProductAttribute productAttribute) {
		return productAttributeRepository.save(productAttribute);
	}

	@Override
	public Set<ProductAttribute> findByIds(List<Long> ids, Pageable pageable) {
		return null;
	}
}
