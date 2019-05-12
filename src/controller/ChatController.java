package controller;

import model.ChatModel;
import timer.AnimationTimerEXT;
import view.ChatScreen;

import java.util.ArrayList;

public class ChatController {
    private ChatScreen chatScreen;
    private ChatModel chatModel;
    private ArrayList<ArrayList<Object>> messages = new ArrayList<>();
    private String latestTime = "";

    public ChatController(DatabaseController databaseController){
        chatModel = new ChatModel(databaseController);
        chatScreen = new ChatScreen(this);
        getMessages();
        createTimer();
    }

    public void sendMessage(String input){chatModel.sendMessage(input);}

    private void getMessages(){
        int gameID = 2;
        this.messages = chatModel.getMessages(gameID);
        chatScreen.displayMessages(messages);
    }

    private void getNewMessages(){
        this.messages = chatModel.getNewMessages(getLatestTime());
        chatScreen.displayMessages(messages);
    }

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
