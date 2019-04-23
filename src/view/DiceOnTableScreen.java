package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import main.GUI;
import model.DiceOnTable;

public class DiceOnTableScreen extends GridPane{
	DiceOnTable diceOnTableModel;
	private Button createNewDices;
	private GUI gui;
	
	public DiceOnTableScreen(GUI gui, DiceOnTable diceOnTableModel) {
		this.diceOnTableModel = diceOnTableModel;
		
		createNewDices = new Button("Gooi");
		createNewDices.setOnAction(e -> gui.makeDices());
		
		add(createNewDices, 2, 3, 5, 1);
		
		setHgap(10);
		setVgap(10);
		setAlignment(Pos.CENTER);
		setStyle("-fx-background-radius: 0 0 0 300;-fx-background-color: DEEPSKYBLUE;");
	}

}
