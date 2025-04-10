package com.appgate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.appgate.dto.ApiResponse;
import com.appgate.util.ResponseBuilder;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(NoSocialAccountException.class)
	public ResponseEntity<ApiResponse<Object>> handleNoSocialAccountException(NoSocialAccountException ex,
			HttpServletRequest request) {
		log.warn("No social account provided: {}", ex.getMessage());
		return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Object>> handleValidationException(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		log.warn("Validation error: {}", errorMessage);
		return buildErrorResponse(HttpStatus.BAD_REQUEST, errorMessage, request);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiResponse<Object>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpServletRequest request) {
		log.warn("Malformed JSON request: {}", ex.getMessage());
		return buildErrorResponse(HttpStatus.BAD_REQUEST, "Malformed JSON request", request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex, HttpServletRequest request) {
		log.error("Unhandled exception occurred", ex);
		return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", request);
	}

	private ResponseEntity<ApiResponse<Object>> buildErrorResponse(HttpStatus status, String message,
			HttpServletRequest request) {
		return ResponseEntity.status(status).body(ResponseBuilder.buildResponse(null, message, status, request));
	}
}