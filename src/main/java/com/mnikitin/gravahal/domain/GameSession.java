package com.mnikitin.gravahal.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mnikitin
 *
 * Domain object used as data container for current game state.
 * Has no business logic.
 */
@Component
public class GameSession {

	private boolean currentTopPlayer = false;
	private boolean gameOver = false;

	@Autowired
	private PlayerProfile topPlayer;

	@Autowired
	private PlayerProfile bottomPlayer;

	public PlayerProfile getTopPlayer() {
		return topPlayer;
	}

	public PlayerProfile getBottomPlayer() {
		return bottomPlayer;
	}

	public void switchPlayer() {
		currentTopPlayer = !currentTopPlayer;
	}

	public boolean isCurrentTopPlayer() {
		return currentTopPlayer;
	}

	public void setCurrentTopPlayer(boolean currentTopPlayer) {
		this.currentTopPlayer = currentTopPlayer;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
}
