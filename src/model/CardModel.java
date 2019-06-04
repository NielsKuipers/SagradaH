package model;

import javafx.scene.paint.Color;
import queries.CardQueries;

public class CardModel {

    private CardQueries cardQueries;
    private GameModel gameModel;

    public CardModel(CardQueries cardQueries, GameModel gameModel) {
        this.gameModel = gameModel;
        this.cardQueries = cardQueries;
    }

    /**
     * checks if you already bought a toolcard in that round
     */
    public boolean checkboughtTCForRound() {
        return !cardQueries.checkBoughtTCForRound(gameModel.getGameID(), gameModel.getPlayer(0).getPlayerId(), gameModel.getRound(), gameModel.getInFirstTurn()).isEmpty();
    }
    
    public GameModel getGameModel() {
        return gameModel;
    }

 

    /**
     * lets you check if a gametoolcard is bought
     * 
     * @param tc = which gametoolcard you want to check for
     * @return = if bought or not 
     */
    public boolean checkboughtTC(int tc) {
        return cardQueries.CheckTCBought(tc, gameModel.getGameID()).size() > 0;
    }

    /**
     * gets the amount of FT
     */
    public int getAmountFT() {
        return cardQueries.CheckIDFT(gameModel.getPlayer(0).getPlayerId(), gameModel.getGameID()).size();
    }

    /**
     * gets the amount of FT for one player for each toolcard
     */
    public int getAmountFTOnToolCard(int tc, int player) {
        switch (player) {
            case 1:
                return cardQueries.CheckAmountFTonTC(tc, gameModel.getPlayer(0).getPlayerId(), gameModel.getGameID()).size();
            case 2:
                return cardQueries.CheckAmountFTonTC(tc, gameModel.getPlayer(1).getPlayerId(), gameModel.getGameID()).size();
            case 3:
                return cardQueries.CheckAmountFTonTC(tc, gameModel.getPlayer(2).getPlayerId(), gameModel.getGameID()).size();
            case 4:
                return cardQueries.CheckAmountFTonTC(tc, gameModel.getPlayer(3).getPlayerId(), gameModel.getGameID()).size();
            default:
                return 0;
        }
    }

    /**
     * set in database which toolcard you bought
     * 
     * @param tc = toolcard which is bought
     */
    public void BuyTC(int tc) {
        cardQueries.BuyTC(tc, (int) cardQueries.CheckIDFT(gameModel.getPlayer(0).getPlayerId(), gameModel.getGameID()).get(0).get(0), gameModel.getGameID(), gameModel.getPlayer(0).getPlayerId(), gameModel.getRound(), gameModel.getInFirstTurn());
    }

   
    /**
     * set in database which toolcard you bought where the price was 2
     * 
     * @param tc = toolcard which is bought
     */
    public void BuyTCPric2(int tc) {


        cardQueries.BuyTCPrice2(tc, (int) cardQueries.CheckIDFT(gameModel.getPlayer(0).getPlayerId(), gameModel.getGameID()).get(0).get(0), (int) cardQueries.CheckIDFT(gameModel.getPlayer(0).getPlayerId(), gameModel.getGameID()).get(1).get(0), gameModel.getGameID(), gameModel.getPlayer(0).getPlayerId(), gameModel.getRound(), gameModel.getInFirstTurn());

    }

    // the methods below return the TC and the OBJC 
    public int getToolCard1() {
        int i = (int) cardQueries.getGameToolcardID(gameModel.getGameID()).get(0).get(0);
        return (int) cardQueries.getToolcard(i, gameModel.getGameID()).get(0).get(0);


    }

    public int getToolCard2() {
        int i = (int) cardQueries.getGameToolcardID(gameModel.getGameID()).get(1).get(0);
        return (int) cardQueries.getToolcard(i, gameModel.getGameID()).get(0).get(0);
    }

    public int getToolCard3() {
        int i = (int) cardQueries.getGameToolcardID(gameModel.getGameID()).get(2).get(0);
        return (int) cardQueries.getToolcard(i, gameModel.getGameID()).get(0).get(0);
    }

    

    public int getPubOBJcard1() {
        return (int) cardQueries.getOBJCard(gameModel.getGameID()).get(0).get(0);
    }

    public int getPubOBJcard2() {
        return (int) cardQueries.getOBJCard(gameModel.getGameID()).get(1).get(0);
    }

    public int getPubOBJcard3() {
        return (int) cardQueries.getOBJCard(gameModel.getGameID()).get(2).get(0);
    }

    public String getPRIVOBJCard() {
        return (String) cardQueries.getPrivateOBJCard(gameModel.getGameID(), gameModel.getPlayer(0).getPlayerId()).get(0).get(0);
    }

    /**
     * updates the gamedie when diceontable is changed
     */
    public void updateDiceOnTable(int eyes, int dieNumber, Color color) {
        cardQueries.updateDiceOnTable(eyes, dieNumber, gameModel.getGameID(), gameModel.getColorForQuerie(color));
    }


}