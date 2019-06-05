package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CardScreen extends Pane{

	public CardScreen(Image FileLink){
		ImageView imageView = new ImageView(FileLink);
		
		imageView.setFitHeight(300);
		imageView.setFitWidth(200);

		getChildren().addAll(imageView);
	}
	
	

	
	
	

}
