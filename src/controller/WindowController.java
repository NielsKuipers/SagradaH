package controller;

import javafx.beans.binding.SetBinding;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import main.GUI;
import model.Dice;
import model.Field;
import model.WindowPattern;
import timer.AnimationTimerEXT;
import view.DiceScreen;
import view.FieldScreen;
import view.WindowPatternScreen;

import java.util.ArrayList;
import java.util.Random;

public class WindowController {

	private WindowPatternScreen window1;
	private WindowPatternScreen window2;
	private WindowPatternScreen window3;
	private WindowPatternScreen window4;
	private WindowPatternScreen window5;

	private WindowPattern windowPattern1Model;
	private WindowPattern windowPattern2Model;
	private WindowPattern windowPattern3Model;
	private WindowPattern windowPattern4Model;
	private WindowPattern windowModel;

	private GameController GC;
	private DiceController DC;
	private RoundScreenController RC;

	private GUI gui;

	private ArrayList<Color> colorsField = new ArrayList<>();
	private ArrayList<Integer> numbers = new ArrayList<>();
	private Random r = new Random();

	private boolean cheatAllPossible = false;
	private boolean cheatBestChoice = false;

	private final DataFormat diceFormat = new DataFormat("MyDice");

	private DiceScreen draggingDice;

	private boolean diceCanBeMoved = false;
	private boolean ignoreEyes = false;
	private boolean ignoreColor = false;
	private boolean ignoreNextToDice = false;
	private boolean movedADice = false;

	private boolean extraTurn = false;
	private boolean skipSecondTurn = false;

	private boolean extraTurnSameColorRoundtrack = false;
	private boolean canOnlyMoveDiceWithSameColorAsDIceOnRoundTrack = false;

	private int dicesChangedByTC = 0;

	private int dicesChangedPlace = 0;

	public WindowController(GUI gui, DatabaseController databaseController) {
		this.gui = gui;

		windowPattern1Model = new WindowPattern(databaseController.getWindowPatternQuerie());
		windowPattern2Model = new WindowPattern(databaseController.getWindowPatternQuerie());
		windowPattern3Model = new WindowPattern(databaseController.getWindowPatternQuerie());
		windowPattern4Model = new WindowPattern(databaseController.getWindowPatternQuerie());
		windowModel = new WindowPattern(databaseController.getWindowPatternQuerie());

		window1 = new WindowPatternScreen("kaart 1", windowPattern1Model, "WHITE");
		window2 = new WindowPatternScreen("kaart 2", windowPattern2Model, "WHITE");
		window3 = new WindowPatternScreen("kaart 3", windowPattern3Model, "WHITE");
		window4 = new WindowPatternScreen("kaart 4", windowPattern4Model, "WHITE");
		window5 = new WindowPatternScreen("GEBRUIKT VOOR RANDOM WINDOW", windowModel, "WHITE");

		addColorsField();
		addNumbersField();

		createGrayWindowPattern(1, window1, windowPattern1Model);
		createGrayWindowPattern(2, window2, windowPattern2Model);
		createGrayWindowPattern(3, window3, windowPattern3Model);
		createGrayWindowPattern(4, window4, windowPattern4Model);
		createGrayWindowPattern(5, window5, windowModel);

	}

	private void addColorsField() {
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

	private void addNumbersField() {
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

	public void buyTC4() {
		diceCanBeMoved = true;
	}

	public void buyTC3() {
		diceCanBeMoved = true;
		ignoreEyes = true;
	}

	public void buyTC2() {
		diceCanBeMoved = true;
		ignoreColor = true;
	}

	public void buyTC9() {
		ignoreNextToDice = true;
	}

	public void changedDiceBoard() {
		dicesChangedByTC++;

		if (dicesChangedByTC > 0) {
			diceCanBeMoved = false;
			ignoreEyes = false;
			ignoreColor = false;
			ignoreNextToDice = false;
			dicesChangedByTC = 0;
		}
	}

	

	public WindowPattern createRandomWindow() {
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
			else {
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
		return windowModel;
	}

	// pick up a dice
	public void dragButton(DiceScreen b) {
		b.setOnDragDetected(e -> {
			if (GC.getGameModel().getPlayer(0).selectCurrentPlayer()) {
				Dragboard db = b.startDragAndDrop(TransferMode.MOVE);
				db.setDragView(b.snapshot(null, null));
				ClipboardContent cc = new ClipboardContent();
				cc.put(diceFormat, " ");
				db.setContent(cc);
				draggingDice = b;
				makeEveryBorderBlack();
			}
		});

	}

	private void addDropHandling(FieldScreen pane) {
		pane.setOnDragOver(e -> {
			Dragboard db = e.getDragboard();
			// check if you have a dice and you want to place it on your own board
			if (db.hasContent(diceFormat) && draggingDice != null && pane.getParent() == window1) {
				e.acceptTransferModes(TransferMode.MOVE);
				if (cheatAllPossible && !cheatBestChoice) {
					whichPlacementIsPossible(draggingDice.getDiceModel(), null);
				} else if (!cheatAllPossible && cheatBestChoice) {
					bestPossiblePlace(draggingDice.getDiceModel());
				}
			}
		});

		pane.setOnDragDropped(e -> {
			Dragboard db = e.getDragboard();
			// check if dice meets all the requirements
			if (db.hasContent(diceFormat)
					&& (draggingDice.getDiceModel().getEyes() == pane.getFieldModel().getEyes()
							|| pane.getFieldModel().getEyes() == 0 || ignoreEyes)
					&& (draggingDice.getDiceModel().getColor() == pane.getFieldModel().getColor()

							|| pane.getFieldModel().getColor() == Color.LIGHTGRAY || ignoreColor)
					&& (DC.getDiceOnTableModel().isDiceOnTable(draggingDice.getDiceModel()) || diceCanBeMoved)
					&& !pane.getFieldModel().hasDice()
					&& meetsNextToDiceRequirements(pane.getFieldModel(), draggingDice.getDiceModel()) || ignoreNextToDice
					&& isDiceNextToAnotherDice(pane.getFieldModel(), draggingDice.getDiceModel()) || ignoreNextToDice) {

				if (DC.getDiceOnTableModel().isDiceOnTable(draggingDice.getDiceModel()) && !ignoreEyes
						&& !ignoreColor && GC.getGameModel().canPlayerPlaceADiceInThisRoundFromTheTable()) {
					
					DC.getDiceOnTableModel().removeDiceFromTable(draggingDice.getDiceModel());
					changedDiceBoard();
					pane.getFieldModel().addDice(draggingDice.getDiceModel());

					GC.getGameModel().getPlayer(0).setDiceOnWindowPatternAndGiveFirstTurn(
							(pane.getFieldModel().getColumn() + 1), pane.getFieldModel().getRow(),
							draggingDice.getDiceModel().getDiceNumber(),
							draggingDice.getDiceModel().getColorForQuerie(), GC.getGameModel().getInFirstTurn(),
							GC.getGameModel().getGameID());

					e.setDropCompleted(true);
					if (!extraTurn) {
						movedADice = true;
					} else {
						movedADice = false;
						extraTurn = false;
						skipSecondTurn = true;
					}

					draggingDice = null;
					calculatePoints();

				} else if (windowPattern1Model.diceOnWindow(draggingDice.getDiceModel())) {
					if ((canOnlyMoveDiceWithSameColorAsDIceOnRoundTrack && GC.getGameModel()
							.checkIfSameColorDiceIsOnRoundTrack(draggingDice.getDiceModel().getColor()))
							|| !canOnlyMoveDiceWithSameColorAsDIceOnRoundTrack) {
						windowPattern1Model.removeDiceFromWindowPattern(draggingDice.getDiceModel());
						
						pane.getFieldModel().addDice(draggingDice.getDiceModel());
						 changedDiceBoard();

						GC.getGameModel().getPlayer(0).removeDiceOnWindowPattern(
								draggingDice.getDiceModel().getDiceNumber(),
								draggingDice.getDiceModel().getColorForQuerie());

						GC.getGameModel().getPlayer(0).setDiceOnWindowPattern((pane.getFieldModel().getColumn() + 1),
								pane.getFieldModel().getRow(), draggingDice.getDiceModel().getDiceNumber(),
								draggingDice.getDiceModel().getColorForQuerie());

						e.setDropCompleted(true);
						if (!extraTurnSameColorRoundtrack) {
							movedADice = true;
						} else {
							movedADice = false;
							extraTurnSameColorRoundtrack = false;
						}
					}
					draggingDice = null;
					calculatePoints();
				}
			}

			makeEveryBorderBlack();
		});

	}

	private boolean meetsNextToDiceRequirements(Field field, Dice dice) {
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
			// e2.printStackTrace();
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
			// e2.printStackTrace();
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
			// e2.printStackTrace();
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
			// e2.printStackTrace();
		}
		return accept;
	}

	private int calculatePoints() {
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
			// e.printStackTrace();
		}

		try {
			// top
			if (windowPattern1Model.getFieldOfWindow(column, row - 1).hasDice()
					&& windowPattern1Model.getFieldOfWindow(column, row - 1).getDice() != dice) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}

		try {
			// top-right
			if (windowPattern1Model.getFieldOfWindow(column + 1, row - 1).hasDice()
					&& windowPattern1Model.getFieldOfWindow(column + 1, row - 1).getDice() != dice) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}

		try {
			// middle-left
			if (windowPattern1Model.getFieldOfWindow(column - 1, row).hasDice()
					&& windowPattern1Model.getFieldOfWindow(column - 1, row).getDice() != dice) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}

		try {
			// middle-right
			if (windowPattern1Model.getFieldOfWindow(column + 1, row).hasDice()
					&& windowPattern1Model.getFieldOfWindow(column + 1, row).getDice() != dice) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}

		try {
			// bottom-left
			if (windowPattern1Model.getFieldOfWindow(column - 1, row + 1).hasDice()
					&& windowPattern1Model.getFieldOfWindow(column - 1, row + 1).getDice() != dice) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}

		try {
			// bottom
			if (windowPattern1Model.getFieldOfWindow(column, row + 1).hasDice()
					&& windowPattern1Model.getFieldOfWindow(column, row + 1).getDice() != dice) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}

		try {
			// bottom-right
			if (windowPattern1Model.getFieldOfWindow(column + 1, row + 1).hasDice()
					&& windowPattern1Model.getFieldOfWindow(column + 1, row + 1).getDice() != dice) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
			// e.printStackTrace();
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

	public void makeWindowsGray(WindowPattern windowModel) {

		for (int row = 1; row < 5; row++) {
			for (int column = 0; column < 5; column++) {
				windowModel.getFieldOfWindow(column, row).setColorAndEyes(Color.LIGHTGRAY, 0);
			}

		}
	}

	private void whichPlacementIsPossible(Dice dice, ArrayList<Field> fields) {
		for (int row = 1; row < 5; row++) {
			for (int column = 0; column < 5; column++) {
				if ((dice.getEyes() == window1.getWindowPatternModel().getFieldOfWindow(column, row).getEyes()
						|| window1.getWindowPatternModel().getFieldOfWindow(column, row).getEyes() == 0 || ignoreEyes)
						&& (dice.getColor() == window1.getWindowPatternModel().getFieldOfWindow(column, row).getColor()
								|| window1.getWindowPatternModel().getFieldOfWindow(column, row)
										.getColor() == Color.LIGHTGRAY
								|| ignoreColor)
						&& (DC.getDiceOnTableModel().isDiceOnTable(draggingDice.getDiceModel()) || diceCanBeMoved)
						&& !window1.getWindowPatternModel().getFieldOfWindow(column, row).hasDice()
						&& meetsNextToDiceRequirements(window1.getWindowPatternModel().getFieldOfWindow(column, row),
								dice)
						&& isDiceNextToAnotherDice(window1.getWindowPatternModel().getFieldOfWindow(column, row),
								dice)) {

					if ((windowPattern1Model.diceOnWindow(dice) && (!ignoreEyes || !ignoreColor))
							|| (DC.getDiceOnTableModel().isDiceOnTable(draggingDice.getDiceModel())) && !ignoreEyes
									&& !ignoreColor) {
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

	private void bestPossiblePlace(Dice dice) {
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
						// e.printStackTrace();
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
						// e.printStackTrace();
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
						// e.printStackTrace();
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
						// e.printStackTrace();
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
			e.printStackTrace();
		}
	}

	private void makeEveryBorderBlack() {
		for (int row = 1; row < 5; row++) {
			for (int column = 0; column < 5; column++) {

				window1.setNormal(column, row);
			}
		}
	}

	void setCheatAllPossible(boolean b) {
		cheatAllPossible = b;
	}

	void setCheatBestChoice(boolean b) {
		cheatBestChoice = b;
	}

	void setGameController(GameController GC) {
		this.GC = GC;
	}

	public int calculateDifficulty(WindowPattern windowPatternModel) {
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

		return difficulty;
	}

	void setDiceController(DiceController DC) {
		this.DC = DC;
	}

	public String getDifficulty() {
		return Integer.toString(windowPattern1Model.getDifficulty());
	}

	private void createGrayWindowPattern(int id, WindowPatternScreen windowScreen, WindowPattern windowModel) {
		for (int row = 1; row < 5; row++) {
			for (int column = 0; column < 5; column++) {
				Field fieldModel = new Field(column, row, Color.LIGHTGRAY);
				FieldScreen fieldScreen = new FieldScreen(fieldModel, this);
				fieldModel.setEyes(0);
				addDropHandling(fieldScreen);
				windowScreen.add(fieldScreen, column, row);
				windowModel.addFieldToWindow(fieldModel);
			}
		}

		calculateDifficulty(windowModel);
	}

	private int[] getColumnAndRowOfField(Field field) {
		for (int row = 1; row < 5; row++) {
			for (int column = 0; column < 5; column++) {
				if (field.equals(windowPattern1Model.getFieldOfWindow(column, row))) {
					return new int[] { column, row };
				}

			}
		}
		return null;
	}

	public void selectDiceOnWindow() {
		GC.stopTimer();
		for (Node node : window1.getChildren()) {
			if (node instanceof FieldScreen) {
				FieldScreen result = (FieldScreen) node;
				for (Node node2 : result.getChildren()) {
					if (node2 instanceof DiceScreen) {
						DiceScreen dice = (DiceScreen) node2;
						dice.setGlowBorder();
						dice.setOnMouseClicked(e -> switchDiceWithRoundTrack(dice,
								(result.getFieldModel().getColumn() + 1), result.getFieldModel().getRow()));

					}
				}
			}
		}
	}

	private void setDiceWhiteBorder() {
		for (Node node : window1.getChildren()) {
			if (node instanceof FieldScreen) {
				FieldScreen result = (FieldScreen) node;
				for (Node node2 : result.getChildren()) {
					if (node2 instanceof DiceScreen) {
						DiceScreen dice = (DiceScreen) node2;
						dice.makeBorderWhite();
						dice.setOnMouseClicked(null);
					}
				}
			}
		}

	}

	private void switchDiceWithRoundTrack(DiceScreen dice, int column, int row) {
		GC.stopTimer();
		setDiceWhiteBorder();
		dice.setGlowBorder();
		gui.handleGoToRoundTrack();
		int diceNumberWindow = dice.getDiceModel().getDiceNumber();
		String diceColorWindow = dice.getDiceModel().getColorForQuerie();
		RC.clickDiceOnRoundTrack(diceNumberWindow, diceColorWindow, column, row);

		// cant go back to gameScreen untill dice ont roundtrack clicked

	}

	private boolean getIsADiceMoved() {
		return movedADice;
	}

	void setMovedToFalse() {
		movedADice = false;
	}

	public boolean skipSecondTurn() {
		return skipSecondTurn;
	}

	public void setSkipSecondTurnFalse() {
		skipSecondTurn = false;
	}

	public void setExtraTurnTrue() {
		extraTurn = true;
	}

	public void setCanOnlyMoveDiceWithSameColorAsDIceOnRoundTrackFalse() {
		canOnlyMoveDiceWithSameColorAsDIceOnRoundTrack = false;
	}

	public void setCanOnlyMoveDiceWithSameColorAsDIceOnRoundTrackTrue() {
		canOnlyMoveDiceWithSameColorAsDIceOnRoundTrack = true;
	}

	public void setExtraTurnSameColorRoundtrackTrue() {
		extraTurnSameColorRoundtrack = true;
	}

	public void setDiceCanBeMovedTrue() {
		diceCanBeMoved = true;
	}

	public void setDiceCanBeMovedFalse() {
		diceCanBeMoved = false;
	}

	public void setRoundController(RoundScreenController RC) {
		this.RC = RC;
	}

	public GameController getGameController() {
		return GC;
	}
}
