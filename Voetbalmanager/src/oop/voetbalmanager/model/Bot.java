package oop.voetbalmanager.model;

import java.util.ArrayList;

public class Bot {
	
	private static Divisie divisie;
	private static Team userTeam;
	private static Team botTeam;
	private static Wedstrijdteam wteam;
	private static boolean gameOver = false;
	
	public static void teamToWTeam(ArrayList<Opstelling> opstellingen, int opIdx, int tactiek){
		wteam = new Wedstrijdteam(botTeam);
		Opstelling opstelling = opstellingen.get(opIdx);		
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
	}
	
	
	public static void volgendeTeam() {
		int speeldag = Divisie.getSpeeldag();
		Team oldBotTeam = botTeam;
		for(Team t: Divisie.getTeamList()){
			if(!User.getWteam().getGespeeldMet().contains(t.getNaam()+"2") && !t.equals(User.getTeam())){//speeldag<Divisie.getTeamList().size()
				botTeam = t;
				System.out.println("bot: " + t.getNaam());

				break;
			}
		}
		if(botTeam == null || User.getWteam().getGespeeldMet().contains(botTeam.getNaam()+"2")){//(oldBotTeam != null && oldBotTeam.equals(botTeam))){
			gameOver = true;
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

	/**
	 * @return the gameOver
	 */
	public static boolean isGameOver() {
		return gameOver;
	}

	/**
	 * @param gameOver the gameOver to set
	 */
	public static void setGameOver(boolean gameOver) {
		Bot.gameOver = gameOver;
	}
}
