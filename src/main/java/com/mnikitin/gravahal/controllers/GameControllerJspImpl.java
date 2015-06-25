package com.mnikitin.gravahal.controllers;

import com.mnikitin.gravahal.controllers.validators.ParamValidationException;
import com.mnikitin.gravahal.controllers.validators.PitIndexValidator;
import com.mnikitin.gravahal.domain.GameSession;
import com.mnikitin.gravahal.services.GameService;
import com.mnikitin.gravahal.services.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Grava Hal controller.
 * Handles all HTTP requests from user regarding to general Grava Hal requests and responses back with JSP.
 * */
@Controller
@RequestMapping("/gravahal")
public class GameControllerJspImpl implements GameController
{
    private static final Logger logger = LoggerFactory.getLogger(GameControllerJspImpl.class);

    private static final String GAME_MODEL_NAME = "game";
    private static final String GAME_SESSION_KEY = "gameSession";
    private static final String ERROR_KEY = "error";

    @Autowired
    @Qualifier("pitIndexValidator")
    private PitIndexValidator pitIndexValidator;

    @Autowired
    @Qualifier("gameServiceImpl")
    private GameService gameService;

    /**
     * Handles service exceptions.
     * Shows warning message to user.
     *
     * @param e service exception
     * @return modelAndView containing game board model and error message
     * */
    @ ExceptionHandler(ServiceException.class)
    public ModelAndView handleServiceExceptions(ServiceException e) {
        logger.error("Service exception has been caught.", e);

        GameSession gameSession = gameService.getGameSession();

        ModelAndView modelAndView = new ModelAndView(GAME_MODEL_NAME);
        modelAndView.addObject(GAME_SESSION_KEY, gameSession);
        modelAndView.addObject(ERROR_KEY, e.getMessage());
        return modelAndView;
    }

    /**
     * Handles validation exceptions.
     * Shows warning message to user.
     *
     * @param e parameter validation exception
     * @return modelAndView containing game board model and error message
     * */
    @ExceptionHandler(ParamValidationException.class)
    public ModelAndView handleValidationExceptions(ParamValidationException e) {
        logger.error("Validation exception has been caught.", e);

        GameSession gameSession = gameService.getGameSession();

        ModelAndView modelAndView = new ModelAndView(GAME_MODEL_NAME);
        modelAndView.addObject(GAME_SESSION_KEY, gameSession);
        modelAndView.addObject(ERROR_KEY, e.getMessage());
        return modelAndView;
    }

    /**
     * Handles requesting welcome page.
     * Shows game board.
     *
     * @return modelAndView containing game board model
     * */
    @Override
    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public ModelAndView showGamePage() {
        GameSession gameSession = gameService.getGameSession();

        ModelAndView modelAndView = new ModelAndView(GAME_MODEL_NAME);
        modelAndView.addObject(GAME_SESSION_KEY, gameSession);
        return modelAndView;
    }

    /**
     * Handles picking up stones from target pit by player.
     * When turn is end, shows game board with current state of game session.
     *
     * @param pitIndex pit index to pick stones from
     * @return modelAndView containing game board model
     * */
    @Override
    @RequestMapping(value = "/turn", method = RequestMethod.GET)
    public ModelAndView doTurn(@RequestParam int pitIndex) {
        pitIndexValidator.validate(pitIndex);
        gameService.doTurn(pitIndex);

        GameSession gameSession = gameService.getGameSession();
        ModelAndView modelAndView = new ModelAndView(GAME_MODEL_NAME);
        modelAndView.addObject(GAME_SESSION_KEY, gameSession);
        return modelAndView;
    }
}
