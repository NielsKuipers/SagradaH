package view;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ChatScreen extends BorderPane {
    private Button send;
    private TextField input;

    public ChatScreen(){
        send = new Button("Send");

        setMinSize(200, 200);
        setPrefSize(1000, 1000);

        setRight(send);
    }
}
