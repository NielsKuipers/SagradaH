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

	
	
	
	private CardController cardController;
	private DiceController diceController;
	private GameController gameController;
	private ChatController chatController;



	void startup(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		DatabaseController databaseController = new DatabaseController();
		WindowController windowController = new WindowController(this, databaseController);
		diceController = new DiceController(this, windowController);

		chatController = new ChatController(this, databaseController);
        gameController = new GameController(this, databaseController, windowController, diceController, chatController);

		
		gameController = new GameController(this, databaseController, windowController, diceController, chatController);
		cardController = new CardController(this,windowController,diceController, gameController,databaseController);
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
