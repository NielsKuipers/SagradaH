package view;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.WindowPattern;

public class WindowPatternScreen extends GridPane{
	Label name;
	Font font = new Font("Consolas", 18);
	Label difficulty;
	WindowPattern windowPatternModel;
	
	public WindowPatternScreen(String name, WindowPattern windowPatternModel, String color) {
		this.windowPatternModel = windowPatternModel;
		this.name = new Label(name);
		difficulty = new Label();
		this.add(this.name, 2, 0, 5, 1);
		this.name.setFont(font);
		setStyle("-fx-background-color: linear-gradient(to bottom, "+color+" 0%,#cccccc 100%);-fx-background-radius: 20 20 20 20;");
		this.add(difficulty, 0, 5, 5, 1);
		
	}
	
	// Get a field of the grid/window pattern

	
	public void setDifficulty(int value) {
		difficulty.setText("Moeilijkheidsgraad: " + Integer.toString(value));
		difficulty.setFont(font);
		setHalignment(difficulty, HPos.CENTER);
	}
	
	public WindowPattern getWindowPatternModel() {
		return windowPatternModel;
	}
	
	public void setColor(int column, int row, Color color) {
		FieldScreen result;
		for (Node node : this.getChildren()) {
			if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row && node instanceof FieldScreen) {
				result = (FieldScreen) node;
				result.setColor(color);
			}
		}
	}
	
	public void setEyes(int column, int row, int eyes) {
		FieldScreen result;
		
		for (Node node : this.getChildren()) {
			if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row && node instanceof FieldScreen) {
				
				result = (FieldScreen) node;
				result.deleteEyesOnField();
				result.checkNumber(eyes);
			}
		}
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
