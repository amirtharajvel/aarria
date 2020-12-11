package com.aarria.retail.web.dto.response;

import java.util.List;

import com.aarria.retail.core.util.Enum.OrderStatus;
import com.aarria.retail.core.util.Enum.Shipper;

public class ViewOrderDetailsDto {

	private Long id;
	private String orderId;
	private String mobile;
	private String orderedDate;
	private Double totalOrderAmount;

	private List<ViewOrderItemDetailsDto> items;
	private String status;
	private ViewDeliveryDto delivery;
	private OrderStatus[] orderStatusEnum = OrderStatus.values();
	private Shipper[] shipper = Shipper.values();
	private boolean isCashOnDelivery;
	private List<BankAccountDetailsDto> bankAccounts;
	private BankAccountDetailsDto selectedBankAccountForRefund;
	private String catalogue;

	private Double totalRefundableAmount;
	private String returnCashMethod;
	private Boolean isAnyOfTheItemIsReturned;

	private Boolean showReturnPickup;
	private Boolean showRefundButton;
	private Boolean showArrangeReturnButton;

	private Boolean isReturnPickupArranged;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(String orderedDate) {
		this.orderedDate = orderedDate;
	}

	public List<ViewOrderItemDetailsDto> getItems() {
		return items;
	}

	public void setItems(List<ViewOrderItemDetailsDto> items) {
		this.items = items;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ViewDeliveryDto getDelivery() {
		return delivery;
	}

	public void setDelivery(ViewDeliveryDto delivery) {
		this.delivery = delivery;
	}

	public OrderStatus[] getOrderStatusEnum() {
		return orderStatusEnum;
	}

	public void setOrderStatusEnum(OrderStatus[] orderStatusEnum) {
		this.orderStatusEnum = orderStatusEnum;
	}

	public Shipper[] getShipper() {
		return shipper;
	}

	public void setShipper(Shipper[] shipper) {
		this.shipper = shipper;
	}

	public Double getTotalOrderAmount() {
		return totalOrderAmount;
	}

	public void setTotalOrderAmount(Double totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}

	public boolean getIsCashOnDelivery() {
		return isCashOnDelivery;
	}

	public void setIsCashOnDelivery(boolean isCashOnDelivery) {
		this.isCashOnDelivery = isCashOnDelivery;
	}

	public List<BankAccountDetailsDto> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(List<BankAccountDetailsDto> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public void setCashOnDelivery(boolean isCashOnDelivery) {
		this.isCashOnDelivery = isCashOnDelivery;
	}

	public Double getTotalRefundableAmount() {
		return totalRefundableAmount;
	}

	public void setTotalRefundableAmount(Double totalRefundableAmount) {
		this.totalRefundableAmount = totalRefundableAmount;
	}

	public String getReturnCashMethod() {
		return returnCashMethod;
	}

	public void setReturnCashMethod(String returnCashMethod) {
		this.returnCashMethod = returnCashMethod;
	}

	public Boolean getIsAnyOfTheItemIsReturned() {
		return isAnyOfTheItemIsReturned;
	}

	public void setIsAnyOfTheItemIsReturned(Boolean isAnyOfTheItemIsReturned) {
		this.isAnyOfTheItemIsReturned = isAnyOfTheItemIsReturned;
	}

	public Boolean getShowReturnPickup() {
		return showReturnPickup;
	}

	public void setShowReturnPickup(Boolean showReturnPickup) {
		this.showReturnPickup = showReturnPickup;
	}

	public Boolean getShowRefundButton() {
		return showRefundButton;
	}

	public void setShowRefundButton(Boolean showRefundButton) {
		this.showRefundButton = showRefundButton;
	}

	public BankAccountDetailsDto getSelectedBankAccountForRefund() {
		return selectedBankAccountForRefund;
	}

	public void setSelectedBankAccountForRefund(BankAccountDetailsDto selectedBankAccountForRefund) {
		this.selectedBankAccountForRefund = selectedBankAccountForRefund;
	}

	public Boolean getIsReturnPickupArranged() {
		return isReturnPickupArranged;
	}

	public void setIsReturnPickupArranged(Boolean isReturnPickupArranged) {
		this.isReturnPickupArranged = isReturnPickupArranged;
	}

	public Boolean getShowArrangeReturnButton() {
		return showArrangeReturnButton;
	}

	public void setShowArrangeReturnButton(Boolean showArrangeReturnButton) {
		this.showArrangeReturnButton = showArrangeReturnButton;
	}

	public String getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(String catalogue) {
		this.catalogue = catalogue;
	}

}
