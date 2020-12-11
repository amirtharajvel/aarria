package com.aarria.retail.core.service;

import java.util.List;
import java.util.Set;

import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.domain.ProductStock;

public interface ProductStockService {

	void delete(ProductStock productStock);

	void delete(Set<ProductStock> zeroStocks);

	ProductStock findStockByProductAndSize(Product product, String size);

	void save(ProductStock productstock);
	
	void save(List<ProductStock> productstocks);

	ProductStock findStockByProductAndSizeWithZeroStock(Product product, String size);
}
