package queries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class InviteHandleQueries {
	private StandardQueries standardQuerie;
	private String currentDate;
	private String clientUsername;
	private int gameID;

	public InviteHandleQueries(StandardQueries standardQuerie) {
		this.standardQuerie = standardQuerie;
	}
	
	// set username as logged in username
	public void setClientUserName(String username) {
		clientUsername = username;
	}
	
	// return array with different random numbers 
	private int[] getRandomNums(int minVal, int maxVal, int resultAmount) {
		int[] randoms = new int[resultAmount];
		int counter = 0;
		boolean exists = false;
		
		while(counter < resultAmount) {
			int random = (int)(Math.random() * (maxVal - minVal) + minVal);
			
			for(int i =0; i<resultAmount; i++) {
				if(randoms[i] == random) {
					exists = true;
				}
			}
			if(!exists) {
				randoms[counter] = random;
				counter++;
			}
			exists =false;
		}
		return randoms;
	}
	
	// get gameID and return it as int
	private int getGameIDint() {
			return (int) getNewGameID().get(0).get(0);
		}
	
	public void setGameID(int gameid) {
		gameID = gameid;
	}
		
	
	//////////////insert queries////////////////////////////
	
	// make setup for a new game
	public void setupGame(String color) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		currentDate = dateFormat.format(date);
		
		// create new gameID and set it as the current gameID
		// gameID = (int) standardQuerie.selectQuery("START TRANSACTION; INSERT INTO game (creationdate) VALUES (NOW()); SELECT LAST_INSERT_ID();commit;").get(0).get(0);
		standardQuerie.updateQuery("INSERT INTO game(creationdate) VALUES(?)",""+currentDate+"");
		gameID = getGameIDint();
		
		// add 90 gamedie
		standardQuerie.updateQuery("INSERT INTO gamedie(idgame, dienumber, diecolor) SELECT ?, number, color FROM die", ""+gameID+"");
		
		// add 3 random toolcards
		int[] randomToolcards = getRandomNums(1, 13, 3);
		for(int i = 0; i < 3; i++) {
			standardQuerie.updateQuery("INSERT INTO gametoolcard(idtoolcard, idgame) VALUES(?,?)", ""+randomToolcards[i]+"\0"+gameID+"");
		}
		
		// add 3 random public objectivecards
		int[] randomObjectiveCards = getRandomNums(1, 11, 3);
		for(int i = 0; i < 3; i++) {
			standardQuerie.updateQuery("INSERT INTO sharedpublic_objectivecard(idpublic_objectivecard, idgame) VALUES(?,?)", ""+randomObjectiveCards[i]+"\0"+gameID+"");
		}
		
		// add 24 favortokens
		for(int i = 1; i < 25; i++) {
			standardQuerie.updateQuery("INSERT INTO gamefavortoken(idgame, idfavortoken) VALUES(?,?)", ""+gameID+"\0"+i+"");
		}
		
		// Add HOST player to game
		standardQuerie.updateQuery("INSERT INTO player(username, game_idgame, playstatus_playstatus, seqnr, isCurrentPlayer, private_objectivecard_color) VALUES (?,?,?,?,?,?)", ""+clientUsername+"\0"+gameID+"\0uitdager\0"+1+"\0 1\0"+color+"");
		
		int idMainPlayer = (int) standardQuerie.selectQuery("SELECT idplayer FROM player", " WHERE game_idgame=?", ""+gameID+"").get(0).get(0);
		
		standardQuerie.updateQuery("UPDATE game SET turn_idplayer=?", ""+idMainPlayer+"", " WHERE idgame=? ", ""+gameID+"");

	}
	
	// invite player
	public void invitePlayer(String username, String color) {
		standardQuerie.updateQuery("INSERT INTO player(username, game_idgame, playstatus_playstatus, seqnr, isCurrentPlayer, private_objectivecard_color) VALUES (?,?,?,?,?,?)", ""+username+"\0"+gameID+"\0uitgedaagde\0"+getSeqnr()+"\0 0\0"+color+"");
		
	}
	
	// accept invte
	public void acceptInvite(int gameid) {
		standardQuerie.updateQuery("UPDATE player SET playstatus_playstatus=?", "geaccepteerd", " WHERE username=? AND game_idgame=?", ""+clientUsername+"\0"+gameid+"");
	}
	
	// decline invite
	public void declineInvite(int gameid) {
		standardQuerie.updateQuery("UPDATE player SET playstatus_playstatus=?", "geweigerd", " WHERE username=? AND game_idgame=?", ""+clientUsername+"\0"+gameid+"");
	}

	///////////////select queries/////////////////////////////	

	/**
	 * @return all accounts except host
	 */
	public ArrayList<ArrayList<Object>> getPlayers() {
		return standardQuerie.selectQuery("SELECT username FROM account", " WHERE username!=?", ""+clientUsername+"");
	}
	
	/**
	 * @param invitedUsername
	 * @return check if there are unanswered challenges for a player
	 */
	public boolean unAnsweredChallenges(String invitedUsername) {
		
		ArrayList<ArrayList<Object>> hostGameIDs = standardQuerie.selectQuery("SELECT game_idgame FROM player", " WHERE username=? AND playstatus_playstatus=?", ""+clientUsername+"\0uitdager");

		for (ArrayList<Object> hostGameID : hostGameIDs) {
			int id = (int) hostGameID.get(0);
			try {
				ArrayList<ArrayList<Object>> playercheck = standardQuerie.selectQuery("SELECT game_idgame FROM player", " WHERE username=? AND playstatus_playstatus=? AND game_idgame=?", "" + invitedUsername + "\0uitgedaagde\0" + id + "");
				if (playercheck.get(0).get(0) != null) {
					return false;
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
		return true;
	}
	
	/**
	 * @param username
	 * @return check if player has already accepted invite
	 */
	public boolean alreadyAcceptedInvite(String username) {
		ArrayList<ArrayList<Object>> playercheck = standardQuerie.selectQuery("SELECT game_idgame FROM player", " WHERE username=? AND game_idgame=? AND (playstatus_playstatus=? OR playstatus_playstatus=?)", ""+username+"\0"+gameID+"\0geaccepteerd\0geweigerd");	
		try {
			if(playercheck.get(0).get(0) != null) {
				return false;
			}
		}catch(Exception e) {
			// e.printStackTrace();
		}
		return true;
	}
		
	/**
	 * @return next seqnr
	 */
	private int getSeqnr() {
		return (int) standardQuerie.selectQuery("SELECT MAX(seqnr) FROM player", " WHERE game_idgame=?", ""+gameID+"").get(0).get(0) + 1;
		
	}
	
	
	/**
	 * @return joined players
	 */
	public ArrayList<ArrayList<Object>> getJoinedPlayers() {
		return standardQuerie.selectQuery("SELECT username FROM player", " WHERE game_idgame=? AND (playstatus_playstatus=? OR playstatus_playstatus=?)", ""+gameID+"\0geaccepteerd\0uitdager");
	}
	
	
	/**
	 * @return newly created gameID
	 */
	private ArrayList<ArrayList<Object>> getNewGameID() {
		return standardQuerie.selectQuery("SELECT idgame FROM game", " WHERE creationdate=?", ""+currentDate+"");
	}
	
	public int getGameID() {
		return gameID;
	}
	
	
	/**
	 * @return player count
	 */
	public ArrayList<ArrayList<Object>> getInvitedPlayerCount() {
		return standardQuerie.selectQuery("SELECT COUNT(idplayer) FROM player", " WHERE game_idgame=?", ""+gameID+"");
	}
	
	
	
	/**
	 * @return returns inviteGetList
	 */
	public ArrayList<ArrayList<Object>> getInviteGetList(){
		return standardQuerie.selectQuery("SELECT game_idgame FROM player", " WHERE username=? AND playstatus_playstatus=?", ""+clientUsername+"\0uitgedaagde");
	}
	
	/**
	 * @param gameid
	 * @return returns the host username for a game
	 */
	public ArrayList<ArrayList<Object>> getInviter(int gameid){
		return standardQuerie.selectQuery("SELECT username, game_idgame FROM player", " WHERE game_idgame=? AND playstatus_playstatus=?", ""+gameid+"\0uitdager");
	}

	
	/**
	 *  checks if there any rejected invites
	 */
	public boolean checkDeclined() {
		ArrayList<ArrayList<Object>> result = standardQuerie.selectQuery("SELECT COUNT(idplayer) FROM player", " WHERE game_idgame=? AND playstatus_playstatus=?", ""+gameID+"\0geweigerd");
		return (long) result.get(0).get(0) > 0;
	}
	
	
	/**
	 * checks if there are any unanswered invites in a game 
	 */
	public boolean checkUnasweredInGame() {
		ArrayList<ArrayList<Object>> result = standardQuerie.selectQuery("SELECT COUNT(idplayer) FROM player", " WHERE game_idgame=? AND playstatus_playstatus=?", ""+gameID+"\0uitgedaagde");
		return (long) result.get(0).get(0) > 0;
	}
	
	
	
	/**
	 * returns all selected private objective colors
	 */
	public ArrayList<ArrayList<Object>> getPrivateColors(){
		return standardQuerie.selectQuery("SELECT private_objectivecard_color FROM player", " WHERE game_idgame=?" , ""+gameID+"");
	}

}
