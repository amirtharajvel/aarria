package com.aarria.retail.core.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.aarria.retail.core.util.Enum.OrderStatus;

@Entity
@Table(name = "cart")
@TableGenerator(name = "cart_generator", initialValue = 0, allocationSize = 1)
@NamedEntityGraphs(@NamedEntityGraph(name = "graph.cart.product.stock", attributeNodes = {
		@NamedAttributeNode(value = "product", subgraph = "graph.product.stock") }, subgraphs = @NamedSubgraph(name = "graph.product.stock", attributeNodes = @NamedAttributeNode("productStock"))))
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "cart_generator")
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	private Product product;

	@Column
	private Integer quantity;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date addedDate;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModified;

	@OneToOne
	private User user;

	@Column
	private Double price;

	@Column
	private String checkoutType;

	@Column // RETHINK ABOUT THIS MIGHT CHANGE TO ATTRIBUTE ID
	private String size;

	@Column
	private String colorImage;

	@Column
	private String status;

	@Column
	private Boolean isPaymentDone = false;

	@Column
	private String offerCode;

	public Item createOrderedItem(Order order) {

		Product product = this.getProduct();

		if (product == null) {
			throw new RuntimeException("Ooh Ohh!!! Product is not present while creating item from cart!!");
		}

		Item item = new Item();
		item.setActualPrice(product.getActualPrice());
		item.setOriginalPrice(product.getOriginalPrice());
		item.setUnitSoldPrice(product.getPrice()); // how much the products'
													// price at the time of sell
		item.setProduct(product);
		item.setDiscount(product.getDiscount());
		item.setQuantity(this.getQuantity());
		item.setSize(this.getSize());
		item.setStatus(OrderStatus.OPEN.ordinal());
		item.setOrder(order);

		return item;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCheckoutType() {
		return checkoutType;
	}

	public void setCheckoutType(String checkoutType) {
		this.checkoutType = checkoutType;
	}

	public String getColorImage() {
		return colorImage;
	}

	public void setColorImage(String colorImage) {
		this.colorImage = colorImage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIsPaymentDone() {
		return isPaymentDone;
	}

	public void setIsPaymentDone(Boolean isPaymentDone) {
		this.isPaymentDone = isPaymentDone;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", product=" + product + ", quantity=" + quantity + ", addedDate=" + addedDate
				+ ", lastModified=" + lastModified + ", user=" + user + ", price=" + price + ", checkoutType="
				+ checkoutType + ", size=" + size + ", colorImage=" + colorImage + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Cart other = (Cart) obj;
		if (product == null) {
			if (other.product != null) {
				return false;
			}
		} else if (!product.equals(other.product)) {
			return false;
		}
		if (size == null) {
			if (other.size != null) {
				return false;
			}
		} else if (!size.equals(other.size)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		return true;
	}

}
