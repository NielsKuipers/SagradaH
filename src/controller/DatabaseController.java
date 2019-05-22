package controller;

import model.DatabaseModel;

import queries.CardQueries;

import queries.ChatQueries;
import queries.GameQuery;
import queries.PlayerQuery;
import queries.StandardQueries;

import queries.WindowPatternQuerie;
import java.sql.Connection;


public class DatabaseController {


    
    private CardQueries CQ;
    

    private ChatQueries chatQueries;
    private GameQuery gameQuery;
    private PlayerQuery playerQuery;

    private WindowPatternQuerie windowPatternQuerie;


    //establish connection with database
    public DatabaseController() {
        DatabaseModel sagradaBaseConn = new DatabaseModel();

       Connection mConn = sagradaBaseConn.connectDB();
       
       StandardQueries standardQueries = new StandardQueries(mConn);
        CQ = new CardQueries(standardQueries);
        
        windowPatternQuerie = new WindowPatternQuerie(standardQueries);
        
    }
    
    
    CardQueries getCardQueries() {
    	return CQ;
    }

    GameQuery getGameQuery() {
        return gameQuery;
    }

    public ChatQueries getChatQueries() {
        return chatQueries;
    }

    PlayerQuery getPlayerQuery() {
        return playerQuery;
    }

    WindowPatternQuerie getWindowPatternQuerie() {
        return windowPatternQuerie;
    }
}

//        example queries below:
//        use question marks for where you want to use variables, declare them in the variable parameters
//        if you're using multiple variables, separate them with a space

//        updateQuery("UPDATE account set username=?, password=?", "Niels2 Gay1234", " WHERE username=? AND password=?", "Niels\0Gay1234");
//        updateQuery("INSERT INTO account VALUES(?,?)", "Mario\0Zario", "", "");
//        selectQuery("SELECT username FROM account", " WHERE username=?", "Niels2");
//        selectQuery("SELECT username FROM account");

