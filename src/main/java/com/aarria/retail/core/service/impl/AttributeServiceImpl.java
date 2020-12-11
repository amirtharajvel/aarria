package com.aarria.retail.core.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aarria.retail.core.domain.Attribute;
import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.service.AttributeService;
import com.aarria.retail.core.util.Enum.Refiners;
import com.aarria.retail.persistence.repository.AttributeRepository;

@Service
public class AttributeServiceImpl implements AttributeService {

	@Autowired
	private AttributeRepository attributeRepository;

	@Override
	public List<String> getAllSizes() {
		return attributeRepository.getAllUniqueRefinersByName(Refiners.SIZE.name());
	}

	@Override
	public List<Attribute> findAttributesByProduct(Product product) {
		return null; // TODO
	}

	@Override
	public Attribute save(Attribute attribute) {
		return attributeRepository.save(attribute);
	}

	@Override
	public List<Attribute> findByValueAndRefinerAndCategory(String refiner, String value, Set<Long> categories) {
		return attributeRepository.findByRefinerAndValueAndCategory(refiner, value, categories);
	}

	@Override
	public Set<Attribute> findByCategory(Set<Long> categoryIds) {
		return attributeRepository.findByCategoryIds(categoryIds);
	}

	@Override
	public Set<Attribute> findByIds(List<Long> ids) {
		Set<Attribute> finalAttributes = new HashSet<>();
		Set<Attribute> attr = attributeRepository.findByIds(ids);
		if (CollectionUtils.isNotEmpty(attr)) {
			for (Attribute a : attr) {
				Set<Attribute> otherAttributes = attributeRepository.findByRefinerAndValue(a.getRefiner(),
						a.getValue());
				if (CollectionUtils.isNotEmpty(otherAttributes)) {
					finalAttributes.addAll(otherAttributes);
				}
			}
		}
		return finalAttributes;
	}
}
