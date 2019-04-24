package controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import model.CardModel;

import view.CardScreen;
import view.GameCardsScreen;
import view.GameCardsScreen;

public class CardController extends Scene {
	private GameCardsScreen cardScreen;
	private GameController gameController;
	private CardModel cardModel;
	
	
	
	//alle toolcards
		private CardScreen toolCard1;
		private CardScreen toolCard2;
		private CardScreen toolCard3;
		private CardScreen toolCard4;
		private CardScreen toolCard5;
		private CardScreen toolCard6;
		private CardScreen toolCard7;
		private CardScreen toolCard8;
		private CardScreen toolCard9;
		private CardScreen toolCard10;
		private CardScreen toolCard11;
		private CardScreen toolCard12;
		
		//alle objectiveCards
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
	
		int TC1;
		int TC2;
		int TC3;
		
		int privOBJ;
		int pubOBJ1;
		int pubOBJ2;
		int pubOBJ3;
		
		
	public CardController(GameController gc) {
		super(new Pane());
	
		gameController = gc;
		cardScreen = new GameCardsScreen(this);
		cardModel = new CardModel();
		
		
		toolCard1 = new CardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard1-driepuntstang.png");
		toolCard2 = new CardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard2-eglomiseBorstel.png");
		toolCard3 = new CardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard3-folie-andrukker.png");
		toolCard4 = new CardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard4-loodopenhaler.png");
		toolCard5 = new CardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard5-rondsnijder.png");
		toolCard6 = new CardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard6-fluxborstel.png");
		toolCard7 = new CardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard7-loodhamer.png");
		toolCard8 = new CardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard8-glasbreektang.png");
		toolCard9 = new CardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard9-snijliniaal.png");
		toolCard10 = new CardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard10-schuurblok.png");
		toolCard11 = new CardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard11-fluxverwijderaar.png");
		toolCard12= new CardScreen("file:Sagrada-cards/sagrada-toolcards/toolcard12-olieglassnijder.png");
		
		
		purpleObjectiveCard = new CardScreen("file:Sagrada-cards/sagrade-objectivecards/objectivecard-pers.paars.png");
		greenObjectiveCard = new CardScreen("file:Sagrada-cards/sagrade-objectivecards/objectivecard-pers.groen.png");
		blueObjectiveCard = new CardScreen("file:Sagrada-cards/sagrade-objectivecards/objectivecard-pers.blauw.png");
		yellowObjectiveCard = new CardScreen("file:Sagrada-cards/sagrade-objectivecards/objectivecard-pers.geel.png");
		redObjectiveCard = new CardScreen("file:Sagrada-cards/sagrade-objectivecards/objectivecard-pers.rood.png");
		
		objectiveCard1 = new CardScreen("file:Sagrada-cards/sagrade-objectivecards/objectivecard-pub.donkere-tinten.png");
		objectiveCard2 = new CardScreen("file:Sagrada-cards/sagrade-objectivecards/objectivecard-pub.halfdonkere-tinten.png");
		objectiveCard3 = new CardScreen("file:Sagrada-cards/sagrade-objectivecards/objectivecard-pub.kleurdiagonalen.png");
		objectiveCard4 = new CardScreen("file:Sagrada-cards/sagrade-objectivecards/objectivecard-pub.kleurvarieteit-per-kolom.png");
		objectiveCard5 = new CardScreen("file:Sagrada-cards/sagrade-objectivecards/objectivecard-pub.kleurvarieteit-per-rij.png");
		objectiveCard6 = new CardScreen("file:Sagrada-cards/sagrade-objectivecards/objectivecard-pub.kleurvarietteit.png");
		objectiveCard7 = new CardScreen("file:Sagrada-cards/sagrade-objectivecards/objectivecard-pub.lichte-tinten.png");
		objectiveCard8 = new CardScreen("file:Sagrada-cards/sagrade-objectivecards/objectivecard-pub.tintvarieteit-per-kolom.png");
		objectiveCard9 = new CardScreen("file:Sagrada-cards/sagrade-objectivecards/objectivecard-pub.tintvarieteit-per-rij.png");
		objectiveCard10 = new CardScreen("file:Sagrada-cards/sagrade-objectivecards/objectivecard-pub.tintvarieteit.png");
		
		generateToolcards();
		generateObjectiveCards();
		cardScreen.createView();
	}
	
	private int generateRandNR(int NR){
		return (int)(Math.random() * (NR))+1;
	}
	
	

	public GameCardsScreen showcards(){
		return cardScreen;
	}
	
	public void handleExit() {
		gameController.switchToGameScreen();
	
		
	}
	
	public void buyTC(CardScreen cardscreen) {
		
		gameController.switchToGameScreen();
	}
	
	public void buyTC1() {
		
	}
	
	
	
public void generateToolcards() {
		
		TC1 = generateRandNR(12);
		 TC2 = generateRandNR(12);
		while (TC1==TC2) {
			 TC2 = generateRandNR(12);
		}
			TC3 = generateRandNR(12);
		while(TC3== TC1 || TC3 == TC2) {
			TC3 = generateRandNR(12);
		}
		
		switch(TC1) {
		  case 1:
			 cardScreen.setTC1(toolCard1);
		    break;
		  case 2:
			  cardScreen.setTC1(toolCard2);
		    break;
		  case 3:
			  cardScreen.setTC1(toolCard3);
			    break;
		  case 4:
			  cardScreen.setTC1(toolCard4);
			    break;
		  case 5:
			  cardScreen.setTC1(toolCard5);
			    break;
		  case 6:
			  cardScreen.setTC1(toolCard6);
			    break;
		  case 7:
			  cardScreen.setTC1(toolCard7);
			    break;
		  case 8:
			  cardScreen.setTC1(toolCard8);
			    break;
		  case 9:
			  cardScreen.setTC1(toolCard9);
			    break;
		  case 10:
			  cardScreen.setTC1(toolCard10);
			    break;
		  case 11:
			  cardScreen.setTC1(toolCard11);
			    break;
		  case 12:
			  cardScreen.setTC1(toolCard12);
			    break;
		 
		}
		
		switch(TC2) {
		  case 1:
			  cardScreen.setTC2(toolCard1);
		    break;
		  case 2:
			  cardScreen.setTC2(toolCard2);
		    break;
		  case 3:
			  cardScreen.setTC2(toolCard3);
			    break;
		  case 4:
			  cardScreen.setTC2(toolCard4);
			    break;
		  case 5:
			  cardScreen.setTC2(toolCard5);
			    break;
		  case 6:
			  cardScreen.setTC2(toolCard6);
			    break;
		  case 7:
			  cardScreen.setTC2(toolCard7);
			    break;
		  case 8:
			  cardScreen.setTC2(toolCard8);
			    break;
		  case 9:
			  cardScreen.setTC2(toolCard9);
			    break;
		  case 10:
			  cardScreen.setTC2(toolCard10);
			    break;
		  case 11:
			  cardScreen.setTC2(toolCard11);
			    break;
		  case 12:
			  cardScreen.setTC2(toolCard12);
			    break;
		 
		
	}
		switch(TC3) {
		  case 1:
			  cardScreen.setTC3(toolCard1);
		    break;
		  case 2:
			  cardScreen.setTC3(toolCard2);
		    break;
		  case 3:
			  cardScreen.setTC3(toolCard3);
			    break;
		  case 4:
			  cardScreen.setTC3(toolCard4);
			    break;
		  case 5:
			  cardScreen.setTC3(toolCard5);
			    break;
		  case 6:
			  cardScreen.setTC3(toolCard6);
			    break;
		  case 7:
			  cardScreen.setTC3(toolCard7);
			    break;
		  case 8:
			  cardScreen.setTC3(toolCard8);
			    break;
		  case 9:
			  cardScreen.setTC3(toolCard9);
			    break;
		  case 10:
			  cardScreen.setTC3(toolCard10);
			    break;
		  case 11:
			  cardScreen.setTC3(toolCard11);
			    break;
		  case 12:
			  cardScreen.setTC3(toolCard12);
			    break;
			    
		  

}
	}
	
	public void generateObjectiveCards() {
		
		privOBJ= generateRandNR(5);
		pubOBJ1 = generateRandNR(10);
		pubOBJ2 = generateRandNR(10);
		while (pubOBJ1==pubOBJ2) {
			pubOBJ2 = generateRandNR(10);
		}
		pubOBJ3 = generateRandNR(10);
		while(pubOBJ3== pubOBJ1 || pubOBJ3 == pubOBJ2) {
			pubOBJ2 = generateRandNR(10);
		}
		
		
		switch(pubOBJ1) {
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
		
		switch(pubOBJ2) {
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
		switch(pubOBJ3) {
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
		
		switch(privOBJ) {
		  case 1:
			  cardScreen.setPRIVOBJCard(blueObjectiveCard);
		    break;
		  case 2:
			  cardScreen.setPRIVOBJCard(purpleObjectiveCard);
		    break;
		  case 3:
			  cardScreen.setPRIVOBJCard(yellowObjectiveCard);
			    break;
		  case 4:
			  cardScreen.setPRIVOBJCard(greenObjectiveCard);
			    break;
		  case 5:
			  cardScreen.setPRIVOBJCard(redObjectiveCard);
			    break;
		  
			
		 
		}
	}
	
	
	
	

}
