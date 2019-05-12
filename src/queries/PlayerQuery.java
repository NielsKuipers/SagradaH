package queries;

import java.util.ArrayList;

public class PlayerQuery {
	StandardQueries standardQueries;
	
	public PlayerQuery(StandardQueries standardQueries) {
		this.standardQueries = standardQueries;
	}
	
	public ArrayList<ArrayList<Object>> getWindowId(int id) {
		return standardQueries.selectQuery("SELECT patterncard_idpatterncard FROM player",
				" WHERE idplayer=?", "" + id + "");
	}
	
	public ArrayList<ArrayList<Object>> getPlayerName(int id) {
		return standardQueries.selectQuery("SELECT username FROM player",
				" WHERE idplayer=?", "" + id + "");
	}
}
