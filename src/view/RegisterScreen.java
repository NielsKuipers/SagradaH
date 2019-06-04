package view;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.GUI;

public class RegisterScreen extends VBox {
	private TextField username;
	private PasswordField password;
	private GUI myGUI;
	RegisterScreen(GUI mygui) {
		this.myGUI = mygui;
		addFields();
		addButton();
		setAlignment(Pos.CENTER_RIGHT);
	}
	
	private void addButton() {
		Button register = new Button("Registreren");
		register.setMaxWidth(150);
		getChildren().add(register);
		register.setOnMouseClicked(e -> handleClick());
	}
	
	private void addFields() {
		username = new TextField();
		username.setPromptText("Gebruikersnaam");
		password = new PasswordField();
		password.setPromptText("Wachtwoord");
		getChildren().addAll(username,password);
		username.setOnKeyReleased(e -> {if(e.getCode() == KeyCode.ENTER) {
			handleClick();
		}});
		password.setOnKeyReleased(e -> {if(e.getCode() == KeyCode.ENTER) {
			handleClick();
		}});
	}
	
	private void handleClick() {
		myGUI.handleregister(username, password);
	}
	
	public void setGreenBorder(TextField username, PasswordField password) {
		username.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		password.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
	}
	public void setRedBorder(TextField username, PasswordField password) {
		username.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		password.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
	}
}
