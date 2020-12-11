package com.aarria.retail.web.dto.response;

public class LatLongResponseDto {

	private String deliveryArea;
	private String mobile;

	public LatLongResponseDto(String deliveryArea, String mobile) {
		super();
		this.deliveryArea = deliveryArea;
		this.mobile = mobile;
	}

	public String getDeliveryArea() {
		return deliveryArea;
	}

	public void setDeliveryArea(String deliveryArea) {
		this.deliveryArea = deliveryArea;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
