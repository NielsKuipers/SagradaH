package controller;

import javafx.scene.Node;
import main.GUI;
import model.DiceOnTable;
import view.DiceOnTableScreen;
import view.DiceScreen;

import java.util.ArrayList;
import java.util.Random;


public class DiceController {

	private Random r = new Random();

	private DiceOnTableScreen diceOnTableScreen;

	private DiceOnTable diceOnTableModel;
	private CardController CC;
	private GameController GC;
	private GUI gui;
	
	public DiceController(GUI gui, WindowController WC) {

		this.gui=gui;
		

		diceOnTableModel = new DiceOnTable();

		
		diceOnTableScreen = new DiceOnTableScreen(gui, diceOnTableModel, WC);

		
	}
	
	public void setGameController(GameController GC) {
		this.GC=GC;
	}
	
				



	public DiceOnTableScreen getDiceOnTableScreen(){

		return diceOnTableScreen;
	}
	
	public void setCardController(CardController CC) {
		this.CC=CC;
	}

	
	DiceOnTable getDiceOnTableModel() {

		return diceOnTableModel;
	}

	void setDiceGlowBorder(int nummer) {
		
		for (Node node : diceOnTableScreen.getChildren()) {
			if (node instanceof DiceScreen) {
				DiceScreen result = (DiceScreen) node;
				result.setGlowBorder();
				result.setOnMouseClicked(e -> selectDice(result, nummer));
				
					
			}

		}

	}

	private void setDiceBlackBorder() {
		for (Node node : diceOnTableScreen.getChildren()) {
			if (node instanceof DiceScreen) {
				DiceScreen result = (DiceScreen) node;
				result.setBlackBorder();
				result.setOnMouseClicked(null);

			}

		}

	}

	private void selectDice(DiceScreen dice, int nummer) {
		setDiceBlackBorder();
		dice.setGlowBorder();
		
		
		
		switch(nummer) {
		case 1:
			dice.setOnMouseClicked(e -> DicesPlus1(dice, false));
			break;
		case 6:	
			dice.setOnMouseClicked(e -> throwDiceOnes(dice));
			break;
			
		case 10: dice.setOnMouseClicked(e -> DiceTurnAround(dice));
			break;
		case 11: dice.setOnMouseClicked(e -> pickNewDice(dice));
		}

	}
	
	private void pickNewDice(DiceScreen dice) {
		System.out.println(dice.getDiceModel().getColorForQuerie());
		System.out.println(dice.getDiceModel().getEyes());
		System.out.println(dice.getDiceModel().getDiceNumber());
		
		dice.setOnMouseClicked(null);
		GC.getGameModel().pickNewDice(dice.getDiceModel().getDiceNumber(), dice.getDiceModel().getColorForQuerie());
		GC.startTimer();
		
//		dice.setOnMouseClicked(e -> DicesPlus1(dice, true));
	}
	
	private void throwDiceOnes(DiceScreen dice) {
		dice.setOnMouseClicked(null);
		
		int i = r.nextInt((6 - 1) + 1) + 1;
		dice.getDiceModel().setEyes(i);
		CC.getCardModel().updateDiceOnTable(i, dice.getDiceModel().getDiceNumber(),dice.getDiceModel().getColor());
		
		
		
	}
	
	
	private void DiceTurnAround(DiceScreen dice) {
		switch(dice.getDiceModel().getEyes()) {
		case 1:	dice.getDiceModel().setEyes(6);
			CC.getCardModel().updateDiceOnTable(6, dice.getDiceModel().getDiceNumber(),dice.getDiceModel().getColor());
			break;
		case 2:	dice.getDiceModel().setEyes(5);
		CC.getCardModel().updateDiceOnTable(5, dice.getDiceModel().getDiceNumber(),dice.getDiceModel().getColor());
		break;
		case 3:	dice.getDiceModel().setEyes(4);
		CC.getCardModel().updateDiceOnTable(4, dice.getDiceModel().getDiceNumber(),dice.getDiceModel().getColor());
		break;
		case 4:	dice.getDiceModel().setEyes(3);
		CC.getCardModel().updateDiceOnTable(3, dice.getDiceModel().getDiceNumber(),dice.getDiceModel().getColor());
		break;
		case 5:	dice.getDiceModel().setEyes(2);
		CC.getCardModel().updateDiceOnTable(2, dice.getDiceModel().getDiceNumber(),dice.getDiceModel().getColor());
		break;
		case 6:	dice.getDiceModel().setEyes(1);
		CC.getCardModel().updateDiceOnTable(1, dice.getDiceModel().getDiceNumber(),dice.getDiceModel().getColor());
		
		break;
		
		
		
		
		}
		
		
		
		
	}
	
	private void dicesMinus2(DiceScreen dice) {
		
		
		switch(dice.getDiceModel().getEyes()) {
		case 1:
			dice.getDiceModel().setEyes(1);
			CC.getCardModel().updateDiceOnTable(1, dice.getDiceModel().getDiceNumber(),dice.getDiceModel().getColor());
			break;
		case 2:
			dice.getDiceModel().setEyes(1);
			CC.getCardModel().updateDiceOnTable(1, dice.getDiceModel().getDiceNumber(),dice.getDiceModel().getColor());
			break;
		case 3:
			dice.getDiceModel().setEyes(1);
			CC.getCardModel().updateDiceOnTable(1, dice.getDiceModel().getDiceNumber(),dice.getDiceModel().getColor());
			break;
		case 4:
			dice.getDiceModel().setEyes(2);
			CC.getCardModel().updateDiceOnTable(1, dice.getDiceModel().getDiceNumber(),dice.getDiceModel().getColor());
			break;
		case 5:
			dice.getDiceModel().setEyes(3);
			CC.getCardModel().updateDiceOnTable(1, dice.getDiceModel().getDiceNumber(),dice.getDiceModel().getColor());
			break;
		case 6:
			dice.getDiceModel().setEyes(5);
			CC.getCardModel().updateDiceOnTable(1, dice.getDiceModel().getDiceNumber(),dice.getDiceModel().getColor());
			break;
		
		}
		
		dice.setOnMouseClicked(null);
		
		
	}

	
	

	private void DicesPlus1(DiceScreen dice, boolean repeat) {
		
		if(dice.getDiceModel().getEyes() == 6 && repeat){
			dice.getDiceModel().setEyes(1);
			CC.getCardModel().updateDiceOnTable(1, dice.getDiceModel().getDiceNumber(),dice.getDiceModel().getColor());
			}
		if (!(dice.getDiceModel().getEyes() == 6) && !repeat){
			dice.getDiceModel().setEyes(dice.getDiceModel().getEyes() + 1);
			CC.getCardModel().updateDiceOnTable(dice.getDiceModel().getEyes() + 1, dice.getDiceModel().getDiceNumber(),dice.getDiceModel().getColor());
		}
		if(!repeat) {
		dice.setOnMouseClicked(e -> dicesMinus2(dice));
		}
		
	}

	

}
