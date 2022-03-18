package com.psoft.tccmatch.exception;

import org.springframework.http.HttpStatus;

public class CustomErrorType {
    private final String errorMessage;
    private final HttpStatus statusCode;

    public CustomErrorType(String errorMessage, HttpStatus statusCode){
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public CustomErrorType(String errorMessage) {
        this(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
