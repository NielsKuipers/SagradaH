package model;

import javafx.scene.paint.Color;

public class Dice {

	private Color color;
	private int eyes;
	private boolean moved;
	
	public Dice(int eyes, Color color) {
		this.eyes = eyes;
		this.color = color;
	}
	
	public void setMoved() {
		moved = true;
	}
	
	public boolean getMoved() {
		return moved;
	}
	
	public int getEyes() {
		return eyes;
	}
	
	public Color getColor() {
		return color;
	}
}
