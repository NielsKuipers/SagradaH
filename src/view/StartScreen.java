package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import main.GUI;

public class StartScreen extends Scene {
	private StartPane startPane;
	private GUI gui;
	public StartScreen(GUI gui) {
		super(new Pane());
		this.gui = gui;
		startPane = new StartPane(gui);
		setRoot(startPane);

	}
}
