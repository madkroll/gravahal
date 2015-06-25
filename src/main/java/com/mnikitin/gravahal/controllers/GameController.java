package com.mnikitin.gravahal.controllers;

import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mnikitin.
 */
public interface GameController {

	ModelAndView showGamePage();

	ModelAndView doTurn(int pitIndex);
}
