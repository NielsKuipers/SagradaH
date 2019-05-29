package queries;

import java.util.ArrayList;

public class GameQuery {
	private StandardQueries standardQueries;
	
	public GameQuery(StandardQueries standardQueries) {
		this.standardQueries = standardQueries;
	}
	
	public ArrayList<ArrayList<Object>> getPlayerIdsAndNames(int idGame) {
		return standardQueries.selectQuery("SELECT idplayer, username FROM player",
				" WHERE game_idgame=?", "" + idGame + "");
	}
	
	public ArrayList<ArrayList<Object>> getMainPlayerId(int idGame, String accountName) {
		return standardQueries.selectQuery("SELECT idplayer FROM player",
				" WHERE game_idgame=? AND username=?", idGame + "\0" + accountName);
	}
	
	public ArrayList<ArrayList<Object>> getWindowOptions(int idPlayer) {
		return standardQueries.selectQuery("SELECT patterncard_idpatterncard FROM patterncardoption",
				" WHERE player_idplayer=?", "" + idPlayer + "");
	}
	
	public ArrayList<ArrayList<Object>> getRound(int idGame) {
		return standardQueries.selectQuery("SELECT roundtrack FROM gamedie",
				" WHERE idgame=? AND roundtrack IS NOT NULL ORDER BY roundtrack DESC", "" + idGame + "");
	}
	
	public ArrayList<ArrayList<Object>> getAllDicesFromAllPlayers(int idGame) {
		return standardQueries.selectQuery("SELECT dienumber, diecolor FROM playerframefield as f inner join player as p on p.idplayer = f.player_idplayer",
				" WHERE game_idgame=? AND dienumber!=?", idGame + "\0" + "0");
	}
	
	public ArrayList<ArrayList<Object>> getAllDicesFromOneRound(int idGame, int round) {
		return standardQueries.selectQuery("SELECT dienumber, diecolor FROM gamedie",
				" WHERE idgame=? AND round=? AND roundtrack IS NULL", idGame + "\0" + round);
	}
	
	public ArrayList<ArrayList<Object>> getEyeOfDice(int idGame, int dieNumber, String dieColor) {
		return standardQueries.selectQuery("SELECT eyes FROM gamedie",
				" WHERE idgame=? AND dienumber=? AND diecolor=?", idGame + "\0" + dieNumber + "\0" + dieColor);
	}
	
	public void updateTurnPlayerInGameTable(int idGame, int idPlayer) {
		standardQueries.updateQuery("UPDATE game set turn_idplayer=?", "" + idPlayer + "", " WHERE idgame=?", "" + idGame + "");
	}
	
	public void insertOneLineForPlayerFrameField(int idPlayer, int position_x, int position_y, int idGame) {
		standardQueries.updateQuery("INSERT INTO playerframefield(player_idplayer,position_x,position_y,idgame) VALUES(?,?,?,?)",idPlayer + "\0" + position_x + "\0" + position_y + "\0" + idGame);
	}
	
	public void insertNewPatternCard(int dif) {
		standardQueries.updateQuery("INSERT INTO patterncard(difficulty,standard) VALUES(?,?)",dif + "\0" + "0");
	}
	
	public ArrayList<ArrayList<Object>> getLastWindowId() {
		return standardQueries.selectQuery("SELECT idpatterncard FROM patterncard order by idpatterncard DESC");
	}
	
	public void insertPatternCardField(int idWindow, int position_x, int position_y, String color, String value) {
		standardQueries.updateQuery("INSERT INTO patterncardfield(patterncard_idpatterncard,position_x,position_y,color,value) VALUES(?,?,?,?,?)",idWindow + "\0" + position_x + "\0" + position_y + "\0" + color+ "\0" + value);
	}
	
	public void insertPatternCardToPlayerOption(int idWindow, int idPlayer) {
		standardQueries.updateQuery("INSERT INTO patterncardoption(patterncard_idpatterncard,player_idplayer) VALUES(?,?)",idWindow + "\0" + idPlayer);
	}
	
	public void updateFavorTokenPlayer(int idPlayer, int idGame, int idFavortoken) {
		standardQueries.updateQuery("UPDATE gamefavortoken set idplayer=?", "" + idPlayer + "", " WHERE idgame=? AND idfavortoken=?", idGame + "\0" + idFavortoken);
	}
	
	public ArrayList<ArrayList<Object>> getLastFavorTokenId(int idGame) {
		return standardQueries.selectQuery("SELECT idfavortoken FROM gamefavortoken",
				" WHERE idgame=? AND idPlayer IS NULL ORDER BY idfavortoken ASC", "" + idGame + "");
	}
	
	public void updateRollDice(int dienumber, String diecolor, int idGame, int eyes, int round) {
		standardQueries.updateQuery("UPDATE gamedie set eyes=?, round=?", eyes + "\0" + round, " WHERE idgame=? AND dienumber=? AND diecolor=?", idGame + "\0" + dienumber + "\0" + diecolor);
	}
	
	public ArrayList<ArrayList<Object>> getAllEmptyDices(int idGame) {
		return standardQueries.selectQuery("SELECT dienumber, diecolor FROM gamedie",
				" WHERE idgame=? AND round IS NULL","" + idGame + "");
	}
	
	public ArrayList<ArrayList<Object>> checkIfThereIsADiceOnRoundtrack10(int idGame) {
		return standardQueries.selectQuery("SELECT dienumber FROM gamedie",
				" WHERE idgame=? AND roundtrack=?",idGame + "\0" + "10");
	}
	
	public ArrayList<ArrayList<Object>> getAllTheDifferntColorsFromTheRoundTrack(int idGame) {
		return standardQueries.selectQuery("SELECT DISTINCT diecolor FROM gamedie",
				" WHERE idgame=? AND roundtrack IS NOT NULL","" + idGame + "");
	}
	
	public ArrayList<ArrayList<Object>> checkIfPlayersHaveFavorTokes(int idGame) {
		return standardQueries.selectQuery("SELECT idfavortoken FROM gamefavortoken",
				" WHERE idgame=? AND idplayer IS NOT NULL","" + idGame + "");
	}
	
	public ArrayList<ArrayList<Object>> didEveryoneChoose(int idGame) {
		return standardQueries.selectQuery("SELECT idplayer FROM player",
				" WHERE game_idgame=? AND patterncard_idpatterncard IS NULL","" + idGame + "");
	}
	
	
	
	///////////////////////////////////ENDSCREEN//////////////////////////////////////////////////////////////////
	
	// geeft spelersnamen, kleur en punten
	public ArrayList<ArrayList<Object>> getPlayerScores(int gameID) {
		return standardQueries.selectQuery("SELECT username, private_objectivecard_color, score FROM player", " WHERE game_idgame=?", ""+gameID+"", " ORDER BY score DESC");
	}
	
	// zet alle spelerstatusen op uitgespeeld
	public void setPlayerStatusFinished(int gameID) {
		standardQueries.updateQuery("UPDATE player SET playstatus_playstatus=?", "uitgespeeld", " WHERE game_idgame=?", ""+gameID+"");
	}
	
	
	
	////////////////////////////////////RONDEBORD//////////////////////////////////////////////////////////////////
	
	
	// geeft dobbelstenen op rondebord terug voor een gekozen ronde
	public ArrayList<ArrayList<Object>> getDicesOnRoundBoard(int round, int gameID) {
		return standardQueries.selectQuery("SELECT diecolor, eyes, dienumber FROM gamedie", " WHERE idgame=? AND roundtrack=?",""+gameID+"\0"+round+"");
	}

	
	// zet dobbelstenen roundtrack waarde op null
	public void removeDice(int diceID, String colorText, int gameID) {
		standardQueries.updateQuery("UPDATE gamedie SET roundtrack=?", ""+null+"", " WHERE idgame=? AND dienumber=? AND diecolor=?", ""+gameID+"\0"+diceID+"\0"+colorText+"");
	}
	
	public void addDiceToRoundTrack(int diceID, String colorText, int round, int gameID) {
		standardQueries.updateQuery("UPDATE gamedie SET roundtrack=?", ""+round+"", " WHERE idgame=? AND dienumber=? AND diecolor=?", ""+gameID+"\0"+diceID+"\0"+colorText+"");
	}
	
	public ArrayList<ArrayList<Object>> getRoundTrackOfDice(int diceID, String colorText, int gameID) {
		return standardQueries.selectQuery("SELECT roundtrack FROM gamedie", " WHERE idgame=? AND dienumber=? AND diecolor=?",""+gameID+"\0"+diceID+"\0"+colorText+"");
	}
	
	
}
