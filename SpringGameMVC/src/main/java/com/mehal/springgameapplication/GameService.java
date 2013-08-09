package com.mehal.springgameapplication;

import java.util.List;

import org.joda.time.DateTime;

import com.mehal.springgameapplication.dao.GameDao;
import com.mehal.springgameapplication.entities.Game;
import com.mehal.springgameapplication.entities.GameInstance;

public class GameService {
    private GameDao gameDao;

    public GameService() {
	gameDao = null;
    }

    public GameService(GameDao gameDao) {
	this.gameDao = gameDao;
    }

    public Game getGame(String gameName) {
	try {
	    return gameDao.getGame(gameName);
	} catch (Exception exception) {
	    return null;
	}
    }

    public Game createNewGame(String gameName, Integer maxPlayers)
	    throws Exception {
	Game game = new Game();
	game.setGameName(gameName);
	game.setMaxPlayers(maxPlayers);

	if (gameDao.getGame(gameName) == null) {
	    gameDao.addGame(game);
	}
	return game;
    }

    public List<Game> getAllGames() throws Exception {
	return gameDao.getAllGames();
    }

    public GameInstance createNewGameInstance(String gameName,
	    DateTime gameDate, String host, String gameLocation)
	    throws Exception {
	GameInstance gameInstance = new GameInstance();
	gameInstance.setGameName(gameName);
	gameInstance.setGameDate(gameDate);
	gameInstance.setHost(host);
	gameInstance.setGameLocation(gameLocation);
	gameDao.addGameInstance(gameInstance);

	return gameInstance;
    }

    public GameInstance createNewGameInstance(GameInstance gameInstance)
	    throws Exception {
	gameDao.addGameInstance(gameInstance);
	return gameInstance;
    }

    public List<GameInstance> getUpcomingGames() throws Exception {
	return gameDao.getUpcomingGameInstances();
    }

}
