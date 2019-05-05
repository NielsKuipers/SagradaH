package model;

import java.util.ArrayList;

public class DiceOnTable {
	private ArrayList<Dice> allDice = new ArrayList<>();
	
	public void addDiceToTable(Dice dice) {
		allDice.add(dice);
	}
	
	public void removeDiceFromTable(Dice dice) {
		for (Dice die : allDice) {
			if(die.equals(dice)) {
				allDice.remove(die);
				break;
			}
		}
	}
	
	public void getDice(int i) {
		allDice.get(i);
	}
}
