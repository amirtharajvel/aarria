package com.aarria.retail.persistence.repository.datatables;

/**
 * Used to provide a custom representation for a table field value.
 *
 * @author David Castelletti
 *
 */
public interface FieldFormatter {
	String format(Object fieldValue);
}
