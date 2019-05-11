package model;

public class Player {

	private String playerName;
	private WindowPattern windowPattern;
	
	public WindowPattern getWindowPatternPlayer() {
		return windowPattern;
	}
	
	public void givePlayerWindowPattern(WindowPattern windowPattern) {
		this.windowPattern = windowPattern;
	}
}
