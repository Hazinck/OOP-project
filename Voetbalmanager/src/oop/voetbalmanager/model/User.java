package oop.voetbalmanager.model;

public class User {
	private static String naam;
	private static String team;
	
	public User(String naam, String team){
		setNaam(naam);//this.naam = naam;
		setTeam(team);//this.team = team;
	}
	
	public String toString(){
		return "Naam:" + naam + "/nTeam:" + "team";
	}

	public static String getNaam() {
		return naam;
	}

	public static void setNaam(String naam) {
		User.naam = naam;
	}

	public static String getTeam() {
		return team;
	}

	public static void setTeam(String team) {
		User.team = team;
	}
	
	

}
