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

class Buttons extends VBox {
	private Background buttonBackground = new Background(new BackgroundFill(Color.GAINSBORO,null,null));
	private Border buttonBorder = new Border(new BorderStroke(Color.DARKGREY, BorderStrokeStyle.SOLID, null, new BorderWidths(5)));
	private Button startGame = new Button("Start Spel");
	private Button gameLijst = new Button("Spellen Lijst");
	private Button openUitdaging = new Button("Open Uitdagingen");
	private Button spelerLijst = new Button("Speler Lijst");
	private Button uitloggen = new Button("Uitloggen");
	private GUI myGUI;

	Buttons(GUI mygui) {
		this.myGUI = mygui;
		setAlignment(Pos.CENTER);
		addButtons();
	}

	private void addButtons() {
		setMargin(startGame, new Insets(20.0));
		startGame.setBackground(buttonBackground);
		startGame.setOnMouseClicked(e -> handleToCreateGame());
		startGame.setBorder(buttonBorder);
		startGame.setPrefSize(300, 30);
		
		setMargin(gameLijst, new Insets(20.0));
		gameLijst.setBackground(buttonBackground);
		gameLijst.setOnMouseClicked(e -> handleToGameList());
		gameLijst.setBorder(buttonBorder);
		gameLijst.setPrefSize(300, 30);

		setMargin(openUitdaging, new Insets(20.0));
		openUitdaging.setBackground(buttonBackground);
		openUitdaging.setOnMouseClicked(e -> handleToGetInvite());
		openUitdaging.setBorder(buttonBorder);
		openUitdaging.setPrefSize(300, 30);
		
		setMargin(spelerLijst, new Insets(20.0));
		spelerLijst.setBackground(buttonBackground);
		spelerLijst.setOnMouseClicked(e -> handleToPlayerList());
		spelerLijst.setBorder(buttonBorder);
		spelerLijst.setPrefSize(300, 30);
		
		setMargin(uitloggen, new Insets(20.0));
		uitloggen.setBackground(buttonBackground);
		uitloggen.setOnMouseClicked(e -> handleUitloggen());
		uitloggen.setBorder(buttonBorder);
		uitloggen.setPrefSize(300, 30);
		
		getChildren().addAll(startGame, gameLijst, openUitdaging, spelerLijst, uitloggen);
	}
	
	private void handleUitloggen() {
		myGUI.handleUitloggen();
	}
	
	private void handleToGameList() {
		myGUI.handleToGameList();
	}
	
	private void handleToGetInvite() {
		myGUI.handleToGetInvite();
	}
	
	private void handleToPlayerList() {
		myGUI.handleToPlayerList();
	}
	
	private void handleToCreateGame() {
		myGUI.handleToCreateGame();
	}
}
