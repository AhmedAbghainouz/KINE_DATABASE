package domain;

public class client {
	

	private int Telephone; 
	private String clientCIN,Nom, Prenom,Sexe,dateNaissance ;
	
	//constructor 
	public client(String clientCIN,  String nom, String prenom,int telephone, String sexe, String dateNaissance) {
		this.clientCIN = clientCIN;
		Telephone = telephone;
		Nom = nom;
		Prenom = prenom;
		Sexe = sexe;
		this.dateNaissance = dateNaissance;
	}
}
