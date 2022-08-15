package com.aarria.retail.web.dto.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aarria.retail.web.dto.request.DescriptionDto;
import com.aarria.retail.web.dto.request.SearchableAttributeDto;

public class ProductDetailDto {

	private String pid;

	private String name;

	private Double rating;

	private Integer actualPrice;

	private Integer offerPrice;

	private Integer price;

	private Integer discount;

	private Integer youSaveAmount;
	
	private String deliveryCharge;

	private List<ProductDetailsImageDto> colorImages;

	private List<DescriptionDto> descriptions;

	private List<DescriptionDto> shippingAndReturnsDescriptions;

	private List<SearchableAttributeDto> searchableAttributes;

	private List<BreadcrumbDto> breadcrumbs;

	private List<IndividualReviewDto> reviews;

	private String measureMeans;

	private Integer MOQ;

	private String measureUnit;

	private String img;

	private String video;

	private String offer;

	private String offerCode;

	private Map<String, Integer> productStock = new HashMap<>();

	private Boolean isAtleastOneSizeHasStock;

	private Long categoryId;

	List<ProductsSuggestionDto> suggestions;

	public int getDescriptionsSize() {
		if (this.descriptions == null) {
			return 0;
		}

		return this.descriptions.size();
	}

	public int getBreadcrumbsSize() {
		if (this.breadcrumbs == null) {
			return 0;
		}

		return this.breadcrumbs.size();
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Integer getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(Integer actualPrice) {
		this.actualPrice = actualPrice;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(Integer offerPrice) {
		this.offerPrice = offerPrice;
	}

	public Boolean getAtleastOneSizeHasStock() {
		return isAtleastOneSizeHasStock;
	}

	public void setAtleastOneSizeHasStock(Boolean atleastOneSizeHasStock) {
		isAtleastOneSizeHasStock = atleastOneSizeHasStock;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(String string) {
		this.deliveryCharge = string;
	}

	public List<DescriptionDto> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<DescriptionDto> descriptionDtos) {
		this.descriptions = descriptionDtos;
	}

	public List<BreadcrumbDto> getBreadcrumbs() {
		return breadcrumbs;
	}

	public void setBreadcrumbs(List<BreadcrumbDto> breadcrumbs) {
		this.breadcrumbs = breadcrumbs;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public List<IndividualReviewDto> getReviews() {
		return reviews;
	}

	public void setReviews(List<IndividualReviewDto> reviews) {
		this.reviews = reviews;
	}

	public List<ProductDetailsImageDto> getColorImages() {
		return colorImages;
	}

	public void setColorImages(List<ProductDetailsImageDto> colorImages) {
		this.colorImages = colorImages;
	}

	public String getMeasureMeans() {
		return measureMeans;
	}

	public void setMeasureMeans(String measureMeans) {
		this.measureMeans = measureMeans;
	}

	public Integer getMOQ() {
		return MOQ;
	}

	public void setMOQ(Integer mOQ) {
		MOQ = mOQ;
	}

	public String getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<SearchableAttributeDto> getSearchableAttributes() {
		return searchableAttributes;
	}

	public void setSearchableAttributes(List<SearchableAttributeDto> searchableAttributes) {
		this.searchableAttributes = searchableAttributes;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public Map<String, Integer> getProductStock() {
		return productStock;
	}

	public void setProductStock(Map<String, Integer> productStock) {
		this.productStock = productStock;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public List<DescriptionDto> getShippingAndReturnsDescriptions() {
		return shippingAndReturnsDescriptions;
	}

	public void setShippingAndReturnsDescriptions(List<DescriptionDto> shippingAndReturnsDescriptions) {
		this.shippingAndReturnsDescriptions = shippingAndReturnsDescriptions;
	}

	public Boolean getIsAtleastOneSizeHasStock() {
		return isAtleastOneSizeHasStock;
	}

	public void setIsAtleastOneSizeHasStock(Boolean isAtleastOneSizeHasStock) {
		this.isAtleastOneSizeHasStock = isAtleastOneSizeHasStock;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public List<ProductsSuggestionDto> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<ProductsSuggestionDto> suggestions) {
		this.suggestions = suggestions;
	}

	public Integer getYouSaveAmount() {
		return youSaveAmount;
	}

	public void setYouSaveAmount(Integer youSaveAmount) {
		this.youSaveAmount = youSaveAmount;
	}

	
}
