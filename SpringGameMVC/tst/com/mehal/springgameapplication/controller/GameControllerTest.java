package com.mehal.springgameapplication.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.mehal.springgameapplication.GameService;
import com.mehal.springgameapplication.entities.Game;

public class GameControllerTest {
	private GameController gameController;
	private GameService mockGameService;
	private Model mockModel;

	@Before
	public void setup() {
		mockGameService = mock(GameService.class);
		mockModel = mock(Model.class);
		gameController = new GameController();
		gameController.gameService = mockGameService;
	}

	@Test
	public void testGameController() throws Exception {
		Game mockGame = new Game();
		mockGame.setGameName("Battlestar%20Galactica");
		when(mockGameService.getGame("Battlestar%20Galactica")).thenReturn(
				mockGame);

		gameController.getGameDetails("Battlestar%20Galactica", mockModel);
	}
}
