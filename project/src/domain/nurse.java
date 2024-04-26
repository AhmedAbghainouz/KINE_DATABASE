package domain;

public class nurse extends user{

	String idNurse;
	
	//constructor 
	public nurse(String username, String fonction, String motdepasse, String nurseId) {
		super(username, fonction, motdepasse);
		this.idNurse = idNurse;
	}
	
	
}
