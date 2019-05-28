package queries;

import java.util.ArrayList;

public class ScoreQueries {
	private StandardQueries standardQueries;
	private int gameID;
	

	public ScoreQueries(StandardQueries standardQueries) {
		this.standardQueries = standardQueries;
		gameID = 1;
	}
	
	// returnt alle dobbelsteen waarden voor 1 speler
	public ArrayList<ArrayList<Object>> getPlayerDiceNumbers(int playerID) {
		return standardQueries.selectQuery("SELECT dienumber FROM playerframefield", " WHERE idgame=? AND player_idplayer=? AND dienumber!=?", ""+gameID+"\0"+playerID+"\0"+null+"");
	}
	
	// returnt alle dobbelsteen kleuren voor 1 speler
	public ArrayList<ArrayList<Object>> getPlayerDiceColors(int playerID) {
		return standardQueries.selectQuery("SELECT diecolor FROM playerframefield", " WHERE idgame=? AND player_idplayer=? AND diecolor!=?", ""+gameID+"\0"+playerID+"\0"+null+"");
	}
	
	
	
}
