package model;

import java.util.ArrayList;
import java.util.Random;

import controller.WindowController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import queries.GameQuery;

public class Game {

	private int gameId = 0;
	private ArrayList<Player> players = new ArrayList<>();

	private String accountName = "";
	private StringProperty gameRound;

	private Random r = new Random();

	private GameQuery gameQuery;

	private DiceOnTable diceOnTableModel;

	private WindowController windowController;

	public Game(GameQuery gameQuery, DiceOnTable diceOnTableModel, WindowController windowController) {
		this.gameQuery = gameQuery;
		this.diceOnTableModel = diceOnTableModel;
		this.windowController = windowController;
		gameRound = new SimpleStringProperty(this, "round", "empty");
	}

	private void setRound(String round) {
		gameRound.set("Ronde: " + round);
	}

	public final StringProperty gameRoundProperty() {
		return gameRound;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public Player getPlayer(int i) {
		return players.get(i);
	}

	public int getGameID() {
		return gameId;
	}

	// give all the players the right id
	public void selectPlayerIds() {
		ArrayList<ArrayList<Object>> result = gameQuery.getPlayerIdsAndNames(gameId);
		int playerLocation = 0;
		boolean accountPlaced = false;
		boolean stop = false;

		for (ArrayList<Object> objects : result) {
			if (accountPlaced) {
				players.get(playerLocation).setPlayerId(Integer.valueOf(String.valueOf(objects.get(0))));
				playerLocation++;
			}
			if (String.valueOf(objects.get(1)).equals(accountName)) {
				players.get(playerLocation).setPlayerId(Integer.valueOf(String.valueOf(objects.get(0))));
				playerLocation++;
				accountPlaced = true;
			}
		}

		for (ArrayList<Object> objects : result) {
			if (!String.valueOf(objects.get(1)).equals(accountName) && !stop) {
				players.get(playerLocation).setPlayerId(Integer.valueOf(String.valueOf(objects.get(0))));
				playerLocation++;
			} else {
				stop = true;
			}
		}
	}

	// make the windowChoose screen
	public void selectwindowOptions() {
		// get the idPlayer of the main player
		ArrayList<ArrayList<Object>> result = gameQuery.getMainPlayerId(gameId, accountName);
		int idPlayer = Integer.valueOf(String.valueOf(result.get(0).get(0)));
		// get the window options for main player and make the windows
		ArrayList<ArrayList<Object>> result2 = gameQuery.getWindowOptions(idPlayer);
		for (int i = 0; i < result2.size(); i++) {
			players.get(i).getWindowPatternPlayer().setId(Integer.valueOf(String.valueOf(result2.get(i).get(0))));
			players.get(i).getWindowPatternPlayer().selectAllFields();
			players.get(i).getWindowPatternPlayer().selectDifficulty();
			players.get(i).getWindowPatternPlayer().setPlayerName("Kaart: " + (i + 1));
			players.get(i).getWindowPatternPlayer().setPlayerScore("");
		}
		selectPlayerIds();
	}
	
	public void makeGameEmpty() {
		diceOnTableModel.removeAllDicesFromTable();
		for (Player player : players) {
			player.setPlayerId(0);
			player.getWindowPatternPlayer().setId(0);
			player.getWindowPatternPlayer().makeWindowEmpty();
		}
	}

	// get everything from the game
	public void selectWholeGame() {
		// selectPlayerIds();
		ArrayList<ArrayList<Object>> result = gameQuery.getPlayerIdsAndNames(gameId);
		int amountOfPlayers = result.size();
		for (Player player : players) {
			player.getWindowPatternPlayer().setPlayerName("Naam: NIEMAND!!!");
			player.getWindowPatternPlayer().setPlayerScore("Score: 0");
		}
		for (int i = 0; i < amountOfPlayers; i++) {
			players.get(i).selectWindow(gameId);
		}
		selectRound();
		selectAllDicesOnTable();
		checkWhoIsQurrentPlayer();
	}

	// get the dice with highest roundtrack and fill the round
	private void selectRound() {
		ArrayList<ArrayList<Object>> result = gameQuery.getRound(gameId);
		if (result.isEmpty()) {
			setRound("1");
		} else {
			int round = Integer.valueOf(String.valueOf(result.get(0).get(0)));
			round++;
			setRound(String.valueOf(round));
		}

	}

	// show all the dices on the table
	private void selectAllDicesOnTable() {
		diceOnTableModel.removeAllDicesFromTable();
		// ask for the eye value for every dice on the table, make a dice and add it to
		// the REAL table
		ArrayList<ArrayList<Object>> diceOnTable = getAllDicesOnTable();
		for (ArrayList<Object> dices : diceOnTable) {
			ArrayList<ArrayList<Object>> eyes = gameQuery.getEyeOfDice(gameId,
					Integer.valueOf(String.valueOf(dices.get(0))), String.valueOf(dices.get(1)));
			Dice dice = new Dice(Integer.valueOf(String.valueOf(eyes.get(0).get(0))), makeColorFromQuerie(dices.get(1)),
					Integer.valueOf(String.valueOf(dices.get(0))));
			dice.setEyes(Integer.valueOf(String.valueOf(eyes.get(0).get(0))));
			diceOnTableModel.addDiceToTable(dice);
		}
	}

	private ArrayList<ArrayList<Object>> getAllDicesOnTable() {
		// check which round you are in
		ArrayList<ArrayList<Object>> result = gameQuery.getRound(gameId);
		int round = 0;
		if (result.isEmpty()) {
			round = 1;
		} else {
			round = Integer.valueOf(String.valueOf(result.get(0).get(0)));
			round++;
		}

		// get all the dices from all the players
		ArrayList<ArrayList<Object>> result2 = gameQuery.getAllDicesFromAllPlayers(gameId);

		// get all the dices that exist on certain round
		ArrayList<ArrayList<Object>> result3 = gameQuery.getAllDicesFromOneRound(gameId, round);

		ArrayList<ArrayList<Object>> diceOnTable = new ArrayList<>();

		// if none of the players have the dice put put it in arraylist diceOnTable
		for (ArrayList<Object> dicesFromRound : result3) {
			if (!result2.contains(dicesFromRound)) {
				diceOnTable.add(dicesFromRound);
			}
		}

		return diceOnTable;
	}

	public void throwAgainWithSameDicesOnTable() {
		ArrayList<ArrayList<Object>> diceOnTable = getAllDicesOnTable();

		ArrayList<ArrayList<Object>> result = gameQuery.getRound(gameId);
		int round = 0;
		if (result.isEmpty()) {
			round = 1;
		} else {
			round = Integer.valueOf(String.valueOf(result.get(0).get(0)));
			round++;
		}

		for (ArrayList<Object> dices : diceOnTable) {
			gameQuery.updateRollDice(Integer.valueOf(String.valueOf(dices.get(0))), String.valueOf(dices.get(1)),
					gameId, (r.nextInt((6 - 1) + 1) + 1), round);
		}
		selectAllDicesOnTable();

	}

	// make a color from a querie
	private Color makeColorFromQuerie(Object c) {
		String color = String.valueOf(c);
		if (color.equals("geel")) {
			return Color.YELLOW;
		} else if (color.equals("groen")) {
			return Color.LIGHTGREEN;
		} else if (color.equals("blauw")) {
			return Color.CORNFLOWERBLUE;
		} else if (color.equals("paars")) {
			return Color.MAGENTA;
		} else if (color.equals("rood")) {
			return Color.RED;
		} else if (color.equals("")) {
			return Color.LIGHTGRAY;
		}
		return Color.LIGHTGRAY;
	}

	// check which player is the qurrentplayer
	private void checkWhoIsQurrentPlayer() {
		ArrayList<ArrayList<Object>> result = gameQuery.getPlayerIdsAndNames(gameId);
		for (int j = 0; j < players.size(); j++) {
			players.get(j).getWindowPatternPlayer().setBackground(Color.WHITE);
		}
		for (int i = 0; i < result.size(); i++) {
			switch (i) {
			case 0:
				players.get(i).getWindowPatternPlayer().setBackground(Color.BLUE);
				break;
			case 1:
				players.get(i).getWindowPatternPlayer().setBackground(Color.RED);
				break;
			case 2:
				players.get(i).getWindowPatternPlayer().setBackground(Color.GREEN);
				break;
			case 3:
				players.get(i).getWindowPatternPlayer().setBackground(Color.YELLOW);
				break;
			default:
				break;
			}
			if (players.get(i).selectCurrentPlayer()) {
				players.get(i).getWindowPatternPlayer().setBackground(Color.PINK);
			}
		}
	}

	// give the turn to the next player
	public void giveTurnToNextPlayer() {
		// get the amount of players in the game
		ArrayList<ArrayList<Object>> result = gameQuery.getPlayerIdsAndNames(gameId);
		// get the sqnr of main player
		int sqnrPlayer = players.get(0).selectSqnr();
		if (players.get(0).selectCurrentPlayer()) {
			if (result.size() == 2) {
				if (sqnrPlayer == 1) {
					players.get(0).updateSqnr(4);
					players.get(0).updateQurrentPlayer(0);
					gameQuery.updateTurnPlayerInGameTable(gameId, players.get(1).getPlayerId());
					players.get(1).updateQurrentPlayer(1);
				} else if (sqnrPlayer == 2) {
					players.get(0).updateSqnr(3);
				} else if (sqnrPlayer == 3) {
					players.get(0).updateQurrentPlayer(0);

					gameQuery.updateTurnPlayerInGameTable(gameId, players.get(1).getPlayerId());
					players.get(1).updateQurrentPlayer(1);
				} else if (sqnrPlayer == 4) {
					players.get(0).updateSqnr(2);
					players.get(1).updateSqnr(1);
					players.get(0).updateQurrentPlayer(0);

					gameQuery.updateTurnPlayerInGameTable(gameId, players.get(1).getPlayerId());
					players.get(1).updateQurrentPlayer(1);
				}

			} else if (result.size() == 3) {
				if (sqnrPlayer == 1) {
					players.get(0).updateSqnr(6);
					players.get(0).updateQurrentPlayer(0);

					gameQuery.updateTurnPlayerInGameTable(gameId, players.get(1).getPlayerId());
					players.get(1).updateQurrentPlayer(1);
				} else if (sqnrPlayer == 2) {
					players.get(0).updateSqnr(5);
					players.get(0).updateQurrentPlayer(0);

					gameQuery.updateTurnPlayerInGameTable(gameId, players.get(1).getPlayerId());
					players.get(1).updateQurrentPlayer(1);
				} else if (sqnrPlayer == 3) {
					players.get(0).updateSqnr(4);
				} else if (sqnrPlayer == 4) {
					players.get(0).updateQurrentPlayer(0);

					gameQuery.updateTurnPlayerInGameTable(gameId, players.get(2).getPlayerId());
					players.get(2).updateQurrentPlayer(1);
				} else if (sqnrPlayer == 5) {
					players.get(0).updateQurrentPlayer(0);

					gameQuery.updateTurnPlayerInGameTable(gameId, players.get(2).getPlayerId());
					players.get(2).updateQurrentPlayer(1);
				} else if (sqnrPlayer == 6) {
					players.get(0).updateSqnr(3);
					players.get(1).updateSqnr(1);
					players.get(2).updateSqnr(2);
					players.get(0).updateQurrentPlayer(0);

					gameQuery.updateTurnPlayerInGameTable(gameId, players.get(1).getPlayerId());
					players.get(1).updateQurrentPlayer(1);
				}

			} else if (result.size() == 4) {
				if (sqnrPlayer == 1) {
					players.get(0).updateSqnr(8);
					players.get(0).updateQurrentPlayer(0);

					gameQuery.updateTurnPlayerInGameTable(gameId, players.get(1).getPlayerId());
					players.get(1).updateQurrentPlayer(1);
				} else if (sqnrPlayer == 2) {
					players.get(0).updateSqnr(7);
					players.get(0).updateQurrentPlayer(0);

					gameQuery.updateTurnPlayerInGameTable(gameId, players.get(1).getPlayerId());
					players.get(1).updateQurrentPlayer(1);
				} else if (sqnrPlayer == 3) {
					players.get(0).updateSqnr(6);
					players.get(0).updateQurrentPlayer(0);

					gameQuery.updateTurnPlayerInGameTable(gameId, players.get(1).getPlayerId());
					players.get(1).updateQurrentPlayer(1);

				} else if (sqnrPlayer == 4) {
					players.get(0).updateSqnr(5);
				} else if (sqnrPlayer == 5) {
					players.get(0).updateQurrentPlayer(0);

					gameQuery.updateTurnPlayerInGameTable(gameId, players.get(3).getPlayerId());
					players.get(3).updateQurrentPlayer(1);
				} else if (sqnrPlayer == 6) {
					players.get(0).updateQurrentPlayer(0);

					gameQuery.updateTurnPlayerInGameTable(gameId, players.get(3).getPlayerId());
					players.get(3).updateQurrentPlayer(1);
				} else if (sqnrPlayer == 7) {
					players.get(0).updateQurrentPlayer(0);

					gameQuery.updateTurnPlayerInGameTable(gameId, players.get(3).getPlayerId());
					players.get(3).updateQurrentPlayer(1);
				} else if (sqnrPlayer == 8) {
					players.get(0).updateSqnr(4);
					players.get(1).updateSqnr(1);
					players.get(2).updateSqnr(2);
					players.get(3).updateSqnr(3);
					players.get(0).updateQurrentPlayer(0);

					gameQuery.updateTurnPlayerInGameTable(gameId, players.get(1).getPlayerId());
					players.get(1).updateQurrentPlayer(1);
				}
			}
		}
		checkWhoIsQurrentPlayer();
	}

	// create a player frame field
	public void createPlayerFrameField(int idPlayer, int idGame) {
		for (int x = 1; x < 6; x++) {
			for (int y = 1; y < 5; y++) {
				gameQuery.insertOneLineForPlayerFrameField(idPlayer, x, y, idGame);
			}
		}
	}

	public void createAllPlayerFrameFields(int idGame, boolean random) {
		ArrayList<ArrayList<Object>> result = gameQuery.getPlayerIdsAndNames(gameId);
		for (int i = 0; i < result.size(); i++) {
			createPlayerFrameField(Integer.valueOf(String.valueOf(result.get(i).get(0))), idGame);
		}

		if (!random) {
			switch (result.size()) {
			case 2:
				givePlayerCardOption((int) result.get(0).get(0), (int) result.get(1).get(0), 0, 0);
				break;
			case 3:
				givePlayerCardOption((int) result.get(0).get(0), (int) result.get(1).get(0), (int) result.get(2).get(0),
						0);
				break;
			case 4:
				givePlayerCardOption((int) result.get(0).get(0), (int) result.get(1).get(0), (int) result.get(2).get(0),
						(int) result.get(3).get(0));
				break;
			default:
				break;
			}
		} else if (random) {
			for (int i = 0; i < result.size(); i++) {
				for (int j = 0; j < 4; j++) {
					createNewRandomPatternCard((int)result.get(i).get(0));
				}
			}
			
		}
	}

	// create ONE random windowpattern
	public void createNewRandomPatternCard(int idPlayer) {
		// create random windowpattern
		WindowPattern windowModel = windowController.createRandomWindow();
		// add new patterncard to db and set difficulty
		gameQuery.insertNewPatternCard(windowController.calculateDifficulty(windowModel));
		// get the patterncardiID
		int idWindow = Integer.valueOf(String.valueOf(gameQuery.getLastWindowId().get(0).get(0)));
		// go through the whole windowmodel and insert all the fields
		insertAllFieldsToPatternCard(windowModel, idWindow);
		// insert patterncard to patterncardoption table
		gameQuery.insertPatternCardToPlayerOption(idWindow, idPlayer);
		// empty the windowmodel
		windowController.makeWindowsGray(windowModel);
	}

	// give ALL the players 4 windowpattern options
	public void givePlayerCardOption(int idPlayer1, int idPlayer2, int idPlayer3, int idPlayer4) {
		ArrayList<Integer> windowIds = new ArrayList<>();
		// fill the arraylist with all possible windowIDs
		for (int i = 1; i < 25; i++) {
			windowIds.add(i);
		}

		// check how many players there are, and give each player 4 window options
		for (int i = 0; i < 4; i++) {
			giveWindowOptions(idPlayer1, idPlayer2, windowIds);

			giveWindowOptions(idPlayer3, idPlayer4, windowIds);

		}

	}

	private void giveWindowOptions(int idPlayer1, int idPlayer2, ArrayList<Integer> windowIds) {
		if (idPlayer1 != 0) {
			int arrayIndex = r.nextInt(windowIds.size());
			gameQuery.insertPatternCardToPlayerOption(windowIds.get(arrayIndex), idPlayer1);
			windowIds.remove(arrayIndex);
		}

		if (idPlayer2 != 0) {
			int arrayIndex = r.nextInt(windowIds.size());
			gameQuery.insertPatternCardToPlayerOption(windowIds.get(arrayIndex), idPlayer2);
			windowIds.remove(arrayIndex);
		}
	}

	// insert ONE field to patterncardfiel table, this is used when making a random
	// window
	private void insertAllFieldsToPatternCard(WindowPattern windowModel, int idWindow) {
		for (int column = 0; column < 5; column++) {
			for (int row = 1; row < 5; row++) {
				gameQuery.insertPatternCardField(idWindow, column + 1, row,
						getColorForQuerie(windowModel.getFieldOfWindow(column, row).getColor()),
						getNumberForQuerie(windowModel.getFieldOfWindow(column, row).getEyes()));
			}
		}
	}

	// make a string for amount of eyes, used when updating or inserting queries
	private String getNumberForQuerie(int number) {
		if (number == 1) {
			return "1";
		} else if (number == 2) {
			return "2";
		} else if (number == 3) {
			return "3";
		} else if (number == 4) {
			return "4";
		} else if (number == 5) {
			return "5";
		} else if (number == 6) {
			return "6";
		} else if (number == 0) {
			return "null";
		}
		return "null";
	}

	// make a string for color, used when updating or inserting queries
	private String getColorForQuerie(Color color) {
		if (color == Color.RED) {
			return "rood";
		} else if (color == Color.YELLOW) {
			return "geel";
		} else if (color == Color.MAGENTA) {
			return "paars";
		} else if (color == Color.LIGHTGREEN) {
			return "groen";
		} else if (color == Color.CORNFLOWERBLUE) {
			return "blauw";
		} else if (color == Color.LIGHTGRAY) {
			return "null";
		}
		return "null";
	}

	// give ONE player a amount of favortokens
	private void givePlayerFavorTokens(int idPlayer, int amount) {
		ArrayList<ArrayList<Object>> result = gameQuery.getLastFavorTokenId(gameId);
		for (int i = 0; i < amount; i++) {
			gameQuery.updateFavorTokenPlayer(idPlayer, gameId, Integer.valueOf(String.valueOf(result.get(i).get(0))));
		}
	}

	// give ALL the players their favortokens
	public void giveAllThePlayersTheirFavorTokens() {
		// get the amount of players
		ArrayList<ArrayList<Object>> result = gameQuery.getPlayerIdsAndNames(gameId);
		for (int i = 0; i < result.size(); i++) {
			// ask for difficulty and give that amount of favortokens to a player
			givePlayerFavorTokens(players.get(i).getPlayerId(),
					players.get(i).getWindowPatternPlayer().getDifficulty());
		}
	}

	// roll the dices
	public void rollTheDices() {
		// get the round
		ArrayList<ArrayList<Object>> result = gameQuery.getRound(gameId);
		int round = 0;
		if (result.isEmpty()) {
			round = 1;
		} else {
			round = Integer.valueOf(String.valueOf(result.get(0).get(0)));
			round++;
		}

		// get the amount of players
		ArrayList<ArrayList<Object>> result3 = gameQuery.getPlayerIdsAndNames(gameId);
		int amountOfPlayers = (result3.size());
		// calculate amount of dices that need to be thrown
		int amountOfDicesThatNeedToBeThrown = (amountOfPlayers * 2) + 1;

		for (int i = 0; i < amountOfDicesThatNeedToBeThrown; i++) {
			// get all the empty dices of a game
			ArrayList<ArrayList<Object>> result2 = gameQuery.getAllEmptyDices(gameId);
			int amountOfDicesInGame = result2.size();

			// choose a random dice
			int indexDice = r.nextInt(amountOfDicesInGame);

			// get random eyes
			int randomEyes = r.nextInt((6 - 1) + 1) + 1;
			int dieNumber = Integer.valueOf(String.valueOf(result2.get(indexDice).get(0)));
			String dieColor = String.valueOf(result2.get(indexDice).get(1));

			// update the dice, the dice has been thrown
			gameQuery.updateRollDice(dieNumber, dieColor, gameId, randomEyes, round);

		}
	}

	public boolean checkIfMainPlayerCanThrowDices() {
		ArrayList<ArrayList<Object>> result = gameQuery.getRound(gameId);
		int round = 0;
		if (result.isEmpty()) {
			round = 1;
		} else {
			round = Integer.valueOf(String.valueOf(result.get(0).get(0)));
			round++;
		}
		
		ArrayList<ArrayList<Object>> result2 = gameQuery.getAllDicesFromOneRound(gameId, round);
		
		
		boolean canThrow = false;
		
		if(players.get(0).selectCurrentPlayer() && players.get(0).selectSqnr() == 1 && result2.isEmpty()) {
			canThrow = true;
		}
		return canThrow;
	}

	public boolean checkIfGameIsOver() {
		ArrayList<ArrayList<Object>> result2 = gameQuery.checkIfThereIsADiceOnRoundtrack10(gameId);
		return !result2.isEmpty();
	}

	public boolean isSecondTurn() {
		ArrayList<ArrayList<Object>> result3 = gameQuery.getPlayerIdsAndNames(gameId);
		int amountOfPlayers = (result3.size());
		int sqnrPlayer = players.get(0).selectSqnr();

		switch (amountOfPlayers) {
		case 2:
			if (sqnrPlayer > 2) {
				return true;
			}
		case 3:
			if (sqnrPlayer > 3) {
				return true;
			}
			break;
		case 4:
			if (sqnrPlayer > 4) {
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}

	public boolean checkIfSameColorDiceIsOnRoundTrack(Color dieColor) {
		ArrayList<ArrayList<Object>> result = gameQuery.getAllTheDifferntColorsFromTheRoundTrack(gameId);
		String stringColor = getColorForQuerie(dieColor);
		for (int i = 0; i < result.size(); i++) {
			if (result.get(i).get(0).toString().equals(stringColor)) {
				return true;
			}
		}
		return false;
	}

	public boolean isRoundTrackEmpty() {
		ArrayList<ArrayList<Object>> result = gameQuery.getAllTheDifferntColorsFromTheRoundTrack(gameId);
		if (result.isEmpty()) {
			return true;
		}
		return false;
	}

	//////////////////////////////////// ENDSCREEN////////////////////////////////////////////////////////////////////////////
	public ArrayList<ArrayList<Object>> getPlayerScores() {
		return gameQuery.getPlayerScores(gameId);
	}
	
	public ArrayList<ArrayList<Object>> getPlayerIds() {
		return gameQuery.getPlayerIdsAndNames(gameId);
	}

	public void setPlayerStatusFinished() {
		gameQuery.setPlayerStatusFinished(gameId);
	}
	
	public void setPoints(int points, int playerID) {
		gameQuery.setScores(points, playerID);
	}

	//////////////////////////////////// RONDEBORD/////////////////////////////////////////////////////////////////////////////

	// returnt rondebord dobbelstenen
	public ArrayList<ArrayList<Object>> getDicesOnRoundBoard(int round) {
		return gameQuery.getDicesOnRoundBoard(round, gameId);
	}

	// verwijdert dobbelsteen van rondebord
	public void removeDice(int diceID, String colorText) {
		gameQuery.removeDice(diceID, colorText, gameId);

	}

	public int getRoundTrackOfDice(int diceID, String colorText) {
		ArrayList<ArrayList<Object>> result = gameQuery.getRoundTrackOfDice(diceID, colorText, gameId);
		int round = Integer.valueOf(String.valueOf(result.get(0).get(0)));
		return round;
	}

	public void addDiceToRoundTrack(int diceID, String colorText, int round) {
		gameQuery.addDiceToRoundTrack(diceID, colorText, round, gameId);
	}
	
	public void setGameID(int gameID) {
		this.gameId = gameID;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
