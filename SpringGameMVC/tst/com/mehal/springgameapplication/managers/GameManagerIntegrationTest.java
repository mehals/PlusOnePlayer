package com.mehal.springgameapplication.managers;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class GameManagerIntegrationTest {

    @Test
    public void testRetrieveGame_existingGame() {
	System.out.println(System.getProperty("user.dir"));
	ApplicationContext context = new FileSystemXmlApplicationContext(
		"src/main/webapp/WEB-INF/spring/appServlet/games-servlet.xml");
	context.getBean("gameDriver");

    }

}
