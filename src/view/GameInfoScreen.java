package view;

import controller.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.GUI;

public class GameInfoScreen extends BorderPane{
	Label l;
	GameController GC;
	Button cheat;
	GUI gui;
	
	public GameInfoScreen(GUI gui, String info, GameController GC) {
		this.gui = gui;
		l = new Label(info);
		l.setPadding(new Insets(70));
		l.setFont(new Font("Consolas", 18));
		setRight(l);
		setMinSize(200, 200);
		setPrefSize(1000, 1000);
		
		cheat = new Button("Zet cheat aan");
		this.GC = GC;
		cheat.setOnMouseClicked(e -> handleCheat());
		setCenter(cheat);
		
	}
	
	public void setPoints(int value) {
		l.setText(Integer.toString(value));
	}
	
	public void handleCheat() {
		gui.handleCheat();
		if(cheat.getText().equals("Zet cheat aan")) {
			cheat.setText("Zet cheat uit");
		}
		else {
			cheat.setText("Zet cheat aan");
		}
	}
}
