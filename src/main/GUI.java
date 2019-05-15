package main;

import controller.AccountController;
import controller.DatabaseController;
import controller.DiceController;
import controller.GameController;
import controller.WindowController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.WindowPattern;
import view.HomePane;
import view.StartPane;

public class GUI extends Application {
	DatabaseController databaseController;
	WindowController windowController;
	DiceController diceController;
	GameController gameController;
	AccountController accountController;
	StartPane startPane;
	HomePane homepane;
	Scene scene;
	Stage stage;
	

	public void startup(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		this.stage = stage;
		startPane = new StartPane(this);
		homepane = new HomePane(this);
		databaseController = new DatabaseController();
		windowController = new WindowController(this, databaseController);
		diceController = new DiceController(this, windowController);
		gameController = new GameController(this, windowController, diceController);
		accountController = new AccountController(this, databaseController, homepane,startPane);
		scene = new Scene(startPane);
		this.stage.setScene(scene);
		//stage.setFullScreen(true);
		//stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH); might be nice for test day.
		this.stage.show();
		
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
	
	public void handlelogin(TextField username, PasswordField password) {
		accountController.login(username, password);
	}
	
	public void changePane(Pane pane) {
		scene.setRoot(pane);
	}
	
	public void handleregister(TextField username, PasswordField password) {
		accountController.register(username, password);
	}
	
	public void handleUitloggen() {
		accountController.uitloggen();
	}
}
