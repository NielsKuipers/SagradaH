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
		// nieuwe gameID aanmaken
		
		// nieuwe player kollom aanmaken
		standardQuerie.updateQuery("INSERT INTO player VALUES (?,?)", "test test1");
		
		// nieuw ..
	}

	// geeft spelerlijst
	public ArrayList<ArrayList<Object>> getPlayers() {
		return standardQuerie.selectQuery("SELECT username, password FROM account");
	}
	
	// geeft gejoinde spelerlijst
	public ArrayList<ArrayList<Object>> getJoinedPlayers() {
		return standardQuerie.selectQuery("SELECT username FROM player", "WHERE game_idgame=?", "" + 2 + "");
	}
}
