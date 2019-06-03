package view;

import controller.WindowController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import main.GUI;
import model.DiceModel;
import model.DiceOnTableModel;

import java.util.ArrayList;

public class DiceOnTableScreen extends GridPane {
	private Button createNewDices;

	private WindowController WC;

	public DiceOnTableScreen(GUI gui, DiceOnTableModel diceOnTableModel, WindowController WC) {
		this.WC = WC;

		createNewDices = new Button("Gooi");
		createNewDices.setOnMouseClicked(e -> gui.makeDices());
		add(createNewDices, 2, 3, 5, 1);
		

		setHgap(10);
		setVgap(10);
		setAlignment(Pos.CENTER);
		setStyle("-fx-background-radius: 0 0 0 300;-fx-background-color: DEEPSKYBLUE;");

		diceOnTableModel.diceOnTableProperty().addListener(new MyDiceOnTableListener());
	}

	/**
	 * checks if arraylist has changed and draws all dices
	 */
	private class MyDiceOnTableListener implements ChangeListener<ArrayList<DiceModel>> {

		@Override
		public void changed(ObservableValue<? extends ArrayList<DiceModel>> observable, ArrayList<DiceModel> oldValue,
                            ArrayList<DiceModel> newValue) {
			// TODO Auto-generated method stu
			try {
				getChildren().clear();
				add(createNewDices, 2, 2, 5, 1);

				if (newValue != null) {
					int column = 0;
					int row = 0;
					boolean volgendeColumn = false;
					for (DiceModel newDice : newValue) {
						int eyes = newDice.getEyes();
						DiceScreen diceScreen = new DiceScreen(newDice);
						WC.dragButton(diceScreen);
						newDice.setEyes(0);
						newDice.setEyes(eyes);
						add(diceScreen, column, row);

						if (!volgendeColumn) {
							row++;
							volgendeColumn = true;
						} else {
							column++;
							row--;
							volgendeColumn = false;
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * clear all dices on table
	 */
	public void removeDicesScreen() {
		getChildren().clear();
		add(createNewDices, 2, 2, 5, 1);
	}

}
