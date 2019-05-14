package view;

import controller.SetupScreenController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class InviteScreen extends BorderPane{
	private VBox playerList;
	private SetupScreenController controller;
	
	public InviteScreen(SetupScreenController controller) {
		this.controller = controller;
		playerList = new VBox();
		playerList.setPrefWidth(600);
		ScrollPane scroll = new ScrollPane(playerList);
		
		addLowerButtons();
		this.setCenter(scroll);	
	}
	
	// maakt knoppen aan onderkant
	private void addLowerButtons() {
		Button returnButton = new Button("terug");
		returnButton.setOnAction(e -> controller.openSetupMenu());
		
		Button refreshButton = new Button("vernieuw");
		refreshButton.setOnAction(e -> refreshList());
		
		HBox buttonBox = new HBox(refreshButton, returnButton);
		buttonBox.setAlignment(Pos.CENTER);
		this.setBottom(buttonBox);
	}
	
	// voegt nieuwe speler to aan invitelijst
	public void addPlayer(String name) {
		playerList.getChildren().add(new PlayerBox(name));
	}
	
	// verniewt invitelijst
	private void refreshList() {
		clearList();
		controller.addPlayersToInviteList();
	}
	
	// leegt invitelijst
	public void clearList() {
		playerList.getChildren().clear();
	}
	
	// custom speler box
	private class PlayerBox extends HBox{
		private PlayerBox(String name) {
			setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
			setPadding(new Insets(5));
			setSpacing(20);
			setAlignment(Pos.CENTER);
			Label label = new Label(name);
			InviteButton button = new InviteButton(name);
			getChildren().addAll(label, button);
		}
	}
	
	// todo invite button
	private class InviteButton extends Button{
		private InviteButton(String username) {
			setText("Invite");
			setOnAction(e -> {
				controller.invitePlayer(username);
				setDisable(true);
			});
			
		}
	}
}
