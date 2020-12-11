package com.aarria.retail.core.service;

import com.aarria.retail.core.domain.Item;
import com.aarria.retail.core.domain.Order;
import com.aarria.retail.web.dto.request.RemoveItemFromOrderDto;

public interface ItemService {

	Item save(Item item);

	Item findOne(Long id);

	void delete(Long id);

	void delete(Item item);
	
	Order removeItemFromOrder(RemoveItemFromOrderDto dto, Order order);

	void addProductStockBackOnRemoveItem(Item item);

}
