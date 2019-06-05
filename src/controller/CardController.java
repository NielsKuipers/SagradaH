package controller;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import main.GUI;
import model.CardModel;
import model.FavorToken;
import view.CardScreen;
import view.FavorTokensScreen;
import view.GameCardsScreen;
import view.ToolCardScreen;

public class CardController extends Scene {
    private GameCardsScreen cardScreen;
    private GameController gameController;

    private WindowController windowController;
    private DiceController diceController;

    private ToolCardScreen[] toolCards = new ToolCardScreen[12];

    private CardScreen purpleObjectiveCard;
    private CardScreen greenObjectiveCard;
    private CardScreen blueObjectiveCard;
    private CardScreen redObjectiveCard;
    private CardScreen yellowObjectiveCard;
    private CardScreen objectiveCard1;
    private CardScreen objectiveCard2;
    private CardScreen objectiveCard3;
    private CardScreen objectiveCard4;
    private CardScreen objectiveCard5;
    private CardScreen objectiveCard6;
    private CardScreen objectiveCard7;
    private CardScreen objectiveCard8;
    private CardScreen objectiveCard9;
    private CardScreen objectiveCard10;

    private FavorTokensScreen TC1FTS1;
    private FavorTokensScreen TC1FTS2;
    private FavorTokensScreen TC1FTS3;
    private FavorTokensScreen TC1FTS4;

    private FavorTokensScreen TC2FTS1;
    private FavorTokensScreen TC2FTS2;
    private FavorTokensScreen TC2FTS3;
    private FavorTokensScreen TC2FTS4;

    private FavorTokensScreen TC3FTS1;
    private FavorTokensScreen TC3FTS2;
    private FavorTokensScreen TC3FTS3;
    private FavorTokensScreen TC3FTS4;

    private CardModel cardModel;
    private GUI gui;

    public CardController(WindowController cc, DiceController dc, GameController GC,
                          DatabaseController databaseController, GUI gui) {
        super(new Pane());
        this.gameController = GC;
        windowController = cc;
        diceController = dc;
        this.gui = gui;
        gameController.setCardController(this);
        diceController.setCardController(this);

        cardModel = new CardModel(databaseController.getCardQueries(), GC.getGameModel());

        cardScreen = new GameCardsScreen(this, gui);

        toolCards[0] = new ToolCardScreen(new Image(getClass().getResourceAsStream("/Sagrada-cards/sagrada-toolcards/toolcard1-driepuntstang.png")));
        toolCards[1] = new ToolCardScreen(new Image(getClass().getResourceAsStream("/Sagrada-cards/sagrada-toolcards/toolcard2-eglomiseBorstel.png")));
        toolCards[2] = new ToolCardScreen(new Image(getClass().getResourceAsStream("/Sagrada-cards/sagrada-toolcards/toolcard3-folie-andrukker.png")));
        toolCards[3] = new ToolCardScreen(new Image(getClass().getResourceAsStream("/Sagrada-cards/sagrada-toolcards/toolcard4-loodopenhaler.png")));
        toolCards[4] = new ToolCardScreen(new Image(getClass().getResourceAsStream("/Sagrada-cards/sagrada-toolcards/toolcard5-rondsnijder.png")));
        toolCards[5] = new ToolCardScreen(new Image(getClass().getResourceAsStream("/Sagrada-cards/sagrada-toolcards/toolcard6-fluxborstel.png")));
        toolCards[6] = new ToolCardScreen(new Image(getClass().getResourceAsStream("/Sagrada-cards/sagrada-toolcards/toolcard7-loodhamer.png")));
        toolCards[7] = new ToolCardScreen(new Image(getClass().getResourceAsStream("/Sagrada-cards/sagrada-toolcards/toolcard8-glasbreektang.png")));
        toolCards[8] = new ToolCardScreen(new Image(getClass().getResourceAsStream("/Sagrada-cards/sagrada-toolcards/toolcard9-snijliniaal.png")));
        toolCards[9] = new ToolCardScreen(new Image(getClass().getResourceAsStream("/Sagrada-cards/sagrada-toolcards/toolcard10-schuurblok.png")));
        toolCards[10] = new ToolCardScreen(new Image(getClass().getResourceAsStream("/Sagrada-cards/sagrada-toolcards/toolcard11-fluxverwijderaar.png")));
        toolCards[11] = new ToolCardScreen(new Image(getClass().getResourceAsStream("/Sagrada-cards/sagrada-toolcards/toolcard12-olieglassnijder.png")));

        purpleObjectiveCard = new CardScreen(new Image(getClass().getResourceAsStream("/Sagrada-cards/sagrade-objectivecards/objectivecard-pers.paars.png")));
        greenObjectiveCard = new CardScreen(new Image(getClass().getResourceAsStream("/Sagrada-cards/sagrade-objectivecards/objectivecard-pers.groen.png")));
        blueObjectiveCard = new CardScreen(new Image(getClass().getResourceAsStream("/Sagrada-cards/sagrade-objectivecards/objectivecard-pers.blauw.png")));
        yellowObjectiveCard = new CardScreen(new Image(getClass().getResourceAsStream("/Sagrada-cards/sagrade-objectivecards/objectivecard-pers.geel.png")));
        redObjectiveCard = new CardScreen(new Image(getClass().getResourceAsStream("/Sagrada-cards/sagrade-objectivecards/objectivecard-pers.rood.png")));

        objectiveCard1 = new CardScreen(new Image(getClass().getResourceAsStream(
                "/Sagrada-cards/sagrade-objectivecards/objectivecard-pub.tintvarieteit.png")));
        objectiveCard2 = new CardScreen(new Image(getClass().getResourceAsStream(
                "/Sagrada-cards/sagrade-objectivecards/objectivecard-pub.halfdonkere-tinten.png")));
        objectiveCard3 = new CardScreen(new Image(getClass().getResourceAsStream(
                "/Sagrada-cards/sagrade-objectivecards/objectivecard-pub.tintvarieteit-per-kolom.png")));
        objectiveCard4 = new CardScreen(new Image(getClass().getResourceAsStream(
                "/Sagrada-cards/sagrade-objectivecards/objectivecard-pub.kleurvarieteit-per-kolom.png")));
        objectiveCard5 = new CardScreen(new Image(getClass().getResourceAsStream(
                "/Sagrada-cards/sagrade-objectivecards/objectivecard-pub.donkere-tinten.png")));
        objectiveCard6 = new CardScreen(new Image(getClass().getResourceAsStream(
                "/Sagrada-cards/sagrade-objectivecards/objectivecard-pub.kleurvarietteit.png")));
        objectiveCard7 = new CardScreen(new Image(getClass().getResourceAsStream(
                "/Sagrada-cards/sagrade-objectivecards/objectivecard-pub.kleurvarieteit-per-rij.png")));
        objectiveCard8 = new CardScreen(new Image(getClass().getResourceAsStream(
                "/Sagrada-cards/sagrade-objectivecards/objectivecard-pub.kleurdiagonalen.png")));
        objectiveCard9 = new CardScreen(new Image(getClass().getResourceAsStream(
                "/Sagrada-cards/sagrade-objectivecards/objectivecard-pub.lichte-tinten.png")));
        objectiveCard10 = new CardScreen(new Image(getClass().getResourceAsStream(
                "/Sagrada-cards/sagrade-objectivecards/objectivecard-pub.tintvarieteit-per-rij.png")));

        TC1FTS1 = new FavorTokensScreen(new FavorToken(0, Color.BLUE));
        TC1FTS2 = new FavorTokensScreen(new FavorToken(0, Color.RED));
        TC1FTS3 = new FavorTokensScreen(new FavorToken(0, Color.GREEN));
        TC1FTS4 = new FavorTokensScreen(new FavorToken(0, Color.YELLOW));

        TC2FTS1 = new FavorTokensScreen(new FavorToken(0, Color.BLUE));
        TC2FTS2 = new FavorTokensScreen(new FavorToken(0, Color.RED));
        TC2FTS3 = new FavorTokensScreen(new FavorToken(0, Color.GREEN));
        TC2FTS4 = new FavorTokensScreen(new FavorToken(0, Color.YELLOW));

        TC3FTS1 = new FavorTokensScreen(new FavorToken(0, Color.BLUE));
        TC3FTS2 = new FavorTokensScreen(new FavorToken(0, Color.RED));
        TC3FTS3 = new FavorTokensScreen(new FavorToken(0, Color.GREEN));
        TC3FTS4 = new FavorTokensScreen(new FavorToken(0, Color.YELLOW));

    }

    CardModel getCardModel() {
        return cardModel;
    }

    public GameCardsScreen showcards() {
        return cardScreen;
    }

    /**
     * gets the amount of favortokens on a toolcard and sets it directly
     */
    void SetAmountFTOnTC() {
        TC1FTS1.getModel().setAmount(cardModel.getAmountFTOnToolCard(1, 1));

        TC1FTS2.getModel().setAmount(cardModel.getAmountFTOnToolCard(1, 2));

        TC1FTS3.getModel().setAmount(cardModel.getAmountFTOnToolCard(1, 3));

        TC1FTS4.getModel().setAmount(cardModel.getAmountFTOnToolCard(1, 4));

        TC2FTS1.getModel().setAmount(cardModel.getAmountFTOnToolCard(2, 1));

        TC2FTS2.getModel().setAmount(cardModel.getAmountFTOnToolCard(2, 2));

        TC2FTS3.getModel().setAmount(cardModel.getAmountFTOnToolCard(2, 3));

        TC2FTS4.getModel().setAmount(cardModel.getAmountFTOnToolCard(2, 4));

        TC3FTS1.getModel().setAmount(cardModel.getAmountFTOnToolCard(3, 1));

        TC3FTS2.getModel().setAmount(cardModel.getAmountFTOnToolCard(3, 2));

        TC3FTS3.getModel().setAmount(cardModel.getAmountFTOnToolCard(3, 3));

        TC3FTS4.getModel().setAmount(cardModel.getAmountFTOnToolCard(3, 4));
    }

    /**
     * handles buying and using correct toolcard and updates price
     *
     * @param cardscreen   = card to buy/use
     * @param cardPosition = position of card in game 1 to 3
     */
    public void buyToolcard(CardScreen cardscreen, int cardPosition) {

        if (cardModel.getAmountFT() != 0 && cardModel.getGameModel().getPlayer(0).selectCurrentPlayer() &&
                !cardModel.checkboughtTCForRound() && gameController.getGameModel().checkIfDieAreThrown()) {

            if (cardscreen == toolCards[6] && !cardModel.getGameModel().isSecondTurn()) {
                return;
            }

            for (int x = 0; x < toolCards.length; x++) {
                if (cardscreen == toolCards[x]) {

                    if (cardModel.checkboughtTC(cardPosition) && cardModel.getAmountFT() > 1) {
                        cardModel.BuyTCPric2(cardPosition);
                        useCard(x + 1);
                    } else {
                        cardModel.BuyTC(cardPosition);
                        useCard(x + 1);
                    }
                }
            }

        }
    }


    /**
     * select correct toolcard and handle toolcard actions
     *
     * @param cardNumber = number of the toolcard
     */
    private void useCard(int cardNumber) {

        switch (cardNumber) {

            case 1:
                gui.stopTimer();
                diceController.setDiceGlowBorder(1);
                gui.handleGoBackToGame();
                break;
            case 2:
                windowController.buyTC2();
                gui.handleGoBackToGame();
                break;
            case 3:
                windowController.buyTC3();
                gui.handleGoBackToGame();
                break;
            case 4:
                gui.stopTimer();
                windowController.buyTC4();
                gui.handleGoBackToGame();
                break;
            case 5:
                gui.handleGoBackToGame();
                if (!cardModel.getGameModel().isRoundTrackEmpty()) {
                    windowController.selectDiceOnWindow();
                    gui.handleGoBackToGame();
                }
                break;
            case 6:
                gui.stopTimer();
                diceController.setDiceGlowBorder(6);
                gui.handleGoBackToGame();
                break;
            case 7:
                gui.handleGoBackToGame();
                cardModel.getGameModel().throwAgainWithSameDicesOnTable();
                break;
            case 8:
                gui.handleGoBackToGame();
                windowController.setExtraTurnTrue();
                break;
            case 9:
                windowController.buyTC9();
                gui.handleGoBackToGame();
                break;
            case 10:
                gui.stopTimer();
                diceController.setDiceGlowBorder(10);
                gui.handleGoBackToGame();
                break;
            case 11:
                gui.stopTimer();
                diceController.setDiceGlowBorder(11);
                gui.handleGoBackToGame();
                break;
            case 12:
                gui.handleGoBackToGame();
                windowController.buyTC12();
                break;
        }
    }

    /**
     * returns card based on what's on the gameboard
     *
     * @param card = correct card in game 1 to 3
     * @return correct toolcardscreen based on what's in the game
     */
    private ToolCardScreen getCorrectCard(int card) {
        switch (card) {
            case 1:
                return toolCards[0];
            case 2:
                return toolCards[1];
            case 3:
                return toolCards[2];
            case 4:
                return toolCards[3];
            case 5:
                return toolCards[4];
            case 6:
                return toolCards[5];
            case 7:
                return toolCards[6];
            case 8:
                return toolCards[7];
            case 9:
                return toolCards[8];
            case 10:
                return toolCards[9];
            case 11:
                return toolCards[10];
            case 12:
                return toolCards[11];

            default:
                return null;
        }
    }

    void getDBcards() {
        checkToolcards(cardModel.getToolCard1(), cardModel.getToolCard2(), cardModel.getToolCard3());
        checkOBJCards(cardModel.getPRIVOBJCard(), cardModel.getPubOBJcard1(), cardModel.getPubOBJcard2(), cardModel.getPubOBJcard3());
    }

    /**
     * sets the correct card in game and sets favortoken indicators
     *
     * @param TC1 = toolcard 1
     * @param TC2 = toolcard 2
     * @param TC3 = toolcard 3
     */
    private void checkToolcards(int TC1, int TC2, int TC3) {
        cardScreen.setTC1(getCorrectCard(TC1));
        getCorrectCard(TC1).addFTScreens(TC1FTS1, TC1FTS2, TC1FTS3, TC1FTS4);

        cardScreen.setTC2(getCorrectCard(TC2));
        getCorrectCard(TC2).addFTScreens(TC2FTS1, TC2FTS2, TC2FTS3, TC2FTS4);

        cardScreen.setTC3(getCorrectCard(TC3));
        getCorrectCard(TC3).addFTScreens(TC3FTS1, TC3FTS2, TC3FTS3, TC3FTS4);
    }

    /**
     * sets objective card corresponding to given cardID and position
     *
     * @param card    = objective card number
     * @param cardPos = position of card in database/game
     */
    private void setCorrectOBJCard(int card, int cardPos) {
        switch (card) {
            case 1:
                cardScreen.setPUBOBJCard(objectiveCard1, cardPos);
                break;
            case 2:
                cardScreen.setPUBOBJCard(objectiveCard2, cardPos);
                break;
            case 3:
                cardScreen.setPUBOBJCard(objectiveCard3, cardPos);
                break;
            case 4:
                cardScreen.setPUBOBJCard(objectiveCard4, cardPos);
                break;
            case 5:
                cardScreen.setPUBOBJCard(objectiveCard5, cardPos);
                break;
            case 6:
                cardScreen.setPUBOBJCard(objectiveCard6, cardPos);
                break;
            case 7:
                cardScreen.setPUBOBJCard(objectiveCard7, cardPos);
                break;
            case 8:
                cardScreen.setPUBOBJCard(objectiveCard8, cardPos);
                break;
            case 9:
                cardScreen.setPUBOBJCard(objectiveCard9, cardPos);
                break;
            case 10:
                cardScreen.setPUBOBJCard(objectiveCard10, cardPos);
        }
    }

    /**
     * sets correct objective cards
     *
     * @param privOBJC = color of private objective card die
     * @param pubOBJ1  = public objective card on position 1
     * @param pubOBJ2  = public objective card on position 2
     * @param pubOBJ3  = public objective card on position 3
     */
    private void checkOBJCards(String privOBJC, int pubOBJ1, int pubOBJ2, int pubOBJ3) {
        setCorrectOBJCard(pubOBJ1, 1);
        setCorrectOBJCard(pubOBJ2, 2);
        setCorrectOBJCard(pubOBJ3, 3);

        switch (privOBJC) {
            case "blauw":
                cardScreen.setPRIVOBJCard(blueObjectiveCard);
                break;
            case "paars":
                cardScreen.setPRIVOBJCard(purpleObjectiveCard);
                break;
            case "geel":
                cardScreen.setPRIVOBJCard(yellowObjectiveCard);
                break;
            case "groen":
                cardScreen.setPRIVOBJCard(greenObjectiveCard);
                break;
            case "rood":
                cardScreen.setPRIVOBJCard(redObjectiveCard);
                break;
        }
        cardScreen.createView();
    }
}
