package com.mnikitin.gravahal.domain;

/**
 * Created by mnikitin
 *
 * General Grava Hal settings
 */
public interface Settings {

	/**
	 * Index of the first pit
	 * */
	int FIRST_PIT_INDEX = 0;

	/**
	 * Number of all ordinary pits
	 * */
	int ORDINARY_PITS_COUNT = 6;

	/**
	 * Index of the first pit
	 * */
	int LAST_PIT_INDEX = ORDINARY_PITS_COUNT - 1;

	/**
	 * All pits count (including Grava Hal pit)
	 * */
	int ALL_PITS_COUNT = ORDINARY_PITS_COUNT + 1;

	/**
	 * Number of stones in each ordinary pit at the start of the game
	 * */
	int INIT_STONES_COUNT_IN_PIT = 6;

	/**
	 * Grava Hal pit index
	 * */
	int GRAVA_HAL_PIT_INDEX = 6;
}
