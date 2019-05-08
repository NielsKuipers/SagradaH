package main;

import controller.CardController;
import controller.DatabaseController;
import controller.DiceController;
import controller.GameController;
import controller.WindowController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.WindowPattern;
import view.WindowPatternScreen;

public class GUI extends Application {
	DatabaseController databaseController;
	WindowController windowController;
	DiceController diceController;
	GameController gameController;
	CardController cardController;
	

	public void startup(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		databaseController = new DatabaseController();
		windowController = new WindowController(this, databaseController);
		diceController = new DiceController(this, windowController);
		cardController = new CardController(this);
		gameController = new GameController(this, windowController, diceController,cardController);
		
		stage.setScene(gameController);
		stage.setFullScreen(true);
		stage.show();
		
	}
	
	public void createGame(WindowPattern windowModel) {
		gameController.createGame(windowModel);
	}
	
	public void handleCheat() {
		gameController.handleCheatGame();
	}
	
	public void makeDices() {
		diceController.makeDices();
	}
}
