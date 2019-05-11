package view;

import controller.ChatController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChatScreen extends BorderPane {

    private static final int fullSize = 1000;

    public ChatScreen(ChatController controller){
        Button send = new Button("Send");
        TextField input = new TextField();
        HBox chatInput = new HBox();
        VBox chat = new VBox();
        Label label = new Label("this is text haha");

        setMinSize(200, 200);
        setPrefSize(fullSize, fullSize);
        send.setPrefSize(200, 20);
        input.setPrefWidth(fullSize - 200);

        chatInput.getChildren().addAll(input, send);
        chat.getChildren().addAll(label);

        this.setTop(chat);
        this.setBottom(chatInput);

        send.setOnMouseClicked(e -> controller.sendMessage(input.getText()));
    }
}
