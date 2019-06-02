package controller;

import main.GUI;
import model.ChatModel;
import view.ChatScreen;
import java.util.ArrayList;

public class ChatController {
    private ChatScreen chatScreen;
    private ChatModel chatModel;
    private ArrayList<ArrayList<Object>> messages = new ArrayList<>();
    private String latestTime = "";

    public ChatController(GUI gui, DatabaseController databaseController){
        chatModel = new ChatModel(databaseController);
        chatScreen = new ChatScreen(gui);
    }

    public void sendMessage(String input, int playerID){chatModel.sendMessage(input, playerID);}

    // get all the messages beloning to the current gameID
    public void getMessages(int gameID){
        chatScreen.clearChat();
        this.messages = chatModel.getMessages(gameID);
        chatScreen.displayMessages(messages);
    }

    // get all messages that got inserted after the latest one and display them
    void getNewMessages(int gameID){
        this.messages = chatModel.getNewMessages(getLatestTime(), gameID);
        chatScreen.displayMessages(messages);
    }

    // return the time of the last message inserted
    private String getLatestTime(){
        if(!messages.isEmpty()) {
            latestTime = messages.get(messages.size() - 1).get(1).toString();
            return latestTime;
        }
        else{
            return latestTime;
        }
    }

    ChatScreen getChatScreen(){
        return this.chatScreen;
    }
}
