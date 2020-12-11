package com.aarria.retail.web.dto.response;

import com.aarria.retail.core.domain.Order;

public class PrepareOrderResultDto {

	private String message;
	private Order order;
	private String reason;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
