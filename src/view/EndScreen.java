package view;
import java.util.ArrayList;

import controller.EndScreenController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class EndScreen extends BorderPane {

	private GridPane gridpane;
	private ArrayList <StackPane> stackpanes;
	private Label text;
	private EndScreenController controller;

	public EndScreen(EndScreenController controller) {
		this.controller = controller;
		gridpane = new GridPane();
		stackpanes = new ArrayList<StackPane>();

		makeGridPane();
		makeTop();
		makeBottom();
		
	}

	// maakt  scorebord rondjes + getallen
	private void makeGridPane() {
		int rowNumber = 0;
		int columnNumber = 0;
		for (int i = 0; i < 100; i++) {
			stackpanes.add(new StackPane());
			gridpane.add(stackpanes.get(i), columnNumber, rowNumber);
			
			if (i == 19 || i == 39 || i == 59 || i == 79) {
				rowNumber++;
				columnNumber = -1;
			}
			columnNumber++;
			
			stackpanes.get(i).getChildren().add(new CustomCircle());
			stackpanes.get(i).getChildren().add(new Label("" + (i + 1)));
		}
		
		this.setCenter(gridpane);
		this.setAlignment(gridpane, Pos.CENTER);
	}
	
	// maakt titel
	private void makeTop() {
		text = new Label("Eindscherm scorebord");
		text.setFont(new Font("Arial", 30));
		this.setTop(text);
		this.setAlignment(text, Pos.CENTER);
	}
	
	// maakt speler/score lijst
	private void makeBottom() {
		Label player1 = new Label("a ------------------  22points");
		Label player2 = new Label("b ------------------  22points");
		Label player3 = new Label("c ------------------  22points");
		Label player4 = new Label("d ------------------  22points");
		System.out.println("hallo");
		
		VBox bottom = new VBox();
		bottom.getChildren().addAll(player1,player2,player3,player4);
		this.setBottom(bottom);
		this.setAlignment(bottom, Pos.CENTER);
	}
	
	// voegt speler toe
	public void addPlayer(int score, Color color) {
		
		if(score < 101 && score > 0) {
			int sizeHelp = stackpanes.get(score - 1).getChildren().size();
			int size = 30 - ((sizeHelp - 1) * 5);
			stackpanes.get(score - 1).getChildren().add(new PlayerCircle(color, size));
		}
	}
	
	
	
	private class CustomCircle extends Circle{
		private final String[] colors = { "GREENYELLOW", "YELLOW",
				"SADDLEBROWN", "DEEPSKYBLUE", "LAVENDER", "BISQUE", "FORESTGREEN" };
		
		private CustomCircle() {
			setRadius(35);
			int random = (int)(Math.random() * colors.length);
			setFill(Color.valueOf(colors[random]));
			
		}
	}
	
	private class PlayerCircle extends Circle {
		private PlayerCircle(Color playercolor, int radius) {
			setFill(playercolor);
	        setRadius(radius);
	        setStroke(Color.BLACK);
	        setStrokeWidth(0.5);
	       
		}
	}
	
	
	
}
