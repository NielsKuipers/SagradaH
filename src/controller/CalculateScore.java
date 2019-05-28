package controller;

import model.CalculateScoreModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CalculateScore {
	private CalculateScoreModel scoreModel;

	public CalculateScore(DatabaseController dataC) {
		scoreModel = new CalculateScoreModel(dataC.getScoreQueries());
	}
	
	// kiest correcte public objectivecard id en returnt punten;
	public int getpoints(int playerID, int publicCardID) {
		switch(publicCardID) {
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
			return 0;
		case 8:
			return 0;
		case 9:
			return calculatePublic2(playerID, 1, 2);
		case 10:
			return 0;
		default: return 0;
		}
	}

	// sets van een van elke waarde, Tintvarieteit
	private int calculatePublic1(int playerID) {
		int score;
		int cardpoints = 5;
		int[] counter = new int[6];
		ArrayList<ArrayList<Object>> result = scoreModel.getPlayerDiceNumbers(playerID);

		for (ArrayList<Object> objects : result) {
			counter[(int) objects.get(0)]++;
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

	private int calculatePublic3(int playerID){
		return calculateRow(playerID, scoreModel.getPlayerDiceColorsPos(playerID), 'y' , 5);
	}

	private int calculatePublic4(int playerID){
		return calculateRow(playerID, scoreModel.getPlayerDiceEyesPos(playerID), 'y' , 4);
	}

	// sets van een van elke kleur, Kleurvarieteit
	private int calculatePublic6(int playerID) {
		int score = 0;
		int cardpoints = 4;
		int[] counter = new int[5];
		ArrayList<ArrayList<Object>> result = scoreModel.getPlayerDiceColors(playerID);

		for (ArrayList<Object> objects : result) {
			switch((String) objects.get(0)) {
			case "rood":
				counter[0]++;
			case "geel":
				counter[1]++;
			case "paars":
				counter[2]++;
			case "groen":
				counter[3]++;
			case "blauw":
				counter[4]++;
			}
		}

		score = getMinValue(counter) * cardpoints;
		return score;
	}

	private int calculateRow(int playerID, ArrayList<ArrayList<Object>> die, char dir, int scoreAdd){
		ArrayList<Object> rowDie = new ArrayList<>();
		int score = 0;
		int i = 1;
		int x = 0;
		int j = 4;

		if(dir == 'x'){
			j = 5;
		}

		for(ArrayList<Object> dice : die ){
			if((int) dice.get(0) == i && !rowDie.contains(dice.get(2))){
				System.out.println(dice.get(0) + " - " + dice.get(2));
				rowDie.add(dice.get(2));
				x++;
				if(x == j){
					score += scoreAdd;
					x = 0;
					rowDie.clear();
					i++;
				}
			}
			else{
				rowDie.clear();
				x = 0;
				i++;
			}
		}
		return score;
	}

	// returnt laagste getal uit array
	private int getMinValue(int[] values) {
		int minValue = values[0];

		for (int i = 0; i < values.length; i++) {
			if (values[i] < minValue) {
				minValue = values[i];
			}
		}
		return minValue;
	}
}
