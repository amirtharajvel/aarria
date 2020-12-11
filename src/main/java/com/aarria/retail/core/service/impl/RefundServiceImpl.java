package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.domain.Item;
import com.aarria.retail.core.domain.Order;
import com.aarria.retail.core.service.RefundService;

public class RefundServiceImpl implements RefundService {

	@Override
	public void deductItemAmountFromOrder(Order order, Item item) {
		if (order.getIsCashOnDelivery() == true) {
			return;
		}
		
		
	}

}
