package com.aarria.retail.core.service;

import com.aarria.retail.core.domain.User;
import com.aarria.retail.web.dto.request.UpdateOrderDetailsDto;
import com.aarria.retail.web.dto.response.ViewOrderDetailsDto;

public interface AdminOrderService {

	ViewOrderDetailsDto createDto(Long orderIdPrimaryKey);
	void updateOrderDetails(UpdateOrderDetailsDto dto, User user);
}
