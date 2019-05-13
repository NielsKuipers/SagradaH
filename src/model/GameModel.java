package model;

import java.util.ArrayList;

import queries.GameQueries;
import queries.InviteHandleQueries;

public class GameModel {
	
	private GameQueries gameQueries;
	
	public GameModel() {
		
	}
	
	// returnt rondebord dobbelstenen
	public ArrayList<ArrayList<Object>> getDicesOnRoundBoard() {
		gameQueries = new GameQueries();
		return gameQueries.getDicesOnRoundBoard();
	}
}
