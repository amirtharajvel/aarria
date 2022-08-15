package com.aarria.retail.web.controller;

import static com.aarria.retail.core.util.View.PRODUCTS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.domain.ProductAttribute;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.exception.UnexpectedException;
import com.aarria.retail.core.service.AttributeService;
import com.aarria.retail.core.service.CategoryService;
import com.aarria.retail.core.service.ProductService;
import com.aarria.retail.core.service.SearchService;
import com.aarria.retail.core.service.ValidationService;
import com.aarria.retail.core.util.AdminView;
import com.aarria.retail.core.util.AppProperties;
import com.aarria.retail.core.util.Application;
import com.aarria.retail.core.util.Util;
import com.aarria.retail.core.util.Util.Sort;
import com.aarria.retail.core.util.View;
import com.aarria.retail.core.util.business.ProductPrice;
import com.aarria.retail.persistence.repository.ProductAttributeRepository;
import com.aarria.retail.web.dto.request.AddProductDto;
import com.aarria.retail.web.dto.request.GetProductsInputDto;
import com.aarria.retail.web.dto.request.PriceCalculatorDto;
import com.aarria.retail.web.dto.request.UpdatePriceDto;
import com.aarria.retail.web.dto.response.CategoryDto;
import com.aarria.retail.web.dto.response.FilterDto;
import com.aarria.retail.web.dto.response.ProductDto;
import com.aarria.retail.web.dto.response.ProductListDto;
import com.aarria.retail.web.dto.response.ProductsSuggestionDto;
import com.google.gson.Gson;

@Controller
public class ProductController {

	private Logger LOGGER = LogManager.getLogger(ProductController.class);

	@Autowired
	private AppProperties properties;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AttributeService attributeService;

	@Autowired
	private SearchService searchService;

	@Autowired
	private ValidationService validationService;

	@Autowired
	private ProductAttributeRepository attributeRepository;

	@RequestMapping(value = "/products/cat/{catid}")
	public ModelAndView listing(@PathVariable("catid") Long catId, GetProductsInputDto input,
			@RequestParam("page") Integer page, HttpSession session) {

		long millis = System.currentTimeMillis();

		ModelAndView modelAndView = new ModelAndView(PRODUCTS);

		if (!input.isValidSort()) {
			input.setSort(Sort.PRICE_LOW_TO_HIGH.name());
		}

		if (page == null || page <= 0) {
			page = 1;
		}

		System.out.println("input " + input);

		CategoryDto categoriesDto = categoryService.findCategoryDto(catId);

		if (categoriesDto != null) {
			input.setCategories(categoriesDto.getChildrenIds());
		}

		if (CollectionUtils.isEmpty(input.getAttributes())) {
			List<Long> attributes = new ArrayList<>();
			attributes.add(-1L);

			input.setAttributes(attributes);
		}

		FilterDto refinersDto = productService.getAllRefinersByCategory(input);

		ProductListDto productListDto = productService.findByCategoryIds(input, page);

		if (refinersDto == null || productListDto == null || productListDto.getProducts() == null) {
			return modelAndView;
		}

		refinersDto.setMaxPrice(productListDto.getMaxPrice().intValue());
		refinersDto.setMinPrice(productListDto.getMinPrice().intValue());

		List<ProductDto> productDtos = new ArrayList<>();

		for (Product p : productListDto.getProducts()) {
			ProductDto dto = new ProductDto(p);
			productDtos.add(dto);
		}

		modelAndView.addObject("cdnResources", properties.cdnResources);
		modelAndView.addObject("categoryId", catId);
		modelAndView.addObject("childCategories", categoriesDto);
		modelAndView.addObject("refiners", refinersDto);
		modelAndView.addObject("products", productDtos);
		modelAndView.addObject("totalPageCount", productListDto.getPageCount());
		modelAndView.addObject("currentPage", page);
		modelAndView.addObject("totalResultCount", productListDto.getResultCount());

		modelAndView.addObject("currentAttributes", StringUtils.join(input.getAttributes(), ","));

		if (CollectionUtils.isEmpty(categoriesDto.getChildren()) && categoriesDto.getCategory() != null) {
			modelAndView.addObject("parentCategories",
					Util.createChildrenCategory(categoriesDto.getCategory().getParentCategory()));
		}

		LOGGER.info(
				"Time taken is " + (System.currentTimeMillis() - millis) + " for " + productDtos.size() + " products");

		return modelAndView;
	}

	@RequestMapping(value = "/listproducts/{categoryId}", method = RequestMethod.GET)
	public @ResponseBody String getProductSuggestions(@PathVariable(name = "categoryId") Long categoryId) {
		List<ProductsSuggestionDto> suggestions = productService.getSuggestions(categoryId);
		return new Gson().toJson(suggestions);
	}

	@RequestMapping(value = "/product/findProducts", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String findByPid(@RequestParam("page") Integer page,
			@RequestParam(name = "includeSoldOut") Integer includeSoldOut, @RequestBody GetProductsInputDto input) {

		if (input.getAttributes() == null || input.getAttributes().isEmpty() || page < 0) {
			return null;
		}

		if (!input.isValidSort()) {
			return "Not an authenticated sort";
		}

		input.isValidPrices();

		ProductListDto products = productService.findByCategoryIds(input, page);

		if (products == null || products.getProducts() == null) {
			return null;
		}

		List<ProductDto> dtos = new ArrayList<ProductDto>();

		StringBuilder ids = new StringBuilder();

		products.getProducts().forEach(p -> {
			if (isSoldOutIncluded(includeSoldOut, p)) {
				ProductDto dto = new ProductDto(p);
				ids.append("[" + p.getId() + "->" + p.getPrice() + "]");
				dtos.add(dto);
			}
		});

		return new Gson().toJson(dtos);
	}

	private boolean isSoldOutIncluded(Integer includeSoldOut, Product p) {

		if (includeSoldOut != null) {
			if (includeSoldOut.equals(Application.INCLUDE_SOLDOUT)) {
				return true;
			} else {
				return p.isAtleastOneSizeHasStock() == true;
			}
		}

		return true;

	}

	@RequestMapping(value = "/product/findRefiners", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getRefiners(@RequestBody GetProductsInputDto input) {

		if (input.getAttributes() == null || input.getAttributes().isEmpty()) {
			return null;
		}

		if (!input.isValidSort()) {
			return "Not an authenticated sort";
		}

		FilterDto dto = productService.getAllRefinersByCategory(input);
		return new Gson().toJson(dto);
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ModelAndView addProduct(HttpSession session, @ModelAttribute("model") @Valid AddProductDto dto,
			BindingResult result) {

		ModelAndView view = new ModelAndView(AdminView.ADD_PRODUCTS);

		User user = validationService.findAdminUserFromSession(session);

		if (user == null) {
			view.setViewName(View.LOGOUT);
			return view;
		}

		dto.resetValues();

		if (result.hasErrors()) {
			view.getModelMap().put("model", dto);
			view.getModelMap().put("errors", result.getAllErrors());
			return view;
		}

		try {
			productService.save(dto);
			searchService.startIndexing();
		} catch (IOException | UnexpectedException ue) {
			ObjectError error = new ObjectError("Run time error", "" + ue);
			LOGGER.error(ue);
			result.addError(error);
			return view;
		}
		return view;
	}

	@RequestMapping(value = "/addproducts")
	public ModelAndView addproducts(HttpSession session) {
		ModelAndView view = new ModelAndView(AdminView.ADD_PRODUCTS);

		User user = validationService.findAdminUserFromSession(session);

		if (user == null) {
			view.setViewName(View.LOGOUT);
			return view;
		}

		AddProductDto dto = new AddProductDto();
		view.addObject("allcategories", dto.createCategoriesForView(this.categoryService.findAll()));
		view.addObject("measureUnits", this.productService.getMeasurementUnits());
		view.addObject("allSizes", this.attributeService.getAllSizes());
		view.addObject("offers", this.productService.getOffers());

		view.getModelMap().put("model", dto);
		return view;
	}

	@RequestMapping(value = "/priceCalculator", method = RequestMethod.POST)
	public @ResponseBody String calculateProductPrice(HttpSession session, PriceCalculatorDto dto) {

		User user = validationService.findAdminUserFromSession(session);

		if (user == null) {
			return "unauthorized";
		}

		return ProductPrice.getFinalPrice(dto.createProduct()).doubleValue() + "";

	}

	@RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
	public @ResponseBody String delete(HttpSession session, Long id) {

		User user = validationService.findAdminUserFromSession(session);

		if (user == null) {
			return "unauthorized";
		}

		productService.deleteProduct(id);

		return "success";

	}

	@RequestMapping(value = "/getAllProductAttribute", method = RequestMethod.GET)
	public @ResponseBody String getProductAttribute(HttpSession session, Long id, HttpServletRequest request) {

		List<ProductAttribute> att = attributeRepository.findAll();

		String s = "";
		for (ProductAttribute pa : att) {
			s += "insert into product_attribute values(" + pa.getId() + ", " + pa.getProduct().getId() + ","
					+ pa.getAttribute().getId() + "); <br/>";
		}

		return s;

	}

	@RequestMapping(value = "/evictAllCache")
	public void evictAllCache() {
		categoryService.evictCategoryCache();
	}

	@RequestMapping(value = "/product/updatePrice/{productId}", method = RequestMethod.POST)
	public @ResponseBody String updateProduct(HttpSession session, UpdatePriceDto dto) {

		User user = validationService.findAdminUserFromSession(session);

		if (user == null) {
			return "unauthorized";
		}

		return String.valueOf(productService.updatePrice(dto));

	}

	public static void main(String[] args) {
		String s = "word";
		System.out.println(WordUtils.capitalizeFully(s));
	}

}
