package view;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class HomeScreen extends Scene {
	private HomePane homePane;
	public HomeScreen() {
		super(new Pane());
		this.homePane = new HomePane();
		setRoot(homePane);
		
	}
}
