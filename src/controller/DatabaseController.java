package controller;

import model.DatabaseModel;
import queries.StandardQuerie;
import queries.WindowPatternQuerie;

import java.sql.*;

public class DatabaseController {

    private Connection mConn;
    
    private StandardQuerie standardQuerie;
    
    private WindowPatternQuerie windowPatternQuerie;
    //establish connection with database
    public DatabaseController(){
        DatabaseModel sagradaBaseConn = new DatabaseModel();
        this.mConn = sagradaBaseConn.connectDB();
       
        standardQuerie = new StandardQuerie(mConn);
        
        windowPatternQuerie = new WindowPatternQuerie(standardQuerie);
        
    }
    
    public WindowPatternQuerie getWindowPatternQuerie() {
    	return windowPatternQuerie;
    }

    

//        example queries below:
//        use question marks for where you want to use variables, declare them in the variable parameters
//        if you're using multiple variables, separate them with a space

//        updateQuery("UPDATE account set username=?, password=?", "Niels2 Gay1234", " WHERE username=?", "Niels");
//        updateQuery("INSERT INTO account VALUES(?,?)", "Mario Zario", "", "");
//        selectQuery("SELECT username FROM account", " WHERE username=?", "Niels2");
//        selectQuery("SELECT username FROM account");
}
