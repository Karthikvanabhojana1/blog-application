package com.karthik.blog.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	
	
	String resourceName;
	String fieldName;
	long fieldValue;
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	String fieldsValue;
	public ResourceNotFoundException(String resourceName, String fieldName, String fieldsValue) {
		super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldsValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldsValue = fieldsValue;
	}

}
