package model;

import java.util.ArrayList;

import queries.InviteHandleQueries;

public class CommunicationModel {
	private InviteHandleQueries inviteQueries;
	private ArrayList<String> colors;
	
	public CommunicationModel(InviteHandleQueries inviteQueries) {
		this.inviteQueries = inviteQueries;
		colors = new ArrayList<>();
		addColors();
	}
	
	// return invitelist
	public ArrayList<ArrayList<Object>> getInviteablePlayers() {
		return inviteQueries.getPlayers();
	}
	
	public ArrayList<ArrayList<Object>> getJoinedPlayers() {
		return inviteQueries.getJoinedPlayers();
	}
	
	public void invitePlayer(String username, String color) {
		inviteQueries.invitePlayer(username, color);
	}
	
	public void makeGame() {
		inviteQueries.setupGame(getPrivateObjectiveColor());
	}
	
	public ArrayList<ArrayList<Object>> getInvitedPlayerCount() {
		return inviteQueries.getInvitedPlayerCount();
	}

	public ArrayList<ArrayList<Object>> getInviteGetList() {
		return inviteQueries.getInviteGetList();
	}
	
	public ArrayList<ArrayList<Object>> getInviter(int gameid){
		return inviteQueries.getInviter(gameid);
	}
	
	private void addColors() {
		colors.add("rood");
		colors.add("groen"); 
		colors.add("blauw");
		colors.add("paars"); 
		colors.add("geel");
	}
	
	public String getPrivateObjectiveColor() {
		int random = (int)(Math.random() * colors.size());
		String color = colors.get(random);
		colors.remove(random);
		return color;
	}

	public void acceptInvite(int gameid) {
		inviteQueries.acceptInvite(gameid);
	}

	public void declineInvite(int gameid) {
		inviteQueries.declineInvite(gameid);
	}

	public boolean checkInviteAllowed(String username) {
		return inviteQueries.unAnsweredChallenges(username);
	}
	
	public boolean notAlreadyAccepted(String username) {
		return inviteQueries.alreadyAcceptedInvite(username);
	}

	public boolean checkDeclined() {
		return inviteQueries.checkDeclined();
	}
	
	public boolean checkUnansweredInGame() {
		return inviteQueries.checkUnasweredInGame();
	}
	
}
