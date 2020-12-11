package com.aarria.retail.core.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.aarria.retail.core.domain.ProductAttribute;

public interface ProductAttributeService {

	ProductAttribute save(ProductAttribute productAttribute);
	
	Set<ProductAttribute> findByIds(List<Long> ids, Pageable pageable);
}
