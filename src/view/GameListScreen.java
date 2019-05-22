package view;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.GUI;

public class GameListScreen extends VBox {
	GUI mygui;
	VBox listBox = new VBox();
	ScrollPane scrollPane = new ScrollPane();
	public GameListScreen(GUI mygui) {
		this.mygui = mygui;
		ChoiceBox<Object> sortBar = new ChoiceBox<>();
		ChoiceBox<String> gameBar = new ChoiceBox<>();
		Label sortLabel = new Label("Sorteer op: ");
		HBox sortMenu = new HBox();
		Button toHomePane = new Button("Hoofdmenu");
		toHomePane.setOnAction(e -> handleHomeMenu());
		sortBar.getItems().addAll("Datum aangemaakt","ID");
		gameBar.getItems().addAll("Mijn spellen", "Alle spellen");
		sortBar.getSelectionModel().select(1);
		gameBar.getSelectionModel().select(1);
		scrollPane.setContent(listBox);
		sortMenu.getChildren().addAll(sortLabel, gameBar, sortBar, toHomePane);
		gameBar.setOnAction(e -> sendString(gameBar.getValue()));
		sortBar.setOnAction(e -> handleSort(sortBar.getValue()));
		setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
		getChildren().addAll(sortMenu, scrollPane);
	}
	
	public void handleSort(Object V) {
		listBox.getChildren().clear();
		mygui.handlegamesort(V);
	}
	
	public void handleHomeMenu() {
		mygui.handleHomeMenu();
	}
	
	public void showGames(ArrayList<HBox> boxes) {
		listBox.getChildren().addAll(boxes);
	}
	
	public void sendString(String S) {
		mygui.sendString(S);
	}
	
}
