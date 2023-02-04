package com.vibhuti.productservice.exception;

public class ProductServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProductServiceException() {
		super();
	}

	public ProductServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProductServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductServiceException(String message) {
		super(message);
	}

	public ProductServiceException(Throwable cause) {
		super(cause);
	}

}
