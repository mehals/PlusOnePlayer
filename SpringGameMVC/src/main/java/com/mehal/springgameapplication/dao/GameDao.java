package com.mehal.springgameapplication.dao;

import java.util.List;

import javax.transaction.*;

import org.hibernate.HibernateException;
import org.joda.time.DateTime;

import com.mehal.springgameapplication.entities.Game;
import com.mehal.springgameapplication.entities.GameInstance;

public interface GameDao {
    public void addGameInstance(GameInstance game) throws HibernateException,
	    NotSupportedException, SystemException, SecurityException,
	    IllegalStateException, RollbackException, HeuristicMixedException,
	    HeuristicRollbackException;

    public void addGame(Game game) throws HibernateException,
	    NotSupportedException, SystemException, SecurityException,
	    IllegalStateException, RollbackException, HeuristicMixedException,
	    HeuristicRollbackException;

    public Game getGame(String gameName) throws HibernateException,
	    NotSupportedException, SystemException;

    public List<Game> getAllGames() throws HibernateException,
	    NotSupportedException, SystemException;

    List<GameInstance> getUpcomingGameInstances(DateTime start, DateTime end)
	    throws Exception;
}
