package com.aarria.retail.core.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.aarria.retail.web.dto.request.SizewiseStockDto;

@Table(name = "product_stock")
@TableGenerator(name = "product_stock_generator", initialValue = 0, allocationSize = 1)
@Entity
public class ProductStock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "product_stock_generator")
	private Long id;

	@ManyToOne(cascade=CascadeType.ALL)
	private Product product;

	@Column
	private String size;

	@Column
	private Integer stock;

	@Column(name = "original_stock")
	private Integer originalStock;

	public ProductStock() {
		super();
	}

	public ProductStock(SizewiseStockDto dto, Product product) {
		this.setProduct(product);
		this.setSize(dto.getKey());
		this.setStock(Integer.valueOf(dto.getValue()));
		this.setOriginalStock(Integer.valueOf(dto.getValue()));
	}

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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getOriginalStock() {
		return originalStock;
	}

	public void setOriginalStock(Integer originalStock) {
		this.originalStock = originalStock;
	}

	@Override
	public String toString() {
		return "ProductStock [id=" + id + ", size=" + size + ", stock=" + stock + "]";
	}

}
