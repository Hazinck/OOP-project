package oop.voetbalmanager.model;

import java.util.ArrayList;

public class Bot {
	
	private static Divisie divisie;
	private static Team userTeam;
	private static Team botTeam;
	private static Wedstrijdteam wteam;
	
	
	public static void teamToWTeam(ArrayList<Opstelling> opstellingen, int opIdx, int tactiek){
		wteam = new Wedstrijdteam(botTeam);
		
		//int opIdx = RNG.getalTot(opstellingen.size());
		Opstelling opstelling = opstellingen.get(opIdx);
		
		//int tactiek = RNG.getalTot(101);
		
		ArrayList<Speler> wSpelerList = new ArrayList<Speler>();
		
		for(Speler s: botTeam.getSpelerList()){
			if(s.getType().equals("doelman")){
				wteam.getWSpelers()[0] = s;
				break;
			}
		}
		for(int i = 1; i < wteam.getWSpelers().length; i++){
			wteam.getWSpelers()[i] = botTeam.getSpelerList().get(i);
		}
		
		wteam.setOpstelling(opstelling);
		wteam.setTactiek(tactiek);
	//	System.out.println("Bot wedstrijdteam: " + wteam.toString());
	}
	
	/**
	 * Geeft een willekeurige tegenstander
	 */
//	public static void volgendeTeam() {//Team 
//		int aantalTeams = 18;
//		Team tegenstander = userTeam;
//		boolean gevonden = false;
//		
//		//zolang een tegenstander niet is gevonden, zoek voor een tegenstander
//		while (!gevonden) {
//			//neem een willekeurig team
//			int i = RNG.getalTot(aantalTeams);// + 1; 
//			tegenstander = divisie.getTeamList().get(i);
//			
//			//als dit niet het team van de user is, hebben we een tegenstander gevonden
//			gevonden = !(tegenstander.equals(userTeam));
//		}
//		
//		botTeam = tegenstander;
//		//return tegenstander;
//	}
	public static void volgendeTeam() {
		int speeldag = Divisie.getSpeeldag();
		for(Team t: Divisie.getTeamList()){
//			Team temp = Divisie.getTeamList().get(speeldag);
			if(!User.getWteam().getGespeeldMet().contains(t.getNaam()+"2") && !t.equals(User.getTeam())){//speeldag<Divisie.getTeamList().size()
				botTeam = t;
				System.out.println("bot: " + t.getNaam());
				break;
			}
		}
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
	
	public static void setBotTeam(Team botTeam){
		Bot.botTeam=botTeam;
	}

	/**
	 * @return the wteam
	 */
	public static Wedstrijdteam getWteam() {
		return wteam;
	}
	
	public static void setWteam(Wedstrijdteam wteam){
		Bot.wteam=wteam;
	}
}
