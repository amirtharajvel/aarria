package com.aarria.retail.core.service;

import com.aarria.retail.web.dto.response.CreateHomePageDto;
import com.aarria.retail.web.dto.response.HomePageProductDto;

import java.util.List;

public interface HomeService {

	List<HomePageProductDto> getSelectedProducts(List<Long> ids);

	List<CreateHomePageDto> allProductsOrderByAddedDateDesc();

	List<CreateHomePageDto> getPopularProducts();

	List<CreateHomePageDto> getBestOffersProducts();

}
