package controller;

import main.GUI;
import model.UserListModel;
import view.UserListScreen;

public class UserListController{

    private UserListModel userListModel;
    private UserListScreen userListScreen;
    private GUI gui;

    public UserListController(GUI gui, DatabaseController databaseController){
        this.userListModel = new UserListModel(databaseController);
        this.userListScreen = new UserListScreen(gui);
        this.gui=gui;
        getUsers();
    }

    public UserListScreen getUserListScreen(){ return userListScreen; }

    private void getUsers(){
      // userListScreen.displayUsers(userListModel.getUsers(), userListModel.getUserStats());
    }

    public void handleSort(Object val){
        userListScreen.displayUsers(userListModel.getUsers(val), userListModel.getUserStats());
    }

    public void toUserListScreen(){ gui.changePane(userListScreen); }
}
