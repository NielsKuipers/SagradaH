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
	
	// returnt dobbelstenen waarden+kleuren voor 1 speler EN return private objectivekaartkleur
	public ArrayList<ArrayList<Object>> getAllColorsEyes(int playerID) {
		return standardQueries.selectQuery("SELECT g.eyes, g.diecolor, pr.private_objectivecard_color FROM gamedie g" + 
				" INNER JOIN playerframefield pl ON g.idgame = pl.idgame AND g.dienumber = pl.dienumber AND g.diecolor = pl.diecolor" + 
				" INNER JOIN player pr ON pl.player_idplayer = pr.idplayer",
				" WHERE g.idgame=? AND pl.player_idplayer=?", ""+gameID+"\0"+playerID+"");
	}
	
	// returnt aantal favortokens voor 1 speler
	public ArrayList<ArrayList<Object>> getPlayerFavortokens(int playerID) {
		return standardQueries.selectQuery("SELECT COUNT(idfavortoken) FROM gamefavortoken", " WHERE idgame=? AND idplayer=?", ""+gameID+"\0"+playerID+"");
	}
	
	// return openstaande velden op patroonkaart voor 1 speler
	public ArrayList<ArrayList<Object>> getEmptyFields(int playerID) {
		return standardQueries.selectQuery("SELECT COUNT(player_idplayer) FROM playerframefield", " WHERE idgame=? AND player_idplayer=? AND dienumber is null", ""+gameID+"\0"+playerID+"");
	}

	public ArrayList<ArrayList<Object>> getPlayerDiceColorsPos(int playerID) {
		return standardQueries.selectQuery("SELECT position_x, position_y, diecolor FROM playerframefield", " WHERE idgame=? AND player_idplayer=? AND diecolor IS NOT NULL", ""+gameID+"\0"+playerID);
	}

	public ArrayList<ArrayList<Object>> getPlayerDiceColorsPosX(int playerID) {
		return standardQueries.selectQuery("SELECT position_y, position_x, diecolor FROM playerframefield", " WHERE idgame=? AND player_idplayer=? AND diecolor IS NOT NULL ORDER BY position_y", ""+gameID+"\0"+playerID);
	}

	public ArrayList<ArrayList<Object>> getPlayerDiceEyesPos(int playerID) {
		return standardQueries.selectQuery("SELECT p.position_x, p.position_y, g.eyes FROM gamedie g" +
				" INNER JOIN playerframefield p on g.idgame = p.idgame and g.dienumber = p.dienumber and g.diecolor = p.diecolor",
				" WHERE p.idgame=? AND p.player_idplayer=? AND p.diecolor IS NOT NULL ORDER BY p.position_x ASC", ""+gameID+"\0"+playerID);
	}

	public ArrayList<ArrayList<Object>> getPlayerDiceEyesPosX(int playerID) {
		return standardQueries.selectQuery("SELECT p.position_y, p.position_x, g.eyes FROM gamedie g" +
						" INNER JOIN playerframefield p on g.idgame = p.idgame and g.dienumber = p.dienumber and g.diecolor = p.diecolor",
				" WHERE p.idgame=? AND p.player_idplayer=? AND p.diecolor IS NOT NULL ORDER BY p.position_y ASC", ""+gameID+"\0"+playerID);
	}
}
