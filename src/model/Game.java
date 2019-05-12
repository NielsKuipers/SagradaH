package model;

import java.util.ArrayList;

import queries.GameQuery;

public class Game {

	int gameId = 2;
	ArrayList<Player> players = new ArrayList<>();

	String accountName = "Gijs";

	private GameQuery gameQuery;

	public Game(GameQuery gameQuery) {
		this.gameQuery = gameQuery;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public Player getPlayer(int i) {
		return players.get(i);
	}

	// get all the player ids in the game and fix that the main player has the first
	// window
	public void selectPlayerIds() {
		ArrayList<ArrayList<Object>> result = gameQuery.getPlayerIdsAndNames(gameId);
		int playerLocation = 0;
		boolean accountPlaced = false;
		boolean stop = false;
		int playerAmount = result.size();

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

		selectAllWindowsForAllPlayers(playerAmount);
		//selectwindowOptions();
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
		}

	}

	// make all windows for all players
	public void selectAllWindowsForAllPlayers(int amountOfPlayers) {
		for (int i = 0; i < amountOfPlayers; i++) {
			players.get(i).selectWindow();
		}
	}

}
