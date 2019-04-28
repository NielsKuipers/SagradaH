package main;


import controller.DatabaseController;

import java.util.ArrayList;

public class Main{

	public static void main(String[] args) {
//		new GUI().startup(args);
		DatabaseController h = new DatabaseController();
		ArrayList g = h.selectQuery("SELECT * FROM account ", "", "");
		for (Object o : g){
			System.out.println(o);
		}
	}

	

}
