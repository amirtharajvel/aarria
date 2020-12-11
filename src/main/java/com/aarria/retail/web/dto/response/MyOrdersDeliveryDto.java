package com.aarria.retail.web.dto.response;

import com.aarria.retail.core.domain.Delivery;
import com.aarria.retail.core.util.Enum.Shipper;

public class MyOrdersDeliveryDto {

	private String trackingUrl;

	// consignment no - for India Post
	// tracking no - for Fed Ex
	// C/n or Ref no - for profession courier
	// AWB/ Consignment Number or Consignment no for DTDC
	private String trackingNoName;

	private String trackingNo;

	private String returnTrackingId;
	private String returnCourierServiceName;

	private Boolean isReturnShippingNotAvailable;

	public MyOrdersDeliveryDto(String trackingUrl, String trackingNoName, String trackingNo, String returnTrackingId,
			String returnCourierServiceName, Boolean isReturnShippingNotAvailable) {
		super();
		this.trackingUrl = trackingUrl;
		this.trackingNoName = trackingNoName;
		this.trackingNo = trackingNo;
		this.returnTrackingId = returnTrackingId;
		this.returnCourierServiceName = returnCourierServiceName;
		this.isReturnShippingNotAvailable = isReturnShippingNotAvailable;
	}

	public MyOrdersDeliveryDto() {
	}

	public static MyOrdersDeliveryDto create(Delivery delivery) {

		if (delivery != null) {

			return new MyOrdersDeliveryDto(getShipper(delivery.getShipper()), delivery.getTrackingName(),
					delivery.getTrackingId(), delivery.getReturnTrackingId(), delivery.getReturnCourierServiceName(),
					delivery.getIsReturnShippingNotAvailable());
		}
		return null;
	}

	private static String getShipper(String shipper) {
		if (shipper == null) {
			return null;
		}

		if (shipper.equals(Shipper.FEDEX.name())) {
			return "https://www.fedex.com/apps/fedextrack/?action=track&cntry_code=in";
		}

		return "https://www.indiapost.gov.in/VAS/Pages/trackconsignment.aspx";
	}

	public String getTrackingUrl() {
		return trackingUrl;
	}

	public void setTrackingUrl(String trackingUrl) {
		this.trackingUrl = trackingUrl;
	}

	public String getTrackingNoName() {
		return trackingNoName;
	}

	public void setTrackingNoName(String trackingNoName) {
		this.trackingNoName = trackingNoName;
	}

	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
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

	public Boolean getIsReturnShippingNotAvailable() {
		return isReturnShippingNotAvailable;
	}

	public void setIsReturnShippingNotAvailable(Boolean isReturnShippingNotAvailable) {
		this.isReturnShippingNotAvailable = isReturnShippingNotAvailable;
	}

	@Override
	public String toString() {
		return "MyOrdersDeliveryDto [trackingUrl=" + trackingUrl + ", trackingNoName=" + trackingNoName
				+ ", trackingNo=" + trackingNo + "]";
	}

}
