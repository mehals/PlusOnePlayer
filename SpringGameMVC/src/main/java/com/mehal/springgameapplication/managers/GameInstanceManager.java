package com.mehal.springgameapplication.managers;

import java.util.List;

import org.joda.time.DateTime;

import com.mehal.springgameapplication.dao.GameDao;
import com.mehal.springgameapplication.entities.GameInstance;

public class GameInstanceManager {
    private GameDao gameDao;

    public GameInstanceManager(GameDao gameDao) {
	this.gameDao = gameDao;
    }

    public boolean createNewGameInstance(GameInstance gameInstance)
	    throws Exception {
	gameDao.addGameInstance(gameInstance);

	return true;
    }

    public List<GameInstance> getUpcomingGamesForDateRange(DateTime start,
	    DateTime end) throws Exception {
	return gameDao.getUpcomingGameInstances(start, end);
    }

    public List<GameInstance> getNextWeekGames() throws Exception {
	return gameDao.getUpcomingGameInstances(new DateTime(),
		new DateTime().plusDays(7));
    }
}
