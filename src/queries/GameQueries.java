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
		return standardQuerie.selectQuery("SELECT diecolor, eyes, dienumber FROM gamedie", " WHERE idgame=? AND roundtrack=?",""+gameid+"\0"+round+"");
	}

	
	// zet dobbelstenen roundtrack waarde op null
	public void removeDice(int diceID, String colorText) {
		int gameid =2;
		standardQuerie.updateQuery("UPDATE gamedie SET roundtrack=?", ""+null+"", " WHERE idgame=? AND dienumber=? AND diecolor=?", ""+gameid+"\0"+diceID+"\0"+colorText+"");
		
	}
}
