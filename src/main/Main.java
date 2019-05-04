package main;


import controller.DatabaseController;

import java.util.ArrayList;

public class Main{

	public static void main(String[] args) {
//		new GUI().startup(args);
		DatabaseController h = new DatabaseController();
		ArrayList r = h.selectQuery("SELECT * FROM player", "", "");
		for(Object v : r){
			System.out.println(v);
		}
	}

	

}
