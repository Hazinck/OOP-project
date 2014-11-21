import java.util.ArrayList;


public class Divisie {
	private String naam;
	private ArrayList<Team> teamList = new ArrayList<Team>();
	private int speeldag;
	
	public Divisie(String naam, ArrayList<Team> teamList, int speeldag){
		this.naam = naam;
		this.teamList = teamList;
		this.speeldag = speeldag;
	}
	
	public String toString(){
		return  "Divisie\n\rNaam: " + naam + "\n\rTeams:\n\r" + teamList.toString() + "\n\rSpeeldag:\n\r"+speeldag;
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
}
