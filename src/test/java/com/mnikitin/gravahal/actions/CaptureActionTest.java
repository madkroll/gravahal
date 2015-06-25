package com.mnikitin.gravahal.actions;

import com.mnikitin.gravahal.domain.PlayerProfile;
import com.mnikitin.gravahal.domain.Settings;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Created by mnikitin
 */
public class CaptureActionTest {

	private static final int LAST_STONE = 1;
	private static final int OPPONENTS_STONES = 10;
	private static final int STONES_IN_GRAVA_HAL = 4;

	@Mock
	private PickUpAction pickUpAction;

	@InjectMocks
	private CaptureAction target;

	@Before
	public void initMocks() {
		target = new CaptureAction();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_captureFromOpponentPit() {
		PlayerProfile currentPlayer = new PlayerProfile();
		currentPlayer.setPits(new int[]{LAST_STONE, 0, 0, 0, 0, 0, STONES_IN_GRAVA_HAL});

		PlayerProfile opponentPlayer = new PlayerProfile();
		opponentPlayer.setPits(new int[]{0, 0, 0, 0, 0, OPPONENTS_STONES, 0});

		Mockito.when(pickUpAction.pickUpFromPit(currentPlayer, Settings.FIRST_PIT_INDEX)).thenReturn(LAST_STONE);
		Mockito.when(pickUpAction.pickUpFromPit(opponentPlayer, Settings.LAST_PIT_INDEX)).thenReturn(OPPONENTS_STONES);

		target.captureFromOpponentPit(currentPlayer, opponentPlayer, Settings.FIRST_PIT_INDEX);
		Assert.assertEquals("Wrong number of stones in Grava Hall", STONES_IN_GRAVA_HAL + LAST_STONE + OPPONENTS_STONES, currentPlayer.getPits()[Settings.GRAVA_HAL_PIT_INDEX]);
	}
}
