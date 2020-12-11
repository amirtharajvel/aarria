package com.aarria.retail.web.dto.request;

import java.util.ArrayList;
import java.util.List;

public class PaymentDto {

	private String encResp;
	private String orderNo;

	private String order_id;
	private String tracking_id = "306003089767";
	private String bank_ref_no = "1490240133615";
	private String order_status = "Success";
	private String failure_message = "";
	private String payment_mode = "Net Banking";
	private String card_name = "AvenuesTest";
	private String status_code = null;
	private String status_message = "Y";
	private String currency = "INR";
	private Double amount = 7777.0;
	private String billing_name = "vishnu";
	private String billing_address = "vadavalli";
	private String billing_city = "coimbatore";
	private String billing_state = "tamil nadu";
	private String billing_zip = "641669";
	private String billing_country = "India";
	private String billing_tel = "9901411006";
	private String billing_email = "amirtharaj@live.com";
	private String delivery_name = "vishnu";
	private String delivery_address = "vadavalli";
	private String delivery_city = "coimbatore";
	private String delivery_state = "tamil nadu";
	private String delivery_zip = "641669";
	private String delivery_country = "India";
	private String delivery_tel = "9901411006";
	private String merchant_param1 = "";
	private String merchant_param2 = "";
	private String merchant_param3 = "";
	private String merchant_param4 = "";
	private String merchant_param5 = "";
	private String vault = "N";
	private String offer_type = null;
	private String offer_code = null;
	private Double discount_value = 0.0;
	private Double mer_amount = 7777.0;
	private String eci_value = null;
	private String retry = null;
	private Integer response_code = 0;
	private String billing_notes = "";
	private String trans_date = "23/03/2017 09:06:58";
	private String bin_country;

	private List<String> unIdentifiedPropertiesFromCCAvenue = new ArrayList<>();

	public String getEncResp() {
		return encResp;
	}

	public void setEncResp(String encResp) {
		this.encResp = encResp;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getTracking_id() {
		return tracking_id;
	}

	public void setTracking_id(String tracking_id) {
		this.tracking_id = tracking_id;
	}

	public String getBank_ref_no() {
		return bank_ref_no;
	}

	public void setBank_ref_no(String bank_ref_no) {
		this.bank_ref_no = bank_ref_no;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getFailure_message() {
		return failure_message;
	}

	public void setFailure_message(String failure_message) {
		this.failure_message = failure_message;
	}

	public String getPayment_mode() {
		return payment_mode;
	}

	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}

	public String getCard_name() {
		return card_name;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}

	public String getStatus_message() {
		return status_message;
	}

	public void setStatus_message(String status_message) {
		this.status_message = status_message;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getBilling_name() {
		return billing_name;
	}

	public void setBilling_name(String billing_name) {
		this.billing_name = billing_name;
	}

	public String getBilling_address() {
		return billing_address;
	}

	public void setBilling_address(String billing_address) {
		this.billing_address = billing_address;
	}

	public String getBilling_city() {
		return billing_city;
	}

	public void setBilling_city(String billing_city) {
		this.billing_city = billing_city;
	}

	public String getBilling_state() {
		return billing_state;
	}

	public void setBilling_state(String billing_state) {
		this.billing_state = billing_state;
	}

	public String getBilling_zip() {
		return billing_zip;
	}

	public void setBilling_zip(String billing_zip) {
		this.billing_zip = billing_zip;
	}

	public String getBilling_country() {
		return billing_country;
	}

	public void setBilling_country(String billing_country) {
		this.billing_country = billing_country;
	}

	public String getBilling_tel() {
		return billing_tel;
	}

	public void setBilling_tel(String billing_tel) {
		this.billing_tel = billing_tel;
	}

	public String getBilling_email() {
		return billing_email;
	}

	public void setBilling_email(String billing_email) {
		this.billing_email = billing_email;
	}

	public String getDelivery_name() {
		return delivery_name;
	}

	public void setDelivery_name(String delivery_name) {
		this.delivery_name = delivery_name;
	}

	public String getDelivery_address() {
		return delivery_address;
	}

	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}

	public String getDelivery_city() {
		return delivery_city;
	}

	public void setDelivery_city(String delivery_city) {
		this.delivery_city = delivery_city;
	}

	public String getDelivery_state() {
		return delivery_state;
	}

	public void setDelivery_state(String delivery_state) {
		this.delivery_state = delivery_state;
	}

	public String getDelivery_zip() {
		return delivery_zip;
	}

	public void setDelivery_zip(String delivery_zip) {
		this.delivery_zip = delivery_zip;
	}

	public String getDelivery_country() {
		return delivery_country;
	}

	public void setDelivery_country(String delivery_country) {
		this.delivery_country = delivery_country;
	}

	public String getDelivery_tel() {
		return delivery_tel;
	}

	public void setDelivery_tel(String delivery_tel) {
		this.delivery_tel = delivery_tel;
	}

	public String getMerchant_param1() {
		return merchant_param1;
	}

	public void setMerchant_param1(String merchant_param1) {
		this.merchant_param1 = merchant_param1;
	}

	public String getMerchant_param2() {
		return merchant_param2;
	}

	public void setMerchant_param2(String merchant_param2) {
		this.merchant_param2 = merchant_param2;
	}

	public String getMerchant_param3() {
		return merchant_param3;
	}

	public void setMerchant_param3(String merchant_param3) {
		this.merchant_param3 = merchant_param3;
	}

	public String getMerchant_param4() {
		return merchant_param4;
	}

	public void setMerchant_param4(String merchant_param4) {
		this.merchant_param4 = merchant_param4;
	}

	public String getMerchant_param5() {
		return merchant_param5;
	}

	public void setMerchant_param5(String merchant_param5) {
		this.merchant_param5 = merchant_param5;
	}

	public String getVault() {
		return vault;
	}

	public void setVault(String vault) {
		this.vault = vault;
	}

	public String getOffer_type() {
		return offer_type;
	}

	public void setOffer_type(String offer_type) {
		this.offer_type = offer_type;
	}

	public String getOffer_code() {
		return offer_code;
	}

	public void setOffer_code(String offer_code) {
		this.offer_code = offer_code;
	}

	public Double getDiscount_value() {
		return discount_value;
	}

	public void setDiscount_value(Double discount_value) {
		this.discount_value = discount_value;
	}

	public Double getMer_amount() {
		return mer_amount;
	}

	public void setMer_amount(Double mer_amount) {
		this.mer_amount = mer_amount;
	}

	public String getEci_value() {
		return eci_value;
	}

	public void setEci_value(String eci_value) {
		this.eci_value = eci_value;
	}

	public String getRetry() {
		return retry;
	}

	public void setRetry(String retry) {
		this.retry = retry;
	}

	public Integer getResponse_code() {
		return response_code;
	}

	public void setResponse_code(Integer response_code) {
		this.response_code = response_code;
	}

	public String getBilling_notes() {
		return billing_notes;
	}

	public void setBilling_notes(String billing_notes) {
		this.billing_notes = billing_notes;
	}

	public String getTrans_date() {
		return trans_date;
	}

	public void setTrans_date(String trans_date) {
		this.trans_date = trans_date;
	}

	public String getBin_country() {
		return bin_country;
	}

	public void setBin_country(String bin_country) {
		this.bin_country = bin_country;
	}

	public List<String> getUnIdentifiedPropertiesFromCCAvenue() {
		return unIdentifiedPropertiesFromCCAvenue;
	}

	public void setUnIdentifiedPropertiesFromCCAvenue(List<String> unIdentifiedPropertiesFromCCAvenue) {
		this.unIdentifiedPropertiesFromCCAvenue = unIdentifiedPropertiesFromCCAvenue;
	}

	@Override
	public String toString() {
		return "PaymentDto [encResp=" + encResp + ", orderNo=" + orderNo + ", order_id=" + order_id + ", tracking_id="
				+ tracking_id + ", bank_ref_no=" + bank_ref_no + ", order_status=" + order_status + ", failure_message="
				+ failure_message + ", payment_mode=" + payment_mode + ", card_name=" + card_name + ", status_code="
				+ status_code + ", status_message=" + status_message + ", currency=" + currency + ", amount=" + amount
				+ ", billing_name=" + billing_name + ", billing_address=" + billing_address + ", billing_city="
				+ billing_city + ", billing_state=" + billing_state + ", billing_zip=" + billing_zip
				+ ", billing_country=" + billing_country + ", billing_tel=" + billing_tel + ", billing_email="
				+ billing_email + ", delivery_name=" + delivery_name + ", delivery_address=" + delivery_address
				+ ", delivery_city=" + delivery_city + ", delivery_state=" + delivery_state + ", delivery_zip="
				+ delivery_zip + ", delivery_country=" + delivery_country + ", delivery_tel=" + delivery_tel
				+ ", merchant_param1=" + merchant_param1 + ", merchant_param2=" + merchant_param2 + ", merchant_param3="
				+ merchant_param3 + ", merchant_param4=" + merchant_param4 + ", merchant_param5=" + merchant_param5
				+ ", vault=" + vault + ", offer_type=" + offer_type + ", offer_code=" + offer_code + ", discount_value="
				+ discount_value + ", mer_amount=" + mer_amount + ", eci_value=" + eci_value + ", retry=" + retry
				+ ", response_code=" + response_code + ", billing_notes=" + billing_notes + ", trans_date=" + trans_date
				+ ", bin_country=" + bin_country + ", unIdentifiedPropertiesFromCCAvenue="
				+ unIdentifiedPropertiesFromCCAvenue + "]";
	}

}
