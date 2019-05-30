package view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.FavorToken;

public class FavorTokensScreen extends StackPane {

	private FavorToken FTmodel;

	public FavorTokensScreen(FavorToken FTmodel) {
		Circle c = new Circle();
		Label amount = new Label();
		this.FTmodel = FTmodel;

		c.setRadius(20);
		c.setOpacity(0.8);
		c.setFill(FTmodel.getColor());
		c.setStroke(Color.BLACK);

		amount.textProperty().bind(FTmodel.getAmountProperty());
		
		getChildren().addAll(c, amount);
		
		
		

	}
	
	
	public FavorToken getModel() {
		return FTmodel;
	}
	
	
	
	
	

}
