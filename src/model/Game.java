package model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import queries.GameQuery;

public class Game {

	private int gameId = 2;
	private ArrayList<Player> players = new ArrayList<>();

	private String accountName = "Gijs";
	private StringProperty gameRound;

	private GameQuery gameQuery;

	private DiceOnTable diceOnTableModel;

	public Game(GameQuery gameQuery, DiceOnTable diceOnTableModel) {
		this.gameQuery = gameQuery;
		this.diceOnTableModel = diceOnTableModel;
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

	// give all the players the right id
	private void selectPlayerIds() {
		ArrayList<ArrayList<Object>> result = gameQuery.getPlayerIdsAndNames(gameId);
		int playerLocation = 0;
		boolean accountPlaced = false;
		boolean stop = false;

		for (ArrayList<Object> objectArrayList : result) {
			if (accountPlaced) {
				players.get(playerLocation).setPlayerId(Integer.valueOf(String.valueOf(objectArrayList.get(0))));
				playerLocation++;
			}
			if (String.valueOf(objectArrayList.get(1)).equals(accountName)) {
				players.get(playerLocation).setPlayerId(Integer.valueOf(String.valueOf(objectArrayList.get(0))));
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

	// make all windows for all players
	public void selectAllWindowsForAllPlayers() {
		selectPlayerIds();
		ArrayList<ArrayList<Object>> result = gameQuery.getPlayerIdsAndNames(gameId);
		int amountOfPlayers = result.size();
		for (Player player : players) {
			player.getWindowPatternPlayer().setPlayerName("Naam: NIEMAND!!!");
		}
		for (int i = 0; i < amountOfPlayers; i++) {
			players.get(i).selectWindow();
		}
		selectRound();
		selectAllDicesOnTable();
	}

	private void selectRound() {
		ArrayList<ArrayList<Object>> result = gameQuery.getRound(gameId);
		if(result.get(0).get(0) == null) {
			setRound("1");
		}else {
			int round = Integer.valueOf(String.valueOf(result.get(0).get(0)));
			round++;
			setRound(String.valueOf(round));
		}
		
	}

	private void selectAllDicesOnTable() {
		ArrayList<ArrayList<Object>> result = gameQuery.getRound(gameId);
		int round = Integer.valueOf(String.valueOf(result.get(0).get(0)));
		round++;

		ArrayList<ArrayList<Object>> result2 = gameQuery.getAllDicesFromAllPlayers(gameId);
		//System.out.println(result2);

		ArrayList<ArrayList<Object>> result3 = gameQuery.getAllDicesFromOneRound(gameId, round);
		//System.out.println(result3);

		ArrayList<ArrayList<Object>> diceOnTable = new ArrayList<>();
		
		for (ArrayList<Object> dicesFromRound: result3) {
			if(!result2.contains(dicesFromRound)) {
				diceOnTable.add(dicesFromRound);
			}
		}
		
		for (ArrayList<Object> dices : diceOnTable) {
			ArrayList<ArrayList<Object>> eyes = gameQuery.getEyeOfDice(gameId, Integer.valueOf(String.valueOf(dices.get(0))), String.valueOf(dices.get(1)));
			Dice dice = new Dice(Integer.valueOf(String.valueOf(eyes.get(0).get(0))), makeColorFromQuerie(dices.get(1)), Integer.valueOf(String.valueOf(dices.get(0))));
			dice.setEyes(Integer.valueOf(String.valueOf(eyes.get(0).get(0))));
			diceOnTableModel.addDiceToTable(dice);
		}
		
		
	}
	
	private Color makeColorFromQuerie(Object c) {
		return getColorFromQuery(c);
	}

	static Color getColorFromQuery(Object c) {
		String color = String.valueOf(c);
		switch (color) {
			case "geel":
				return Color.YELLOW;
			case "groen":
				return Color.LIGHTGREEN;
			case "blauw":
				return Color.CORNFLOWERBLUE;
			case "paars":
				return Color.MAGENTA;
			case "rood":
				return Color.RED;
			case "":
				return Color.LIGHTGRAY;
		}
		return Color.LIGHTGRAY;
	}

}
