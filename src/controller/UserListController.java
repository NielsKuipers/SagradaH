package controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import main.GUI;
import model.UserListModel;
import view.UserListScreen;

import java.util.ArrayList;

public class UserListController extends Scene {

    private UserListModel userListModel;
    private UserListScreen userListScreen;
    private ArrayList<Object> playerStats;

    public UserListController(GUI gui, DatabaseController databaseController){
        super(new Pane());
        this.userListModel = new UserListModel(databaseController);
        this.userListScreen = new UserListScreen(gui);

        getUsers();

        setRoot(userListScreen);
    }

    private void getUsers(){
        userListScreen.displayUsers(userListModel.getUsers(), userListModel.getUserStats());
    }

    public void handleSort(Object val){
        userListScreen.displayUsers(userListModel.getUsers(val), userListModel.getUserStats());
    }
}
