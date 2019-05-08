package view;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class HomePane extends HBox {
	private Window w1 = new Window();
	private Window w2 = new Window();
	private Window w3 = new Window();
	private Window w4 = new Window();
	private Buttons b = new Buttons();
	public HomePane() {
		setSpacing(30.0);
		setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
		getChildren().addAll(w1, w2, b, w3, w4);
	}
}
