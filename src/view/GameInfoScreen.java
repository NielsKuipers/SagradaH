package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import main.GUI;
import model.Game;

public class GameInfoScreen extends VBox{
	private Label l;

	private GUI gui;
	
	public GameInfoScreen(GUI gui, Game game,String info) {
		this.gui = gui;

		l = new Label(info);
		l.setPadding(new Insets(20, 0, 20, 30));
		l.setFont(new Font("Consolas", 18));
		setMinSize(200, 200);
		setPrefSize(1000, 1000);

		Label round = new Label();
		round.setFont(new Font("Consolas", 18));
		round.setPadding(new Insets(0, 0, 20, 0));
		
		round.textProperty().bind(game.gameRoundProperty());

		RadioButton noCheat = new RadioButton("Geen cheat");
		RadioButton cheatAllPossible = new RadioButton("Cheat");
		RadioButton cheatBestChoice = new RadioButton("Cheat extreme");

		Button pass = new Button("Passen");


		ToggleGroup cheatModus = new ToggleGroup();
		
		
		noCheat.setToggleGroup(cheatModus);
		cheatAllPossible.setToggleGroup(cheatModus);
		cheatBestChoice.setToggleGroup(cheatModus);
		noCheat.setSelected(true);
		
		VBox cheatBox = new VBox(noCheat, cheatAllPossible, cheatBestChoice);
		cheatBox.setPadding(new Insets(0, 0, 20, 30));
		
		VBox otherVbox = new VBox(round, pass);
		otherVbox.setPadding(new Insets(0, 0, 20, 30));
		
		getChildren().addAll(l, cheatBox, otherVbox);
		
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
