package oop.voetbalmanager.model;
import java.util.ArrayList;


public class Team {
	
	private String naam;
	private int rank;
	private ArrayList<Speler> spelerList = new ArrayList<Speler>();
	private int winst;
	private int verlies;
	private int gelijkspel;
	private int doelsaldo;
	private int doeltegen;
	private int doelvoor;
	private int score;
	private double budget;
	
	public Team(String naam, int rank, ArrayList<Speler> spelerList,int winst,
					int verlies,int gelijkspel,int doelsaldo,int doeltegen, int doelvoor, double budget, int score){
		this.naam = naam;
		this.rank = rank;
		this.spelerList = spelerList;
		this.winst = winst;
		this.verlies = verlies;
		this.gelijkspel = gelijkspel;
		this.doelsaldo = doelsaldo;
		this.doeltegen = doeltegen;
		this.doelvoor = doelvoor;
		this.budget = budget;
		this.score = score;
	}
	

	public String toString(){
		return  "Teamnaam: " + naam + "\nRank: " + rank + 
				"\nSpelers:\n" + spelerList.toString() + 
				"\nWinst: " + winst + "\nVerlies: " + verlies + 
				"\nGelijkspel: " + gelijkspel + "\nDoelsaldo: " + 
				doelsaldo + "\nDoelpunten tegen: " + doeltegen + 
				"\nDoelpunten voor: " + doelvoor + "\nBudget: " + budget+ "\n\n" ;
	}

	/**
	 * @return the naam
	 */
	public String getNaam() {
		return naam;
	}

	/**
	 * @param naam the naam to set
	 */
	public void setNaam(String naam) {
		this.naam = naam;
	}

	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}


	public int getWinst() {
		return winst;
	}


	public void setWinst(int winst) {
		this.winst = winst;
	}


	public int getVerlies() {
		return verlies;
	}


	public void setVerlies(int verlies) {
		this.verlies = verlies;
	}


	public int getGelijkspel() {
		return gelijkspel;
	}


	public void setGelijkspel(int gelijkspel) {
		this.gelijkspel = gelijkspel;
	}


	public int getDoelsaldo() {
		return doelsaldo;
	}


	public void setDoelsaldo(int doelsaldo) {
		this.doelsaldo = doelsaldo;
	}


	public int getDoeltegen() {
		return doeltegen;
	}


	public void setDoeltegen(int doeltegen) {
		this.doeltegen = doeltegen;
	}


	public int getDoelvoor() {
		return doelvoor;
	}


	public void setDoelvoor(int doelvoor) {
		this.doelvoor = doelvoor;
	}


	public double getBudget() {
		return budget;
	}


	public void setBudget(long budget) {
		this.budget = budget;
	}
	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


}
