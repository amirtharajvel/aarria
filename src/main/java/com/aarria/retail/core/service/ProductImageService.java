package com.aarria.retail.core.service;

import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.domain.ProductImage;
import com.aarria.retail.web.dto.response.ProductDetailsImageDto;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ProductImageService {

	Set<ProductImage> getAllProductColorsByProduct(Product product);

	ProductImage findById(Long id);

	List<ProductDetailsImageDto> getColorDetails(Product product);

	void deleteImageByProductAdditionalImageId(Long productAdditionalImageId) throws IOException, Exception;
	
	ProductImage saveProductImage(ProductImage image);
}
