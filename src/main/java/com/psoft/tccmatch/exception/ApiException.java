package com.psoft.tccmatch.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends Exception {
    private final CustomErrorType apiError;

    public ApiException(CustomErrorType apiError) {
        super(apiError.getErrorMessage());
        this.apiError = apiError;
    }

    public ApiException(String message, HttpStatus statusCode) {
        this(new CustomErrorType(message, statusCode));
    }

    public ApiException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public HttpStatus getStatusCode() {
        return apiError.getStatusCode();
    }

    public CustomErrorType getApiError() {
        return apiError;
    }
}
