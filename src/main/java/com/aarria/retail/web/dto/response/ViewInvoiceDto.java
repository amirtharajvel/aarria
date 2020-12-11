package com.aarria.retail.web.dto.response;

import java.util.List;

public class ViewInvoiceDto {

	private String invoiceNo;
	private String invoiceDate;
	private String receiptVoucher;
	private AddressDto shippingAddress;
	private AddressDto billingAddress;
	private Double totalInvoiceAmount;

	private List<ViewInvoiceItemsDto> items;

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getReceiptVoucher() {
		return receiptVoucher;
	}

	public void setReceiptVoucher(String receiptVoucher) {
		this.receiptVoucher = receiptVoucher;
	}

	public AddressDto getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(AddressDto shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public AddressDto getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(AddressDto billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Double getTotalInvoiceAmount() {
		return totalInvoiceAmount;
	}

	public void setTotalInvoiceAmount(Double totalInvoiceAmount) {
		this.totalInvoiceAmount = totalInvoiceAmount;
	}

	public List<ViewInvoiceItemsDto> getItems() {
		return items;
	}

	public void setItems(List<ViewInvoiceItemsDto> items) {
		this.items = items;
	}

}
