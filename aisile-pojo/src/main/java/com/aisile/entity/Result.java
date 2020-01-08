package com.aisile.entity;

import java.io.Serializable;

public class Result implements Serializable{

	private boolean success;
	private String message;
	private long code;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "Result [success=" + success + ", message=" + message + ", code=" + code + "]";
	}
	public Result(boolean success, String message, long code) {
		this.success = success;
		this.message = message;
		this.code = code;
	}
	public Result(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	public Result() {
	}
	
}
