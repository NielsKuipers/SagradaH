package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

public class FavorToken {
	
	
	private Color color;
	
	
	private StringProperty amountProperty;
	
	public FavorToken(int amount, Color color){
		this.color = color;
		
		amountProperty =  new SimpleStringProperty(this,"amountProperty");
		setAmount(amount);
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setAmount(int amount) {
		amountProperty.set(Integer.toString(amount));
	}
	
	public int getAmount() {
		return Integer.parseInt(amountProperty.get());
	}
	
	

	public StringProperty getAmountProperty() {
		return amountProperty;
	}
	
	

}
