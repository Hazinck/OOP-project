import java.util.ArrayList;


public class Divisie {
	private String naam;
	private ArrayList<Team> teamList = new ArrayList<Team>();
	
	
	public Divisie(String naam, ArrayList<Team> teamList){
		this.naam = naam;
		this.teamList = teamList;
	}
	
	public String toString(){
		return  "Divisie\n\rNaam: " + naam + "\n\rTeams:\n\r" + teamList.toString();
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
}
