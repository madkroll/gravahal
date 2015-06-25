package com.mnikitin.gravahal.services;

import com.mnikitin.gravahal.domain.GameSession;

/**
 * Created by mnikitin
 */
public interface GameService {

	GameSession getGameSession();

	void doTurn(int pitToPickUpFrom);
}
