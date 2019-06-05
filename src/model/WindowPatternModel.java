package model;

import java.util.ArrayList;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import queries.WindowPatternQueries;

public class WindowPatternModel {

	private int idWindow;
	private ArrayList<FieldModel> fields = new ArrayList<>();
	private StringProperty difficulty = new SimpleStringProperty(this, "difficulty", "empty");
	private StringProperty playerName = new SimpleStringProperty(this, "playerName", "empty");
	private StringProperty playerScore = new SimpleStringProperty(this, "playerScore", "empty");
	private Property<Color> backgroundProperty = new SimpleObjectProperty<>();

	private WindowPatternQueries windowPatternQueries;

	public WindowPatternModel(WindowPatternQueries windowPatternQueries) {
		this.windowPatternQueries = windowPatternQueries;
	}

	public void addFieldToWindow(FieldModel field) {
		fields.add(field);
	}

	/**
	 * @param column = column of a field
	 * @param row = row of a field
	 * @return a field model
	 * get a field
	 */
	public FieldModel getFieldOfWindow(int column, int row) {
		FieldModel result = null;
		for (FieldModel field : fields) {
			if (field.getColumn() == column && field.getRow() == row) {
				result = field;
			}
		}
		return result;
	}

	public final StringProperty difficultyProperty() {
		return difficulty;
	}
	
	void setPlayerName(String name) {
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
	
	public Property<Color> backgroundPropery() {
		return backgroundProperty;
	}
	
	public void setBackground(Color color) {
		backgroundProperty.setValue(color);
	}

	/**
	 * @param dice = dice model
	 * remove a dice from a field
	 */
	public void removeDiceFromWindowPattern(DiceModel dice) {
		for (FieldModel field : fields) {
			if (field.getDice() == dice) {
				field.deleteDice();
			}
		}
	}

	/**
	 * @param dice = dice model
	 * @return true or false
	 * checks if a specific dice is on a field
	 */
	public boolean diceOnWindow(DiceModel dice) {
		for (FieldModel field : fields) {
			if (field.getDice() == dice) {
				return true;
			}
		}
		return false;
	}

	
	
	/**
	 * give all the fields the right values
	 */
	void selectAllFields() {
		for (FieldModel field : fields) {
			field.deleteDice();
		}
		ArrayList<ArrayList<Object>> result = windowPatternQueries.getAllFields(idWindow);
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
	
	/**
	 * @param idPlayer = id of a player
	 * @param idGame = id of the game
	 * place all the dices on the fields
	 */
	void selectAllDicesOnField(int idPlayer, int idGame) {
		ArrayList<ArrayList<Object>> result = windowPatternQueries.getAllDicesOnField(idPlayer, idGame);
		for (int row = 1; row < 5; row++) {
			for (int column = 0; column < 5; column++) {
				getFieldOfWindow(column, row).deleteDice();
				for (ArrayList<Object> objects : result) {
					try {
						if (row == Integer.valueOf(objects.get(3).toString()) && column == Integer.valueOf(objects.get(2).toString()) - 1 && objects.get(1) != null) {
							int eyes = Integer.valueOf(objects.get(1).toString());
							getFieldOfWindow(column, row).addDice(new DiceModel(makeEyeFromQuerie(objects.get(1)), makeColorFromQuerie(objects.get(0)), Integer.valueOf(String.valueOf(objects.get(4)))));
							getFieldOfWindow(column, row).getDice().setEyes(eyes);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}
		}
	}

	void selectDifficulty() {
		ArrayList<ArrayList<Object>> result = windowPatternQueries.getDifficulty(idWindow);
		difficulty.set("Moeilijkheidsgraad: " + result.get(0).get(0));
	}

	/**
	 * @param c = Object/String color
	 * @return a java color
	 * make a java color from Object/String
	 */
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
	
	/**
	 * make all the windows gray and delete all the dices
	 */
	void makeWindowEmpty() {
		for (FieldModel field : fields) {
			field.setColorAndEyes(Color.LIGHTGRAY, 0);
			field.deleteDice();
		}
		difficulty.set("Moeilijkheidsgraad: 0" );
	}
	
	
}

