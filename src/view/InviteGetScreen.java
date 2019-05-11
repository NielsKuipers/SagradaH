package view;

import controller.SetupScreenController;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class InviteGetScreen extends BorderPane{
	
	private VBox inviteList;
	private SetupScreenController controller;
	
	public InviteGetScreen(SetupScreenController controller) {
		this.controller = controller;
		inviteList = new VBox();
		
		
	}

}
