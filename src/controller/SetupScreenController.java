package controller;

import java.util.ArrayList;

import main.GUI;
import model.CommunicationModel;
import view.InviteGetScreen;
import view.InviteScreen;
import view.SetupScreen;


public class SetupScreenController {
	
	private SetupScreen setupScreen;
	private CommunicationModel cModel;
	private InviteScreen inviteScreen;
	private InviteGetScreen inviteGetScreen;
	private GUI gui;
	private boolean randomPatterns = false;
	private GameController gameController;
	private AccountController accountController;
	
	public SetupScreenController(DatabaseController dataController, GUI gui, GameController gameController, AccountController accountController) {
		this.gui = gui;
		this.gameController = gameController;
		this.accountController = accountController;
		cModel = new CommunicationModel(dataController.getInviteQueries());
		inviteScreen = new InviteScreen(this, gui);
		setupScreen = new SetupScreen(this, gui);
		inviteGetScreen = new InviteGetScreen(this, gui);
		cModel.setClientUsername(accountController.getAccount());
	}
	
	
	/**
	 * setup when a new game needs to be made
	 */
	public void makeNewGame() {
		setupScreen.makeNewGame();
		randomPatterns = false;
		cModel.setClientUsername(accountController.getAccount());
	}
	
	
	/** setup when existing game needs to be loaded
	 * @param gameid
	 */
	public void loadSetup(int gameid) {
		cModel.setGameID(gameid);
		setupScreen.loadSetup();
		randomPatterns = false;
		cModel.setClientUsername(accountController.getAccount());
		addJoinedPlayers();

	}
	
	public SetupScreen getSetupScreen() {
		return setupScreen;
	}
	
	public InviteScreen getInviteScreen() {
		return inviteScreen;
	}

	
	/**
	 * game starts when all invited player have accepted. switches from setupscreen to windowchoosescreen
	 */
	public void startGame() {
		if((long) cModel.getInvitedPlayerCount().get(0).get(0) < 2) {
			setupScreen.warning(3);
		}else if(cModel.checkDeclined()) {
			setupScreen.warning(1);
		}else if(cModel.checkUnansweredInGame()) {
			setupScreen.warning(2);
		}else{
			gui.setGameIDforScoreCalc(cModel.getGameID());
			gameController.getGameModel().setGameID(cModel.getGameID());
			gameController.getGameModel().createAllPlayerFrameFields(cModel.getGameID(), randomPatterns);
			gameController.addWindowScreens();
			gameController.getGameModel().selectwindowOptions();
			gui.handleChooseScreen();
		}
	}

	
	public void setRandomWindow(boolean random) {
		randomPatterns = random;
	}

	
	/**
	 * switch from setupscreen to invitescreen
	 */
	public void openInviterMenu() {
		gui.changePane(inviteScreen);
		inviteScreen.clearList();
		addPlayersToInviteList();
	}
	
	
	
	/**
	 * switch from invitescreen to setup screen
	 */
	public void openSetupMenu() {
		gui.changePane(setupScreen);
		setupScreen.clearJoinedList();
		addJoinedPlayers();
	}
	
	
	/**
	 * add players to invitelist
	 */
	public void addPlayersToInviteList() {
		ArrayList<ArrayList <Object>> result = cModel.getInviteablePlayers();

		for (ArrayList<Object> objects : result) {
			inviteScreen.addPlayer((String) objects.get(0));
		}
	}
		
	
	/**
	 * add joined players to setupscreen
	 */
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
	
	
	/** invite player if allowed, otherwise put up warning screen
	 * @param username
	 */
	public void invitePlayer(String username) {
		long invitedPlayerCount = (long) cModel.getInvitedPlayerCount().get(0).get(0);
		String color;
		
		if(invitedPlayerCount < 4) {
			if(cModel.notAlreadyAccepted(username)) {
				if(cModel.checkInviteAllowed(username)) {
					color = cModel.getPrivateObjectiveColor();
					cModel.invitePlayer(username, color);	
				}else {
					inviteScreen.warning(3);
				}
			}else {
				inviteScreen.warning(2);
			}
		}else{
			inviteScreen.warning(1);
		}
	}
	
	public void toSetupScreen() {
		gui.changePane(setupScreen);
	}
	

	
	//////////////////////// inviteGetScreen ///////////////////////////////	
		
	/**
	 * add players to invites list  
	 */
	public void addPlayersToInviteGetList() {
		ArrayList<ArrayList <Object>> result = cModel.getInviteGetList();

		for (ArrayList<Object> objects : result) {

			int gameid = (int) objects.get(0);
			String inviter = (String) cModel.getInviter(gameid).get(0).get(0);
			inviteGetScreen.addPlayer(inviter, gameid);
		}
	}
	
	// switch playstatus to accepted
	public void acceptInvite(int gameid) {
		cModel.acceptInvite(gameid);
	}
	
	// switch playstatus to declined
	public void declineInvite(int gameid) {
		cModel.declineInvite(gameid);
	}
	
	public void toInviteGetScreen() {
		cModel.setClientUsername(accountController.getAccount());
		gui.changePane(this.inviteGetScreen);
		inviteGetScreen.refreshList();
	}

	
}
