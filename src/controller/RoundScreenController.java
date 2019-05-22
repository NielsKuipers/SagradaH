package controller;

import model.GameModel;
import timer.AnimationTimerEXT;
import view.RoundScreen;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RoundScreenController {

	private RoundScreen roundScreen;
	private GameModel gameModel;
	private Boolean roundScreenToolcardActive = false;

	public RoundScreenController(Stage stage, DatabaseController dataController) {
		roundScreen = new RoundScreen(this);
		gameModel = new GameModel(dataController.getGameQueries());
		stage.setScene(new Scene(roundScreen));
		
		addDice();
		createTimer();
	}
	
	// aan de hand van rondenummers worden alle dobbelstenen op het rondebord opgevraagd uit de database, en in de view gezet.
	private void addDice() {
		for(int round = 1; round <11; round++) {
			int row = 2;
			ArrayList<ArrayList <Object>> result = gameModel.getDicesOnRoundBoard(round);

			for (ArrayList<Object> objects : result) {
				roundScreen.addDice(round, (int) objects.get(1), (String) objects.get(0), row, (int) objects.get(2));
				row++;
			}
		}
	}
	
	//verwijdert dice en voegt opnieuw toe
	public void refreshDice() {
		roundScreen.clearBoard();
		addDice();
	}

	
	// verwijdert dice uit model
	public void removeDice(int diceID, Color color) {
		String colorText = getColorTranslation(color);
		gameModel.removeDice(diceID, colorText);
		
	}

	
	// vertaald hexadecimale kleur naar nederlandse woorden
	private String getColorTranslation(Color color){
		String colortext = color.toString();
		
		switch(colortext) {
		  case "0x0000ffff":
			 return "blauw";
		  case "0xffff00ff":
			 return "geel";
		  case "0xff0000ff":
			 return "rood";
		  case "0x800080ff":
			  return "paars";
		  case "0x008000ff":
			  return "groen";
		  default:
		    return "";
		}
	}

	// wordt aangezet als toolcard gebruikt wordt, zorgt er voor dat dobbelstenen gekozen kunnen worden 
	public Boolean getRoundScreenToolcardActive() {
		return roundScreenToolcardActive;
	}
	
	private void createTimer() {
		AnimationTimerEXT timer = new AnimationTimerEXT(10000) {
			@Override
			public void doAction() {
				refreshDice();
			}
		};
		timer.start();
	}
}
