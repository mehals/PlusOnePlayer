package com.mehal.springgameapplication;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.mehal.springgameapplication.entities.Game;
import com.mehal.springgameapplication.entities.GameInstance;
import com.mehal.springgameapplication.managers.GameInstanceManager;
import com.mehal.springgameapplication.managers.GameManager;

public class GameServiceTest {

    private static final String GAME_NAME = "GAME_NAME";
    private static final DateTime GAME_TIME = new DateTime();
    private static final String HOST_NAME = "HOST NAME";
    private static final String GAME_LOCATION = "LOCATION";
    GameService gameService;
    GameManager mockGameManager;
    GameInstanceManager mockGameInstanceManager;
    Game game;
    GameInstance gameInstance_1, gameInstance_2;

    @Before
    public void setUp() throws Exception {
	mockGameManager = mock(GameManager.class);
	mockGameInstanceManager = mock(GameInstanceManager.class);
	gameService = new GameService(mockGameManager, mockGameInstanceManager);
	game = new Game();
	game.setGameName(GAME_NAME);
	gameInstance_1 = new GameInstance();
	gameInstance_1.setGameDate(GAME_TIME);
	gameInstance_1.setGameLocation(GAME_LOCATION);
	gameInstance_1.setGameName(GAME_NAME);
	gameInstance_1.setHost(HOST_NAME);
	gameInstance_2 = new GameInstance();
	when(mockGameManager.retrieveGame(GAME_NAME)).thenReturn(game);
	when(mockGameInstanceManager.getNextWeekGames()).thenReturn(
		ImmutableList.of(gameInstance_1, gameInstance_2));
	when(mockGameManager.getAllGames()).thenReturn(ImmutableList.of(game));
	when(mockGameManager.createGame(GAME_NAME)).thenReturn(game);
    }

    @Test
    public void test_get_game() throws Exception {
	assertEquals("Game should be retrieved", game,
		gameService.getGame(GAME_NAME));
    }

    @Test
    public void test_create_game() throws Exception {
	assertEquals("Game should be created", game,
		gameService.createNewGame(GAME_NAME));

	verify(mockGameManager, times(1)).createGame(GAME_NAME);
    }

    @Test
    public void test_get_all_games() throws Exception {
	assertEquals("Game list should be created", ImmutableList.of(game),
		gameService.getAllGames());
    }

    @Test
    public void test_create_new_game_instance() throws Exception {
	GameInstance returnedInstance = gameService.createNewGameInstance(
		GAME_NAME, GAME_TIME, HOST_NAME, GAME_LOCATION);
	assertEquals("Returned game should be what was expected",
		gameInstance_1, returnedInstance);
	verify(mockGameInstanceManager, times(1)).createNewGameInstance(
		any(GameInstance.class));

	returnedInstance = gameService.createNewGameInstance(gameInstance_1);
	assertEquals("Returned game should be what was expected",
		gameInstance_1, returnedInstance);
	verify(mockGameInstanceManager, times(2)).createNewGameInstance(
		any(GameInstance.class));
    }

    @Test
    public void test_get_upcoming_games() throws Exception {
	assertEquals("Next week games should be expected",
		ImmutableList.of(gameInstance_1, gameInstance_2),
		gameService.getUpcomingGames());
	verify(mockGameInstanceManager, times(1)).getNextWeekGames();
    }

}
