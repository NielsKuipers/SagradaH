
package controller;

import model.DatabaseModel;
import java.sql.*;

public class DatabaseController {

    private Connection mConn;

    public DatabaseController(){
        DatabaseModel sagradaBaseConn = new DatabaseModel();
        this.mConn = sagradaBaseConn.connectDB();
    }

    public void selectQuery(String query, String where, String whereVal){
        try {
           PreparedStatement stmt = mConn.prepareStatement(query + where);

            int i=1;
            if(where!=null){
                String[] whereVals = whereVal.split(" ");
                for(String val : whereVals){
                    stmt.setString(i,val);
                    i++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateQuery(String query, String values, String where, String whereVal){
        try {
            PreparedStatement stmt = mConn.prepareStatement(query + where);

            String[] vals = values.split(" ");
            int i=1;
            for(String val : vals){
                stmt.setString(i,val);
                i++;
            }

            if(where!=null){
                String[] whereVals = whereVal.split(" ");
                for(String val : whereVals){
                    stmt.setString(i,val);
                    i++;
                }
            }
            System.out.println(stmt);
//            stmt.executeUpdate();
        }
        catch(SQLException e){
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

    public void test(){
//        updateQuery("update account set username=?, password=?", "Niels2 Gay1234", " WHERE username=?", "Niels");
//        updateQuery("INSERT INTO account(?,?)", "Mario Zario", null, null);
    }
}
