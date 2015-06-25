package com.mnikitin.gravahal.actions;

import com.mnikitin.gravahal.domain.PlayerProfile;
import com.mnikitin.gravahal.domain.Settings;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by mnikitin
 */
public class SowActionTest {

	private static final int STONES_COUNT_TO_SOW = 6;

	private SowAction target;

	@Before
	public void initMocks() {
		target = new SowAction();
	}

	@Test
	public void test_sowStones_sixStonesToEmptyPits() {
		PlayerProfile playerProfile = new PlayerProfile();
		playerProfile.setPits(new int[]{0, 0, 0, 0, 0, 0, 0});

		int lastPit = target.sowStones(playerProfile, Settings.FIRST_PIT_INDEX, STONES_COUNT_TO_SOW);
		Assert.assertTrue("Wrong number of stones in pits", Arrays.equals(new int[]{0, 1, 1, 1, 1, 1, 1}, playerProfile.getPits()));
		Assert.assertEquals("Wrong last pit index", Settings.GRAVA_HAL_PIT_INDEX, lastPit);
	}

	@Test
	public void test_sowStones_sixStonesBehindTheLastPit() {
		PlayerProfile playerProfile = new PlayerProfile();
		playerProfile.setPits(new int[]{0, 0, 0, 0, 0, 0, 0});

		int lastPit = target.sowStones(playerProfile, Settings.LAST_PIT_INDEX, STONES_COUNT_TO_SOW);
		Assert.assertTrue("Wrong number of stones in pits", Arrays.equals(new int[]{1, 1, 1, 1, 1, 0, 1}, playerProfile.getPits()));
		Assert.assertEquals("Wrong last pit index", 4, lastPit);
	}

	@Test
	public void test_sowStones_twoFullCycles() {
		PlayerProfile playerProfile = new PlayerProfile();
		playerProfile.setPits(new int[]{0, 0, 0, 0, 0, 0, 0});

		int lastPit = target.sowStones(playerProfile, Settings.FIRST_PIT_INDEX, STONES_COUNT_TO_SOW * 3);
		Assert.assertTrue("Wrong number of stones in pits", Arrays.equals(new int[]{2, 3, 3, 3, 3, 2, 2}, playerProfile.getPits()));
		Assert.assertEquals("Wrong last pit index", 4, lastPit);
	}
}
