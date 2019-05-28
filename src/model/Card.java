package model;

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

    public boolean checkboughtTC(int tc) {
        if(cardQuerie.CheckTCBought(tc, gameModel.getGameID()).isEmpty()) {
            System.out.println("test123");
            return false;
        }else {
            return true;
            
        }
    }

    public int getAmountFTOnTC(int tc,int playerID) {
        return cardQuerie.CheckAmountFTonTC(tc, playerID, gameModel.getGameID()).size();
    }

    public void BuyTC(int tc) {
    	int infirstTurn=0;
        if(gameModel.isSecondTurn()) {
        	infirstTurn = 1;
        }
       
        cardQuerie.BuyTC(tc,(int)cardQuerie.CheckIDFT(1, gameModel.getGameID()).get(0).get(0), gameModel.getGameID(), 1,gameModel.getRound(),infirstTurn);
        
        
    }

    public void BuyTCPric2(int tc) {
    	int infirstTurn=0;
        if(gameModel.isSecondTurn()) {
        	infirstTurn = 1;
        }
        
        cardQuerie.BuyTCPrice2(tc,(int)cardQuerie.CheckIDFT(1, gameModel.getGameID()).get(0).get(0),(int)cardQuerie.CheckIDFT(1, gameModel.getGameID()).get(1).get(0), gameModel.getGameID(),1,gameModel.getRound(),infirstTurn);
        
    }
public void setToolcards(int TC1,int TC2,int TC3) {
        cardQuerie.updateGameTC(1, TC1, gameModel.getGameID());
        cardQuerie.updateGameTC(2, TC2, gameModel.getGameID());
        cardQuerie.updateGameTC(3, TC3, gameModel.getGameID());
    }
    
    public int getToolCard1() {
        return (int) cardQuerie.getToolcard(1, gameModel.getGameID()).get(0).get(0);
        
    }
    public int getToolCard2() {
        return (int) cardQuerie.getToolcard(2, gameModel.getGameID()).get(0).get(0);
        
    }
    public int getToolCard3() {
        return (int) cardQuerie.getToolcard(3, gameModel.getGameID()).get(0).get(0);
        
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
    
    
    
}