package queries;

import java.util.ArrayList;

import controller.DatabaseController;

public class GameQueries {
	
	StandardQuerie standardQuerie;
	DatabaseController databaseController = new DatabaseController();

	public GameQueries() {
		standardQuerie = databaseController.returnStandardQuerie();
	}
	
	public ArrayList<ArrayList<Object>> getDicesOnRoundBoard() {
		return standardQuerie.selectQuery("SELECT roundtrack, diecolor, eyes FROM gamedie", " WHERE idgame=?", "" + 2 + "");
	}
}
