package model;

import controller.DatabaseController;
import queries.ChatQueries;

import java.util.ArrayList;

public class ChatModel {
    private ChatQueries queries;

    public ChatModel(DatabaseController DC){
        this.queries = DC.getChatQueries();
    }

    public ArrayList<ArrayList<Object>> getMessages(int gameID){
        return queries.getMessages(gameID);
    }

    public ArrayList<ArrayList<Object>> getNewMessages(String time, int gameID){
        return queries.getNewMessages(time, gameID);
    }

    public void sendMessage(String input, int playerID){
        queries.sendMessage(input, playerID);
    }
}
