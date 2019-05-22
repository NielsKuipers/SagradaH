package queries;
import java.util.ArrayList;

public class AccountQuery {
	StandardQueries standardQuery;
	public AccountQuery(StandardQueries standardQuery) {
		this.standardQuery = standardQuery;
	}
	
	public Boolean Login(String username, String password) {
		if(!username.isEmpty() && !password.isEmpty())
		if(!standardQuery.selectQuery("SELECT username, password FROM account", " WHERE username=? AND password=?", username+"\0"+password).isEmpty()) {
			return true;
		} else {
			return false;
		}
		return false;
	}
	
	public boolean register(String username, String password) {
		if(username.length() >= 3 && password.length() >= 3) {
			if(standardQuery.selectQuery("SELECT username FROM account", " WHERE username=?", username).isEmpty()) {
				//standardQuery.updateQuery("INSERT INTO account VALUES(?,?)", username+"\0"+password);
				System.out.println("new AC is ok!");
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean getPlayers(int idGame, String username){
		if(!standardQuery.selectQuery("SELECT username FROM player"," WHERE game_idgame=? AND username=?", ""+idGame+""+"\0"+username).isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
//	
//	public ArrayList<ArrayList<Object>> getRound(int idGame) {
//		return standardQuery.selectQuery("SELECT MAX(roundtrack) FROM gamedie",
//				" WHERE idgame=? ORDER BY roundtrack DESC", "" + idGame + "");
//	}
//	
//	public Boolean getGameStatus(int idGame){
//		if(standardQuery.selectQuery("SELECT DISTINCT playstatus_playstatus FROM player", " WHERE game_idgame=?", ""+idGame+"") == standardQuery.selectQuery("SELECT playstatus FROM playstatus", " WHERE playstatus = 'uitgespeeld'", null)) {
//			return true;
//		}
//		return false;
//	}
//	
//	public ArrayList<ArrayList<Object>> getDate(int idGame){
//		return standardQuery.selectQuery("SELECT date(creationdate) FROM game", " WHERE idgame=?", ""+idGame+"");
//	}
//	
//	public boolean checkIDgame(int idGame) {
//		if(!standardQuery.selectQuery("SELECT idgame FROM game", " WHERE idgame=?", ""+idGame+"").isEmpty()){
//			return true;
//		}
//		return false;
//	}
//	
//	public ArrayList<ArrayList<Object>> getMaxidGame() {
//		return standardQuery.selectQuery("SELECT MAX(idgame) FROM game");
//	}
	
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
}
