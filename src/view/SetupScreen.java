package view;

import controller.SetupScreenController;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import main.GUI;

/**
 * @author pasa
 *
 */
public class SetupScreen extends BorderPane {
	
	// buttons
	private Button WindowFrameSetChooser;
	private Button refreshListButton;
	private Button startGame;
	private Button inviteButton;
	
	// shapes/layout/animation
	private Text playerCountText;
	private Circle animation;
	private FillTransition transition;
	private BorderPane setupPane;
	private SetupScreenController controller;
	private BorderPane inviteBar;
	private VBox playerList;
	
	// booleans
	private boolean searching;
	private boolean regularSet = true;
	private boolean gameMade = false;
	
	private GUI gui;
	

	public SetupScreen(SetupScreenController controller, GUI gui) {
		this.gui = gui;
		this.controller = controller;
		makeInviteBar();
		
		setupPane = new BorderPane();
		this.setCenter(setupPane);
		this.setRight(inviteBar);
		
		makeButtons();
		setPlayerAmountText();
		currentPlayerAmount();
		
	}

	
	
	/**
	 * create and place all buttons
	 */
	private void makeButtons() {
		
		//////////////////top buttons////////////////////////
		Button start = new Button("START ZOEKEN");
		start.setPrefSize(120, 60);
		start.setOnAction(e -> startSearch());

		Button stop = new Button("STOP ZOEKEN");
		stop.setPrefSize(120, 60);
		stop.setOnAction(e -> stopSearch());

		HBox topButtons = new HBox(start, stop);
		topButtons.setAlignment(Pos.CENTER);
		topButtons.setSpacing(5);
		
		
		/////////////////bottom buttons/////////////////////////
		WindowFrameSetChooser = new Button("Standaard windowpatterns");
		WindowFrameSetChooser.setOnAction(e -> clickPatternButton());
		
		startGame = new Button("Start Spel");
		startGame.setOnAction(e -> controller.startGame());
		
		Button returnButton = new Button("Terug");
		returnButton.setOnAction(e-> gui.handleHomeMenu());
		
		HBox bottomButtons = new HBox(WindowFrameSetChooser, startGame, returnButton);
		bottomButtons.setAlignment(Pos.CENTER);
		bottomButtons.setSpacing(5);
		
		setupPane.setTop(topButtons);
		setupPane.setBottom(bottomButtons);
		setAlignment(WindowFrameSetChooser, Pos.BOTTOM_CENTER);
		
		
		/////////////////inviteBar//////////////////////////
		inviteButton = new Button("Invite");
		inviteButton.setOnAction(e -> gui.openInviterMenu());
		inviteButton.setDisable(true);
		inviteButton.setPrefSize(80, 30);
		
		refreshListButton = new Button("vernieuw");
		refreshListButton.setOnAction(e -> refreshPlayerList());
		refreshListButton.setPrefSize(80, 30);
		refreshListButton.setDisable(true);
		
		HBox playerListBottomButtons = new HBox(refreshListButton, inviteButton);
		playerListBottomButtons.setSpacing(10);
		playerListBottomButtons.setAlignment(Pos.CENTER);
		playerListBottomButtons.setPadding(new Insets(10));
		
		inviteBar.setBottom(playerListBottomButtons);
	}

	
	// enter playercount in circle
	private void setPlayerAmountText() {
		int playerCount = playerList.getChildren().size();
		playerCountText = new Text("speleraantal: \r         " + (playerCount));
		currentPlayerAmount();
	}

	
	
	/**
	 * start playersearch,
	 * turn on and off correct buttons,
	 * create game in database
	 */
	private void startSearch() {
		if(!gameMade) {
			gameMade = true;
			controller.makeGame();
			refreshListButton.setDisable(false);
		}
		
		if (!searching) {
			System.out.println("started searching");
			searching = true;
			transition.play();
			startGame.setDisable(true);
			inviteButton.setDisable(false);
		} else {
			System.out.println("already searching!!");
		}
		refreshPlayerList();
	}

	
	/**
	 * stop playersearch,
	 * correct buttons turn on and off
	 */
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
	
	// create search animation
	private void makeSearchAnimation() {
		transition = new FillTransition();
		transition.setDuration(new Duration(650));
		transition.setToValue(Color.BLUE);
		transition.setAutoReverse(true);
		transition.setCycleCount(Animation.INDEFINITE);
		transition.setShape(animation);
	}

	// switches random or standard patterns
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

	// draw circle and place player count inside
	private void currentPlayerAmount() {
		StackPane stackpane = new StackPane();
		Circle playerCount = new Circle(0, 0, 80, Color.RED);

		animation = new Circle(0, 0, 100, Color.WHITE);
		animation.setStroke(Color.RED);
		makeSearchAnimation();

		stackpane.getChildren().addAll(animation, playerCount, playerCountText);
		setupPane.setCenter(stackpane);
	}
	
	// create invitebar
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
	
	// add joined players to playerlist
	public void addJoinedPlayer(String username) {
		Label name = new Label(username);
		name.setFont(new Font(20));
		name.setUnderline(true);
		playerList.getChildren().add(name);
	}
	
	public void clearJoinedList() {
		playerList.getChildren().clear();
	}
	
	// refresh playerlist + playercount
	private void refreshPlayerList() {
		clearJoinedList();
		controller.addJoinedPlayers();
		setPlayerAmountText();
		if(searching) {
			transition.play();
		}
	}
	
	
	/**
	 * setup for creating new game
	 */
	public void makeNewGame() {
		regularSet = true;
		gameMade = false;
		clearJoinedList();
		refreshListButton.setDisable(true);
		setPlayerAmountText();
	}
	
	/**
	 *  setup for loading existing game
	 */
	public void loadSetup() {
		regularSet = true;
		gameMade = true;
		clearJoinedList();
		refreshListButton.setDisable(false);
		setPlayerAmountText();
	}
	
	/** opens warning box depending on warning code
	 * @param warningCode
	 */
	
	public void warning(int warningCode) {
		switch (warningCode) {
		case 1:
			alert("Iemand heeft je uitnodiging geweigerd!!", "Spel kan niet meer gespeeld worden!!");
			break;
		case 2:
			alert("Je hebt nog onbeantwoorde uitnodigingen!!", "Het spel kan niet starten voordat iedereen accepteerd!!");
			break;
		case 3:
			alert("Je kan niet alleen spelen!!", "Het spel kan gespeeld worden met 2-4 mensen!!");
			break;

		default:
			break;
		}
	}
	
	
	// make alert box
	private void alert(String a, String b) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(a);
		alert.setHeaderText("WAARSCHUWING");
		alert.setContentText(b);
		alert.showAndWait();
	}	
}

