package queries;

import java.util.ArrayList;

import controller.DatabaseController;

public class GameQueries {
	
	StandardQuerie standardQuerie;
	private int gameID;

	public GameQueries(StandardQuerie standardQuerie) {
		this.standardQuerie = standardQuerie;
		gameID=2;
	}
	
	
	
	///////////////////////////////////ENDSCREEN//////////////////////////////////////////////////////////////////
	
	// geeft spelersnamen, kleur en punten
	public ArrayList<ArrayList<Object>> getPlayerScores() {
		return standardQuerie.selectQuery("SELECT username, private_objectivecard_color, score FROM speler", " WHERE idgame=?",""+gameID+"", "ORDER BY score DESC");
	}
	
	
	
	
	////////////////////////////////////RONDEBORD//////////////////////////////////////////////////////////////////
	
	
	// geeft dobbelstenen op rondebord terug voor een gekozen ronde
	public ArrayList<ArrayList<Object>> getDicesOnRoundBoard(int round) {
		return standardQuerie.selectQuery("SELECT diecolor, eyes, dienumber FROM gamedie", " WHERE idgame=? AND roundtrack=?",""+gameID+"\0"+round+"");
	}

	
	// zet dobbelstenen roundtrack waarde op null
	public void removeDice(int diceID, String colorText) {
		int gameid =2;
		standardQuerie.updateQuery("UPDATE gamedie SET roundtrack=?", ""+null+"", " WHERE idgame=? AND dienumber=? AND diecolor=?", ""+gameid+"\0"+diceID+"\0"+colorText+"");
		
	}
}
