package controller;

import java.util.ArrayList;
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
import view.DiceScreen;
import view.FieldScreen;
import view.WindowPatternScreen;

public class WindowController {

	WindowPatternScreen window1;
	WindowPatternScreen window2;
	WindowPatternScreen window3;
	WindowPatternScreen window4;

	GameController GC;

	ArrayList<Color> colorsField = new ArrayList<>();
	ArrayList<Integer> numbers = new ArrayList<>();
	Random r = new Random();

	boolean cheatMode = false;

	private final DataFormat diceFormat = new DataFormat("MyDice");

	private DiceScreen draggingDice;

	public WindowController() {

		window1 = new WindowPatternScreen("kaart 1");
		window2 = new WindowPatternScreen("kaart 2");
		window3 = new WindowPatternScreen("kaart 3");
		window4 = new WindowPatternScreen("kaart 4");

		addColorsField();
		addNumbersField();

		createRandomWindow(window1);
		createRandomWindow(window2);
		createRandomWindow(window3);
		createRandomWindow(window4);

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

	public void createRandomWindow(WindowPatternScreen grid) {

		// all rows
		for (int j = 1; j < 5; j++) {
			// first row
			if (j == 1) {
				// every field in that row
				for (int i = 0; i < 5; i++) {
					// check color of field left
					int highColor = colorsField.size();
					for (int z = 0; z < colorsField.size(); z++) {
						if (i > 0 && grid.getNode(i - 1, j).getColor().equals(colorsField.get(z).toString())) {
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
						if (i > 0 && grid.getNode(i - 1, j).getValue() == numbers.get(k)) {
							numbers.remove(k);
							highNumber--;
						}
					}

					// generate random numbers
					int resultColor = r.nextInt(highColor);
					int resultNumber = r.nextInt(highNumber);

					// check if field is gray, than it has no eyes
					if (colorsField.get(resultColor).equals(Color.LIGHTGRAY)) {
						FieldScreen fieldScreen = new FieldScreen(colorsField.get(resultColor),
								numbers.get(resultNumber));
						addDropHandling(fieldScreen);
						grid.add(fieldScreen, i, j);
					} else {
						FieldScreen fieldScreen = new FieldScreen(colorsField.get(resultColor), 0);
						addDropHandling(fieldScreen);
						grid.add(fieldScreen, i, j);
					}

					addColorsField();
					addNumbersField();

				}

			}

			// row 2
			else if (j > 1) {
				// all fields in that row
				for (int i = 0; i < 5; i++) {
					int highColor = colorsField.size();
					// every color above
					for (int z = 0; z < colorsField.size(); z++) {
						if (grid.getNode(i, j - 1).getColor().equals(colorsField.get(z).toString())) {
							// gray fields can be placed next to each other
							if (!colorsField.get(z).equals(Color.LIGHTGRAY)) {
								colorsField.remove(z);
								highColor--;
							}
						}
					}
					// every color left
					for (int z = 0; z < colorsField.size(); z++) {
						if (i > 0 && grid.getNode(i - 1, j).getColor().equals(colorsField.get(z).toString())) {
							if (!colorsField.get(z).equals(Color.LIGHTGRAY)) {
								colorsField.remove(z);
								highColor--;
							}
						}
					}

					int highNumber = numbers.size();
					// check eyes above
					for (int k = 0; k < numbers.size(); k++) {
						if (grid.getNode(i, j - 1).getValue() == numbers.get(k)) {
							numbers.remove(k);
							highNumber--;
						}
					}

					// check eyes left
					for (int k = 0; k < numbers.size(); k++) {
						if (i > 0 && grid.getNode(i - 1, j).getValue() == numbers.get(k)) {
							numbers.remove(k);
							highNumber--;
						}
					}

					Random r = new Random();
					int resultColor = r.nextInt(highColor);
					int resultNumber = r.nextInt(highNumber);

					// check if field is gray, than it has no eyes
					if (colorsField.get(resultColor).equals(Color.LIGHTGRAY)) {
						FieldScreen fieldScreen = new FieldScreen(colorsField.get(resultColor), numbers.get(resultNumber));
						addDropHandling(fieldScreen);
						grid.add(fieldScreen, i, j);
					} else {
						FieldScreen fieldScreen = new FieldScreen(colorsField.get(resultColor),
								0);
						addDropHandling(fieldScreen);
						grid.add(fieldScreen, i, j);
					}

					// fill the array again
					addColorsField();
					addNumbersField();
				}
			}

		}

		calculateDifficulty(grid);

		grid.setHgap(2); // horizontal gap in pixels
		grid.setVgap(2); // vertical gap in pixels

		grid.setPadding(new Insets(20, 20, 20, 20));

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
				if (cheatMode == true) {
					whichPlacementIsPossible(draggingDice);
				}
			}
		});

		pane.setOnDragDropped(e -> {
			Dragboard db = e.getDragboard();

			// check if dice meets all the requirements
			if (db.hasContent(diceFormat) && (draggingDice.getValue() == pane.getValue() || pane.getValue() == 0)
					&& (draggingDice.getColor() == pane.getColorNotString()
							|| pane.getColorNotString().equals(Color.LIGHTGRAY))
					&& draggingDice.getMoved() == false && pane.hasDice() == false
					&& meetsNextToDiceRequirements(pane) == true && isDiceNextToAnotherDice(pane) == true) {

				((Pane) draggingDice.getParent()).getChildren().remove(draggingDice);
				pane.getChildren().add(draggingDice);
				e.setDropCompleted(true);
				draggingDice.setMoved();
				pane.addDice(draggingDice);
				draggingDice.setBorder(
						new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));
				draggingDice = null;
				calculatePoints();
			}

			makeEveryBorderBlack();
		});

	}

	public boolean meetsNextToDiceRequirements(Pane pane) {
		// check if dice can be placed next to a other dice
		int column = -1;
		int row = -1;
		boolean accept = true;
		for (Node node : window1.getChildren()) {
			// get row and column of field you want to place a dice in
			if (pane == node) {
				column = window1.getColumnIndex(node);
				row = window1.getRowIndex(node);
				break;
			}
		}

		// check left
		try {
			if (window1.getNode(column - 1, row).hasDice()) {
				if (window1.getNode(column - 1, row).getDice().getColor().equals(draggingDice.getColor())
						|| window1.getNode(column - 1, row).getDice().getValue() == draggingDice.getValue()) {
					accept = false;
				}
			}

		} catch (Exception e2) {

		}

		// check right
		try {
			if (window1.getNode(column + 1, row).hasDice()) {
				if (window1.getNode(column + 1, row).getDice().getColor().equals(draggingDice.getColor())
						|| window1.getNode(column + 1, row).getDice().getValue() == draggingDice.getValue()) {
					accept = false;

				}
			}

		} catch (Exception e2) {

		}

		// check above
		try {
			if (window1.getNode(column, row - 1).hasDice()) {
				if (window1.getNode(column, row - 1).getDice().getColor().equals(draggingDice.getColor())
						|| window1.getNode(column, row - 1).getDice().getValue() == draggingDice.getValue()) {
					accept = false;

				}
			}

		} catch (Exception e2) {

		}

		// check bottom
		try {
			if (window1.getNode(column, row + 1).hasDice()) {
				if (window1.getNode(column, row + 1).getDice().getColor().equals(draggingDice.getColor())
						|| window1.getNode(column, row + 1).getDice().getValue() == draggingDice.getValue()) {
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
				if (window1.getNode(i, j).hasDice())
					points = points + window1.getNode(i, j).getDice().getValue();
				GC.setPoints(points);
			}
		}
		return points;

	}

	private boolean isDiceNextToAnotherDice(Pane pane) {
		// Checks if dice is diagonally, vertically or horizontally next to another dice
		boolean isNextToAnotherDice = false;
		int column = -1;
		int row = -1;

		for (Node node : window1.getChildren()) {
			// get row and column of field you want to place a dice in
			if (pane == node) {
				column = window1.getColumnIndex(node);
				row = window1.getRowIndex(node);
				break;
			}
		}

		try {
			// top-left
			if (window1.getNode(column - 1, row - 1).hasDice()) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
		}

		try {
			// top
			if (window1.getNode(column, row - 1).hasDice()) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
		}

		try {
			// top-right
			if (window1.getNode(column + 1, row - 1).hasDice()) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
		}

		try {
			// middle-left
			if (window1.getNode(column - 1, row).hasDice()) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
		}

		try {
			// middle-right
			if (window1.getNode(column + 1, row).hasDice()) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
		}

		try {
			// bottom-left
			if (window1.getNode(column - 1, row + 1).hasDice()) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
		}

		try {
			// bottom
			if (window1.getNode(column, row + 1).hasDice()) {
				isNextToAnotherDice = true;
			}
		} catch (Exception e) {
		}

		try {
			// bottom-right
			if (window1.getNode(column + 1, row + 1).hasDice()) {
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

	public void setWindow1(WindowPatternScreen ws) {
		for (int j = 1; j < 5; j++) {
			for (int i = 0; i < 5; i++) {
				window1.add(ws.getNode(i, j), i, j);
			}
		}

		calculateDifficulty(window1);
	}

	public void makeWindowsGray() {

		for (int j = 1; j < 5; j++) {
			for (int i = 0; i < 5; i++) {
				window2.add(new FieldScreen(Color.LIGHTGRAY, 0), i, j);
			}
		}

		for (int j = 1; j < 5; j++) {
			for (int i = 0; i < 5; i++) {
				window3.add(new FieldScreen(Color.LIGHTGRAY, 0), i, j);
			}
		}

		for (int j = 1; j < 5; j++) {
			for (int i = 0; i < 5; i++) {
				window4.add(new FieldScreen(Color.LIGHTGRAY, 0), i, j);
			}
		}

		calculateDifficulty(window2);
		calculateDifficulty(window3);
		calculateDifficulty(window4);

	}

	public void whichPlacementIsPossible(DiceScreen dice) {
		for (int j = 1; j < 5; j++) {
			for (int i = 0; i < 5; i++) {
				if ((draggingDice.getValue() == window1.getNode(i, j).getValue()
						|| window1.getNode(i, j).getValue() == 0)
						&& (draggingDice.getColor() == window1.getNode(i, j).getColorNotString()
								|| window1.getNode(i, j).getColorNotString().equals(Color.LIGHTGRAY))
						&& draggingDice.getMoved() == false && window1.getNode(i, j).hasDice() == false
						&& meetsNextToDiceRequirements(window1.getNode(i, j)) == true
						&& isDiceNextToAnotherDice(window1.getNode(i, j)) == true) {

					window1.getNode(i, j).cheatBorder();

				}
			}
		}

	}

	public void makeEveryBorderBlack() {
		for (int j = 1; j < 5; j++) {
			for (int i = 0; i < 5; i++) {

				window1.getNode(i, j).normalBorder();
			}
		}
	}

	public boolean getCheat() {
		return cheatMode;
	}

	public void setCheatMode(boolean b) {
		cheatMode = b;
	}

	public void setGameController(GameController GC) {
		this.GC = GC;
	}

	public void calculateDifficulty(WindowPatternScreen wp) {
		int difficulty = 0;
		for (int j = 1; j < 5; j++) {
			for (int i = 0; i < 5; i++) {
				if (!wp.getNode(i, j).getColorNotString().equals(Color.LIGHTGRAY)) {
					difficulty++;
				}

				if (wp.getNode(i, j).getValue() > 0 && wp.getNode(i, j).getValue() < 7) {
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

		wp.setDifficulty(difficulty);
	}

}
