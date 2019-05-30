package controller;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import main.GUI;
import model.Game;
import model.Player;
import model.WindowPattern;
import timer.AnimationTimerEXT;
import view.*;

public class GameController extends Scene {
	private GameInfoScreen gameInfo;

	private ChatScreen chat;
	private CardsInfoScreen kaarten;

	private GameScreen gameScreen;
	private WindowPatternChooseScreen windowChoooseScreen;
	private CardController CardController;

	private Game gameModel;

	private WindowController WC;
	private DiceController DC;
	
	private boolean hasThrown = false;
	
	private AnimationTimerEXT timer;

	public GameController(GUI gui, DatabaseController databaseController, WindowController WC, DiceController DC,
			ChatController CC) {
		super(new Pane());
		this.WC = WC;
		this.DC = DC;

		gameModel = new Game(databaseController.getGameQuery(), DC.getDiceOnTableModel(), WC, CardController);
		gameModel.addPlayer(new Player(databaseController.getPlayerQuery()));
		gameModel.addPlayer(new Player(databaseController.getPlayerQuery()));
		gameModel.addPlayer(new Player(databaseController.getPlayerQuery()));
		gameModel.addPlayer(new Player(databaseController.getPlayerQuery()));

		gameModel.getPlayer(0).givePlayerWindowPattern(WC.getWindow1().getWindowPatternModel());
		gameModel.getPlayer(1).givePlayerWindowPattern(WC.getWindow2().getWindowPatternModel());
		gameModel.getPlayer(2).givePlayerWindowPattern(WC.getWindow3().getWindowPatternModel());
		gameModel.getPlayer(3).givePlayerWindowPattern(WC.getWindow4().getWindowPatternModel());


		gameScreen = new GameScreen();
		kaarten = new CardsInfoScreen(gui,"Kaarten",this);
		gameInfo = new GameInfoScreen(gui, gameModel, "GameInfo");
		chat = CC.getChatScreen();


		gameInfo.setStyle("-fx-background-radius: 0 0 300 0;-fx-background-color: DEEPSKYBLUE; ");
		chat.setStyle("-fx-background-radius: 0 300 0 0;-fx-background-color: DEEPSKYBLUE;");
		kaarten.setStyle("-fx-background-radius: 300 0 0 0;-fx-background-color: DEEPSKYBLUE;");

		windowChoooseScreen = new WindowPatternChooseScreen(gui, WC);

		WC.setGameController(this);
		WC.setDiceController(DC);

		
		gameScreen.add(gameInfo, 0, 0, 2, 1);
		gameScreen.add(DC.getDiceOnTableScreen(), 2, 0, 2, 1);
		gameScreen.add(chat, 0, 2, 2, 1);
		gameScreen.add(kaarten, 2, 2, 2, 1);
		
		addGameScreens();

//		WC.makeWindowsGray(WC.getWindow2().getWindowPatternModel());
//		WC.makeWindowsGray(WC.getWindow3().getWindowPatternModel());
//		WC.makeWindowsGray(WC.getWindow4().getWindowPatternModel());

		


		//setAmountFT(WC.getDifficulty());

		GridPane.setMargin(WC.getWindow1(), new Insets(0, 0, 0, 80));
		GridPane.setMargin(WC.getWindow4(), new Insets(0, 80, 0, 0));

		

	
		createTimer();
		//gameModel.giveAllThePlayersTheirFavorTokens(); 
		//gameModel.selectwindowOptions();
	}
	
	void setCardController(CardController cc) {
		this.CardController=cc;
	}
	
	
	public void addGameScreens() {
		gameScreen.add(WC.getWindow1(), 0, 1);
		gameScreen.add(WC.getWindow2(), 1, 1);
		gameScreen.add(WC.getWindow3(), 2, 1);
		gameScreen.add(WC.getWindow4(), 3, 1);
	}
	
	public void addWindowScreens() {
		windowChoooseScreen.add(WC.getWindow1(), 0, 1);
		windowChoooseScreen.add(WC.getWindow2(), 1, 1);
		windowChoooseScreen.add(WC.getWindow3(), 2, 1);
		windowChoooseScreen.add(WC.getWindow4(), 3, 1);
	}
	

	public void handleCheatGame(boolean allPossible, boolean bestChoice) {
		WC.setCheatAllPossible(allPossible);
		WC.setCheatBestChoice(bestChoice);
	}

	void setPoints(int value) {
		gameInfo.setPoints(value);
	}
	
	public void switchToolcards() {
		setRoot(CardController.showcards());
	}
	
	void switchToGameScreen() {
		setRoot(gameScreen);
	}
	
	
    
    void setAmountFT(String tokens) {
        kaarten.setAmountFT(tokens);
    }
    
    int getAmountFT() {
        return Integer.parseInt(kaarten.getAmountFT());
    }



	public Game getGameModel() {
		return gameModel;
	}

	private void createTimer() {
		//favor tokens
		//card costs
		//chat
		timer = new AnimationTimerEXT(5000) {
			@Override
			public void doAction() {
				gameModel.selectWholeGame();
				System.out.println("timer");
				//has to do with toolcard 8
				if(WC.skipSecondTurn() && gameModel.isSecondTurn() && gameModel.getPlayer(0).selectCurrentPlayer()) {
					WC.setSkipSecondTurnFalse();
					gameModel.giveTurnToNextPlayer();
				}
				//roundtrack
				//favor tokens
				//card costs
				//chat
			}
		};
	}
	
	public void stopTimer() {
		timer.stop();
	}
	
	public void startTimer() {
		timer.start();
	}
	
	public void handleFinishTurn() {
		if(gameModel.getPlayer(0).selectCurrentPlayer()) {
			gameModel.giveTurnToNextPlayer();
			WC.setMovedToFalse();
			hasThrown = false;
			WC.setCanOnlyMoveDiceWithSameColorAsDIceOnRoundTrackFalse();
			WC.setDiceCanBeMovedFalse();
		}
		
	}
	
	public void handleRollDices() {
		System.out.println(gameModel.checkIfMainPlayerCanThrowDices());
		if(gameModel.checkIfMainPlayerCanThrowDices()) {
			gameModel.rollTheDices();
			//change if
		}
	}
	
	public void chooseWindow(WindowPattern windowModel) {
		gameModel.getPlayer(0).updateWindowId(windowModel.getId());
	}
	
	//creating game
	//createNewRandomPatternCard(147); create random window for one player and give it to him
	//givePlayerCardOption(119, 120, 121, 122); give all the players standard window choise
	//createPlayerFrameField(); create a player frame field

	
	public WindowPatternChooseScreen getChooseScreen() {
		return windowChoooseScreen;
	}
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}
}
