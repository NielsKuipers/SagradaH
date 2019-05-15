package queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class StandardQueries {
	private Connection mConn;
	
	public StandardQueries(Connection connection) {
		mConn = connection;
	}

	//method for select queries
    ArrayList<ArrayList<Object>> selectQuery(String query, String where, String whereVal){
        ArrayList<ArrayList<Object>> result = new ArrayList<>();
        try {
            PreparedStatement stmt = mConn.prepareStatement(query + where);

            //if there's a where clause handle it
            int i = 1;
            if(!where.isEmpty()){ handleWhere(whereVal, stmt, i); }

            //get the results from the query and put them in an array
            ResultSet rs = stmt.executeQuery();
            getResult(rs, result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //overload for selectquery without where clause
    public ArrayList<ArrayList<Object>> selectQuery(String query){
        return selectQuery(query, "", "");
    }

    //method for update queries
    void updateQuery(String query, String values, String where, String whereVal){
        try {
            PreparedStatement stmt = mConn.prepareStatement(query + where);
            //split the values and use them in the statement
            String[] vals = values.split("\0");
            int i=1;
            for(String val : vals){
                if(checkInt(val)){
                    int x = Integer.parseInt(val);
                    stmt.setInt(i, x);
                }
                else if(val.equals("null")){
                    stmt.setObject(i,null);
                }
                else{
                    stmt.setString(i, val);
                }
                i++;
            }

            //if there's a where clause handle it
            if(!where.isEmpty()){ handleWhere(whereVal, stmt, i); }
            stmt.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    //overload for insert query without where
    public void updateQuery(String query, String values){updateQuery(query, values, "", "");}

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

    private void handleWhere(String whereVal, PreparedStatement stmt, int i) throws SQLException {
        String[] whereVals = whereVal.split("\0");
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

    // get all columns from query and add all data from row to arraylist
    private void getResult(ResultSet rs, ArrayList<ArrayList<Object>> result) throws SQLException{
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        while(rs.next()){
            ArrayList<Object> row = new ArrayList<>();
            for(int i=1; i<=columns; i++){
                row.add(rs.getObject(i));
            }
            result.add(row);
        }
    }
}