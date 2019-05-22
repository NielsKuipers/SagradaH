package main;


import controller.*;
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
	
	

	public void sendMessage(String input){chatController.sendMessage(input);}


}
