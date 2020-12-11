package com.aarria.retail.web.dto.response;

import com.aarria.retail.core.domain.Item;
import com.aarria.retail.core.domain.Order;
import com.aarria.retail.core.util.Enum.ItemStatus;
import com.aarria.retail.core.util.Enum.ReturnCashMethod;

import java.util.ArrayList;
import java.util.List;

public class ViewOrderItemDetailsDto {

	private Long itemId;

	private Integer quantity;

	private Integer discount;

	private String size;

	private Double unitSoldPrice;

	private Double totalSoldPrice;

	private Double originalPrice;

	private Double actualPrice;

	private ViewProductDto product;

	private String status;

	private String returnCashMethod;

	public ViewOrderItemDetailsDto(Long itemId, Integer quantity, Integer discount, String size, Double unitSoldPrice,
			Double totalSoldPrice, Double originalPrice, Double actualPrice, ViewProductDto product, String status,
			String returnCashMethod) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
		this.discount = discount;
		this.size = size;
		this.unitSoldPrice = unitSoldPrice;
		this.totalSoldPrice = totalSoldPrice;
		this.originalPrice = originalPrice;
		this.actualPrice = actualPrice;
		this.product = product;
		this.status = status;
		this.returnCashMethod = returnCashMethod;

	}

	public static List<ViewOrderItemDetailsDto> createFromOrder(Order order) {
		List<Item> items = order.getOrderedItems();
		List<ViewOrderItemDetailsDto> itemDtos = new ArrayList<>();
		for (Item item : items) {

			ViewProductDto productDto = ViewProductDto.createFromProduct(item.getProduct());

			String returnCashMethod = "";
			if (item.getReturnCashMethod() != null) {
				returnCashMethod = ReturnCashMethod.getString(item.getReturnCashMethod());
			}

			ViewOrderItemDetailsDto itemDto = new ViewOrderItemDetailsDto(item.getId(), item.getQuantity(),
					item.getDiscount(), item.getSize(), item.getUnitSoldPrice(), item.getTotalSoldPrice(),
					item.getOriginalPrice(), item.getActualPrice(), productDto, ItemStatus.getString(item.getStatus()),
					returnCashMethod);
			itemDtos.add(itemDto);
		}
		return itemDtos;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
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

	public ViewProductDto getProduct() {
		return product;
	}

	public void setProduct(ViewProductDto product) {
		this.product = product;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReturnCashMethod() {
		return returnCashMethod;
	}

	public void setReturnCashMethod(String returnCashMethod) {
		this.returnCashMethod = returnCashMethod;
	}

}
