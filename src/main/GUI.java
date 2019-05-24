package main;


import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import model.WindowPattern;

import java.util.ArrayList;

public class GUI extends Application {
	private DiceController diceController;
	private GameController gameController;
	private ChatController chatController;
	private UserListController userListController;


	void startup(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		DatabaseController databaseController = new DatabaseController();
		WindowController windowController = new WindowController(this, databaseController);
		diceController = new DiceController(this, windowController);
		chatController = new ChatController(this, databaseController);
        gameController = new GameController(this, databaseController, windowController, diceController, chatController);
        userListController = new UserListController(this, databaseController);

		stage.setScene(userListController);
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

	public void sendMessage(String input){chatController.sendMessage(input);}

	public void handleSort(Object val){
		userListController.handleSort(val);
	}

}
