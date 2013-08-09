package com.mehal.springgameapplication.managers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.mehal.springgameapplication.dao.GameDao;
import com.mehal.springgameapplication.entities.Game;
import com.mehal.springgameapplication.outbound.BoardGameGeekClient;

public class GameManagerTest {

    private static final String MOCK_GAME_NAME = "GAME_NAME";
    private BoardGameGeekClient mockBGGClient;
    private GameDao mockGameDao;
    private Game exampleGame;

    GameManager gameManager;

    @Before
    public void setUp() {
	mockBGGClient = mock(BoardGameGeekClient.class);
	mockGameDao = mock(GameDao.class);
	gameManager = new GameManager(mockBGGClient, mockGameDao);
	exampleGame = new Game();
	exampleGame.setGameName(MOCK_GAME_NAME);
    }

    @Test
    public void retrieveGame_gameInDao() throws Exception {
	when(mockGameDao.getGame(MOCK_GAME_NAME)).thenReturn(exampleGame);

	Game returnedGame = gameManager.retrieveGame(MOCK_GAME_NAME);
	assertEquals("Game should match", exampleGame, returnedGame);

	verify(mockGameDao, times(1)).getGame(MOCK_GAME_NAME);
	verify(mockGameDao, times(0)).addGame(any(Game.class));
	verifyZeroInteractions(mockBGGClient);
    }

    @Test
    public void retrieveGame_gameNotInDao() throws Exception {
	when(mockGameDao.getGame(MOCK_GAME_NAME)).thenReturn(null);
	when(mockBGGClient.getGameForName(MOCK_GAME_NAME)).thenReturn(
		exampleGame);

	Game returnedGame = gameManager.retrieveGame(MOCK_GAME_NAME);
	assertEquals("Game should match", exampleGame, returnedGame);

	verify(mockGameDao, times(1)).getGame(MOCK_GAME_NAME);
	verify(mockBGGClient, times(1)).getGameForName(MOCK_GAME_NAME);
	verify(mockGameDao, times(1)).addGame(any(Game.class));

    }

    @Test
    public void retrieveGame_noGameExists() throws Exception {
	when(mockGameDao.getGame(MOCK_GAME_NAME)).thenReturn(null);
	when(mockBGGClient.getGameForName(MOCK_GAME_NAME)).thenReturn(null);

	Game returnedGame = gameManager.retrieveGame(MOCK_GAME_NAME);
	assertNull("Game should be null", returnedGame);

	verify(mockGameDao, times(1)).getGame(MOCK_GAME_NAME);
	verify(mockBGGClient, times(1)).getGameForName(MOCK_GAME_NAME);
	verify(mockGameDao, times(0)).addGame(any(Game.class));
    }
}
