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
	private String username;
	private int gameID;

	public InviteHandleQueries(StandardQuerie standardQuerie) {
		this.standardQuerie = standardQuerie;
		hostUsername = "Lucas";
		username = "Gijs";
	}
	
	// gameID ophalen en int returnen
		public int getGameIDint() {
			return (int) getGameID().get(0).get(0);
		}
		
	
	//////////////insert queries////////////////////////////
	
	// maakt setup voor nieuwe game.  heeft username nodig (uit inlog klasse?)
	public void setupGame(String color) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		currentDate = dateFormat.format(date);
		
		// nieuwe gameID aanmaken en gameid opslaan
		standardQuerie.updateQuery("INSERT INTO game(creationdate) VALUES(?)",""+currentDate+"");
		gameID = getGameIDint();
		
		// gamedie toevoegen
		standardQuerie.updateQuery("INSERT INTO gamedie(idgame, dienumber, diecolor) SELECT ?, number, color FROM die", ""+gameID+"");
		
		// betaalstenen toevoegen
		for(int i = 1; i < 25; i++) {
			standardQuerie.updateQuery("INSERT INTO gamefavortoken(idgame, idfavortoken) VALUES(?,?)", ""+gameID+"\0"+i+"");
		}
		
		// HOST player aanmaken
		standardQuerie.updateQuery("INSERT INTO player(username, game_idgame, playstatus_playstatus, seqnr, isCurrentPlayer, private_objectivecard_color) VALUES (?,?,?,?,?,?)", ""+hostUsername+"\0"+gameID+"\0uitdager\0"+seqNR+"\0 1\0"+color+"");
		seqNR++;
	}
	
	// invite speler
	public void invitePlayer(String username, String color) {
		standardQuerie.updateQuery("INSERT INTO player(username, game_idgame, playstatus_playstatus, seqnr, isCurrentPlayer, private_objectivecard_color) VALUES (?,?,?,?,?,?)", ""+username+"\0"+gameID+"\0uitgedaagde\0"+seqNR+"\0 0\0"+color+"");
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
	
	// controlleer of speler al een openstaande uitdaging heeft van de host
	public boolean unAnsweredChallenges(String invitedUsername) {
		
		ArrayList<ArrayList<Object>> hostGameIDs = standardQuerie.selectQuery("SELECT game_idgame FROM player", " WHERE username=? AND playstatus_playstatus=?", ""+hostUsername+"\0uitdager");
		
		for(int i =0; i <hostGameIDs.size(); i++) {
			int id = (int) hostGameIDs.get(i).get(0);
			try {
				ArrayList<ArrayList<Object>> playercheck = standardQuerie.selectQuery("SELECT game_idgame FROM player", " WHERE username=? AND playstatus_playstatus=? AND game_idgame=?", ""+invitedUsername+"\0uitgedaagde\0"+id+"");
				if(playercheck.get(0).get(0) != null) {
					return false;
				}
			}
			catch(Exception e) {
			}
		}
		return true;
	}
	
	// controlleer of de speler al de uitdaging geaccepteerd heeft
	public boolean alreadyAcceptedInvite(String username) {
		ArrayList<ArrayList<Object>> playercheck = standardQuerie.selectQuery("SELECT game_idgame FROM player", " WHERE username=? AND game_idgame=? AND playstatus_playstatus=?", ""+username+"\0"+gameID+"\0geaccepteerd");	
		try {
			if(playercheck.get(0).get(0) != null) {
				return false;
			}
		}catch(Exception e) {
		}
		return true;
	}
		
	
	// geeft gejoinde spelerlijst
	public ArrayList<ArrayList<Object>> getJoinedPlayers() {
		return standardQuerie.selectQuery("SELECT username FROM player", " WHERE game_idgame=? AND (playstatus_playstatus=? OR playstatus_playstatus=?)", ""+gameID+"\0geaccepteerd\0uitdager");
	}
	
	// nieuwe GameID ophalen
	private ArrayList<ArrayList<Object>> getGameID() {
		return standardQuerie.selectQuery("SELECT idgame FROM game", " WHERE creationdate=?", ""+currentDate+"");
	}
	
	// speler aantal ophalen
	public ArrayList<ArrayList<Object>> getInvitedPlayerCount() {
		return standardQuerie.selectQuery("SELECT COUNT(idplayer) FROM player", " WHERE game_idgame=?", ""+gameID+"");
	}
	
	// inviteGetLijst ophalen
	public ArrayList<ArrayList<Object>> getInviteGetList(){
		return standardQuerie.selectQuery("SELECT game_idgame FROM player", " WHERE username=? AND playstatus_playstatus=?", ""+username+"\0uitgedaagde");
	}
	
	// haalt uitdager naam op om toe te voegen aan invitegetlist screen
	public ArrayList<ArrayList<Object>> getInviter(int gameid){
		return standardQuerie.selectQuery("SELECT username, game_idgame FROM player", " WHERE game_idgame=? AND playstatus_playstatus=?", ""+gameid+"\0uitdager");
	}

	
	
	
	
}
