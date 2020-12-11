package com.aarria.retail.core.service;

import com.aarria.retail.core.domain.Product;
import com.aarria.retail.web.dto.request.*;
import com.aarria.retail.web.dto.response.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ProductService {

	Set<Product> findAllProductsOrderByAddedDate();

	Product findById(Long id);

	Product findByPidWithStock(String pid);

	public ProductListDto findByCategoryIds(GetProductsInputDto input, Integer page);

	public FilterDto getAllRefinersByCategory(GetProductsInputDto input);

	public void save(AddProductDto dto) throws IOException;

	public List<String> getMeasurementUnits();

	public void save(Product product);

	Set<Product> findProductsByIds(List<Long> ids);

	Set<Product> getPopularProducts();

	Set<Product> getBestOfferProducts();

	Product findByProductPid(String pid);

	List<DisplayOffersInAddProductDto> getOffers();

	boolean deleteProduct(Long productId);

	Set<Product> findSoldProducts();

	boolean updatePrice(UpdatePriceDto dto);

	FetchProductImagesDto getProductImages(Long productId);

	void addAdditionalImages(Long productId, AddAdditionalImagesDto imagesDto2) throws IOException;

	void addPreviewImage(AddPreviewAndSmallImage dto) throws IOException;

	List<ProductsSuggestionDto> getSuggestions(Long categoryId);

}
