package com.mnikitin.gravahal.services;

/**
 * Created by mnikitin
 *
 * General class for all service-specific exceptions.
 * Main purpose is to send status about any exceptional behaviour upper to Controller
 * and let him decide how to handle this case and how to correctly respond to user.
 */
public class ServiceException extends RuntimeException {

	public ServiceException(String errorMessage) {
		super(errorMessage);
	}
}
