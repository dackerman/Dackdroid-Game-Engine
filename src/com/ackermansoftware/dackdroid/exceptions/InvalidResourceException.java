package com.ackermansoftware.dackdroid.exceptions;

public class InvalidResourceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidResourceException() {
		super();
	}

	public InvalidResourceException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public InvalidResourceException(String detailMessage) {
		super(detailMessage);
	}

	public InvalidResourceException(Throwable throwable) {
		super(throwable);
	}
}
