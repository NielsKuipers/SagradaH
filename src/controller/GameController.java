package controller;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import view.GameInfoScreen;
import view.GameScreen;
import view.WindowPatternChooseScreen;
import view.WindowPatternScreen;

public class GameController extends Scene {
	private GameInfoScreen gameInfo;
	private GameInfoScreen chat;
	private GameInfoScreen kaarten;

	private GameScreen gameScreen;

	private WindowPatternChooseScreen windowChoooseScreen;
	private Button window1Button;
	private Button window2Button;
	private Button window3Button;
	private Button window4Button;

	private WindowController WC;
	private DiceController DC;

	public GameController(WindowController WC, DiceController DC) {
		super(new Pane());
		this.WC = WC;
		this.DC = DC;

		gameScreen = new GameScreen();
		gameInfo = new GameInfoScreen("GameInfo", this);
		chat = new GameInfoScreen("Chat", this);
		kaarten = new GameInfoScreen("Kaarten", this);

		gameInfo.setStyle("-fx-background-radius: 0 0 300 0;-fx-background-color: DEEPSKYBLUE;");
		chat.setStyle("-fx-background-radius: 0 300 0 0;-fx-background-color: DEEPSKYBLUE;");
		kaarten.setStyle("-fx-background-radius: 300 0 0 0;-fx-background-color: DEEPSKYBLUE;");

		windowChoooseScreen = new WindowPatternChooseScreen();

		window1Button = new Button("Deze wil ik !");
		window2Button = new Button("Deze wil ik !");
		window3Button = new Button("Deze wil ik !");
		window4Button = new Button("Deze wil ik !");

		windowChoooseScreen.add(WC.getWindow1(), 0, 1);
		windowChoooseScreen.add(WC.getWindow2(), 1, 1);
		windowChoooseScreen.add(WC.getWindow3(), 2, 1);
		windowChoooseScreen.add(WC.getWindow4(), 3, 1);

		windowChoooseScreen.add(window1Button, 0, 2);
		windowChoooseScreen.add(window2Button, 1, 2);
		windowChoooseScreen.add(window3Button, 2, 2);
		windowChoooseScreen.add(window4Button, 3, 2);

		windowChoooseScreen.setHalignment(window1Button, HPos.CENTER);
		windowChoooseScreen.setHalignment(window2Button, HPos.CENTER);
		windowChoooseScreen.setHalignment(window3Button, HPos.CENTER);
		windowChoooseScreen.setHalignment(window4Button, HPos.CENTER);
		
		window1Button.setOnMouseClicked(e -> createGame(WC.getWindow1()));
		window2Button.setOnMouseClicked(e -> createGame(WC.getWindow2()));
		window3Button.setOnMouseClicked(e -> createGame(WC.getWindow3()));
		window4Button.setOnMouseClicked(e -> createGame(WC.getWindow4()));

		setRoot(windowChoooseScreen);
		
		WC.setGameController(this);
	}

	public void createGame(WindowPatternScreen windowPattern) {

		gameScreen.add(gameInfo, 0, 0, 2, 1);
		gameScreen.add(DC.getDraggableDices(), 2, 0, 2, 1);
		gameScreen.add(chat, 0, 2, 2, 1);
		gameScreen.add(kaarten, 2, 2, 2, 1);

		if (windowPattern != WC.getWindow1()) {
			WC.setWindow1(windowPattern);
		}

		WC.makeWindowsGray();

		gameScreen.add(WC.getWindow1(), 0, 1);
		gameScreen.add(WC.getWindow2(), 1, 1);
		gameScreen.add(WC.getWindow3(), 2, 1);
		gameScreen.add(WC.getWindow4(), 3, 1);
		

		gameScreen.setMargin(WC.getWindow1(), new Insets(0, 0, 0, 20));
		gameScreen.setMargin(WC.getWindow4(), new Insets(0, 20, 0, 0));

		setRoot(gameScreen);
	}
	
	public void handleCheatGame() {
		if(WC.getCheat() == true) {
			WC.setCheatMode(false);
		}
		else {
			WC.setCheatMode(true);
		}
	}
	
	public void setPoints(int value) {
		gameInfo.setPoints(value);
	}

}
