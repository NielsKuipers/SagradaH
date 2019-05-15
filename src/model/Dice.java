package model;

import java.sql.Connection;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class Dice {

	private int id;
	private Color color;
	private boolean moved;
	private Property<Background> backgroundProperty;
	private IntegerProperty eyesProperty;


	public Dice(int eyes, Color color, int dieNumber) {
		this.color = color;
		backgroundProperty = new SimpleObjectProperty<>();
		backgroundProperty.setValue(new Background(new BackgroundFill(color, null, null)));
		eyesProperty = new SimpleIntegerProperty(this, "eyesProperty");
	}

	public void setEyes(int newVal) {
		eyesProperty.set(newVal);
	}

	public void setMoved() {
		moved = true;
	}

	public boolean getMoved() {
		return moved;
	}

	public int getEyes() {
		return eyesProperty.intValue();
	}

	public Color getColor() {
		return color;
	}

	public Property<Background> backgroundPropery() {
		return backgroundProperty;
	}

	public final IntegerProperty eyesProperty() {
		return eyesProperty;
	}
	
	public void setColor(Color color) {
		this.color = color;
		backgroundProperty.setValue(new Background(new BackgroundFill(color, null, null)));
	}

}
