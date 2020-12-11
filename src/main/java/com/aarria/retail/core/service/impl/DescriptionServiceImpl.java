package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.domain.Description;
import com.aarria.retail.core.service.DescriptionService;
import com.aarria.retail.persistence.repository.DescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DescriptionServiceImpl implements DescriptionService {

	@Autowired
	private DescriptionRepository repository;

	@Override
	public List<Description> getdDescriptionsByProduct(Long productId) {
		return repository.getDescriptionsByProductId(productId);
	}

}
