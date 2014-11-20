import java.util.ArrayList;


public class Team {
	
	private String naam;
	private int rank;
	private ArrayList<Speler> spelerList = new ArrayList<Speler>();
	
	public Team(String naam, int rank, ArrayList<Speler> spelerList){
		this.naam = naam;
		this.rank = rank;
		this.spelerList = spelerList;
	}
	
	
	public String toString(){
		return  "Teamnaam: " + naam + "\n\rRank: " + rank + "\n\rSpelers:\n\r" + spelerList.toString();
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

}
