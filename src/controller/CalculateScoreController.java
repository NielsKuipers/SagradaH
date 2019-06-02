package controller;


import model.CalculateScoreModel;
import java.util.ArrayList;

public class CalculateScoreController {
	private CalculateScoreModel scoreModel;

	public CalculateScoreController(DatabaseController dataC) {
		scoreModel = new CalculateScoreModel(dataC.getScoreQueries());
	}

	/**
	 * method for calculating points per public objective cards
	 * @param playerID = ID of current player
	 * @param publicCardID = ID of the card the player is using
	 * @return total points of public objective card based on player's windowpatterncard
	 */
	private int getpoints(int playerID, int publicCardID) {
		switch (publicCardID) {
		case 1:
			return calculatePublic1(playerID);
		case 2:
			return calculatePublic2(playerID, 3, 4);
		case 3:
			return calculatePublic3(playerID);
		case 4:
			return calculatePublic4(playerID);
		case 5:
			return calculatePublic2(playerID, 5, 6);
		case 6:
			return calculatePublic6(playerID);
		case 7:
			return calculatePublic7(playerID);
		case 8:
			return calculatePublic8(playerID);
		case 9:
			return calculatePublic2(playerID, 1, 2);
		case 10:
			return calculatePublic10(playerID);
		default:
			return 0;
		}
	}

	/**
	 * calculates private objective card points per player
	 * @param playerID = ID of player
	 * @return private objective card points based on player's windowpatterncard
	 */
	private int calculatePrivatePoints(int playerID) {
		ArrayList<ArrayList<Object>> result = scoreModel.getAllColorsEyes(playerID);
		int score = 0;

		try {
			String privateColor = (String) result.get(0).get(2);

			for (ArrayList<Object> objects : result) {
				if (objects.get(1).equals(privateColor)) {
					score += (int) objects.get(0);
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return score;
	}

	/**
	 * gets a player's favor tokens
	 * @param playerID = ID of player
	 * @return amount of favor tokens belonging to player
	 */
	private int calculateFavorTokens(int playerID) {
		return (int) (long) scoreModel.getPlayerFavortokens(playerID).get(0).get(0);
	}

	/**
	 * gets amount of empty spaces on a player's windowpatterncard
	 * @param playerID = ID of player
	 * @return  amount of empty spaces on a player's windowpatterncard
	 */
	private int calculateEmptyFields(int playerID) {
		return (int) (long) scoreModel.getEmptyFields(playerID).get(0).get(0);
	}

	/**
	 * calculates points for sets of 1 of each shade
	 * @param playerID = ID of player
	 * @return calculated score based on player's windowpatterncard
	 */
	private int calculatePublic1(int playerID) {
		int score;
		int cardpoints = 5;
		int[] counter = new int[6];
		ArrayList<ArrayList<Object>> result = scoreModel.getPlayerDiceNumbers(playerID);

		for (ArrayList<Object> objects : result) {
			counter[(int) objects.get(0) - 1]++;
		}

		score = getMinValue(counter) * cardpoints;
		return score;
	}

	/**
	 * calculate points for sets of 2 of each shade
	 * @param playerID = ID of player
	 * @param value1 = number of eyes for die 1
	 * @param value2 = number of eyes for die 2
	 * @return calculated score based on player's windowpatterncard
	 */
	private int calculatePublic2(int playerID, int value1, int value2) {
		int score;
		int cardpoints = 2;
		int[] counter = new int[2];
		ArrayList<ArrayList<Object>> result = scoreModel.getPlayerDiceNumbers(playerID);

		for (ArrayList<Object> objects : result) {
			if ((int) objects.get(0) == value1) {
				counter[0]++;
			} else if ((int) objects.get(0) == value2) {
				counter[1]++;
			}
		}

		score = getMinValue(counter) * cardpoints;
		return score;
	}

	/**
	 * calculates points for die in a column without duplicate eyes
	 * @param playerID = ID of player
	 * @return calculated score based on player's windowpatterncard
	 */
	private int calculatePublic3(int playerID) {
		return calculateRow(scoreModel.getPlayerDiceEyesPos(playerID), 'y', 4);
	}

	/**
	 * calculates points for die in a column without duplicate colors
	 * @param playerID = ID of player
	 * @return calculated score based on player's windowpatterncard
	 */
	private int calculatePublic4(int playerID) {
		return calculateRow(scoreModel.getPlayerDiceColorsPos(playerID), 'y', 5);
	}

	/**
	 * calculates points for sets of one of every color
	 * @param playerID = ID of player
	 * @return calculated score based on player's windowpatterncard
	 */
	private int calculatePublic6(int playerID) {
		int score;
		int cardpoints = 4;
		int[] counter = new int[5];
		ArrayList<ArrayList<Object>> result = scoreModel.getPlayerDiceColors(playerID);

		for (ArrayList<Object> objects : result) {
			switch ((String) objects.get(0)) {
			case "rood":
				counter[0]++;
				break;
			case "geel":
				counter[1]++;
				break;
			case "paars":
				counter[2]++;
				break;
			case "groen":
				counter[3]++;
				break;
			case "blauw":
				counter[4]++;
				break;
			}
		}

		score = getMinValue(counter) * cardpoints;
		return score;
	}

	/**
	 * calculates points for die in a row without duplicate colors
	 * @param playerID = ID of player
	 * @return calculated score based on player's windowpatterncard
	 */
	private int calculatePublic7(int playerID) {
		return calculateRow(scoreModel.getPlayerDiceColorsPosX(playerID), 'x', 6);
	}

	/**
	 * calculates points for die in a diagonal without duplicate colors
	 * @param playerID = ID of player
	 * @return calculated score based on player's windowpatterncard
	 */
	private int calculatePublic8(int playerID){
		return checkDiagonal(convertToGrid(scoreModel.getPlayerDiceColorsPosDiag(playerID)));
	}

	/**
	 * calculates points for die in a column without duplicate eyes
	 * @param playerID = ID of player
	 * @return calculated score based on player's windowpatterncard
	 */
	private int calculatePublic10(int playerID) {
		return calculateRow(scoreModel.getPlayerDiceEyesPosX(playerID), 'x', 5);
	}

	/**
	 * convert query result into a grid type arraylist for calculating diagonals
	 * @param list = query to convert
	 * @return ArrayList with colors based on a 5x4 grid
	 */
	private ArrayList<ArrayList<String>> convertToGrid(ArrayList<ArrayList<Object>> list){
		int i = 0;
		int j = 1;
		ArrayList<ArrayList<String>> result = new ArrayList<>();

		result.add(new ArrayList<>());

		for (ArrayList<Object> val : list) {
			if (j == (int) val.get(0)) {
				result.get(i).add(val.get(2).toString());
			} else {
				i++;
				j++;
				result.add(new ArrayList<>());
				result.get(i).add(val.get(2).toString());
			}
		}
		return result;
	}
	
	public void setGameID(int gameID) {
		scoreModel.setGameID(gameID);
	}

	/**
	 * figure out if die in a row (eyes or color) are unique and calculate score based on player's windowpatterncard
	 * @param die = die color or die eyes on player's windowpatterncard
	 * @param dir = direction the method should calculate in being X or Y
	 * @param scoreAdd = amount of score to add based on objective card
	 * @return calculated points based on player's windowpatterncard
	 */
	private int calculateRow(ArrayList<ArrayList<Object>> die, char dir, int scoreAdd) {
		ArrayList<Object> rowDie = new ArrayList<>();
		int score = 0;
		int i = 1;
		int j = 4;

		if (dir == 'x') {
			j = 5;
		}

		for(ArrayList<Object> dice : die ){
			if((int) dice.get(0) == i && !rowDie.contains(dice.get(2))){
				rowDie.add(dice.get(2));
				if (rowDie.size() == j) {
					score += scoreAdd;
					rowDie.clear();
					i++;
				}
			} else {
				if((int) dice.get(0) == i){ continue; }
				rowDie.clear();
				rowDie.add(dice.get(2));
				i++;
			}
		}
		return score;
	}

	/**
	 * returns lowest value in array
	 * @param values = array of values
	 * @return lowest value
	 */
	private int getMinValue(int[] values) {
		int minValue = values[0];

        for (int value : values) {
            if (value < minValue) {
                minValue = value;
            }
        }
		return minValue;
	}

	/**
	 * gets the total amount of points based on player's cards
	 * @param playerID = ID of player
	 * @return total amount of points acquired being: public card, private card, favor tokens left, and empty fields
	 */
	private int getScore(int playerID){
		
		ArrayList<ArrayList<Object>> result = scoreModel.getPublicCards();
		int points = 0;
		for(ArrayList cards : result){
			for(Object card : cards){
				points += getpoints(playerID, (int) card);
			}
		}
		points += calculateFavorTokens(playerID);
		points -= calculateEmptyFields(playerID);

		return points;
	}

	/**
	 * method that calculates points based on diecolors in a diagonal row on a player's windowpatterncard
	 * @param grid = arraylist to itterate over
	 * @return amount of points
	 */
	private int checkDiagonal(ArrayList<ArrayList<String>> grid){
		try {
			String curColor = null;
			int score = 0;
			int z = 2;
			int x = 2;
			int h = 2;
			int y = 0;
			int i = 0;

			for(int j = 0; j<6; j++){
				for(; y<z; y++, x++){
					if(grid.get(x).get(y).equals(curColor) && !grid.get(x).get(y).equals("0")){
						score += 1;
					}
					curColor = grid.get(x).get(y);
				}
				curColor = null;
				if(j > 2){i++;}
				else if (j == 2){i++; z++;}
				else{h--; z++;}
				y = i;
				x = h;
			}

			z = 2;
			x = 1;
			h = 1;
			y = 0;
			i = 0;

			for(int j = 0; j<6; j++){
				for(; y<z; y++, x--){
					if(grid.get(x).get(y).equals(curColor) && !grid.get(x).get(y).equals("0")){
						score += 1;
					}
					curColor = grid.get(x).get(y);
				}
				curColor = null;
				if(j > 2){i++;}
				else if (j == 2){i++; z++;}
				else{h++; z++;}
				y = i;
				x = h;
			}
			return score;
		}catch(Exception e){
			return 0;
		}
	}

	int getClientScore(int playerID){ return getScore(playerID) + calculatePrivatePoints(playerID); }

	int getOtherScore(int playerID){ return getScore(playerID); }
}
