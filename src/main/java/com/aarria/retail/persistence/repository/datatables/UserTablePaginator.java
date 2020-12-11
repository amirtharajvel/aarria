package com.aarria.retail.persistence.repository.datatables;

import com.aarria.retail.core.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserTablePaginator extends AbstractTablePaginator<Order> {

	@Autowired
	public UserTablePaginator(TableRepository<Order> repo) {
		super(repo);
	}

}