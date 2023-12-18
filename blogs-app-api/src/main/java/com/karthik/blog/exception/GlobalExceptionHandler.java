package com.karthik.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.karthik.blog.payloads.APIResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIResponse> resourseNotfoundExceptionHandler(ResourceNotFoundException ex){
		
		String msg=ex.getMessage();
		
		APIResponse api=new APIResponse(msg,false);
		 
		
		return new ResponseEntity<APIResponse>(api,HttpStatus.NOT_FOUND);
		
		
		
		
	}

}
