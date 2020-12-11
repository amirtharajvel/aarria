package com.aarria.retail.core.domain;

import javax.persistence.*;

@Entity
@Table(name = "item")
@TableGenerator(name = "item_generator", initialValue = 0, allocationSize = 1)
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "item_generator")
	private Long id;

	@OneToOne
	private Product product;

	@ManyToOne
	private Order order;
	
	@Column
	private Integer quantity;

	@Column
	private Integer discount;

	@Column
	private String size;

	@Column
	private Double unitSoldPrice;

	@Column
	private Double totalSoldPrice;

	@Column
	private Double originalPrice;

	@Column
	private Double actualPrice;

	@Column
	private Double taxableValue;

	@Column
	private Double igstRate;

	@Column
	private Double igstAmount;

	@Column
	private String orderItemId;

	@Column
	private Integer status;

	@Column
	private Integer returnCashMethod;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Double getUnitSoldPrice() {
		return unitSoldPrice;
	}

	public void setUnitSoldPrice(Double unitSoldPrice) {
		this.unitSoldPrice = unitSoldPrice;
	}

	public Double getTotalSoldPrice() {
		return totalSoldPrice;
	}

	public void setTotalSoldPrice(Double totalSoldPrice) {
		this.totalSoldPrice = totalSoldPrice;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getTaxableValue() {
		return taxableValue;
	}

	public void setTaxableValue(Double taxableValue) {
		this.taxableValue = taxableValue;
	}

	public Double getIgstRate() {
		return igstRate;
	}

	public void setIgstRate(Double igstRate) {
		this.igstRate = igstRate;
	}

	public Double getIgstAmount() {
		return igstAmount;
	}

	public void setIgstAmount(Double igstAmount) {
		this.igstAmount = igstAmount;
	}

	public Integer getReturnCashMethod() {
		return returnCashMethod;
	}

	public void setReturnCashMethod(Integer returnCashMethod) {
		this.returnCashMethod = returnCashMethod;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", quantity=" + quantity + ", size=" + size + ", unitSoldPrice=" + unitSoldPrice
				+ ", totalSoldPrice=" + totalSoldPrice + ", status=" + status + "]";
	}

}
