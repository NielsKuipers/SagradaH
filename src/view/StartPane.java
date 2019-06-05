package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.GUI;

public class StartPane extends HBox {
    private RegisterScreen reg;
    private LoginScreen log;
    private GUI gui;

    public StartPane(GUI gui) {
        this.gui = gui;
        log = new LoginScreen(gui);
        reg = new RegisterScreen(gui);
        Button exit = new Button("Afsluiten");
        exit.setMinWidth(328);
        exit.setOnMouseClicked(e -> handleExitGame());
        HBox hb = new HBox();
        VBox vb = new VBox();
        hb.getChildren().addAll(log, reg);
        vb.getChildren().addAll(hb, exit);
        hb.setSpacing(30.0);
        vb.setSpacing(20.0);
        vb.setAlignment(Pos.CENTER);
        setSpacing(30.0);
        setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();
        Window w4 = new Window();
        getChildren().addAll(w1, w2, vb, w3, w4);
    }

    public RegisterScreen getReg() {
        return reg;
    }

    public LoginScreen getLog() {
        return log;
    }

    private void handleExitGame() {
        gui.HandleExitGame();
    }
}
