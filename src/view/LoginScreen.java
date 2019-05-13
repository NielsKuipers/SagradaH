package view;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.GUI;

public class LoginScreen extends VBox {
	private Button login;
	private TextField username;
	private PasswordField password;
	GUI myGUI;
	public LoginScreen(GUI mygui) {
		this.myGUI = mygui;
		addFields();
		addButton();
		setAlignment(Pos.CENTER_LEFT);
	}
	
	private void addButton() {
		login = new Button("Login");
		login.setMaxWidth(150);
		getChildren().add(login);
		login.setOnMouseClicked(e -> handleClick());
	}
	private void addFields() {
		username = new TextField();
		username.setPromptText("Username");
		password = new PasswordField();
		password.setPromptText("Password");
		getChildren().addAll(username,password);
	}
	
	public void emptyFields() {
		username.setText(null);
		username.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(5))));
		password.setText(null);
		password.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(5))));
	}
	
	public void handleClick() {
		System.out.print("handleClick");
		myGUI.handlelogin("gijs", "gijs");
	}
	
}
