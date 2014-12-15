package oop.voetbalmanager.model;

import java.util.ArrayList;

public class Wedstrijdteam extends Team{
	
	private int off = 0;//offensieve kracht
	private int def = 0;//defensieve kracht
	private int uith = 0;//uithoudingsvermogen
	private String opstelling = "";//opstelling
	private int tactiek = 0;//tactiek
	
	
	
	/**
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
	public Wedstrijdteam(String naam, int rank, ArrayList<Speler> spelerList,
			int winst, int verlies, int gelijkspel, int doelsaldo,
			int doeltegen, int doelvoor, double budget, int score) {
		super(naam, rank, spelerList, winst, verlies, gelijkspel, doelsaldo, doeltegen,
				doelvoor, budget, score);
		teamOffence();
		teamDefence();
		teamUithouding();
	}
	
	public void teamOffence(){
		int off = 0;
		
		for(Speler s: spelerList){
			off += s.getOffense();
		}
		System.out.println(off);
		off /= spelerList.size();
		
		this.off = off;
		System.out.println("Offence: "+off);
	}
	
	public void teamDefence(){
		int def = 0;
		
		for(Speler s: spelerList){
			def += s.getDefence();
		}
		def /= spelerList.size();
		
		this.def = def;
		System.out.println("Defence: "+def);
	}
	
	public void teamUithouding(){
		int uith = 0;
		
		for(Speler s: spelerList){
			uith += s.getUithouding();
		}
		uith /= spelerList.size();
		
		this.uith = uith;
		System.out.println("Uithouding: "+uith);
	}
	/**
	 * @return the off
	 */
	public int getOff() {
		return off;
	}
	/**
	 * @param off the off to set
	 */
	public void setOff(int off) {
		this.off = off;
	}
	/**
	 * @return the def
	 */
	public int getdef() {
		return def;
	}
	/**
	 * @param def the def to set
	 */
	public void setdef(int def) {
		this.def = def;
	}
	/**
	 * @return the uith
	 */
	public int getUith() {
		return uith;
	}
	/**
	 * @param uith the uith to set
	 */
	public void setUith(int uith) {
		this.uith = uith;
	}
	/**
	 * @return the opstelling
	 */
	public String getOpstelling() {
		return opstelling;
	}
	/**
	 * @param opstelling the opstelling to set
	 */
	public void setOpstelling(String opstelling) {
		this.opstelling = opstelling;
	}
	/**
	 * @return the tactiek
	 */
	public int getTactiek() {
		return tactiek;
	}
	/**
	 * @param tactiek the tactiek to set
	 */
	public void setTactiek(int tactiek) {
		this.tactiek = tactiek;
	}
}
