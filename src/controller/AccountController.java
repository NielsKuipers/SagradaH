package controller;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import main.GUI;
import model.Account;
import view.GameListScreen;
import view.HomePane;
import view.StartPane;

public class AccountController {
	Account myaccount;
	GUI myGUI;
	HomePane homePane;
	StartPane startpane;
	String accountname;
	GameListScreen gameListScreen;
	String gameboolean = "";
	
	public AccountController(GUI gui, DatabaseController DC, HomePane HP, StartPane SP, GameListScreen GLS) {
		this.myGUI = gui;
		this.homePane = HP;
		this.startpane = SP;
		this.gameListScreen = GLS;
		myaccount = new Account(DC);
	}
	
	public void login(TextField username, PasswordField password) {
		if(myaccount.login(username.getText(), password.getText()) == true) {
			//System.out.println(username+""+password);
			myGUI.changePane(homePane);
			setAccount(username.getText());
			startpane.getLog().emptyFields();
			//System.out.println("passed if in login");
		} else {
			//System.out.println("passed else in login");
			startpane.getLog().badFields(username, password);
		}
	}
	public void register(TextField username, PasswordField password) {
		if(myaccount.register(username.getText(), password.getText()) == true) {
			startpane.getReg().setGreenBorder(username, password);
		} else {
			startpane.getReg().setRedBorder(username, password);
		}
	}
	
//	public void makeGameList() {
//		myGUI.changePane(gameListScreen);
//		for(int x = 1; x <= myaccount.getMax(); x++) {
//			//System.out.println("loop "+x);
//			if(myaccount.checkIDgame(x)) {
//				gameListScreen.addGameBox(myaccount.getPlayers(x), myaccount.getRound(x), myaccount.getDate(x));
//				//System.out.println(myaccount.getPlayers(x)+" "+ myaccount.getRound(x)+" "+myaccount.getDate(x));
//			}
//		}
//		//gameListScreen.addGameList();
//	}
	
	public ArrayList<HBox> getGames(ArrayList<ArrayList<Object>> games) {
		StringBuilder stringBuilder = new StringBuilder();
		ArrayList<HBox> hboxList = new ArrayList<HBox>();
		int gameID = 0;
		int newGameID;
		
		for(ArrayList<Object> row : games ) {
			
			newGameID = (int) row.get(1);
			
			if(newGameID != gameID) {
				HBox gameLine = new HBox();
				Button joinGame = new Button("Join game");
				if(myaccount.checkIfInGame(newGameID,getAccount())) {
					gameLine.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,null,null)));
					joinGame.setDisable(false);
				}
				else {
					gameLine.setBackground(new Background(new BackgroundFill(Color.GRAY,null,null)));
					joinGame.setDisable(true);
				}
				for(Object game: row.subList(1, row.size()-1) ) {
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
	
	public void handleSort(Object sortV) {
		if(gameboolean == "Alle spellen") {
			gameListScreen.showGames(getGames(myaccount.getGames(sortV)));
		}
		if(gameboolean == "Mijn spellen") {
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
	
	public void setAccount(String AC) {
		this.accountname = AC;
	}
	
	public String getAccount() {
		return accountname;
	}
	
	public void toHomeMenu() {
		myGUI.changePane(homePane);
	}
	
	public void setGameboolean(String S) {
		gameboolean = S;
	}
}
