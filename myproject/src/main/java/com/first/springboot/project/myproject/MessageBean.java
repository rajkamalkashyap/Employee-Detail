package com.first.springboot.project.myproject;

public class MessageBean {
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageBean(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageBean [message=" + message + "]";
	}
	
}
