package model;

import java.util.ArrayList;

import queries.PlayerQuery;

public class Player {

	private String playerName;
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
	
	public void setPlayerName(String name) {
		playerName = name;
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
			windowPattern.selectDifficulty();
			selectPlayerName();
	}
	
	public void selectPlayerName() {
		ArrayList<ArrayList<Object>> result = playerQuery.getPlayerName(idPlayer);
		windowPattern.setPlayerName("Naam: "+ String.valueOf(result.get(0).get(0)));
	}
}
