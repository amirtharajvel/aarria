package com.aarria.retail.web.dto.response;

public class ApplyOfferHelperDto {

	private Double offerPrice;
	private Double productPrice;
	private Integer offerQuantity;
	private Integer actualCartQuantity;
	
	public ApplyOfferHelperDto(Double offerPrice, Double productPrice, Integer offerQuantity,
			Integer actualCartQuantity) {
		super();
		this.offerPrice = offerPrice;
		this.productPrice = productPrice;
		this.offerQuantity = offerQuantity;
		this.actualCartQuantity = actualCartQuantity;
	}

	public Double getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(Double offerPrice) {
		this.offerPrice = offerPrice;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getOfferQuantity() {
		return offerQuantity;
	}

	public void setOfferQuantity(Integer offerQuantity) {
		this.offerQuantity = offerQuantity;
	}

	public Integer getActualCartQuantity() {
		return actualCartQuantity;
	}

	public void setActualCartQuantity(Integer actualCartQuantity) {
		this.actualCartQuantity = actualCartQuantity;
	}

	@Override
	public String toString() {
		return "ApplyOfferHelperDto [offerPrice=" + offerPrice + ", productPrice=" + productPrice + ", offerQuantity="
				+ offerQuantity + ", actualCartQuantity=" + actualCartQuantity + "]";
	}
	
}
