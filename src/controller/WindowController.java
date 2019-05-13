package controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.GUI;
import model.Dice;
import model.Field;
import model.WindowPattern;
import timer.AnimationTimerEXT;
import view.DiceScreen;
import view.FieldScreen;
import view.WindowPatternScreen;

public class WindowController {
	private AnimationTimerEXT timer;
	
	private WindowPatternScreen window1;
	private WindowPatternScreen window2;
	private WindowPatternScreen window3;
	private WindowPatternScreen window4;

	private WindowPattern windowPattern1Model;
	private WindowPattern windowPattern2Model;
	private WindowPattern windowPattern3Model;
	private WindowPattern windowPattern4Model;

	private DatabaseController databaseController;
	private GameController GC;
	private DiceController DC;

	private GUI gui;

	private ArrayList<Color> colorsField = new ArrayList<>();
	private ArrayList<Integer> numbers = new ArrayList<>();
	private Random r = new Random();

	private boolean cheatAllPossible = false;
	private boolean cheatBestChoice = false;

	private final DataFormat diceFormat = new DataFormat("MyDice");

	private DiceScreen draggingDice;

	private boolean diceCanBeMoved = true;
	private boolean ignoreEyes = false;
	private boolean ignoreColor = false;

	public WindowController(GUI gui, DatabaseController databaseController) {

		this.gui = gui;

		this.databaseController = databaseController;

		windowPattern1Model = new WindowPattern(databaseController.getWindowPatternQuerie());
		windowPattern2Model = new WindowPattern(databaseController.getWindowPatternQuerie());
		windowPattern3Model = new WindowPattern(databaseController.getWindowPatternQuerie());
		windowPattern4Model = new WindowPattern(databaseController.getWindowPatternQuerie());

		window1 = new WindowPatternScreen("kaart 1", windowPattern1Model, "WHITE");
		window2 = new WindowPatternScreen("kaart 2", windowPattern2Model, "WHITE");
		window3 = new WindowPatternScreen("kaart 3", windowPattern3Model, "WHITE");
		window4 = new WindowPatternScreen("kaart 4", windowPattern4Model, "WHITE");

		addColorsField();
		addNumbersField();

		createGrayWindowPattern(1, window1, windowPattern1Model);
		createGrayWindowPattern(2, window2, windowPattern2Model);
		createGrayWindowPattern(3, window3, windowPattern3Model);
		createGrayWindowPattern(4, window4, windowPattern4Model);

//		createRandomWindow(windowPattern1Model);
//		createRandomWindow(windowPattern2Model);
//		createRandomWindow(windowPattern3Model);
//		createRandomWindow(windowPattern4Model);
		
		// setStandardWindowPatern(windowPattern1Model);
		
		createTimer();


	}

	public void addColorsField() {
		colorsField.removeAll(colorsField);
		colorsField.add(Color.CORNFLOWERBLUE);
		colorsField.add(Color.YELLOW);
		colorsField.add(Color.RED);
		colorsField.add(Color.MAGENTA);
		colorsField.add(Color.LIGHTGREEN);
		colorsField.add(Color.LIGHTGRAY);
		colorsField.add(Color.LIGHTGRAY);
		colorsField.add(Color.LIGHTGRAY);
		colorsField.add(Color.LIGHTGRAY);
		colorsField.add(Color.LIGHTGRAY);
		colorsField.add(Color.LIGHTGRAY);
		colorsField.add(Color.LIGHTGRAY);
		colorsField.add(Color.LIGHTGRAY);

	}

	public void addNumbersField() {
		numbers.removeAll(numbers);
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		numbers.add(5);
		numbers.add(6);
		numbers.add(0);
		numbers.add(0);
		numbers.add(0);
		numbers.add(0);
		numbers.add(0);
		numbers.add(0);
		numbers.add(0);
		numbers.add(0);
		numbers.add(0);
		numbers.add(0);
		numbers.add(0);
		numbers.add(0);
		numbers.add(0);
		numbers.add(0);
		numbers.add(0);
	}

	public void createRandomWindow(WindowPattern windowModel) {

		// all rows
		for (int row = 1; row < 5; row++) {
			// first row
			if (row == 1) {
				// every field in that row
				for (int column = 0; column < 5; column++) {
					// check color of field left
					int highColor = colorsField.size();
					for (int z = 0; z < colorsField.size(); z++) {
						if (column > 0
								&& windowModel.getFieldOfWindow(column - 1, row).getColor() == colorsField.get(z)) {
							if (!colorsField.get(z).equals(Color.LIGHTGRAY)) {
								colorsField.remove(z);
								highColor--;
							}
						}
					}

					// check eyes left
					// highnumber is the amount of places in the array, its 10 because you want to
					// have more chances to get 0 eyes out of it
					int highNumber = numbers.size();
					for (int k = 0; k < numbers.size(); k++) {
						if (column > 0 && windowModel.getFieldOfWindow(column - 1, row).getEyes() == numbers.get(k)) {
							numbers.remove(k);
							highNumber--;
						}
					}

					// generate random numbers
					int resultColor = r.nextInt(highColor);
					int resultNumber = r.nextInt(highNumber);

					// check if field is gray, than it has no eyes
					if (colorsField.get(resultColor).equals(Color.LIGHTGRAY)) {
						windowModel.getFieldOfWindow(column, row).setColorAndEyes(colorsField.get(resultColor),
								numbers.get(resultNumber));

					} else {

						windowModel.getFieldOfWindow(column, row).setColorAndEyes(colorsField.get(resultColor), 0);
					}

					addColorsField();
					addNumbersField();

				}

			}

			// row 2
			else if (row > 1) {
				// all fields in that row
				for (int column = 0; column < 5; column++) {
					int highColor = colorsField.size();
					// every color above
					for (int z = 0; z < colorsField.size(); z++) {
						if (windowModel.getFieldOfWindow(column, row - 1).getColor() == colorsField.get(z)) {
							// gray fields can be placed next to each other
							if (!colorsField.get(z).equals(Color.LIGHTGRAY)) {
								colorsField.remove(z);
								highColor--;
							}
						}
					}
					// every color left
					for (int z = 0; z < colorsField.size(); z++) {
						if (column > 0
								&& windowModel.getFieldOfWindow(column - 1, row).getColor() == colorsField.get(z)) {
							if (!colorsField.get(z).equals(Color.LIGHTGRAY)) {
								colorsField.remove(z);
								highColor--;
							}
						}
					}

					int highNumber = numbers.size();
					// check eyes above
					for (int k = 0; k < numbers.size(); k++) {
						if (windowModel.getFieldOfWindow(column, row - 1).getEyes() == numbers.get(k)) {
							numbers.remove(k);
							highNumber--;
						}
					}

					// check eyes left
					for (int k = 0; k < numbers.size(); k++) {
						if (column > 0 && windowModel.getFieldOfWindow(column - 1, row).getEyes() == numbers.get(k)) {
							numbers.remove(k);
							highNumber--;
						}
					}

					Random r = new Random();
					int resultColor = r.nextInt(highColor);
					int resultNumber = r.nextInt(highNumber);

					// check if field is gray, than it has no eyes
					if (colorsField.get(resultColor).equals(Color.LIGHTGRAY)) {
						windowModel.getFieldOfWindow(column, row).setColorAndEyes(colorsField.get(resultColor),
								numbers.get(resultNumber));
					} else {
						windowModel.getFieldOfWindow(column, row).setColorAndEyes(colorsField.get(resultColor), 0);
					}

					// fill the array again
					addColorsField();
					addNumbersField();
				}
			}

		}

		calculateDifficulty(windowModel);
	}

	// pick up a dice
	public void dragButton(DiceScreen b) {
		b.setOnDragDetected(e -> {
			Dragboard db = b.startDragAndDrop(TransferMode.MOVE);
			db.setDragView(b.snapshot(null, null));
			ClipboardContent cc = new ClipboardContent();
			cc.put(diceFormat, " ");
			db.setContent(cc);
			draggingDice = b;
			makeEveryBorderBlack();
		});

	}

	public void addDropHandling(FieldScreen pane) {
		pane.setOnDragOver(e -> {
			Dragboard db = e.getDragboard();
			// check if you have a dice and you want to place it on your own board
			if (db.hasContent(diceFormat) && draggingDice != null && pane.getParent() == window1) {
				e.acceptTransferModes(TransferMode.MOVE);
				if (cheatAllPossible == true && cheatBestChoice == false) {
					whichPlacementIsPossible(draggingDice.getDiceModel(), null);
				} else if (cheatAllPossible == false && cheatBestChoice == true) {
					bestPossiblePlace(draggingDice.getDiceModel());
				}
			}
		});

		pane.setOnDragDropped(e -> {
			Dragboard db = e.getDragboard();

			// check if dice meets all the requirements
			if (db.hasContent(diceFormat)
					&& (draggingDice.getDiceModel().getEyes() == pane.getFieldModel().getEyes()
							|| pane.getFieldModel().getEyes() == 0 || ignoreEyes == true)
					&& (draggingDice.getDiceModel().getColor() == pane.getFieldModel().getColor()
							|| pane.getFieldModel().getColor() == Color.LIGHTGRAY || ignoreColor == true)
					&& (draggingDice.getDiceModel().getMoved() == false || diceCanBeMoved == true)
					&& pane.getFieldModel().hasDice() == false
					&& meetsNextToDiceRequirements(pane.getFieldModel(), draggingDice.getDiceModel()) == true
					&& isDiceNextToAnotherDice(pane.getFieldModel(), draggingDice.getDiceModel()) == true) {

				if (DC.getDiceOnTableModel().isDiceOnTable(draggingDice.getDiceModel()) == true && ignoreEyes == false
						&& ignoreColor == false) {
					// ((Pane) draggingDice.getParent()).getChildren().remove(draggingDice);
					DC.getDiceOnTableModel().removeDiceFromTable(draggingDice.getDiceModel());

					pane.getFieldModel().addDice(draggingDice.getDiceModel());

					e.setDropCompleted(true);
					draggingDice.getDiceModel().setMoved();

					draggingDice = null;
					calculatePoints();
				} else if (windowPattern1Model.diceOnWindow(draggingDice.getDiceModel())) {
					// ((Pane) draggingDice.getParent()).getChildren().remove(draggingDice);
					windowPattern1Model.removeDiceFromWindowPattern(draggingDice.getDiceModel());

					pane.getFieldModel().addDice(draggingDice.getDiceModel());

					e.setDropCompleted(true);
					draggingDice.getDiceModel().setMoved();

					draggingDice = null;
					calculatePoints();
				}
			}

			makeEveryBorderBlack();
		});

	}

	public boolean meetsNextToDiceRequirements(Field field, Dice dice) {
		// check if dice can be placed next to a other dice
		int column = getColumnAndRowOfField(field)[0];
		int row = getColumnAndRowOfField(field)[1];
		boolean accept = true;

		// check left
		try {
			if (windowPattern1Model.getFieldOfWindow(column - 1, row).hasDice()
					&& windowPattern1Model.getFieldOfWindow(column - 1, row).getDice() != dice) {
				if (windowPattern1Model.getFieldOfWindow(column - 1, row).getDice().getColor()
						.equals(draggingDice.getDiceModel().getColor())
						|| windowPattern1Model.getFieldOfWindow(column - 1, row).getDice().getEyes() == draggingDice
								.getDiceModel().getEyes()) {
					accept = false;
				}
			}

		} catch (Exception e2) {

		}

		// check right
		try {
			if (windowPattern1Model.getFieldOfWindow(column + 1, row).hasDice()
					&& windowPattern1Model.getFieldOfWindow(column + 1, row).getDice() != dice) {
				if (windowPattern1Model.getFieldOfWindow(column + 1, row).getDice().getColor()
						.equals(draggingDice.getDiceModel().getColor())
						|| windowPattern1Model.getFieldOfWindow(column + 1, row).getDice().getEyes() == draggingDice
								.getDiceModel().getEyes()) {
					accept = false;

				}
			}

		} catch (Exception e2) {

		}

		// check above
		try {
			if (windowPattern1Model.getFieldOfWindow(column, row - 1).hasDice()
					&& windowPattern1Model.getFieldOfWindow(column, row - 1).getDice() != dice) {
				if (windowPattern1Model.getFieldOfWindow(column, row - 1).getDice().getColor()
						.equals(draggingDice.getDiceModel().getColor())
						|| windowPattern1Model.getFieldOfWindow(column, row - 1).getDice().getEyes() == draggingDice
								.getDiceModel().getEyes()) {
					accept = false;

				}
			}

		} catch (Exception e2) {

		}

		// check bottom
		try {
			if (windowPattern1Model.getFieldOfWindow(column, row + 1).hasDice()
					&& windowPattern1Model.getFieldOfWindow(column, row + 1).getDice() != dice) {
				if (windowPattern1Model.getFieldOfWindow(column, row + 1).getDice().getColor()
						.equals(draggingDice.getDiceModel().getColor())
						|| windowPattern1Model.getFieldOfWindow(column, row + 1).getDice().getEyes() == draggingDice
								.getDiceModel().getEyes()) {
					accept = false;

				}
			}

		} catch (Exception e2) {

		}
		return accept;
	}

	public int calculatePoints() {
		int points = 0;
		for (int j = 1; j < 5; j++) {
			for (int i = 0; i < 5; i++) {
				if (windowPattern1Model.getFieldOfWindow(i, j).hasDice())
					points = points + windowPattern1Model.getFieldOfWindow(i, j).getDice().getEyes();
				GC.setPoints(points);
			}
		}
		return points;

	}

	private boolean isDiceNextToAnotherDice(Field field, Dice dice) {
		// Checks if dice is diagonally, vertically or horizontally next to another dice
		boolean isNextToAnotherDice = false;
		int column = getColumnAndRowOfField(field)[0];
		int row = getColumnAndRowOfField(field)[1];

		try {
			// top-left
			if (windowPattern1Model.getFieldOfWindow(column - 1, row - 1).hasDice()
					&& windowPattern1Model.getFieldOfWindow(column - 1, row - 1).getDice() != dice) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
		}

		try {
			// top
			if (windowPattern1Model.getFieldOfWindow(column, row - 1).hasDice()
					&& windowPattern1Model.getFieldOfWindow(column, row - 1).getDice() != dice) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
		}

		try {
			// top-right
			if (windowPattern1Model.getFieldOfWindow(column + 1, row - 1).hasDice()
					&& windowPattern1Model.getFieldOfWindow(column + 1, row - 1).getDice() != dice) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
		}

		try {
			// middle-left
			if (windowPattern1Model.getFieldOfWindow(column - 1, row).hasDice()
					&& windowPattern1Model.getFieldOfWindow(column - 1, row).getDice() != dice) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
		}

		try {
			// middle-right
			if (windowPattern1Model.getFieldOfWindow(column + 1, row).hasDice()
					&& windowPattern1Model.getFieldOfWindow(column + 1, row).getDice() != dice) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
		}

		try {
			// bottom-left
			if (windowPattern1Model.getFieldOfWindow(column - 1, row + 1).hasDice()
					&& windowPattern1Model.getFieldOfWindow(column - 1, row + 1).getDice() != dice) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
		}

		try {
			// bottom
			if (windowPattern1Model.getFieldOfWindow(column, row + 1).hasDice()
					&& windowPattern1Model.getFieldOfWindow(column, row + 1).getDice() != dice) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
		}

		try {
			// bottom-right
			if (windowPattern1Model.getFieldOfWindow(column + 1, row + 1).hasDice()
					&& windowPattern1Model.getFieldOfWindow(column + 1, row + 1).getDice() != dice) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
		}

		if (calculatePoints() == 0 && (row == 1 || row == 4 || column == 0 || column == 4)) {
			isNextToAnotherDice = true;
		}

		return isNextToAnotherDice;
	}

	public WindowPatternScreen getWindow1() {
		return window1;
	}

	public WindowPatternScreen getWindow2() {
		return window2;
	}

	public WindowPatternScreen getWindow3() {
		return window3;
	}

	public WindowPatternScreen getWindow4() {
		return window4;
	}

	public void setWindow1(WindowPattern windowModel) {
		for (int row = 1; row < 5; row++) {
			for (int column = 0; column < 5; column++) {
				window1.getWindowPatternModel().getFieldOfWindow(column, row).setColorAndEyes(
						windowModel.getFieldOfWindow(column, row).getColor(),
						windowModel.getFieldOfWindow(column, row).getEyes());
			}
		}

		calculateDifficulty(windowPattern1Model);
	}

	public void makeWindowsGray(WindowPattern windowModel) {

		for (int row = 1; row < 5; row++) {
			for (int column = 0; column < 5; column++) {
				windowModel.getFieldOfWindow(column, row).setColorAndEyes(Color.LIGHTGRAY, 0);
			}

			calculateDifficulty(windowModel);

		}
	}

	public void whichPlacementIsPossible(Dice dice, ArrayList<Field> fields) {
		for (int row = 1; row < 5; row++) {
			for (int column = 0; column < 5; column++) {
				if ((dice.getEyes() == window1.getWindowPatternModel().getFieldOfWindow(column, row).getEyes()
						|| window1.getWindowPatternModel().getFieldOfWindow(column, row).getEyes() == 0
						|| ignoreEyes == true)
						&& (dice.getColor() == window1.getWindowPatternModel().getFieldOfWindow(column, row).getColor()
								|| window1.getWindowPatternModel().getFieldOfWindow(column, row)
										.getColor() == Color.LIGHTGRAY
								|| ignoreColor == true)
						&& (dice.getMoved() == false || diceCanBeMoved == true)
						&& window1.getWindowPatternModel().getFieldOfWindow(column, row).hasDice() == false
						&& meetsNextToDiceRequirements(window1.getWindowPatternModel().getFieldOfWindow(column, row),
								dice) == true
						&& isDiceNextToAnotherDice(window1.getWindowPatternModel().getFieldOfWindow(column, row),
								dice) == true) {

					if ((windowPattern1Model.diceOnWindow(dice) == true
							&& (ignoreEyes == false || ignoreColor == false))
							|| (DC.getDiceOnTableModel().isDiceOnTable(draggingDice.getDiceModel()) == true)
									&& ignoreEyes == false && ignoreColor == false) {
						if (fields != null) {
							fields.add(windowPattern1Model.getFieldOfWindow(column, row));
						} else {
							window1.setCheat(column, row);
						}
					}

				}
			}
		}

	}

	public void bestPossiblePlace(Dice dice) {
		ArrayList<Field> allFields = new ArrayList<>();
		int highestPoints = 0;
		ArrayList<Field> allBestFields = new ArrayList<>();

		whichPlacementIsPossible(dice, allFields);

		try {
			if (allFields.size() != 0) {
				for (Field field : allFields) {
					int column = getColumnAndRowOfField(field)[0];
					int row = getColumnAndRowOfField(field)[1];
					int points = 0;
					// top
					try {
						if ((windowPattern1Model.getFieldOfWindow(column, row - 1).getColor() != dice.getColor()
								|| windowPattern1Model.getFieldOfWindow(column, row - 1).getColor() == Color.LIGHTGRAY)
								&& (windowPattern1Model.getFieldOfWindow(column, row - 1).getEyes() != dice.getEyes()
										|| windowPattern1Model.getFieldOfWindow(column, row - 1).getEyes() == 0)) {
							points++;
						} else {
							points = points - 2;
						}
					} catch (Exception e) {
						// TODO: handle exception
					}

					// middle-left
					try {
						if ((windowPattern1Model.getFieldOfWindow(column - 1, row).getColor() != dice.getColor()
								|| windowPattern1Model.getFieldOfWindow(column - 1, row).getColor() == Color.LIGHTGRAY)
								&& (windowPattern1Model.getFieldOfWindow(column - 1, row).getEyes() != dice.getEyes()
										|| windowPattern1Model.getFieldOfWindow(column - 1, row).getEyes() == 0)) {
							points++;
						} else {
							points = points - 2;
						}
					} catch (Exception e) {
						// TODO: handle exception
					}

					// middle-right
					try {
						if ((windowPattern1Model.getFieldOfWindow(column + 1, row).getColor() != dice.getColor()
								|| windowPattern1Model.getFieldOfWindow(column + 1, row).getColor() == Color.LIGHTGRAY)
								&& (windowPattern1Model.getFieldOfWindow(column + 1, row).getEyes() != dice.getEyes()
										|| windowPattern1Model.getFieldOfWindow(column + 1, row).getEyes() == 0)) {
							points++;
						} else {
							points = points - 2;
						}
					} catch (Exception e) {
						// TODO: handle exception
					}

					// bottom
					try {

						if ((windowPattern1Model.getFieldOfWindow(column, row + 1).getColor() != dice.getColor()
								|| windowPattern1Model.getFieldOfWindow(column, row + 1).getColor() == Color.LIGHTGRAY)
								&& (windowPattern1Model.getFieldOfWindow(column, row + 1).getEyes() != dice.getEyes()
										|| windowPattern1Model.getFieldOfWindow(column, row + 1).getEyes() == 0)) {
							points++;
						} else {
							points = points - 2;
						}

					} catch (Exception e) {
						// TODO: handle exception
					}

					if (field.getColor() != Color.LIGHTGRAY || field.getEyes() != 0) {
						points++;
					}

					if (points == highestPoints) {
						allBestFields.add(field);
					} else if (points > highestPoints) {
						allBestFields.clear();
						allBestFields.add(field);
						highestPoints = points;
					}
				}

				for (Field bestField : allBestFields) {
					window1.setCheat(bestField.getColumn(), bestField.getRow());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void makeEveryBorderBlack() {
		for (int row = 1; row < 5; row++) {
			for (int column = 0; column < 5; column++) {

				window1.setNormal(column, row);
			}
		}
	}

	public boolean getCheatAllPossible() {
		return cheatAllPossible;
	}

	public void setCheatAllPossible(boolean b) {
		cheatAllPossible = b;
	}

	public boolean getCheatBestChoice() {
		return cheatBestChoice;
	}

	public void setCheatBestChoice(boolean b) {
		cheatBestChoice = b;
	}

	public void setGameController(GameController GC) {
		this.GC = GC;
	}

	public void calculateDifficulty(WindowPattern windowPatternModel) {
		int difficulty = 0;
		for (int row = 1; row < 5; row++) {
			for (int column = 0; column < 5; column++) {
				if (windowPatternModel.getFieldOfWindow(column, row).getColor() != Color.LIGHTGRAY) {
					difficulty++;
				}

				if (windowPatternModel.getFieldOfWindow(column, row).getEyes() > 0
						&& windowPatternModel.getFieldOfWindow(column, row).getEyes() < 7) {
					difficulty++;
				}
			}
		}

		if (difficulty > 0 && difficulty <= 4) {
			difficulty = 1;
		} else if (difficulty > 4 && difficulty <= 8) {
			difficulty = 2;
		} else if (difficulty > 8 && difficulty <= 11) {
			difficulty = 3;
		} else if (difficulty > 11 && difficulty <= 14) {
			difficulty = 4;
		} else if (difficulty > 14 && difficulty <= 17) {
			difficulty = 5;
		} else if (difficulty > 17 && difficulty <= 20) {
			difficulty = 6;
		} else {
			difficulty = 0;
		}

		windowPatternModel.setDifficultyWindowPattern(difficulty);
	}

	public void setDiceController(DiceController DC) {
		this.DC = DC;
	}

	public void setStandardWindowPatern(WindowPattern windowModel) {
		// de row begint bij 1 aangezien de naam van de kaart/speler in row 0 staat
		int value = 24;
		switch (value) {
		case 1:
			// Bellesguard
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.LIGHTGRAY, 6);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.YELLOW, 0);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.LIGHTGRAY, 6);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.LIGHTGRAY, 4);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGRAY, 1);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.LIGHTGREEN, 0);

			windowModel.setDifficultyWindowPattern(3);
			break;
		case 2:
			// Symphony of Light
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGRAY, 1);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.LIGHTGRAY, 6);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.RED, 0);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.LIGHTGREEN, 0);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.setDifficultyWindowPattern(6);
			break;
		case 3:
			// Industria
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.LIGHTGRAY, 1);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.RED, 0);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGRAY, 6);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.LIGHTGRAY, 4);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.RED, 0);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.RED, 0);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGRAY, 1);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.RED, 0);

			windowModel.setDifficultyWindowPattern(5);
			break;
		case 4:
			// Firelight
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.LIGHTGRAY, 4);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.LIGHTGRAY, 1);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.LIGHTGRAY, 6);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.YELLOW, 0);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.RED, 0);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.RED, 0);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.LIGHTGRAY, 6);

			windowModel.setDifficultyWindowPattern(5);
			break;
		case 5:
			// Sun Catcher
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.YELLOW, 0);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.LIGHTGRAY, 4);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.RED, 0);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.LIGHTGREEN, 0);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.MAGENTA, 0);

			windowModel.setDifficultyWindowPattern(3);
			break;
		case 6:
			// Comitas
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGRAY, 6);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.LIGHTGRAY, 4);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.YELLOW, 0);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGRAY, 5);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.LIGHTGRAY, 1);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.setDifficultyWindowPattern(5);
			break;
		case 7:
			// Fractal Drops
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.LIGHTGRAY, 4);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGRAY, 6);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.RED, 0);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.RED, 0);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGRAY, 1);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.setDifficultyWindowPattern(3);
			break;
		case 8:
			// Lux Astram
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.LIGHTGRAY, 1);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.LIGHTGREEN, 0);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGRAY, 4);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.LIGHTGRAY, 6);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.LIGHTGREEN, 0);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.LIGHTGRAY, 1);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.LIGHTGREEN, 0);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.MAGENTA, 0);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.setDifficultyWindowPattern(5);
			break;
		case 9:
			// Aurora Sagradis
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.RED, 0);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.YELLOW, 0);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.LIGHTGRAY, 4);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGREEN, 0);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.LIGHTGRAY, 2);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.LIGHTGRAY, 1);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.LIGHTGRAY, 6);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.setDifficultyWindowPattern(4);
			break;
		case 10:
			// Firmitas
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.LIGHTGRAY, 6);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGRAY, 3);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.LIGHTGRAY, 1);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.LIGHTGRAY, 1);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.LIGHTGRAY, 4);

			windowModel.setDifficultyWindowPattern(5);
			break;
		case 11:
			// Water of Life
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.LIGHTGRAY, 6);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGRAY, 1);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.LIGHTGRAY, 4);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.RED, 0);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.LIGHTGREEN, 0);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.LIGHTGRAY, 6);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.MAGENTA, 0);

			windowModel.setDifficultyWindowPattern(6);
			break;
		case 12:
			// Luz Celestial
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.RED, 0);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.LIGHTGRAY, 4);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGREEN, 0);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.LIGHTGRAY, 3);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.LIGHTGRAY, 6);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.setDifficultyWindowPattern(3);
			break;
		case 13:
			// Bastllo
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.LIGHTGRAY, 6);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGRAY, 4);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.LIGHTGREEN, 0);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGRAY, 2);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.LIGHTGRAY, 1);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.LIGHTGRAY, 4);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.RED, 0);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.LIGHTGRAY, 3);

			windowModel.setDifficultyWindowPattern(5);
			break;
		case 14:
			// Virtus
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.LIGHTGRAY, 4);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGREEN, 0);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.LIGHTGRAY, 6);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGREEN, 0);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.LIGHTGRAY, 2);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.LIGHTGREEN, 0);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.LIGHTGRAY, 4);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.LIGHTGREEN, 0);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.LIGHTGRAY, 1);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.setDifficultyWindowPattern(5);
			break;
		case 15:
			// Via Lux
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.LIGHTGRAY, 6);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.LIGHTGRAY, 1);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.LIGHTGRAY, 2);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.RED, 0);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.LIGHTGRAY, 4);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.RED, 0);

			windowModel.setDifficultyWindowPattern(4);
			break;
		case 16:
			// Sun's Glory
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.LIGHTGRAY, 1);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGRAY, 4);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.LIGHTGRAY, 6);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGRAY, 3);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.LIGHTGRAY, 4);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.LIGHTGRAY, 1);

			windowModel.setDifficultyWindowPattern(6);
			break;
		case 17:
			// Shadow Thief
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.LIGHTGRAY, 6);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGRAY, 5);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.RED, 0);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.LIGHTGRAY, 6);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.RED, 0);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGRAY, 4);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.LIGHTGRAY, 3);

			windowModel.setDifficultyWindowPattern(5);
			break;
		case 18:
			// Chromatic Splendor
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.LIGHTGREEN, 0);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.LIGHTGRAY, 1);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.RED, 0);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.LIGHTGRAY, 1);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.LIGHTGRAY, 6);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.LIGHTGRAY, 4);

			windowModel.setDifficultyWindowPattern(4);
			break;
		case 19:
			// Ripples of Light
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.RED, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGRAY, 5);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGRAY, 4);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.CORNFLOWERBLUE, 0);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGRAY, 6);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.LIGHTGREEN, 0);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGRAY, 1);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.RED, 0);

			windowModel.setDifficultyWindowPattern(5);
			break;
		case 20:
			// Lux Mundi
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.LIGHTGRAY, 1);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.LIGHTGRAY, 1);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.LIGHTGREEN, 0);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.LIGHTGRAY, 2);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.LIGHTGRAY, 4);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.LIGHTGRAY, 6);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGREEN, 0);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGREEN, 0);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.setDifficultyWindowPattern(6);
			break;
		case 21:
			// Aurorea Magnificus
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.LIGHTGREEN, 0);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGRAY, 2);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.MAGENTA, 0);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.YELLOW, 0);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.LIGHTGRAY, 6);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.MAGENTA, 0);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.LIGHTGRAY, 1);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGREEN, 0);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.LIGHTGRAY, 4);

			windowModel.setDifficultyWindowPattern(5);
			break;
		case 22:
			// Kaleidoscopic Dream
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.YELLOW, 0);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGRAY, 1);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.LIGHTGREEN, 0);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.LIGHTGRAY, 4);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.RED, 0);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGREEN, 0);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.YELLOW, 0);

			windowModel.setDifficultyWindowPattern(4);
			break;
		case 23:
			// Gravitas
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.LIGHTGRAY, 1);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.LIGHTGRAY, 6);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.LIGHTGRAY, 4);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.LIGHTGRAY, 1);

			windowModel.setDifficultyWindowPattern(5);
			break;
		case 24:
			// Fulgor del Cielo
			windowModel.getFieldOfWindow(0, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 1).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(2, 1).setColorAndEyes(Color.RED, 0);
			windowModel.getFieldOfWindow(3, 1).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 1).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.getFieldOfWindow(0, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(1, 2).setColorAndEyes(Color.LIGHTGRAY, 4);
			windowModel.getFieldOfWindow(2, 2).setColorAndEyes(Color.LIGHTGRAY, 5);
			windowModel.getFieldOfWindow(3, 2).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(4, 2).setColorAndEyes(Color.CORNFLOWERBLUE, 0);

			windowModel.getFieldOfWindow(0, 3).setColorAndEyes(Color.CORNFLOWERBLUE, 0);
			windowModel.getFieldOfWindow(1, 3).setColorAndEyes(Color.LIGHTGRAY, 2);
			windowModel.getFieldOfWindow(2, 3).setColorAndEyes(Color.LIGHTGRAY, 0);
			windowModel.getFieldOfWindow(3, 3).setColorAndEyes(Color.RED, 0);
			windowModel.getFieldOfWindow(4, 3).setColorAndEyes(Color.LIGHTGRAY, 5);

			windowModel.getFieldOfWindow(0, 4).setColorAndEyes(Color.LIGHTGRAY, 6);
			windowModel.getFieldOfWindow(1, 4).setColorAndEyes(Color.RED, 0);
			windowModel.getFieldOfWindow(2, 4).setColorAndEyes(Color.LIGHTGRAY, 3);
			windowModel.getFieldOfWindow(3, 4).setColorAndEyes(Color.LIGHTGRAY, 1);
			windowModel.getFieldOfWindow(4, 4).setColorAndEyes(Color.LIGHTGRAY, 0);

			windowModel.setDifficultyWindowPattern(5);
			break;

		default:
			break;
		}

	}

	public void createGrayWindowPattern(int id, WindowPatternScreen windowScreen, WindowPattern windowModel) {
		for (int row = 1; row < 5; row++) {
			for (int column = 0; column < 5; column++) {
				Field fieldModel = new Field(column, row, Color.LIGHTGRAY, 0, id);
				FieldScreen fieldScreen = new FieldScreen(fieldModel, this);
				fieldModel.setEyes(0);
				addDropHandling(fieldScreen);
				windowScreen.add(fieldScreen, column, row);
				windowModel.addFieldToWindow(fieldModel);
			}
		}

		calculateDifficulty(windowModel);
	}

	public int[] getColumnAndRowOfField(Field field) {
		for (int row = 1; row < 5; row++) {
			for (int column = 0; column < 5; column++) {
				if (field.equals(windowPattern1Model.getFieldOfWindow(column, row))) {
					int[] values = { column, row };
					return values;
				}

			}
		}
		return null;

	}

	public void createTimer() {
		timer = new AnimationTimerEXT(5000) {
			@Override
			public void doAction() {
				// TODO Auto-generated method stub
				windowPattern1Model.updateAllFields();
				windowPattern1Model.updateAllDicesOnField();
			}
		};
		timer.start();
	}

}
