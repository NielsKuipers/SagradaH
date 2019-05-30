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
	
	// zet username op ingelogde account naam
	public void setClientUserName(String username) {
		clientUsername = username;
	}
	
	// returnt array met verschillende random getallen
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
	
	// gameID ophalen en int returnen
	private int getGameIDint() {
			return (int) getGameID().get(0).get(0);
		}
	
	public void setGameID(int gameid) {
		gameID = gameid;
	}
		
	
	//////////////insert queries////////////////////////////
	
	// maakt setup voor nieuwe game.  heeft username nodig (uit inlog klasse?)
	public void setupGame(String color) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		currentDate = dateFormat.format(date);
		
		// nieuwe gameID aanmaken en gameid opslaan
		// gameID = (int) standardQuerie.selectQuery("START TRANSACTION; INSERT INTO game (creationdate) VALUES (NOW()); SELECT LAST_INSERT_ID();commit;").get(0).get(0);
		standardQuerie.updateQuery("INSERT INTO game(creationdate) VALUES(?)",""+currentDate+"");
		gameID = getGameIDint();
		
		// 90 gamedie toevoegen
		standardQuerie.updateQuery("INSERT INTO gamedie(idgame, dienumber, diecolor) SELECT ?, number, color FROM die", ""+gameID+"");
		
		// 3 random toolcards toevoegen
		int[] randomToolcards = getRandomNums(1, 13, 3);
		for(int i = 0; i < 3; i++) {
			standardQuerie.updateQuery("INSERT INTO gametoolcard(idtoolcard, idgame) VALUES(?,?)", ""+randomToolcards[i]+"\0"+gameID+"");
		}
		
		// 3 random public objectivecards toevoegen
		int[] randomObjectiveCards = getRandomNums(1, 11, 3);
		for(int i = 0; i < 3; i++) {
			standardQuerie.updateQuery("INSERT INTO sharedpublic_objectivecard(idpublic_objectivecard, idgame) VALUES(?,?)", ""+randomObjectiveCards[i]+"\0"+gameID+"");
		}
		
		// 24 random betaalstenen toevoegen
		for(int i = 1; i < 25; i++) {
			standardQuerie.updateQuery("INSERT INTO gamefavortoken(idgame, idfavortoken) VALUES(?,?)", ""+gameID+"\0"+i+"");
		}
		
		// HOST player aanmaken
		standardQuerie.updateQuery("INSERT INTO player(username, game_idgame, playstatus_playstatus, seqnr, isCurrentPlayer, private_objectivecard_color) VALUES (?,?,?,?,?,?)", ""+clientUsername+"\0"+gameID+"\0uitdager\0"+1+"\0 1\0"+color+"");
		
		int idMainPlayer = (int) standardQuerie.selectQuery("SELECT idplayer FROM player", " WHERE game_idgame=?", ""+gameID+"").get(0).get(0);
		
		standardQuerie.updateQuery("UPDATE game SET turn_idplayer=?", ""+idMainPlayer+"", " WHERE idgame=? ", ""+gameID+"");

	}
	
	// invite speler
	public void invitePlayer(String username, String color) {
		standardQuerie.updateQuery("INSERT INTO player(username, game_idgame, playstatus_playstatus, seqnr, isCurrentPlayer, private_objectivecard_color) VALUES (?,?,?,?,?,?)", ""+username+"\0"+gameID+"\0uitgedaagde\0"+getSeqnr()+"\0 0\0"+color+"");
		
	}
	
	// invite accepteren
	public void acceptInvite(int gameid) {
		standardQuerie.updateQuery("UPDATE player SET playstatus_playstatus=?", "geaccepteerd", " WHERE username=? AND game_idgame=?", ""+clientUsername+"\0"+gameid+"");
	}
	
	// invite weigeren
	public void declineInvite(int gameid) {
		standardQuerie.updateQuery("UPDATE player SET playstatus_playstatus=?", "geweigerd", " WHERE username=? AND game_idgame=?", ""+clientUsername+"\0"+gameid+"");
	}

	///////////////select queries/////////////////////////////	
	
	// geeft spelerlijst uit alle accounts behalve hostaccount
	public ArrayList<ArrayList<Object>> getPlayers() {
		return standardQuerie.selectQuery("SELECT username FROM account", " WHERE username!=?", ""+clientUsername+"");
	}
	
	// controlleer of speler al een openstaande uitdaging heeft van de host
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
				System.out.println("geen invites gevonden");
			}
		}
		return true;
	}
	
	// controlleer of de speler al de uitdaging geaccepteerd heeft
	public boolean alreadyAcceptedInvite(String username) {
		ArrayList<ArrayList<Object>> playercheck = standardQuerie.selectQuery("SELECT game_idgame FROM player", " WHERE username=? AND game_idgame=? AND (playstatus_playstatus=? OR playstatus_playstatus=?)", ""+username+"\0"+gameID+"\0geaccepteerd\0geweigerd");	
		try {
			if(playercheck.get(0).get(0) != null) {
				return false;
			}
		}catch(Exception e) {
			System.out.println("geen invites gevonden");
		}
		return true;
	}
		
	// return next playerseqnr
	private int getSeqnr() {
		return (int) standardQuerie.selectQuery("SELECT MAX(seqnr) FROM player", " WHERE game_idgame=?", ""+gameID+"").get(0).get(0) + 1;
		
	}
	
	// geeft gejoinde spelerlijst
	public ArrayList<ArrayList<Object>> getJoinedPlayers() {
		return standardQuerie.selectQuery("SELECT username FROM player", " WHERE game_idgame=? AND (playstatus_playstatus=? OR playstatus_playstatus=?)", ""+gameID+"\0geaccepteerd\0uitdager");
	}
	
	// nieuwe GameID ophalen
	public ArrayList<ArrayList<Object>> getGameID() {
		return standardQuerie.selectQuery("SELECT idgame FROM game", " WHERE creationdate=?", ""+currentDate+"");
	}
	
	// speler aantal ophalen
	public ArrayList<ArrayList<Object>> getInvitedPlayerCount() {
		return standardQuerie.selectQuery("SELECT COUNT(idplayer) FROM player", " WHERE game_idgame=?", ""+gameID+"");
	}
	
	// inviteGetLijst ophalen
	public ArrayList<ArrayList<Object>> getInviteGetList(){
		return standardQuerie.selectQuery("SELECT game_idgame FROM player", " WHERE username=? AND playstatus_playstatus=?", ""+clientUsername+"\0uitgedaagde");
	}
	
	// haalt uitdager naam op om toe te voegen aan invitegetlist screen
	public ArrayList<ArrayList<Object>> getInviter(int gameid){
		return standardQuerie.selectQuery("SELECT username, game_idgame FROM player", " WHERE game_idgame=? AND playstatus_playstatus=?", ""+gameid+"\0uitdager");
	}

	// controlleert of er geweigerde uitnodigingen zijn
	public boolean checkDeclined() {
		ArrayList<ArrayList<Object>> result = standardQuerie.selectQuery("SELECT COUNT(idplayer) FROM player", " WHERE game_idgame=? AND playstatus_playstatus=?", ""+gameID+"\0geweigerd");
		return (long) result.get(0).get(0) > 0;
	}
	
	// controlleert of er geweigerde uitnodigingen zijn in de game
	public boolean checkUnasweredInGame() {
		ArrayList<ArrayList<Object>> result = standardQuerie.selectQuery("SELECT COUNT(idplayer) FROM player", " WHERE game_idgame=? AND playstatus_playstatus=?", ""+gameID+"\0uitgedaagde");
		return (long) result.get(0).get(0) > 0;
	}
	
	// returnt alle geselecteerde private objective kleuren
	public ArrayList<ArrayList<Object>> getPrivateColors(){
		return standardQuerie.selectQuery("SELECT private_objectivecard_color FROM player", " WHERE game_idgame=?" , ""+gameID+"");
	}

}
