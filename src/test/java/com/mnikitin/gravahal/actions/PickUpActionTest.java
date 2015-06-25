package com.mnikitin.gravahal.actions;

import com.mnikitin.gravahal.domain.PlayerProfile;
import com.mnikitin.gravahal.domain.Settings;
import com.mnikitin.gravahal.services.StonePickUpException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by mnikitin
 */
public class PickUpActionTest {

	private static final int EXPECTED_STONES_COUNT = 6;

	private PickUpAction target;

	@Before
	public void initMocks() {
		target = new PickUpAction();
	}

	@Test(expected = StonePickUpException.class)
	public void test_pickUpFromOwnPit_emptyPit() {
		PlayerProfile playerWithAllEmptyPits = Mockito.mock(PlayerProfile.class);
		Mockito.when(playerWithAllEmptyPits.getPits()).thenReturn(new int[Settings.ALL_PITS_COUNT]);
		target.pickUpFromOwnPit(playerWithAllEmptyPits, Settings.FIRST_PIT_INDEX);
	}

	@Test
	public void test_pickUpFromOwnPit_stonesExist() {
		PlayerProfile playerWithAllEmptyPits = Mockito.mock(PlayerProfile.class);
		Mockito.when(playerWithAllEmptyPits.getPits()).thenReturn(new int[]{EXPECTED_STONES_COUNT, 0, 0, 0, 0, 0, 0});
		int pickedUpStones = target.pickUpFromOwnPit(playerWithAllEmptyPits, Settings.FIRST_PIT_INDEX);
		Assert.assertEquals("Unexpected number of picked stones", EXPECTED_STONES_COUNT, pickedUpStones);
	}

	@Test(expected = RuntimeException.class)
	public void test_pickUpFromOwnPit_gravaHalPit() {
		PlayerProfile playerWithAllEmptyPits = Mockito.mock(PlayerProfile.class);
		Mockito.when(playerWithAllEmptyPits.getPits()).thenReturn(new int[]{0, 0, 0, 0, 0, 0, EXPECTED_STONES_COUNT});
		target.pickUpFromOwnPit(playerWithAllEmptyPits, Settings.GRAVA_HAL_PIT_INDEX);
	}
}
