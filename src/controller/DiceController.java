package controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import main.GUI;
import model.Dice;
import model.DiceOnTable;
import view.DiceOnTableScreen;
import view.DiceScreen;

public class DiceController {

	private ArrayList<Color> colorsDice = new ArrayList<>();
	private Random r = new Random();
	
	private WindowController WC;
	
	private GUI gui;
	
	private DiceOnTableScreen diceOnTableScreen;
	
	private DiceOnTable diceOnTableModel;

	public DiceController(GUI gui, WindowController WC) {
		this.gui = gui;
		this.WC = WC;
		
		diceOnTableModel = new DiceOnTable();
		
		diceOnTableScreen = new DiceOnTableScreen(gui, diceOnTableModel, WC);
		
		addColorsDice();
		makeDices();

	}
	
	public void makeDices() {
		for (int i = 0; i < 9; i++) {
				int eyes = r.nextInt((6 - 1) + 1) + 1;
				int color = r.nextInt(5);
				Dice diceModel = new Dice(eyes, colorsDice.get(color));
				diceModel.setEyes(eyes);
				diceOnTableModel.addDiceToTable(diceModel);
		}
	}
	
	public void addColorsDice() {
		colorsDice.add(Color.CORNFLOWERBLUE);
		colorsDice.add(Color.YELLOW);
		colorsDice.add(Color.RED);
		colorsDice.add(Color.MAGENTA);
		colorsDice.add(Color.LIGHTGREEN);
	}
	
	public DiceOnTableScreen getDiceOnTableScreen(){
		return diceOnTableScreen;
	}
	
	public DiceOnTable getDiceOnTableModel() {
		return diceOnTableModel;
	}

}
