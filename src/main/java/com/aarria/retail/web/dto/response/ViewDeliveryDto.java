package com.aarria.retail.web.dto.response;

import java.text.SimpleDateFormat;

import com.aarria.retail.core.domain.Delivery;

public class ViewDeliveryDto {

	private static final String E_DD_MMM_YYYY_HH_MM_SS_Z = "E, dd MMM YYYY HH:mm:ss Z";

	private String shippingDate;

	private String expectedDeliveryDate;

	private String deliveredDate;

	private AddressDto shippingAddress;

	private String trackingId;
	private String trackingName;
	private String shipper;
	private Double shippingCharge;

	private String returnTrackingId;
	private String returnCourierServiceName;
	private Double returnShippingCharge;

	public ViewDeliveryDto(String shippingDate, String expectedDeliveryDate, String deliveredDate,
			AddressDto shippingAddress, String trackingId, String trackingName, String shipper, Double shippingCharge,
			String returnTrackingId, String returnCourierServiceName, Double returnShippingCharge) {
		super();
		this.shippingDate = shippingDate;
		this.expectedDeliveryDate = expectedDeliveryDate;
		this.deliveredDate = deliveredDate;
		this.shippingAddress = shippingAddress;
		this.trackingId = trackingId;
		this.trackingName = trackingName;
		this.shipper = shipper;
		this.shippingCharge = shippingCharge;
		this.returnTrackingId = returnTrackingId;
		this.returnCourierServiceName = returnCourierServiceName;
		this.returnShippingCharge = returnShippingCharge;
	}

	public static ViewDeliveryDto createDtoFromDelivery(Delivery delivery) {

		String shipDate = null;
		if (delivery.getShippingDate() != null) {
			shipDate = new SimpleDateFormat(E_DD_MMM_YYYY_HH_MM_SS_Z).format(delivery.getShippingDate());
		}

		String expectDeliveryDate = null;
		if (delivery.getExpectedDeliveryDate() != null) {
			expectDeliveryDate = new SimpleDateFormat(E_DD_MMM_YYYY_HH_MM_SS_Z)
					.format(delivery.getExpectedDeliveryDate());
		}

		String deliverDate = null;
		if (delivery.getDeliveredDate() != null) {
			deliverDate = new SimpleDateFormat(E_DD_MMM_YYYY_HH_MM_SS_Z).format(delivery.getDeliveredDate());
		}

		AddressDto shippingAddress = AddressDto.createDtoFromAddress(delivery.getBillingAddress());

		ViewDeliveryDto dto = new ViewDeliveryDto(shipDate, expectDeliveryDate, deliverDate, shippingAddress,
				delivery.getTrackingId(), delivery.getTrackingName(), delivery.getShipper(),
				delivery.getShippingCharge(), delivery.getReturnTrackingId(), delivery.getReturnCourierServiceName(),
				delivery.getReturnShippingCharge());

		return dto;
	}

	public String getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(String shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(String expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public String getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(String deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	public AddressDto getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(AddressDto shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public Double getShippingCharge() {
		return shippingCharge;
	}

	public void setShippingCharge(Double shippingCharge) {
		this.shippingCharge = shippingCharge;
	}

	public String getTrackingName() {
		return trackingName;
	}

	public void setTrackingName(String trackingName) {
		this.trackingName = trackingName;
	}

	public String getReturnTrackingId() {
		return returnTrackingId;
	}

	public void setReturnTrackingId(String returnTrackingId) {
		this.returnTrackingId = returnTrackingId;
	}

	public String getReturnCourierServiceName() {
		return returnCourierServiceName;
	}

	public void setReturnCourierServiceName(String returnCourierServiceName) {
		this.returnCourierServiceName = returnCourierServiceName;
	}

	public Double getReturnShippingCharge() {
		return returnShippingCharge;
	}

	public void setReturnShippingCharge(Double returnShippingCharge) {
		this.returnShippingCharge = returnShippingCharge;
	}

}
