package controller;

import model.DatabaseModel;
import queries.GameQueries;
import queries.InviteHandleQueries;
import queries.StandardQuerie;
import queries.WindowPatternQuerie;

import java.sql.*;

public class DatabaseController {

    private Connection mConn;
    
    private StandardQuerie standardQuerie;
    
    private WindowPatternQuerie windowPatternQuerie;
    private InviteHandleQueries inviteHandleQueries;
    private GameQueries gameQueries;
    
    //establish connection with database
    public DatabaseController(){
        DatabaseModel sagradaBaseConn = new DatabaseModel();
        this.mConn = sagradaBaseConn.connectDB();
       
        standardQuerie = new StandardQuerie(mConn);
        
        windowPatternQuerie = new WindowPatternQuerie(standardQuerie);
        inviteHandleQueries = new InviteHandleQueries(standardQuerie);
        gameQueries = new GameQueries(standardQuerie);
        
        
    }
    
    public GameQueries getGameQueries() {
    	return gameQueries;
    }
    
    public InviteHandleQueries getInviteQueries() {
    	return inviteHandleQueries;
    }
    
    public WindowPatternQuerie getWindowPatternQuerie() {
    	return windowPatternQuerie;
    }

    

//        example queries below:
//        use question marks for where you want to use variables, declare them in the variable parameters
//        if you're using multiple variables, separate them with a space

//        updateQuery("UPDATE account set username=?, password=?", "Niels2 Gay1234", " WHERE username=? AND password=?", "Niels0/Gay1234");
//        updateQuery("INSERT INTO account VALUES(?,?)", "Mario\0Zario", "", "");
//        selectQuery("SELECT username FROM account", " WHERE username=?", "Niels2");
//        selectQuery("SELECT username FROM account");
}
