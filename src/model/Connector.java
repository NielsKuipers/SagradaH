package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static final String USER = "ggrpgest";
    private static final String PASS = "Ab12345";
    private static final String DB_URL = "jdbc:mysql://databases.aii.avans.nl:3306/nprjkuip_db2";

    public Connector() {
        connectDB();
    }

    public Connection connectDB() {
        try {
            Connection mConn = DriverManager.getConnection(DB_URL, USER, PASS);
            return mConn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
