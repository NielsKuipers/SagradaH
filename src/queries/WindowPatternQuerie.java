package queries;

import java.util.ArrayList;

public class WindowPatternQuerie {
	StandardQueries standardQueries;

	public WindowPatternQuerie(StandardQueries standardQueries) {
		this.standardQueries = standardQueries;
	}

	public ArrayList<ArrayList<Object>> getAllFields(int id) {
		return standardQueries.selectQuery("SELECT color, value, position_x, position_y FROM patterncardfield",
				" WHERE patterncard_idpatterncard=?", "" + id + "");
	}

	public ArrayList<ArrayList<Object>> getAllDicesOnField(int id) {
		return standardQueries.selectQuery(
				"SELECT gamedie.diecolor, gamedie.eyes, playerframefield.position_x, playerframefield.position_y FROM playerframefield INNER JOIN gamedie ON gamedie.dienumber = playerframefield.dienumber",
				" WHERE playerframefield.player_idplayer=?", "" + id + "");
	}
	
	public ArrayList<ArrayList<Object>> getDifficulty(int id) {
		return standardQueries.selectQuery("SELECT difficulty FROM patterncard",
				" WHERE idpatterncard=?", "" + id + "");
	}
}
