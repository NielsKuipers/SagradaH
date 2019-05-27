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
		return standardQueries.selectQuery("SELECT eyes FROM gamedie g" + 
				" INNER JOIN playerframefield p ON g.idgame = p.idgame AND g.dienumber = p.dienumber AND g.diecolor = p.diecolor",
				" WHERE g.idgame=? AND player_idplayer=?", ""+gameID+"\0"+playerID+"");
	}
	
	// returnt alle dobbelsteen kleuren voor 1 speler
	public ArrayList<ArrayList<Object>> getPlayerDiceColors(int playerID) {
		return standardQueries.selectQuery("SELECT g.diecolor FROM gamedie g" + 
				" INNER JOIN playerframefield p ON g.idgame = p.idgame AND g.dienumber = p.dienumber AND g.diecolor = p.diecolor", 
				" WHERE g.idgame=? AND player_idplayer=?", ""+gameID+"\0"+playerID+"");
	}

	public ArrayList<ArrayList<Object>> getPlayerDiceColorsPos(int playerID) {
		return standardQueries.selectQuery("SELECT position_x, position_y, diecolor FROM playerframefield", " WHERE idgame=? AND player_idplayer=? AND diecolor!=?", ""+gameID+"\0"+playerID+"\0"+null+"");
	}

	public ArrayList<ArrayList<Object>> getPlayerDiceEyesPos(int playerID) {
		System.out.println(standardQueries.selectQuery("SELECT p.position_x, p.position_y, g.eyes FROM gamedie g" +
						" INNER JOIN playerframefield p on g.idgame = p.idgame and g.dienumber = p.dienumber and g.diecolor = p.diecolor",
				" WHERE p.idgame=? AND p.player_idplayer=? AND p.diecolor IS NOT NULL", ""+gameID+"\0"+playerID));

		return standardQueries.selectQuery("SELECT p.position_x, p.position_y, g.eyes FROM gamedie g" +
				" INNER JOIN playerframefield p on g.idgame = p.idgame and g.dienumber = p.dienumber and g.diecolor = p.diecolor",
				" WHERE p.idgame=? AND p.player_idplayer=? AND p.diecolor IS NOT NULL ORDER BY p.position_x ASC", ""+gameID+"\0"+playerID);
	}
}
