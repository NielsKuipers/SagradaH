package queries;

import java.util.ArrayList;

import controller.DatabaseController;

public class GameQueries {
	
	StandardQuerie standardQuerie;

	public GameQueries(StandardQuerie standardQuerie) {
		this.standardQuerie = standardQuerie;
	}
	
	// geeft dobbelstenen op rondebord terug voor een gekozen ronde
	public ArrayList<ArrayList<Object>> getDicesOnRoundBoard(int round) {
		int gameid =2;
		return standardQuerie.selectQuery("SELECT diecolor, eyes FROM gamedie", " WHERE idgame=? AND roundtrack=?",""+gameid+"\0"+round+"");
	}
}
