package controller;

import model.DatabaseModel;
import queries.ChatQueries;
import queries.StandardQueries;
import queries.WindowPatternQuerie;
import java.sql.Connection;


public class DatabaseController {

    private WindowPatternQuerie windowPatternQuerie;
    private ChatQueries chatQueries;

    //establish connection with database
    public DatabaseController(){
        DatabaseModel sagradaBaseConn = new DatabaseModel();
        Connection mConn = sagradaBaseConn.connectDB();

        StandardQueries standardQueries = new StandardQueries(mConn);
        
        windowPatternQuerie = new WindowPatternQuerie(standardQueries);
        chatQueries = new ChatQueries(standardQueries);
    }
    
    public WindowPatternQuerie getWindowPatternQuerie() {
    	return windowPatternQuerie;
    }

    public ChatQueries getChatQueries() {
        return chatQueries;
    }
    //        example queries below:
//        use question marks for where you want to use variables, declare them in the variable parameters
//        if you're using multiple variables, separate them with a space

//        updateQuery("UPDATE account set username=?, password=?", "Niels2 Gay1234", " WHERE username=?", "Niels");
//        updateQuery("INSERT INTO account VALUES(?,?)", "Mario_Zario", "", "");
//        selectQuery("SELECT username FROM account", " WHERE username=?", "Niels2");
//        selectQuery("SELECT username FROM account");
}
