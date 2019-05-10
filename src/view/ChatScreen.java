package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ChatScreen extends BorderPane {
    private Button send;
    private TextField input;
    private HBox chat;
    private HBox chatInput;

    public ChatScreen(){
        send = new Button("Send");
        input = new TextField();
        chat = new HBox();

        setMinSize(200, 200);
        setPrefSize(1000, 1000);

        chat.getChildren().addAll(input, send);

        this.setBottom(chat);
        this.setAlignment(chat, Pos.BOTTOM_CENTER);
    }
}
