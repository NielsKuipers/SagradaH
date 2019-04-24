package main;

import controller.DatabaseController;
import controller.DiceController;
import controller.GameController;
import controller.WindowController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.WindowPatternScreen;

public class GUI extends Application {
	WindowController windowController;
	DiceController diceController;
	GameController gameController;
	DatabaseController sagradaBase = new DatabaseController();

	public void startup(String[] args) {
		launch(args);
		
		
	}
	
	public void start(Stage stage) {

		windowController = new WindowController(this);
		diceController = new DiceController(this, windowController);
		gameController = new GameController(this,windowController, diceController);

		stage.setScene(gameController);
		stage.setFullScreen(true);
		stage.show();
		sagradaBase.selectQuery2("SELECT * FROM nprjkuip_db2.account");
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
