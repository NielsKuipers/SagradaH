package queries;

public class AccountQuery {
	StandardQuerie standardQuery;
	public AccountQuery(StandardQuerie standardQuery) {
		this.standardQuery = standardQuery;
	}
	
	public Boolean Login(String username, String password) {
		if(standardQuery.selectQuery("SELECT username, password FROM account", "WHERE username=?, password=?", username+"\0"+password)!= null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean register(String username, String password) {
		if(username.length() >= 3 && password.length() >= 3) {
			if(standardQuery.selectQuery("SELECT username FROM accout", "WHERE username=?", username) == null) {
				standardQuery.updateQuery("INSERT INTO account VALUES(?,?)", username+"\0"+password);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
