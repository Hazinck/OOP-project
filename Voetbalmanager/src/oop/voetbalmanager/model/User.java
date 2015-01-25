package oop.voetbalmanager.model;

public class User {
	private static String naam;
	private static Team team;
	private static Wedstrijdteam wteam;
	
	public User(String naam, Team team){
		setNaam(naam);
		setTeam(team);
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

	/**
	 * @return the wteam
	 */
	public static Wedstrijdteam getWteam() {
		return wteam;
	}

	/**
	 * @param wteam the wteam to set
	 */
	public static void setWteam(Wedstrijdteam wteam) {
		User.wteam = wteam;
	}
	
	

}
