package controller;

import main.GUI;
import model.Account;
import view.HomePane;
import view.LoginScreen;
import view.RegisterScreen;

public class AccountController {
	Account myaccount;
	GUI myGUI;
	HomePane homePane;
	LoginScreen loginScreen;
	RegisterScreen registerScreen;
	
	public AccountController(GUI gui, DatabaseController DC, RegisterScreen RS, LoginScreen LS) {
		this.myGUI = gui;
		homePane = new HomePane();
		this.loginScreen = LS;
		this.registerScreen = RS;
		myaccount = new Account(DC);
	}
	
	public void login(String username, String password) {
		if(myaccount.login(username, password) == true) {
			System.out.println(username+""+password);
			myGUI.changePane(homePane);
			//System.out.println("passed if in login");
		} else {
			//System.out.println("passed else in login");
			loginScreen.emptyFields();
		}
	}
	public void register(String username, String password) {
		if(myaccount.register(username, password) == true) {
			registerScreen.setGreenBorder();
		} else {
			registerScreen.setRedBorder();
		}
	}
	
	//borderstuff does not wanne work for me will fix it.
}
