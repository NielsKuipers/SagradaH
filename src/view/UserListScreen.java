package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import main.GUI;
import java.util.ArrayList;

public class UserListScreen extends VBox {

    private VBox userList = new VBox();
    private String[] statText = {"Hoogste score: ", "Aantal keer gewonnen: ", "Aantal keer verloren: ", "Meest gebruikte kleur: ", "Meest gebruikte ogen: ", "Unieke tegenstanders: "};
    private ArrayList<Button> buttons = new ArrayList<>();
    private GUI gui;

    public UserListScreen(GUI gui){
        this.gui = gui;

        setStyle("-fx-background-color: DEEPSKYBLUE;");

        HBox sortBox = new HBox();
        Label sortLabel = new Label("Sorteren op:");
        ChoiceBox sort = new ChoiceBox();
        sort.getItems().addAll("Gewonnen potjes", "Alfabetisch");
        sortBox.getChildren().addAll(sortLabel, sort);
        sortBox.setPadding(new Insets(5,5,0,5));

        userList.getChildren().addAll(sortBox);

        ScrollPane userScroll = new ScrollPane();
        userScroll.setFitToHeight(true);
        userScroll.setContent(userList);

        sort.setOnAction(e-> handleSort(sort.getValue()));

        getChildren().setAll(userScroll);
    }

    public void displayUsers(ArrayList<ArrayList<Object>> users, ArrayList<ArrayList<Object>> stats){
        String name;
        ArrayList<Object> relevantStats = new ArrayList<>();
        int i = 0;
        for (ArrayList row : users) {
            name = row.get(0).toString();

            Label username = new Label(name);
            buttons.add(new Button("Speler info"));
            Button curButton = buttons.get(i);
            HBox userRow = new HBox();

            for(ArrayList<Object> statUser : stats){
                if(statUser.contains(name)){relevantStats = statUser; break;}
                else{relevantStats = null;}
            }

            VBox userInfo = showUserStats(relevantStats);

            Region region = new Region();
            HBox.setHgrow(region, Priority.ALWAYS);

            userInfo.setManaged(false);
            userInfo.setVisible(false);
            userInfo.setId("invisible");

            userRow.setPrefWidth(200);
            userRow.setPadding(new Insets(5, 5, 0, 5));

            userRow.getChildren().addAll(username, region, curButton);
            userList.getChildren().addAll(userRow, userInfo);

            curButton.setOnMouseClicked(e-> showInfo(userInfo));
            i++;
        }
    }

    private VBox showUserStats(ArrayList<Object> stats){
        String statLine = "N/A";
        VBox playerStats = new VBox();
        if(stats != null){
            for(int i=0; i<6; i++){
                if(stats.get(i+1) != null){statLine = stats.get(i+1).toString();}
                createStat(statLine, playerStats, i);
            }
        }
        else{
            for(int i=0; i<6; i++){
                createStat(statLine, playerStats, i);
            }
        }
        playerStats.setPadding(new Insets(0,0,0,5));
        return playerStats;
    }

    private void createStat(String statLine, VBox playerStats, int i) {
        Label statLineLabel = new Label(statLine);
        Label standardText = new Label(statText[i]);
        Region region = new Region();
        HBox box = new HBox(standardText, region, statLineLabel);
        box.setPrefWidth(200);
        playerStats.getChildren().add(box);
    }

    private void showInfo(VBox userInfo){
        if(userInfo.getId().equals("invisible")){
            userInfo.setManaged(true);
            userInfo.setVisible(true);
            userInfo.setId("visible");
        }
        else{
            userInfo.setManaged(false);
            userInfo.setVisible(false);
            userInfo.setId("invisible");
        }
    }

    private void handleSort(Object val){
        userList.getChildren().remove(1, userList.getChildren().size());
        gui.handleSort(val);
    }
}
