package domain;

public class doctor extends user{
	String idDoctor;

	//constructor
	public doctor(String username, String fonction, String motdepasse, String idDoctor) {
		super(username, fonction, motdepasse);
		this.idDoctor = idDoctor;
	}
	
}
