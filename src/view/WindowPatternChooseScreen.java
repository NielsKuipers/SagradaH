package view;

import controller.WindowController;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.GUI;

public class WindowPatternChooseScreen extends GridPane {

    public WindowPatternChooseScreen(GUI gui, WindowController WC) {
        setHgap(50);
        setVgap(40);
        setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
        setAlignment(Pos.CENTER);
        setMinHeight(750);

        Button window1Button = new Button("Deze wil ik !");
        Button window2Button = new Button("Deze wil ik !");
        Button window3Button = new Button("Deze wil ik !");
        Button window4Button = new Button("Deze wil ik !");

        window1Button.setStyle("-fx-font: bold italic 15pt Arial;-fx-effect: dropshadow( one-pass-box , black , 30 , 0.0 , 2 , 0 );");
        window2Button.setStyle("-fx-font: bold italic 15pt Arial;-fx-effect: dropshadow( one-pass-box , black , 30 , 0.0 , 2 , 0 );");
        window3Button.setStyle("-fx-font: bold italic 15pt Arial;-fx-effect: dropshadow( one-pass-box , black , 30 , 0.0 , 2 , 0 );");
        window4Button.setStyle("-fx-font: bold italic 15pt Arial;-fx-effect: dropshadow( one-pass-box , black , 30 , 0.0 , 2 , 0 );");

        add(window1Button, 0, 2);
        add(window2Button, 1, 2);
        add(window3Button, 2, 2);
        add(window4Button, 3, 2);

        window1Button.setOnMouseClicked(e -> gui.createGame(WC.getWindow1().getWindowPatternModel()));
        window2Button.setOnMouseClicked(e -> gui.createGame(WC.getWindow2().getWindowPatternModel()));
        window3Button.setOnMouseClicked(e -> gui.createGame(WC.getWindow3().getWindowPatternModel()));
        window4Button.setOnMouseClicked(e -> gui.createGame(WC.getWindow4().getWindowPatternModel()));

        setHalignment(window1Button, HPos.CENTER);
        setHalignment(window2Button, HPos.CENTER);
        setHalignment(window3Button, HPos.CENTER);
        setHalignment(window4Button, HPos.CENTER);

        Label text = new Label(" Kies je kaart! ");
        text.setStyle("-fx-font: 80px Tahoma;");
        setVgap(150);

        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.BLUEVIOLET);

        text.setEffect(ds);
        this.add(text, 0, 0, 4, 1);
        setHalignment(text, HPos.CENTER);

    }

}
