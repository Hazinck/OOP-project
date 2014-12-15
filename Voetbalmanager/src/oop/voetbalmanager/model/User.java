package oop.voetbalmanager.model;

public class User {
	private static String naam;
	private static Team team;
	
	public User(String naam, Team team){
		setNaam(naam);//this.naam = naam;
		setTeam(team);//this.team = team;
	}
	
	public String toString(){
		return "Naam:" + naam + "/nTeam:" + team.getNaam();
	}

	public static String getNaam() {
		return naam;
	}

	public static void setNaam(String naam) {
		User.naam = naam;
	}

	public static Team getTeam() {
		return team;
	}

	public static void setTeam(Team team) {
		User.team = team;
	}
	
	

}
