package oop.voetbalmanager.model;

public class Bot {
	
	private static Divisie divisie;
	private static Team userTeam;
	private static Team botTeam;
	
	
	/**
	 * Geeft een willekeurige tegenstander
	 * @param divisie	de huidige divisie
	 * @param userTeam	het team van de user
	 * @return			een willekeurige tegenstander
	 */
	public static void volgendeTeam() {//Team 
		int aantalTeams = 18;
		Team tegenstander = userTeam;
		boolean gevonden = false;
		
		//zolang een tegenstander niet is gevonden, zoek voor een tegenstander
		while (!gevonden) {
			//neem een willekeurig team
			int i = RNG.getalTot(aantalTeams);// + 1;
			tegenstander = divisie.getTeamList().get(i);
			
			//als dit niet het team van de user is, hebben we een tegenstander gevonden
			gevonden = !(tegenstander.equals(userTeam));
		}
		
		botTeam = tegenstander;
		//return tegenstander;
	}


	/**
	 * @return the divisie
	 */
	public static Divisie getDivisie() {
		return divisie;
	}


	/**
	 * @param divisie the divisie to set
	 */
	public static void setDivisie(Divisie divisie) {
		Bot.divisie = divisie;
	}


	/**
	 * @return the userTeam
	 */
	public static Team getUserTeam() {
		return userTeam;
	}


	/**
	 * @param userTeam the userTeam to set
	 */
	public static void setUserTeam(Team userTeam) {
		Bot.userTeam = userTeam;
	}


	/**
	 * @return the botTeam
	 */
	public static Team getBotTeam() {
		return botTeam;
	}
}
