package com.appgate.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {
	private LocalDateTime timestamp;
	private int status;
	private String message;
	private T data;
	private String path;
}