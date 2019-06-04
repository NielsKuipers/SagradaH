
package controller;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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


    public AccountController(GUI gui, DatabaseController DC, HomePane HP, StartPane SP, GameListScreen GLS, GameController gameController, DiceController diceController, CardController cardController) {

        this.myGUI = gui;
        this.gameController = gameController;
        this.diceController = diceController;
        this.cardController = cardController;
        this.homePane = HP;
        this.startpane = SP;
        this.gameListScreen = GLS;

        myaccount = new Account(DC);
    }

    public void login(TextField username, PasswordField password) {
        if (myaccount.login(username.getText(), password.getText())) {
            myGUI.changePane(homePane);
            setAccount(username.getText());
            gameController.getGameModel().setAccountName(username.getText());
            startpane.getLog().emptyFields();
        } else {
            startpane.getLog().badFields(username, password);
        }
    }

    public void register(TextField username, PasswordField password) {
        if (myaccount.register(username.getText(), password.getText())) {
            startpane.getReg().setGreenBorder(username, password);
        } else {
            startpane.getReg().setRedBorder(username, password);
        }
    }

    /**
     * This makes the list with all the games in it.
     */
    private ArrayList<HBox> getGames(ArrayList<ArrayList<Object>> games, String kind) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<HBox> hboxList = new ArrayList<>();
        HashMap<String, String> playerStatus = new HashMap<>();
        int idGame;
        int rowCount;

        if (kind.equals("ID")) {
            idGame = 0;
            rowCount = 0;
        } else {
            idGame = games.size()-1;
            rowCount = 0;
        }

        for (ArrayList<Object> row: games) {
            int newGameID = (int) row.get(1);

            if (newGameID != idGame) {
                String status = " Status: niet een deelnemer";
                HBox gameLine = new HBox();
                gameLine.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
                for(ArrayList<Object> curGame: makeSublistOf(newGameID, games)) {
                	playerStatus.put(curGame.get(0).toString(), curGame.get(3).toString());
                }

                if (playerStatus.containsKey(getAccount())) {
                    Button joinGame = new Button("Join game");
                    switch (playerStatus.get(getAccount())) {
                        case "afgebroken":
                            gameLine.setBackground(new Background(new BackgroundFill(Color.DARKRED, null, null)));
                            status = " Status: afgebroken";
                            break;
                        case "uitgespeeld":
                            gameLine.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, null, null)));
                            status = " Status: uitgespeeld";
                            gameLine.getChildren().add(joinGame);
                            break;
                        case "geaccepteerd":
                            gameLine.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, null, null)));
                            status = " Status: game is gestart/ bezig";
                            gameLine.getChildren().add(joinGame);
                            break;
                        case "geweigerd":
                            gameLine.setBackground(new Background(new BackgroundFill(Color.CRIMSON, null, null)));
                            status = " Status: geweigerd";
                            break;
                        case "uitdager":
                            gameLine.setBackground(new Background(new BackgroundFill(Color.ROYALBLUE, null, null)));
                            status = " Status: uitdager";
                            gameLine.getChildren().add(joinGame);
                            break;
                    }
                    int gameNumber = idGame;
                    joinGame.setOnMouseClicked(e -> handleJoinGame(gameNumber));
                }

                for (Object object : games.get(rowCount).subList(1, games.get(rowCount).size() - 1)) {
                    stringBuilder.append(object).append(" ");
                }

                Label textforgame = new Label(stringBuilder.toString() + status);
                gameLine.getChildren().add(textforgame);
                gameLine.setSpacing(10.0);
                hboxList.add(gameLine);
                idGame = newGameID;
                stringBuilder.setLength(0);
                playerStatus.clear();
            }
            rowCount++;
        }
        return hboxList;
    }

    private ArrayList<ArrayList<Object>> makeSublistOf(int gameID,ArrayList<ArrayList<Object>> games) {
    	ArrayList<ArrayList<Object>> curGame = new ArrayList<>();
    	for(ArrayList<Object> row: games) {
    		if((int)row.get(1) == gameID) {
    			curGame.add(row);
    		}
    	}
    	return curGame;
    }
    
    /**
     * how the button must behave
     */
    private void handleJoinGame(int newGameID) {
        if (myaccount.hostplayer(getAccount(), newGameID) || (!myaccount.hostplayer(getAccount(), newGameID) && myaccount.patternsCreated(getAccount(), newGameID))) {
            gameController.getGameModel().setGameID(newGameID);
            gameController.getGameModel().selectPlayerIds();

            gameController.getGameModel().selectWholeGame();
            cardController.getDBcards();

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
        } else {
            alert();
        }
    }

    /**
     * show a alert
     */
    private void alert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Game is nog niet gereed");
        alert.setHeaderText("WAARSCHUWING");
        alert.setContentText("Windowpattern keuzes zijn nog niet gemaakt,\n wij vragen u vriendelijk om nog heel eventjes te wachten");
        alert.showAndWait();
    }

    public void handleSort(Object sortV) {
        if (gameboolean.equals("Alle spellen")) {
            gameListScreen.showGames(getGames(myaccount.getGames(sortV), sortV.toString()));
        }
        if (gameboolean.equals("Mijn spellen")) {
            gameListScreen.showGames(getGames(myaccount.getGames(sortV, getAccount()), sortV.toString()));
        }
    }

    public void showGames() {
        gameListScreen.showGames(getGames(myaccount.getGames(), "ID"));
        myGUI.changePane(gameListScreen);
    }

    public void logOut() {
        myGUI.changePane(startpane);
        this.accountname = null;
    }

    private void setAccount(String AC) {
        this.accountname = myaccount.getUsername(AC);
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
