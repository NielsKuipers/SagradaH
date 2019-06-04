package model;

import java.util.ArrayList;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class DiceOnTableModel {
	private ArrayList<DiceModel> allDice = new ArrayList<>();
	private Property<ArrayList<DiceModel>> diceOnTableProperty = new SimpleObjectProperty<>();


	void addDiceToTable(DiceModel dice) {
		allDice.add(dice);
		diceOnTableProperty.setValue(new ArrayList<>());
		diceOnTableProperty.setValue(allDice);
		
	}
	
	public void removeDiceFromTable(DiceModel dice) {
		for (DiceModel die : allDice) {
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
	
	void removeAllDicesFromTable() {
			
			allDice.clear();
			diceOnTableProperty.setValue(new ArrayList<>());
			diceOnTableProperty.setValue(allDice);
	}


	public boolean isDiceOnTable(DiceModel dice) {
		for (DiceModel die : allDice) {
			if (die.equals(dice)) {
				return true;
			}
		}
		return false;
	}
  
	public Property<ArrayList<DiceModel>> diceOnTableProperty() {
		return diceOnTableProperty;
	}
}
