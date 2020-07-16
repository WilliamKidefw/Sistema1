package com.william.sistema1.modelo;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {

	private List<T> data;	
	public static final int STATUS_OK=0,STATUS_ERROR=1;
	private int error;
	private String message;
	
	public Response() {
		this.error=0;
		this.message="";
		this.data = new ArrayList<T>();
	}
	
	/**
	 * @param status
	 */
	public Response(int status) {
		this();
		this.error=status;
		this.message="";
	}
	
	/**
	 * @param status
	 * @param message
	 */
	public Response(int status,String message) {
		this();
		this.error=status;
		this.message=message;
	}
	
	/**
	 * @param status
	 * @param data
	 */
	public Response(int error,List<T> data) {
		this();
		this.error=error;
		this.message="";
		this.data = data;
	}
	
	/**
	 * 
	 * @param error
	 * @param message
	 * @param data
	 */
	public Response(int error, String message,List<T> data) {
		super();
		this.data = data;
		this.error = error;
		this.message = message;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
