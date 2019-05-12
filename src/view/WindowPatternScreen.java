package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.WindowPattern;

public class WindowPatternScreen extends GridPane{
	private Label name;
	private Font font = new Font("Consolas", 18);
	private Label difficulty;
	private WindowPattern windowPatternModel;
	
	public WindowPatternScreen(String name, WindowPattern windowPatternModel, String color) {
		this.windowPatternModel = windowPatternModel;
		this.name = new Label(name);
		difficulty = new Label();
		this.add(this.name, 0, 0, 5, 1);
		this.name.setFont(font);
		setStyle("-fx-background-color: linear-gradient(to bottom, "+color+" 0%,#cccccc 100%);-fx-background-radius: 20 20 20 20;");
		this.add(difficulty, 0, 5, 5, 1);
		difficulty.setFont(font);
		setHalignment(difficulty, HPos.CENTER);
		setHalignment(this.name, HPos.CENTER);
		difficulty.textProperty().bind(windowPatternModel.difficultyProperty());
		this.name.textProperty().bind(windowPatternModel.playerNameProperty());
		
		setHgap(2); // horizontal gap in pixels
		setVgap(2); // vertical gap in pixels

		setPadding(new Insets(20, 20, 20, 20));
	}
	
	// Get a field of the grid/window pattern

	
	public WindowPattern getWindowPatternModel() {
		return windowPatternModel;
	}
	
	
	
	public void setCheat(int column, int row) {
		FieldScreen result;
		for (Node node : this.getChildren()) {
			if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row && node instanceof FieldScreen) {
				result = (FieldScreen) node;
				result.cheatBorder();
			}
		}
	}
	
	public void setNormal(int column, int row) {
		FieldScreen result;
		for (Node node : this.getChildren()) {
			if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row && node instanceof FieldScreen) {
				result = (FieldScreen) node;
				result.normalBorder();
			}
		}
	}
	
	
	

}
