package main;

import controller.AccountController;
import controller.DatabaseController;
import controller.DiceController;
import controller.GameController;
import controller.WindowController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.WindowPattern;
import view.StartScreen;

public class GUI extends Application {
	DatabaseController databaseController;
	WindowController windowController;
	DiceController diceController;
	GameController gameController;
	AccountController accountController;
	StartScreen startScreen;
	Stage stage;
	

	public void startup(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		this.stage = stage;
		databaseController = new DatabaseController();
		windowController = new WindowController(this, databaseController);
		diceController = new DiceController(this, windowController);
		gameController = new GameController(this, windowController, diceController);
		accountController = new AccountController(this, databaseController);
		startScreen = new StartScreen(this);
		
		switchScreen(startScreen);
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
	
	public void handlelogin(String username, String password) {
		accountController.login(username, password);
	}
	
	public void switchScreen(Scene scene) {
		stage.setScene(scene);
	}
}
