package model;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import queries.PlayerQuery;

public class Player {

	private int idPlayer;
	private WindowPattern windowPattern;
	private Color playerColor;

	private PlayerQuery playerQuery;

	public Player(PlayerQuery playerQuery) {
		this.playerQuery = playerQuery;
	}

	public WindowPattern getWindowPatternPlayer() {
		return windowPattern;
	}

	public void givePlayerWindowPattern(WindowPattern windowPattern) {
		this.windowPattern = windowPattern;
	}

	void setPlayerId(int id) {
		idPlayer = id;
	}

	public int getPlayerId() {
		return idPlayer;
	}

	/**
	 * @param idGame = id of the game get all the information about a windowpattern
	 *               and add it to the model
	 */
	void selectWindow(int idGame) {
		ArrayList<ArrayList<Object>> result = playerQuery.getWindowId(idPlayer);
		try {
			if (!result.isEmpty()) {
				windowPattern.setId(Integer.valueOf(String.valueOf(result.get(0).get(0))));
				windowPattern.selectAllFields();
				windowPattern.selectAllDicesOnField(idPlayer, idGame);
				windowPattern.selectDifficulty();
				selectPlayerName();
				selectPlayerScore();
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	private void selectPlayerName() {
		ArrayList<ArrayList<Object>> result = playerQuery.getPlayerName(idPlayer);
		windowPattern.setPlayerName("Naam: " + result.get(0).get(0));
	}

	private void selectPlayerScore() {
		ArrayList<ArrayList<Object>> result = playerQuery.getPlayerScore(idPlayer);
		windowPattern.setPlayerScore("Score: " + result.get(0).get(0));
	}

	public void updateWindowId(int windowId) {
		playerQuery.updateWindowID(idPlayer, windowId);
	}

	/**
	 * check if this player is has turn
	 * @return true or false
	 */
	public boolean selectCurrentPlayer() {
		ArrayList<ArrayList<Object>> result = playerQuery.getIsCurrentPlayer(idPlayer);
		int currentPlayer = 0;
		if (!result.isEmpty()) {
			currentPlayer = Integer.valueOf(String.valueOf(result.get(0).get(0)));
		}
		return currentPlayer == 1;
	}

	int selectSqnr() {
		ArrayList<ArrayList<Object>> result = playerQuery.getSqnrPlayer(idPlayer);
		return Integer.valueOf(String.valueOf(result.get(0).get(0)));
	}

	void updateSqnr(int sqnr) {
		playerQuery.updateSqnrPlayer(idPlayer, sqnr);
	}

	void updateQurrentPlayer(int isQurrent) {
		playerQuery.updateIsCurrentPlayer(idPlayer, isQurrent);
	}

	public void setDiceOnWindowPattern(int posX, int posY, int dienumber, String diecolor) {
		playerQuery.updateDiceOnWindowPattern(idPlayer, posX, posY, dienumber, diecolor);
	}

	/**
	 * @param posX = position x of field
	 * @param posY = position y of field
	 * @param dienumber = die number
	 * @param diecolor = diecolor
	 * @param inFirstTurn = 1: firsturn  0: secondturn
	 * @param idGame = id of game
	 * add a dice to playerframefield and give inFirstTurn
	 */
	public void setDiceOnWindowPatternAndGiveFirstTurn(int posX, int posY, int dienumber, String diecolor,
			int inFirstTurn, int idGame) {
		playerQuery.updateDiceOnWindowPattern(idPlayer, posX, posY, dienumber, diecolor);
		playerQuery.updateFirstTurnDice(idGame, dienumber, diecolor, inFirstTurn);
	}

	public void removeDiceOnWindowPattern(int dienumber, String diecolor) {
		playerQuery.removeDiceOnWindowPattern(idPlayer, dienumber, diecolor);
	}

}
