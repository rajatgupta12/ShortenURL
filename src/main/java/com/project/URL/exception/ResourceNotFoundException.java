package com.project.URL.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue)
	{
		System.out.printf("%s %s already exists: %s", resourceName, fieldName, fieldValue);
	}
	
	public ResourceNotFoundException(String fieldName)
	{
		System.out.printf("for this %s, URL not Found", fieldName);
	}	
}
