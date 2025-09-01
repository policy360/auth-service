package com.policy360.auth.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // exclude null fields in JSON
public class ApiResponse<T> {

    private String status;        // SUCCESS / ERROR
    private String message;       // Human readable message
    private T data;               // Generic data type (User, Claim, Policy, etc.)
    private Object errors;        // Field errors / system errors
    private LocalDateTime timestamp;
    private String path;

    public static <T> ApiResponse<T> success(String message, T data, String path) {
        return ApiResponse.<T>builder()
                .status("SUCCESS")
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .path(path)
                .build();
    }

    public static <T> ApiResponse<T> error(String message, Object errors, String path) {
        return ApiResponse.<T>builder()
                .status("ERROR")
                .message(message)
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .path(path)
                .build();
    }
}
