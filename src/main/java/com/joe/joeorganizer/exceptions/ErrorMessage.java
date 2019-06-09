package com.joe.joeorganizer.exceptions;

import java.util.Date;

public class ErrorMessage {

	private Date timestamp;
	private String message;

	public ErrorMessage(String message) {
		this.message = message;
		this.timestamp = new Date();
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

}
