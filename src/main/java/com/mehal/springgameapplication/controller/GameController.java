package com.mehal.springgameapplication.controller;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mehal.springgameapplication.GameService;
import com.mehal.springgameapplication.entities.Game;
import com.mehal.springgameapplication.entities.GameInstance;

@Controller
@RequestMapping({ "/" })
public class GameController {
    @Autowired
    GameService gameService;

    @RequestMapping({ "/", "/home" })
    public String showGameList(Map<String, Object> model) throws Exception {
	List<Game> allGames = gameService.getAllGames();

	model.put("games", allGames);
	Random r = new Random();

	model.put("randomGameName", allGames.get(r.nextInt(allGames.size())).getGameName());
	return "home";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(method = RequestMethod.GET, value = "/games")
    public String getGameDetails(@RequestParam("gameName") String gameName, Model model) throws Exception {
	String url = "http://www.boardgamegeek.com/xmlapi2/search?query=" + gameName + "&exact=1&type=boardgame";

	SAXReader reader = new SAXReader();
	Document document = reader.read(url);

	List<Node> gameIds = document.selectNodes("/items/item/@id");

	String getGameUrl = "http://www.boardgamegeek.com/xmlapi2/thing?id=";

	for (Node id : gameIds) {
	    getGameUrl += id.getStringValue() + ",";   
	}

	getGameUrl += "&stats=1";
   
	document = reader.read(getGameUrl);
	document.normalize();
	List<Node> gameDetails = document.selectNodes("/items/item");
	int maxGameCount = -1;
	Node maxGameNode = null;

	for (Node gameDetail : gameDetails) {
	    int gameCount = Integer.parseInt(gameDetail.selectSingleNode("statistics/ratings/owned").valueOf("@value"));
	    if (maxGameCount < gameCount) {
		maxGameNode = gameDetail;
		maxGameCount = gameCount;
	    }
	}

	if (maxGameNode != null) {
	    model.addAttribute("gameImageUrl", maxGameNode.selectSingleNode("thumbnail").getText());
	} else {
	    model.addAttribute("missingGameName", gameName);
	    return "gameInfo/gameNotFound";
	}

	System.out.println();

	Game game = gameService.getGame(gameName);
	if (game == null) {
	    game = gameService.createNewGame(gameName,
		    Integer.parseInt(maxGameNode.selectSingleNode("maxplayers").valueOf("@value")));
	}
	model.addAttribute("game", game);

	return "gameInfo/gameDetail";
    }

    @RequestMapping(method = RequestMethod.GET, params = "newGame")
    public String createGameInstance(Model model) throws Exception {
	model.addAttribute(gameService.getAllGames());
	model.addAttribute(new GameInstance());
	return "gameInstances/createGameInstance";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String gameInstanceSubmission(GameInstance gameInstance, BindingResult bindingResult) throws Exception {
	if (gameInstance.getGameDate() == null) {
	    gameInstance.setGameDate(new DateTime().plusDays(7));
	}
	gameService.createNewGameInstance(gameInstance);
	return "redirect:/home";
    }

    @RequestMapping({ "/map" })
    public String getMap(Map<String, Object> model) throws Exception {
	model.put("upcomingGames", gameService.getUpcomingGames());

	return "map";
    }
}
