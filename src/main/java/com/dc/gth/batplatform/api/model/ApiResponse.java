package com.dc.gth.batplatform.api.model;

import java.io.Serializable;

public class ApiResponse implements Serializable {
	private static final long serialVersionUID = -3729341532477417344L;

	private String status;
    private String message;

    public ApiResponse(String status, String message){
    	this.status = status;
    	this.message = message; 
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
