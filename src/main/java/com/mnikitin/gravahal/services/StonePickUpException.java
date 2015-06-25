package com.mnikitin.gravahal.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mnikitin
 */
@ResponseStatus(HttpStatus.OK)
public class StonePickUpException extends ServiceException {

	private static final String MESSAGE_BASE = "Failed to pick up stone from the given pit. ";

	public StonePickUpException(String message) {
		super(MESSAGE_BASE + message);
	}
}
