package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CardScreen extends Pane{

	public CardScreen(String FileLink){
		ImageView imageView = new ImageView(new Image(FileLink));
		
		imageView.setFitHeight(300);
		imageView.setFitWidth(200);

		getChildren().addAll(imageView);
	}
	
	

	
	
	

}
