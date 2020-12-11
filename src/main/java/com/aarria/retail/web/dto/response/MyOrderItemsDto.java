package com.aarria.retail.web.dto.response;

import com.aarria.retail.core.util.Enum.ItemStatus;
import com.aarria.retail.core.util.Util;

import java.util.Date;

public class MyOrderItemsDto {

	private Long itemId;

	private String image;

	private String productName;

	private Double totalSoldPrice;

	private Double unitSoldPrice;

	private Integer quantity;

	private String sellerName;

	private Boolean isReturnable;

	private String orderItemId;

	private Long productId;

	private String size;

	private String status;

	private String returnCashMethod;

	private String pid;
	
	public MyOrderItemsDto(Long itemId, String image, String productName, Double unitSoldPrice, Double totalSoldPrice,
			Integer quantity, String sellerName, Boolean isReturnable, Date deliveredDate, String orderItemId,
			Integer status, Long productId, String size, String returnCashMethod, String pid) {
		super();
		this.itemId = itemId;
		this.image = image;
		this.productName = productName;
		this.totalSoldPrice = totalSoldPrice;
		this.quantity = quantity;
		this.sellerName = sellerName;
		this.isReturnable = isReturnable;
		this.orderItemId = orderItemId;
		this.unitSoldPrice = unitSoldPrice;
		this.productId = productId;
		this.size = size;
		this.returnCashMethod = returnCashMethod;
		this.pid = pid;

		this.image = Util.generateImageLocation(image);
		this.status = ItemStatus.getString(status);

	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getTotalSoldPrice() {
		return totalSoldPrice;
	}

	public void setTotalSoldPrice(Double totalSoldPrice) {
		this.totalSoldPrice = totalSoldPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Boolean getIsReturnable() {
		return isReturnable;
	}

	public void setIsReturnable(Boolean isReturnable) {
		this.isReturnable = isReturnable;
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Double getUnitSoldPrice() {
		return unitSoldPrice;
	}

	public void setUnitSoldPrice(Double unitSoldPrice) {
		this.unitSoldPrice = unitSoldPrice;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "MyOrderItemsDto [itemId=" + itemId + ", image=" + image + ", productName=" + productName
				+ ", totalSoldPrice=" + totalSoldPrice + ", unitSoldPrice=" + unitSoldPrice + ", quantity=" + quantity
				+ ", sellerName=" + sellerName + ", isReturnable=" + isReturnable + ", orderItemId=" + orderItemId
				+ ", productId=" + productId + ", size=" + size + "]";
	}

}
