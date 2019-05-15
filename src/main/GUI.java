package main;

import controller.DatabaseController;
import controller.DiceController;
import controller.GameController;
import controller.WindowController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.WindowPattern;

public class GUI extends Application {
	private DatabaseController databaseController;
	private WindowController windowController;
	private DiceController diceController;
	private GameController gameController;
	

	void startup(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		databaseController = new DatabaseController();
		windowController = new WindowController(this, databaseController);
		diceController = new DiceController(this, windowController);
		gameController = new GameController(this, databaseController, windowController, diceController);
		
		stage.setScene(gameController);
		stage.setFullScreen(true);
		stage.show();
		
	}
	
	public void createGame(WindowPattern windowModel) {
		gameController.createGame(windowModel);
	}
	
	public void handleCheat(boolean allPossible, boolean bestChoice) {
		gameController.handleCheatGame(allPossible, bestChoice);
	}
	
	public void makeDices() {
		diceController.makeDices();
	}
}
