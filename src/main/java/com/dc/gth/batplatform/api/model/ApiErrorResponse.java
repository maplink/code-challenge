package com.dc.gth.batplatform.api.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

public class ApiErrorResponse extends ApiResponse implements Serializable{
	private static final long serialVersionUID = -1432934153247741744L;
	
	private Collection<String> errors;
	 
	public ApiErrorResponse(String status, String message, Collection<String> errors) {
		super(status, message);
        this.errors = errors;
    }
	
	public ApiErrorResponse(String status, String message, String error) {
		super(status, message);
        this.errors = Arrays.asList(error);
    }

	public Collection<String> getErrors() {
		return errors;
	}

	public void setErrors(Collection<String> errors) {
		this.errors = errors;
	}
	
}
