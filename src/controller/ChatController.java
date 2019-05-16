package controller;

import main.GUI;
import model.ChatModel;
import timer.AnimationTimerEXT;
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
        getMessages();
        createTimer();
    }

    public void sendMessage(String input){chatModel.sendMessage(input);}

    // get all the messages beloning to the current gameID
    private void getMessages(){
        int gameID = 2;
        this.messages = chatModel.getMessages(gameID);
        chatScreen.displayMessages(messages);
    }

    // get all messages that got inserted after the latest one and display them
    private void getNewMessages(){
        this.messages = chatModel.getNewMessages(getLatestTime());
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

    // create timer to check for new messages
    private void createTimer(){
        AnimationTimerEXT timer = new AnimationTimerEXT(5000) {
            @Override
            public void doAction() {
                getNewMessages();
            }
        };
        timer.start();
    }
}
