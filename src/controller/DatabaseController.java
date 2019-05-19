package controller;

import model.DatabaseModel;
import queries.*;

import java.sql.Connection;


public class DatabaseController {

    private ChatQueries chatQueries;
    private GameQuery gameQuery;
    private PlayerQuery playerQuery;
    private WindowPatternQuerie windowPatternQuerie;
    private UserListQueries userListQueries;

    //establish connection with database
    public DatabaseController() {
        DatabaseModel sagradaBaseConn = new DatabaseModel();
        Connection mConn = sagradaBaseConn.connectDB();

        StandardQueries standardQueries = new StandardQueries(mConn);

        userListQueries = new UserListQueries(standardQueries);
        chatQueries = new ChatQueries(standardQueries);
        gameQuery = new GameQuery(standardQueries);
        playerQuery = new PlayerQuery(standardQueries);
        windowPatternQuerie = new WindowPatternQuerie(standardQueries);
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

    public UserListQueries getUserListQueries() {
        return userListQueries;
    }
}

//        example queries below:
//        use question marks for where you want to use variables, declare them in the variable parameters
//        if you're using multiple variables, separate them with a space

//        updateQuery("UPDATE account set username=?, password=?", "Niels2 Gay1234", " WHERE username=? AND password=?", "Niels\0Gay1234");
//        updateQuery("INSERT INTO account VALUES(?,?)", "Mario\0Zario", "", "");
//        selectQuery("SELECT username FROM account", " WHERE username=?", "Niels2");
//        selectQuery("SELECT username FROM account");

