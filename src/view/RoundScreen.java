package view;
import java.util.ArrayList;

import controller.RoundScreenController;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class RoundScreen extends GridPane {
	private ArrayList <CustomStackPane> stackPanes;
	private RoundScreenController controller;
	
	public RoundScreen(RoundScreenController controller) {
		this.controller = controller;
		stackPanes = new ArrayList<CustomStackPane>();
		addRoundNumbers();
		makePane();
		
		Label round = new Label("RONDEBORD");
		round.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		round.setPadding(new Insets(5));
	
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(20));
		this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		this.add(round, 0, 0);
	}

	// lijst van stackpanes wordt gemaakt om dobbelstenen in te plaatsen
	private void makePane() {
		int counter =0;
		
		for(int row = 2; row <11; row ++) {
			for(int i = 0; i< 10; i++) {
				stackPanes.add(new CustomStackPane());
				this.add(stackPanes.get(counter), i, row);
				this.setMargin(stackPanes.get(counter), new Insets(10));
				counter++;
			}	
		}
	}
	
	//methode om board te clearen
	public void clearBoard() {
		for(int i = 0; i < stackPanes.size(); i++) {
			stackPanes.get(i).getChildren().clear();
		}
	}
	
	// methode om dice toe te voegen
	public void addDice(int round, int number, String color, int row,int diceID) {
		round--;
		Color diceColor = getColorTranslation(color);
		
		CustomStackPane node = (CustomStackPane) getNode(round, row);
		node.getChildren().add(new DiceScreen(number, diceColor, diceID, controller));
	}
	
	// geeft de node in de gridpane waar een dobbelsteen in moet komen
	private Node getNode(int column, int row) {
		Node stackpaneNode = null;
		ObservableList<Node> gridChildren = this.getChildren();
		
		for(Node node: gridChildren) {
			if(this.getColumnIndex(node) == column && this.getRowIndex(node) == row) {
				stackpaneNode = node;
				break;
			}
		}
		
		return stackpaneNode;
	}
	
	// verandert kleur uit database naar javakleur
	private Color getColorTranslation(String color){
		
		switch(color) {
		  case "blauw":
			 return Color.BLUE;
		  case "geel":
			 return Color.YELLOW;
		  case "rood":
			 return Color.RED;
		  case "paars":
			  return Color.PURPLE;
		  case "groen":
			  return Color.GREEN;
		  default:
		    return Color.BLACK;
		}
	}
	
	// voeg rondenummers toe
	private void addRoundNumbers() {
		Label [] labels = new Label[10];
		for(int i =0; i<labels.length; i++) {
			labels[i] = new Label("" + (i +1));
			this.add(labels[i], i, 1);
			this.setHalignment(this.getChildren().get(i), HPos.CENTER);
		}
	}
	

	private class CustomStackPane extends StackPane{
		 private CustomStackPane() {
			 int size = 60;
			 setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
			 setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			 setMinWidth(size);
			 setMaxWidth(size);
			 setMaxHeight(size);
			 setMinHeight(size);
			 
			 
			 
			 
		 }
	}
	

	
}
