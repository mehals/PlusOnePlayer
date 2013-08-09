package com.mehal.springgameapplication.outbound;

import java.util.List;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import com.mehal.springgameapplication.entities.Game;

public class BoardGameGeekClient {
    private static final String bggSearchUrl = "http://www.boardgamegeek.com/xmlapi2/search?query=%s&exact=1&type=boardgame";
    private static final String bggGameDetailUrl = "http://www.boardgamegeek.com/xmlapi2/thing?id=";
    private SAXReader reader;

    BoardGameGeekClient() {
	reader = new SAXReader();
    }

    public Game getGameForName(String gameName) throws DocumentException {
	String url = String.format(bggSearchUrl, gameName);

	Document document = reader.read(url);

	@SuppressWarnings("unchecked")
	List<Node> gameIds = document.selectNodes("/items/item/@id");

	if (gameIds.size() == 0) {
	    return null;
	}

	String getGameUrl = bggGameDetailUrl;

	for (Node id : gameIds) {
	    getGameUrl += id.getStringValue() + ",";
	}

	getGameUrl += "&stats=1";

	document = reader.read(getGameUrl);
	document.normalize();
	@SuppressWarnings("unchecked")
	List<Node> gameDetails = document.selectNodes("/items/item");
	int maxGameCount = -1;
	Node maxGameNode = null;

	for (Node gameDetail : gameDetails) {
	    int gameCount = Integer.parseInt(gameDetail.selectSingleNode(
		    "statistics/ratings/owned").valueOf("@value"));
	    if (maxGameCount < gameCount) {
		maxGameNode = gameDetail;
		maxGameCount = gameCount;
	    }
	}

	System.out.println();

	Game returnGame = new Game();
	returnGame.setGameName(gameName);
	returnGame.setMaxPlayers(Integer.parseInt(maxGameNode.selectSingleNode(
		"maxplayers").valueOf("@value")));
	returnGame.setMinPlayers(Integer.parseInt(maxGameNode.selectSingleNode(
		"minplayers").valueOf("@value")));
	returnGame.setLargeIconUrl(maxGameNode.selectSingleNode("image")
		.getText());
	returnGame.setSmallIconUrl(maxGameNode.selectSingleNode("thumbnail")
		.getText());
	returnGame.setRuntime(Integer.parseInt(maxGameNode.selectSingleNode(
		"playingtime").valueOf("@value")));

	return returnGame;
    }
}
