package main;


import controller.DiceController;
import controller.GameController;
import controller.WindowController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main_Class extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	public void start(Stage stage){
		
		WindowController windowController = new WindowController();
		DiceController diceController = new DiceController(windowController);
		GameController gameController = new GameController(windowController, diceController);
		

		stage.setScene(gameController);
		stage.setResizable(true);
		stage.show();
		
	}

}
