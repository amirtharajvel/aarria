package com.aarria.retail.web.dto.request;

import java.util.Date;

import com.aarria.retail.core.domain.Cart;
import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.domain.User;

public class AddToCartItemsDto {

	private String pid;
	private String name;
	private Double price;
	private String image;
	private Integer quantity;
	private String size;

	public Cart getCart(User user, Product product, String checkoutType) {
		Cart cart = new Cart();
		cart.setAddedDate(new Date());
		cart.setPrice(quantity * product.getPrice());
		cart.setQuantity(quantity);
		cart.setUser(user);
		cart.setProduct(product);
		cart.setSize(size);
		cart.setCheckoutType(checkoutType);
		cart.setColorImage(image);

		return cart;
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "AddToCartItemsDto [pid=" + pid + ", name=" + name + ", price=" + price + ", image=" + image
				+ ", quantity=" + quantity + ", size=" + size + "]";
	}

}
