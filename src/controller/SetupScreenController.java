package controller;

import model.CommunicationModel;
import view.InviteGetScreen;
import view.InviteScreen;
import view.SetupScreen;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.GUI;

public class SetupScreenController {
	
	private SetupScreen setupScreen;
	private CommunicationModel cModel;
	private InviteScreen inviteScreen;
	private InviteGetScreen inviteGetScreen;
	private GUI gui;
	private boolean randomPatterns = false;
	private GameController gameController;
	
	public SetupScreenController(DatabaseController dataController, GUI gui, GameController gameController) {
		this.gui = gui;
		this.gameController = gameController;
		cModel = new CommunicationModel(dataController.getInviteQueries());
		inviteScreen = new InviteScreen(this, gui);
		setupScreen = new SetupScreen(this, gui);
	}
	
	public SetupScreen getSetupScreen() {
		return setupScreen;
	}
	
	public InviteScreen getInviteScreen() {
		return inviteScreen;
	}
	
	// spel wordt gestart als alle uitgenodigden geaccepteerd hebben. schakel van setup scherm naar window kies scherm 
	public void startGame() {
		if((long) cModel.getInvitedPlayerCount().get(0).get(0) < 2) {
			setupScreen.onlyOnePlayerWarning();
		}else if(cModel.checkDeclined()) {
			setupScreen.declinedInviteWarning();
		}else if(cModel.checkUnansweredInGame()) {
			setupScreen.unAnsweredInviteWarning();
		}else{
			gameController.getGameModel().setGameID(cModel.getGameID());
			gameController.getGameModel().createAllPlayerFrameFields(cModel.getGameID(), randomPatterns);
			gameController.getGameModel().selectwindowOptions();
			gui.handleChooseScreen();
		}
	}

	// bepaalt random/standaard patterns
	public void setRandomWindow(boolean random) {
		randomPatterns = random;
	}
	
	// voeg spelers to aan de invite lijst
	public void addPlayersToInviteList() {
		ArrayList<ArrayList <Object>> result = cModel.getInviteablePlayers();

		for (ArrayList<Object> objects : result) {
			inviteScreen.addPlayer((String) objects.get(0));
		}
	}
	
	
	// voeg toegevoegde spelers aan speler lijst
	public void addJoinedPlayers() {
		ArrayList<ArrayList <Object>> result = cModel.getJoinedPlayers();

		for (ArrayList<Object> objects : result) {
			if (objects.get(0) != null) {
				setupScreen.addJoinedPlayer((String) objects.get(0));
			}
		}
	}
	
	public void makeGame() {
		cModel.makeGame();
	}
	
	// controlleer aantal spelers in database en voeg nieuwe toe als het mag.
	public void invitePlayer(String username) {
		long invitedPlayerCount = (long) cModel.getInvitedPlayerCount().get(0).get(0);
		String color;
		
		if(invitedPlayerCount < 4) {
			if(cModel.notAlreadyAccepted(username)) {
				if(cModel.checkInviteAllowed(username)) {
					color = cModel.getPrivateObjectiveColor();
					cModel.invitePlayer(username, color);	
				}else {
					inviteScreen.inviteNotAllowedWarning();
				}
			}else {
				inviteScreen.alreadyAcceptedWarning();
			}
		}else{
			inviteScreen.maxInvitedWarning();
		}
	}
	
//////////////////////// inviteGetScreen ///////////////////////////////	
	// schakel van setup scherm naar patternkeuze scherm
	private void openInviteGetScreen() {
		inviteGetScreen = new InviteGetScreen(this, gui);
		addPlayersToInviteGetList();
		//scene.setRoot(inviteGetScreen);
	}

	// voegt uitnodigingen en inviternaam toe aan de invite getlist
	public void addPlayersToInviteGetList() {
		ArrayList<ArrayList <Object>> result = cModel.getInviteGetList();

		for (ArrayList<Object> objects : result) {

			int gameid = (int) objects.get(0);
			String inviter = (String) cModel.getInviter(gameid).get(0).get(0);
			inviteGetScreen.addPlayer(inviter, gameid);
		}
	}
	
	// verandert spelerstatus naar geaccepteerd
	public void acceptInvite(int gameid) {
		cModel.acceptInvite(gameid);
	}
	
	// verandert spelerstatus naar geweigerd
	public void declineInvite(int gameid) {
		cModel.declineInvite(gameid);
	}

	
}
