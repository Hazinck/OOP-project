package oop.voetbalmanager.model;

import java.util.ArrayList;

public class Opstelling {
	private String naam;
	private ArrayList<Positie> posities = new ArrayList<Positie>();
	
	/**
	 * @param naam
	 * @param posities
	 */
	public Opstelling(String naam, ArrayList<Positie> posities) {
		super();
		this.naam = naam;
		this.posities = posities;
	}
	
	public String toString(){
		return "Opstelling: " + naam + ":\n" + posities;
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
	 * @return the posities
	 */
	public ArrayList<Positie> getPosities() {
		return posities;
	}


}

