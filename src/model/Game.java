package model;

import java.util.ArrayList;

public class Game {

	int gameId;
	ArrayList<Player> players = new ArrayList<>();
	
	public Game() {
		addPlayer(new Player());
		addPlayer(new Player());
		addPlayer(new Player());
		addPlayer(new Player());
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public Player getPlayer(int i) {
		return players.get(i);
	}
	
}
