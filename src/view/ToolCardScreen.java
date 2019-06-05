package view;

import javafx.scene.image.Image;

public class ToolCardScreen extends CardScreen {

	public ToolCardScreen(Image FileLink) {
		super(FileLink);
	}
	
	public void addFTScreens(FavorTokensScreen FVS1,FavorTokensScreen FVS2,FavorTokensScreen FVS3,FavorTokensScreen FVS4) {
		
		
		
		
		
		FVS1.setLayoutX(10);
		FVS1.setLayoutY(55);
		
		FVS2.setLayoutX(60);
		FVS2.setLayoutY(55);
		
		FVS3.setLayoutX(110);
		FVS3.setLayoutY(55);
		
		FVS4.setLayoutX(160);
		FVS4.setLayoutY(55);
		super.getChildren().remove(FVS1);
		super.getChildren().remove(FVS2);
		super.getChildren().remove(FVS3);
		super.getChildren().remove(FVS4);
		
		super.getChildren().addAll(FVS1,FVS2,FVS3,FVS4);
	}

}
