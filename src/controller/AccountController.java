package controller;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.GUI;
import model.Account;
import view.HomePane;
import view.StartPane;

public class AccountController {
	Account myaccount;
	GUI myGUI;
	HomePane homePane;
	StartPane startpane;
	String accountname;
	
	public AccountController(GUI gui, DatabaseController DC, HomePane HP, StartPane SP) {
		this.myGUI = gui;
		this.homePane = HP;
		this.startpane = SP;
		myaccount = new Account(DC);
	}
	
	public void login(TextField username, PasswordField password) {
		if(myaccount.login(username.getText(), password.getText()) == true) {
			System.out.println(username+""+password);
			myGUI.changePane(homePane);
			setAccount(username.getText());
			startpane.getLog().emptyFields();
			//System.out.println("passed if in login");
		} else {
			//System.out.println("passed else in login");
			startpane.getLog().badFields(username, password);
		}
	}
	public void register(TextField username, PasswordField password) {
		if(myaccount.register(username.getText(), password.getText()) == true) {
			startpane.getReg().setGreenBorder(username, password);
		} else {
			startpane.getReg().setRedBorder(username, password);
		}
	}
	
	public void uitloggen() {
		myGUI.changePane(startpane);
		setAccount(null);
	}
	
	public void setAccount(String AC) {
		this.accountname = AC;
	}
	
	public String getAccount() {
		return accountname;
	}
}
