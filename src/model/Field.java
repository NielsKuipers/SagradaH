package model;

import java.sql.Connection;

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
	private Property<Dice> diceProperty;

	public Field(int column, int row, Color color, int eyes, int id) {
		this.row = row;
		this.column = column;
		this.color = color;
		backgroundProperty = new SimpleObjectProperty<>();
		backgroundProperty.setValue(new Background(new BackgroundFill(color, null, null)));
		eyesProperty = new SimpleIntegerProperty(this, "eyesProperty");
		diceProperty = new SimpleObjectProperty<>();
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
		if (diceProperty.getValue() == null) {
			return false;
		} else {
			return true;
		}
	}

	public void addDice(Dice dice) {
		diceProperty.setValue(dice);
	}

	public Dice getDice() {
		return diceProperty.getValue();
	}
	
	public Property<Dice> diceProperty(){
		return diceProperty;
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

	public void deleteDice() {
		diceProperty.setValue(null);
	}
	
}
