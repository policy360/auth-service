package com.policy360.auth.exception;

public class InvalidCredentialsException extends RuntimeException {
    private final String fieldName;

    public InvalidCredentialsException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
