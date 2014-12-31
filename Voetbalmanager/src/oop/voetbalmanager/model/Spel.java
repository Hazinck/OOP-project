package oop.voetbalmanager.model;

import java.util.ArrayList;

public class Spel {
	
	private Team userTeam;
	private Team botTeam;
	private int geluksfactor;
	
	/**
	 * @param userTeam
	 * @param tegenstander
	 * @param geluksfactor
	 */
	public Spel(Team userTeam, Team botTeam, int geluksfactor) {
		this.userTeam = userTeam;
		this.botTeam = botTeam;
		this.geluksfactor = geluksfactor;
	}
	
	public Team winner(){
		Team winner;
		
		int spelerUResult = 0;
		for(Speler s: userTeam.getSpelerList()){
			spelerUResult += spelerAnalyse(s);
		}
		
		int spelerBResult = 0;
		for(Speler s: botTeam.getSpelerList()){
			spelerBResult += spelerAnalyse(s);
		}
		
		int teamUserResult = teamAnalyse(userTeam, spelerUResult);
		int teamBotResult = teamAnalyse(botTeam, spelerBResult);
		
		System.out.println(teamUserResult + "  " + teamBotResult);
		
		if(teamUserResult > teamBotResult){
			winner = userTeam;
			if(geluksfactor > teamBotResult){
				winner  = botTeam;
			}
		}else if(teamUserResult < teamBotResult){
			winner = botTeam;
			if(geluksfactor > teamUserResult){
				winner  = userTeam;
			}
		}else{
			winner = null;
		}
		
		return winner;
	}
	
	public int spelerAnalyse(Speler speler){
		double result = 0;
		
		double o = speler.getOffense()*1.4;
		double d = speler.getDefence()*1.2;
		double u = speler.getUithouding()*1.8;
		
		result = (o+d+u)/3;
		
		return (int)result;
	}
	
	public int teamAnalyse(Team team, int spelerResult){
		double result = 0;
		
		double w = team.getWinst()*2;
		double g = team.getGelijkspel()*1.5;
		double v = team.getVerlies();
		double dv = team.getDoelvoor()*1.6;
		double dt = team.getDoeltegen()*1.3;
		
		result = (w + g - v + dv - dt + spelerResult)/6;
		
		return (int)result;
	}
	
	public ArrayList<String> verslag(){
		String uOpstelling="";
		String bOpstelling="";
		
		for(int i=0; i<11; i++){
			if(i==userTeam.getSpelerList().size()-1){
				uOpstelling += User.getWteam().getWSpelers()[i].getNaam() + ".";
				bOpstelling += Bot.getWteam().getWSpelers()[i].getNaam() + ".";
			}
			else{
				uOpstelling += User.getWteam().getWSpelers()[i].getNaam() + ", ";
				bOpstelling += Bot.getWteam().getWSpelers()[i].getNaam() + ", ";
			}
		}
		
		ArrayList<String> verslag = new ArrayList<String>();
		
		verslag.add("Welkom bij het liveverslag van "+userTeam.getNaam()+" thuis tegen "+botTeam.getNaam()+".");		
		verslag.add("De opstellingen van beide teams zijn bekend en volgen nu.");
		verslag.add("Opstelling "+userTeam.getNaam()+": "+uOpstelling);
		verslag.add("Opstelling "+botTeam.getNaam()+": "+bOpstelling);
		
		return verslag;
	}
	
}
