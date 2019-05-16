package queries;

import java.util.ArrayList;

public class ChatQueries {
    private StandardQueries standardQueries;
    private int playerID = 3;

    public ChatQueries(StandardQueries standardQueries){ this.standardQueries = standardQueries; }

    public ArrayList<ArrayList<Object>> getMessages(int gameID){
        return standardQueries.selectQuery("SELECT p.username, c.time, c.message FROM chatline c " +
                                           "INNER JOIN player p on c.player_idplayer = p.idplayer ",
                                    "WHERE p.game_idgame=? ", ""+ gameID +"",
                                   "ORDER BY time ASC");
    }

    public ArrayList<ArrayList<Object>> getNewMessages(String latestTime){
        return standardQueries.selectQuery("SELECT p.username, c.time, c.message FROM chatline c " +
                                           "INNER JOIN player p on c.player_idplayer = p.idplayer ", " WHERE time > ?",
                                  "" + latestTime + "", "ORDER BY time ASC");
    }

    public void sendMessage(String message){
        standardQueries.updateQuery("INSERT INTO chatline(player_idplayer, message) VALUES(?,?)",
                                    playerID + "\0" + message);
    }
}
