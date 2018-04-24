package com.scp.util;

public class CustomErrorType extends Exception {
	 private String errorMessage;
	 
	    public CustomErrorType(String errorMessage){
	        this.errorMessage = errorMessage;
	    }
	 
	    public String getErrorMessage() {
	        return errorMessage;
	    }
	 
	}

