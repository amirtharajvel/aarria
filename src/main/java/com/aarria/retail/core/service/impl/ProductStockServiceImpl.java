package com.aarria.retail.core.service.impl;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.domain.ProductStock;
import com.aarria.retail.core.service.ProductStockService;
import com.aarria.retail.persistence.repository.ProductStockRepository;

@Service
public class ProductStockServiceImpl implements ProductStockService {

	@Autowired
	private ProductStockRepository productStockRepository;

	@Transactional
	@Override
	public void delete(ProductStock productStock) {
		productStockRepository.delete(productStock);
	}

	@Transactional
	@Override
	public void delete(Set<ProductStock> stocks) {
		productStockRepository.deleteAll(stocks);
	}

	@Transactional
	@Override
	public ProductStock findStockByProductAndSize(Product product, String size) {
		return productStockRepository.findByProductAndSize(product, size);
	}

	@Override
	public ProductStock findStockByProductAndSizeWithZeroStock(Product product, String size) {
		return productStockRepository.findByProductAndSizeWithZeroStock(product, size);
	}
	
	@Override
	public void save(ProductStock productstock) {
		productStockRepository.save(productstock);
	}

	@Override
	public void save(List<ProductStock> productstocks) {
		productStockRepository.saveAll(productstocks);
	}

}
