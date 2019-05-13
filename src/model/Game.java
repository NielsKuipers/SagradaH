package model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import queries.GameQuery;

public class Game {

	private int gameId = 2;
	private ArrayList<Player> players = new ArrayList<>();

	private String accountName = "Gijs";
	private StringProperty gameRound;

	private GameQuery gameQuery;

	public Game(GameQuery gameQuery) {
		this.gameQuery = gameQuery;
		gameRound = new SimpleStringProperty(this, "round", "empty");
	}
	
	public void setRound(String round) {
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

	// give all the players the right id
	public void selectPlayerIds() {
		ArrayList<ArrayList<Object>> result = gameQuery.getPlayerIdsAndNames(gameId);
		int playerLocation = 0;
		boolean accountPlaced = false;
		boolean stop = false;

		for (int i = 0; i < result.size(); i++) {
			if (accountPlaced) {
				players.get(playerLocation).setPlayerId(Integer.valueOf(String.valueOf(result.get(i).get(0))));
				playerLocation++;
			}
			if (String.valueOf(result.get(i).get(1)).equals(accountName)) {
				players.get(playerLocation).setPlayerId(Integer.valueOf(String.valueOf(result.get(i).get(0))));
				playerLocation++;
				accountPlaced = true;
			}
		}

		for (int i = 0; i < result.size(); i++) {
			if (!String.valueOf(result.get(i).get(1)).equals(accountName) && !stop) {
				players.get(playerLocation).setPlayerId(Integer.valueOf(String.valueOf(result.get(i).get(0))));
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

	// make all windows for all players
	public void selectAllWindowsForAllPlayers() {
		selectPlayerIds();
		ArrayList<ArrayList<Object>> result = gameQuery.getPlayerIdsAndNames(gameId);
		int amountOfPlayers = result.size();
		for (int j = 0; j < players.size(); j++) {
			players.get(j).getWindowPatternPlayer().setPlayerName("Naam: NIEMAND!!!");
		}
		for (int i = 0; i < amountOfPlayers; i++) {
			players.get(i).selectWindow();
		}
		selectRound();
	}
	
	public void selectRound() {
		ArrayList<ArrayList<Object>> result = gameQuery.getRound(gameId);
		int round = Integer.valueOf(String.valueOf(result.get(0).get(0)));
		round++;
		setRound(String.valueOf(round));
	}

}
