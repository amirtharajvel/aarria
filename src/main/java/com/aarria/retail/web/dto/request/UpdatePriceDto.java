package com.aarria.retail.web.dto.request;

public class UpdatePriceDto {

	private Double newPrice = 0d;
	private Long productId;
	private Double originalPrice = 0d;
	private boolean isShowDiscount;

	public Double getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(Double newPrice) {
		this.newPrice = newPrice;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public boolean isShowDiscount() {
		return isShowDiscount;
	}

	public void setShowDiscount(boolean isShowDiscount) {
		this.isShowDiscount = isShowDiscount;
	}

}
