package controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import main.GUI;

import model.Card;

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

	// alle toolcards
	private ToolCardScreen toolCard1;
	private ToolCardScreen toolCard2;
	private ToolCardScreen toolCard3;
	private ToolCardScreen toolCard4;
	private ToolCardScreen toolCard5;
	private ToolCardScreen toolCard6;
	private ToolCardScreen toolCard7;
	private ToolCardScreen toolCard8;
	private ToolCardScreen toolCard9;
	private ToolCardScreen toolCard10;
	private ToolCardScreen toolCard11;
	private ToolCardScreen toolCard12;

	// alle objectiveCards
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

	private int TC1;
	private int TC2;
	private int TC3;

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

	private Card cardModel;
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

		cardModel = new Card(databaseController.getCardQueries(), GC.getGameModel());

		cardScreen = new GameCardsScreen(this, gui);

		toolCard1 = new ToolCardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard1-driepuntstang.png");
		toolCard2 = new ToolCardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard2-eglomiseBorstel.png");
		toolCard3 = new ToolCardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard3-folie-andrukker.png");
		toolCard4 = new ToolCardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard4-loodopenhaler.png");
		toolCard5 = new ToolCardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard5-rondsnijder.png");
		toolCard6 = new ToolCardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard6-fluxborstel.png");
		toolCard7 = new ToolCardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard7-loodhamer.png");
		toolCard8 = new ToolCardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard8-glasbreektang.png");
		toolCard9 = new ToolCardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard9-snijliniaal.png");
		toolCard10 = new ToolCardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard10-schuurblok.png");
		toolCard11 = new ToolCardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard11-fluxverwijderaar.png");
		toolCard12 = new ToolCardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard12-olieglassnijder.png");

		purpleObjectiveCard = new CardScreen("file:Sagrada-cards/sagrade-objectivecards/objectivecard-pers.paars.png");
		greenObjectiveCard = new CardScreen("file:Sagrada-cards/sagrade-objectivecards/objectivecard-pers.groen.png");
		blueObjectiveCard = new CardScreen("file:Sagrada-cards/sagrade-objectivecards/objectivecard-pers.blauw.png");
		yellowObjectiveCard = new CardScreen("file:Sagrada-cards/sagrade-objectivecards/objectivecard-pers.geel.png");
		redObjectiveCard = new CardScreen("file:Sagrada-cards/sagrade-objectivecards/objectivecard-pers.rood.png");

		objectiveCard1 = new CardScreen(
				"file:Sagrada-cards/sagrade-objectivecards/objectivecard-pub.tintvarieteit.png");
		objectiveCard2 = new CardScreen(
				"file:Sagrada-cards/sagrade-objectivecards/objectivecard-pub.halfdonkere-tinten.png");
		objectiveCard3 = new CardScreen(
				"file:Sagrada-cards/sagrade-objectivecards/objectivecard-pub.tintvarieteit-per-kolom.png");
		objectiveCard4 = new CardScreen(
				"file:Sagrada-cards/sagrade-objectivecards/objectivecard-pub.kleurvarieteit-per-kolom.png");
		objectiveCard5 = new CardScreen(
				"file:Sagrada-cards/sagrade-objectivecards/objectivecard-pub.donkere-tinten.png");
		objectiveCard6 = new CardScreen(
				"file:Sagrada-cards/sagrade-objectivecards/objectivecard-pub.kleurvarietteit.png");
		objectiveCard7 = new CardScreen(
				"file:Sagrada-cards/sagrade-objectivecards/objectivecard-pub.kleurvarieteit-per-rij.png");
		objectiveCard8 = new CardScreen(
				"file:Sagrada-cards/sagrade-objectivecards/objectivecard-pub.kleurdiagonalen.png");
		objectiveCard9 = new CardScreen(
				"file:Sagrada-cards/sagrade-objectivecards/objectivecard-pub.lichte-tinten.png");
		objectiveCard10 = new CardScreen(
				"file:Sagrada-cards/sagrade-objectivecards/objectivecard-pub.tintvarieteit-per-rij.png");

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

	public Card getCardModel() {
		return cardModel;
	}

	

	public GameCardsScreen showcards() {
		return cardScreen;
	}

	public void handleExit() {
		gameController.switchToGameScreen();

	}

	/**
	 * sets the bought toolcard in the database
	 */
	

	/**
	 *  gets the amount of FT on a toolcard and sets it directly
	 */
	public void SetAmountFTOnTC() {
		 
		
		TC1FTS1.getModel().setAmount( cardModel.getAmountFTOnToolCard(1, 1));

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
	 * handles when you buy GameTC1
	 */
	public void buyToolcard1(CardScreen cardscreen) {
		if (cardModel.getAmountFT() != 0 && cardModel.getGameModel().getPlayer(0).selectCurrentPlayer()&& !cardModel.checkboughtTCForRound() && gameController.getGameModel().checkIfDieAreThrown()) {
			
			if (cardscreen == toolCard1) {
				
				if (cardModel.checkboughtTC(1) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(2);
					buyTC1();
					
				}
				else if (!cardModel.checkboughtTC(1)) {
					cardModel.BuyTC(2);
					buyTC1();
					

				}
			}

			if (cardscreen == toolCard2) {

				if (cardModel.checkboughtTC(1) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(1);

					buyTC2();
					

				}

				else if (!cardModel.checkboughtTC(1)) {
					cardModel.BuyTC(1);

					buyTC2();
					
				}
			}

			if (cardscreen == toolCard3) {

				if (cardModel.checkboughtTC(1) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(1);
					buyTC3();
					
				}
				else if (!cardModel.checkboughtTC(1)) {
					cardModel.BuyTC(1);

					buyTC3();
					
				}
			}
			if (cardscreen == toolCard4) {

				if (cardModel.checkboughtTC(1) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(1);
					buyTC4();
					

				}
				else if (!cardModel.checkboughtTC(1)) {
					cardModel.BuyTC(1);

					buyTC4();
					

				}
			}
			if (cardscreen == toolCard5) {

				if (cardModel.checkboughtTC(1) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(1);
					buyTC5();
					

				}
				else if (!cardModel.checkboughtTC(1)) {
					cardModel.BuyTC(1);

					buyTC5();
					

				}

			}
			
			if (cardscreen == toolCard6) {
				
				
				if (cardModel.checkboughtTC(1) && cardModel.getAmountFT() > 1) {
					
					cardModel.BuyTCPric2(1);
					buyTC6();
					
					
				}
				if (!cardModel.checkboughtTC(1)) {
					cardModel.BuyTC(1);
					

					buyTC6();
					
				}

			}
			if (cardscreen == toolCard7 && gameController.getGameModel().isSecondTurn() ) {

				if (cardModel.checkboughtTC(1) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(1);
					buyTC7();
					

				}
				else if (!cardModel.checkboughtTC(1)) {
					cardModel.BuyTC(1);

					buyTC7();
					

				}
			}
			if (cardscreen == toolCard8 && !cardModel.getGameModel().isSecondTurn()) {

				if (cardModel.checkboughtTC(1) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(1);
					buyTC8();
					

				}
				else if (!cardModel.checkboughtTC(1)) {
					cardModel.BuyTC(1);

					buyTC8();
					
				}
			}

			if (cardscreen.equals(toolCard9)) {
				
					
				if (cardModel.checkboughtTC(1) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(1);
					buyTC9();
					
					
										
				}
				else if (!cardModel.checkboughtTC(1)) {
					cardModel.BuyTC(1);
						
					buyTC9();
					
				}
			}
			if (cardscreen == toolCard10) {

				if (cardModel.checkboughtTC(1) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(1);
					buyTC10();
					
				}
				else if (!cardModel.checkboughtTC(1)) {
					cardModel.BuyTC(1);

					buyTC10();
					

				}
			}
			if (cardscreen == toolCard11) {

				if (cardModel.checkboughtTC(1) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(1);
					buyTC11();
					
				}
				else if (!cardModel.checkboughtTC(1)) {
					cardModel.BuyTC(1);

					buyTC11();
					
				}

			}
			if (cardscreen == toolCard12) {

				if (cardModel.checkboughtTC(1) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(1);
					buyTC12();
					

				}
				else if (!cardModel.checkboughtTC(1)) {
					cardModel.BuyTC(1);

					buyTC12();
					

				}

			}
		}
	}
	/**
	 * handles when you buy GameTC2
	 */
	public void buyToolcard2(CardScreen cardscreen) {
		if (cardModel.getAmountFT() != 0 && cardModel.getGameModel().getPlayer(0).selectCurrentPlayer()&& !cardModel.checkboughtTCForRound()&& gameController.getGameModel().checkIfDieAreThrown()) {
			
			if (cardscreen == toolCard1) {

				if (cardModel.checkboughtTC(2) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(2);
					buyTC1();
					
				}
				else if (!cardModel.checkboughtTC(2)) {
					cardModel.BuyTC(2);
					buyTC1();
					

				}
			}

			if (cardscreen == toolCard2) {

				if (cardModel.checkboughtTC(2) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(2);

					buyTC2();
					
				}

			}
			else if (!cardModel.checkboughtTC(2)) {
				cardModel.BuyTC(2);

				buyTC2();
				
			}

			if (cardscreen == toolCard3) {

				if (cardModel.checkboughtTC(2) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(2);
					buyTC3();
					
				}
				else if (!cardModel.checkboughtTC(2)) {
					cardModel.BuyTC(2);

					buyTC3();
					
				}
			}
			if (cardscreen == toolCard4) {

				if (cardModel.checkboughtTC(2) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(2);
					buyTC4();
					

				}
				else if (!cardModel.checkboughtTC(2)) {
					cardModel.BuyTC(2);

					buyTC4();
					

				}
			}
			if (cardscreen == toolCard5) {

				if (cardModel.checkboughtTC(2) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(2);
					buyTC5();
					

				}
				else if (!cardModel.checkboughtTC(2)) {
					cardModel.BuyTC(2);

					buyTC5();
					

				}

			}
			System.out.println(cardModel.checkboughtTC(2));
			if (cardscreen == toolCard6) {

				if (cardModel.checkboughtTC(2) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(2);
					buyTC6();
					
					
				}
				else if (!cardModel.checkboughtTC(2)) {
					cardModel.BuyTC(2);
					
					buyTC6();
					
				}

			}
			if (cardscreen == toolCard7 && gameController.getGameModel().isSecondTurn()) {

				if (cardModel.checkboughtTC(2) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(2);
					buyTC7();
					

				}
				else if (!cardModel.checkboughtTC(2)) {
					cardModel.BuyTC(2);

					buyTC7();
					

				}
			}
			if (cardscreen == toolCard8 && !cardModel.getGameModel().isSecondTurn()) {

				if (cardModel.checkboughtTC(2) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(2);
					buyTC8();
					

				}
				else if (!cardModel.checkboughtTC(2)) {
					cardModel.BuyTC(2);

					buyTC8();
					
				}
			}
			if (cardscreen == toolCard9) {

				if (cardModel.checkboughtTC(2) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(2);
					buyTC9();
					
					
				}
				else if (!cardModel.checkboughtTC(2)) {
					cardModel.BuyTC(2);
					

					buyTC9();
					
				}
			}
			if (cardscreen == toolCard10) {

				if (cardModel.checkboughtTC(2) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(2);
					buyTC10();
					
				}
				else if (!cardModel.checkboughtTC(2)) {
					cardModel.BuyTC(2);

					buyTC10();
					

				}
			}
			if (cardscreen == toolCard11) {

				if (cardModel.checkboughtTC(2) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(2);
					buyTC11();
					
				}
				else if (!cardModel.checkboughtTC(2)) {
					cardModel.BuyTC(2);

					buyTC11();
					
				}

			}
			if (cardscreen == toolCard12) {

				if (cardModel.checkboughtTC(2) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(2);
					buyTC12();
					

				}
				else if (!cardModel.checkboughtTC(2)) {
					cardModel.BuyTC(2);

					buyTC12();
					

				}

			}

		}
	}
	/**
	 * handles when you buy GameTC3
	 */
	public void buyToolcard3(CardScreen cardscreen ) {
		if (cardModel.getAmountFT() != 0 && cardModel.getGameModel().getPlayer(0).selectCurrentPlayer() && !cardModel.checkboughtTCForRound()&& gameController.getGameModel().checkIfDieAreThrown()) {
			System.out.println(cardModel.checkboughtTC(1));
			if (cardscreen == toolCard1) {

				if (cardModel.checkboughtTC(3) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(3);
					buyTC1();
					
				}
				else if (!cardModel.checkboughtTC(3)) {
					cardModel.BuyTC(3);
					buyTC1();
					

				}
			}

			if (cardscreen == toolCard2) {

				if (cardModel.checkboughtTC(3) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(3);

					buyTC2();
					

				}

			}

			else if (!cardModel.checkboughtTC(3)) {
				cardModel.BuyTC(3);

				buyTC2();
				
			}

			if (cardscreen == toolCard3) {

				if (cardModel.checkboughtTC(3) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(3);
					buyTC3();
					
				}
				else if (!cardModel.checkboughtTC(3)) {
					cardModel.BuyTC(3);

					buyTC3();
					
				}
			}
			if (cardscreen == toolCard4) {

				if (cardModel.checkboughtTC(3) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(3);
					buyTC4();
					

				}
				else if (!cardModel.checkboughtTC(3)) {
					cardModel.BuyTC(3);

					buyTC4();
					

				}
			}
			if (cardscreen == toolCard5) {

				if (cardModel.checkboughtTC(3) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(3);
					buyTC5();
					
					

				}
				else if (!cardModel.checkboughtTC(3)) {
					cardModel.BuyTC(3);

					buyTC5();
					

				}

			}
			if (cardscreen == toolCard6) {
					
				if (cardModel.checkboughtTC(3) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(3);
					buyTC6();
					
					
				}
				else if (!cardModel.checkboughtTC(3)) {
					cardModel.BuyTC(3);
					
					buyTC6();
					
				}

			}
			if (cardscreen == toolCard7 && gameController.getGameModel().isSecondTurn() ) {

				if (cardModel.checkboughtTC(3) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(3);
					buyTC7();
					

				}
				else if (!cardModel.checkboughtTC(3)) {
					cardModel.BuyTC(3);

					buyTC7();
					

				}
			}
			if (cardscreen == toolCard8 && !cardModel.getGameModel().isSecondTurn()) {

				if (cardModel.checkboughtTC(3) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(3);
					buyTC8();
					

				}
				else	if (!cardModel.checkboughtTC(3)) {
					cardModel.BuyTC(3);
					
					buyTC8();

				}
			}
			if (cardscreen == toolCard9) {

				if (cardModel.checkboughtTC(3) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(3);
					buyTC9();
					System.out.println("TC");
					
				}
				else if (!cardModel.checkboughtTC(3)) {
					cardModel.BuyTC(3);

					buyTC9();
					
				}
			}
			if (cardscreen == toolCard10) {

				if (cardModel.checkboughtTC(3) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(3);
					buyTC10();
					
				}
				else if (!cardModel.checkboughtTC(3)) {
					cardModel.BuyTC(3);

					buyTC10();
					

				}
			}
			if (cardscreen == toolCard11) {

				if (cardModel.checkboughtTC(3) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(3);
					buyTC11();
					
				}
				else if (!cardModel.checkboughtTC(3)) {
					cardModel.BuyTC(3);
					
					buyTC11();

				}

			}
			if (cardscreen == toolCard12) {

				if (cardModel.checkboughtTC(3) && cardModel.getAmountFT() > 1) {
					cardModel.BuyTCPric2(3);
					buyTC12();
					

				}
				else if (!cardModel.checkboughtTC(3)) {
					cardModel.BuyTC(3);
					
					buyTC12();

				}

			}

		}

	}

	//the methods below handles when you buy that toolcard
	
	private void buyTC1() {
		
		gui.stopTimer();
		diceController.setDiceGlowBorder(1);
		

		gui.handleGoBackToGame();

	}

	private void buyTC2() {
		windowController.buyTC2();
		gui.handleGoBackToGame();
		

	}

	private void buyTC3() {
		windowController.buyTC3();
		gui.handleGoBackToGame();
		

	}

	private void buyTC4() {
		windowController.buyTC4();
		gui.handleGoBackToGame();
		
	}

	private void buyTC5() {

		gui.handleGoBackToGame();

		if (!cardModel.getGameModel().isRoundTrackEmpty()) {
			windowController.selectDiceOnWindow();
			gui.handleGoBackToGame();
		}

	}

	private void buyTC6() {
		gui.stopTimer();
		diceController.setDiceGlowBorder(6);
		gui.handleGoBackToGame();
	}

	private void buyTC7() {
		
		gui.handleGoBackToGame();
		cardModel.getGameModel().throwAgainWithSameDicesOnTable();
		

	}

	private void buyTC8() {
		
		gui.handleGoBackToGame();
		windowController.setExtraTurnTrue();
		
		// you cant go to homescreen

	}

	private void buyTC9() {
		
		windowController.buyTC9();
		gui.handleGoBackToGame();

	}

	private void buyTC10() {
		gui.stopTimer();
		diceController.setDiceGlowBorder(10);
		gui.handleGoBackToGame();
	}

	private void buyTC11() {
		gui.stopTimer();
		diceController.setDiceGlowBorder(11);
		gui.handleGoBackToGame();
	}

	private void buyTC12() {
		
		gui.handleGoBackToGame();
		windowController.buyTC12();

	}

	public void getDBcards() {
		checkToolcards(cardModel.getToolCard1(), cardModel.getToolCard2(), cardModel.getToolCard3());
		checkOBJCards(cardModel.getPRIVOBJCard(), cardModel.getPubOBJcard1(), cardModel.getPubOBJcard2(), cardModel.getPubOBJcard3());

	}

	/**
	 * checks which toolcards you have in the game and sets them on screen
 	 */
	public void checkToolcards(int TC1, int TC2, int TC3) {
		switch (TC1) {
		case 1:
			cardScreen.setTC1(toolCard1);
			toolCard1.addFTScreens(TC1FTS1, TC1FTS2, TC1FTS3, TC1FTS4);

			break;
		case 2:
			cardScreen.setTC1(toolCard2);
			toolCard2.addFTScreens(TC1FTS1, TC1FTS2, TC1FTS3, TC1FTS4);
			break;
		case 3:
			cardScreen.setTC1(toolCard3);
			toolCard3.addFTScreens(TC1FTS1, TC1FTS2, TC1FTS3, TC1FTS4);
			break;
		case 4:
			cardScreen.setTC1(toolCard4);
			toolCard4.addFTScreens(TC1FTS1, TC1FTS2, TC1FTS3, TC1FTS4);
			break;
		case 5:
			cardScreen.setTC1(toolCard5);
			toolCard5.addFTScreens(TC1FTS1, TC1FTS2, TC1FTS3, TC1FTS4);
			break;
		case 6:
			cardScreen.setTC1(toolCard6);
			toolCard6.addFTScreens(TC1FTS1, TC1FTS2, TC1FTS3, TC1FTS4);
			break;
		case 7:
			cardScreen.setTC1(toolCard7);
			toolCard7.addFTScreens(TC1FTS1, TC1FTS2, TC1FTS3, TC1FTS4);
			break;
		case 8:
			cardScreen.setTC1(toolCard8);
			toolCard8.addFTScreens(TC1FTS1, TC1FTS2, TC1FTS3, TC1FTS4);
			break;
		case 9:
			cardScreen.setTC1(toolCard9);
			toolCard9.addFTScreens(TC1FTS1, TC1FTS2, TC1FTS3, TC1FTS4);
			break;
		case 10:
			cardScreen.setTC1(toolCard10);
			toolCard10.addFTScreens(TC1FTS1, TC1FTS2, TC1FTS3, TC1FTS4);
			break;
		case 11:
			cardScreen.setTC1(toolCard11);
			toolCard11.addFTScreens(TC1FTS1, TC1FTS2, TC1FTS3, TC1FTS4);
			break;
		case 12:
			cardScreen.setTC1(toolCard12);
			toolCard12.addFTScreens(TC1FTS1, TC1FTS2, TC1FTS3, TC1FTS4);
			break;

		}

		switch (TC2) {
		case 1:
			cardScreen.setTC2(toolCard1);
			toolCard1.addFTScreens(TC2FTS1, TC2FTS2, TC2FTS3, TC2FTS4);
			break;
		case 2:
			cardScreen.setTC2(toolCard2);
			toolCard2.addFTScreens(TC2FTS1, TC2FTS2, TC2FTS3, TC2FTS4);
			break;
		case 3:
			cardScreen.setTC2(toolCard3);
			toolCard3.addFTScreens(TC2FTS1, TC2FTS2, TC2FTS3, TC2FTS4);
			break;
		case 4:
			cardScreen.setTC2(toolCard4);
			toolCard4.addFTScreens(TC2FTS1, TC2FTS2, TC2FTS3, TC2FTS4);
			break;
		case 5:
			cardScreen.setTC2(toolCard5);
			toolCard5.addFTScreens(TC2FTS1, TC2FTS2, TC2FTS3, TC2FTS4);
			break;
		case 6:
			cardScreen.setTC2(toolCard6);
			toolCard6.addFTScreens(TC2FTS1, TC2FTS2, TC2FTS3, TC2FTS4);
			break;
		case 7:
			cardScreen.setTC2(toolCard7);
			toolCard7.addFTScreens(TC2FTS1, TC2FTS2, TC2FTS3, TC2FTS4);
			break;
		case 8:
			cardScreen.setTC2(toolCard8);
			toolCard8.addFTScreens(TC2FTS1, TC2FTS2, TC2FTS3, TC2FTS4);
			break;
		case 9:
			cardScreen.setTC2(toolCard9);
			toolCard9.addFTScreens(TC2FTS1, TC2FTS2, TC2FTS3, TC2FTS4);
			break;
		case 10:
			cardScreen.setTC2(toolCard10);
			toolCard10.addFTScreens(TC2FTS1, TC2FTS2, TC2FTS3, TC2FTS4);
			break;
		case 11:
			cardScreen.setTC2(toolCard11);
			toolCard11.addFTScreens(TC2FTS1, TC2FTS2, TC2FTS3, TC2FTS4);
			break;
		case 12:
			cardScreen.setTC2(toolCard12);
			toolCard12.addFTScreens(TC2FTS1, TC2FTS2, TC2FTS3, TC2FTS4);
			break;

		}
		switch (TC3) {
		case 1:
			cardScreen.setTC3(toolCard1);
			toolCard1.addFTScreens(TC3FTS1, TC3FTS2, TC3FTS3, TC3FTS4);
			break;
		case 2:
			cardScreen.setTC3(toolCard2);
			toolCard2.addFTScreens(TC3FTS1, TC3FTS2, TC3FTS3, TC3FTS4);
			break;
		case 3:
			cardScreen.setTC3(toolCard3);
			toolCard3.addFTScreens(TC3FTS1, TC3FTS2, TC3FTS3, TC3FTS4);
			break;
		case 4:
			cardScreen.setTC3(toolCard4);
			toolCard4.addFTScreens(TC3FTS1, TC3FTS2, TC3FTS3, TC3FTS4);
			break;
		case 5:
			cardScreen.setTC3(toolCard5);
			toolCard5.addFTScreens(TC3FTS1, TC3FTS2, TC3FTS3, TC3FTS4);
			break;
		case 6:
			cardScreen.setTC3(toolCard6);
			toolCard6.addFTScreens(TC3FTS1, TC3FTS2, TC3FTS3, TC3FTS4);
			break;
		case 7:
			cardScreen.setTC3(toolCard7);
			toolCard7.addFTScreens(TC3FTS1, TC3FTS2, TC3FTS3, TC3FTS4);
			break;
		case 8:
			cardScreen.setTC3(toolCard8);
			toolCard8.addFTScreens(TC3FTS1, TC3FTS2, TC3FTS3, TC3FTS4);
			break;
		case 9:
			cardScreen.setTC3(toolCard9);
			toolCard9.addFTScreens(TC3FTS1, TC3FTS2, TC3FTS3, TC3FTS4);
			break;
		case 10:
			cardScreen.setTC3(toolCard10);
			toolCard10.addFTScreens(TC3FTS1, TC3FTS2, TC3FTS3, TC3FTS4);
			break;
		case 11:
			cardScreen.setTC3(toolCard11);
			toolCard11.addFTScreens(TC3FTS1, TC3FTS2, TC3FTS3, TC3FTS4);
			break;
		case 12:
			cardScreen.setTC3(toolCard12);
			toolCard12.addFTScreens(TC3FTS1, TC3FTS2, TC3FTS3, TC3FTS4);
			break;

		}

	}
	/**
	 * checks which objective cards you have in the game and sets them on screen
 	 */
	private void checkOBJCards(String privOBJC, int pubOBJ3, int pubOBJ2, int pubOBJ1) {

		switch (pubOBJ1) {
		case 1:
			cardScreen.setPUBOBJCard1(objectiveCard1);
			break;
		case 2:
			cardScreen.setPUBOBJCard1(objectiveCard2);
			break;
		case 3:
			cardScreen.setPUBOBJCard1(objectiveCard3);
			break;
		case 4:
			cardScreen.setPUBOBJCard1(objectiveCard4);
			break;
		case 5:
			cardScreen.setPUBOBJCard1(objectiveCard5);
			break;
		case 6:
			cardScreen.setPUBOBJCard1(objectiveCard6);
			break;
		case 7:
			cardScreen.setPUBOBJCard1(objectiveCard7);
			break;
		case 8:
			cardScreen.setPUBOBJCard1(objectiveCard8);
			break;
		case 9:
			cardScreen.setPUBOBJCard1(objectiveCard9);
			break;
		case 10:
			cardScreen.setPUBOBJCard1(objectiveCard10);

		}

		switch (pubOBJ2) {
		case 1:
			cardScreen.setPUBOBJCard2(objectiveCard1);
			break;
		case 2:
			cardScreen.setPUBOBJCard2(objectiveCard2);
			break;
		case 3:
			cardScreen.setPUBOBJCard2(objectiveCard3);
			break;
		case 4:
			cardScreen.setPUBOBJCard2(objectiveCard4);
			break;
		case 5:
			cardScreen.setPUBOBJCard2(objectiveCard5);
			break;
		case 6:
			cardScreen.setPUBOBJCard2(objectiveCard6);
			break;
		case 7:
			cardScreen.setPUBOBJCard2(objectiveCard7);
			break;
		case 8:
			cardScreen.setPUBOBJCard2(objectiveCard8);
			break;
		case 9:
			cardScreen.setPUBOBJCard2(objectiveCard9);
			break;
		case 10:
			cardScreen.setPUBOBJCard2(objectiveCard10);

		}
		switch (pubOBJ3) {
		case 1:
			cardScreen.setPUBOBJCard3(objectiveCard1);
			break;
		case 2:
			cardScreen.setPUBOBJCard3(objectiveCard2);
			break;
		case 3:
			cardScreen.setPUBOBJCard3(objectiveCard3);
			break;
		case 4:
			cardScreen.setPUBOBJCard3(objectiveCard4);
			break;
		case 5:
			cardScreen.setPUBOBJCard3(objectiveCard5);
			break;
		case 6:
			cardScreen.setPUBOBJCard3(objectiveCard6);
			break;
		case 7:
			cardScreen.setPUBOBJCard3(objectiveCard7);
			break;
		case 8:
			cardScreen.setPUBOBJCard3(objectiveCard8);
			break;
		case 9:
			cardScreen.setPUBOBJCard3(objectiveCard9);
			break;
		case 10:
			cardScreen.setPUBOBJCard3(objectiveCard10);

		}

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

	// check with every button click if
	// cardModel.getGameModel().getPlayer(0).selectCurrentPlayer(), main player is
	// currentplayer
	// favortokens not always -1 or -2 when clicked, only when main player is
	// qurrentplayer and when other if statements in methods are true
	// fix that when card has been bought you cant go to home screen, you can back
	// to home screen when you click ronde beëindigen.
	// with some cards you can cant go back untill there is a new round, for example
	// the card where you can do two turns in one.

}
