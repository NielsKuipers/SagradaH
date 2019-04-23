package model;

import java.util.ArrayList;

public class WindowPattern {

	private ArrayList<Field> fields = new ArrayList<>();
	private int difficulty = 0;
	
	
	
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
		difficulty = dif;
	}
	

}
