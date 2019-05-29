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
	
	public boolean canNotBePlayed(int idGame) {
		if(standardQuery.selectQuery("SELECT distinct playstatus_playstatus FROM player"," WHERE playstatus_playstatus=? AND game_idgame = ?", "uitdager"+"\0"+""+idGame+"").isEmpty() && standardQuery.selectQuery("SELECT distinct playstatus_playstatus FROM player"," WHERE playstatus_playstatus=? AND game_idgame = ?", "uitgedaagde"+"\0"+""+idGame+"").isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
}
