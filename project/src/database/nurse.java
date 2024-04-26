package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import domain.client;

public class nurse {
	

	//class connection to database
	static Connection c;
	static Statement ss;
	static String query;
	static ResultSet r;
	
		
	static Connect sec = new Connect();


	


	//add client function 
	public void addClient(String clientCIN, String Nom, String Prenom, int Telephone, String sexe, String dateNaissance) {
	    try {
	        Connection conn = sec.connect();
	        PreparedStatement p = conn.prepareStatement("INSERT INTO client VALUES (?, ?, ?, ?, ?, ?)");
	        p.setString(1, clientCIN);
	        p.setString(2, Nom);
	        p.setString(3, Prenom);
	        p.setInt(4, Telephone);
	        p.setString(5, sexe);
	        p.setString(6, dateNaissance);
	        
	        int rowsAffected = p.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Client with ID " + clientCIN + " added successfully.");
	        } else {
	            System.out.println("Failed to add client with ID " + clientCIN + ".");
	        }
	        
	        conn.close(); // Close connection
	        
	    } catch(SQLException ee) {
	        if (ee.getMessage().contains("UNIQUE constraint failed")) {
	            System.err.println("Client with ID " + clientCIN + " already exists.");
	        } else {
	            System.out.println("Error: " + ee.getMessage());
	        }
	    }
	}

	
	//edit client 
	public void editClient(String clientCIN, String newNom, String newPrenom, int newTelephone, String newSexe, String newDateNaissance) {
	    try {
	        Connection conn = sec.connect();
	        PreparedStatement p = conn.prepareStatement("UPDATE client SET Nom = ?, Prenom = ?, Telephone = ?, sexe = ?, dateNaissance = ? WHERE clientCIN = ?");
	        p.setString(1, newNom);
	        p.setString(2, newPrenom);
	        p.setInt(3, newTelephone);
	        p.setString(4, newSexe);
	        p.setString(5, newDateNaissance);
	        p.setString(6, clientCIN);
	        
	        // Execute the update
	        p.executeUpdate();
	        
	        // Close the connection
	        conn.close();
	        
	        System.out.println("Client with ID " + clientCIN + " updated successfully.");
	        
	    } catch(SQLException ee) {
	        if (ee.getMessage().contains("UNIQUE constraint failed")) {
	            System.err.println("Client with ID " + clientCIN + " already exists.");
	        } else {
	            System.out.println("Error: " + ee.getMessage());
	        }
	    }
	}

	
	//get client function gets all users in client table 
	public ArrayList<client> getAllClients() {
	    ArrayList<client> clients = new ArrayList<>();
	    try {
	        Connection conn = sec.connect();
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM client");
	        
	        while (rs.next()) {
	            String clientCIN = rs.getString("clientCIN");
	            String Nom = rs.getString("Nom");
	            String Prenom = rs.getString("Prenom");
	            int Telephone = rs.getInt("Telephone");
	            String sexe = rs.getString("sexe");
	            String dateNaissance = rs.getString("dateNaissance");
	            
	            System.out.println("Client ID: " + clientCIN);
	            System.out.println("Nom: " + Nom);
	            System.out.println("Prenom: " + Prenom);
	            System.out.println("Telephone: " + Telephone);
	            System.out.println("Sexe: " + sexe);
	            System.out.println("Date de Naissance: " + dateNaissance);
	            System.out.println("----------------------");
	            
	            
	            client client = new client(clientCIN, Nom, Prenom, Telephone, sexe, dateNaissance);
	            clients.add(client);
	        }
	        
	        conn.close(); // Close connection
	        
	    } catch(SQLException ee) {
	        System.out.println("Error: " + ee.getMessage());
	    }
	    return clients;
	}
	
	/*
	//checks if client is in the table 
	public static boolean checkClient(String Nom) throws SQLException{
		ArrayList<client> array = getClients();
		for(int i=0;i<array.size();i++) {
			if (array.get(i).getNom().equalsIgnoreCase(Nom)) {
				return true ;
			}
		}
		return false;
		
	}*/
	
	//add rendez vous 
	public void ajouterRendezVous(String date, String heure, String clientCIN, String idNurse) {
	    try {
	        Connection conn = sec.connect();
	        PreparedStatement p = conn.prepareStatement("INSERT INTO RDV (date, heure, clientCIN, idNurse) VALUES (?, ?, ?, ?)");
	        p.setString(1, date);
	        p.setString(2, heure);
	        p.setString(3, clientCIN);
	        p.setString(4, idNurse);
	        
	        int rowsAffected = p.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("RDV added successfully.");
	        } else {
	            System.out.println("Failed to add RDV.");
	        }
	        
	        conn.close(); // Close connection
	        
	    } catch(SQLException ee) {
	        System.out.println("Error: " + ee.getMessage());
	    }
	}
	
	//delete rendezvous 
	public void deleteRDV(int id_RDV) {
		try {
			Connection conn = sec.connect();
			PreparedStatement p = conn.prepareStatement("DELETE FROM RDV WHERE id_RDV = ?");
	        p.setInt(1, id_RDV);
	        
	        
	        //cheking the execution
	        int rowsAffected = p.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Rendezvous with ID =" + id_RDV + " deleted successfully.");
	        } else {
	            System.out.println("Rendezvous with ID =" + id_RDV + " not found.");
	        }
			
			
	        conn.close(); // Close connection
			}catch(SQLException ee ){
				System.out.println(ee.getMessage());
			}		
	}
	
	//edit RDV 
	public void editRendezVous(int id_rendezvous, String date, String heure, String clientCIN, String idNurse) {
	    try {
	        Connection conn = sec.connect();
	        PreparedStatement p = conn.prepareStatement("UPDATE RDV SET date = ?, heure = ?, clientCIN = ?, idNurse = ? WHERE id_rendezvous = ?");
	        p.setString(1, date);
	        p.setString(2, heure);
	        p.setString(3, clientCIN);
	        p.setString(4, idNurse);
	        p.setInt(5, id_rendezvous);
	        
	        int rowsAffected = p.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Rendezvous with ID " + id_rendezvous + " updated successfully.");
	        } else {
	            System.out.println("Rendezvous with ID " + id_rendezvous + " not found.");
	        }
	        
	        conn.close(); // Close connection
	        
	    } catch(SQLException ee) {
	        System.out.println("Error: " + ee.getMessage());
	    }
	}
	
	
	//get client by char 
	public void getClientsByCharacter(String character) {
	    try {
	        Connection conn = sec.connect();
	        PreparedStatement p = conn.prepareStatement("SELECT * FROM client WHERE clientCIN LIKE ? OR Nom LIKE ? OR Prenom LIKE ?");
	        p.setString(1, "%" + character + "%");
	        p.setString(2, "%" + character + "%");
	        p.setString(3, "%" + character + "%");
	        
	        ResultSet rs = p.executeQuery();
	        
	        while (rs.next()) {
	            String clientCIN = rs.getString("clientCIN");
	            String Nom = rs.getString("Nom");
	            String Prenom = rs.getString("Prenom");
	            int Telephone = rs.getInt("Telephone");
	            String sexe = rs.getString("sexe");
	            String dateNaissance = rs.getString("dateNaissance");
	            
	            System.out.println("Client ID: " + clientCIN);
	            System.out.println("Nom: " + Nom);
	            System.out.println("Prenom: " + Prenom);
	            System.out.println("Telephone: " + Telephone);
	            System.out.println("Sexe: " + sexe);
	            System.out.println("Date de Naissance: " + dateNaissance);
	            System.out.println("----------------------");
	        }
	     
	        
	        conn.close(); // Close connection
	        
	    } catch(SQLException ee) {
	        System.out.println("Error: " + ee.getMessage());
	    }
	}
	
}
