package com.mehal.springgameapplication.managers;

import java.util.List;

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

    public Game retrieveOrCreateGame(String gameName) throws Exception {
	Game returnGame = retrieveGame(gameName);
	if (returnGame == null) {
	    return createGame(gameName);
	} else {
	    return returnGame;
	}
    }

    public Game retrieveGame(String gameName) throws Exception {
	return gameDao.getGame(gameName);
    }

    public Game createGame(String gameName) throws Exception {
	Game returnGame = bggClient.getGameForName(gameName);
	if (returnGame == null) {

	} else {
	    gameDao.addGame(returnGame);
	}
	return returnGame;
    }

    public List<Game> getAllGames() throws Exception {
	return gameDao.getAllGames();
    }

}
