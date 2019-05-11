package controller;
import main.GUI;
import model.ChatModel;
import queries.ChatQueries;
import view.ChatScreen;

public class ChatController {

    private ChatModel chatModel;
    private ChatScreen chatScreen;
    private DatabaseController DC;
    private ChatQueries queries;

    public ChatController(DatabaseController databaseController){
        chatModel = new ChatModel();
        chatScreen = new ChatScreen(this);
        this.DC = databaseController;
        this.queries = DC.getChatQueries();
    }

    public void sendMessage(String input){
        queries.sendMessage(input);
    }

//    databaseController.getChatQueries().getMessages();
    public ChatScreen getChatScreen(){
        return this.chatScreen;
    }
}
