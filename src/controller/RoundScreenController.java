package controller;

import model.Game;
import timer.AnimationTimerEXT;
import view.DiceScreen;
import view.FieldScreen;
import view.RoundScreen;
import view.RoundScreen.CustomStackPane;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.GUI;


public class RoundScreenController {

	private RoundScreen roundScreen;
	private Game gameModel;
	private WindowController windowController;

	public RoundScreenController(Stage stage, DatabaseController dataController, GUI gui,
			WindowController windowController, GameController gameController) {
		this.windowController = windowController;
		roundScreen = new RoundScreen(this, gui);
		gameModel = gameController.getGameModel();
		windowController.setRoundController(this);

		addDice();
		createTimer();
		
	}

	
	/**
	 * put dices on roundboard 
	 */
	private void addDice() {
		for (int round = 1; round < 11; round++) {
			int row = 2;
			ArrayList<ArrayList<Object>> result = gameModel.getDicesOnRoundBoard(round);

			for (ArrayList<Object> objects : result) {
				roundScreen.addDice(round, (int) objects.get(1), (String) objects.get(0), row, (int) objects.get(2));
				row++;
			}
		}
	}

	
	/**
	 * Delete and reput dices
	 */
	public void refreshDice() {
		roundScreen.clearBoard();
		addDice();
	}

	
	/** remove dice from roundboard
	 * @param diceID
	 * @param color
	 */
	public void removeDice(int diceID, Color color) {
		String colorText = getColorTranslation(color);
		gameModel.removeDice(diceID, colorText);
	}
	


	
	/** translates hexadecimal color into dutch colors
	 * @param color
	 * @return translated color
	 */
	private String getColorTranslation(Color color) {
		String colortext = color.toString();

		switch (colortext) {
		case "0x0000ffff":
			return "blauw";
		case "0xffff00ff":
			return "geel";
		case "0xff0000ff":
			return "rood";
		case "0x800080ff":
			return "paars";
		case "0x008000ff":
			return "groen";
		default:
			return "";
		}
	}

	
	private void createTimer() {
		AnimationTimerEXT timer = new AnimationTimerEXT(10000) {
			@Override
			public void doAction() {
				refreshDice();
			}
		};
		timer.start();
	}

	public RoundScreen getRoundScreen() {
		return roundScreen;
	}

	
	/** handles toolcard 12 actions
	 * @param diceNumberWindow
	 * @param diceColorWindow
	 * @param column
	 * @param row
	 */
	public void clickDiceOnRoundTrack(int diceNumberWindow, String diceColorWindow, int column, int row) {
		for (Node node : roundScreen.getChildren()) {
			if (node instanceof CustomStackPane) {
				CustomStackPane result = (CustomStackPane) node;
				for (Node node2 : result.getChildren()) {
					if (node2 instanceof DiceScreen) {
						DiceScreen dice = (DiceScreen) node2;
						dice.setOnMouseClicked(e -> handleClickedOnDiceOnRoundTrack(diceNumberWindow, diceColorWindow,
								dice.getIdRoundTrack(), dice.getColorRoundTrack(), column, row));
					}
				}

			}
		}

	}

	
	/** switch dice on window with dice on roundboard
	 * @param diceNumberWindow
	 * @param diceColorWindow
	 * @param diceNumberRoundTrack
	 * @param diceColorRoundTrack
	 * @param column
	 * @param row
	 */
	public void handleClickedOnDiceOnRoundTrack(int diceNumberWindow, String diceColorWindow, int diceNumberRoundTrack,
			Color diceColorRoundTrack, int column, int row) {
		for (Node node : roundScreen.getChildren()) {
			if (node instanceof DiceScreen) {
				DiceScreen dice = (DiceScreen) node;
				dice.setOnMouseClicked(null);
			}
		}
		
		int round = gameModel.getRoundTrackOfDice(diceNumberRoundTrack, getColorTranslation(diceColorRoundTrack));
		
		removeDice(diceNumberRoundTrack, diceColorRoundTrack);
		windowController.getGameController().getGameModel().getPlayer(0).removeDiceOnWindowPattern(diceNumberWindow,diceColorWindow);
		
		gameModel.addDiceToRoundTrack(diceNumberWindow, diceColorWindow, round);
		windowController.getGameController().getGameModel().getPlayer(0).setDiceOnWindowPattern(column,
				row, diceNumberRoundTrack, getColorTranslation(diceColorRoundTrack));
		
		windowController.getGameController().getGameModel().selectWholeGame();
		refreshDice();
		
		windowController.getGameController().startTimer();
	}

}
