package main;

import controller.SetupScreenController;
import controller.DatabaseController;
import controller.DiceController;
import controller.EndScreenController;
import controller.GameController;
import controller.RoundScreenController;
import controller.WindowController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.WindowPattern;
import view.WindowPatternScreen;

public class GUI extends Application {
	DatabaseController databaseController;
	WindowController windowController;
	DiceController diceController;
	GameController gameController;
	

	public void startup(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		databaseController = new DatabaseController();
		windowController = new WindowController(this, databaseController);
		diceController = new DiceController(this, windowController);
		gameController = new GameController(this, windowController, diceController);
		
		// SetupScreenController SetupController = new SetupScreenController(stage, databaseController);
		// EndScreenController EndController = new EndScreenController(stage);
		 RoundScreenController RoundController = new RoundScreenController(stage, databaseController);
		
		//stage.setScene(gameController);
		
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
