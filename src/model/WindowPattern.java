package model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import queries.WindowPatternQuerie;

public class WindowPattern {

	private int idWindow;
	private ArrayList<Field> fields = new ArrayList<>();
	private StringProperty difficulty = new SimpleStringProperty(this, "difficulty", "empty");
	private StringProperty playerName = new SimpleStringProperty(this, "playerName", "empty");
	private StringProperty playerScore = new SimpleStringProperty(this, "playerScore", "empty");

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
	
	public void setPlayerName(String name) {
		playerName.set(name);
	}

	public final StringProperty playerNameProperty() {
		return playerName;
	}
	
	public void setPlayerScore(String s) {
		playerScore.set(String.valueOf(s));
	}

	public final StringProperty playerScoreProperty() {
		return playerScore;
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
	public void selectAllFields() {
		ArrayList<ArrayList<Object>> result = windowPatternQuerie.getAllFields(idWindow);
		for (int row = 1; row < 5; row++) {
			for (int column = 0; column < 5; column++) {
				for (int i = 0; i < result.size(); i++) {
					try {
						if (row == Integer.valueOf(result.get(i).get(3).toString()) && column == Integer.valueOf(result.get(i).get(2).toString()) - 1) {
							getFieldOfWindow(column, row).setColorAndEyes(makeColorFromQuerie(result.get(i).get(0)),makeEyeFromQuerie(result.get(i).get(1)));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}
		}
		
	}
	
	public void selectAllDicesOnField(int idPlayer) {
		ArrayList<ArrayList<Object>> result = windowPatternQuerie.getAllDicesOnField(idPlayer);
		for (int row = 1; row < 5; row++) {
			for (int column = 0; column < 5; column++) {
				for (int i = 0; i < result.size(); i++) {
					try {
						if (row == Integer.valueOf(result.get(i).get(3).toString()) && column == Integer.valueOf(result.get(i).get(2).toString()) - 1) {
							int eyes = Integer.valueOf(result.get(i).get(1).toString());
							getFieldOfWindow(column, row).addDice(new Dice(makeEyeFromQuerie(result.get(i).get(1)), makeColorFromQuerie(result.get(i).get(0)), Integer.valueOf(String.valueOf(result.get(i).get(4)))));
							getFieldOfWindow(column, row).getDice().setEyes(eyes);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}
		}
	}
	
	public void selectDifficulty() {
		ArrayList<ArrayList<Object>> result = windowPatternQuerie.getDifficulty(idWindow);
		difficulty.set(String.valueOf("Moeilijkheidsgraad: " + result.get(0).get(0)));
	}

	public Color makeColorFromQuerie(Object c) {
		String color = String.valueOf(c);
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
	
	public int makeEyeFromQuerie(Object eye) {
		
		if (eye == null) {
			return 0;
		}
		return Integer.valueOf(String.valueOf(eye));
	}
	
	public void setId(int id) {
		idWindow = id;
	}
	
	public int getId() {
		return idWindow;
	}
}
