package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import main.GUI;
import model.Game;
import view.EndScreen;

import java.util.ArrayList;

public class EndScreenController {
	
	private EndScreen endScreen;
	private Game gameModel;
	private CalculateScoreController calcScore; // nodig
	
	public EndScreenController(DatabaseController dataController, GameController gameController, CalculateScoreController calcScore, GUI gui) {
		endScreen = new EndScreen(gui);
		gameModel = gameController.getGameModel();
		this.calcScore = calcScore;
		putPlayersOnBoard();
		makeEndScoreList();
	} 
	
	public EndScreen getEndScreen() {
		enterEndScreen();
		return endScreen;
	}
	
	/** 
	 * 
	 */
	public void enterEndScreen() {
		endScreen.clearPlayers();
		gameModel.setPlayerStatusFinished();
		calculateEndScores();
		putPlayersOnBoard();
		makeEndScoreList();
	}
	
	
	/**
	 *  Calculate scores and put in database
	 */
	private void calculateEndScores() {
		ArrayList<ArrayList<Object>> result = gameModel.getPlayerIds();
		int points;
		
		for(ArrayList<Object> objects: result) {
			points = calcScore.getClientScore((int) objects.get(0)); 
			gameModel.setPoints(points, (int) (objects.get(0)));
		}
	}

	
	/**
	 * put player pointer on scoreboard
	 */
	private void putPlayersOnBoard() {
		ArrayList<ArrayList<Object>> result = gameModel.getPlayerScores();

		for (ArrayList<Object> objects : result) {
			endScreen.addPlayer((int) objects.get(2), (String) objects.get(1));
		}
	}
	
	
	/**
	 * make player + score list
	 */
	private void makeEndScoreList() {
		ArrayList<ArrayList<Object>> result = gameModel.getPlayerScores();

		for (ArrayList<Object> objects : result) {
			endScreen.addPlayerLabels((String) objects.get(0), (int) objects.get(2), (String) objects.get(1));
		}
		endScreen.makeBottom();
	}
}
