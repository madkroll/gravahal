package com.mnikitin.gravahal.actions;

import com.mnikitin.gravahal.domain.PlayerProfile;
import com.mnikitin.gravahal.domain.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by mnikitin
 */
@Component
public class SowAction {

	private static final Logger logger = LoggerFactory.getLogger(SowAction.class);

	/**
	 * This method sows stones of target player one by one to each pit starting from pit next to pitPickedFrom,
	 * including Grava Hal pit. Next pit after Grava Hal pit is - first pit.
	 * As result, returns index of the pit in which last stone has been sown
	 *
	 * @param playerProfile target player
	 * @param pitPickedFrom pit, from where stones has been picked up
	 * @param stonesCount   stones number to sow
	 * @return pit index where last stone has been sown
	 */
	public int sowStones(PlayerProfile playerProfile, int pitPickedFrom, int stonesCount) {
		logger.debug("Sow " + stonesCount + " stones picked up from " + pitPickedFrom + " pit.");
		int pitCursor = pitPickedFrom;
		while (stonesCount > 0) {
			if (pitCursor == Settings.GRAVA_HAL_PIT_INDEX) {
				pitCursor = Settings.FIRST_PIT_INDEX;
				logger.debug("Last pit was Grava Hal. Select first pit to sow in: " + Settings.FIRST_PIT_INDEX);
			} else {
				pitCursor++;
				logger.debug("Select next pit to sow in: " + pitCursor);
			}

			logger.debug("Sow next stone to selected pit");
			playerProfile.getPits()[pitCursor] = playerProfile.getPits()[pitCursor] + 1;
			stonesCount--;
			logger.debug("Stones left to sow: " + stonesCount);
		}

		return pitCursor;
	}
}
