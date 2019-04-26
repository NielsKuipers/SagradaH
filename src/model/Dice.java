package model;

import java.sql.Connection;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import queries.DiceQueries;

public class Dice {

	private Color color;
	private boolean moved;
	Property<Background> backgroundProperty;
	private IntegerProperty eyesProperty;

	DiceQueries diceQueries;

	public Dice(Connection connection, int eyes, Color color) {
		diceQueries = new DiceQueries(connection);

		this.color = color;
		backgroundProperty = new SimpleObjectProperty<>();
		backgroundProperty.setValue(new Background(new BackgroundFill(color, null, null)));
		eyesProperty = new SimpleIntegerProperty(this, "eyesProperty");
		diceQueries.getName("lucas");
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
