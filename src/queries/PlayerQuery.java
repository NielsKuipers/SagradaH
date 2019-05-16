package queries;

import java.util.ArrayList;

public class PlayerQuery {
	StandardQueries standardQueries;
	
	public PlayerQuery(StandardQueries standardQueries) {
		this.standardQueries = standardQueries;
	}
	
	public ArrayList<ArrayList<Object>> getWindowId(int idPlayer) {
		return standardQueries.selectQuery("SELECT patterncard_idpatterncard FROM player",
				" WHERE idplayer=?", "" + idPlayer + "");
	}
	
	public ArrayList<ArrayList<Object>> getPlayerName(int idPlayer) {
		return standardQueries.selectQuery("SELECT username FROM player",
				" WHERE idplayer=?", "" + idPlayer + "");
	}
	
	public ArrayList<ArrayList<Object>> getPlayerScore(int idPlayer) {
		return standardQueries.selectQuery("SELECT score FROM player",
				" WHERE idplayer=?", "" + idPlayer + "");
	}
	
	public void updateWindowID(int idPlayer, int windowId) {
		standardQueries.updateQuery("UPDATE player set patterncard_idpatterncard=?", "" + windowId + "", " WHERE idplayer=?", "" + idPlayer + "");
	}
	
	public ArrayList<ArrayList<Object>> getIsCurrentPlayer(int idPlayer) {
		return standardQueries.selectQuery("SELECT isCurrentPlayer FROM player",
				" WHERE idplayer=?", "" + idPlayer + "");
	}
	
	public ArrayList<ArrayList<Object>> getSqnrPlayer(int idPlayer) {
		return standardQueries.selectQuery("SELECT seqnr FROM player",
				" WHERE idplayer=?", "" + idPlayer + "");
	}
	
	public void updateSqnrPlayer(int idPlayer, int sqnr) {
		standardQueries.updateQuery("UPDATE player set seqnr=?", "" + sqnr + "", " WHERE idplayer=?", "" + idPlayer + "");
	}
	
	public void updateIsCurrentPlayer(int idPlayer, int isCurrent) {
		standardQueries.updateQuery("UPDATE player set isCurrentPlayer=?", "" + isCurrent + "", " WHERE idplayer=?", "" + idPlayer + "");
	}
}
