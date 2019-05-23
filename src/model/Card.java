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
		if(!cardQuerie.CheckTCBought(tc, gameModel.getGameID()).isEmpty()) {
			return true;
		}else {
			return false;
		}
	}

	public int getAmountFTOnTC(int tc) {
		return cardQuerie.CheckAmountFTonTC(tc, 1, gameModel.getGameID()).size();
	}

	public void BuyTC(int tc, int amountFT) {
		cardQuerie.BuyTC(tc, amountFT, gameModel.getGameID(), 1);
		
	}

	public void BuyTCPric2(int tc, int amountFT, int amountFT2) {
		cardQuerie.BuyTCPrice2(tc, amountFT,amountFT2, gameModel.getGameID(), 1);
		
	}
	
	
	
}
