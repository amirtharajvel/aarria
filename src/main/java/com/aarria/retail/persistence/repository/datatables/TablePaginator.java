package com.aarria.retail.persistence.repository.datatables;

/**
 * The main component, used to generate a {@link TablePage}} according to {@link PaginationCriteria}.
 *
 * @author David Castelletti
 *
 */
public interface TablePaginator {

	TablePage getPage(PaginationCriteria pCriteria) throws TablePaginationException;

}
