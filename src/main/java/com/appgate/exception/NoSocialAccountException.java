package com.appgate.exception;

public class NoSocialAccountException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSocialAccountException() {
		super("At least one social account (Twitter or Facebook) must be provided");
	}
}