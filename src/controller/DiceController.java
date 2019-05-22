package controller;

import main.GUI;
import model.DiceOnTable;
import view.DiceOnTableScreen;

import java.util.Random;

public class DiceController {

	private Random r = new Random();

	private DiceOnTableScreen diceOnTableScreen;
	
	private DiceOnTable diceOnTableModel;

	public DiceController(GUI gui, WindowController WC) {

		diceOnTableModel = new DiceOnTable();
		
		diceOnTableScreen = new DiceOnTableScreen(gui, diceOnTableModel, WC);

	}
	
	DiceOnTableScreen getDiceOnTableScreen(){
		return diceOnTableScreen;
	}
	
	DiceOnTable getDiceOnTableModel() {
		return diceOnTableModel;
	}

}
