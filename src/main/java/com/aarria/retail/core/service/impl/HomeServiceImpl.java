package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.service.HomeService;
import com.aarria.retail.core.service.ProductService;
import com.aarria.retail.core.util.Util;
import com.aarria.retail.web.dto.response.CreateHomePageDto;
import com.aarria.retail.web.dto.response.HomePageProductDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	private ProductService productService;

	@Override
	public List<CreateHomePageDto> allProductsOrderByAddedDateDesc() {
		Set<Product> products = productService.findAllProductsOrderByAddedDate();
		List<CreateHomePageDto> all = new ArrayList<>();
		for (Product p : products) {
			CreateHomePageDto dto = new CreateHomePageDto();

			dto.setImage(Util.generateImageLocation(p.getListingImage()));
			dto.setProduct(p);

			all.add(dto);
		}

		return all;
	}

	@Override
	public List<CreateHomePageDto> getPopularProducts() {
		Set<Product> products = productService.getPopularProducts();
		List<CreateHomePageDto> all = new ArrayList<>();
		for (Product p : products) {
			CreateHomePageDto dto = new CreateHomePageDto();

			dto.setImage(Util.generateImageLocation(p.getListingImage()));
			dto.setProduct(p);

			all.add(dto);
		}

		return all;
	}

	@Override
	public List<CreateHomePageDto> getBestOffersProducts() {
		Set<Product> products = productService.getBestOfferProducts();
		List<CreateHomePageDto> all = new ArrayList<>();
		for (Product p : products) {
			CreateHomePageDto dto = new CreateHomePageDto();

			dto.setImage(Util.generateImageLocation(p.getListingImage()));
			dto.setProduct(p);

			all.add(dto);
		}

		return all;
	}

	@Override
	public List<HomePageProductDto> getSelectedProducts(List<Long> ids) {
		Set<Product> products = productService.findProductsByIds(ids);
		List<HomePageProductDto> dtos = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(products)) {
			for (Product p : products) {
				HomePageProductDto dto = new HomePageProductDto(p.getId(), p.getName(), p.getPrice(),
						p.getOriginalPrice(), p.getDiscount(), p.getListingImage());
				dtos.add(dto);
			}

			return dtos;
		}

		return null;
	}

}
