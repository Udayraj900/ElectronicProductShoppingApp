package com.mindtree.webservices.exception;

public class productException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMsg;
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public productException() {
	}

	public productException(String errorMsg) {
		super(errorMsg);
		this.errorMsg = errorMsg;
	}

	public productException(Throwable cause) {
		super(cause);
	}

	public productException(String message, Throwable cause) {
		super(message, cause);
	}

	public productException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	
}
