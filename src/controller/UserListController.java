package controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import main.GUI;
import model.UserListModel;
import view.UserListScreen;

public class UserListController extends Scene {

    private UserListModel userListModel;
    private UserListScreen userListScreen;

    public UserListController(GUI gui, DatabaseController databaseController){
        super(new Pane());
        this.userListModel = new UserListModel(databaseController);
        this.userListScreen = new UserListScreen(gui);

        getUsers();

        setRoot(userListScreen);
    }

    public UserListScreen getUserListScreen(){ return userListScreen; }

    private void getUsers(){
        userListScreen.displayUsers(userListModel.getUsers(), userListModel.getUserStats());
    }

    public void handleSort(Object val){
        userListScreen.displayUsers(userListModel.getUsers(val), userListModel.getUserStats());
    }
}
