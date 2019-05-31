package controller;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import model.CalculateScoreModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CalculateScoreController {
	private CalculateScoreModel scoreModel;

	public CalculateScoreController(DatabaseController dataC) {
		scoreModel = new CalculateScoreModel(dataC.getScoreQueries());
	}

	// kiest correcte public objectivecard id en returnt punten;
	public int getpoints(int playerID, int publicCardID) {
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

	// berekent private objective kaart punten
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
			System.out.println("no points");
		}
		return score;
	}
	
	// return aantal favortokens van speler
	private int calculateFavorTokens(int playerID) {
		return (int) (long) scoreModel.getPlayerFavortokens(playerID).get(0).get(0);
	}
	
	// return aantal leegstaande velden
	private int calculateEmptyFields(int playerID) {
		return (int) (long) scoreModel.getEmptyFields(playerID).get(0).get(0);
	}

	// sets van een van elke waarde, Tintvarieteit
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

	// sets van van 2 waarden
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

	private int calculatePublic3(int playerID) {
		return calculateRow(scoreModel.getPlayerDiceEyesPos(playerID), 'y', 4);
	}

	private int calculatePublic4(int playerID) {
		return calculateRow(scoreModel.getPlayerDiceColorsPos(playerID), 'y', 5);
	}

	// sets van een van elke kleur, Kleurvarieteit
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

	private int calculatePublic7(int playerID) {
		return calculateRow(scoreModel.getPlayerDiceColorsPosX(playerID), 'x', 6);
	}

	private int calculatePublic8(int playerID){
		return checkDiagonal(convertToGrid(scoreModel.getPlayerDiceColorsPosDiag(playerID)));
	}

	private int calculatePublic10(int playerID) {
		return calculateRow(scoreModel.getPlayerDiceEyesPosX(playerID), 'x', 5);
	}

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

	// returnt laagste getal uit array
	private int getMinValue(int[] values) {
		int minValue = values[0];

        for (int value : values) {
            if (value < minValue) {
                minValue = value;
            }
        }
		return minValue;
	}

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

	private int checkDiagonal(ArrayList<ArrayList<String>> grid){

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
		System.out.println(score);
		return score;
	}

	int getClientScore(int playerID){
		return getScore(playerID) + calculatePrivatePoints(playerID);
	}

	int getOtherScore(int playerID){ return getScore(playerID); }
}
