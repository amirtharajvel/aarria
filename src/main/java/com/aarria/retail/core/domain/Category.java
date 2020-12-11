package com.aarria.retail.core.domain;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "category")
@TableGenerator(name = "category_generator", initialValue = 0, allocationSize = 1)
@Indexed
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "category_generator")
	private Long id;

	@Column
	@Field(store = Store.YES)
	private String name;

	@Column
	private Boolean active;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id")
	private Category parentCategory;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories", cascade = { CascadeType.REMOVE })
	private List<Product> products;

	@OneToMany(mappedBy = "parentCategory", fetch = FetchType.EAGER)
	private List<Category> childrenCategories;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date temporaryDate;

	public void addProduct(Product p) {
		this.products.add(p);
		p.getCategories().add(this);
	}

	public void removeProduct(Product p) {
		this.products.remove(p);
		p.getCategories().remove(this);
	}

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

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Date getTemporaryDate() {
		return temporaryDate;
	}

	public void setTemporaryDate(Date temporaryDate) {
		this.temporaryDate = temporaryDate;
	}

	public List<Category> getChildrenCategories() {
		return childrenCategories;
	}

	public void setChildrenCategories(List<Category> childrenCategories) {
		this.childrenCategories = childrenCategories;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
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
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
