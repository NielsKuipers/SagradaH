package model;

import java.util.ArrayList;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import queries.WindowPatternQuerie;

public class WindowPattern {

	private int idWindow;
	private ArrayList<Field> fields = new ArrayList<>();
	private StringProperty difficulty = new SimpleStringProperty(this, "difficulty", "empty");
	private StringProperty playerName = new SimpleStringProperty(this, "playerName", "empty");
	private StringProperty playerScore = new SimpleStringProperty(this, "playerScore", "empty");
	private Property<Color> backgroundProperty = new SimpleObjectProperty<>();

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

	public final StringProperty difficultyProperty() {
		return difficulty;
	}
	
	int getDifficulty() {
		ArrayList<ArrayList<Object>> result = windowPatternQuerie.getDifficulty(idWindow);
		return Integer.valueOf(String.valueOf(result.get(0).get(0)));
	}
	
	void setPlayerName(String name) {
		playerName.set(name);
	}

	public final StringProperty playerNameProperty() {
		return playerName;
	}
	
	void setPlayerScore(String s) {
		playerScore.set(String.valueOf(s));
	}

	public final StringProperty playerScoreProperty() {
		return playerScore;
	}
	
	public Property<Color> backgroundPropery() {
		return backgroundProperty;
	}
	
	public void setBackground(Color color) {
		backgroundProperty.setValue(color);
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

	
	//get all the fields and add it to the right model
	void selectAllFields() {
		for (Field field : fields) {
			field.deleteDice();
		}
		ArrayList<ArrayList<Object>> result = windowPatternQuerie.getAllFields(idWindow);
		for (int row = 1; row < 5; row++) {
			for (int column = 0; column < 5; column++) {
				for (ArrayList<Object> objects : result) {
					try {
						if (row == Integer.valueOf(objects.get(3).toString()) && column == Integer.valueOf(objects.get(2).toString()) - 1) {
							getFieldOfWindow(column, row).setColorAndEyes(makeColorFromQuerie(objects.get(0)), makeEyeFromQuerie(objects.get(1)));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}
		}
		
	}
	
	//get all the dices on all fields
	void selectAllDicesOnField(int idPlayer, int idGame) {
		ArrayList<ArrayList<Object>> result = windowPatternQuerie.getAllDicesOnField(idPlayer, idGame);
		for (int row = 1; row < 5; row++) {
			for (int column = 0; column < 5; column++) {
				getFieldOfWindow(column, row).deleteDice();
				for (ArrayList<Object> objects : result) {
					try {
						if (row == Integer.valueOf(objects.get(3).toString()) && column == Integer.valueOf(objects.get(2).toString()) - 1 && objects.get(1) != null) {
							int eyes = Integer.valueOf(objects.get(1).toString());
							getFieldOfWindow(column, row).addDice(new Dice(makeEyeFromQuerie(objects.get(1)), makeColorFromQuerie(objects.get(0)), Integer.valueOf(String.valueOf(objects.get(4)))));
							getFieldOfWindow(column, row).getDice().setEyes(eyes);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}
		}
	}
	
	public int getDifficulty() {
		return Integer.parseInt(difficulty.get());
	}

	void selectDifficulty() {
		ArrayList<ArrayList<Object>> result = windowPatternQuerie.getDifficulty(idWindow);
		difficulty.set("Moeilijkheidsgraad: " + result.get(0).get(0));

	}

	private Color makeColorFromQuerie(Object c) {
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
	
	private int makeEyeFromQuerie(Object eye) {
		
		if (eye == null) {
			return 0;
		}
		return Integer.valueOf(String.valueOf(eye));
	}
	
	void setId(int id) {
		idWindow = id;
	}
	
	public int getId() {
		return idWindow;
	}
	
	
}

