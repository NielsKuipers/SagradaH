package controller;
import main.GUI;
import model.ChatModel;
import view.ChatScreen;

public class ChatController {

    private ChatModel chatModel;
    private ChatScreen chatScreen;

    public ChatController(){
        chatModel = new ChatModel();
        chatScreen = new ChatScreen(this);
    }

    public void sendMessage(String input){
        System.out.println(input);
    }

    public ChatScreen getChatScreen(){
        return this.chatScreen;
    }
}
