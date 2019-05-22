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
		kaarten = new CardsInfoScreen(gui,"Kaarten",this);
		gameInfo = new GameInfoScreen(gui, gameModel, "GameInfo");
		chat = CC.getChatScreen();


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
		//setRoot(windowChoooseScreen);
		gameModel.selectwindowOptions();

	}
	
	void setCardController(CardController cc) {
		this.CardController=cc;
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


		setAmountFT(WC.getDifficulty());

		GridPane.setMargin(WC.getWindow1(), new Insets(0, 0, 0, 80));
		GridPane.setMargin(WC.getWindow4(), new Insets(0, 80, 0, 0));

		gameModel.getPlayer(0).updateWindowId(windowModel.getId());

		//setRoot(gameScreen);
		createTimer();
		//gameModel.giveAllThePlayersTheirFavorTokens(); give all the players their favortokens
		
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



	        

	Game getGameModel() {
		return gameModel;
	}

	private void createTimer() {
		//favor tokens
		//card costs
		//chat
		AnimationTimerEXT timer = new AnimationTimerEXT(5000) {
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
		if(!hasThrown && gameModel.checkIfMainPlayerCanThrowDices()) {
			//gameModel.rollTheDices();
			hasThrown = true;
		}
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
