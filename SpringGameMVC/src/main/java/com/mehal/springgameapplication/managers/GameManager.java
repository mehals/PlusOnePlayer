package com.mehal.springgameapplication.managers;

import com.mehal.springgameapplication.dao.GameDao;
import com.mehal.springgameapplication.entities.Game;
import com.mehal.springgameapplication.outbound.BoardGameGeekClient;

public class GameManager {
    private BoardGameGeekClient bggClient;
    private GameDao gameDao;

    GameManager(BoardGameGeekClient bggClient, GameDao gameDao) {
	this.bggClient = bggClient;
	this.gameDao = gameDao;
    }

    public Game retrieveGame(String gameName) throws Exception {
	Game returnGame = gameDao.getGame(gameName);

	if (returnGame == null) {
	    returnGame = bggClient.getGameForName(gameName);
	    if (returnGame == null) {

	    } else {
		gameDao.addGame(returnGame);
	    }
	}

	return returnGame;
    }
}
