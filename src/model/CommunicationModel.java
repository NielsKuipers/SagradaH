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
	
	public void setGameID(int gameid) {
		inviteQueries.setGameID(gameid);
	}
	
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

	//add colors to arraylist, representing the private objective colors
	private void addColors() {
		colors.add("rood");
		colors.add("groen"); 
		colors.add("blauw");
		colors.add("paars"); 
		colors.add("geel");
	}
	
	
	/** return random private objectivecolor and remove it from the arrayList
	 * @return random color
	 */
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
	
	
	public int getGameID() {
		return inviteQueries.getGameID();
	}
	
}
