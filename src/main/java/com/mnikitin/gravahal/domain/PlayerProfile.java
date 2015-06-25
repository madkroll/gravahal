package com.mnikitin.gravahal.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by mnikitin
 *
 * Domain object used as data container for player state.
 * Has no business logic.
 */
@Component
@Scope("prototype")
public class PlayerProfile {

	private int[] pits = new int[Settings.ALL_PITS_COUNT];

	@PostConstruct
	public void initProfile() {
		for (int i = 0; i < Settings.ALL_PITS_COUNT - 1; i++) {
			pits[i] = Settings.INIT_STONES_COUNT_IN_PIT;
		}
	}

	public int[] getPits() {
		return pits;
	}

	public void setPits(int[] pits) {
		this.pits = pits;
	}
}
