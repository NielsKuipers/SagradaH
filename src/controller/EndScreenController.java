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
		stage.setScene(new Scene(endScreen));
		putPlayersOnBoard();
		makeEndScoreList();
		
	}
	
	// zet speler pion op scorebord
	private void putPlayersOnBoard() {
		ArrayList<ArrayList<Object>> result = gameModel.getPlayerScores();
		
		
		endScreen.addPlayer(10, Color.RED);
		endScreen.addPlayer(1, Color.WHITE);
		endScreen.addPlayer(1, Color.BLUE);
		endScreen.addPlayer(1, Color.YELLOW);
		endScreen.addPlayer(1, Color.BLACK);
		endScreen.addPlayer(100, Color.BLUE);
		endScreen.addPlayer(0, Color.YELLOW);
		endScreen.addPlayer(-20, Color.GREEN);
	}
	
	// maakt speler + score lijst
	private void makeEndScoreList() {
		
	}
}
