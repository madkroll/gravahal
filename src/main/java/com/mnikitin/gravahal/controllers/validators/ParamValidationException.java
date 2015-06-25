package com.mnikitin.gravahal.controllers.validators;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mnikitin
 */
@ResponseStatus(HttpStatus.OK)
public class ParamValidationException extends RuntimeException {

	private static final String MESSAGE_BASE = "Parameter has invalid value.";

	public ParamValidationException(String message) {
		super(MESSAGE_BASE + message);
	}
}
