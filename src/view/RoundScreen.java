package view;

import controller.RoundScreenController;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import main.GUI;

import java.util.ArrayList;

import static view.EndScreen.getColorTranslation;

public class RoundScreen extends GridPane {
	private ArrayList <CustomStackPane> stackPanes;
	private RoundScreenController controller;
	private GUI gui;
	
	public RoundScreen(RoundScreenController controller, GUI gui) {
		this.controller = controller;
		this.gui = gui;
		stackPanes = new ArrayList<>();
		addRoundNumbers();
		makePane();
		
		Label round = new Label("RONDEBORD");
		round.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		round.setPadding(new Insets(5));
		
		Button goBackToGame = new Button("Terug naar spel");
		goBackToGame.setOnMouseClicked(e -> handleGoBackToGame());
		
		VBox goBacktoGameBox = new VBox(goBackToGame);
		this.add(goBacktoGameBox, 9, 0, 11, 1);
		goBacktoGameBox.setPadding(new Insets(0, 20, 0, 0));
	
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(20));
		this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		this.add(round, 0, 0);
	}

	
	/**
	 * create stackpane list to add dice into
	 */
	private void makePane() {
		int counter =0;
		
		for(int row = 2; row <11; row ++) {
			for(int i = 0; i< 10; i++) {
				stackPanes.add(new CustomStackPane());
				this.add(stackPanes.get(counter), i, row);
				setMargin(stackPanes.get(counter), new Insets(10));
				counter++;
			}	
		}
	}
	
	// clears board
	public void clearBoard() {
		for (CustomStackPane stackPane : stackPanes) {
			stackPane.getChildren().clear();
		}
	}
	
	
	/** adds dice to roundscreen
	 * @param round
	 * @param number
	 * @param color
	 * @param row
	 * @param diceID
	 */
	public void addDice(int round, int number, String color, int row,int diceID) {
		round--;
		Color diceColor = getColorTranslation(color);
		
		CustomStackPane node = (CustomStackPane) getNode(round, row);
		node.getChildren().add(new DiceScreen(number, diceColor, diceID, controller));
	}
	
	
	/**returns node in gridpane where a dice will be placed 
	 * @param column
	 * @param row
	 * @return stackpane node
	 */
	private Node getNode(int column, int row) {
		Node stackpaneNode = null;
		ObservableList<Node> gridChildren = this.getChildren();
		
		for(Node node: gridChildren) {
			if(getColumnIndex(node) == column && getRowIndex(node) == row) {
				stackpaneNode = node;
				break;
			}
		}
		
		return stackpaneNode;
	}
	
	// add roundnumbers
	private void addRoundNumbers() {
		Label [] labels = new Label[10];
		for(int i =0; i<labels.length; i++) {
			labels[i] = new Label("" + (i +1));
			this.add(labels[i], i, 1);
			setHalignment(this.getChildren().get(i), HPos.CENTER);
		}
	}
	

	public class CustomStackPane extends StackPane{
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
	

	private void handleGoBackToGame() {
		gui.handleGoBackToGame();
	}
	
}
