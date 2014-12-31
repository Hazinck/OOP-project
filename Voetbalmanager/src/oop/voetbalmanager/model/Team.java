package oop.voetbalmanager.model;
import java.util.ArrayList;


public class Team {
	
	protected String naam;
	protected int rank;
	protected ArrayList<Speler> spelerList = new ArrayList<Speler>();
	protected int winst;
	protected int verlies;
	protected int gelijkspel;
	protected int doelsaldo;
	protected int doeltegen;
	protected int doelvoor;
	protected int score;
	protected double budget;
	
	/**
	 * 
	 * @param naam
	 * @param rank
	 * @param spelerList
	 * @param winst
	 * @param verlies
	 * @param gelijkspel
	 * @param doelsaldo
	 * @param doeltegen
	 * @param doelvoor
	 * @param budget
	 * @param score
	 */
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
	protected Team(Team team){
		this.naam = team.naam;
		this.rank = team.rank;
		this.spelerList = team.spelerList;
		this.winst = team.winst;
		this.verlies = team.verlies;
		this.gelijkspel = team.gelijkspel;
		this.doelsaldo = team.doelsaldo;
		this.doeltegen = team.doeltegen;
		this.doelvoor = team.doelvoor;
		this.budget = team.budget;
		this.score = team.score;
	}
	

	public String toString(){
		return  "Teamnaam:" + naam + "\nRank:" + rank + 
				"\nSpelers:\n" + spelerList.toString() + 
				"\nWinst:" + winst + "\nVerlies:" + verlies + 
				"\nGelijkspel:" + gelijkspel + "\nDoelsaldo:" + 
				doelsaldo + "\nDoelpunten tegen:" + doeltegen + 
				"\nDoelpunten voor:" + doelvoor + "\nBudget:" + budget+ "\nScore:"+score+"\n" ;
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
	
	public ArrayList<Speler> getSpelerList(){
		return spelerList;
	}
	
	public void setSpelerList(ArrayList<Speler> changelist){
		spelerList=changelist;
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

	public boolean equals(Object obj) {
		if (obj instanceof Team){
			Team that = (Team)obj;
			return this.naam.equals(that.naam);
		} else {
			return false;
		}
	}
}
