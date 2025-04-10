package com.appgate.util;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.appgate.dto.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;

public class ResponseBuilder {

    public static <T> ApiResponse<T> buildResponse(T data, String message, HttpStatus status, HttpServletRequest request) {
        return ApiResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .message(message)
                .data(data)
                .path(request.getRequestURI())
                .build();
    }
}