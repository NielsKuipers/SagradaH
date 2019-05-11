package queries;

import java.util.ArrayList;

import controller.DatabaseController;

public class InviteHandleQueries {
	StandardQuerie standardQuerie;
	DatabaseController databaseController = new DatabaseController();

	public InviteHandleQueries() {
		standardQuerie = databaseController.returnStandardQuerie();
	}
	
	// heeft username nodig (uit inlog klasse?)
	public void setupGame() {
		standardQuerie.updateQuery("INSERT INTO player VALUES (?,?)", "test test1");
	}

	// geeft speler lijst
	public ArrayList<ArrayList<Object>> getPlayers() {
		return standardQuerie.selectQuery("Select username, password FROM account");
		
	}
	
	public ArrayList<ArrayList<Object>> getJoinedPlayers() {
		return standardQuerie.selectQuery("Select username FROM player", " WHERE game_idgame=?", "" + 2 + "");
		
	}

	public ArrayList<ArrayList<Object>> getAllDicesOnField(int id) {
		return standardQuerie.selectQuery(
				"SELECT gamedie.diecolor, gamedie.eyes, playerframefield.position_x, playerframefield.position_y FROM playerframefield INNER JOIN gamedie ON gamedie.dienumber = playerframefield.dienumber",
				" WHERE playerframefield.player_idplayer=?", "" + id + "");
	}
}
