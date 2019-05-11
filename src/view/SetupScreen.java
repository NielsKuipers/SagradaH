package view;

import controller.SetupScreenController;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SetupScreen extends BorderPane {
	private Button WindowFrameSetChooser;
	private Boolean regularSet = true;
	private Text playerCountText;
	private boolean searching;
	private Circle animation;
	private FillTransition transition;
	private BorderPane setupPane;
	private SetupScreenController controller;
	private Button startGame;
	private BorderPane inviteBar;
	private Button inviteButton;
	private VBox playerList;

	public SetupScreen(SetupScreenController controller) {
		
		this.controller = controller;
		makeInviteBar();
		
		setupPane = new BorderPane();
		this.setCenter(setupPane);
		this.setRight(inviteBar);
		
		makeButtons();
		setPlayerAmountText();
		currentPlayerAmount();
		
	}

	
	// maakt alle knoppen aan en plaatst ze in de correcte plekken
	private void makeButtons() {
		Button start = new Button("START");
		start.setPrefSize(80, 60);
		start.setOnAction(e -> startSearch());

		Button stop = new Button("STOP");
		stop.setPrefSize(80, 60);
		stop.setOnAction(e -> stopSearch());

		HBox topButtons = new HBox(start, stop);
		topButtons.setAlignment(Pos.CENTER);
		topButtons.setSpacing(5);
		
		WindowFrameSetChooser = new Button("Standaard windowpatterns");
		WindowFrameSetChooser.setOnAction(e -> clickPatternButton());
		
		startGame = new Button("Start Spel");
		startGame.setOnAction(e -> startGame());
		
		HBox bottomButtons = new HBox(WindowFrameSetChooser, startGame);
		bottomButtons.setAlignment(Pos.CENTER);
		bottomButtons.setSpacing(5);
		
		inviteButton = new Button("Invite");
		inviteButton.setOnAction(e -> controller.openInviterMenu());
		inviteButton.setDisable(true);
		inviteButton.setPrefSize(80, 30);
		
		Button refreshListButton = new Button("vernieuw");
		refreshListButton.setOnAction(e -> refreshPlayerList());
		refreshListButton.setPrefSize(80, 30);
		
		HBox playerListBottomButtons = new HBox(refreshListButton, inviteButton);
		playerListBottomButtons.setSpacing(10);
		playerListBottomButtons.setAlignment(Pos.CENTER);
		playerListBottomButtons.setPadding(new Insets(10));
		
		inviteBar.setBottom(playerListBottomButtons);

		setupPane.setTop(topButtons);
		setupPane.setBottom(bottomButtons);
		setupPane.setAlignment(WindowFrameSetChooser, Pos.BOTTOM_CENTER);
	}

	
	// zet spelersaantal in rondje
	private void setPlayerAmountText() {
		int playerCount = playerList.getChildren().size();
		playerCountText = new Text("speleraantal: \r         " + Integer.toString(playerCount));
		currentPlayerAmount();
	}

	
	// start spelerzoeken, speler invitescherm wordt ingeschakeld en startgame knop stop met werken
	private void startSearch() {
		if (!searching) {
			System.out.println("started searching");
			searching = true;
			transition.play();
			startGame.setDisable(true);
			inviteButton.setDisable(false);
		} else {
			System.out.println("already searching!!");
		}
	}

	// stopt spelerzoeken, speler invitescherm wordt uitgeschakeld en startgame knop werkt
	private void stopSearch() {
		if (searching) {
			System.out.println("stopped search");
			searching = false;
			transition.stop();
			animation.setFill(Color.WHITE);
			startGame.setDisable(false);
			inviteButton.setDisable(true);
		} else {
			System.out.println("search already stopped!!");
		}
	}
	
	// maakt zoek animatie 
	private void makeSearchAnimation() {
		transition = new FillTransition();
		transition.setDuration(new Duration(650));
		transition.setToValue(Color.BLUE);
		transition.setAutoReverse(true);
		transition.setCycleCount(Animation.INDEFINITE);
		transition.setShape(animation);
	}

	// zorgt voor random of standaard pattern keuze
	private void clickPatternButton() {
		if (regularSet) {
			WindowFrameSetChooser.setText("Random windowpatterns");
			controller.setRandomWindow(true);
		} else {
			WindowFrameSetChooser.setText("Standaard windowpatterns");
			controller.setRandomWindow(false);
		}
		regularSet = !regularSet;
	}

	// teken rondje en spelersaantal
	private void currentPlayerAmount() {
		StackPane stackpane = new StackPane();
		Circle playerCount = new Circle(0, 0, 80, Color.RED);

		animation = new Circle(0, 0, 100, Color.WHITE);
		animation.setStroke(Color.RED);
		makeSearchAnimation();

		stackpane.getChildren().addAll(animation, playerCount, playerCountText);
		setupPane.setCenter(stackpane);
	}
	
	// schakelt naar het volgende scherm via de controller
	private void startGame() {
		controller.finishSetup();
	}
	
	// maakt inviteBar
	private void makeInviteBar() {
			
		inviteBar = new BorderPane();
		playerList = new VBox();
		playerList.setSpacing(100);
		playerList.setPadding(new Insets(10));
		playerList.setAlignment(Pos.TOP_CENTER);
		
		inviteBar.setPrefSize(300, this.getHeight());
		inviteBar.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
		inviteBar.setCenter(playerList);
	}
	
	// voegt spelers toe aan spelerlijst
	public void addJoinedPlayer(String username) {
		Label name = new Label(username);
		name.setFont(new Font(20));
		name.setUnderline(true);
		playerList.getChildren().add(name);
	}
	
	public void clearJoinedList() {
		playerList.getChildren().clear();
	}
	
	private void refreshPlayerList() {
		clearJoinedList();
		controller.addJoinedPlayers();
		setPlayerAmountText();
	}
	
}

