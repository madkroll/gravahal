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
public class PutToGravaHalAction {

	private static final Logger logger = LoggerFactory.getLogger(PutToGravaHalAction.class);

	@Autowired
	private PickUpAction pickUpAction;

	/**
	 * This method picks up all stones from each ordinary pit of target player
	 * and puts them all to Grava Hal
	 *
	 * @param player target player
	 */
	public void putAllStonesToGravaHal(PlayerProfile player) {
		int[] pits = player.getPits();

		int movedStones = 0;
		for (int i = 0; i <= Settings.LAST_PIT_INDEX; i++) {
			movedStones += pickUpAction.pickUpFromPit(player, i);
		}

		pits[Settings.GRAVA_HAL_PIT_INDEX] = pits[Settings.GRAVA_HAL_PIT_INDEX] + movedStones;
		logger.debug("Number of stones moved to Grava Hal: " + movedStones);
		logger.debug("Total number of stones in Grava Hal: " + pits[Settings.GRAVA_HAL_PIT_INDEX]);
	}
}
