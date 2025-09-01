package com.policy360.auth.controller;


import com.policy360.auth.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class BaseController {

    protected <T> ResponseEntity<ApiResponse<T>> success(T data, String message, HttpServletRequest request) {
        ApiResponse<T> response = ApiResponse.<T>builder()
                .status("SUCCESS")
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    protected <T> ResponseEntity<ApiResponse<T>> created(T data, String message, HttpServletRequest request) {
        ApiResponse<T> response = ApiResponse.<T>builder()
                .status("SUCCESS")
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    protected <T> ResponseEntity<ApiResponse<T>> error(Object errors, String message, HttpServletRequest request, HttpStatus status) {
        ApiResponse<T> response = ApiResponse.<T>builder()
                .status("ERROR")
                .message(message)
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(response);
    }
}
