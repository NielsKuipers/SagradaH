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

    public ArrayList<ArrayList<Object>> getGames() {
        return accountQuery.getGames();
    }

    public ArrayList<ArrayList<Object>> getGames(Object sortV) {
        return accountQuery.getGames(sortV);
    }

    public ArrayList<ArrayList<Object>> getGames(Object sortV, String username) {
        return accountQuery.getGames(sortV, username);
    }

    public boolean patternsCreated(String username, int gameid) {
        try {
            if (accountQuery.patternsCreated(username, gameid).get(0).get(0) == null) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String getUsername(String username) {
        return accountQuery.getCorrectUsername(username).get(0).get(0).toString();
    }

    public boolean hostplayer(String username, int gameid) {
        return accountQuery.isHost(username, gameid).get(0).get(0).equals("uitdager");
    }
}
