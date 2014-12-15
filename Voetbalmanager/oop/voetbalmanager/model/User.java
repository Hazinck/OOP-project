package oop.voetbalmanager.model;

public class User {
	private String naam;
	private String team;
	
	public User(String naam, String team){
		this.naam = naam;
		this.team = team;
	}
	
	public String toString(){
		return "Naam:" + naam + "/nTeam:" + team;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}
	
	

}
