package main;

import controller.AccountController;
import controller.DatabaseController;
import controller.DiceController;
import controller.GameController;
import controller.WindowController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.WindowPattern;
import view.LoginScreen;
import view.RegisterScreen;
import view.StartPane;

public class GUI extends Application {
	DatabaseController databaseController;
	WindowController windowController;
	DiceController diceController;
	GameController gameController;
	AccountController accountController;
	RegisterScreen registerScreen;
	LoginScreen loginScreen;
	StartPane startPane;
	Scene scene;
	

	public void startup(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		registerScreen = new RegisterScreen(this);
		loginScreen = new LoginScreen(this);
		databaseController = new DatabaseController();
		windowController = new WindowController(this, databaseController);
		diceController = new DiceController(this, windowController);
		gameController = new GameController(this, windowController, diceController);
		accountController = new AccountController(this, databaseController, registerScreen, loginScreen);
		startPane = new StartPane(this);
		scene = new Scene(startPane);
		stage.setScene(scene);
		//stage.setFullScreen(true);
		//stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH); might be nice for test day.
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
	
	public void changePane(Pane pane) {
		scene.setRoot(pane);
	}
	
	public void handleregister(String username, String password) {
		accountController.register(username, password);
	}
}
