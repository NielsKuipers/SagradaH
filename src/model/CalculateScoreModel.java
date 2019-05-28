package model;

import java.util.ArrayList;

import queries.ScoreQueries;

public class CalculateScoreModel {

	private ScoreQueries scoreQueries;
	
	public CalculateScoreModel(ScoreQueries scoreQueries) {
		this.scoreQueries = scoreQueries;
	}
	
	public ArrayList<ArrayList<Object>> getPlayerDiceNumbers(int playerID) {
		return scoreQueries.getPlayerDiceNumbers(playerID);
	}
	
	public ArrayList<ArrayList<Object>> getPlayerDiceColors(int playerID) {
		return scoreQueries.getPlayerDiceColors(playerID);
	}
}
