package controller;

import view.EndScreen;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EndScreenController {
	
	private EndScreen screen;
	
	public EndScreenController(Stage stage) {
		screen = new EndScreen(this);
		stage.setScene(new Scene(screen));
		calculateEndScores();
		putPlayersOnBoard();
		makeEndScoreList();
		
	}
	
	// berekent scores per speler inclusief private objective cards
	private void calculateEndScores() {
		
	}
	
	// zet speler pion op scorebord
	private void putPlayersOnBoard() {
		//tests
		screen.addPlayer(10, Color.RED);
		screen.addPlayer(1, Color.WHITE);
		screen.addPlayer(1, Color.BLUE);
		screen.addPlayer(1, Color.YELLOW);
		screen.addPlayer(1, Color.BLACK);
		screen.addPlayer(100, Color.BLUE);
		screen.addPlayer(0, Color.YELLOW);
		screen.addPlayer(-20, Color.GREEN);
	}
	
	// maakt speler + score lijst
	private void makeEndScoreList() {
		
	}
}
