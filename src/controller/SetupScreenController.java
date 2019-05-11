package controller;

import model.CommunicationModel;
import view.EndScreen;
import view.InviteScreen;
import view.SetupScreen;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SetupScreenController {
	
	private SetupScreen screen;
	private Stage stage;
	private CommunicationModel cModel;
	private InviteScreen inviteScreen;
	private Scene scene;
	
	public SetupScreenController(Stage stage) {
		cModel = new CommunicationModel();
		inviteScreen = new InviteScreen(this);
		screen = new SetupScreen(this);
		scene = new Scene(screen);
		
		this.stage = stage;
		stage.setScene(scene);

	}

	// bepaalt random/standaard patterns
	public void setRandomWindow(boolean random) {
		
	}
	
	// schakel van setup scherm naar patternkeuze scherm
	public void finishSetup() {
		scene.setRoot(new Pane());
	}
	
	// schakel van setup scherm naar invite scherm
	public void openInviterMenu() {
		scene.setRoot(inviteScreen);
		inviteScreen.clearList();
		addPlayersToInviteList();
	}
	
	// schakel van invite naar setup scherm
	public void openSetupMenu() {
		scene.setRoot(screen);
		screen.clearJoinedList();
		addJoinedPlayers();
	}
	
	// voeg spelers to aan de invite lijst
	public void addPlayersToInviteList() {
		ArrayList<ArrayList <Object>> result = cModel.getInviteablePlayers();
		
		for(int i = 0; i < result.size(); i++) {
			if(result.get(i).get(1) != null) {
				inviteScreen.addPlayer((String) result.get(i).get(0));
			}
		}
	}
	
	
	// voeg toegevoegde spelers aan speler lijst
	public void addJoinedPlayers() {
		ArrayList<ArrayList <Object>> result = cModel.getJoinedPlayers();
		
		for(int i = 0; i < result.size(); i++) {
			if(result.get(i).get(0) != null) {
				screen.addJoinedPlayer((String) result.get(i).get(0));
			}
		}
	}
	
}
