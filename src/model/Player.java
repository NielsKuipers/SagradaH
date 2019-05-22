package model;

import java.util.ArrayList;

import queries.PlayerQuery;

public class Player {

	private int idPlayer;
	private WindowPattern windowPattern;
	
	private PlayerQuery playerQuery;
	
	public Player(PlayerQuery playerQuery) {
		this.playerQuery = playerQuery;
	}
	
	public WindowPattern getWindowPatternPlayer() {
		return windowPattern;
	}
	
	public void givePlayerWindowPattern(WindowPattern windowPattern) {
		this.windowPattern = windowPattern;
	}
	
	public void setPlayerId(int id) {
		idPlayer = id;
	}
	
	public int getPlayerId() {
		return idPlayer;
	}
	
	//get all the information about a windowpattern and add it to the model
	public void selectWindow(int idGame) {
		ArrayList<ArrayList<Object>> result = playerQuery.getWindowId(idPlayer);
			windowPattern.setId(Integer.valueOf(String.valueOf(result.get(0).get(0))));
			windowPattern.selectAllFields();
			windowPattern.selectAllDicesOnField(idPlayer, idGame);
			windowPattern.selectDifficulty();
			selectPlayerName();
			selectPlayerScore();
	}
	
	//get player name
	public void selectPlayerName() {
		ArrayList<ArrayList<Object>> result = playerQuery.getPlayerName(idPlayer);
		windowPattern.setPlayerName("Naam: "+ String.valueOf(result.get(0).get(0)));
	}
	
	//get player score
	public void selectPlayerScore() {
		ArrayList<ArrayList<Object>> result = playerQuery.getPlayerScore(idPlayer);
		windowPattern.setPlayerScore("Score: " + String.valueOf(result.get(0).get(0)));
	}
	
	//give the window of the player the right id
	public void updateWindowId(int windowId) {
		playerQuery.updateWindowID(idPlayer, windowId);
	}
	
	//check if player has turn
	public boolean selectCurrentPlayer() {
		ArrayList<ArrayList<Object>> result = playerQuery.getIsCurrentPlayer(idPlayer);
		int currentPlayer = Integer.valueOf(String.valueOf(result.get(0).get(0)));
		if (currentPlayer == 1) {
			return true;
		}
		return false;
	}
	
	//get seqnr of player
	public int selectSqnr() {
		ArrayList<ArrayList<Object>> result = playerQuery.getSqnrPlayer(idPlayer);
		return Integer.valueOf(String.valueOf(result.get(0).get(0)));
	}
	
	//update seqnr
	public void updateSqnr(int sqnr) {
		playerQuery.updateSqnrPlayer(idPlayer, sqnr);
	}
	
	//update currentplayer in game table
	public void updateQurrentPlayer(int isQurrent) {
		playerQuery.updateIsCurrentPlayer(idPlayer, isQurrent);
	}
	
	//add a dice to playerframefield
	public void setDiceOnWindowPattern(int posX, int posY, int dienumber, String diecolor) {
		playerQuery.updateDiceOnWindowPattern(idPlayer, posX, posY, dienumber, diecolor);
	}
	
	//remove a dice from playerframefield
	public void removeDiceOnWindowPattern(int dienumber, String diecolor) {
		playerQuery.removeDiceOnWindowPattern(idPlayer, dienumber, diecolor);
	}

}
