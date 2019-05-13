package view;
import java.util.ArrayList;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
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
	private ArrayList <StackPane> stackPanes;
	
	public RoundScreen() {
		stackPanes = new ArrayList<StackPane>();
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
		
		for(int i = 0; i< 10; i++) {
			stackPanes.add(new StackPane());
			stackPanes.get(i).getChildren().add(new CustomRect());
			this.add(stackPanes.get(i), i, 2);
			this.setMargin(stackPanes.get(i), new Insets(10));
		}
	}
	
	
	// methode om dice toe te voegen, moet nog verandert worden.
	public void addDice(int round, int number, String color) {
		
		Color diceColor = getColorTranslation(color);
		round --;
		
		if(round < stackPanes.size()) {
			if(stackPanes.get(round).getChildren().size() == 1) {
				stackPanes.get(round).getChildren().add(new Dice(number, diceColor));
			}else {
				System.out.println("er zit hier al een steen!!");
			}
		}else {
			System.out.println("geen steen ruimte hier!!");
		}
	}
	
	// methode om dice te verwijderen
	public void removeDice(int round) {
		if(round < stackPanes.size()) {
			if(stackPanes.get(round).getChildren().size() > 1) {
				stackPanes.get(round).getChildren().remove(1);
			}else {
				System.out.println("geen dobbelsteen hier!!");
			}
		}else {
			System.out.println("geen steen ruimte hier!!");
		}
	}
	
	// verandert kleur uit database naar javafxkleur
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
	

	private class CustomRect extends Rectangle{
		 private CustomRect() {
			 setFill(Color.WHITE);
			 setStroke(Color.BLACK);
			 setWidth(80);
			 setHeight(80);
		 }
	}
	
	private class Dice extends StackPane{
		
		private Dice (int number, Color color) {
			this.setBackground(new Background(new BackgroundFill(color, null, null)));
			this.setPrefSize(60, 60);
			setPadding(new Insets(10));
			this.setMargin(this, new Insets(10));
			
			checkNumber(number);
		}
		
		public void checkNumber(int value) {
			switch (value) {
			case 1:
				Circle cir = new Circle(4, Color.BLACK);
				setAlignment(cir, Pos.CENTER);
				getChildren().add(cir);
				break;
			case 2:
				Circle cir2 = new Circle(4, Color.BLACK);
				setAlignment(cir2, Pos.TOP_LEFT);
				
				Circle cir3 = new Circle(4, Color.BLACK);
				setAlignment(cir3, Pos.BOTTOM_RIGHT);
				getChildren().addAll(cir2, cir3);
				break;
			case 3:
				Circle cir4 = new Circle(4, Color.BLACK);
				setAlignment(cir4, Pos.TOP_RIGHT);
				
				Circle cir5 = new Circle(4, Color.BLACK);
				setAlignment(cir5, Pos.CENTER);
				
				Circle cir6 = new Circle(4, Color.BLACK);
				setAlignment(cir6, Pos.BOTTOM_LEFT);
				
				getChildren().addAll(cir4, cir5, cir6);
				
				break;
			case 4:
				Circle cir7 = new Circle(4, Color.BLACK);
				setAlignment(cir7, Pos.BOTTOM_RIGHT);
				
				Circle cir8 = new Circle(4, Color.BLACK);
				setAlignment(cir8, Pos.BOTTOM_LEFT);
				
				Circle cir9 = new Circle(4, Color.BLACK);
				setAlignment(cir9, Pos.TOP_RIGHT);
				
				Circle cir10 = new Circle(4, Color.BLACK);
				setAlignment(cir10, Pos.TOP_LEFT);
				
				getChildren().addAll(cir7, cir8, cir9, cir10);
				break;
			case 5:
				Circle cir11 = new Circle(4, Color.BLACK);
				setAlignment(cir11, Pos.TOP_LEFT);
				
				Circle cir12 = new Circle(4, Color.BLACK);
				setAlignment(cir12, Pos.TOP_RIGHT);
				
				Circle cir13 = new Circle(4, Color.BLACK);
				setAlignment(cir13, Pos.BOTTOM_LEFT);
				
				Circle cir14 = new Circle(4, Color.BLACK);
				setAlignment(cir14, Pos.BOTTOM_RIGHT);
				
				Circle cir15 = new Circle(4, Color.BLACK);
				setAlignment(cir15, Pos.CENTER);
				
				getChildren().addAll(cir11, cir12, cir13, cir14, cir15);
				break;
			case 6:
				Circle cir16 = new Circle(4, Color.BLACK);
				setAlignment(cir16, Pos.BOTTOM_RIGHT);
				
				Circle cir17 = new Circle(4, Color.BLACK);
				setAlignment(cir17, Pos.CENTER_RIGHT);
				
				Circle cir18 = new Circle(4, Color.BLACK);
				setAlignment(cir18, Pos.TOP_RIGHT);
				
				Circle cir19 = new Circle(4, Color.BLACK);
				setAlignment(cir19, Pos.TOP_LEFT);
				
				Circle cir20 = new Circle(4, Color.BLACK);
				setAlignment(cir20, Pos.CENTER_LEFT);
				
				Circle cir21 = new Circle(4, Color.BLACK);
				setAlignment(cir21, Pos.BOTTOM_LEFT);
				
				getChildren().addAll(cir16, cir17, cir18, cir19, cir20, cir21);
				break;

			default:
				break;
			}
		}
	}
	
}
