package com.aarria.retail.web.dto.request;

import com.aarria.retail.core.domain.Cart;
import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.domain.ProductStock;

public class CheckoutCartDto {

	private Long productId;
	private String name;
	private Double price;
	private String image;
	private Integer quantity;
	private Long colorId;
	private String size;
	private Double sellingPriceBeforeOffer;
	private Double unitPrice;
	private String pid;
	private String offerCode;
	private Double amountAfterOfferApplied;
	private Integer stock;

	public CheckoutCartDto(Cart cart, Product product) {
		this.setImage(cart.getColorImage());
		String name = cart.getProduct().getName();
		if (name != null && name.length() > 40) {
			name = name.substring(0, 40) + "...";
		}
		this.setName(name);

		this.setProductId(cart.getProduct().getId());
		this.setSize(cart.getSize());
		this.setPid(cart.getProduct().getPid());
		this.setOfferCode(cart.getProduct().getOfferCode());

		if (product != null) {
			if (product.getProductStock() != null) {
				ProductStock ps = product.getProductStock().stream().filter(p -> p.getSize().equals(this.getSize()))
						.findFirst().orElse(null);
				if (ps != null) {
					this.setStock(ps.getStock());
					if (ps.getStock() < cart.getQuantity()) {
						cart.setPrice(stock * product.getPrice());
						cart.setQuantity(stock);
					}
				}
			}
		}
		
		this.setPrice(cart.getPrice());
		this.setUnitPrice(cart.getProduct().getActualPrice());
		this.setQuantity(cart.getQuantity());

	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getColorId() {
		return colorId;
	}

	public void setColorId(Long colorId) {
		this.colorId = colorId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public Double getAmountAfterOfferApplied() {
		return amountAfterOfferApplied;
	}

	public void setAmountAfterOfferApplied(Double amountAfterOfferApplied) {
		this.amountAfterOfferApplied = amountAfterOfferApplied;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Double getSellingPriceBeforeOffer() {
		return sellingPriceBeforeOffer;
	}

	public void setSellingPriceBeforeOffer(Double sellingPriceBeforeOffer) {
		this.sellingPriceBeforeOffer = sellingPriceBeforeOffer;
	}

	@Override
	public String toString() {
		return "CheckoutCartDto [pid=" + productId + ", name=" + name + ", price=" + price + ", image=" + image
				+ ", quantity=" + quantity + "]";
	}

}
