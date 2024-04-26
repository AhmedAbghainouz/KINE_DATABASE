package database;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import domain.client;

public class acceuil{
			static Connection c;
	    	static Statement ss;
	    	static String query;
	    	static ResultSet r;
	    	static Connect sec = new Connect();

	    	
	public static String login(String username, String password) {
	    try {
	    	Connection conn = sec.connect();
	        String fonction = "";
	        
	        
	        PreparedStatement p = conn.prepareStatement("SELECT * FROM medecin WHERE userName = ? AND mdp = ?");
	        p.setString(1, username);
	        p.setString(2, password);
	        
	        PreparedStatement p2 = conn.prepareStatement("SELECT * FROM nurse WHERE userName = ? AND mdp = ?");
	        p2.setString(1, username);
	        p2.setString(2, password);
	        
	        ResultSet rs = p.executeQuery();
	        
	        ResultSet rs2 = p2.executeQuery();

	        
	    
	        if (rs.next()) {
	        	fonction = rs.getString("fonction");
	        	
	        	return fonction ; 
	        }
	        else if (rs2.next()){
	        	fonction = rs2.getString("fonction");
	        	return fonction ; 
	        	
	        }
	        else {
	            return "Invalid username or password.";
	        }
	        
	    } catch(SQLException ee) {
	        return "Error: " + ee.getMessage();
	    }
	}

}
