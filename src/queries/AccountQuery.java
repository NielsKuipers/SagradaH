package queries;

public class AccountQuery {
	StandardQuerie standardQuery;
	public AccountQuery(StandardQuerie standardQuery) {
		this.standardQuery = standardQuery;
	}
	
	public Boolean Login(String username, String password) {
		if(!username.isEmpty() && !password.isEmpty())
		if(!standardQuery.selectQuery("SELECT username, password FROM account", " WHERE username=? AND password=?", username+"\0"+password).isEmpty()) {
			return true;
		} else {
			return false;
		}
		return false;
	}
	
	public boolean register(String username, String password) {
		if(username.length() >= 3 && password.length() >= 3) {
			if(standardQuery.selectQuery("SELECT username FROM account", " WHERE username=?", username).isEmpty()) {
				//standardQuery.updateQuery("INSERT INTO account VALUES(?,?)", username+"\0"+password);
				System.out.println("new AC is ok!");
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
