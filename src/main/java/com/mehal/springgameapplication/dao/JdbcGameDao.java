package com.mehal.springgameapplication.dao;

import java.util.List;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

import org.hibernate.HibernateException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mehal.springgameapplication.entities.Game;
import com.mehal.springgameapplication.entities.GameInstance;

public class JdbcGameDao implements GameDao {
    private String SQL_INSERT_GAME = "insert into game_instances (GAME_NAME, HOST, GAME_DATE) values (?, ?, ?)";
    private String SQL_INSERT_GAME_TYPE = "insert into games (GAME_NAME) values (?)";

    private JdbcTemplate jdbcTemplate;

    public JdbcGameDao(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addGameInstance(GameInstance game) {
	java.sql.Date sqlDate = new java.sql.Date(game.getGameDate().getMillis());
	jdbcTemplate.update(SQL_INSERT_GAME, game.getGameName(), game.getHost(), sqlDate);

    }

    @Override
    public void addGame(Game game) {
	jdbcTemplate.update(SQL_INSERT_GAME_TYPE, game.getGameName());

    }

    @Override
    public Game getGame(String gameName) throws HibernateException, NotSupportedException, SystemException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Game> getAllGames() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<GameInstance> getUpcomingGameInstances() throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

}
