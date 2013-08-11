package com.mehal.springgameapplication;

import java.util.List;

import org.joda.time.DateTime;

import com.mehal.springgameapplication.entities.Game;
import com.mehal.springgameapplication.entities.GameInstance;
import com.mehal.springgameapplication.managers.GameInstanceManager;
import com.mehal.springgameapplication.managers.GameManager;

public class GameService {
    private GameManager gameManager;
    private GameInstanceManager gameInstanceManager;

    public GameService(GameManager gameManager,
	    GameInstanceManager gameInstanceManager) {
	this.gameManager = gameManager;
	this.gameInstanceManager = gameInstanceManager;
    }

    public Game getGame(String gameName) throws Exception {
	return gameManager.retrieveGame(gameName);
    }

    public Game createNewGame(String gameName) throws Exception {
	return gameManager.createGame(gameName);
    }

    public List<Game> getAllGames() throws Exception {
	return gameManager.getAllGames();
    }

    public GameInstance createNewGameInstance(String gameName,
	    DateTime gameDate, String host, String gameLocation)
	    throws Exception {
	GameInstance gameInstance = new GameInstance();
	gameInstance.setGameName(gameName);
	gameInstance.setGameDate(gameDate);
	gameInstance.setHost(host);
	gameInstance.setGameLocation(gameLocation);

	gameInstanceManager.createNewGameInstance(gameInstance);

	return gameInstance;
    }

    public GameInstance createNewGameInstance(GameInstance gameInstance)
	    throws Exception {
	gameInstanceManager.createNewGameInstance(gameInstance);
	return gameInstance;
    }

    public List<GameInstance> getUpcomingGames() throws Exception {
	return gameInstanceManager.getNextWeekGames();
    }

}
