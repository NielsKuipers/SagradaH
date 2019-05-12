package view;

import controller.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.GUI;

public class GameInfoScreen extends VBox{
	private Label l;
	private GameController GC;
	
	private RadioButton noCheat;
	private RadioButton cheatAllPossible;
	private RadioButton cheatBestChoice;
	
	private ToggleGroup cheatModus;
	
	private GUI gui;
	
	public GameInfoScreen(GUI gui, String info) {
		this.gui = gui;
		l = new Label(info);
		l.setPadding(new Insets(20, 0, 20, 30));
		l.setFont(new Font("Consolas", 18));
		setMinSize(200, 200);
		setPrefSize(1000, 1000);
		
		noCheat = new RadioButton("Geen cheat");
		cheatAllPossible = new RadioButton("Cheat");
		cheatBestChoice = new RadioButton("Cheat extreme");
		
		cheatModus = new ToggleGroup();
		
		
		noCheat.setToggleGroup(cheatModus);
		cheatAllPossible.setToggleGroup(cheatModus);
		cheatBestChoice.setToggleGroup(cheatModus);
		noCheat.setSelected(true);
		
		VBox cheatBox = new VBox(noCheat, cheatAllPossible, cheatBestChoice);
		cheatBox.setPadding(new Insets(0, 0, 20, 30));
		
		getChildren().addAll(l, cheatBox);
		
		noCheat.setOnMouseClicked(e -> handleCheat(false, false));
		cheatAllPossible.setOnMouseClicked(e -> handleCheat(true, false));
		cheatBestChoice.setOnMouseClicked(e -> handleCheat(false, true));
	}
	
	public void setPoints(int value) {
		l.setText(Integer.toString(value));
	}
	
	private void handleCheat(boolean allPossible, boolean bestChoice) {
		gui.handleCheat(allPossible, bestChoice);
		
	}
}
