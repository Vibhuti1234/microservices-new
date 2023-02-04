package com.vibhuti.productservice.exception;

public class NoProductsFoundException extends ProductServiceException {

	private static final long serialVersionUID = 1L;

	public NoProductsFoundException() {
		super();
	}

	public NoProductsFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoProductsFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoProductsFoundException(String message) {
		super(message);
	}

	public NoProductsFoundException(Throwable cause) {
		super(cause);
	}

}
