package com.mnikitin.gravahal.services;

import com.mnikitin.gravahal.actions.CaptureAction;
import com.mnikitin.gravahal.actions.PickUpAction;
import com.mnikitin.gravahal.actions.PutToGravaHalAction;
import com.mnikitin.gravahal.actions.SowAction;
import com.mnikitin.gravahal.domain.GameSession;
import com.mnikitin.gravahal.domain.PlayerProfile;
import com.mnikitin.gravahal.domain.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mnikitin
 * <p>
 * This service provides general logic to manage Grava Hal game flow.
 * TODO: thread-safe issue. Operations are not atomic. Synchronization is required.
 */
@Service
public class GameServiceImpl implements GameService {

	private static final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

	@Autowired
	private GameSession gameSession;

	@Autowired
	private PickUpAction pickUpAction;

	@Autowired
	private SowAction sowAction;

	@Autowired
	private CaptureAction captureAction;

	@Autowired
	private PutToGravaHalAction putToGravaHalAction;

	/**
	 * This method performs all turn phases.
	 *
	 * @param pitToPickFrom pit, selected by current player, to pick stones from
	 */
	@Override
	public void doTurn(int pitToPickFrom) {
		if (pitToPickFrom < Settings.FIRST_PIT_INDEX || pitToPickFrom > Settings.LAST_PIT_INDEX) {
			throw new RuntimeException("Incorrect pit index: " + pitToPickFrom);
		}

		logger.debug("Selected pit index to pick stones from: " + pitToPickFrom);
		PlayerProfile currentPlayer = getCurrentPlayerPits();
		PlayerProfile opponentPlayer = getOpponentPits();

		int pickedStones = pickUpAction.pickUpFromOwnPit(currentPlayer, pitToPickFrom);
		logger.debug("Stones count picked up from pit: " + pickedStones);

		int lastPitIndex = sowAction.sowStones(currentPlayer, pitToPickFrom, pickedStones);
		logger.debug("Last stone has been sown in pit: " + lastPitIndex);

		if (lastPitIndex != Settings.GRAVA_HAL_PIT_INDEX) {
			if (currentPlayer.getPits()[lastPitIndex] == 1) {
				logger.debug("Stone has been sown to empty pit. Capture stones.");
				captureAction.captureFromOpponentPit(currentPlayer, opponentPlayer, lastPitIndex);
			}

			logger.debug("Stone hasn't been sown to Grava Hal. Switch to opponent player.");
			gameSession.switchPlayer();
		}

		if (!hasStonesInPits(currentPlayer) || !hasStonesInPits(opponentPlayer)) {
			logger.debug("One of the players has no any stones. Finishing the game.");
			finalizeGame();
		}
	}

	/**
	 * This method is responsible to finalize Grava Hal game and count scores for each player
	 */
	private void finalizeGame() {
		logger.debug("Game is over");
		gameSession.setGameOver(true);
		putToGravaHalAction.putAllStonesToGravaHal(gameSession.getTopPlayer());
		putToGravaHalAction.putAllStonesToGravaHal(gameSession.getBottomPlayer());
	}

	/**
	 * This method decides which player is current player this turn
	 * TODO: rework it
	 *
	 * @return current player
	 */
	private PlayerProfile getCurrentPlayerPits() {
		if (gameSession.isCurrentTopPlayer()) {
			return gameSession.getTopPlayer();
		} else {
			return gameSession.getBottomPlayer();
		}
	}

	/**
	 * This method decides which player is opponent player this turn
	 * TODO: rework it
	 *
	 * @return opponent player
	 */
	private PlayerProfile getOpponentPits() {
		if (gameSession.isCurrentTopPlayer()) {
			return gameSession.getBottomPlayer();
		} else {
			return gameSession.getTopPlayer();
		}
	}

	/**
	 * This method checks if target player has any left stones in ordinary pits (not in Grava Hal)
	 *
	 * @param playerProfile target player
	 * @return true, if player has at least one left stone placed in any ordinary pit (not in Grava Hal)
	 */
	private boolean hasStonesInPits(PlayerProfile playerProfile) {
		for (int i = 0; i < Settings.ALL_PITS_COUNT - 1; i++) {
			if (playerProfile.getPits()[i] > 0) {
				return true;
			}
		}

		return false;
	}

	public GameSession getGameSession() {
		return gameSession;
	}
}
