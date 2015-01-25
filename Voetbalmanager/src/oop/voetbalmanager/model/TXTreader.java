package oop.voetbalmanager.model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class TXTreader {
	
	public static String pathTxt = System.getProperty("user.dir") + "/teams-txt/";
	
	public static ArrayList<String> teams = new ArrayList<String>();
	
	private static XMLwriter writer = new XMLwriter(Driver.path);
	
	public static void setWriter(String infile){
		writer=new XMLwriter(infile);
	}
	
	public static void readTeam(String teamFile, String teamNaam){
			String file = pathTxt + teamFile;
			Scanner sc;
			try {
				
				sc = new Scanner(new BufferedReader(new FileReader(file)));
				while(sc.hasNextLine()){
					
					parse(sc, teamNaam);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	public static void addTeamInfo(String infoFile){
		String file = infoFile;
		Scanner sc;
		try {
			
			sc = new Scanner(new BufferedReader(new FileReader(file)));
			while(sc.hasNextLine()){
				
				parseInfo(sc);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void parseInfo(Scanner sc){
		String team = sc.nextLine();
		//player array
		String[] pA = team.split("/");
		String naam = pA[0];
		String rank = pA[1];
		String winst = pA[2];
		String gelijkspel = pA[3];
		String verlies = pA[4];
		String doelvoor = pA[5];
		String doeltegen = pA[6];
		String doelsaldo = pA[7];
		String punten = pA[8];
		String budget = pA[9];
		
		System.out.println("Team: "+naam+", rank: "+rank+", winst: "+winst+", gelijkspel: "+gelijkspel+
					", verlies: "+verlies+", doelvoor: "+doelvoor+", doeltegen: "+doeltegen+
					", doelsaldo: "+doelsaldo+", punten: "+punten+", budget: "+budget);
		
	//	toevoegen aan XML
		writer.updaten("team" , naam, "rank", rank);
		writer.updaten("team" , naam, "winst", winst);
		writer.updaten("team" , naam, "gelijkspel", gelijkspel);
		writer.updaten("team" , naam, "verlies", verlies);
		writer.updaten("team" , naam, "doelvoor", doelvoor);
		writer.updaten("team" , naam, "doeltegen", doeltegen);
		writer.updaten("team" , naam, "doelsaldo", doelsaldo);
		writer.updaten("team" , naam, "score", punten);
		writer.updaten("team" , naam, "budget", budget);
	}
	
	public static void parse(Scanner sc, String teamNaam){
		String player = sc.nextLine();
		//player array
		String[] pA = player.split(",");
		String naam = pA[0];
		String pos = pA[1];
		String uith = pA[2];
		String aanv = pA[3];
		String verd = pA[4];
		
	//	toevoegen aan XML
		writer.add("team", teamNaam, "speler", naam);	
		switch(pos){
			case "A":
				writer.updaten("speler" , naam, "type", "aanvaller");
				break;
			case "M":
				writer.updaten("speler" , naam, "type", "middenvelder");
				break;
			case "V":
				writer.updaten("speler" , naam, "type", "verdediger");
				break;
			case "K":
				writer.updaten("speler" , naam, "type", "doelman");
				break;
		}
       
		writer.updaten("speler" , naam, "uithouding", uith);
		writer.updaten("speler" , naam, "offense", aanv);
		writer.updaten("speler" , naam, "defence", verd);
	}
	
	public static String listFilesForFolder(final File folder) {
		String allFiles="";
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {	            
	            String teamFile = fileEntry.getName();
	            String teamNaam = teamFile.replaceAll(".txt", "");
	            //toevoegen aan XML
	            writer.add("divisie", "Eredivisie", "team", teamNaam);
	            readTeam(teamFile, teamNaam);
	            allFiles += teamFile+"\n";
	        }
	    }
	    System.out.println(teams.toString());
	    return allFiles;
	}

	/**
	 * @return the writer
	 */
	public static XMLwriter getWriter() {
		return writer;
	}

	
}
