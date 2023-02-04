package com.vibhuti.orderservice.exception;

public class OutOfStockException extends OrderServiceException {

	private static final long serialVersionUID = 1L;

	public OutOfStockException() {
		super();
	}

	public OutOfStockException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public OutOfStockException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public OutOfStockException(String arg0) {
		super(arg0);
	}

	public OutOfStockException(Throwable arg0) {
		super(arg0);
	}

}
