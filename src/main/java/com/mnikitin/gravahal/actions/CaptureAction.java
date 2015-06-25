package com.mnikitin.gravahal.actions;

import com.mnikitin.gravahal.domain.PlayerProfile;
import com.mnikitin.gravahal.domain.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mnikitin
 */
@Component
public class CaptureAction {

	@Autowired
	private PickUpAction pickUpAction;

	private static final Logger logger = LoggerFactory.getLogger(CaptureAction.class);

	/**
	 * This method captures last stone from current player's target pit,
	 * captures all stones from the opposite opponent's pit
	 * and puts them all to current player's Grava Hal
	 */
	public void captureFromOpponentPit(PlayerProfile currentPlayer, PlayerProfile opponentPlayer, int lastStonePit) {
		logger.debug("Capture last stone from own pit: " + lastStonePit);
		int ownStone = pickUpAction.pickUpFromPit(currentPlayer, lastStonePit);

		int oppositePitIndex = calculateOppositePitIndex(lastStonePit);
		logger.debug("Capture all stones from opposite pit: " + oppositePitIndex);
		int opponentStones = pickUpAction.pickUpFromPit(opponentPlayer, oppositePitIndex);
		logger.debug("Captured opponent's stones: " + opponentStones);

		int totalCapturedStones = ownStone + opponentStones;
		logger.debug("Total number of captured stones: " + totalCapturedStones);
		currentPlayer.getPits()[Settings.GRAVA_HAL_PIT_INDEX] = currentPlayer.getPits()[Settings.GRAVA_HAL_PIT_INDEX] + totalCapturedStones;
	}

	private int calculateOppositePitIndex(int userPitIndex) {
		return Settings.ORDINARY_PITS_COUNT - 1 - userPitIndex;
	}

}
