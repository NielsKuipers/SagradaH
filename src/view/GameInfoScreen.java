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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.GUI;
import model.Game;

public class GameInfoScreen extends VBox{
	private Label l;
	private Label round;
	
	private GameController GC;
	
	private Game gameModel;
	
	private RadioButton noCheat;
	private RadioButton cheatAllPossible;
	private RadioButton cheatBestChoice;
	
	private Button finishTurn;
	private Button backToHomeScreen;
	
	private ToggleGroup cheatModus;
	
	private GUI gui;
	
	public GameInfoScreen(GUI gui, Game game,String info) {
		this.gui = gui;
		this.gameModel = game;
		
		l = new Label(info);
		l.setFont(new Font("Consolas", 18));
		setMinSize(200, 200);
		setPrefSize(1000, 1000);
		
		round = new Label();
		round.setFont(new Font("Consolas", 18));
		round.setPadding(new Insets(0, 0, 20, 0));
		
		round.textProperty().bind(game.gameRoundProperty());
		
		noCheat = new RadioButton("Geen cheat");
		cheatAllPossible = new RadioButton("Cheat");
		cheatBestChoice = new RadioButton("Cheat extreme");
		
		finishTurn = new Button("Beurt beëndigen");
		finishTurn.setStyle("-fx-background-radius: 50 50 50 50; -fx-background-color: lavender");
		
		backToHomeScreen = new Button("Terug naar hoofdscherm");
		backToHomeScreen.setStyle("-fx-background-color: linear-gradient(to right, lightblue 0%,white 50%, cornflowerblue 100%);-fx-background-radius: 50 50 50 50;");
		backToHomeScreen.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID,new CornerRadii(50), new BorderWidths(2))));
	
		
		cheatModus = new ToggleGroup();
		
		
		noCheat.setToggleGroup(cheatModus);
		cheatAllPossible.setToggleGroup(cheatModus);
		cheatBestChoice.setToggleGroup(cheatModus);
		noCheat.setSelected(true);
		
		VBox cheatBox = new VBox(l, noCheat, cheatAllPossible, cheatBestChoice);
		cheatBox.setPadding(new Insets(50, 0, 20, 30));
		
		VBox otherVbox = new VBox(round, finishTurn);
		otherVbox.setPadding(new Insets(50, 0, 20, 80));
		
		VBox homeScreenButton = new VBox(backToHomeScreen);
		homeScreenButton.setPadding(new Insets(80, 0, 0, 100));
		
		HBox everything = new HBox(cheatBox, otherVbox, homeScreenButton);
		getChildren().addAll(everything);
		
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
