package com.aarria.retail.web.dto.response;

public class DisplayOffersInAddProductDto {

	private String offerText;

	private Integer offerQuantity;

	private String offerCode;

	public DisplayOffersInAddProductDto() {
		super();
	}

	public DisplayOffersInAddProductDto(String offerText, Integer offerQuantity, String offerCode) {
		super();
		this.offerText = offerText;
		this.offerQuantity = offerQuantity;
		this.offerCode = offerCode;
	}

	public String getOfferText() {
		return offerText;
	}

	public void setOfferText(String offerText) {
		this.offerText = offerText;
	}

	public Integer getOfferQuantity() {
		return offerQuantity;
	}

	public void setOfferQuantity(Integer offerQuantity) {
		this.offerQuantity = offerQuantity;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	@Override
	public String toString() {
		return "DisplayOffersInAddProductDto [offerText=" + offerText + ", offerQuantity=" + offerQuantity
				+ ", offerCode=" + offerCode + "]";
	}

	
}
