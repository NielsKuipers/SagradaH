package queries;

import java.sql.*;
import java.util.ArrayList;

import static view.ChatScreen.spamError;

public class StandardQueries {
    private Connection mConn;

    public StandardQueries(Connection connection) {
        mConn = connection;
    }

    private PreparedStatement stmt;

    /**
     * standard method for executing a select query with prepared statement
     *
     * @param query    = SELECT part of the query
     * @param where    = if required a WHERE clause
     * @param whereVal = values to be used in the WHERE clause
     * @param orderBY  = orderBY if needed
     * @return ArrayList conatining Arraylists of objects for every selected row
     */
    public ArrayList<ArrayList<Object>> selectQuery(String query, String where, String whereVal, String orderBY) {
        ArrayList<ArrayList<Object>> result = new ArrayList<>();
        try {
            stmt = mConn.prepareStatement(query + where + orderBY);

            int i = 1;
            if (!where.isEmpty()) {
                handleVals(whereVal, stmt, i);
            }

            ResultSet rs = stmt.executeQuery();
            getResult(rs, result);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * overload for query without where clause
     *
     * @param query = select query
     * @return query
     */
    public ArrayList<ArrayList<Object>> selectQuery(String query) {
        return selectQuery(query, "", "", "");
    }

    //overload for selectquery with where but without order by
    public ArrayList<ArrayList<Object>> selectQuery(String query, String where, String whereVal) {
        return selectQuery(query, where, whereVal, "");
    }

    /**
     * method for executing update or insert queries with prepared statement
     *
     * @param query    = UPDATE or INSERT part of query
     * @param values   = values to update or inset
     * @param where    = where clause if needed
     * @param whereVal = values for where clause
     */
    void updateQuery(String query, String values, String where, String whereVal) {
        try {
            stmt = mConn.prepareStatement(query + where);
            //split the values and use them in the statement
            String[] vals = values.split("\0");
            int i = 1;
            for (String val : vals) {
                if (checkInt(val)) {
                    int x = Integer.parseInt(val);
                    stmt.setInt(i, x);
                } else if (val.equals("null")) {
                    stmt.setObject(i, null);
                } else {
                    stmt.setString(i, val);
                }
                i++;
            }
            if (!where.isEmpty()) {
                handleVals(whereVal, stmt, i);
            }
            stmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e2) {
            if (query.contains("chatline")) {
                spamError();
            } else {
                e2.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //overload for insert query without where
    void updateQuery(String query, String values) {
        updateQuery(query, values, "", "");
    }

    /**
     * method to check if passed string can be converted to an int
     *
     * @param str = string to check
     * @return true if string can be converted to int otherwise false
     */
    private boolean checkInt(String str) {
        if (!str.isEmpty()) {
            for (int x = 0; x < str.length(); x++) {
                char c = str.charAt(x);
                if (c < '0' || c > '9') {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * method that checks values for if they're an int and adds them to the stmt
     *
     * @param v    = values to check
     * @param stmt = the statement to add the values to
     * @param i    = iterator used in the query method so it continues to add values to the right places
     * @throws SQLException sql error message
     */
    private void handleVals(String v, PreparedStatement stmt, int i) throws SQLException {
        String[] vals = v.split("\0");
        for (String val : vals) {
            if (checkInt(val)) {
                int x = Integer.parseInt(val);
                stmt.setInt(i, x);
            } else {
                stmt.setString(i, val);
            }
            i++;
        }
    }

    /**
     * get all rows from a resultset and add them to an array
     *
     * @param rs     = resultset to use
     * @param result = ArrayList to add the rows to
     * @throws SQLException sql error message
     */
    private void getResult(ResultSet rs, ArrayList<ArrayList<Object>> result) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        while (rs.next()) {
            ArrayList<Object> row = new ArrayList<>();
            for (int i = 1; i <= columns; i++) {
                row.add(rs.getObject(i));
            }
            result.add(row);
        }
    }
}