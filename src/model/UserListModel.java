package model;

import controller.DatabaseController;
import queries.UserListQueries;

import java.util.ArrayList;

public class UserListModel {
    private UserListQueries userListQueries;

    public UserListModel(DatabaseController databaseController) {
        userListQueries = databaseController.getUserListQueries();
    }

    public ArrayList<ArrayList<Object>> getUsers() {
        return userListQueries.getUserList();
    }

    public ArrayList<ArrayList<Object>> getUsers(Object val) {
        return userListQueries.getUserList(val);
    }

    public ArrayList<ArrayList<Object>> getUserStats() {
        return userListQueries.getUserStats();
    }
}
