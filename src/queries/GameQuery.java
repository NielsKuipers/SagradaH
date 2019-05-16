package queries;

import java.util.ArrayList;

public class GameQuery {
	StandardQueries standardQueries;
	
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
				" WHERE idgame=? ORDER BY roundtrack DESC", "" + idGame + "");
	}
	
	public ArrayList<ArrayList<Object>> getAllDicesFromAllPlayers(int idGame) {
		return standardQueries.selectQuery("SELECT dienumber, diecolor FROM playerframefield as f inner join player as p on p.idplayer = f.player_idplayer",
				" WHERE game_idgame=? AND dienumber!=?", idGame + "\0" + "0");
	}
	
	public ArrayList<ArrayList<Object>> getAllDicesFromOneRound(int idGame, int round) {
		return standardQueries.selectQuery("SELECT dienumber, diecolor FROM gamedie",
				" WHERE idgame=? AND round=?", idGame + "\0" + round);
	}
	
	public ArrayList<ArrayList<Object>> getEyeOfDice(int idGame, int dieNumber, String dieColor) {
		return standardQueries.selectQuery("SELECT eyes FROM gamedie",
				" WHERE idgame=? AND dienumber=? AND diecolor=?", idGame + "\0" + dieNumber + "\0" + dieColor);
	}
	
	public void updateTurnPlayerInGameTable(int idGame, int idPlayer) {
		standardQueries.updateQuery("UPDATE game set turn_idplayer=?", "" + idPlayer + "", " WHERE idgame=?", "" + idGame + "");
	}
}
