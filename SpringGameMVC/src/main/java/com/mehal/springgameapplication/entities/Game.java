package com.mehal.springgameapplication.entities;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
@Table(name = "GAMES")
public class Game {
    @Id
    @Column(name = "GAME_NAME", nullable = false)
    private String gameName;

    @Column(name = "MAX_PLAYERS", nullable = false)
    private Integer maxPlayers;

    @Column(name = "MIN_PLAYERS", nullable = false)
    private Integer minPlayers;

    @Column(name = "IDEAL_PLAYERS", nullable = true)
    private Integer idealPlayers;

    @Column(name = "SMALL_ICON_URL", nullable = true)
    private String smallIconUrl;

    @Column(name = "LARGE_ICON_URL", nullable = true)
    private String largeIconUrl;

    @Column(name = "RUNTIME_MINUTES", nullable = true)
    private Integer runtime;

    public String getGameName() {
	return gameName;
    }

    public Integer getMaxPlayers() {
	return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
	this.maxPlayers = maxPlayers;
    }

    public void setGameName(String gameName) {
	this.gameName = gameName;
    }

    public Integer getMinPlayers() {
	return minPlayers;
    }

    public void setMinPlayers(Integer minPlayers) {
	this.minPlayers = minPlayers;
    }

    public Integer getIdealPlayers() {
	return idealPlayers;
    }

    public void setIdealPlayers(Integer idealPlayers) {
	this.idealPlayers = idealPlayers;
    }

    public String getSmallIconUrl() {
	return smallIconUrl;
    }

    public void setSmallIconUrl(String smallIconUrl) {
	this.smallIconUrl = smallIconUrl;
    }

    public String getLargeIconUrl() {
	return largeIconUrl;
    }

    public void setLargeIconUrl(String largeIconUrl) {
	this.largeIconUrl = largeIconUrl;
    }

    public Integer getRuntime() {
	return runtime;
    }

    public void setRuntime(Integer runtime) {
	this.runtime = runtime;
    }

    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (obj == this) {
	    return true;
	}
	if (obj.getClass() != this.getClass()) {
	    return false;
	}

	Game other = (Game) obj;
	return new EqualsBuilder().append(gameName, other.gameName).isEquals();
    }
}
