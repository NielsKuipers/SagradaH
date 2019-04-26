package queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DiceQueries {
	Connection connection;
	public DiceQueries(Connection connection) {
		this.connection = connection;
	}

	
	public void getName(String name) {
		selectQuery2("SELECT username FROM nprjkuip_db2.account WHERE username = '"+name+"';");
	}
	
	public void selectQuery2(String query) {
		try {
			Statement stmt = connection.createStatement();
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				
				System.out.println(res.getString(1));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
