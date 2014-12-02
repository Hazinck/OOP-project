package oop.voetbalmanager.model;
import java.util.ArrayList;


public class Divisie {
	private String naam;
	private ArrayList<Team> teamList = new ArrayList<Team>();
	private int speeldag;
	private int stand;
	
	public Divisie(String naam, ArrayList<Team> teamList, int speeldag, int stand){
		this.naam = naam;
		this.teamList = teamList;
		this.speeldag = speeldag;
		this.stand =stand;
	}
	
	public String toString(){
		return  "Divisie\nNaam: " + naam + 
				"\n\nTeams:\n" + teamList.toString() + "\nSpeeldag:"+speeldag + "\nStand:"+stand;
	}

	/**
	 * @return the naam
	 */
	public String getNaam() {
		return naam;
	}
	public int getSpeeldag() {
		return speeldag;
	}

	/**
	 * @param naam the naam to set
	 */
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public void setSpeeldag(int speeldag) {
		this.speeldag = speeldag;
	}

	public int getStand() {
		return stand;
	}

	public void setStand(int stand) {
		this.stand = stand;
	}
	
	
}
