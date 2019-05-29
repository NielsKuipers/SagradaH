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
import main.GUI;



public class InviteGetScreen extends BorderPane{
	
	private VBox inviteList;
	private SetupScreenController controller;
	private GUI gui;
	
	public InviteGetScreen(SetupScreenController controller, GUI gui) {
		this.gui = gui;
		this.controller = controller;
		inviteList = new VBox();
		inviteList.setPrefWidth(600);
		ScrollPane scroll = new ScrollPane(inviteList);
		addLowerButtons();
		this.setCenter(scroll);	
	}
	
	// voegt nieuwe speler to aan invitelijst
		public void addPlayer(String name, int gameid) {
			inviteList.getChildren().add(new PlayerBox(name, gameid));
		}
		
		// verniewt invitelijst
		private void refreshList() {
			clearList();
			controller.addPlayersToInviteGetList();
		}
		
		// leegt invitelijst
		private void clearList() {
			inviteList.getChildren().clear();
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
		
		// custom speler box
		private class PlayerBox extends HBox{
			private PlayerBox(String name, int gameid) {
				setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
				setPadding(new Insets(5));
				setSpacing(20);
				setAlignment(Pos.CENTER);
				Label label = new Label("Host: " + name + "            GameID: " + gameid);
				AcceptButton button = new AcceptButton(gameid);
				DeclineButton button2 = new DeclineButton(gameid);
				getChildren().addAll(label, button, button2);
			}
		}
		
		//  accept button
		private class AcceptButton extends Button{
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
		private class DeclineButton extends Button{
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
