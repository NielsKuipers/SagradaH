package model;

import java.util.ArrayList;

public class GameModel {
	private ArrayList <Dice> dices;
	
	public GameModel() {
		dices = new ArrayList<Dice>();
		
	}
	
	// zet data uit de database in dice objecten en voegt ze toe aan de dices array
	public void setDice(int round, int number, String color) {
		dices.add(new Dice(round, number, color));
		
	}
	
	// return dices array
	public ArrayList<Dice> getDices(){
		return dices;
	}
	
	//klasse om dice properties op te slaan
	private class Dice{
		private int roundNumber;
		private int number;
		private String color;
		
		private Dice(int round, int number, String color) {
			roundNumber = round;
			this.number = number;
			this.color = color;
		}
	}
}
