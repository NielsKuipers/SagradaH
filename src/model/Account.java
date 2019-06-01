package model;

import java.util.ArrayList;

import controller.DatabaseController;
import queries.AccountQuery;

public class Account {
	private AccountQuery accountQuery;
	public Account(DatabaseController DC) {
		this.accountQuery = DC.getAccountQuery();
	}
	
	public boolean login(String username, String password) {return accountQuery.Login(username, password);}
	public boolean register(String username, String password) {return accountQuery.register(username, password);}
	public boolean checkIfInGame(int idGame, String username) {return accountQuery.getPlayers(idGame, username);}
	public ArrayList<ArrayList<Object>> getGames(){return accountQuery.getGames();}
	public ArrayList<ArrayList<Object>> getGames(Object sortV){return accountQuery.getGames(sortV);} 
	public ArrayList<ArrayList<Object>> getGames(Object sortV, String username){return accountQuery.getGames(sortV, username);} 
	public boolean canNotBePLayed(int idGame) {return accountQuery.canNotBePlayed(idGame);}
	public boolean hasNotChosenWindowPattern(int idGame, String username) {return accountQuery.hasNotChosenWindowPattern(idGame, username);}
}
