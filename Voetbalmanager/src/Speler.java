
public class Speler {
	private String naam;
	private int nummer;
	
	public Speler(String naam, int nummer){
		this.naam = naam;
		this.nummer = nummer;
	}
	
	public String toString(){
		return "Naam: " + naam + " - Nummer: " + nummer + "\n\r";
	}

	/**
	 * @return naam
	 */
	public String getNaam() {
		return naam;
	}

	/**
	 * @param naam name to set
	 */
	public void setNaam(String naam) {
		this.naam = naam;
	}

	/**
	 * @return nummer
	 */
	public int getNummer() {
		return nummer;
	}

	/**
	 * @param nummer the number to set
	 */
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
}
