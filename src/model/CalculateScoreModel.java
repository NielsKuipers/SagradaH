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
	
	public ArrayList<ArrayList<Object>> getPlayerFavortokens(int playerID) {
		return scoreQueries.getPlayerFavortokens(playerID);
	}
	
	public ArrayList<ArrayList<Object>> getEmptyFields(int playerID) {
		return scoreQueries.getEmptyFields(playerID);
	}
	
	public ArrayList<ArrayList<Object>> getAllColorsEyes(int playerID) {
		return scoreQueries.getAllColorsEyes(playerID);
	}

	public ArrayList<ArrayList<Object>> getPlayerDiceColorsPos(int playerID) {
		return scoreQueries.getPlayerDiceColorsPos(playerID);
	}

	public ArrayList<ArrayList<Object>> getPlayerDiceColorsPosX(int playerID) {
		return scoreQueries.getPlayerDiceColorsPosX(playerID);
	}

	public ArrayList<ArrayList<Object>> getPlayerDiceEyesPos(int playerID){
		return scoreQueries.getPlayerDiceEyesPos(playerID);
	}

	public ArrayList<ArrayList<Object>> getPlayerDiceEyesPosX(int playerID){
		return scoreQueries.getPlayerDiceEyesPosX(playerID);
	}

	public ArrayList<ArrayList<Object>> getPublicCards(){
		return scoreQueries.getPublicCards();
	}
}
