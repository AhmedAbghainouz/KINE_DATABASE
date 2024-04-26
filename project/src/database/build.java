package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.Scanner;

public class build {
	static Connection c;
	static Statement ss;
	static String query;
	static ResultSet r;
	public static void main(String[] args) throws SQLException {
		Connect sec = new Connect();
		try {
			c = sec.connect();
			nurse nrs = new nurse();
			doctor doc1 = new doctor();

	
	nrs.addClient("f1111","younesse","younesse",3328,"elhouda","elhouda");
	doc1.addNurse("f2222", "malika", "malika", "agadir");
	nrs.ajouterRendezVous("24-03-20111","12:00","i11","f22");
	doc1.addAct_medical("e555", "f1111", "date debut", "date fin");
	
	System.out.println();
	System.out.println("----------------------\r\n"
			+ "");

	nrs.getClientsByCharacter("youb");

	//doc1.getAllMedicalActs();
	
	//doc1.getMedicalActsByChar("date");
	//doc1.getnursesByCharacter("");
	//doc1.getAllNurses();
	
	String login = acceuil.login("malika","agadir");
	
	System.out.println(login);
	
	
		}catch(SQLException ee) {
			System.out.println(ee.getMessage());
		}
	}
	
}


