package main;


import controller.CardController;


import controller.ChatController;

import controller.DatabaseController;
import controller.DiceController;
import controller.GameController;
import controller.WindowController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.WindowPattern;

public class GUI extends Application {


	private GameController gameController;
	private ChatController chatController;



	void startup(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		DatabaseController databaseController = new DatabaseController();
		WindowController windowController = new WindowController(this, databaseController);
		DiceController diceController = new DiceController(this, windowController);
		CardController cardController = new CardController(windowController, diceController, gameController, databaseController);

		chatController = new ChatController(this, databaseController);
        gameController = new GameController(this, databaseController, windowController, diceController, chatController);
		gameController = new GameController(this, databaseController, windowController, diceController, chatController);

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
	
	

	public void sendMessage(String input){chatController.sendMessage(input);}


}
