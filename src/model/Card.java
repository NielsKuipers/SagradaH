package model;

import java.util.ArrayList;

import controller.WindowController;
import javafx.beans.property.SimpleStringProperty;
import queries.CardQueries;
import queries.GameQuery;

public class Card {

    private CardQueries cardQuerie;
    private Game gameModel;
   
    
    public Card(CardQueries cardQuerie, Game gameModel) {
        this.gameModel = gameModel;
        this.cardQuerie = cardQuerie;
    }
    
    
    public boolean checkboughtTCForRound() {
    	if(cardQuerie.checkBoughtTCForRound(gameModel.getGameID(), gameModel.getPlayer(0).getPlayerId(),gameModel.getRound(), gameModel.getInFirstTurn()).isEmpty()) {
    		
    		return false;
    	}else {
    		return true;
    	}
    }

    public boolean checkboughtTC(int tc) {
    	System.out.println(cardQuerie.CheckTCBought(tc, gameModel.getGameID()));
        if(cardQuerie.CheckTCBought(tc, gameModel.getGameID()).isEmpty()) {
            
            return false;
        }else {
            return true;
            
        }
    }
    
    public int getAmountFT() {
		return cardQuerie.CheckIDFT(gameModel.getPlayer(0).getPlayerId(), gameModel.getGameID()).size();
    	
    }

    public int getAmountFTOnToolCard(int tc, int player) {
    	switch(player) {
    	case 1:
    		return cardQuerie.CheckAmountFTonTC(tc, gameModel.getPlayer(0).getPlayerId(), gameModel.getGameID()).size();
    		
    	case 2:
    		return cardQuerie.CheckAmountFTonTC(tc, gameModel.getPlayer(1).getPlayerId(), gameModel.getGameID()).size();
    	case 3:
    		return cardQuerie.CheckAmountFTonTC(tc, gameModel.getPlayer(2).getPlayerId(), gameModel.getGameID()).size();
    	case 4:
    		return cardQuerie.CheckAmountFTonTC(tc, gameModel.getPlayer(3).getPlayerId(), gameModel.getGameID()).size();
    	default:
    		return 0;
    	
    	}
       
    }
    
    public void BuyTC(int tc) {
    	int infirstTurn=0;
        if(gameModel.isSecondTurn()) {
        	infirstTurn = 1;
        }
   
       
        cardQuerie.BuyTC(tc,(int)cardQuerie.CheckIDFT(gameModel.getPlayer(0).getPlayerId(), gameModel.getGameID()).get(0).get(0), gameModel.getGameID(), gameModel.getPlayer(0).getPlayerId(),1,infirstTurn);
      
        
    }

    public void BuyTCPric2(int tc) {
    	int infirstTurn=0;
        if(gameModel.isSecondTurn()) {
        	infirstTurn = 1;
        }
        
        cardQuerie.BuyTCPrice2(tc,(int)cardQuerie.CheckIDFT(gameModel.getPlayer(0).getPlayerId(), gameModel.getGameID()).get(0).get(0),(int)cardQuerie.CheckIDFT(gameModel.getPlayer(0).getPlayerId(), gameModel.getGameID()).get(1).get(0), gameModel.getGameID(),gameModel.getPlayer(0).getPlayerId(),gameModel.getRound(),infirstTurn);
        
    }

    
    public int getToolCard1() {
    		int i = (int) cardQuerie.getGameToolcardID(gameModel.getGameID()).get(0).get(0);
        return (int) cardQuerie.getToolcard(i, gameModel.getGameID()).get(0).get(0);
       
       
    }
    public int getToolCard2() {
    	int i = (int) cardQuerie.getGameToolcardID(gameModel.getGameID()).get(1).get(0);
        return (int) cardQuerie.getToolcard(i, gameModel.getGameID()).get(0).get(0);
    }
    public int getToolCard3() {
    	int i = (int) cardQuerie.getGameToolcardID(gameModel.getGameID()).get(2).get(0);
        return (int) cardQuerie.getToolcard(i, gameModel.getGameID()).get(0).get(0);
    }
    
    public Game getGameModel() {
		return gameModel;
	}
      
    public int getPubOBJcard1() {
        return (int) cardQuerie.getOBJCard(gameModel.getGameID()).get(0).get(0);
    }
    public int getPubOBJcard2() {
        return (int) cardQuerie.getOBJCard(gameModel.getGameID()).get(1).get(0);
    }
    public int getPubOBJcard3() {
        return (int) cardQuerie.getOBJCard(gameModel.getGameID()).get(2).get(0);
    }
    
    public void updateDiceOnTable(int eyes,int dieNumber) {
		cardQuerie.updateDiceOnTable(eyes, dieNumber, gameModel.getGameID());
	}
    
    
}