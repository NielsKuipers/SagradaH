package controller;

import model.GameModel;
import view.RoundScreen;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RoundScreenController {

	private RoundScreen screen;
	private GameModel model;
	
	public RoundScreenController(Stage stage) {
		screen = new RoundScreen();
		stage.setScene(new Scene(screen));
		
		addDice();
	}
	
	// rondenummer, kleur en nummer moeten uit de model worden gehaald en aangemaakt in de roundscreen
	public void addDice() {
		
		
		
		// tests
		screen.addDice(0, 2, "green");
		screen.addDice(1, 3, "blue");
		screen.addDice(2, 1, "red");
		screen.addDice(3, 6, "yellow");
		screen.addDice(9, 5, "purple");
		screen.addDice(10, 5, "purple");
		screen.addDice(2, 1, "red");
		screen.removeDice(2);
		screen.removeDice(2);
		screen.removeDice(10);
	}
}
