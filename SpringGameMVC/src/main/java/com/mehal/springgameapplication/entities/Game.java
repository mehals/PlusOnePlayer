package com.mehal.springgameapplication.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GAMES")
public class Game {
    @Id
    @Column(name = "GAME_NAME", nullable = false)
    private String gameName;

    @Column(name = "MAX_PLAYERS", nullable = false)
    private int maxPlayers;

    public String getGameName() {
	return gameName;
    }

    public int getMaxPlayers() {
	return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
	this.maxPlayers = maxPlayers;
    }

    public void setGameName(String gameName) {
	this.gameName = gameName;
    }
}
