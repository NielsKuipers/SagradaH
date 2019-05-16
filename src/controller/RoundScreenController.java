package controller;

import model.GameModel;
import view.RoundScreen;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class RoundScreenController {

	private RoundScreen roundScreen;
	private GameModel gameModel;
	
	
	public RoundScreenController(Stage stage, DatabaseController dataController) {
		roundScreen = new RoundScreen();
		gameModel = new GameModel(dataController.getGameQueries());
		stage.setScene(new Scene(roundScreen));
		
		addDice();
	}
	
	// aan de hand van rondenummers worden alle dobbelstenen op het rondebord opgevraagd uit de database, en in de view gezet.
	public void addDice() {
		
		for(int round = 1; round <11; round++) {
			int row = 2;
			ArrayList<ArrayList <Object>> result = gameModel.getDicesOnRoundBoard(round);
			
			for(int i=0; i<result.size(); i++) {
				roundScreen.addDice(round, (int) result.get(i).get(1), (String) result.get(i).get(0), row);
				row++;
			}
		}
	}
}
