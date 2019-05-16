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
	private String username = "Gijs";

	public InviteHandleQueries(StandardQuerie standardQuerie) {
		this.standardQuerie = standardQuerie;
		hostUsername = "Lucas";
	}
	
	// gameID ophalen en int returnen
		public int getGameIDint() {
			int gameID = (int) getGameID().get(0).get(0);
			return gameID;
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
	
	// invite accepteren
	public void acceptInvite(String host, int gameid) {
		standardQuerie.updateQuery("UPDATE player SET playstatus_playstatus=?", "geaccepteerd", " WHERE username=? AND game_idgame=?", ""+username+"\0"+gameid+"");
		
	}
	
	// invite weigeren
	public void declineInvite(String host, int gameid) {
		standardQuerie.updateQuery("UPDATE player SET playstatus_playstatus=?", "geweigerd", " WHERE username=? AND game_idgame=?", ""+username+"\0"+gameid+"");
		
	}

	///////////////select queries/////////////////////////////	
	
	// geeft spelerlijst uit alle accounts behalve hostaccount
	public ArrayList<ArrayList<Object>> getPlayers() {
		return standardQuerie.selectQuery("SELECT username FROM account", " WHERE username!=?", ""+hostUsername+"");
	}
	
	// geeft gejoinde spelerlijst
	public ArrayList<ArrayList<Object>> getJoinedPlayers() {
		return standardQuerie.selectQuery("SELECT username FROM player", " WHERE game_idgame=? AND (playstatus_playstatus=? OR playstatus_playstatus=?)", ""+getGameIDint()+"\0geaccepteerd\0uitdager");
	}
	
	// nieuwe GameID ophalen
	public ArrayList<ArrayList<Object>> getGameID() {
		return standardQuerie.selectQuery("SELECT idgame FROM game", " WHERE creationdate=?", ""+currentDate+"");
	}
	
	// speler aantal ophalen
	public ArrayList<ArrayList<Object>> getInvitedPlayerCount() {
		return standardQuerie.selectQuery("SELECT COUNT(idplayer) FROM player", " WHERE game_idgame=?", ""+getGameIDint()+"");
	}
	
	// inviteGetLijst ophalen
	public ArrayList<ArrayList<Object>> getInviteGetList(){
		return standardQuerie.selectQuery("SELECT game_idgame FROM player", " WHERE username=? AND playstatus_playstatus=?", ""+username+"\0uitgedaagde");
	}
	
	public ArrayList<ArrayList<Object>> getInviter(int gameid){
		return standardQuerie.selectQuery("SELECT username, game_idgame FROM player", " WHERE game_idgame=? AND playstatus_playstatus=?", ""+gameid+"\0uitdager");
	}

	
	
	
	
}
