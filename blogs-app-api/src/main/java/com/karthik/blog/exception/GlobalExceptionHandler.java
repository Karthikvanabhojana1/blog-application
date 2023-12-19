package com.karthik.blog.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>>  handleMethodArgumentNotValidException(MethodArgumentNotValidException methodargumentNotvalid){
		Map<String, String> errorResponse= new HashMap<>();
		methodargumentNotvalid.getBindingResult().getAllErrors().forEach((error)->{
			String FieldName=((FieldError) error).getField();
			String msg=error.getDefaultMessage();
			errorResponse.put(FieldName, msg);
		});
		return new ResponseEntity<Map<String,String>>(errorResponse,HttpStatus.BAD_REQUEST);
		
	}
	 @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
	    public ResponseEntity<APIResponse> handleIncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException ex) {
	        // Handle the exception and return an appropriate response
	        String errorMessage = "Email ID entered is already being used by some other user";
			APIResponse api=new APIResponse(errorMessage,false);

	        return new ResponseEntity<APIResponse>(api, HttpStatus.CONFLICT);
	    }

}
