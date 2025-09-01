package com.policy360.auth.exception;


import com.policy360.auth.controller.BaseController;
import com.policy360.auth.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends BaseController {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleUserAlreadyExists(
            UserAlreadyExistsException ex, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getFieldName(), ex.getMessage());
        return error(errors, "Conflict error", request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleUserNotFound(
            UserNotFoundException ex, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getFieldName(), ex.getMessage());
        return error(errors, "Not Found", request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidCredentials(
            InvalidCredentialsException ex, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getFieldName(), ex.getMessage());
        return error(errors, "Unauthorized", request, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return error(errors, "Validation failed", request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntimeException(
            RuntimeException ex, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return error(errors, "Internal server error", request, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
