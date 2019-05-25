package main;

import controller.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.WindowPattern;
import view.GameListScreen;
import view.HomePane;
import view.StartPane;


public class GUI extends Application {
	private DiceController diceController;
	private GameController gameController;
	private AccountController accountController;
	private Scene scene;
	private ChatController chatController;
	private RoundScreenController roundController;
	private CardController cardController;


	void startup(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {

		StartPane startPane = new StartPane(this);
		HomePane homepane = new HomePane(this);
		GameListScreen gameListScreen = new GameListScreen(this);
		
		DatabaseController databaseController = new DatabaseController();
	
		WindowController windowController = new WindowController(this, databaseController);

		accountController = new AccountController(this, databaseController, homepane, startPane, gameListScreen);

		DiceController diceController = new DiceController(this, windowController);

		chatController = new ChatController(this, databaseController);
		gameController = new GameController(this, databaseController, windowController, diceController, chatController);

		
		cardController = new CardController(windowController, diceController, gameController, databaseController, this);

		
//	  SetupScreenController SetupController = new SetupScreenController(stage, databaseController);
//	  EndScreenController EndController = new EndScreenController(stage, databaseController);
		roundController = new RoundScreenController(stage, databaseController, this, windowController);
		
		scene = new Scene(gameController.getChooseScreen());

		stage.setScene(scene);
	//	stage.setScene(gameController);
		//stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH); might be nice for test day.
		
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
	
	public void handlelogin(TextField username, PasswordField password) {
		accountController.login(username, password);
	}
	
	public void changePane(Pane pane) {
		scene.setRoot(pane);
	}
	
	public void changePane(ScrollPane pane) {
		scene.setRoot(pane);
	}
	
	public void handleregister(TextField username, PasswordField password) {
		accountController.register(username, password);
	}
	
	public void handleUitloggen() {
		accountController.uitloggen();
	}
	
	public void handleToGameList() {
		accountController.showGames();
	}
                                              
	public void handlegamesort(Object sortV) {
		accountController.handleSort(sortV);
	}
	
	public void handleHomeMenu() {
		accountController.toHomeMenu();
	}
	
	public void sendString(String S) {
		accountController.setGameboolean(S);
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
	

	public void handleGoToCards() {

		scene.setRoot(cardController.showcards());
	}
}
