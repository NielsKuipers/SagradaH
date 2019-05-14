package queries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class InviteHandleQueries {
	private StandardQuerie standardQuerie;
	private String currentDate;
	private int seqNR = 1;
	private String hostUsername;

	public InviteHandleQueries(StandardQuerie standardQuerie) {
		this.standardQuerie = standardQuerie;
		hostUsername = "Pasa";
	}
	
	
	//////////////insert queries////////////////////////////
	
	// heeft username nodig (uit inlog klasse?)
	public void setupGame(String color) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		currentDate = dateFormat.format(date);
		
		// nieuwe gameID aanmaken
		standardQuerie.updateQuery("INSERT INTO game(creationdate) VALUES(?)",""+currentDate+"");
		
		// HOST player aanmaken
		standardQuerie.updateQuery("INSERT INTO player(username, game_idgame, playstatus_playstatus, seqnr, isCurrentPlayer, private_objectivecard_color) VALUES (?,?,?,?,?,?)", ""+hostUsername+"\0"+getGameIDint()+"\0uitdager\0"+seqNR+"\0 1\0"+color+"");
		seqNR++;
		// nieuw ..
	}
	
	// invite speler
	public void invitePlayer(String username, String color) {
		standardQuerie.updateQuery("INSERT INTO player(username, game_idgame, playstatus_playstatus, seqnr, isCurrentPlayer, private_objectivecard_color) VALUES (?,?,?,?,?,?)", ""+username+"\0"+getGameIDint()+"\0uitgedaagde\0"+seqNR+"\0 0\0"+color+"");
		seqNR++;
	}

	///////////////select queries/////////////////////////////	
	
	// geeft spelerlijst
	public ArrayList<ArrayList<Object>> getPlayers() {
		return standardQuerie.selectQuery("SELECT username, password FROM account", " WHERE username!=?", "+hostUsername+");
	}
	
	// geeft gejoinde spelerlijst
	public ArrayList<ArrayList<Object>> getJoinedPlayers() {
		return standardQuerie.selectQuery("SELECT username FROM player", " WHERE game_idgame=? AND (playstatus_playstatus=? OR playstatus_playstatus=?)", ""+getGameIDint()+"\0geaccepteerd\0uitdager");
	}
	
	// nieuwe GameID ophalen
	public ArrayList<ArrayList<Object>> getGameID() {
		return standardQuerie.selectQuery("SELECT idgame FROM game", " WHERE creationdate=?", ""+currentDate+"");
	}
	
	public ArrayList<ArrayList<Object>> getInvitedPlayerCount() {
		return standardQuerie.selectQuery("SELECT COUNT(idplayer) FROM player", " WHERE game_idgame=?", ""+getGameIDint()+"");
	}
	
	// gameID ophalen en int returnen
	public int getGameIDint() {
		int gameID = (int) getGameID().get(0).get(0);
		return gameID;
	}
	
	
	
}
