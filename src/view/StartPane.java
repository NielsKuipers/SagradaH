package view;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import main.GUI;

public class StartPane extends HBox {
	private RegisterScreen reg = new RegisterScreen();
	private LoginScreen log;
	private Window w1 = new Window();
	private Window w2 = new Window();
	private Window w3 = new Window();
	private Window w4 = new Window();
	public StartPane(GUI gui) {
		log = new LoginScreen(gui);
		HBox hb = new HBox();
		hb.getChildren().addAll(log,reg);
		hb.setSpacing(30.0);
		setSpacing(30.0);
		setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
		getChildren().addAll(w1,w2,hb,w3,w4);
	}
}
