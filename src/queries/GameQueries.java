package queries;

import java.util.ArrayList;

import controller.DatabaseController;

public class GameQueries {
	
	StandardQuerie standardQuerie;

	public GameQueries(StandardQuerie standardQuerie) {
		this.standardQuerie = standardQuerie;
	}
	
	public ArrayList<ArrayList<Object>> getDicesOnRoundBoard() {
		return standardQuerie.selectQuery("SELECT roundtrack, diecolor, eyes FROM gamedie", " WHERE idgame=?", "" + 2 + "");
	}
}
