package queries;

import java.util.ArrayList;

public class ChatQueries {
    private StandardQueries standardQueries;
    private int playerID = 3;

    public ChatQueries(StandardQueries standardQueries){ this.standardQueries = standardQueries; }

    public ArrayList<ArrayList<Object>> getMessages(){
        return standardQueries.selectQuery("SELECT p.username, c.time, c.message FROM chatline c " +
                                           "INNER JOIN player p on c.player_idplayer = p.idplayer" +
                                           "ORDER BY time ASC");
    }

    public void sendMessage(String message){
        standardQueries.updateQuery("INSERT INTO chatline(player_idplayer, message) VALUES(?,?)",
                                    playerID + "\0" + message);
    }
}

//        return standardQueries.selectQuery("SELECT c.time, c.message, p.username " +
//                "FROM chatline c INNER JOIN player p ON p.idplayer = c.player_idplayer",
//                " WHERE p.idplayer =?", ""+ playerID +"");
