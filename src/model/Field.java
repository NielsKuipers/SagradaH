package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class Field {

	private int row;
	private int column;
	private Color color;
	private IntegerProperty eyesProperty;
	private Property<Background> backgroundProperty;
	private Dice dice;
	
	public Field(int column, int row, Color color, int eyes) {
		this.row = row;
		this.column = column;
		this.color = color;
		backgroundProperty = new SimpleObjectProperty<>();
		backgroundProperty.setValue(new Background(new BackgroundFill(color, null, null)));
		eyesProperty = new SimpleIntegerProperty(this, "eyesProperty");
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getEyes() {
		return eyesProperty.intValue();
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
		backgroundProperty.setValue(new Background(new BackgroundFill(color, null, null)));
	}
	
	public void setEyes(int eyes) {
		eyesProperty.set(eyes);
	}
	
	public final IntegerProperty eyesProperty() {
		return eyesProperty;
	}
	
	public Property<Background> backgroundPropery() {
		return backgroundProperty;
	}
	
	public void setColorAndEyes(Color color, int eyes) {
		this.color = color;
		backgroundProperty.setValue(new Background(new BackgroundFill(color, null, null)));
		eyesProperty.set(eyes);
	}
}
