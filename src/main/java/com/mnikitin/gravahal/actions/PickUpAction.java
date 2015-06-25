package com.mnikitin.gravahal.actions;

import com.mnikitin.gravahal.domain.PlayerProfile;
import com.mnikitin.gravahal.domain.Settings;
import com.mnikitin.gravahal.services.StonePickUpException;
import org.springframework.stereotype.Component;

/**
 * Created by mnikitin
 */
@Component
public class PickUpAction {

	/**
	 * This method picks up all stones from selected pit for target player.
	 * If selected pit is empty already - this method throws StonePickUpException.
	 *
	 * @param playerProfile target player
	 * @param pitIndex      selected pit
	 * @return number of picked stones
	 * <p>
	 * @see StonePickUpException
	 */
	public int pickUpFromOwnPit(PlayerProfile playerProfile, int pitIndex) {
		if (playerProfile.getPits()[pitIndex] == 0) {
			throw new StonePickUpException("Target pit is empty. Nothing to pick up.");
		}

		return pickUpFromPit(playerProfile, pitIndex);
	}

	/**
	 * This method picks up all stones from selected pit for target player.
	 *
	 * @param playerProfile target player
	 * @param pitIndex      selected pit
	 * @return number of picked stones
	 */
	public int pickUpFromPit(PlayerProfile playerProfile, int pitIndex) {
		if (pitIndex == Settings.GRAVA_HAL_PIT_INDEX) {
			throw new RuntimeException("Target pit is Grava Hal pit. It's not allowed to pick up stones from it.");
		}

		int pickedStonesCount = playerProfile.getPits()[pitIndex];
		playerProfile.getPits()[pitIndex] = 0;
		return pickedStonesCount;
	}
}
