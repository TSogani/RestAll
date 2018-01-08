package com.ji.beans;

import java.util.Date;

import javax.ws.rs.MatrixParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

public class Match {
	@PathParam("tournament")
	private String tournament;
	@PathParam("teamOne")
	private String teamOne;
	@PathParam("teamTwo")
	private String teamTwo;
	@MatrixParam("innings")
	private String innings;
	@QueryParam("over")
	private String over;
	@MatrixParam("matchDate")
	private Date matchDate;
	@QueryParam("playerName")
	private String playerName;
	
	
	public String getTournament() {
		return tournament;
	}
	public void setTournament(String tournament) {
		this.tournament = tournament;
	}
	
	public String getTeamOne() {
		return teamOne;
	}
	public void setTeamOne(String teamOne) {
		this.teamOne = teamOne;
	}
	
	public String getTeamTwo() {
		return teamTwo;
	}
	public void setTeamTwo(String teamTwo) {
		this.teamTwo = teamTwo;
	}
	
	public String getInnings() {
		return innings;
	}
	public void setInnings(String innings) {
		this.innings = innings;
	}
	
	public String getOver() {
		return over;
	}
	public void setOver(String over) {
		this.over = over;
	}
	
	public Date getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	@Override
	public String toString() {
		return "Match [tournament=" + tournament + ", teamOne=" + teamOne + ", teamTwo=" + teamTwo + ", innings="
				+ innings + ", over=" + over + ", matchDate=" + matchDate + ", playerName=" + playerName + "]";
	}
}
