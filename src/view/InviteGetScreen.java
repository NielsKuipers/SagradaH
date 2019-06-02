package view;

import controller.SetupScreenController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.GUI;

public class InviteGetScreen extends BorderPane {

	private VBox inviteList;
	private SetupScreenController controller;
	private GUI gui;

	public InviteGetScreen(SetupScreenController controller, GUI gui) {
		this.gui = gui;
		this.controller = controller;
		makeLayout();
	}

	// make layout
	public void makeLayout() {
		inviteList = new VBox();
		inviteList.setPrefWidth(600);
		Label label = new Label("Invites lijst");
		label.setFont(new Font(20));
		
		ScrollPane scroll = new ScrollPane(inviteList);
		scroll.setMaxWidth(600);
		inviteList.setAlignment(Pos.CENTER);
		
		addLowerButtons();
		this.setCenter(scroll);
		this.setTop(label);
		this.setAlignment(label, Pos.CENTER);
	}

	// add player to invitelist
	public void addPlayer(String name, int gameid) {
		inviteList.getChildren().add(new PlayerBox(name, gameid));
	}

	// refresh invitelist
	public void refreshList() {
		clearList();
		controller.addPlayersToInviteGetList();
	}

	// empty invitelijst
	private void clearList() {
		inviteList.getChildren().clear();
	}

	private void addLowerButtons() {
		Button returnButton = new Button("terug");
		returnButton.setOnAction(e -> gui.handleHomeMenu());

		Button refreshButton = new Button("vernieuw");
		refreshButton.setOnAction(e -> refreshList());

		HBox buttonBox = new HBox(refreshButton, returnButton);
		buttonBox.setAlignment(Pos.CENTER);
		this.setBottom(buttonBox);
	}

	// custom player box
	private class PlayerBox extends HBox {
		private PlayerBox(String name, int gameid) {
			setPadding(new Insets(5));
			setSpacing(20);
			setAlignment(Pos.CENTER);
			Label label = new Label("Host: " + name + "            GameID: " + gameid);
			AcceptButton button = new AcceptButton(gameid);
			DeclineButton button2 = new DeclineButton(gameid);
			getChildren().addAll(label, button, button2);
		}
	}

	// accept button
	private class AcceptButton extends Button {
		private AcceptButton(int gameid) {
			setText("Accept");
			setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, null, null)));
			setOnAction(e -> {
				controller.acceptInvite(gameid);
				refreshList();
			});

		}
	}

	// decline button
	private class DeclineButton extends Button {
		private DeclineButton(int gameid) {
			setText("Decline");
			setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
			setOnAction(e -> {
				controller.declineInvite(gameid);
				refreshList();
			});

		}
	}

}
