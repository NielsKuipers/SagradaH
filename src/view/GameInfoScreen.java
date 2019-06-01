package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.GUI;
import model.Game;

public class GameInfoScreen extends VBox{
	private Label l;

	private GUI gui;
	
	public GameInfoScreen(GUI gui, Game game,String info) {
		this.gui = gui;
		
		l = new Label(info);
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
		
		Button finishTurn = new Button("Beurt beëndigen");
		finishTurn.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: lavender");
		
		Button roundTrack = new Button("Rondebord");
		roundTrack.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: lavender");
		
		Button backToHomeScreen = new Button("Terug naar hoofdscherm");
		backToHomeScreen.setStyle("-fx-background-color: linear-gradient(to right, lightblue 0%,white 50%, cornflowerblue 100%);-fx-background-radius: 50 50 50 50;");
		backToHomeScreen.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID,new CornerRadii(50), new BorderWidths(2))));
		backToHomeScreen.setOnMouseClicked(e -> gui.handleHomeMenu());
		
		ToggleGroup cheatModus = new ToggleGroup();
		
		
		noCheat.setToggleGroup(cheatModus);
		cheatAllPossible.setToggleGroup(cheatModus);
		cheatBestChoice.setToggleGroup(cheatModus);
		noCheat.setSelected(true);
		
		VBox cheatBox = new VBox(l, noCheat, cheatAllPossible, cheatBestChoice);
		cheatBox.setPadding(new Insets(50, 0, 20, 30));
		
		VBox roundTrackBox = new VBox(roundTrack);
		roundTrackBox.setPadding(new Insets(20, 0, 20, 0));
		
		VBox otherVbox = new VBox(round, finishTurn, roundTrackBox);
		otherVbox.setPadding(new Insets(50, 0, 20, 80));
		
		VBox homeScreenButton = new VBox(backToHomeScreen);
		homeScreenButton.setPadding(new Insets(80, 0, 0, 100));
		
		HBox everything = new HBox(cheatBox, otherVbox, homeScreenButton);
		getChildren().addAll(everything);
		
		noCheat.setOnMouseClicked(e -> handleCheat(false, false));
		cheatAllPossible.setOnMouseClicked(e -> handleCheat(true, false));
		cheatBestChoice.setOnMouseClicked(e -> handleCheat(false, true));
		finishTurn.setOnMouseClicked(e -> handleFinishTurn());
		roundTrack.setOnMouseClicked(e -> handleGoToRoundTrack());
	}
	
	private void handleGoToRoundTrack() {
		gui.handleGoToRoundTrack();
	}
	

	private void handleCheat(boolean allPossible, boolean bestChoice) {
		gui.handleCheat(allPossible, bestChoice);
	}
	
	private void handleFinishTurn() {
		gui.handleFinishTurn();
	}
}
