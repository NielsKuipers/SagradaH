package controller;

import model.DatabaseModel;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseController {

    private Connection mConn;

    //establish connection with database
    public DatabaseController(){
        DatabaseModel sagradaBaseConn = new DatabaseModel();
        this.mConn = sagradaBaseConn.connectDB();
    }

    //method for select queries
    public ArrayList<Object> selectQuery(String query, String where, String whereVal){
        ArrayList<Object> result = new ArrayList<>();
        try {
            PreparedStatement stmt = mConn.prepareStatement(query + where);

            //if there's a where clause, separate them if need be and set the values in the statement
            int i=1;
            if(!where.isEmpty()){
                String[] whereVals = whereVal.split(" ");
                for(String val : whereVals){
                    if(checkInt(val)){
                        int x = Integer.parseInt(val);
                        stmt.setInt(i, x);
                    }
                    else{
                        stmt.setString(i, val);
                    }
                }
            }

            //get the results from the query and put them in and array
            int x=1;
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                result.add(rs.getObject(x));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //method for update queries
    public void updateQuery(String query, String values, String where, String whereVal){
        try {
            PreparedStatement stmt = mConn.prepareStatement(query + where);

            //split the values and use them in the statement
            String[] vals = values.split(" ");
            int i=1;
            for(String val : vals){
                if(checkInt(val)){
                    int x = Integer.parseInt(val);
                    stmt.setInt(i, x);
                }
                else{
                    stmt.setString(i, val);
                }
                i++;
            }

            //if there's a where clause do the same
            if(!where.isEmpty()){
                String[] whereVals = whereVal.split(" ");
                for(String val : whereVals){
                    if(checkInt(val)){
                        int x = Integer.parseInt(val);
                        stmt.setInt(i, x);
                    }
                    else{
                        stmt.setString(i, val);
                    }
                    i++;
                }
            }
            stmt.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    //check if passed string is an int
    private boolean checkInt(String str){
        for(int x=0;x<str.length(); x++){
            char c = str.charAt(x);
            if(c < '0' || c > '9'){
                return false;
            }
        }
        return true;
    }

//        example queries below:
//        use question marks for where you want to use variables, declare them in the variable parameter
//        if you're using multiple variables, separate them with a space
//        if you don't want to use a where clause, just give it an empty string like this ""

//        updateQuery("UPDATE account set username=?, password=?", "Niels2 Gay1234", " WHERE username=?", "Niels");
//        updateQuery("INSERT INTO account VALUES(?,?)", "Mario Zario", "", "");
//        selectQuery("SELECT username FROM account", " WHERE username=?", "Niels2");
}
