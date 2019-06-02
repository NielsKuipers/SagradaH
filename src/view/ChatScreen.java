package view;

import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.GUI;

import java.util.ArrayList;

public class ChatScreen extends BorderPane {

    private static final int fullSize = 1000;
    private VBox chat = new VBox();
    private TextField input;
    private GUI gui;

    public ChatScreen(GUI gui){
        this.gui = gui;
        input = new TextField();
        HBox chatInput = new HBox();
        ScrollPane sp = new ScrollPane();

        setMinSize(200, 200);
        setPrefSize(fullSize, fullSize);
        Button send = new Button("Versturen");
        send.setPrefSize(150, 20);
        send.setMinWidth(150);
        input.setPrefWidth(fullSize - 200);
        chatInput.setPrefSize(fullSize, 20);

        chatInput.getChildren().addAll(input, send);
        
        sp.setPrefHeight(fullSize - 685);
        sp.vvalueProperty().bind(chat.heightProperty());
        sp.setContent(chat);
        
        //fire event if send button is clicked or enter pressed
        send.setOnMouseClicked(e -> handleMessage());
        input.setOnKeyReleased(e ->{
            if(e.getCode() == KeyCode.ENTER){handleMessage();}
        });
        
        VBox wholeChat = new VBox(sp, chatInput); 
        this.setBottom(wholeChat);
    }

    //if input isn't empty send the message
    private void handleMessage(){
        if(!input.getText().isEmpty()) {
            gui.sendMessage(input.getText());
            input.clear();
        }
    }

    //create new stringbuilder for handling result of query, put result into chat, reset string
    public void displayMessages(ArrayList<ArrayList<Object>> messages){
        StringBuilder message = new StringBuilder();
        for(ArrayList<Object> msg : messages){
            for(Object data : msg){
                message.append(data).append(" ");
            }
            Label text = new Label(message.toString());
            chat.getChildren().add(text);
            message.setLength(0);
        }
    }

    public static void spamError(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Rustig aan");
        alert.setHeaderText("Rustig aan!");
        alert.setContentText("Je verstuurt veel berichten in een korte tijd, rustig aan AUB!");
        alert.show();
    }

    public void clearChat(){
        chat.getChildren().clear();
    }
}
