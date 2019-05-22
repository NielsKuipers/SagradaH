package model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import queries.WindowPatternQuerie;
import static model.Game.getColorFromQuery;

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
		difficulty.set(Integer.toString(dif));
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
	
	void setPlayerScore(String s) {
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
	
	void selectAllDicesOnField(int idPlayer) {
		ArrayList<ArrayList<Object>> result = windowPatternQuerie.getAllDicesOnField(idPlayer);
		for (int row = 1; row < 5; row++) {
			for (int column = 0; column < 5; column++) {
				for (ArrayList<Object> objects : result) {
					try {
						if (row == Integer.valueOf(objects.get(3).toString()) && column == Integer.valueOf(objects.get(2).toString()) - 1) {
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

	public void selectDifficulty() {
		ArrayList<ArrayList<Object>> result = windowPatternQuerie.getDifficulty(idWindow);
		difficulty.set("Moeilijkheidsgraad: " + result.get(0).get(0));

	}

	private Color makeColorFromQuerie(Object c) {
		return getColorFromQuery(c);
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

