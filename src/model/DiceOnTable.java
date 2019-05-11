package model;

import java.util.ArrayList;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class DiceOnTable {
	private ArrayList<Dice> allDice = new ArrayList<>();
	private Property<ArrayList<Dice>> diceOnTableProperty = new SimpleObjectProperty<>();


	public void addDiceToTable(Dice dice) {
		allDice.add(dice);
		diceOnTableProperty.setValue(new ArrayList<>());
		diceOnTableProperty.setValue(allDice);
		
	}
	
	public void removeDiceFromTable(Dice dice) {
		for (Dice die : allDice) {
			if (die.equals(dice)) {
				allDice.remove(die);
				diceOnTableProperty.setValue(new ArrayList<>());
				diceOnTableProperty.setValue(allDice);
				break;
			}
		}
		
		if (allDice.size() == 0) {
			diceOnTableProperty.setValue(null);
		}
		
	}

	public boolean isDiceOnTable(Dice dice) {
		for (Dice die : allDice) {
			if (die.equals(dice)) {
				return true;
			}
		}
		return false;
	}

	public void getDice(int i) {
		allDice.get(i);
	}

	public Property<ArrayList<Dice>> diceOnTableProperty() {
		return diceOnTableProperty;
	}
}
