package com.aarria.retail.persistence.repository.datatables;


public class TableRepositoryException extends TablePaginationException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableRepositoryException(String string, Throwable ex) {
        super(string, ex);
    }
}
