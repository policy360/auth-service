package com.policy360.auth.exception;

public class UserAlreadyExistsException extends RuntimeException {

    private String fieldName;

    public UserAlreadyExistsException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}

