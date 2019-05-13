package model;

import java.util.ArrayList;

import controller.DatabaseController;
import queries.InviteHandleQueries;

public class CommunicationModel {
	private InviteHandleQueries inviteQueries;
	
	public CommunicationModel(InviteHandleQueries inviteQueries) {
		this.inviteQueries = inviteQueries;
	}
	
	// return invitelist
	public ArrayList<ArrayList<Object>> getInviteablePlayers() {
		return inviteQueries.getPlayers();
	}
	
	public ArrayList<ArrayList<Object>> getJoinedPlayers() {
		return inviteQueries.getJoinedPlayers();
	}
	
	public void invitePlayer(String username) {
		inviteQueries.invitePlayer(username);
	}
	
	
}
