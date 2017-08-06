package com.dc.gth.batplatform.utils;

import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.dc.gth.batplatform.api.model.ApiErrorResponse;

@Component
public class ErrorValidationHandler {
	private final static Logger LOGGER = LoggerFactory.getLogger(ErrorValidationHandler.class);
	
	@Inject    
	private MessageSource messageSource;

	public ResponseEntity<ApiErrorResponse> buildApiErrorResponse(String messageResource, HttpStatus status ){
		LOGGER.debug("Building api error response");
		ApiErrorResponse errorResponse =new ApiErrorResponse(status.getReasonPhrase(), "Validation Error", this.getMessage(messageResource));
		return new ResponseEntity<ApiErrorResponse>(errorResponse, status);
	}
	
	public ResponseEntity<ApiErrorResponse> buildApiErrorResponse(Errors errors, HttpStatus status){
		LOGGER.debug("Constraint violation");
		Stream<String> messageStream = errors.getAllErrors().stream().map(e -> this.getMessage(e.getDefaultMessage()));
		return new ResponseEntity<ApiErrorResponse>(new ApiErrorResponse(status.getReasonPhrase(),
				this.getMessage("validation.user.errormessage"), messageStream.collect(Collectors.toList())), status);
	}
	
	private String getMessage(String keyError){
		return this.messageSource.getMessage(keyError, null, Locale.ENGLISH);
	}
}
