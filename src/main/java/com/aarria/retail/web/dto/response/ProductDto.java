package com.aarria.retail.web.dto.response;

import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.util.Util;

public class ProductDto {

	private Long id;

	private String pid;

	private String name;

	private String shortName;

	private String brand;

	private String listingImage;

	private Double rating;

	private Integer actualPrice;

	private Integer price;

	private Integer discount;

	private Boolean isAtleastOneSizeHasStock;

	private boolean isAdminUser;
	
	public ProductDto(Product product) {
		this.createProductDto(product);
	}

	private void createProductDto(Product product) {
		this.id = product.getId();
		this.pid = product.getPid();
		this.name = product.getName();
		this.rating = product.getRating();
		this.price = product.getPrice().intValue();
		this.discount = retrieveDiscount(product);
		this.isAtleastOneSizeHasStock = product.isAtleastOneSizeHasStock();
		
		if (product.getActualPrice() != null) {
			this.actualPrice = product.getActualPrice().intValue();
		}

		this.createShortName();

		this.listingImage = Util.generateImageLocation(product.getListingImage());
	}

	private Integer retrieveDiscount(Product product) {
		return  product.getOfferPrice() != null ? product.getOfferPrice().intValue() : null;
	}

	private void createShortName() {
		if (this.name != null && this.name.length() > 25) {
			this.shortName = this.name.substring(0, 25) + "...";
		} else {
			this.shortName = this.name;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getListingImage() {
		return listingImage;
	}

	public void setListingImage(String listingImage) {
		this.listingImage = listingImage;
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

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Boolean getIsAtleastOneSizeHasStock() {
		return isAtleastOneSizeHasStock;
	}

	public void setIsAtleastOneSizeHasStock(Boolean isAtleastOneSizeHasStock) {
		this.isAtleastOneSizeHasStock = isAtleastOneSizeHasStock;
	}

	public boolean isAdminUser() {
		return isAdminUser;
	}

	public void setAdminUser(boolean isAdminUser) {
		this.isAdminUser = isAdminUser;
	}

}
