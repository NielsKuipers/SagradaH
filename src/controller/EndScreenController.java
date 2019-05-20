package controller;

import view.EndScreen;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.GameModel;

public class EndScreenController {
	
	private EndScreen endScreen;
	private GameModel gameModel;
	
	public EndScreenController(Stage stage, DatabaseController dataController) {
		endScreen = new EndScreen(this);
		gameModel = new GameModel(dataController.getGameQueries());
		gameModel.setPlayerStatusFinished();
		stage.setScene(new Scene(endScreen));
		putPlayersOnBoard();
		makeEndScoreList();
		
	}
	
	// zet speler pion op scorebord
	private void putPlayersOnBoard() {
		ArrayList<ArrayList<Object>> result = gameModel.getPlayerScores();
		
		for(int i=0; i < result.size(); i++) {
			endScreen.addPlayer((int)result.get(i).get(2), (String) result.get(i).get(1));
		}
	}
	
	// maakt speler + score lijst
	private void makeEndScoreList() {
		ArrayList<ArrayList<Object>> result = gameModel.getPlayerScores();
		
		for(int i=0; i<result.size(); i++) {
			endScreen.addPlayerLabels((String) result.get(i).get(0), (int) result.get(i).get(2), (String) result.get(i).get(1));
		}
		endScreen.makeBottom();
	}
}
