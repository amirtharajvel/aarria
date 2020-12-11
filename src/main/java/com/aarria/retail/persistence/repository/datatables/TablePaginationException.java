package com.aarria.retail.persistence.repository.datatables;

public class TablePaginationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TablePaginationException(String message, Throwable cause) {
		super(message, cause);
	}

	public TablePaginationException(String message) {
		super(message);
	}

}
