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
	

	public void getDices() {
		for(int c = 0; c<allDice.size();c ++) {
			allDice.get(c);
		}	
		}

	public boolean isDiceOnTable(Dice dice){
		for (Dice die : allDice) {
			if(die.equals(dice)) {
				return true;
			}
		}
		return false;
	}
	
	public void getDice(int i) {
		allDice.get(i);

	}
}
