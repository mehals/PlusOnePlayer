package com.mehal.springgameapplication.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "GAME_INSTANCES")
public class GameInstance {
    @Id
    @GeneratedValue
    @Column(name = "GAME_INSTANCE_ID")
    private String gameInstanceId;

    @Column(name = "GAME_NAME")
    private String gameName;

    @Column(name = "HOST")
    private String host;

    @Column(name = "GAME_DATE")
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime gameDate;

    @Column(name = "GAME_LOCATION")
    private String gameLocation;

    public String getGameName() {
	return gameName;
    }

    public void setGameName(String gameType) {
	this.gameName = gameType;
    }

    public String getHost() {
	return host;
    }

    public void setHost(String host) {
	this.host = host;
    }

    public DateTime getGameDate() {
	return gameDate;
    }

    public void setGameDate(DateTime gameDate) {
	this.gameDate = gameDate;
    }

    public String getGameInstanceId() {
	return gameInstanceId;
    }

    public void setGameInstanceId(String gameInstanceId) {
	this.gameInstanceId = gameInstanceId;
    }

    public String getGameLocation() {
	return gameLocation;
    }

    public void setGameLocation(String gameLocation) {
	this.gameLocation = gameLocation;
    }
}
