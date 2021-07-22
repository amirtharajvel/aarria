package com.aarria.retail.web.controller;

import com.aarria.retail.core.domain.*;
import com.aarria.retail.core.service.*;
import com.aarria.retail.core.util.Application;
import com.aarria.retail.core.util.Enum;
import com.aarria.retail.core.util.Enum.ProductDescriptionType;
import com.aarria.retail.core.util.Util;
import com.aarria.retail.web.dto.request.DescriptionDto;
import com.aarria.retail.web.dto.request.SearchableAttributeDto;
import com.aarria.retail.web.dto.response.*;
import com.aarria.retail.web.exception.ResourceNotFoundException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class ProductDetailsController {

	private static final String DETAIL_PAGE = "product";

	@Autowired
	private ProductService productService;

	@Autowired
	private DescriptionService descriptionService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ProductImageService productColorsService;

	@SuppressWarnings("unused")
	@Autowired
	private AttributeService attributeService;

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/product")
	public ModelAndView detail(@RequestParam("id") String productId, HttpServletResponse response) {

		long millis = System.currentTimeMillis();

		System.out.println("productId received  is = " + productId);

		ModelAndView modelAndView = new ModelAndView(DETAIL_PAGE);

		if (productId != null) {
			Product product = productService.findByPidWithStock(productId);

			if (product == null) {
				throw new ResourceNotFoundException();
			} else {

				ProductDetailDto dto = createDto(product);
				if (dto != null) {
					modelAndView.addObject("product", dto);
				} else {
					// modelAndView.setViewName("redirect:/");
					throw new ResourceNotFoundException();
				}
			}
		}

		System.out.println("Time consumed " + (System.currentTimeMillis() - millis));

		return modelAndView;
	}

	private ProductDetailDto createDto(Product product) {

		try {
			List<Attribute> attributes = null;// TODO//attributeService.findAttributesByProduct(product);

			ProductDetailDto dto = new ProductDetailDto();

			if (this.retriveAttributes(attributes, dto, product) == false) {
				// return null;

			}

			// dto.setCategoryIds();
			dto.setPid(product.getPid());
			dto.setRating(product.getRating());
			dto.setMeasureMeans(product.getMeasureMeans());
			dto.setMOQ(product.getMOQ());
			dto.setMeasureUnit(product.getMeasureUnit());
			getDescription(dto, product.getId());
			getBreadCrumbsAndCurrentCategoryId(product.getId(), dto);

			List<ProductsSuggestionDto> suggestions = productService.getSuggestions(dto.getCategoryId());
			Collections.shuffle(suggestions);
			dto.setSuggestions(suggestions);

			dto.setImg(Application.IMAGES_DOMAIN + product.getListingImage());
			dto.setVideo(product.getVideo());
			dto.setOffer(product.getOfferText());
			dto.setDeliveryCharge(product.getDeliveryCharge());

			ReviewDto reviewDto = reviewService.getReviewsForProduct(product.getId());
			if (reviewDto != null && CollectionUtils.isNotEmpty(reviewDto.getReviews())) {
				dto.setReviews(reviewDto.getReviews());
				dto.setRating(reviewDto.getOverallRatingForProduct());
			}

			if (CollectionUtils.isNotEmpty(product.getProductStock())) {
				for (ProductStock ps : product.getProductStock()) {
					dto.getProductStock().put(ps.getSize(), ps.getStock());
				}
			}

			dto.setName(Util.getShortenedName(product.getName(), 100));

			List<ProductDetailsImageDto> colors = productColorsService.getColorDetails(product);

			dto.setColorImages(colors);

			Integer actualPrice = null;

			if (product.getActualPrice() != null) {
				actualPrice = product.getActualPrice().intValue();
			} else {
				actualPrice = product.getOriginalPrice().intValue() * 2;
			}

			dto.setActualPrice(actualPrice);

			Integer price = null;

			if (product.getPrice() != null) {
				price = product.getPrice().intValue();
			}

			dto.setPrice(price);

			double price1 = product.getPrice();
			double actual = product.getActualPrice();
			double medium = (1 - (price1 / actual)) * 100;
			int percentge = (int) Math.round(medium);

			dto.setDiscount(percentge);
			dto.setYouSaveAmount(((int) (actual - price1)));

			return dto;

		} catch (Exception e) {
			messageService.sendEmailToAdmin(null, Util.retrieveStackTraceFromException(e) +
					"Product detail page exception block");
			return null;
		}
	}

	private boolean retriveAttributes(List<Attribute> attributes, ProductDetailDto productDetailDto, Product product) {
		List<SearchableAttributeDto> searchableAttributes = new ArrayList<>();

		Set<ProductStock> productStocks = product.getProductStock();
		Set<String> availableStockSizes = new HashSet<>();

		boolean isAtleastOneSizeAvailable = false;
		if (CollectionUtils.isNotEmpty(productStocks)) {
			for (ProductStock productStock : productStocks) {
				availableStockSizes.add(productStock.getSize());
				if (productStock != null && productStock.getStock() > 0) {
					isAtleastOneSizeAvailable = true;
				}
			}
		}

		productDetailDto.setIsAtleastOneSizeHasStock(isAtleastOneSizeAvailable);

		if (attributes != null && !attributes.isEmpty()) {

			for (Attribute attribute : attributes) {
				// Yes modify this if you are adding new Refiner in Enum
				if (attribute.getRefiner().equals(Enum.Refiners.SIZE.name())
						&& availableStockSizes.contains(attribute.getValue())) {
					// sizes.add(attribute.getValue());
				} else if (attribute.getRefiner().equals(Enum.Refiners.COLOR.name())) {

				} else {
					SearchableAttributeDto dto = new SearchableAttributeDto(attribute.getRefiner(),
							attribute.getValue());
					searchableAttributes.add(dto);
				}
			}

			productDetailDto.setSearchableAttributes(searchableAttributes);
		}

		return isAtleastOneSizeAvailable;
	}

	private void getBreadCrumbsAndCurrentCategoryId(Long productId, ProductDetailDto dto) {
		List<Category> category = categoryService.findByProductId(productId);

		if (category != null) {
			List<BreadcrumbDto> parents = Util.getParents(category.get(0));
			dto.setBreadcrumbs(parents);
		}

		dto.setCategoryId(category.get(0).getId());
	}

	private void getDescription(ProductDetailDto dto2, Long productId) {
		List<Description> descriptions = descriptionService.getdDescriptionsByProduct(productId);

		List<DescriptionDto> descriptionDtos = new ArrayList<>();
		List<DescriptionDto> shipDtos = new ArrayList<>();

		if (descriptions != null && !descriptions.isEmpty()) {
			for (Description d : descriptions) {
				DescriptionDto dto = new DescriptionDto(d.getKey(), d.getDescription());
				if (d.getType().equals(ProductDescriptionType.PRODUCT_DESC.name())) {
					descriptionDtos.add(dto);
				} else if (d.getType().equals(ProductDescriptionType.SHIP_AND_RETURNS.name())) {
					shipDtos.add(dto);
				}
			}
		}

		dto2.setDescriptions(descriptionDtos);
		dto2.setShippingAndReturnsDescriptions(shipDtos);
	}

}
