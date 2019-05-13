package controller;

import java.sql.Connection;

import model.DatabaseModel;
import queries.AccountQuery;
import queries.StandardQuerie;
import queries.WindowPatternQuerie;

public class DatabaseController {

    private Connection mConn;
    private AccountQuery AQ;
    private StandardQuerie standardQuerie;
    
    private WindowPatternQuerie windowPatternQuerie;
    //establish connection with database
    public DatabaseController(){
        DatabaseModel sagradaBaseConn = new DatabaseModel();
        this.mConn = sagradaBaseConn.connectDB();
       
        standardQuerie = new StandardQuerie(mConn);
        
        windowPatternQuerie = new WindowPatternQuerie(standardQuerie);
        AQ = new AccountQuery(standardQuerie);
        
    }
    
    public WindowPatternQuerie getWindowPatternQuerie() {
    	return windowPatternQuerie;
    }
    public AccountQuery getAccountQuery() {
    	return AQ;
    }

    

//        example queries below:
//        use question marks for where you want to use variables, declare them in the variable parameters
//        if you're using multiple variables, separate them with a space

//        updateQuery("UPDATE account set username=?, password=?", "Niels2 Gay1234", " WHERE username=? AND password=?", "Niels0/Gay1234");
//        updateQuery("INSERT INTO account VALUES(?,?)", "Mario\0Zario", "", "");
//        selectQuery("SELECT username FROM account", " WHERE username=?", "Niels2");
//        selectQuery("SELECT username FROM account");
}
