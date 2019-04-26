package main;

import controller.ConnectionController;
import controller.DiceController;
import controller.GameController;
import controller.WindowController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.WindowPatternScreen;

public class GUI extends Application {
	ConnectionController sagradaBase;
	WindowController windowController;
	DiceController diceController;
	GameController gameController;
	

	public void startup(String[] args) {
		launch(args);
		
		
	}
	
	public void start(Stage stage) {
		sagradaBase = new ConnectionController();
		windowController = new WindowController(this, sagradaBase.getConnection());
		diceController = new DiceController(this, sagradaBase.getConnection(), windowController);
		gameController = new GameController(this, sagradaBase.getConnection(), windowController, diceController);
		
		stage.setScene(gameController);
		stage.setFullScreen(true);
		stage.show();
		
	}
	
	public void createGame(WindowPatternScreen windowPattern) {
		gameController.createGame(windowPattern);
	}
	
	public void handleCheat() {
		gameController.handleCheatGame();
	}
	
	public void makeDices() {
		diceController.makeDices();
	}
}
