package com.aarria.retail.core.service;

import com.aarria.retail.core.domain.Item;
import com.aarria.retail.core.domain.Order;

public interface RefundService {
	void deductItemAmountFromOrder(Order order, Item item);
}
