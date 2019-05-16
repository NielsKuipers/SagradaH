package view;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GameScreen extends GridPane{
	
	public GameScreen() {
		setHgap(50); // horizontal gap in pixels => that's what you are asking for
		setVgap(40); // vertical gap in pixels
		setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
		setAlignment(Pos.CENTER);
		
		
		
	}
	
}
