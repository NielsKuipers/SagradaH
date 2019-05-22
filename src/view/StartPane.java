package view;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import main.GUI;

public class StartPane extends HBox {
	private RegisterScreen reg;
	private LoginScreen log;

	public StartPane(GUI gui) {
		log = new LoginScreen(gui);
		reg = new RegisterScreen(gui);
		HBox hb = new HBox();
		hb.getChildren().addAll(log,reg);
		hb.setSpacing(30.0);
		setSpacing(30.0);
		setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
		Window w1 = new Window();
		Window w2 = new Window();
		Window w3 = new Window();
		Window w4 = new Window();
		getChildren().addAll(w1, w2,hb, w3, w4);
	}
	
	public RegisterScreen getReg() {
		return reg;
	}
	public LoginScreen getLog() {
		return log;
	}
}
