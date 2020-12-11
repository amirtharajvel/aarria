package com.aarria.retail.core.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aarria.retail.core.domain.Item;
import com.aarria.retail.core.domain.Order;
import com.aarria.retail.core.domain.Product;
import com.aarria.retail.core.domain.ProductStock;
import com.aarria.retail.core.service.ItemService;
import com.aarria.retail.core.service.ProductStockService;
import com.aarria.retail.core.util.Constants;
import com.aarria.retail.core.util.Enum.ItemStatus;
import com.aarria.retail.core.util.Enum.OrderStatus;
import com.aarria.retail.core.util.Enum.ReturnCashMethod;
import com.aarria.retail.core.util.Util;
import com.aarria.retail.persistence.repository.ItemRepository;
import com.aarria.retail.web.dto.request.RemoveItemFromOrderDto;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ProductStockService productStockService;

	@Override
	public Item findOne(Long id) {
		return itemRepository.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		itemRepository.deleteById(id);
	}

	@Override
	public void delete(Item item) {
		itemRepository.delete(item);
	}

	@Override
	public Item save(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public Order removeItemFromOrder(RemoveItemFromOrderDto dto, Order order) {

		Item item = findOneFromOrder(dto.getItemId(), order);

		if (item != null) {

			// check if it is already cancelled
			if (!Util.isItemEligibleForRemove(order, item)) {
				return null;
			}

			boolean isOrderHasSingleItem = false;
			if (order != null) {
				isOrderHasSingleItem = isOrderHasSingleItem(order);
			}

			if (dto.getFromPage().contains(Constants.MYORDERS)) {
				item.setStatus(ItemStatus.CANCELLED_WITHIN_30_MINS.ordinal());

				if (isOrderHasSingleItem) {
					order.setStatus(OrderStatus.CANCELLED_WITHIN_30_MINS.ordinal());
				}

			} else if (dto.getFromPage().contains(Constants.VIEW_ORDER_DETAILS)) {
				item.setStatus(ItemStatus.CANCELLED_WHILE_PACKAGING.ordinal());

				if (isOrderHasSingleItem) {
					order.setStatus(OrderStatus.CANCELLED_WHILE_PACKAGING.ordinal());
				}
			}

			// Means it's Non-COD
			if (dto.getReturnCashMethod() != null) {
				item.setStatus(ItemStatus.REFUND_INITIATED.ordinal());
				item.setReturnCashMethod(dto.getReturnCashMethod());
				order.setReturnCashMethod(dto.getReturnCashMethod());

				if (dto.getReturnCashMethod().equals(ReturnCashMethod.BANK_ACCOUNT.ordinal())
						&& dto.getSelectedAccountForRefund() != null) {
					order.setSelectedAccountForRefund(dto.getSelectedAccountForRefund());
				}
			}

			addProductStockBackOnRemoveItem(item);
			save(item);

			return order;
		}

		return null;
	}

	@Override
	public void addProductStockBackOnRemoveItem(Item item) {
		Product product = item.getProduct();
		ProductStock stock = productStockService.findStockByProductAndSizeWithZeroStock(product, item.getSize());
		if (stock == null) {
			stock = new ProductStock();
			stock.setProduct(product);
			stock.setSize(item.getSize());

			int s = stock.getStock() == null ? 0 : stock.getStock();
			stock.setStock(s + item.getQuantity());
		} else {
			stock.setStock(stock.getStock() + item.getQuantity());
		}
		productStockService.save(stock);
	}

	private Item findOneFromOrder(Long itemId, Order order) {
		if (CollectionUtils.isNotEmpty(order.getOrderedItems())) {
			for (Item item : order.getOrderedItems()) {
				if (item.getId().equals(itemId)) {
					return item;
				}
			}
		}

		return null;
	}

	private boolean isOrderHasSingleItem(Order order) {
		boolean isOrderHasSingleItem;
		isOrderHasSingleItem = order.getOrderedItems() != null && order.getOrderedItems().size() == 1;
		int i = 0;
		if (!isOrderHasSingleItem) {
			for (Item item : order.getOrderedItems()) {
				if (Util.isItemPurchased(item)) {
					i++;
				}
			}

			if (i <= 1) {
				isOrderHasSingleItem = true;
			}
		}
		return isOrderHasSingleItem;
	}

}
