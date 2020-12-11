package com.aarria.retail.web.dto.response;

public class CartItemsDto {

	private Long productId;

	private String pid;

	private Integer quantity;

	private String image;

	private String name;

	private Double price;

	private String size;
	
	private Integer stock;
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "CartItemsDto [productId=" + productId + ", pid=" + pid + ", quantity=" + quantity + ", image=" + image
				+ ", name=" + name + ", price=" + price + ", size=" + size + ", stock=" + stock + "]";
	}

	
	
}
