package oop.voetbalmanager.model;

import java.awt.Dimension;
import java.util.ArrayList;

public class Spel {
	
	private Wedstrijdteam userTeam;
	private Wedstrijdteam botTeam;
	private int geluksfactor, score1, score2, teamUserResult, teamBotResult;
	private Dimension score = new Dimension();
	
	/**
	 * @param userTeam
	 * @param tegenstander
	 * @param geluksfactor
	 */
	public Spel(Wedstrijdteam userTeam, Wedstrijdteam botTeam, int geluksfactor) {
		this.userTeam = userTeam;
		this.botTeam = botTeam;
		this.geluksfactor = geluksfactor;
	}
	
	public Wedstrijdteam winner(int score1, int score2){
		Wedstrijdteam winner;
		
		this.score1 = score1;
		this.score2 = score2;
		int w, l;
		if(score1>score2){
			w = score1;
			l = score2;
		}else{
			w = score2;
			l = score1;
		}
		
		int spelerUResult = 0;
		for(Speler s: userTeam.getSpelerList()){
			spelerUResult += spelerAnalyse(s);
		}
		
		int spelerBResult = 0;
		for(Speler s: botTeam.getSpelerList()){
			spelerBResult += spelerAnalyse(s);
		}
		
		teamUserResult = teamAnalyse(userTeam, spelerUResult);
		teamBotResult = teamAnalyse(botTeam, spelerBResult);
		
		System.out.println(teamUserResult + "  " + teamBotResult);
		
		if(teamUserResult > teamBotResult){
			winner = userTeam;
			if(geluksfactor > teamBotResult){
				winner  = botTeam;
				score.setSize(l, w);
			}
		}else if(teamUserResult < teamBotResult){
			winner = botTeam;
			if(geluksfactor > teamUserResult){
				winner  = userTeam;
				score.setSize(l, w);
			}
		}else{
			winner = null;
			score.setSize(w, w);
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
	
	public int teamAnalyse(Wedstrijdteam team, int spelerResult){
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

	/**
	 * @return the score
	 */
	public Dimension getScore() {
		return score;
	}

	/**
	 * @return the geluksfactor
	 */
	public int getGeluksfactor() {
		return geluksfactor;
	}

	
	
}
