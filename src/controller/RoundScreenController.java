package controller;

import model.GameModel;
import view.RoundScreen;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class RoundScreenController {

	private RoundScreen screen;
	private GameModel gameModel;
	
	
	public RoundScreenController(Stage stage, DatabaseController dataController) {
		screen = new RoundScreen();
		gameModel = new GameModel(dataController.getGameQueries());
		stage.setScene(new Scene(screen));
		
		addDice();
	}
	
	// rondenummer, kleur en nummer moeten uit de model worden gehaald en aangemaakt in de roundscreen
	public void addDice() {
		ArrayList<ArrayList <Object>> result = gameModel.getDicesOnRoundBoard();
		
		for(int i =0; i < result.size(); i ++) {
			screen.addDice((int) result.get(i).get(0), (int) result.get(i).get(2), (String) result.get(i).get(1));
		}
		// tests
//		screen.addDice(0, 2, "green");
//		screen.addDice(1, 3, "blue");
//		screen.addDice(2, 1, "red");
//		screen.addDice(3, 6, "yellow");
//		screen.addDice(9, 5, "purple");
//		screen.addDice(10, 5, "purple");
//		screen.addDice(2, 1, "red");
//		screen.removeDice(2);
//		screen.removeDice(2);
//		screen.removeDice(10);
	}
}
