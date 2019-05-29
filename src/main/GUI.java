package main;

import controller.AccountController;
import controller.CardController;
import controller.ChatController;
import controller.DatabaseController;
import controller.DiceController;
import controller.GameController;
import controller.RoundScreenController;
import controller.SetupScreenController;
import controller.WindowController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
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
	private UserListController userListController;
	private RoundScreenController roundController;
	private CardController cardController;
	private SetupScreenController SetupController;
	//private EndScreenController EndController;
	private CalculateScore calcScore;

	void startup(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {

		StartPane startPane = new StartPane(this);
		HomePane homepane = new HomePane(this);
		GameListScreen gameListScreen = new GameListScreen(this);
		DatabaseController databaseController = new DatabaseController();
		WindowController windowController = new WindowController(this, databaseController);
		DiceController diceController = new DiceController(this, windowController);
		accountController = new AccountController(this, databaseController, homepane, startPane, gameListScreen);
		accountController = new AccountController(this, databaseController, homepane, startPane, gameListScreen);
		DiceController diceController = new DiceController(this, windowController);
		chatController = new ChatController(this, databaseController);
        userListController = new UserListController(this, databaseController);
		gameController = new GameController(this, databaseController, windowController, diceController, chatController);
		cardController = new CardController(windowController, diceController, gameController, databaseController, this);
		SetupController = new SetupScreenController(this, databaseController);
		//EndController = new EndScreenController(stage, databaseController);
		roundController = new RoundScreenController(stage, databaseController, this);
		roundController = new RoundScreenController(stage, databaseController, this);
		calcScore = new CalculateScore(databaseController);
		scene = new Scene(startPane);
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
	
	public void makeDices() { gameController.handleRollDices(); }
	
	public void handlelogin(TextField username, PasswordField password) { accountController.login(username, password); }
	
	public void changePane(Pane pane) { scene.setRoot(pane); }
	
	public void changePane(ScrollPane pane) { scene.setRoot(pane); }
	
	public void handleregister(TextField username, PasswordField password) {
		accountController.register(username, password);
	}
	
	public void handleUitloggen() { accountController.uitloggen(); }
	
	public void handleToGameList() { accountController.showGames(); }
                                              
	public void handlegamesort(Object sortV) { accountController.handleSort(sortV); }
	
	public void handleHomeMenu() { accountController.toHomeMenu(); }
	
	public void sendString(String S) { accountController.setGameboolean(S); }

	public void sendMessage(String input){
		chatController.sendMessage(input);
	}

	public void sendMessage(String input){chatController.sendMessage(input);}

	public void handleSort(Object val){ userListController.handleSort(val); }
	
	public void handleFinishTurn() { gameController.handleFinishTurn(); }
	
	public void handleGoToRoundTrack() { scene.setRoot(roundController.getRoundScreen()); }
	
	public void handleGoBackToGame() {
		scene.setRoot(gameController.getGameScreen());
	}
	
	public void switchToolcards() {
		scene.setRoot(cardController.showcards());
	}

	public void handleToGetInvite() {
		SetupController.toInviteGetScreen();
		
	}

	public void handleToPlayerList() {
		// TODO kan ik nog niet, mis userlist.
		
	}

	public void handleToCreateGame() {
		SetupController.toSetupScreen();
		
	}

	public void HandleExitGame() {
		System.exit(0);
	}

	public void handleGoBackToGame() { scene.setRoot(gameController.getGameScreen()); }

	public void handleGoToCards() { scene.setRoot(cardController.showcards()); }
  
	public void switchToolcards() { scene.setRoot(cardController.showcards()); }

	public void handleUserList() { scene.setRoot(userListController.getUserListScreen()); }

}
