package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.GameModel;
import view.EndScreen;

import java.util.ArrayList;

public class EndScreenController {
	
	private EndScreen endScreen;
	private GameModel gameModel;
	
	public EndScreenController(Stage stage, DatabaseController dataController) {
		endScreen = new EndScreen();
		gameModel = new GameModel(dataController.getGameQueries());
		gameModel.setPlayerStatusFinished();
		stage.setScene(new Scene(endScreen));
		putPlayersOnBoard();
		makeEndScoreList();
		
	}
	
	// zet speler pion op scorebord
	private void putPlayersOnBoard() {
		ArrayList<ArrayList<Object>> result = gameModel.getPlayerScores();

		for (ArrayList<Object> objects : result) {
			endScreen.addPlayer((int) objects.get(2), (String) objects.get(1));
		}
	}
	
	// maakt speler + score lijst
	private void makeEndScoreList() {
		ArrayList<ArrayList<Object>> result = gameModel.getPlayerScores();

		for (ArrayList<Object> objects : result) {
			endScreen.addPlayerLabels((String) objects.get(0), (int) objects.get(2), (String) objects.get(1));
		}
		endScreen.makeBottom();
	}
}
