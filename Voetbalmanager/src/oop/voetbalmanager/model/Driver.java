package oop.voetbalmanager.model;
import java.io.File;


public class Driver {
	public static String path = System.getProperty("user.dir") + "/database.xml";

	final static File folder = new File(TXTreader.pathTxt);
	
	public static void main(String[] args) {
	
	/*	XMLreader reader = new XMLreader();
		Divisie divisie = reader.readDivisie();
		XMLreader reader = new XMLreader();
		Divisie divisie = reader.readDivisie();
		Team team1 = divisie.getTeamList().get(8);
		Wedstrijdteam wt = new Wedstrijdteam(team1.naam, team1.rank, team1.spelerList, team1.winst, team1.verlies, team1.gelijkspel, team1.doelsaldo, team1.doeltegen,
				team1.doelvoor, team1.budget, team1.score);
		
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
	
		
	//spel
	
		XMLreader reader = new XMLreader();
		Divisie divisie = reader.readDivisie(path);
		Wedstrijdteam team1 = new Wedstrijdteam(divisie.getTeamList().get(1));
		Wedstrijdteam team2 = new Wedstrijdteam(divisie.getTeamList().get(8));
		int geluksfactor = RNG.getalTot(800);
		Spel s = new Spel(team1, team2, geluksfactor);
		System.out.println(s.winner().getNaam() + " geluksfactor: "+geluksfactor);
	*/	/*	
		System.out.println("Team1 budget old: "+team1.getBudget());
		team1.setBudget(67);
		System.out.println("Team1 budget new: "+team1.getBudget());
		//writer.updaten("team" , team1.getNaam(), "budget", team1.getBudget()+"");
		Divisie.getTeamList().get(1).setBudget(team1.getBudget());
		System.out.println("Team1 in divisie budget new: "+divisie.getTeamList().get(1).getBudget());
			
//		Divisie.getTeamList().get(1).setWinst(Divisie.getTeamList().get(1).getWinst() + 3);;
		XMLreader reader = new XMLreader();
		Divisie divisie = reader.readDivisie(path);
		Divisie.rankTeams();
		Divisie.getTeamList().get(1).setWinst(30);
		Divisie.rankTeams();
		System.out.println(TXTreader.listFilesForFolder(new File("teams-txt")));
*/	
	}
	

	

}
