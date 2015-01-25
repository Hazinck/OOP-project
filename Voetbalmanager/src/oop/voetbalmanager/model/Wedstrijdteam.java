package oop.voetbalmanager.model;

import java.util.ArrayList;
import java.util.Arrays;


public class Wedstrijdteam extends Team{
	
	private int off = 0;//offensieve kracht
	private int def = 0;//defensieve kracht
	private int uith = 0;//uithoudingsvermogen
	private Opstelling opstelling;//opstelling
	private int tactiek = 0;//tactiek
	private Speler[] wSpelers = new Speler[11];
	private ArrayList<Opstelling> opstellingen;
	private String gespeeldMet = "";
	/**
	 * @param team
	 */
	public Wedstrijdteam(Team team) {
		super(team);
		teamOffence();
		teamDefence();
		teamUithouding();
	}
	
	public void teamOffence(){
		int off = 0;
		
		for(Speler s: spelerList){
			off += s.getOffense();
		}
		off /= spelerList.size();
		
		this.off = off;
	}
	
	public void teamDefence(){
		int def = 0;
		
		for(Speler s: spelerList){
			def += s.getDefence();
		}
		def /= spelerList.size();
		
		this.def = def;
	}
	
	public void teamUithouding(){
		int uith = 0;
		
		for(Speler s: spelerList){
			uith += s.getUithouding();
		}
		uith /= spelerList.size();
		
		this.uith = uith;
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
	public int getDef() {
		return def;
	}
	/**
	 * @param def the def to set
	 */
	public void setDef(int def) {
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
	public Opstelling getOpstelling() {
		return opstelling;
	}
	/**
	 * @param opstelling the opstelling to set
	 */
	public void setOpstelling(Opstelling opstelling) {
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

	/**
	 * @return the def
	 */
	

	/**
	 * @return the spelers
	 */
	public Speler[] getWSpelers() {
		return wSpelers;
	}

	@Override
	public String toString() {
		return "Wedstrijdteam: "+getNaam()+"\nOffensieve kracht: " + off + "\nDefensieve kracht: " + def + "\nUithoudingsvermogen: " + uith
				+ "\n" + opstelling + "\nTactiek: " + tactiek
				+ "\nSpelers:\n" + Arrays.toString(wSpelers);
	}



	/**
	 * @param wSpelers the wSpelers to set
	 */
	public void setWSpelers(Speler[] wSpelers) {
		this.wSpelers = wSpelers;
	}

	/**
	 * @return the gespeeldMet
	 */
	public String getGespeeldMet() {
		return gespeeldMet;
	}

	/**
	 * @param gespeeldMet the gespeeldMet to set
	 */
	public void setGespeeldMet(String gespeeldMet) {
		this.gespeeldMet = gespeeldMet;
	}
}
