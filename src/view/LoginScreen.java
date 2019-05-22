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
		username = new TextField("Gijs");
		username.setPromptText("Username");
		password = new PasswordField();
		password.setPromptText("Password");
		getChildren().addAll(username,password);
	}
	
	public void badFields(TextField username, PasswordField password) {
		//System.out.println(password.getBorder());
		//System.out.println(username.getText());
		username.clear();
		username.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		password.clear();
		password.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		//System.out.println(password.getBorder());
	}
	
	public void emptyFields() {
		username.clear();
		password.clear();
	}
	
	public void handleClick() {
		myGUI.handlelogin(username, password);
		//username.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
	}
	
}
