package controller;

import model.DatabaseModel;
import java.sql.*;

public class DatabaseController {

    private Connection mConn;

    public DatabaseController(){
        DatabaseModel sagradaBaseConn = new DatabaseModel();
        this.mConn = sagradaBaseConn.connectDB();
    }

    public void selectQuery(String query){
        try {
           Statement stmt = mConn.createStatement();
           executeQuery(stmt, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void selectQuery2(String query){
        try {
           Statement stmt = mConn.createStatement();
          ResultSet res = stmt.executeQuery(query);
          
          while(res.next()) {
				System.out.print(res.getString(1) + ", password ");
				System.out.println(res.getString(2));
		}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executeQuery(Statement stmt, String query){
        try {
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                int i = 1;
                System.out.println(rs.getString(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
