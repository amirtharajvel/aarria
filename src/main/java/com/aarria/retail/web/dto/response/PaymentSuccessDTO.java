package com.aarria.retail.web.dto.response;

public class PaymentSuccessDTO {

	private String orderNo;
	private String mobile;
	private String email;
	private String estimatedDeilvery;
	private Long orderId;

	public PaymentSuccessDTO() {
		super();
	}

	public PaymentSuccessDTO(String orderNo, String mobile, String email, String estimatedDeilvery, Long orderId) {
		super();
		this.orderNo = orderNo;
		this.mobile = mobile;
		this.email = email;
		this.estimatedDeilvery = estimatedDeilvery;
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstimatedDeilvery() {
		return estimatedDeilvery;
	}

	public void setEstimatedDeilvery(String estimatedDeilvery) {
		this.estimatedDeilvery = estimatedDeilvery;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "PaymentSuccessDTO [orderNo=" + orderNo + ", mobile=" + mobile + ", email=" + email
				+ ", estimatedDeilvery=" + estimatedDeilvery + ", orderId=" + orderId + "]";
	}

}
