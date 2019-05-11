package model;

import java.util.ArrayList;

import queries.InviteHandleQueries;

public class CommunicationModel {
	private InviteHandleQueries inviteQueries;
	
	public CommunicationModel() {
		
	}
	
	// return invitelist
	public ArrayList<ArrayList<Object>> getInviteablePlayers() {
		inviteQueries = new InviteHandleQueries();
		return inviteQueries.getPlayers();
	}
	
	public ArrayList<ArrayList<Object>> getJoinedPlayers() {
		inviteQueries = new InviteHandleQueries();
		return inviteQueries.getJoinedPlayers();
	}
	
	
}
