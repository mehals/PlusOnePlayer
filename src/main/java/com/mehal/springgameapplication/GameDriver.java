package com.mehal.springgameapplication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Mehal Shah
 * 
 */
public class GameDriver {
    private GameService gameService;

    public GameDriver(GameService gameService) {
	this.gameService = gameService;
    }

    public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/mehal/springgameapplication/gametest.xml");
		GameDriver instance = (GameDriver) context.getBean("gameDriver");
		instance.doStuff();
    }

    public void doStuff() throws Exception {
	System.out.println(gameService.getUpcomingGames());
    }

}
