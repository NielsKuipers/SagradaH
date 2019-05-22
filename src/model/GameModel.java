package model;

import queries.GameQueries;

import java.util.ArrayList;

public class GameModel {
	
	private GameQueries gameQueries;
	
	public GameModel(GameQueries gameQueries) {
		this.gameQueries = gameQueries;
	}
	
	
	
	////////////////////////////////////ENDSCREEN////////////////////////////////////////////////////////////////////////////
	public ArrayList<ArrayList<Object>> getPlayerScores() {
		return gameQueries.getPlayerScores();
	}
	
	public void setPlayerStatusFinished() {
		gameQueries.setPlayerStatusFinished();
	}
	
	
	////////////////////////////////////RONDEBORD/////////////////////////////////////////////////////////////////////////////
	
	// returnt rondebord dobbelstenen
	public ArrayList<ArrayList<Object>> getDicesOnRoundBoard(int round) {
		return gameQueries.getDicesOnRoundBoard(round);
	}

	// verwijdert dobbelsteen van rondebord
	public void removeDice(int diceID, String colorText) {
		gameQueries.removeDice(diceID, colorText);
		
	}
}
