package controller;
import main.GUI;
import model.ChatModel;
import view.ChatScreen;

public class ChatController {

    private ChatModel chatModel;
    private ChatScreen chatScreen;

    public ChatController(GUI gui){
        chatModel = new ChatModel();
        chatScreen = new ChatScreen();
    }
}
