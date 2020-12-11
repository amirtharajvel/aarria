package com.aarria.retail.core.domain;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

@Entity
@Table(name = "product_attribute")
@TableGenerator(name = "product_attribute_generator", initialValue = 0, allocationSize = 1)
//@Indexed
public class ProductAttribute {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "product_attribute_generator")
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	private Product product;

	@ManyToOne(cascade = CascadeType.ALL)
	private Attribute attribute;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
