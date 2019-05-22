package model;

import java.util.ArrayList;

import controller.DatabaseController;
import queries.AccountQuery;

public class Account {
	private AccountQuery accountQuery;
	public Account(DatabaseController DC) {
		this.accountQuery = DC.getAccountQuery();
	}
	
	public boolean login(String username, String password) {
		return accountQuery.Login(username, password);
	}
	
	public boolean register(String username, String password) {
		return accountQuery.register(username, password);
	}
	
	public boolean checkIfInGame(int idGame, String username) {
		return accountQuery.getPlayers(idGame, username);
	}
//	public boolean checkIDgame(int idGame) {
//		return accountQuery.checkIDgame(idGame);
//	}
//	
//	public boolean getGameStatus(int idGame) {
//		return accountQuery.getGameStatus(idGame);
//	}
//	
//	public String getPlayers(int idGame) {
//		String playerList = String.valueOf(accountQuery.getPlayers(idGame)).substring(1,String.valueOf(accountQuery.getPlayers(idGame)).length()-1);
//		if(playerList.length() > 3) {
//			String returnString = "Players: "+playerList;
//			return returnString;
//		}
//		else {
//			String returnString = "Players: N/A";
//			return returnString;
//		}
//	}
//	
//	public String getRound(int idGame) {
//		String noRound = "Round: N/A";
//		if(String.valueOf(accountQuery.getRound(idGame)).toCharArray().length == 5) {
//			String roundnumber = String.valueOf(accountQuery.getRound(idGame)).substring(2,3);
//			String round = "Round: "+ roundnumber;
//			return round;
//		}
//		if(String.valueOf(accountQuery.getRound(idGame)).toCharArray().length == 6) {
//			String roundnumber = String.valueOf(accountQuery.getRound(idGame)).substring(2,4);
//			String round = "Round: "+ roundnumber;
//			return round;
//		}
//		return noRound;
//	}
//	
//	public String getDate(int idGame) {
//		String date = String.valueOf(accountQuery.getDate(idGame)).substring(2,12);
//		String startdate = "Start datum van spel: "+ date;
//		return startdate;
//	}
//	
//	public int getMax() {
//		return Integer.valueOf(String.valueOf(accountQuery.getMaxidGame()).substring(2, 4));
//	}
	public ArrayList<ArrayList<Object>> getGames(){
		return accountQuery.getGames();
	}
	public ArrayList<ArrayList<Object>> getGames(Object sortV){
		return accountQuery.getGames(sortV);
	} 
	public ArrayList<ArrayList<Object>> getGames(Object sortV, String username){
		return accountQuery.getGames(sortV, username);
	} 
}
