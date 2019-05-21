package model;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class FavorToken {
	
	private int amount;
	private Color color;
	
	
	private StringProperty amountProperty;
	
	public FavorToken(int amount, Color color){
		this.color = color;
		this.amount = amount;
		amountProperty =  new SimpleStringProperty(this,"amountProperty");
		amountProperty.set(String.valueOf(this.amount));
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	

	public StringProperty getAmountProperty() {
		return amountProperty;
	}
	
	

}
