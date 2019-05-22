package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

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
