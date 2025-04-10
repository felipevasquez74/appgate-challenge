package com.appgate.util;

import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class MDCLoggingFilter {

	private MDCLoggingFilter() {
	}

	public static void builderMDC() {
		MDC.put("requestId", UUID.randomUUID().toString());
	}
}
