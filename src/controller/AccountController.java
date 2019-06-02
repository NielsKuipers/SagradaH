
package controller;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import main.GUI;
import model.Account;
import view.GameListScreen;
import view.HomePane;
import view.StartPane;
import java.util.ArrayList;

public class AccountController {
	private Account myaccount;
	private GUI myGUI;
	private HomePane homePane;
	private StartPane startpane;
	private String accountname;
	private GameListScreen gameListScreen;
	private String gameboolean = "";
	private GameController gameController;

	private DiceController diceController;
	private CardController cardController;

  




	
	public AccountController(GUI gui, DatabaseController DC, HomePane HP, StartPane SP, GameListScreen GLS, GameController gameController, DiceController diceController, CardController cardController2) {

		this.myGUI = gui;
		this.gameController = gameController;

		this.diceController = diceController;
		this.cardController=cardController;

		this.homePane = HP;
		this.startpane = SP;
		this.gameListScreen = GLS;
        this.diceController = diceController;
		myaccount = new Account(DC);
	}
	
	public void login(TextField username, PasswordField password) {
		if(myaccount.login(username.getText(), password.getText())) {
			myGUI.changePane(homePane);
			setAccount(username.getText());
			gameController.getGameModel().setAccountName(username.getText());
			startpane.getLog().emptyFields();
		} else {
			startpane.getLog().badFields(username, password);
		}
	}
	public void register(TextField username, PasswordField password) {
		if(myaccount.register(username.getText(), password.getText())) {
			startpane.getReg().setGreenBorder(username, password);
		} else {
			startpane.getReg().setRedBorder(username, password);
		}
	}
			
	private ArrayList<HBox> getGames(ArrayList<ArrayList<Object>> games) {
		StringBuilder stringBuilder = new StringBuilder();
		ArrayList<HBox> hboxList = new ArrayList<>();
		int gameID = 0;

		for (ArrayList<Object> row : games) {
			int newGameID;
			newGameID = (int) row.get(1);

			if (newGameID != gameID) {
				HBox gameLine = new HBox();
				Button joinGame = new Button("Join game");
				if (myaccount.checkIfInGame(newGameID, getAccount())) {
					gameLine.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
					joinGame.setDisable(false);
					joinGame.setOnMouseClicked(e -> handleJoinGame(newGameID));

				} else {
					gameLine.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
					joinGame.setDisable(true);
				}
				for (Object game : row.subList(1, row.size() - 1)) {
					stringBuilder.append(game).append(" ");
				}
				Label textforgame = new Label(stringBuilder.toString());
				gameLine.getChildren().addAll(textforgame, joinGame);
				hboxList.add(gameLine);
				gameID = newGameID;
				stringBuilder.setLength(0);
			}
		}
		return hboxList;
	}
  
	private void handleJoinGame(int newGameID) {
		if (myaccount.hostplayer(getAccount(), newGameID) || (!myaccount.hostplayer(getAccount(), newGameID) && myaccount.patternsCreated(getAccount(), newGameID))) {
			gameController.getGameModel().setGameID(newGameID);
			gameController.getGameModel().selectPlayerIds();

			gameController.getGameModel().selectWholeGame();
			cardController.getDBcards();
			gameController.startTimer();
			myGUI.handleGoBackToGame();

			myGUI.setGameIDforScoreCalc(newGameID);

			if (!myaccount.patternsCreated(getAccount(), newGameID) && myaccount.hostplayer(getAccount(), newGameID)) {
				myGUI.handleLoadSetup(newGameID);
			} else if (!gameController.getGameModel().checkIfPlayerMainPlayerPickedWindow()) {
				gameController.addWindowScreens();
				gameController.getGameModel().selectwindowOptions();
				myGUI.handleChooseScreen();
			} else {
				gameController.getGameModel().makeGameEmpty();
				diceController.getDiceOnTableScreen().removeDicesScreen();
				gameController.getGameModel().setGameID(newGameID);
				gameController.getGameModel().selectPlayerIds();
				gameController.getGameModel().selectWholeGame();
				gameController.getCC().getMessages(gameController.getGameModel().getGameID());
				gameController.startTimer();
				myGUI.handleGoBackToGame();
			}
		}else {
			alert();
			

		}
	}
	
	/**
	 * show a alert
	 */
	private void alert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Game is nog niet ready");
		alert.setHeaderText("WAARSCHUWING");
		alert.setContentText("Windowpattern keuzes zijn nog niet gemaakt,\n wij vragen u vriendelijk om nog heel eventjes te wachten");
		alert.showAndWait();
	}	
	public void handleSort(Object sortV) {
		if(gameboolean.equals("Alle spellen")) {
			gameListScreen.showGames(getGames(myaccount.getGames(sortV)));
		}
		if(gameboolean.equals("Mijn spellen")) {
			gameListScreen.showGames(getGames(myaccount.getGames(sortV, getAccount())));
		}
	}
	
	public void showGames() {
		gameListScreen.showGames(getGames(myaccount.getGames()));
		myGUI.changePane(gameListScreen);
	}
	
	public void uitloggen() {
		myGUI.changePane(startpane);
		setAccount(null);
	}
	
	private void setAccount(String AC) {
		this.accountname = AC;
	}

	String getAccount() {
		return accountname;
	}
	
	public void toHomeMenu() {
		myGUI.changePane(homePane);
	}
	 
	public void setGameboolean(String S) {
		gameboolean = S;
	}
}
