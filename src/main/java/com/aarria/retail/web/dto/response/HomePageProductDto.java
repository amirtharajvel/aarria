package com.aarria.retail.web.dto.response;

public class HomePageProductDto {

	private Long id;
	private String name;
	private Double price;
	private Double actualPrice;
	private Integer discount;
	private String image;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public HomePageProductDto(Long id, String name, Double price, Double originalPrice, Integer discount,
			String image) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.actualPrice = originalPrice;
		this.discount = discount;
		this.image = image;
	}

	@Override
	public String toString() {
		return "HomePageProductDto [id=" + id + ", name=" + name + ", price=" + price + ", originalPrice="
				+ actualPrice + ", discount=" + discount + ", image=" + image + "]";
	}
}
