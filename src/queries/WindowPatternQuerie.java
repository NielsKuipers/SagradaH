package queries;

import java.util.ArrayList;

public class WindowPatternQuerie {
	StandardQuerie standardQuerie;

	public WindowPatternQuerie(StandardQuerie standardQuerie) {
		this.standardQuerie = standardQuerie;
	}

	public ArrayList<ArrayList<Object>> getAllFields(int id) {
		return standardQuerie.selectQuery("SELECT color, value, position_x, position_y FROM patterncardfield",
				" WHERE patterncard_idpatterncard=?", "" + id + "");
	}

	public ArrayList<ArrayList<Object>> getAllDicesOnField(int id) {
		return standardQuerie.selectQuery(
				"SELECT gamedie.diecolor, gamedie.eyes, playerframefield.position_x, playerframefield.position_y FROM playerframefield INNER JOIN gamedie ON gamedie.dienumber = playerframefield.dienumber",
				" WHERE playerframefield.player_idplayer=?", "" + id + "");
	}
}
