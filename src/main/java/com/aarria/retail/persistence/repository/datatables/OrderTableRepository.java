package com.aarria.retail.persistence.repository.datatables;

import com.aarria.retail.core.domain.Item;
import com.aarria.retail.core.domain.Order;
import com.aarria.retail.core.util.Enum.ItemStatus;
import com.aarria.retail.core.util.Enum.OrderStatus;
import com.aarria.retail.core.util.Util;
import com.aarria.retail.persistence.repository.ItemRepository;
import com.aarria.retail.persistence.repository.OrderRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository
public class OrderTableRepository implements TableRepository<Order> {

	private static Logger LOGGER = LogManager.getLogger(OrderTableRepository.class);

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public long countTotalEntries(Integer status) throws TableRepositoryException {
		long count = orderRepository.countByStatus(status);
		if (status.equals(OrderStatus.RETURN_PICKUP_REQUESTED.ordinal())) {
			List<Order> orders = orderRepository
					.getOrderByItemStatus(Util.getReturnPickupRequestedStatusInAdminForItems());
			removeNonPickupRequestOrders(orders);
			if (CollectionUtils.isNotEmpty(orders)) {
				count += orders.size();
			}

			// this means All Cancelled and refunded orders
		} else if (status.equals(OrderStatus.CANCELLED_WITHIN_30_MINS.ordinal())) {
			count = orderRepository.countByStatus(status);
			count += orderRepository.countByStatus(OrderStatus.CANCELLED_WHILE_PACKAGING.ordinal());
			count += orderRepository.countByStatus(OrderStatus.REFUNDED.ordinal());
			count += itemRepository.countByStatus(ItemStatus.CANCELLED_WHILE_PACKAGING.ordinal());
			// count += itemRepository.countByStatus(ItemStatus.REFUNDED.ordinal());
			count += itemRepository.countByStatus(ItemStatus.CANCELLED_WITHIN_30_MINS.ordinal());
		}

		return count;
	}

	@Override
	public List<Order> findPageEntries(PaginationCriteria pCriteria) {
		// ... order and filter your data here

		List<Order> orders = new ArrayList<>();
		if (pCriteria.getStatus().equals(OrderStatus.RETURN_PICKUP_REQUESTED.ordinal())) {
			orders.addAll(findRecordsByItemStatus(Util.getReturnPickupRequestedStatusInAdminForItems()));
			removeNonPickupRequestOrders(orders);

			// this means All Cancelled and refunded orders
		} else if (pCriteria.getStatus().equals(OrderStatus.CANCELLED_WITHIN_30_MINS.ordinal())) {
			orders.addAll(findRecordsByStatus(OrderStatus.CANCELLED_WHILE_PACKAGING.ordinal()));
			orders.addAll(findRecordsByStatus(OrderStatus.REFUNDED.ordinal()));
			orders.addAll(findRecordsByItemStatus(Util.getCancelAndRefundStatusForItems()));
		}

		orders.addAll(findRecordsByStatus(pCriteria.getStatus()));
		orders = new ArrayList<>(new HashSet<>(orders));
		return orders;
	}

	private List<Order> findRecordsByItemStatus(List<Integer> statusList) {

		List<Order> orders = new ArrayList<>();
		try {
			List<Order> ordersFromDb = orderRepository.getOrderByItemStatus(statusList);
			if (CollectionUtils.isNotEmpty(ordersFromDb)) {
				orders = ordersFromDb;
			}

		} catch (Exception e) {
			LOGGER.error("Error in data table " + e);
		}

		return orders;
	}

	private List<Order> findRecordsByStatus(Integer status) {
		List<Order> orders = new ArrayList<>();
		try {
			List<Order> ordersFromDb = orderRepository.findByStatus(status);
			if (CollectionUtils.isNotEmpty(ordersFromDb)) {
				orders = ordersFromDb;
			}

		} catch (Exception e) {
			LOGGER.error("Error in data table " + e);
		}

		return orders;
	}

	private void removeNonPickupRequestOrders(List<Order> orders) {
		if (CollectionUtils.isNotEmpty(orders)) {
			for (int i = 0; i < orders.size(); i++) {
				Order order = orders.get(i);
				if (CollectionUtils.isNotEmpty(order.getOrderedItems())) {

					boolean isAnyItemHasReturnPickupRequested = isAnyItemHasReturnPickupRequested(order);

					for (Item item : order.getOrderedItems()) {
						// if one of the item has refunded status and order is delivered then it's not a
						// pick up request order
						if (order.getStatus() != null && order.getStatus().equals(OrderStatus.DELIVERED.ordinal())
								&& item.getStatus() != null && item.getStatus().equals(ItemStatus.REFUNDED.ordinal())
								&& !isAnyItemHasReturnPickupRequested) {
							orders.remove(order);
						}
					}
				}
			}
		}
	}

	boolean isAnyItemHasReturnPickupRequested(Order order) {
		for (Item item : order.getOrderedItems()) {
			if (item.getStatus() != null && item.getStatus().equals(ItemStatus.RETURN_PICKUP_REQUESTED.ordinal())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public long countFilteredEntries(PaginationCriteria pCriteria) throws TableRepositoryException {
		// TODO Auto-generated method stub
		return 0;
	}

}