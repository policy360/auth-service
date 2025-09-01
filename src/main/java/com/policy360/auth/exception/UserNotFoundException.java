package com.policy360.auth.exception;

public class UserNotFoundException extends RuntimeException {
    private final String fieldName;

    public UserNotFoundException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
