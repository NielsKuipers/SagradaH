package view;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import main.GUI;

public class HomePane extends HBox {

	public HomePane(GUI mygui) {
		Buttons b = new Buttons(mygui);
		Window w1 = new Window();
		Window w2 = new Window();
		Window w3 = new Window();
		Window w4 = new Window();
		setSpacing(30.0);
		setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
		getChildren().addAll(w1, w2, b, w3, w4);
	}
}
