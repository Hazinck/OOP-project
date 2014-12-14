package oop.voetbalmanager.model;
import java.io.File;
import java.util.List;

import org.jdom2.Element;


public class Driver {
	public static String path = System.getProperty("user.dir") + "/database.xml";

	final static File folder = new File(TXTreader.pathTxt);
	
	public static void main(String[] args) {
	
	/*	XMLreader reader = new XMLreader();
		Divisie divisie = reader.readDivisie();
		
		System.out.println(divisie.toString());
	
		XMLwriter writer = new XMLwriter();
		writer.updaten("team" , "Ajax", "budget", "500000000");
		writer.updaten("team" , "Feyenoord", "budget", "0");
		writer.updaten("divisie" , "Eredivisie", "speeldag", "5");
		writer.updaten("divisie" , "Eredivisie", "stand", "0");
		writer.updaten("team" , "Feyenoord", "gelijkspel", "0");
		writer.updaten("team" , "Feyenoord", "doelsaldo", "0");
		writer.updaten("team" , "Feyenoord", "doeltegen", "7");
		writer.updaten("team" , "Feyenoord", "doelvoor", "0");
		
		writer.updaten("speler" , "Jordy Clasie", "type", "Middenvelder");
		writer.updaten("speler" , "Jordy Clasie", "offense", "0");
		writer.updaten("speler" , "Jordy Clasie", "defence", "8");
		writer.updaten("speler" , "Jordy Clasie", "uithouding", "0");
		writer.updaten("speler" , "Jordy Clasie", "beschikbaarheid", "ja");
		writer.updaten("speler" , "Jordy Clasie", "prijs", "4500000");
	
		writer.add("divisie", "Eredivisie", "team", "PSV");
		writer.add("divisie", "Eredivisie", "team", "FC Groningen");
		writer.add("team", "PSV", "speler", "Memphis Depay");
		writer.add("team", "PSV", "speler", "Andrés Guardado");
		writer.add("team", "FC Groningen", "speler", "Danny Hoesen");
		writer.add("team", "Ajax", "speler", "Lasse Schöne");*/
		
		
/*		//teams, spelers bijvullen
		TXTreader.listFilesForFolder(folder);
	
		//Teams info toevoegen
		TXTreader.addTeamInfo("teamInfo.txt");
*/	
		
		//spel
		XMLreader reader = new XMLreader();
		Divisie divisie = reader.readDivisie();
		Team team1 = divisie.getTeamList().get(1);
		Team team2 = divisie.getTeamList().get(8);
		Spel s = new Spel(team1, team2, 1000);
		System.out.println(s.winner());
		
		
	}
	
	/**
	 * Geeft een willekeurige tegenstander
	 * @param divisie	de huidige divisie
	 * @param userTeam	het team van de user
	 * @return			een willekeurige tegenstander
	 */
	public static Team volgendeTeam(Divisie divisie, Team userTeam) {
		int aantalTeams = 18;
		Team tegenstander = userTeam;
		boolean gevonden = false;
		
		//zolang een tegenstander niet is gevonden, zoek voor een tegenstander
		while (!gevonden) {
			//neem een willekeurig team
			int i = RNG.getalTot(aantalTeams) + 1;
			tegenstander = divisie.getTeamList().get(i);
			
			//als dit niet het team van de user is, hebben we een tegenstander gevonden
			gevonden = !(tegenstander.equals(userTeam));
		}
		
		return tegenstander;
	}
	

}
