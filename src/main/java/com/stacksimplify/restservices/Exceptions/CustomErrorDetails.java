package com.stacksimplify.restservices.Exceptions;

import java.util.Date;

//simple custom error details bean
public class CustomErrorDetails {
	private Date timestamp;
	private String message;
	private String errordetails;
	
	public CustomErrorDetails(Date timestamp, String message, String errordetails) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errordetails = errordetails;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrordetails() {
		return errordetails;
	}

	public void setErrordetails(String errordetails) {
		this.errordetails = errordetails;
	}
	
	

}
