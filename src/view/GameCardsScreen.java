package view;

import controller.CardController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.GUI;

public class GameCardsScreen extends VBox {

    private CardController cardController;

    //drie van de twaalf random gekozen toolcards
    private ToolCardScreen gameTC1;
    private ToolCardScreen gameTC2;
    private ToolCardScreen gameTC3;

    //1 priv and 3 random gekozen obj cards
    private CardScreen privOBJCard;
    private CardScreen pubOBJCard1;
    private CardScreen pubOBJCard2;
    private CardScreen pubOBJCard3;
    private GUI gui;

    public GameCardsScreen(CardController CC, GUI gui) {
        super(new Pane());
        cardController = CC;
        this.gui = gui;
    }

    public void setTC1(ToolCardScreen toolcard) {
        gameTC1 = toolcard;
    }

    public void setTC2(ToolCardScreen toolcard) {
        gameTC2 = toolcard;
    }

    public void setTC3(ToolCardScreen toolcard) {
        gameTC3 = toolcard;
    }

    public void setPRIVOBJCard(CardScreen privateOjectiveCard) {
        privOBJCard = privateOjectiveCard;
    }

    public void setPUBOBJCard(CardScreen objectiveCard, int card) {
        switch (card) {
            case 1:
                pubOBJCard1 = objectiveCard;
                break;
            case 2:
                pubOBJCard2 = objectiveCard;
                break;
            case 3:
                pubOBJCard3 = objectiveCard;
                break;
        }
    }

    public void createView() {
        getChildren().clear();
        setBackground(new Background(new BackgroundFill(Color.SKYBLUE, null, null)));

        Button buyTC1 = new Button("Koop gereedschapskaart");
        Button buyTC2 = new Button("Koop gereedschapskaart");
        Button buyTC3 = new Button("Koop gereedschapskaart");

        buyTC1.setOnAction(e -> cardController.buyToolcard(gameTC1, 1));
        buyTC2.setOnAction(e -> cardController.buyToolcard(gameTC2, 2));
        buyTC3.setOnAction(e -> cardController.buyToolcard(gameTC3, 3));
        Button exit = new Button("Exit");
        exit.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
        exit.setPrefSize(1600, 50);
        exit.setOnAction(e -> gui.handleGoBackToGame());

        Label labelTC = new Label("Gereedschapskaarten");
        labelTC.setFont(new Font("Consolas", 24));
        labelTC.setPadding(new Insets(30, 0, 0, 610));

        Label labelOBC = new Label("Doelkaarten");
        labelOBC.setFont(new Font("Consolas", 24));
        labelOBC.setPadding(new Insets(25, 0, 0, 610));

        //creates and positions buttons
        HBox buttons = new HBox(150, buyTC1, buyTC2, buyTC3);
        buttons.setPadding(new Insets(15, 0, 0, 400));

        //creates and positions toolcards
        HBox toolCards = new HBox(30, gameTC1, gameTC2, gameTC3);
        toolCards.setPadding(new Insets(10, 0, 0, 350));

        //creates and positions objective cards
        HBox objectiveCards = new HBox(30, privOBJCard, pubOBJCard1, pubOBJCard2, pubOBJCard3);
        objectiveCards.setPadding(new Insets(10, 0, 30, 250));
        setCenterShape(true);

        getChildren().addAll(labelTC, toolCards, buttons, labelOBC, objectiveCards, exit);
    }
}






















