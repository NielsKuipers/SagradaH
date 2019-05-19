package view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.FavorToken;

import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;

public class FavorTokensScreen extends StackPane {
	
	private Label amount;
	
	private FavorToken FTmodel;
	private Circle c;
	
	public FavorTokensScreen(FavorToken FTmodel) {
		c =new Circle();
		amount = new Label();
		this.FTmodel = FTmodel;

		c.setRadius(20);
		c.setOpacity(0.8);
		c.setFill(FTmodel.getColor());
		c.setStroke(Color.BLACK);
		
		getChildren().addAll(c,amount);
		
		
		
		amount.textProperty().bind(FTmodel.getAmountProperty());
		

	}
	
	
	public FavorToken getModel() {
		return FTmodel;
	}
	
	
	
	
	

}
