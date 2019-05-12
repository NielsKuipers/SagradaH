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

    public ArrayList<ArrayList<Object>> getNewMessages(String time){
        return queries.getNewMessages(time);
    }

    public void sendMessage(String input){
        queries.sendMessage(input);
    }
}
