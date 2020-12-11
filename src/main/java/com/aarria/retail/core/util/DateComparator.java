package com.aarria.retail.core.util;

import java.util.Comparator;

import com.aarria.retail.core.domain.Order;

public class DateComparator implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
		if (o1.getOrderedDate().before(o2.getOrderedDate())) {
			return -1;
		} else if (o1.getOrderedDate().after(o2.getOrderedDate())) {
			return 1;
		} else {
			return 0;
		}
	}
}
