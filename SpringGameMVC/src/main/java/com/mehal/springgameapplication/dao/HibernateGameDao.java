package com.mehal.springgameapplication.dao;

import java.util.List;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mehal.springgameapplication.entities.Game;
import com.mehal.springgameapplication.entities.GameInstance;

@Repository
@Transactional
public class HibernateGameDao implements GameDao {
    private SessionFactory sessionFactory;

    @Autowired
    public HibernateGameDao(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    private Session currentSession() throws NotSupportedException,
	    SystemException {
	return sessionFactory.getCurrentSession();
    }

    @Override
    public void addGame(Game game) throws HibernateException,
	    NotSupportedException, SystemException {
	currentSession().save(game);
    }

    @Override
    public void addGameInstance(GameInstance gameInstance)
	    throws HibernateException, NotSupportedException, SystemException {
	currentSession().save(gameInstance);
    }

    @Override
    public Game getGame(String gameName) throws HibernateException,
	    NotSupportedException, SystemException {
	return (Game) currentSession().get(Game.class, gameName);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Game> getAllGames() throws HibernateException,
	    NotSupportedException, SystemException {
	List<Game> games = currentSession().createQuery("from Game").list();

	return games;
    }

    @Override
    public List<GameInstance> getUpcomingGameInstances(DateTime start,
	    DateTime end) throws Exception {
	Criteria criteria = currentSession().createCriteria(GameInstance.class)
		.add(Restrictions.between("gameDate", start, end));

	@SuppressWarnings("unchecked")
	List<GameInstance> gameInstances = criteria.list();

	return gameInstances;
    }
}
