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

@Entity
@Table(name = "description")
@TableGenerator(name = "description_generator", initialValue = 0, allocationSize = 1)
public class Description {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "description_generator")
	private Long id;

	@Column
	private String type;

	@Column(name = "desc_key")
	private String key;

	@Column
	private String description;

	@ManyToOne(targetEntity = Product.class, cascade = CascadeType.ALL)
	private Product product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
