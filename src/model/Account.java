package model;

import controller.DatabaseController;
import queries.AccountQuery;

public class Account {
	private AccountQuery accountQuery;
	public Account(DatabaseController DC) {
		this.accountQuery = DC.getAccountQuery();
	}
	
	public boolean login(String username, String password) {
		return accountQuery.Login(username, password);
	}
	
	public boolean register(String username, String password) {
		return accountQuery.register(username, password);
	}
}
