package queries;

import java.util.ArrayList;

public class WindowPatternQueries {
	private StandardQueries standardQueries;

	public WindowPatternQueries(StandardQueries standardQueries) {
		this.standardQueries = standardQueries;
	}

	public ArrayList<ArrayList<Object>> getAllFields(int id) {
		return standardQueries.selectQuery("SELECT color, value, position_x, position_y FROM patterncardfield",
				" WHERE patterncard_idpatterncard=?", "" + id + "");
	}

	public ArrayList<ArrayList<Object>> getAllDicesOnField(int idPlayer, int idGame) {
		return standardQueries.selectQuery("SELECT gamedie.diecolor, gamedie.eyes, playerframefield.position_x, playerframefield.position_y, " +
						" gamedie.dienumber FROM playerframefield INNER JOIN gamedie ON gamedie.dienumber = playerframefield.dienumber " +
						" AND gamedie.diecolor = playerframefield.diecolor ",
				" WHERE playerframefield.player_idplayer=? AND gamedie.idgame=?", idPlayer + "\0" + idGame);
	}
	
	public ArrayList<ArrayList<Object>> getDifficulty(int id) {
		return standardQueries.selectQuery("SELECT difficulty FROM patterncard",
				" WHERE idpatterncard=?", "" + id + "");
	}
}
