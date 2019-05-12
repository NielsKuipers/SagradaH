package queries;

import java.util.ArrayList;

public class GameQuery {
	StandardQueries standardQueries;
	
	public GameQuery(StandardQueries standardQueries) {
		this.standardQueries = standardQueries;
	}
	
	public ArrayList<ArrayList<Object>> getPlayerIdsAndNames(int idGame) {
		return standardQueries.selectQuery("SELECT idplayer, username FROM player",
				" WHERE game_idgame=?", "" + idGame + "");
	}
	
	public ArrayList<ArrayList<Object>> getMainPlayerId(int idGame, String accountName) {
		return standardQueries.selectQuery("SELECT idplayer FROM player",
				" WHERE game_idgame=? AND username=?", idGame + "\0" + accountName);
	}
	
	public ArrayList<ArrayList<Object>> getWindowOptions(int idPlayer) {
		return standardQueries.selectQuery("SELECT patterncard_idpatterncard FROM patterncardoption",
				" WHERE player_idplayer=?", "" + idPlayer + "");
	}
}
