package controller;

import java.util.ArrayList;
import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import view.DiceScreen;
import view.FieldScreen;

public class DiceController {
	private GridPane draggableDices;
	private ArrayList<Color> colorsDice = new ArrayList<>();
	private Random r = new Random();
	private Button createNewDices;
	private WindowController WC;
	
	public DiceController(WindowController WC) {
		
		this.WC = WC;
		
		draggableDices = new GridPane();
		draggableDices.setStyle("-fx-background-radius: 0 0 0 300;-fx-background-color: DEEPSKYBLUE;");
		
		createNewDices = new Button("Gooi");
		createNewDices.setOnAction(e -> makeDices());

		draggableDices.add(createNewDices, 2, 3, 5, 1);
		
		addColorsDice();
		makeDices();
		
		draggableDices.setHgap(10);
		draggableDices.setVgap(10);
		draggableDices.setAlignment(Pos.CENTER);
	}
	
	public void makeDices() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 2; j++) {
				DiceScreen b = new DiceScreen(r.nextInt((6 - 1) + 1) + 1, colorsDice.get(r.nextInt(5)));
				b.setPrefHeight(35);
				b.setPrefWidth(35);
				draggableDices.add(b, i, j);
				WC.dragButton(b);
			}
		}

	}
	
	public void addColorsDice() {
		colorsDice.add(Color.CORNFLOWERBLUE);
		colorsDice.add(Color.YELLOW);
		colorsDice.add(Color.RED);
		colorsDice.add(Color.MAGENTA);
		colorsDice.add(Color.LIGHTGREEN);
	}
	
	public GridPane getDraggableDices(){
		return draggableDices;
	}

}
