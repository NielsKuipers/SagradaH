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
	
	public void setClientUsername(String username) {
		inviteQueries.setClientUserName(username);
	}
	
	// setGameID
	public void setGameID(int gameid) {
		inviteQueries.setGameID(gameid);
	}
	
	// return invitelist
	public ArrayList<ArrayList<Object>> getInviteablePlayers() {
		return inviteQueries.getPlayers();
	}
	
	// return gejoinde spelers
	public ArrayList<ArrayList<Object>> getJoinedPlayers() {
		return inviteQueries.getJoinedPlayers();
	}
	
	// invite speler
	public void invitePlayer(String username, String color) {
		inviteQueries.invitePlayer(username, color);
	}
	
	// maak game setup
	public void makeGame() {
		inviteQueries.setupGame(getPrivateObjectiveColor());
	}
	
	// return aantal spelers in game
	public ArrayList<ArrayList<Object>> getInvitedPlayerCount() {
		return inviteQueries.getInvitedPlayerCount();
	}

	// return invite accepteer/weiger lijst
	public ArrayList<ArrayList<Object>> getInviteGetList() {
		return inviteQueries.getInviteGetList();
	}
	
	// return naam van inviter
	public ArrayList<ArrayList<Object>> getInviter(int gameid){
		return inviteQueries.getInviter(gameid);
	}

	// voegt kleuren toe aan private objective card kleur arraylist
	private void addColors() {
		colors.add("rood");
		colors.add("groen"); 
		colors.add("blauw");
		colors.add("paars"); 
		colors.add("geel");
	}
	
	// returnt random private objectivekleur en haalt het uit de arrayList
	public String getPrivateObjectiveColor() {
		ArrayList<ArrayList<Object>> result = inviteQueries.getPrivateColors();
		for(ArrayList<Object> objects: result) {
			for(String color: colors) {
				if(objects.get(0).equals(color)) {
					colors.remove(color);
					break;
				}
			}
		}
		
		int random = (int)(Math.random() * colors.size());
		String color = colors.get(random);
		colors.clear();
		addColors();
		return color;
	}

	public void acceptInvite(int gameid) {
		inviteQueries.acceptInvite(gameid);
	}

	public void declineInvite(int gameid) {
		inviteQueries.declineInvite(gameid);
	}

	// controlleerd of er nog onbeantwoorde invites zijn aan deze speler van de client
	public boolean checkInviteAllowed(String username) {
		return inviteQueries.unAnsweredChallenges(username);
	}
	
	// controlleerd of er niet al geaccepteerd of geweigerd is
	public boolean notAlreadyAccepted(String username) {
		return inviteQueries.alreadyAcceptedInvite(username);
	}

	// controlleerd of iemand een uitnodiging geweigerd heeft
	public boolean checkDeclined() {
		return inviteQueries.checkDeclined();
	}
	
	// controlleerd of er nog onbeantwoorde uitnodigingen zijn
	public boolean checkUnansweredInGame() {
		return inviteQueries.checkUnasweredInGame();
	}
	
	
	public int getGameID() {
		return (int) inviteQueries.getGameID().get(0).get(0);
	}
	
}
