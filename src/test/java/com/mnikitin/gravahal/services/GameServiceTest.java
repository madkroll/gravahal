package com.mnikitin.gravahal.services;

import com.mnikitin.gravahal.domain.GameSession;
import com.mnikitin.gravahal.domain.Settings;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Created by mnikitin
 */
public class GameServiceTest {

	@InjectMocks
	private GameService target;

	@Mock
	private GameSession gameSession;

	@Before
	public void initMocks() {
		target = new GameServiceImpl();
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = RuntimeException.class)
	public void test_doTurn_invalidPitIndex() {
		target.doTurn(Settings.FIRST_PIT_INDEX - 1);
	}

	// TODO:

}
