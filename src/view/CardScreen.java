package view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CardScreen extends Pane{
	private ImageView imageView;
	
	
	public CardScreen(String FileLink){
		imageView = new ImageView(new Image(FileLink));
		
		imageView.setFitHeight(300);
		imageView.setFitWidth(200);
		
		
	
		
		
		
		
		getChildren().addAll(imageView);
	}
	
	

	
	
	

}
