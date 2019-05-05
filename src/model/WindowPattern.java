package model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WindowPattern {

	private ArrayList<Field> fields = new ArrayList<>();
	private StringProperty difficulty = new SimpleStringProperty(this, "difficulty", "empty");
	
	
	
	public void addFieldToWindow(Field field) {
		fields.add(field);
	}
	
	public Field getFieldOfWindow(int column, int row) {
		Field result = null;
		for (Field field : fields) {
			if(field.getColumn() == column && field.getRow() == row) {
				result = field;
			}
			
		}
		
		return result;
	}
	
	public void setDifficultyWindowPattern(int dif) {
		difficulty.set("Moeilijkheidsgraad: " + dif);
	}
	
	public final StringProperty difficultyProperty() {
		return difficulty;
	}
}
