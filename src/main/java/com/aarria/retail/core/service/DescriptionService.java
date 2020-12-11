package com.aarria.retail.core.service;

import com.aarria.retail.core.domain.Description;

import java.util.List;

public interface DescriptionService {

	public List<Description> getdDescriptionsByProduct(Long productId);
}
