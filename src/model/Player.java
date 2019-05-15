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
	
	WindowPattern getWindowPatternPlayer() {
		return windowPattern;
	}
	
	public void givePlayerWindowPattern(WindowPattern windowPattern) {
		this.windowPattern = windowPattern;
	}
	
	void setPlayerId(int id) {
		idPlayer = id;
	}
	
	public int getPlayerId() {
		return idPlayer;
	}
	
	void selectWindow() {
		ArrayList<ArrayList<Object>> result = playerQuery.getWindowId(idPlayer);
			windowPattern.setId(Integer.valueOf(String.valueOf(result.get(0).get(0))));
			windowPattern.selectAllFields();
			windowPattern.selectAllDicesOnField(idPlayer);
			windowPattern.selectDifficulty();
			selectPlayerName();
			selectPlayerScore();
	}
	
	private void selectPlayerName() {
		ArrayList<ArrayList<Object>> result = playerQuery.getPlayerName(idPlayer);
		windowPattern.setPlayerName("Naam: "+ String.valueOf(result.get(0).get(0)));
	}
	
	private void selectPlayerScore() {
		ArrayList<ArrayList<Object>> result = playerQuery.getPlayerScore(idPlayer);
		windowPattern.setPlayerScore("Score: " + String.valueOf(result.get(0).get(0)));
	}
	
	public void updateWindowId(int windowId) {
		playerQuery.updateWindowID(idPlayer, windowId);
	}
	

}
