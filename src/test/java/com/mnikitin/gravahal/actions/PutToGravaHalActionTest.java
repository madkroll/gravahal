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
import org.springframework.stereotype.Component;

/**
 * Created by mnikitin
 */
@Component
public class PutToGravaHalActionTest {

	@Mock
	private PickUpAction pickUpAction;

	@InjectMocks
	private PutToGravaHalAction target;

	@Before
	public void initMocks() {
		target = new PutToGravaHalAction();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_putAllStonesToGravaHal_allAreFilled() {
		PlayerProfile playerProfile = new PlayerProfile();
		playerProfile.setPits(new int[]{1, 2, 3, 4, 5, 6, 7});

		for (int i = Settings.FIRST_PIT_INDEX; i <= Settings.LAST_PIT_INDEX; i++) {
			Mockito.when(pickUpAction.pickUpFromPit(playerProfile, i)).thenReturn(playerProfile.getPits()[i]);
		}

		target.putAllStonesToGravaHal(playerProfile);
		Assert.assertEquals("Wrong number of stones in Grava Hal", 28, playerProfile.getPits()[Settings.GRAVA_HAL_PIT_INDEX]);
	}

	@Test
	public void test_putAllStonesToGravaHal_allAreEmpty() {
		PlayerProfile playerProfile = new PlayerProfile();
		playerProfile.setPits(new int[]{0, 0, 0, 0, 0, 0, 0});

		for (int i = Settings.FIRST_PIT_INDEX; i <= Settings.LAST_PIT_INDEX; i++) {
			Mockito.when(pickUpAction.pickUpFromPit(playerProfile, i)).thenReturn(playerProfile.getPits()[i]);
		}

		target.putAllStonesToGravaHal(playerProfile);
		Assert.assertEquals("Wrong number of stones in Grava Hal", 0, playerProfile.getPits()[Settings.GRAVA_HAL_PIT_INDEX]);
	}

}
