package controller;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import main.GUI;
import model.Game;
import model.Player;
import model.WindowPattern;
import timer.AnimationTimerEXT;
import view.ChatScreen;
import view.GameInfoScreen;
import view.GameScreen;
import view.WindowPatternChooseScreen;

public class GameController extends Scene {
	private GameInfoScreen gameInfo;
	private ChatScreen chat;
	private GameInfoScreen kaarten;
	private GameScreen gameScreen;

	private WindowPatternChooseScreen windowChoooseScreen;

	private Game gameModel;

	private AnimationTimerEXT timer;

	private WindowController WC;
	private DiceController DC;
	
	private boolean hasThrown = false;

	public GameController(GUI gui, DatabaseController databaseController, WindowController WC, DiceController DC,
			ChatController CC) {
		super(new Pane());
		this.WC = WC;
		this.DC = DC;

		gameModel = new Game(databaseController.getGameQuery(), DC.getDiceOnTableModel(), WC);

		gameModel.addPlayer(new Player(databaseController.getPlayerQuery()));
		gameModel.addPlayer(new Player(databaseController.getPlayerQuery()));
		gameModel.addPlayer(new Player(databaseController.getPlayerQuery()));
		gameModel.addPlayer(new Player(databaseController.getPlayerQuery()));

		gameModel.getPlayer(0).givePlayerWindowPattern(WC.getWindow1().getWindowPatternModel());
		gameModel.getPlayer(1).givePlayerWindowPattern(WC.getWindow2().getWindowPatternModel());
		gameModel.getPlayer(2).givePlayerWindowPattern(WC.getWindow3().getWindowPatternModel());
		gameModel.getPlayer(3).givePlayerWindowPattern(WC.getWindow4().getWindowPatternModel());

		gameScreen = new GameScreen();

		gameInfo = new GameInfoScreen(gui, gameModel, "GameInfo");
		chat = CC.getChatScreen();
		kaarten = new GameInfoScreen(gui, gameModel, "Kaarten");

		gameInfo.setStyle("-fx-background-radius: 0 0 300 0;-fx-background-color: DEEPSKYBLUE; ");
		chat.setStyle("-fx-background-radius: 0 300 0 0;-fx-background-color: DEEPSKYBLUE;");
		kaarten.setStyle("-fx-background-radius: 300 0 0 0;-fx-background-color: DEEPSKYBLUE;");

		windowChoooseScreen = new WindowPatternChooseScreen(gui, WC);

		windowChoooseScreen.add(WC.getWindow1(), 0, 1);
		windowChoooseScreen.add(WC.getWindow2(), 1, 1);
		windowChoooseScreen.add(WC.getWindow3(), 2, 1);
		windowChoooseScreen.add(WC.getWindow4(), 3, 1);

		WC.setGameController(this);
		WC.setDiceController(DC);
		setRoot(windowChoooseScreen);
		gameModel.selectwindowOptions();
	}

	public void createGame(WindowPattern windowModel) {

		gameScreen.add(gameInfo, 0, 0, 2, 1);
		gameScreen.add(DC.getDiceOnTableScreen(), 2, 0, 2, 1);
		gameScreen.add(chat, 0, 2, 2, 1);
		gameScreen.add(kaarten, 2, 2, 2, 1);

		WC.makeWindowsGray(WC.getWindow2().getWindowPatternModel());
		WC.makeWindowsGray(WC.getWindow3().getWindowPatternModel());
		WC.makeWindowsGray(WC.getWindow4().getWindowPatternModel());

		gameScreen.add(WC.getWindow1(), 0, 1);
		gameScreen.add(WC.getWindow2(), 1, 1);
		gameScreen.add(WC.getWindow3(), 2, 1);
		gameScreen.add(WC.getWindow4(), 3, 1);

		gameScreen.setMargin(WC.getWindow1(), new Insets(0, 0, 0, 80));
		gameScreen.setMargin(WC.getWindow4(), new Insets(0, 80, 0, 0));

		gameModel.getPlayer(0).updateWindowId(windowModel.getId());

		setRoot(gameScreen);
		createTimer();
		//gameModel.giveAllThePlayersTheirFavorTokens(); give all the players their favortokens
		
	}

	public void handleCheatGame(boolean allPossible, boolean bestChoice) {
		WC.setCheatAllPossible(allPossible);
		WC.setCheatBestChoice(bestChoice);
	}

	public void setPoints(int value) {
		gameInfo.setPoints(value);
	}

	public Game getGameModel() {
		return gameModel;
	}

	public void createTimer() {
		timer = new AnimationTimerEXT(5000) {
			@Override
			public void doAction() {
				gameModel.selectWholeGame();
				//favor tokens
				//card costs
				//chat
			}
		};
		timer.start();
	}
	
	public void handleFinishTurn() {
		if(gameModel.getPlayer(0).selectCurrentPlayer()) {
			gameModel.giveTurnToNextPlayer();
			WC.setMovedToFalse();
			hasThrown = false;
		}
		
	}
	
	public void handleRollDices() {
		if(hasThrown == false && gameModel.checkIfMainPlayerCanThrowDices()) {
			//gameModel.rollTheDices();
			hasThrown = true;
		}
	}
	
	//creating game
	//createNewRandomPatternCard(147); create random window for one player and give it to him
	//givePlayerCardOption(119, 120, 121, 122); give all the players standard window choise
	//createPlayerFrameField(); create a player frame field

}
