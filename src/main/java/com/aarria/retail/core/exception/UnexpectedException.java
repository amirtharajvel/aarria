package com.aarria.retail.core.exception;

public class UnexpectedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	public UnexpectedException() {
		super();
	}

	public UnexpectedException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "Error : " + message;
	}

}
