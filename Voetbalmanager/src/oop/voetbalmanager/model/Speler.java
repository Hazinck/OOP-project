package oop.voetbalmanager.model;

public class Speler {
	private String naam;
	private int nummer;
	private String type;
	private int offense;
	private int defence;
	private int uithouding;
	private String beschikbaarheid;
	private int prijs;
	
	/**
	 * 
	 * @param naam
	 * @param nummer
	 * @param type
	 * @param offense
	 * @param defence
	 * @param uithouding
	 * @param beschikbaarheid
	 * @param prijs
	 */
	public Speler(String naam, int nummer, String type, int offense, int defence, int uithouding, String beschikbaarheid, int prijs){
		this.naam = naam;
		this.nummer = nummer;
		this.type = type;
		this.offense = offense;
		this.defence = defence;
		this.uithouding = uithouding;
		this.beschikbaarheid = beschikbaarheid;
		this.prijs = prijs;
	}
	
	public String toString(){
		return "Naam:" + naam + " - Nummer:" + nummer + "\n" + "Type:" +type +
				"\nOffense:" + offense + "\nDefence:" + defence +"\nConditie:" + uithouding + 
				"\nBeschikbaarheid:"+beschikbaarheid+"\nPrijs:"+prijs +"\n" ;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getOffense() {
		return offense;
	}

	public void setOffense(int offense) {
		this.offense = offense;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getUithouding() {
		return uithouding;
	}

	public void setUithouding(int uithouding) {
		this.uithouding = uithouding;
	}

	public String getBeschikbaarheid() {
		return beschikbaarheid;
	}

	public void setBeschikbaarheid(String beschikbaarheid) {
		this.beschikbaarheid = beschikbaarheid;
	}

	public int getPrijs() {
		return prijs;
	}

	public void setPrijs(int prijs) {
		this.prijs = prijs;
	}
	
}
