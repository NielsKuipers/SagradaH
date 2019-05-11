package main;


import controller.ChatController;
import controller.DatabaseController;
import controller.DiceController;
import controller.GameController;
import controller.WindowController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.WindowPattern;

public class GUI extends Application {
	private DiceController diceController;
	private GameController gameController;


	public void startup(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		DatabaseController databaseController = new DatabaseController();
		WindowController windowController = new WindowController(this, databaseController);
		diceController = new DiceController(this, windowController);
		ChatController chatController = new ChatController();
		gameController = new GameController(this, windowController, diceController, chatController);

		
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
		diceController.makeDices();
	}

}
