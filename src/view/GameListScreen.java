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
		Button sortIt = new Button("Sorteer");
		Button toHomePane = new Button("Hoofdmenu");
		toHomePane.setOnAction(e -> handleHomeMenu());
		sortBar.getItems().addAll("Datum aangemaakt","ID");
		gameBar.getItems().addAll("Mijn spellen", "Alle spellen");
		sortBar.getSelectionModel().select(1);
		gameBar.getSelectionModel().select(1);
		scrollPane.setContent(listBox);
		scrollPane.setFitToWidth(true);
		sortMenu.getChildren().addAll(sortLabel, gameBar, sortBar, sortIt, toHomePane);
		sortIt.setOnMouseClicked(e -> handleSort(gameBar.getValue(), sortBar.getValue()));
		setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
		getChildren().addAll(sortMenu, scrollPane);
	}
	
	public void handleHomeMenu() {
		mygui.handleHomeMenu();
	}
	
	public void showGames(ArrayList<HBox> boxes) {
		listBox.getChildren().clear();
		listBox.getChildren().addAll(boxes);
	}
	
	public void handleSort(String S, Object V) {
		mygui.sendString(S);
		listBox.getChildren().clear();
		mygui.handlegamesort(V);
	}
}
