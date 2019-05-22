package main;


import controller.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.WindowPattern;
import view.WindowPatternScreen;

public class GUI extends Application {
	private DiceController diceController;
	private GameController gameController;
	private ChatController chatController;
	private RoundScreenController roundController;
	private Scene scene;

	public void startup(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		scene = new Scene(new Pane());
		
		DatabaseController databaseController = new DatabaseController();
	
		WindowController windowController = new WindowController(this, databaseController);
		DiceController diceController = new DiceController(this, windowController);
		CardController cardController = new CardController(windowController, diceController, gameController, databaseController);

		chatController = new ChatController(this, databaseController);
		gameController = new GameController(this, databaseController, windowController, diceController, chatController);
		
//		 SetupScreenController SetupController = new SetupScreenController(stage, databaseController);
//		 EndScreenController EndController = new EndScreenController(stage, databaseController);
		 roundController = new RoundScreenController(stage, databaseController, this);
		
		scene.setRoot(gameController.getChooseScreen());
		stage.setScene(scene);
		
		stage.setFullScreen(true);
		stage.show();
	}
	
	public void createGame(WindowPattern windowModel) {
		gameController.createGame(windowModel);
		scene.setRoot(gameController.getGameScreen());
	}
	
	public void handleCheat(boolean allPossible, boolean bestChoice) {
		gameController.handleCheatGame(allPossible, bestChoice);
	}
	
	public void makeDices() {
		gameController.handleRollDices();
	}

	public void sendMessage(String input){chatController.sendMessage(input);}
	
	public void handleFinishTurn() {
		gameController.handleFinishTurn();
	}
	
	public void handleGoToRoundTrack() {
		scene.setRoot(roundController.getRoundScreen());
	}
	
	public void handleGoBackToGame() {
		scene.setRoot(gameController.getGameScreen());
	}


}
