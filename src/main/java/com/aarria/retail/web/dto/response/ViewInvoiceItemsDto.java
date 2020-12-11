package com.aarria.retail.web.dto.response;

public class ViewInvoiceItemsDto {

	private Integer serialNo;
	private String descriptionOfSupplies;
	private String hanCode;
	private Integer quantity;
	private Double unitPrice;
	private Double totalBasePrice;
	private Integer discount;
	private Double taxableValue;
	private Double igstRate;
	private Double igstAmount;
	private Double netAmount;

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	public String getDescriptionOfSupplies() {
		return descriptionOfSupplies;
	}

	public void setDescriptionOfSupplies(String descriptionOfSupplies) {
		this.descriptionOfSupplies = descriptionOfSupplies;
	}

	public String getHanCode() {
		return hanCode;
	}

	public void setHanCode(String hanCode) {
		this.hanCode = hanCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getTotalBasePrice() {
		return totalBasePrice;
	}

	public void setTotalBasePrice(Double totalBasePrice) {
		this.totalBasePrice = totalBasePrice;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
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

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

}
