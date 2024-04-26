package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import domain.client;

public class doctor {
	static Scanner clavier = new Scanner(System.in);
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
	//delete client function
		public void deleteClient(String clientCIN) {
		   	try {
		        Connection conn = sec.connect();
		        PreparedStatement p = conn.prepareStatement("DELETE FROM client WHERE clientCIN = ?");
		        p.setString(1, clientCIN);
		        
		        int rowsAffected = p.executeUpdate();
		        if (rowsAffected > 0) {
		            System.out.println("Client with ID " + clientCIN + " deleted successfully.");
		        } else {
		            System.out.println("Client with ID " + clientCIN + " not found.");
		        }
		        
		        conn.close(); // Close connection
		        
		    } catch(SQLException ee) {
		        System.out.println("Error: " + ee.getMessage());
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
		            
		            client client = new client(clientCIN, Nom, Prenom, Telephone, sexe, dateNaissance);
		            clients.add(client);
		        }
		        
		        conn.close(); // Close connection
		        
		    } catch(SQLException ee) {
		        System.out.println("Error: " + ee.getMessage());
		    }
		    return clients;
		}
		
	//get client by character 
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
		
	
		
	//add nurse function 
		public  void addNurse(String idNurse,String userName, String fonction ,String mdp) throws SQLException {
			try {
			Connection conn = sec.connect();
			PreparedStatement p = conn.prepareStatement("insert into nurse values(?,?,?,?)");
			p.setString(1, idNurse);
			p.setString(2, userName);
			p.setString(3, fonction);
			p.setString(4, mdp);
			
			
			//cheking the execution
			int rowsAffected = p.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Client with ID " + idNurse + " added successfully.");
	        } else {
	            System.out.println("Failed to add client with ID " + idNurse + ".");
	        }        
	       
	        conn.close(); // Close connection
			}catch(SQLException ee ){
				if (ee.getMessage().contains("UNIQUE constraint failed")) {
		            System.err.println("Nurse with ID " + idNurse + " already exists.");
		        } else {
		            System.out.println("Error: " + ee.getMessage());
		        }
			}
			}
		
	//delete Nurse function
		public void deleteNurse(String idNurse) {
			    try {
			        Connection conn = sec.connect();
			        PreparedStatement p = conn.prepareStatement("DELETE FROM Nurse WHERE idNurse = ?");
			        p.setString(1, idNurse);
			        
			        int rowsAffected = p.executeUpdate();
			        if (rowsAffected > 0) {
			            System.out.println("Nurse with ID " + idNurse + " deleted successfully.");
			        } else {
			            System.out.println("Nurse with ID " + idNurse + " not found.");
			        }
			        
			        conn.close(); // Close connection
			        
			    } catch(SQLException ee) {
			        System.out.println("Error: " + ee.getMessage());
			    }
			}

	//edit Nurse
		public void editNurse(String idNurse, String newUsername, String newfonction, String newpassword) {
		    try {
		        Connection conn = sec.connect();
		        PreparedStatement p = conn.prepareStatement("UPDATE client SET username = ?, fonction = ?, password = ? WHERE clientCIN = ?");
		        p.setString(1, newUsername);
		        p.setString(2, newfonction);
		        p.setString(3, newpassword);
		        p.setString(4, idNurse);
		        
		        // Execute the update
		        p.executeUpdate();
		        
		        // Close the connection
		        conn.close();
		        
		        System.out.println("Client with ID " + idNurse + " updated successfully.");
		        
		    } catch(SQLException ee) {
		        if (ee.getMessage().contains("UNIQUE constraint failed")) {
		            System.err.println("Client with ID " + idNurse + " already exists.");
		        } else {
		            System.out.println("Error: " + ee.getMessage());
		        }
		    }
		}

	//get all nurses 
		public void getAllNurses() {
		    try {
		        Connection conn = sec.connect();
		        Statement stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery("SELECT * FROM nurse");
		        
		        while (rs.next()) {
		            String idNurse = rs.getString("idNurse");
		            String username = rs.getString("userName");
		            String fonction = rs.getString("fonction");
		            String motdepasse = rs.getString("mdp");

		            
		            System.out.println("idNurse: " + idNurse);
		            System.out.println("USERNAME: " + username);
		            System.out.println("fonction: " + fonction);
		            System.out.println("motdepasse : " + motdepasse);
		            System.out.println("----------------------");
		        }
		        
		        conn.close(); // Close connection
		        
		    } catch(SQLException ee) {
		        System.out.println("Error: " + ee.getMessage());
		    }
		}

		
		//get nurse by char 
		public void getnursesByCharacter(String character) {
		    try {
		        Connection conn = sec.connect();
		        PreparedStatement p = conn.prepareStatement("SELECT * FROM nurse WHERE idNurse LIKE ? OR userName LIKE ? OR fonction LIKE ?");
		        p.setString(1, "%" + character + "%");
		        p.setString(2, "%" + character + "%");
		        p.setString(3, "%" + character + "%");
		        
		        ResultSet rs = p.executeQuery();
		        
		        while (rs.next()) {
		            String idNurse = rs.getString("idNurse");
		            String username = rs.getString("userName");
		            String fonction = rs.getString("fonction");
		            String mdp = rs.getString("mdp");
		            
		            System.out.println("nurse ID: " + idNurse);
		            System.out.println("username : " + username);
		            System.out.println("fonction: " + fonction);
		            System.out.println("motdepasse : " + mdp);
		            System.out.println("----------------------");
		        }
		        
		        conn.close(); // Close connection
		        
		    } catch(SQLException ee) {
		        System.out.println("Error: " + ee.getMessage());
		    }
		}
		
		
		
		
		
		
	//add act_medical 
		public void addAct_medical(String idMedecin, String clientCIN, String datededebut, String datedefin) {
		    try {
		        Connection conn = sec.connect();
		        PreparedStatement p = conn.prepareStatement("INSERT INTO act_medical (idMedecin, clientCIN, DDF, DFI) VALUES (?, ?, ?, ?)");
		        p.setString(1, idMedecin);
		        p.setString(2, clientCIN);
		        p.setString(3, datededebut);
		        p.setString(4, datedefin);
		        
		        int rowsAffected = p.executeUpdate();
		        if (rowsAffected > 0) {
		            System.out.println("Medical act added successfully.");
		        } else {
		            System.out.println("Failed to add medical act.");
		        }
		        
		        conn.close(); // Close connection
		        
		    } catch(SQLException ee) {
		        System.out.println("Error: " + ee.getMessage());
		    }
		}

		
	//edit act_medical
		public void editMedicalAct(int id_actmedical, String datedefin, String datededebut, String clientCIN, String idDoctor) {
		    try {
		        Connection conn = sec.connect();
		        PreparedStatement p = conn.prepareStatement("UPDATE act_medical SET datedefin = ?, datededebut = ?, clientCIN = ?, idDoctor = ? WHERE id_actmedical = ?");
		        p.setString(1, datedefin);
		        p.setString(2, datededebut);
		        p.setString(3, clientCIN);
		        p.setString(4, idDoctor);
		        p.setInt(5, id_actmedical);
		        
		        int rowsAffected = p.executeUpdate();
		        if (rowsAffected > 0) {
		            System.out.println("Medical Act with ID " + id_actmedical + " updated successfully.");
		        } else {
		            System.out.println("Medical Act with ID " + id_actmedical + " not found.");
		        }
		        
		        conn.close(); // Close connection
		        
		    } catch(SQLException ee) {
		        System.out.println("Error: " + ee.getMessage());
		    }
		}
		
	//delete act_medical 
		public void deleteMedicalAct(int id_actmedical) {
	    try {
	        Connection conn = sec.connect();
	        PreparedStatement p = conn.prepareStatement("DELETE FROM act_medical WHERE id_actmedical = ?");
	        p.setInt(1, id_actmedical);
	        
	        int rowsAffected = p.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Medical Act with ID " + id_actmedical + " deleted successfully.");
	        } else {
	            System.out.println("Medical Act with ID " + id_actmedical + " not found.");
	        }
	        
	        conn.close(); // Close connection
	        
	    } catch(SQLException ee) {
	        System.out.println("Error: " + ee.getMessage());
	    }
	}

	//get all act_medical
		public void getAllMedicalActs() {
		    try {
		        Connection conn = sec.connect();
		        Statement stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery("SELECT * FROM act_medical");
		        
		        while (rs.next()) {
		            int id_actmedical = rs.getInt("id_actmedical");
		            String DDF = rs.getString("DDF");
		            String DFI = rs.getString("DFI");
		            String clientCIN = rs.getString("clientCIN");
		            String id_Medecin = rs.getString("idMedecin");
		            
		            System.out.println("ID: " + id_actmedical);
		            System.out.println("Date Défin: " + DDF);
		            System.out.println("Date Début: " + DFI);
		            System.out.println("Client CIN: " + clientCIN);
		            System.out.println("Doctor ID: " + id_Medecin);
		            System.out.println("----------------------");
		        }
		        
		        conn.close(); // Close connection
		        
		    } catch(SQLException ee) {
		        System.out.println("Error: " + ee.getMessage());
		    }
		}

	//get act by char 
		public void getMedicalActsByChar(String character) {
		    try {
		        Connection conn = sec.connect();
		        PreparedStatement p = conn.prepareStatement("SELECT * FROM act_medical WHERE clientCIN LIKE ? OR idMedecin LIKE ? OR DFI LIKE ? OR DDF LIKE ?");
		        p.setString(1, "%" + character + "%");
		        p.setString(2, "%" + character + "%");
		        p.setString(3, "%" + character + "%");
		        p.setString(4, "%" + character + "%");
		        
		        ResultSet rs = p.executeQuery();
		        
		        while (rs.next()) {
		            int id_actmedical = rs.getInt("id_actmedical");
		            String DDF = rs.getString("DDF");
		            String DFI = rs.getString("DFI");
		            String clientCIN = rs.getString("clientCIN");
		            String idMedecin = rs.getString("idMedecin");
		            
		            System.out.println("ID: " + id_actmedical);
		            System.out.println("Date Défin: " + DDF);
		            System.out.println("Date Début: " + DFI);
		            System.out.println("Client CIN: " + clientCIN);
		            System.out.println("Doctor ID: " + idMedecin);
		            System.out.println("----------------------");
		        }
		        
		        conn.close(); // Close connection
		        
		    } catch(SQLException ee) {
		        System.out.println("Error: " + ee.getMessage());
		    }
		}

	
}
