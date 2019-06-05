package controller;

import java.sql.Connection;

import model.DatabaseModel;
import queries.*;

import queries.WindowPatternQueries;

public class DatabaseController {

    private AccountQuery AQ;
    private CardQueries CQ;
    private ChatQueries chatQueries;
    private GameQueries gameQueries;
    private PlayerQueries playerQueries;
    private WindowPatternQueries windowPatternQueries;
    private UserListQueries userListQueries;
    private InviteHandleQueries inviteHandleQueries;
    private ScoreQueries scoreQueries;

    //establish connection with database
    public DatabaseController() {
        DatabaseModel sagradaBaseConn = new DatabaseModel();
        Connection mConn = sagradaBaseConn.connectDB();
        StandardQueries standardQueries = new StandardQueries(mConn);

        userListQueries = new UserListQueries(standardQueries);
        chatQueries = new ChatQueries(standardQueries);
        gameQueries = new GameQueries(standardQueries);
        playerQueries = new PlayerQueries(standardQueries);
        CQ = new CardQueries(standardQueries);
        windowPatternQueries = new WindowPatternQueries(standardQueries);
        inviteHandleQueries = new InviteHandleQueries(standardQueries);
        AQ = new AccountQuery(standardQueries);
        scoreQueries = new ScoreQueries(standardQueries);
    }

    public AccountQuery getAccountQuery() {
        return AQ;
    }

    CardQueries getCardQueries() {
        return CQ;
    }

    InviteHandleQueries getInviteQueries() {
        return inviteHandleQueries;
    }

    GameQueries getGameQueries() {
        return gameQueries;
    }

    public ChatQueries getChatQueries() {
        return chatQueries;
    }

    PlayerQueries getPlayerQueries() {
        return playerQueries;
    }

    WindowPatternQueries getWindowPatternQueries() {
        return windowPatternQueries;
    }

    public UserListQueries getUserListQueries() {
        return userListQueries;
    }

    ScoreQueries getScoreQueries() {
        return scoreQueries;
    }
}

//        example queries below:
//        use question marks for where you want to use variables, declare them in the variable parameters
//        if you're using multiple variables, separate them with a space

//        updateQuery("UPDATE account set username=?, password=?", "Niels2 Gay1234", " WHERE username=? AND password=?", "Niels\0Gay1234");
//        updateQuery("INSERT INTO account VALUES(?,?)", "Mario\0Zario", "", "");
//        selectQuery("SELECT username FROM account", " WHERE username=?", "Niels2");
//        selectQuery("SELECT username FROM account");
