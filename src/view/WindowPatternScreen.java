package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.WindowPattern;

public class WindowPatternScreen extends GridPane{
	private WindowPattern windowPatternModel;

	public WindowPatternScreen(String name, WindowPattern windowPatternModel, String color) {
		this.windowPatternModel = windowPatternModel;
		
		Label name1 = new Label(name);
		Label difficulty = new Label();
		Label score = new Label();
		
		Font font = new Font("Consolas", 18);
		name1.setFont(font);
		difficulty.setFont(font);
		score.setFont(font);
		
		setStyle("-fx-background-color: linear-gradient(to bottom, "+color+" 0%,#cccccc 100%);-fx-background-radius: 20 20 20 20;");
		
		this.add(name1, 0, 0, 5, 1);
		this.add(difficulty, 0, 5, 5, 1);
		this.add(score, 0, 6, 5, 1);
		
		setHalignment(score, HPos.CENTER);
		setHalignment(difficulty, HPos.CENTER);
		setHalignment(name1, HPos.CENTER);
		
		score.textProperty().bind(windowPatternModel.playerScoreProperty());
		difficulty.textProperty().bind(windowPatternModel.difficultyProperty());
		name1.textProperty().bind(windowPatternModel.playerNameProperty());
		this.windowPatternModel.backgroundPropery().addListener(new MyBackgroundListener());
		
		setHgap(2); 
		setVgap(2);

		setPadding(new Insets(20, 20, 20, 20));
	}
	
	
	public WindowPattern getWindowPatternModel() {
		return windowPatternModel;
	}
	
	
	
	/**
	 * @param column = field on which column
	 * @param row =  field on which row
	 * make border of field glow
	 */
	public void setCheat(int column, int row) {
		FieldScreen result;
		for (Node node : this.getChildren()) {
			if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row && node instanceof FieldScreen) {
				result = (FieldScreen) node;
				result.cheatBorder();
			}
		}
	}
	
	/**
	 * @param column = field on which column
	 * @param row =  field on which row
	 * make border of field normal
	 */
	public void setNormal(int column, int row) {
		FieldScreen result;
		for (Node node : this.getChildren()) {
			if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row && node instanceof FieldScreen) {
				result = (FieldScreen) node;
				result.normalBorder();
			}
		}
	}
	
	/**
	 *check if background has changed, when changed change window background
	 */
	private class MyBackgroundListener implements ChangeListener<Color> {
		@Override
		public void changed(ObservableValue<? extends Color> observable, Color oldValue, Color newValue) {
			if(newValue == Color.WHITE) {
				setStyle("-fx-background-color: linear-gradient(to bottom, white 0%,#cccccc 100%);-fx-background-radius: 20 20 20 20;");
			}
			else if(newValue == Color.RED) {
				setStyle("-fx-background-color: linear-gradient(to bottom, red 0%,#cccccc 100%);-fx-background-radius: 20 20 20 20;");
			}
			else if(newValue == Color.BLUE) {
				setStyle("-fx-background-color: linear-gradient(to bottom, blue 0%,#cccccc 100%);-fx-background-radius: 20 20 20 20;");
			}
			else if(newValue == Color.GREEN) {
				setStyle("-fx-background-color: linear-gradient(to bottom, green 0%,#cccccc 100%);-fx-background-radius: 20 20 20 20;");
			}
			else if(newValue == Color.YELLOW) {
				setStyle("-fx-background-color: linear-gradient(to bottom, yellow 0%,#cccccc 100%);-fx-background-radius: 20 20 20 20;");
			}
			else if(newValue == Color.PINK) {
				setStyle("-fx-background-color: linear-gradient(to bottom, red 20%, orange 40%,yellow 60%,lightgreen 80%, aqua 100%);-fx-background-radius: 20 20 20 20;");
			}
				
		}
	}
}
