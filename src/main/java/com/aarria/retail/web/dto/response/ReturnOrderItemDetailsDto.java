package com.aarria.retail.web.dto.response;

import com.aarria.retail.core.domain.Item;
import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.util.Util;

public class ReturnOrderItemDetailsDto {

	private Long id;

	private String pid;

	private String name;

	private String image;

	private Integer quantity;

	private String size;

	private Double unitSoldPrice;

	private Double totalSoldPrice;

	public ReturnOrderItemDetailsDto(Long id, String pid, String name, String image, Integer quantity, String size,
			Double unitSoldPrice, Double totalSoldPrice) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.image = image;
		this.quantity = quantity;
		this.size = size;
		this.unitSoldPrice = unitSoldPrice;
		this.totalSoldPrice = totalSoldPrice;
	}

	public static ReturnOrderItemDetailsDto createFromProduct(Product p, Item item) {
		String imageLocation = Util.generateImageLocation(p.getListingImage());

		return new ReturnOrderItemDetailsDto(item.getId(), p.getPid(), p.getName(), imageLocation, item.getQuantity(),
				item.getSize(), item.getUnitSoldPrice(), item.getTotalSoldPrice());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
