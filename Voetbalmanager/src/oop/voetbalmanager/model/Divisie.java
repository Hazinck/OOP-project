package oop.voetbalmanager.model;


import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Divisie {
	private String naam;
	private static ArrayList<Team> teamList = new ArrayList<Team>();
	private static int speeldag;
	private int stand;
	private static String avatarPath;
	private static ArrayList<Team> teamsGespeeld = new ArrayList<Team>();
	private static String skipVerslag="";
	
	public Divisie(String naam, ArrayList<Team> teamList, int speeldag, int stand, String avatarPath){
		this.naam = naam;
		Divisie.teamList = teamList;
		Divisie.speeldag = speeldag;
		this.stand =stand;
		this.avatarPath = avatarPath;
	}
	
	public String toString(){
		return  "Divisie\nNaam:" + naam + 
				"\nTeams:\n" + teamList.toString() + "\nSpeeldag:"+speeldag + "\nStand:"+stand;
	}

	
	/**
	 * 
	 * @param gewonnen aantal gewonnen wedstrijden
	 * @param gelijkspel aantal gelijkgespeelde wedstrijden
	 * @return Score voor ranking
	 */
	public static void rekenPunten(Team team){
	//	System.out.println(team.getNaam()+": "+team.getWinst());
		int score = team.getWinst()*3 + team.getGelijkspel();
		team.setScore(score);
	}
	
	public static void rankTeams(){
		for(Team t: teamList){
			rekenPunten(t);
		//	System.out.println(t.getNaam()+": "+t.getScore());
		}
		//System.out.println("\n=========================");
		Collections.sort(teamList, new Comparator<Team>() {
		    public int compare(Team t1, Team t2) {
		    	Integer t1score = t1.getScore();
		    	Integer t2score = t2.getScore();
		    	if(t1score!=t2score){
		    		return t1score.compareTo(t2score);
		    	}
		    	else{
		    		Integer t1ds = t1.getDoelsaldo();
			    	Integer t2ds = t2.getDoelsaldo();
		    		return t1ds.compareTo(t2ds);
		    	}
		    }
		});
		Collections.reverse(teamList);
		int i = 1;
		for(Team t: teamList){
			t.setRank(i);
			//System.out.println(t.getNaam()+": "+t.getScore());
			i++;
		}
	}
	
	public static void rekenDoelpunten(Dimension score, int team12, Wedstrijdteam team){
		int voor, tegen;
		if(team12 == 1){
			voor =  (int)score.getWidth();
			tegen =  (int)score.getHeight();
		}else{
			voor = (int)score.getHeight();
			tegen = (int)score.getWidth();
		}

		System.out.println("\nDivisie: score" + score.toString());
		
		if(voor > tegen){
			System.out.println("Divisie: " + team.getNaam() + " " + team.getWinst());
			team.setWinst(team.getWinst() + 1);
			System.out.println("Divisie: " + team.getNaam() + " " + team.getWinst());
			skipVerslag = team.getNaam() + " heeft gewonnen met: " + (int)score.getWidth() + "-" + (int)score.getHeight();
		}else if(voor < tegen){
			team.setVerlies(team.getVerlies() + 1);
		}else{
			team.setGelijkspel(team.getGelijkspel() + 1);
			skipVerslag = "Het is afgelopen: " + (int)score.getWidth() + "-" + (int)score.getHeight();
		}
		team.setDoelvoor(team.getDoelvoor() + voor);
		team.setDoeltegen(team.getDoeltegen() + tegen);
		team.setDoelsaldo(team.getDoelvoor() - team.getDoeltegen());
	}
	
	
	public static void teamsToDiv(Team team1, Team team2){
		for(int i = 0; i < teamList.size(); i++){
		//	System.out.println("Divisie: team toDiv old " + t.getNaam() + " " + t.getWinst());
			if(teamList.get(i).getNaam().equals(team1.getNaam())){
				teamList.set(i, team1) ;
//				System.out.println("\nDivisie: team toDiv new " + teamList.get(i).getNaam() + " " + teamList.get(i).getWinst());
			}else if(teamList.get(i).getNaam().equals(team2.getNaam())){
				teamList.set(i, team2);
//				System.out.println("\nDivisie: team toDiv new " + teamList.get(i).getNaam() + " " + teamList.get(i).getWinst());
			}
		}
	}
	
	public static Team findTeamByName(String teamNaam){
		Team team = null;
		for(Team t: teamList){
			if(t.getNaam().equals(teamNaam)){
			System.out.println(t.getNaam()+"\n"+teamNaam);
				team = t;
				break;
			}
		}
		return team;
	}
	
	/**
	 * @return the naam
	 */
	public String getNaam() {
		return naam;
	}
	public static int getSpeeldag() {
		return speeldag;
	}
	public static ArrayList<Team> getTeamList(){
		return teamList;
	}

	/**
	 * @param naam the naam to set
	 */
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public static void setSpeeldag(int speeldag) {
		Divisie.speeldag = speeldag;
	}

	public int getStand() {
		return stand;
	}

	public void setStand(int stand) {
		this.stand = stand;
	}
	public static void setTeamList(ArrayList<Team> nieuw){
		teamList=nieuw;
	}
	
	
	/**
	 * @return the teamsGespeeld
	 */
	public static ArrayList<Team> getTeamsGespeeld() {
		return teamsGespeeld;
	}

	/**
	 * @return the skipVerslag
	 */
	public static String getSkipVerslag() {
		return skipVerslag;
	}

	/**
	 * @return the avatarPath
	 */
	public static String getAvatarPath() {
		return avatarPath;
	}

	/**
	 * @param avatarPath the avatarPath to set
	 */
	public static void setAvatarPath(String avatarPath) {
		Divisie.avatarPath = avatarPath;
	}
	
}
