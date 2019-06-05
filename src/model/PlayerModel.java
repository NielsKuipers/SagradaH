package model;

import java.util.ArrayList;

import queries.PlayerQueries;

public class PlayerModel {

    private int idPlayer;
    private WindowPatternModel windowPattern;

    private PlayerQueries playerQueries;

    public PlayerModel(PlayerQueries playerQueries) {
        this.playerQueries = playerQueries;
    }

    public WindowPatternModel getWindowPatternPlayer() {
        return windowPattern;
    }

    public void givePlayerWindowPattern(WindowPatternModel windowPatternModel) {
        this.windowPattern = windowPatternModel;
    }

    void setPlayerId(int id) {
        idPlayer = id;
    }

    public int getPlayerId() {
        return idPlayer;
    }

    /**
     * @param idGame = id of the game get all the information about a windowpattern
     *               and add it to the model
     */
    void selectWindow(int idGame) {
        ArrayList<ArrayList<Object>> result = playerQueries.getWindowId(idPlayer);
        try {
            if (!result.isEmpty()) {
                windowPattern.setId(Integer.valueOf(String.valueOf(result.get(0).get(0))));
                windowPattern.selectAllFields();
                windowPattern.selectAllDicesOnField(idPlayer, idGame);
                windowPattern.selectDifficulty();
                selectPlayerName();
                selectPlayerScore();
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    private void selectPlayerName() {
        ArrayList<ArrayList<Object>> result = playerQueries.getPlayerName(idPlayer);
        windowPattern.setPlayerName("Naam: " + result.get(0).get(0));
    }

    private void selectPlayerScore() {
        ArrayList<ArrayList<Object>> result = playerQueries.getPlayerScore(idPlayer);
        windowPattern.setPlayerScore("Score: " + result.get(0).get(0));
    }

    public void updateWindowId(int windowId) {
        playerQueries.updateWindowID(idPlayer, windowId);
    }

    /**
     * check if this player is has turn
     *
     * @return true or false
     */
    public boolean selectCurrentPlayer() {
        ArrayList<ArrayList<Object>> result = playerQueries.getIsCurrentPlayer(idPlayer);
        int currentPlayer = 0;
        if (!result.isEmpty()) {
            currentPlayer = Integer.valueOf(String.valueOf(result.get(0).get(0)));
        }
        return currentPlayer == 1;
    }

    int selectSqnr() {
        ArrayList<ArrayList<Object>> result = playerQueries.getSqnrPlayer(idPlayer);
        return Integer.valueOf(String.valueOf(result.get(0).get(0)));
    }

    void updateSqnr(int sqnr) {
        playerQueries.updateSqnrPlayer(idPlayer, sqnr);
    }

    void updateQurrentPlayer(int isQurrent) {
        playerQueries.updateIsCurrentPlayer(idPlayer, isQurrent);
    }

    public void setDiceOnWindowPattern(int posX, int posY, int dienumber, String diecolor) {
        playerQueries.updateDiceOnWindowPattern(idPlayer, posX, posY, dienumber, diecolor);
    }

    /**
     * @param posX        = position x of field
     * @param posY        = position y of field
     * @param dienumber   = die number
     * @param diecolor    = diecolor
     * @param inFirstTurn = 1: firsturn  0: secondturn
     * @param idGame      = id of game
     *                    add a dice to playerframefield and give inFirstTurn
     */
    public void setDiceOnWindowPatternAndGiveFirstTurn(int posX, int posY, int dienumber, String diecolor,
                                                       int inFirstTurn, int idGame) {
        playerQueries.updateDiceOnWindowPattern(idPlayer, posX, posY, dienumber, diecolor);
        playerQueries.updateFirstTurnDice(idGame, dienumber, diecolor, inFirstTurn);
    }

    public void removeDiceOnWindowPattern(int dienumber, String diecolor) {
        playerQueries.removeDiceOnWindowPattern(idPlayer, dienumber, diecolor);
    }

}
