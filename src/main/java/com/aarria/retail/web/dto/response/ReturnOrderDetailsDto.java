package com.aarria.retail.web.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReturnOrderDetailsDto {

	private String orderId;
	private List<BankAccountDetailsDto> bankAccounts;
	private List<AddressDto> addresses;
	private List<ReturnOrderItemDetailsDto> items = new ArrayList<>();
	private List<String> pickupDatesOptions = new ArrayList<>();

	public ReturnOrderDetailsDto() {
		super();
		calculatePickupdateOptions();
	}

	private void calculatePickupdateOptions() {
		LocalDateTime now = LocalDateTime.now().plusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
		String formatDateTime = now.format(formatter);
		pickupDatesOptions.add(formatDateTime);

		now = now.plusDays(1);
		pickupDatesOptions.add(now.format(formatter));

		now = now.plusDays(1);
		pickupDatesOptions.add(now.format(formatter));

	}

	public List<ReturnOrderItemDetailsDto> getItems() {
		return items;
	}

	public void setItems(List<ReturnOrderItemDetailsDto> items) {
		this.items = items;
	}

	public List<BankAccountDetailsDto> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(List<BankAccountDetailsDto> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public List<AddressDto> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressDto> addresses) {
		this.addresses = addresses;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<String> getPickupDatesOptions() {
		return pickupDatesOptions;
	}

	public void setPickupDatesOptions(List<String> pickupDatesOptions) {
		this.pickupDatesOptions = pickupDatesOptions;
	}

}
