package view;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RegisterScreen extends VBox {
	private Button register;
	private TextField username = new TextField();
	private PasswordField password = new PasswordField();
	public RegisterScreen() {
		addFields();
		addButton();
		setAlignment(Pos.CENTER_RIGHT);
	}
	
	private void addButton() {
		register = new Button("Register");
		register.setMaxWidth(150);
		getChildren().add(register);
	}
	private void addFields() {
		//username = new TextField();
		username.setPromptText("Username");
		//password = new PasswordField();
		password.setPromptText("Password");
		getChildren().addAll(username,password);
	}
}
