package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseModel {
    //set database login details
    private static final String USER = "42IN04SOh";
    private static final String PASS = "haalbaarheid";
    private static final String DB_URL = "jdbc:mysql://databases.aii.avans.nl:3306/2019_soprj4_sagrada_ghijkl";

    public DatabaseModel() {
        connectDB();
    }

    //connect to database
    public Connection connectDB() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
