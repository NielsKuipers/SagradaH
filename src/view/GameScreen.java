package view;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class GameScreen extends GridPane{
	
	public GameScreen() {
		setHgap(50);
		setVgap(40);
		setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
		setAlignment(Pos.CENTER);
	}
	
}
