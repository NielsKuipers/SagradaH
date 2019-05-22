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

	private Color color;
	private int dieNumber;
	private Property<Background> backgroundProperty;
	private IntegerProperty eyesProperty;


	public Dice(int eyes, Color color, int dieNumber) {
		this.color = color;
		this.dieNumber = dieNumber;
		backgroundProperty = new SimpleObjectProperty<>();
		backgroundProperty.setValue(new Background(new BackgroundFill(color, null, null)));
		eyesProperty = new SimpleIntegerProperty(this, "eyesProperty");
	}

	public void setEyes(int newVal) {
		eyesProperty.set(newVal);
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

	public int getDiceNumber() {
		return dieNumber;
	}
	
	public String getColorForQuerie() {
		if(color == Color.RED) {
			return "rood";
		}
		else if(color == Color.YELLOW) {
			return "geel";
		}
		else if(color == Color.MAGENTA) {
			return "paars";
		}
		else if(color == Color.LIGHTGREEN) {
			return "groen";
		}
		else if(color == Color.CORNFLOWERBLUE) {
			return "blauw";
		}
		return "rood";
	}
}
