package view;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private HomeScreen homeScreen = new HomeScreen();
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(homeScreen);
		stage.show();
	}

}
