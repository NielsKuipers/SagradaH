package view;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WindowPatternChooseScreen extends GridPane {

	private Label text;
	Font font = new Font("Consolas", 60);

	public WindowPatternChooseScreen() {
		setHgap(50); // horizontal gap in pixels => that's what you are asking for
		setVgap(40); // vertical gap in pixels
		setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
		setAlignment(Pos.CENTER);
		setMinHeight(750);
		
		text = new Label("WindowPatternChooseScreen");
		text.setFont(font);
		this.add(text, 0, 0, 4, 1);
		this.setHalignment(text, HPos.CENTER);

	}

}
