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
	
	public void selectWindow() {
		ArrayList<ArrayList<Object>> result = playerQuery.getWindowId(idPlayer);
			windowPattern.setId(Integer.valueOf(String.valueOf(result.get(0).get(0))));
			windowPattern.selectAllFields();
			windowPattern.selectAllDicesOnField(idPlayer);
			windowPattern.selectDifficulty();
			selectPlayerName();
			selectPlayerScore();
	}
	
	public void selectPlayerName() {
		ArrayList<ArrayList<Object>> result = playerQuery.getPlayerName(idPlayer);
		windowPattern.setPlayerName("Naam: "+ String.valueOf(result.get(0).get(0)));
	}
	
	public void selectPlayerScore() {
		ArrayList<ArrayList<Object>> result = playerQuery.getPlayerScore(idPlayer);
		windowPattern.setPlayerScore("Score: " + String.valueOf(result.get(0).get(0)));
	}
	
	public void updateWindowId(int windowId) {
		playerQuery.updateWindowID(idPlayer, windowId);
	}
	
	public boolean selectCurrentPlayer() {
		ArrayList<ArrayList<Object>> result = playerQuery.getIsCurrentPlayer(idPlayer);
		int currentPlayer = Integer.valueOf(String.valueOf(result.get(0).get(0)));
		if (currentPlayer == 1) {
			return true;
		}
		return false;
	}
	
	public int selectSqnr() {
		ArrayList<ArrayList<Object>> result = playerQuery.getSqnrPlayer(idPlayer);
		return Integer.valueOf(String.valueOf(result.get(0).get(0)));
	}
	
	public void updateSqnr(int sqnr) {
		playerQuery.updateSqnrPlayer(idPlayer, sqnr);
	}
	
	public void updateQurrentPlayer(int isQurrent) {
		playerQuery.updateIsCurrentPlayer(idPlayer, isQurrent);
	}

}
