package view;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class WindowPatternScreen extends GridPane{
	Label name;
	Font font = new Font("Consolas", 18);
	Label difficulty;
	
	
	public WindowPatternScreen(String name) {
		this.name = new Label(name);
		difficulty = new Label();
		this.add(this.name, 2, 0, 5, 1);
		this.name.setFont(font);
		setStyle("-fx-background-color: DEEPSKYBLUE;-fx-background-radius: 20 20 20 20;");
		this.add(difficulty, 0, 5, 5, 1);
		
	}
	
	// Get a field of the grid/window pattern
	public FieldScreen getNode(int column, int row) {
		FieldScreen result = null;
		for (Node node : this.getChildren()) {
			if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row && node instanceof FieldScreen) {
				result = (FieldScreen) node;
			}
		}
		return result;
	}
	
	public void setDifficulty(int value) {
		difficulty.setText("Moeilijkheidsgraad: " + Integer.toString(value));
		difficulty.setFont(font);
		setHalignment(difficulty, HPos.CENTER);
	}
	

}
