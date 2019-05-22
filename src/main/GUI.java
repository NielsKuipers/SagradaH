package main;


import controller.SetupScreenController;


import controller.ChatController;

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
	private DiceController diceController;
	private GameController gameController;
	private ChatController chatController;

	public void startup(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		DatabaseController databaseController = new DatabaseController();
	
		WindowController windowController = new WindowController(this, databaseController);
		diceController = new DiceController(this, windowController);
		chatController = new ChatController(this, databaseController);
        gameController = new GameController(this, databaseController, windowController, diceController, chatController);
		
//		 SetupScreenController SetupController = new SetupScreenController(stage, databaseController);
//		 EndScreenController EndController = new EndScreenController(stage, databaseController);
		// RoundScreenController RoundController = new RoundScreenController(stage, databaseController);
		
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
		gameController.handleRollDices();
	}

	public void sendMessage(String input){chatController.sendMessage(input);}
	
	public void handleFinishTurn() {
		gameController.handleFinishTurn();
	}

}
