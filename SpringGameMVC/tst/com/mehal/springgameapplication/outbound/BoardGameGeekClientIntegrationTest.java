package com.mehal.springgameapplication.outbound;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;

import com.mehal.springgameapplication.entities.Game;

public class BoardGameGeekClientIntegrationTest {
    BoardGameGeekClient bggClient;

    @Before
    public void setUp() {
	bggClient = new BoardGameGeekClient();
    }

    @Test
    public void getScrabble() throws DocumentException {
	Game actual = bggClient.getGameForName("Scrabble");
	assertEquals("Scrabble", actual.getGameName());
	assertEquals("Number of players should match", 4, actual
		.getMaxPlayers().intValue());
	System.out.println(actual.getLargeIconUrl());
    }

    @Test
    public void getBSG() throws DocumentException {
	Game actual = bggClient.getGameForName("Battlestar Galactica");
	assertEquals("Battlestar Galactica", actual.getGameName());
	assertEquals("Number of players should match", 6, actual
		.getMaxPlayers().intValue());
	System.out.println(actual);
	System.out.println(actual.getLargeIconUrl());
    }

    @Test
    public void getNullGame() throws DocumentException {
	Game actual = bggClient.getGameForName("asdfasdfasdf");
	assertNull(actual);
    }
}
