package view;

import controller.SetupScreenController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import main.GUI;

public class InviteScreen extends BorderPane{
	private VBox playerList;
	private SetupScreenController controller;
	private GUI gui;
	
	public InviteScreen(SetupScreenController controller, GUI gui) {
		this.gui = gui;
		this.controller = controller;
		makeLayout();
	}
	
	// make layout
	private void makeLayout() {
		playerList = new VBox();
		playerList.setPrefWidth(790);
		Label label = new Label("Invite lijst");
		label.setFont(new Font(20));
	
		ScrollPane scroll = new ScrollPane(playerList);
		scroll.setMaxWidth(800);
		playerList.setAlignment(Pos.CENTER);
		addLowerButtons();
		
		this.setCenter(scroll);		
		this.setTop(label);
		this.setAlignment(label, Pos.CENTER);
	}
	
	private void addLowerButtons() {
		Button returnButton = new Button("terug");
		returnButton.setOnAction(e -> gui.openSetupMenu());
		
		Button refreshButton = new Button("vernieuw");
		refreshButton.setOnAction(e -> refreshList());
		
		HBox buttonBox = new HBox(refreshButton, returnButton);
		buttonBox.setAlignment(Pos.CENTER);
		this.setBottom(buttonBox);
	}
	
	// add player to invitelist
	public void addPlayer(String name) {
		playerList.getChildren().add(new PlayerBox(name));
	}
	
	// refresh invitelist
	private void refreshList() {
		clearList();
		controller.addPlayersToInviteList();
	}
	
	// empty invitelist
	public void clearList() {
		playerList.getChildren().clear();
	}
	
	// custom speler box
	private class PlayerBox extends HBox{
		private PlayerBox(String name) {
		//	setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
			setPadding(new Insets(5));
			setSpacing(20);
			setAlignment(Pos.CENTER);
			Label label = new Label(name);
			InviteButton button = new InviteButton(name);
			getChildren().addAll(label, button);
		}
	}
	
	//invite button
	private class InviteButton extends Button{
		private InviteButton(String username) {
			setText("Invite");
			setOnAction(e -> {
				controller.invitePlayer(username);
				setDisable(true);
			});
			
		}
	}
	
	
	/** opens warning box depending on warning code
	 * @param warningCode
	 */
	public void warning(int warningCode) {
		switch (warningCode) {
		case 1:
			alert("Maximaal aantal spelers bereikt!!", "Je hebt al drie spelers uitgenodigd!!");
			break;
		case 2:
			alert("Je hebt deze speler al uitgenodigd!!", "Deze speler heeft je uitnodiging al geaccepteerd/geweigerd!!");
			break;
		case 3:
			alert("Je hebt deze speler al uitgenodigd!!", "Deze speler heeft al een openstaande uitnodiging!!");
			break;

		default:
			break;
		}
	}
	
	private void alert(String a, String b) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(a);
		alert.setHeaderText("WAARSCHUWING");
		alert.setContentText(b);
		alert.showAndWait();
	}
}
