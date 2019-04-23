package model;

import javafx.scene.paint.Color;

public class Field {

	private int row;
	private int column;
	private Color color;
	private int eyes;
	private Dice dice;
	
	public Field(int column, int row, Color color, int eyes) {
		this.row = row;
		this.column = column;
		this.color = color;
		this.eyes = eyes;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public String getColor() {
		return color.toString();
	}
	
	public Color getColorNotString() {
		return color;
	}
	
	public int getEyes() {
		return eyes;
	}
	
	public boolean hasDice() {
		if(dice == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public void addDice(Dice dice) {
		this.dice = dice;
	}
	
	public Dice getDice() {
		return dice;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setEyes(int eyes) {
		this.eyes = eyes;
	}
}
