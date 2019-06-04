package main;

import controller.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.WindowPatternModel;
import view.GameListScreen;
import view.HomePane;
import view.StartPane;
import view.ToolCardScreen;

public class GUI extends Application {
    private DiceController diceController;
    private GameController gameController;
    private AccountController accountController;
    private Scene scene;
    private ChatController chatController;
    private UserListController userListController;
    private RoundScreenController roundController;
    private CardController cardController;
    private SetupScreenController setupScreenController;
    private EndScreenController endController;

    WindowController windowController;

    private CalculateScoreController calcController;


    void startup(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        stage.setTitle("Sagrada\u00a9 Groep-H\u2122");

        StartPane startPane = new StartPane(this);
        HomePane homepane = new HomePane(this);
        GameListScreen gameListScreen = new GameListScreen(this);
        DatabaseController databaseController = new DatabaseController();

        windowController = new WindowController(this, databaseController);
        calcController = new CalculateScoreController(databaseController);


        calcController = new CalculateScoreController(databaseController);
        diceController = new DiceController(this, windowController);
        chatController = new ChatController(this, databaseController);
        userListController = new UserListController(this, databaseController);
        gameController = new GameController(this, databaseController, windowController, diceController, chatController, calcController);


        cardController = new CardController(windowController, diceController, gameController, databaseController, this);
        accountController = new AccountController(this, databaseController, homepane, startPane, gameListScreen, gameController, diceController, cardController);
        roundController = new RoundScreenController(this, windowController, gameController);
        setupScreenController = new SetupScreenController(databaseController, this, gameController, accountController, cardController);

        endController = new EndScreenController(databaseController, gameController, calcController, this);


        scene = new Scene(startPane);
        stage.setScene(scene);

        //	stage.setScene(gameController);
        //stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH); might be nice for test day.

        stage.setFullScreen(true);
        stage.show();
    }

    /**
     * @param windowModel = the window you have chosen
     *                    handle go to game, when you are on the window choose screen
     */
    public void createGame(WindowPatternModel windowModel) {
        gameController.chooseWindow(windowModel);
        gameController.addGameScreens();
        gameController.getGameModel().makeGameEmpty();
        diceController.getDiceOnTableScreen().removeDicesScreen();
        gameController.getGameModel().selectPlayerIds();
        gameController.getGameModel().selectWholeGame();
        chatController.getMessages(gameController.getGameModel().getGameID());
        
        scene.setRoot(gameController.getGameScreen());
        gameController.startTimer();
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


    public void startTimer() {
        gameController.startTimer();
    }

    public void stopTimer() {
        gameController.stopTimer();
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
        gameController.stopTimer();
        windowController.setExtraTurnFalse();
    }

    public void sendString(String S) {
        accountController.setGameboolean(S);
    }

    public void sendMessage(String input) {
        chatController.sendMessage(input, gameController.getGameModel().getClientPlayer().getPlayerId());
    }

    public void handleSort(Object val) {
        userListController.handleSort(val);
    }

    public void handleFinishTurn() {
        gameController.handleFinishTurn();
        windowController.changedDiceBoard(false);
        startTimer();
    }

    public void handleGoToRoundTrack() {
        scene.setRoot(roundController.getRoundScreen());
    }

    public void handleGoBackToGame() {
        scene.setRoot(gameController.getGameScreen());
        gameController.setClientScore();
        gameController.setOtherScore();
    }

    public void handleGoToCards() {
        scene.setRoot(cardController.showcards());
    }

    public void switchToolcards() {
        scene.setRoot(cardController.showcards());
    }

    public void handleChooseScreen() {
        scene.setRoot(gameController.getChooseScreen());
    }

    public void handleToEndScreen() {
        scene.setRoot(endController.getEndScreen());
    }

    public void handleLoadSetup(int gameid) {
        scene.setRoot(setupScreenController.getSetupScreen());
        setupScreenController.loadSetup(gameid);
    }

    // schakel van setup scherm naar invite scherm
    public void openInviterMenu() {
        setupScreenController.getInviteScreen().clearList();
        setupScreenController.addPlayersToInviteList();
        scene.setRoot(setupScreenController.getInviteScreen());
    }

    // schakel van invite naar setup scherm
    public void openSetupMenu() {
        scene.setRoot(setupScreenController.getSetupScreen());
        setupScreenController.getSetupScreen().clearJoinedList();
        setupScreenController.addJoinedPlayers();
    }

    public void handleToGetInvite() {
        setupScreenController.toInviteGetScreen();
    }

    public void handleToPlayerList() {
        userListController.toUserListScreen();
    }

	  public void buyToolcard(ToolCardScreen gameTC, int i) {
		  cardController.buyToolcard(gameTC, i);
	  }

    public void handleToCreateGame() {
        setupScreenController.toSetupScreen();
        setupScreenController.makeNewGame();
    }

    public void setGameIDforScoreCalc(int gameID) {
        calcController.setGameID(gameID);
    }

    public void HandleExitGame() {
        System.exit(0);
    }


}

