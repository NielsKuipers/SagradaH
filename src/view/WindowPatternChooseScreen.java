package view;

import controller.WindowController;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.GUI;

public class WindowPatternChooseScreen extends GridPane {

	private Label text;
	Font font = new Font("Consolas", 60);
	private Button window1Button;
	private Button window2Button;
	private Button window3Button;
	private Button window4Button;
	
	private WindowController WC;
	
	private GUI gui;

	public WindowPatternChooseScreen(GUI gui, WindowController WC) {
		this.gui = gui;
		this.WC = WC;
		
		setHgap(50); // horizontal gap in pixels => that's what you are asking for
		setVgap(40); // vertical gap in pixels
		setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
		setAlignment(Pos.CENTER);
		setMinHeight(750);
		
		window1Button = new Button("Deze wil ik !");
		window2Button = new Button("Deze wil ik !");
		window3Button = new Button("Deze wil ik !");
		window4Button = new Button("Deze wil ik !");
		
		window1Button.setStyle("-fx-font: bold italic 15pt Arial;-fx-effect: dropshadow( one-pass-box , black , 30 , 0.0 , 2 , 0 );");
		window2Button.setStyle("-fx-font: bold italic 15pt Arial;-fx-effect: dropshadow( one-pass-box , black , 30 , 0.0 , 2 , 0 );");
		window3Button.setStyle("-fx-font: bold italic 15pt Arial;-fx-effect: dropshadow( one-pass-box , black , 30 , 0.0 , 2 , 0 );");
		window4Button.setStyle("-fx-font: bold italic 15pt Arial;-fx-effect: dropshadow( one-pass-box , black , 30 , 0.0 , 2 , 0 );");
		
		add(window1Button, 0, 2);
		add(window2Button, 1, 2);
		add(window3Button, 2, 2);
		add(window4Button, 3, 2);
		
		window1Button.setOnMouseClicked(e -> gui.createGame(WC.getWindow1()));
		window2Button.setOnMouseClicked(e -> gui.createGame(WC.getWindow2()));
		window3Button.setOnMouseClicked(e -> gui.createGame(WC.getWindow3()));
		window4Button.setOnMouseClicked(e -> gui.createGame(WC.getWindow4()));
		
		setHalignment(window1Button, HPos.CENTER);
		setHalignment(window2Button, HPos.CENTER);
		setHalignment(window3Button, HPos.CENTER);
		setHalignment(window4Button, HPos.CENTER);
		
		
		text = new Label(" WindowPatternChooseScreen ");
		text.setStyle("-fx-background-radius: 300 300 300 300;-fx-background-color:DEEPSKYBLUE");
		setVgap(150);
		text.setFont(font);
		
		this.add(text, 0, 0, 4, 1);
		this.setHalignment(text, HPos.CENTER);

	}

}
