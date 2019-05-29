package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Game;
import view.EndScreen;

import java.util.ArrayList;

public class EndScreenController {
	
	private EndScreen endScreen;
	private Game gameModel;
	
	public EndScreenController(Stage stage, DatabaseController dataController, GameController gameController) {
		endScreen = new EndScreen();
		gameModel = gameController.getGameModel();
		gameModel.setPlayerStatusFinished();
		stage.setScene(new Scene(endScreen));
		putPlayersOnBoard();
		makeEndScoreList();
		
	} // moet er uit.
	
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
