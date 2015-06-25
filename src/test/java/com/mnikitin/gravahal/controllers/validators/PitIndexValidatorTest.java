package com.mnikitin.gravahal.controllers.validators;

import com.mnikitin.gravahal.domain.Settings;
import org.junit.Test;

/**
 * Created by mnikitin
 *
 * Pure JUnit test for PitIndexValidator class
 */
public class PitIndexValidatorTest {

	private PitIndexValidator target = new PitIndexValidator();

	@Test
	public void test_validate_isValid() {
		for (int i = Settings.FIRST_PIT_INDEX; i < Settings.LAST_PIT_INDEX; i++) {
			target.validate(i);
		}
	}

	@Test(expected = ParamValidationException.class)
	public void test_validate_lessThan() {
		target.validate(Settings.FIRST_PIT_INDEX - 1);
	}

	@Test(expected = ParamValidationException.class)
	public void test_validate_greaterThan() {
		target.validate(Settings.GRAVA_HAL_PIT_INDEX);
	}
}
