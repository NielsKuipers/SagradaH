package view;

import controller.CardController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameCardsScreen  extends VBox{
	
private CardController cardController;

    //drie van de twaalf random gekozen toolcards
    private CardScreen gameTC1;
    private CardScreen gameTC2;
    private CardScreen gameTC3;
    
    //1 priv and 3 random gekozen obj cards
    private CardScreen privOBJCard;
    private CardScreen pubOBJCard1;
    private CardScreen pubOBJCard2;
    private CardScreen pubOBJCard3;

    private Button exit;
    
    private Label labelTC;
    private Label labelOBC;

    private HBox toolCards;
    private HBox objectiveCards;
    private HBox buttons;
    private HBox prices;
    private Label priceTC1= new Label("Price = 1");
    private Label priceTC2= new Label("Price = 1");
    private Label priceTC3= new Label("Price = 1");

    public GameCardsScreen(CardController CC) {
        super(new Pane());
        cardController = CC;
    }
    
    public void setTC1(CardScreen toolcard) {
        gameTC1 = toolcard;
    }
    
    public void setTC2(CardScreen toolcard) {
        gameTC2 = toolcard;
    }
    
    public void setTC3(CardScreen toolcard) {
        gameTC3 = toolcard;
    }
    
    public void setPRIVOBJCard(CardScreen privateOjectiveCard) {
        privOBJCard = privateOjectiveCard;
    }
    
    public void setPUBOBJCard1(CardScreen objectiveCard) {
        pubOBJCard1 = objectiveCard;
    }
    
    public void setPUBOBJCard2(CardScreen objectiveCard) {
        pubOBJCard2 = objectiveCard;
    }
    
    public void setPUBOBJCard3(CardScreen objectiveCard) {
        pubOBJCard3 = objectiveCard;
    }

    
    public void createView() {
        
        setBackground(new Background(new BackgroundFill(Color.SKYBLUE,null,null)));

        Button buyTC1 = new Button("buy toolcard");
        Button buyTC2 = new Button("buy toolcard");
        Button buyTC3 = new Button("buy toolcard");
    
    
    
    
    buyTC1.setOnAction(e -> cardController.buyTC1(gameTC1));
    buyTC2.setOnAction(e -> cardController.buyTC2(gameTC2));
    buyTC3.setOnAction(e -> cardController.buyTC3(gameTC3));
    exit = new Button("Exit");
    exit.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));
    exit.setPrefSize(1600, 50);
    exit.setOnAction(e -> cardController.handleExit());
    
    
    labelTC = new Label("Toolcards");
    labelTC.setFont(new Font("Consolas", 24));
    labelTC.setPadding(new Insets(30,0,0,610));
    
    labelOBC = new Label("Objective Cards");
    labelOBC.setFont(new Font("Consolas", 24));
    labelOBC.setPadding(new Insets(25,0,0,610));
    
    
    //creates and positions buttons
    buttons = new HBox(150, buyTC1, buyTC2, buyTC3);
    buttons.setPadding(new Insets(5,0,0,400));
    
    prices = new HBox(200,priceTC1,priceTC2,priceTC3);
    prices.setPadding(new Insets(15,0,0,400));
    //creates and positions toolcards
    toolCards = new HBox(30,gameTC1,gameTC2,gameTC3);
    toolCards.setPadding(new Insets(10,0,0,350));

    //creates and positions objective cards
    objectiveCards = new HBox(30,privOBJCard,pubOBJCard1,pubOBJCard2,pubOBJCard3);
    objectiveCards.setPadding(new Insets(10,0,30,250));
    setCenterShape(true);
    getChildren().addAll(labelTC,toolCards,prices,buttons,labelOBC,objectiveCards,exit);
    
}

public void BoughtTC1() {
    priceTC1.setText("Price = 2");
    getChildren().clear();
    getChildren().addAll(labelTC,toolCards,prices,buttons,labelOBC,objectiveCards,exit);
    }
public void BoughtTC2() {
    priceTC2.setText("Price = 2");
    getChildren().clear();
    getChildren().addAll(labelTC,toolCards,prices,buttons,labelOBC,objectiveCards,exit);
}


public void BoughtTC3() {
	  priceTC3.setText("Price = 2");
	    getChildren().clear();
	    getChildren().addAll(labelTC,toolCards,prices,buttons,labelOBC,objectiveCards,exit);
	
}

}






















