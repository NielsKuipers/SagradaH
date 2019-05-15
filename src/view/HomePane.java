package view;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import main.GUI;

public class HomePane extends HBox {
	GUI mygui;
	private Window w1;
	private Window w2;
	private Window w3;
	private Window w4;
	private Buttons b;
	public HomePane(GUI mygui) {
		this.mygui = mygui;
		b = new Buttons(mygui);
		w1 = new Window();
		w2 = new Window();
		w3 = new Window();
		w4 = new Window();
		setSpacing(30.0);
		setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
		getChildren().addAll(w1, w2, b, w3, w4);
	}
}
