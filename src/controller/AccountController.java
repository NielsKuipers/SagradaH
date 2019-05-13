package controller;

import main.GUI;
import model.Account;
import view.HomeScreen;
import view.LoginScreen;

public class AccountController {
	Account myaccount;
	GUI myGUI;
	HomeScreen homeScreen;
	LoginScreen loginScreen;
	
	
	public AccountController(GUI gui, DatabaseController DC) {
		this.myGUI = gui;
		homeScreen = new HomeScreen();
		loginScreen = new LoginScreen(gui);
		myaccount = new Account(DC);
	}
	
	public void login(String username, String password) {
		if(myaccount.login(username, password) == true) {
			System.out.println(username+""+password);
			myGUI.switchScreen(homeScreen);
			System.out.println("passed if in login");
		} else {
			loginScreen.emptyFields();
		}
	}
}
