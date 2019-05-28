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
			return 0;
		case 9:
			return calculatePublic2(playerID, 1, 2);
		case 10:
			return calculatePublic10(playerID);
		default:
			return 0;
		}
	}

	// berekent private objective kaart punten
	public int calculatePrivatePoints(int playerID) {
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
		System.out.println(score);
		return score;
	}
	
	// return aantal favortokens van speler
	public int calculateFavorTokens(int playerID) {
		return (int) (long) scoreModel.getPlayerFavortokens(playerID).get(0).get(0);
	}
	
	// return aantal leegstaande velden
	public int calculateEmptyFields(int playerID) {
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
		return calculateRow(scoreModel.getPlayerDiceColorsPos(playerID), 'y', 5);
	}

	private int calculatePublic4(int playerID) {
		return calculateRow(scoreModel.getPlayerDiceEyesPos(playerID), 'y', 4);
	}

	// sets van een van elke kleur, Kleurvarieteit
	private int calculatePublic6(int playerID) {
		int score = 0;
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

	private int calculatePublic10(int playerID) {
		return calculateRow(scoreModel.getPlayerDiceEyesPosX(playerID), 'x', 5);
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

		for (int i = 0; i < values.length; i++) {
			if (values[i] < minValue) {
				minValue = values[i];
			}
		}
		return minValue;
	}
}
