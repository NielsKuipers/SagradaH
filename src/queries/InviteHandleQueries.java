package queries;

import java.util.ArrayList;

import controller.DatabaseController;

public class InviteHandleQueries {
	StandardQuerie standardQuerie;

	public InviteHandleQueries(StandardQuerie standardQuerie) {
		this.standardQuerie = standardQuerie;
	}
	
	
	//////////////insert queries////////////////////////////
	
	// heeft username nodig (uit inlog klasse?)
	public void setupGame() {
		// nieuwe gameID aanmaken
		
		// nieuwe player kollom aanmaken
		standardQuerie.updateQuery("INSERT INTO player VALUES (?,?)", "test/0test1");
		
		// nieuw ..
	}
	
	//add player to game
	public void addPlayer(String username) {
		int gameID = 2;
		
	}
	
	
	// invite speler
	public void invitePlayer(String username) {
		int gameID = 2;
		System.out.println("geklikt");
		standardQuerie.updateQuery("INSERT INTO player(username, game_idgame, playstatus_playstatus, isCurrentPlayer, private_objectivecard_color) VALUES (?,?,?,?,?)", ""+username+"\0"+gameID+"\0uitgedaagde\0 0\0groen");
	}

	///////////////select queries/////////////////////////////	
	
	// geeft spelerlijst
	public ArrayList<ArrayList<Object>> getPlayers() {
		return standardQuerie.selectQuery("SELECT username, password FROM account");
	}
	
	// geeft gejoinde spelerlijst
	public ArrayList<ArrayList<Object>> getJoinedPlayers() {
		return standardQuerie.selectQuery("SELECT username FROM player", " WHERE game_idgame=?", "2");
	}
	
	
	
}
