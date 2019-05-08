package model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import queries.WindowPatternQuerie;

public class WindowPattern {

	int id = 1;
	private ArrayList<Field> fields = new ArrayList<>();
	private StringProperty difficulty = new SimpleStringProperty(this, "difficulty", "empty");

	private WindowPatternQuerie windowPatternQuerie;

	public WindowPattern(WindowPatternQuerie windowPatternQuerie) {
		this.windowPatternQuerie = windowPatternQuerie;
	}

	public void addFieldToWindow(Field field) {
		fields.add(field);
	}

	public Field getFieldOfWindow(int column, int row) {
		Field result = null;
		for (Field field : fields) {
			if (field.getColumn() == column && field.getRow() == row) {
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

	public void removeDiceFromWindowPattern(Dice dice) {
		for (Field field : fields) {
			if (field.getDice() == dice) {
				field.deleteDice();
			}
		}
	}

	public boolean diceOnWindow(Dice dice) {
		for (Field field : fields) {
			if (field.getDice() == dice) {
				return true;
			}
		}
		return false;
	}

	
	//mysql related
	public void updateAllFields() {
		ArrayList<ArrayList<Object>> result = windowPatternQuerie.getAllFields(id);
		for (int row = 1; row < 5; row++) {
			for (int column = 0; column < 5; column++) {
				for (int i = 0; i < result.size(); i++) {
					try {
						if (row == Integer.valueOf(result.get(i).get(3).toString()) && column == Integer.valueOf(result.get(i).get(2).toString()) - 1) {
							getFieldOfWindow(column, row).setColorAndEyes(makeColorFromQuerie(result.get(i).get(0).toString()),Integer.valueOf(result.get(i).get(1).toString()));
							
						}
					} catch (Exception e) {
						// TODO: handle exception
					}

				}

			}
		}
	}
	

	public int getDifficulty() {
		return Integer.parseInt(difficulty.get());
	}
	
	
	

	public void updateAllDicesOnField() {
		ArrayList<ArrayList<Object>> result = windowPatternQuerie.getAllDicesOnField(id);
		for (int row = 1; row < 5; row++) {
			for (int column = 0; column < 5; column++) {
				for (int i = 0; i < result.size(); i++) {
					try {
						if (row == Integer.valueOf(result.get(i).get(3).toString()) && column == Integer.valueOf(result.get(i).get(2).toString()) - 1) {
							int eyes = Integer.valueOf(result.get(i).get(1).toString());
							getFieldOfWindow(column, row).addDice(new Dice(Integer.valueOf(result.get(i).get(1).toString()), makeColorFromQuerie(result.get(i).get(0).toString())));
							getFieldOfWindow(column, row).getDice().setEyes(eyes);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}


				}

			}
		}
	}

	public Color makeColorFromQuerie(String color) {
		if (color.equals("geel")) {
			return Color.YELLOW;
		} else if (color.equals("groen")) {
			return Color.LIGHTGREEN;
		} else if (color.equals("blauw")) {
			return Color.CORNFLOWERBLUE;
		} else if (color.equals("paars")) {
			return Color.MAGENTA;
		} else if (color.equals("rood")) {
			return Color.RED;
		} else if (color.equals("")) {
			return Color.LIGHTGRAY;
		}
		return Color.LIGHTGRAY;
	}
}
