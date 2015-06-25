package com.mnikitin.gravahal.controllers.validators;

import com.mnikitin.gravahal.domain.Settings;
import org.springframework.stereotype.Component;

/**
 * Created by mnikitin
 */
@Component
public class PitIndexValidator {

	public void validate(int pitIndex) {
		validate(pitIndex >= Settings.FIRST_PIT_INDEX, "Pit index could not be less than " + Settings.FIRST_PIT_INDEX);
		validate(pitIndex <= Settings.LAST_PIT_INDEX, "Pit index could not be greater than " + Settings.LAST_PIT_INDEX);
	}

	private void validate(boolean statement, String errorMessage) {
		if (!statement) {
			throw new ParamValidationException(errorMessage);
		}
	}
}
