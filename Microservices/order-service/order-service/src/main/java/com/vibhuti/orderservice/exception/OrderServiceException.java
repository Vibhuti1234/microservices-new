package com.vibhuti.orderservice.exception;

public class OrderServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public OrderServiceException() {
		super();
	}

	public OrderServiceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public OrderServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public OrderServiceException(String arg0) {
		super(arg0);
	}

	public OrderServiceException(Throwable arg0) {
		super(arg0);
	}

}
