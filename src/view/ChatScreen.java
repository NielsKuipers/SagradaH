package view;

import controller.ChatController;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ChatScreen extends BorderPane {

    private static final int fullSize = 1000;
    private VBox chat = new VBox();

    public ChatScreen(ChatController controller){
        Button send = new Button("Send");
        TextField input = new TextField();
        HBox chatInput = new HBox();
        ScrollPane sp = new ScrollPane();

        setMinSize(200, 200);
        setPrefSize(fullSize, fullSize);
        send.setPrefSize(200, 20);
        input.setPrefWidth(fullSize - 200);
        chatInput.setPrefSize(fullSize, 20);

        chatInput.getChildren().addAll(input, send);

        sp.setPrefHeight(fullSize - 675);

        sp.setContent(chat);

        this.setTop(sp);
        this.setBottom(chatInput);

        send.setOnMouseClicked(e -> controller.sendMessage(input.getText()));
    }

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
}
