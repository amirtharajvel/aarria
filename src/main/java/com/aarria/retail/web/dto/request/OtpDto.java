package com.aarria.retail.web.dto.request;

public class OtpDto {

	private String otp;
	private String mobile;

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "OtpDto [otp=" + otp + ", mobile=" + mobile + "]";
	}
}
