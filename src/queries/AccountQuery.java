package queries;
import java.util.ArrayList;

public class AccountQuery {
	private StandardQueries standardQuery;
	public AccountQuery(StandardQueries standardQuery) {
		this.standardQuery = standardQuery;
	}
	
	public Boolean Login(String username, String password) {
		if(!username.isEmpty() && !password.isEmpty())
			return !standardQuery.selectQuery("SELECT username, password FROM account", " WHERE username=? AND password=?", username + "\0" + password).isEmpty();
		return false;
	}
	
	public boolean register(String username, String password) {
		if(username.length() >= 3 && password.length() >= 3) {
			if(standardQuery.selectQuery("SELECT username FROM account", " WHERE username=?", username).isEmpty()) {
				standardQuery.updateQuery("INSERT INTO account VALUES(?,?)", username+"\0"+password);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean getPlayers(int idGame, String username){
		return !standardQuery.selectQuery("SELECT username FROM player", " WHERE game_idgame=? AND username=?", "" + idGame + "" + "\0" + username).isEmpty();
	}
	
	public ArrayList<ArrayList<Object>> getGames(){
		return standardQuery.selectQuery("SELECT p.username, g.idgame, g.creationdate, p.playstatus_playstatus FROM game g INNER JOIN player p ON p.game_idgame = g.idgame");
	}
	public ArrayList<ArrayList<Object>> getGames(Object sort){
		String sortV = sort.toString();
		switch(sortV) {
		case "Datum aangemaakt": return standardQuery.selectQuery("SELECT p.username, g.idgame, g.creationdate, p.playstatus_playstatus FROM game g INNER JOIN player p ON p.game_idgame = g.idgame ORDER BY g.creationdate DESC");
		case "ID":return standardQuery.selectQuery("SELECT p.username, g.idgame, g.creationdate, p.playstatus_playstatus FROM game g INNER JOIN player p ON p.game_idgame = g.idgame ORDER BY g.idgame ASC");
		default: return null;
		}
	}
	
	public ArrayList<ArrayList<Object>> getGames(Object sort, String username){
		String sortV = sort.toString();
		switch(sortV) {
		case "Datum aangemaakt": return standardQuery.selectQuery("SELECT p.username, g.idgame, g.creationdate, p.playstatus_playstatus FROM game g INNER JOIN player p ON p.game_idgame = g.idgame", " WHERE p.username=? ORDER BY g.creationdate DESC", username);
		case "ID":return standardQuery.selectQuery("SELECT p.username, g.idgame, g.creationdate, p.playstatus_playstatus FROM game g INNER JOIN player p ON p.game_idgame = g.idgame", " WHERE p.username=? ORDER BY g.idgame ASC", username);
		default: return null;
		}
	}
	
	public boolean hasBeenCanceld(int idGame) {
		if(standardQuery.selectQuery("SELECT distinct playstatus_playstatus FROM player"," WHERE playstatus_playstatus=? AND game_idgame = ?", "afgebroken"+"\0"+""+idGame+"").isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean hasEnded(int idGame) {
		if(standardQuery.selectQuery("SELECT distinct playstatus_playstatus FROM player"," WHERE playstatus_playstatus=? AND game_idgame=?","uitgespeeld"+"\0"+""+idGame+"").isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public boolean iHaveNotChosenWindowPattern(int idGame, String username) {
		if(standardQuery.selectQuery("SELECT patterncard_idpatterncard FROM player"," WHERE username=? AND game_idGame=?",""+ ""+ idGame+""+"\0"+ username).isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean somebodyHasNotChosenWindowPattern(int idGame) {
		if(standardQuery.selectQuery("SELECT username FROM player"," WHERE game_idGame =?",""+idGame+"").size() > standardQuery.selectQuery("SELECT p.patterncard_idpatterncard FROM patterncardoption p" + " INNER JOIN player pl ON pl.idplayer = p.player_idplayer"," WHERE game_idgame=?",""+ ""+ idGame+"").size()) {
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<ArrayList<Object>> patternsCreated(String username, int gameid){
		
		return standardQuery.selectQuery("SELECT p.patterncard_idpatterncard FROM patterncardoption p" + 
				" INNER JOIN player pl ON pl.idplayer = p.player_idplayer",
				" WHERE username=? AND game_idgame=?", username+"\0"+gameid);
	}
	
	public ArrayList<ArrayList<Object>> isHost(String username, int gameid){
		return standardQuery.selectQuery("SELECT playstatus_playstatus FROM player", " WHERE username=? AND game_idgame=?", username+"\0"+gameid);
	}
	
	public ArrayList<ArrayList<Object>> getCorrectUsername(String username){
		return standardQuery.selectQuery("SELECT username FROM account", " WHERE username=?", username);
	}
}
