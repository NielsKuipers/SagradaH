package view;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.GUI;

public class Buttons extends VBox {
	private Background buttonBackground = new Background(new BackgroundFill(Color.GAINSBORO,null,null));
	private Border buttonBorder = new Border(new BorderStroke(Color.DARKGREY, BorderStrokeStyle.SOLID, null, new BorderWidths(5)));
	private Button startGame = new Button("Start Game");
	private Button gameLijst = new Button("Game Lijst");
	private Button openUitdaging = new Button("Open Uitdagingen");
	private Button spelerLijst = new Button("Speler Lijst");
	private Button uitloggen = new Button("Uitloggen");
	GUI myGUI;
	
	
	public Buttons(GUI mygui) {
		this.myGUI = mygui;
		setAlignment(Pos.CENTER);
		addButtons();
	}
	private void addButtons() {
		setMargin(startGame, new Insets(20.0));
		setMargin(openUitdaging, new Insets(20.0));
		setMargin(spelerLijst, new Insets(20.0));
		setMargin(uitloggen, new Insets(20.0));
		setMargin(gameLijst, new Insets(20.0));
		startGame.setBackground(buttonBackground);
		gameLijst.setBackground(buttonBackground);
		openUitdaging.setBackground(buttonBackground);
		spelerLijst.setBackground(buttonBackground);
		uitloggen.setBackground(buttonBackground);
		startGame.setBorder(buttonBorder);
		gameLijst.setBorder(buttonBorder);
		openUitdaging.setBorder(buttonBorder);
		spelerLijst.setBorder(buttonBorder);
		uitloggen.setBorder(buttonBorder);
		startGame.setPrefSize(300, 30);
		gameLijst.setPrefSize(300, 30);
		openUitdaging.setPrefSize(300, 30);
		spelerLijst.setPrefSize(300, 30);
		uitloggen.setPrefSize(300, 30);
		uitloggen.setOnMouseClicked(e -> handleUitloggen());
		getChildren().addAll(startGame, gameLijst, openUitdaging, spelerLijst, uitloggen);
	}
	
	public void handleUitloggen() {
		myGUI.handleUitloggen();
	} 
}
