package controller;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import main.GUI;
import model.DiceOnTableModel;
import view.DiceOnTableScreen;
import view.DiceScreen;
import java.util.Optional;
import java.util.Random;

/**
 * @author user
 *
 */
public class DiceController {

    private Random r = new Random();

    private DiceOnTableScreen diceOnTableScreen;

    private DiceOnTableModel diceOnTableModel;
    private CardController CC;
    private GameController GC;

    public DiceController(GUI gui, WindowController WC) {
        diceOnTableModel = new DiceOnTableModel();
        diceOnTableScreen = new DiceOnTableScreen(gui, diceOnTableModel, WC);
    }

    void setGameController(GameController GC) {
        this.GC = GC;
    }

    public DiceOnTableScreen getDiceOnTableScreen() {
        return diceOnTableScreen;
    }

    void setCardController(CardController CC) {
        this.CC = CC;
    }

    DiceOnTableModel getDiceOnTableModel() {
        return diceOnTableModel;
    }
    
    
    /**
     * sets all dices with a glow border and lets you select a dice after
     * 
     * @param number = the number is used to check which interaction needs to happen to the dice 
     */
    void setDiceGlowBorder(int number) {
        for (Node node : diceOnTableScreen.getChildren()) {
            if (node instanceof DiceScreen) {
                DiceScreen result = (DiceScreen) node;
                result.setGlowBorder();
                result.setOnMouseClicked(e -> selectDice(result, number));
            }
        }
    }

    /**
     * all dices on screen get a black border 
     */
    private void setDiceBlackBorder() {
        for (Node node : diceOnTableScreen.getChildren()) {
            if (node instanceof DiceScreen) {
                DiceScreen result = (DiceScreen) node;
                result.setBlackBorder();
                result.setOnMouseClicked(null);
            }
        }
    }

    /**
     * select one dice and sets glow border other dices cant be used anymore
     * @param dice = dice you want ot interact with
     * @param number = the method were it needs to go
     */
    private void selectDice(DiceScreen dice, int number) {
        setDiceBlackBorder();
        dice.setGlowBorder();

        switch (number) {
            case 1:
                plusOrMinus(dice);
                break;
            case 6:
                throwDiceOnce(dice);
                break;

            case 10:
                dice.setOnMouseClicked(e -> DiceTurnAround(dice));
                break;
            case 11:
                dice.setOnMouseClicked(e -> pickNewDice(dice));
        }
    }

    /**
     * functionality for toolcard 11
     * lets you pick random dice and set the eyes you chooose
     * 
     * @param dice = dice to be replaced
     */
    private void pickNewDice(DiceScreen dice) {
        TextInputDialog dialog = new TextInputDialog("1");

        dialog.setTitle("Toolcard 11");
        dialog.setHeaderText("Choose eyes between 1-6");
        dialog.setContentText("Eyes:");

        Optional<String> result = dialog.showAndWait();
        dice.setOnMouseClicked(null);
        result.ifPresent(name -> {
            if (name.equals("1") || name.equals("2") || name.equals("3") || name.equals("4") || name.equals("5")
                    || name.equals("6")) {
                GC.getGameModel().pickNewDice(dice.getDiceModel().getDiceNumber(),
                        dice.getDiceModel().getColorForQuerie(), Integer.valueOf(name));
            }
        });

        GC.startTimer();
    }

    /**
     *  functionality for Toolcard 6
     * throws the dice ones
     * 
     * @param dice
     */
    private void throwDiceOnce(DiceScreen dice) {
        dice.setOnMouseClicked(null);

        int i = r.nextInt((6 - 1) + 1) + 1;
        dice.getDiceModel().setEyes(i);
        CC.getCardModel().updateDiceOnTable(i, dice.getDiceModel().getDiceNumber(), dice.getDiceModel().getColor());
    }

    /**
     * functionality for Toolcard 10
     * turns the dice to the other side
     * 
     * @param dice = dice to turn around
     *
     * */
    private void DiceTurnAround(DiceScreen dice) {
        switch (dice.getDiceModel().getEyes()) {
            case 1:
                dice.getDiceModel().setEyes(6);
                CC.getCardModel().updateDiceOnTable(6, dice.getDiceModel().getDiceNumber(), dice.getDiceModel().getColor());
                break;
            case 2:
                dice.getDiceModel().setEyes(5);
                CC.getCardModel().updateDiceOnTable(5, dice.getDiceModel().getDiceNumber(), dice.getDiceModel().getColor());
                break;
            case 3:
                dice.getDiceModel().setEyes(4);
                CC.getCardModel().updateDiceOnTable(4, dice.getDiceModel().getDiceNumber(), dice.getDiceModel().getColor());
                break;
            case 4:
                dice.getDiceModel().setEyes(3);
                CC.getCardModel().updateDiceOnTable(3, dice.getDiceModel().getDiceNumber(), dice.getDiceModel().getColor());
                break;
            case 5:
                dice.getDiceModel().setEyes(2);
                CC.getCardModel().updateDiceOnTable(2, dice.getDiceModel().getDiceNumber(), dice.getDiceModel().getColor());
                break;
            case 6:
                dice.getDiceModel().setEyes(1);
                CC.getCardModel().updateDiceOnTable(1, dice.getDiceModel().getDiceNumber(), dice.getDiceModel().getColor());
                break;
        }
    }

    /**
     * functionality for toolcard 1
     * opens a popup box where you can choose to increase or decrease die eyes by 1
     *
     * @param dice = die to increase or decrease
     */
    private void plusOrMinus(DiceScreen dice) {
        int eyes = dice.getDiceModel().getEyes();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Omhoog of omlaag?");
        alert.setHeaderText("Gereedschapskaart 1");
        alert.setContentText("Wil je de waarde van deze steen verhogen of verlagen?");

        ButtonType increase = new ButtonType("Verhogen");
        ButtonType decrease = new ButtonType("Verlagen");

        alert.getButtonTypes().setAll(increase, decrease);

        if (eyes == 6) {
            alert.getDialogPane().lookupButton(increase).setDisable(true);
        } else if (eyes == 1) {
            alert.getDialogPane().lookupButton(decrease).setDisable(true);
        }

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == increase) {
            dice.getDiceModel().setEyes(eyes += 1);
            CC.getCardModel().updateDiceOnTable(eyes, dice.getDiceModel().getDiceNumber(), dice.getDiceModel().getColor());
        } else if (result.get() == decrease) {
            dice.getDiceModel().setEyes(eyes -= 1);
            CC.getCardModel().updateDiceOnTable(eyes, dice.getDiceModel().getDiceNumber(), dice.getDiceModel().getColor());
        }
    }
}
