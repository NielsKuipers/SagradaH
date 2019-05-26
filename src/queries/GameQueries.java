package queries;

import java.util.ArrayList;

public class GameQueries {
	
	private StandardQueries standardQuerie;
	private int gameID;

	public GameQueries(StandardQueries standardQuerie) {
		this.standardQuerie = standardQuerie;
		gameID=1;
	}
	
	
	
	///////////////////////////////////ENDSCREEN//////////////////////////////////////////////////////////////////
	
	// geeft spelersnamen, kleur en punten
	public ArrayList<ArrayList<Object>> getPlayerScores() {
		return standardQuerie.selectQuery("SELECT username, private_objectivecard_color, score FROM player", " WHERE game_idgame=?", ""+gameID+"", " ORDER BY score DESC");
	}
	
	// zet alle spelerstatusen op uitgespeeld
	public void setPlayerStatusFinished() {
		standardQuerie.updateQuery("UPDATE player SET playstatus_playstatus=?", "uitgespeeld", " WHERE game_idgame=?", ""+gameID+"");
	}
	
	
	
	////////////////////////////////////RONDEBORD//////////////////////////////////////////////////////////////////
	
	
	// geeft dobbelstenen op rondebord terug voor een gekozen ronde
	public ArrayList<ArrayList<Object>> getDicesOnRoundBoard(int round) {
		return standardQuerie.selectQuery("SELECT diecolor, eyes, dienumber FROM gamedie", " WHERE idgame=? AND roundtrack=?",""+gameID+"\0"+round+"");
	}

	
	// zet dobbelstenen roundtrack waarde op null
	public void removeDice(int diceID, String colorText) {
		standardQuerie.updateQuery("UPDATE gamedie SET roundtrack=?", ""+null+"", " WHERE idgame=? AND dienumber=? AND diecolor=?", ""+gameID+"\0"+diceID+"\0"+colorText+"");
	}
	
	public void addDiceToRoundTrack(int diceID, String colorText, int round) {
		standardQuerie.updateQuery("UPDATE gamedie SET roundtrack=?", ""+round+"", " WHERE idgame=? AND dienumber=? AND diecolor=?", ""+gameID+"\0"+diceID+"\0"+colorText+"");
	}
	
	public ArrayList<ArrayList<Object>> getRoundTrackOfDice(int diceID, String colorText) {
		return standardQuerie.selectQuery("SELECT roundtrack FROM gamedie", " WHERE idgame=? AND dienumber=? AND diecolor=?",""+gameID+"\0"+diceID+"\0"+colorText+"");
	}
}
