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
	
	private static XMLwriter writer = new XMLwriter();
	
	public static void read(String teamFile, String teamNaam){
			String file = pathTxt + teamFile;
		//	File input = new File(file);
			Scanner sc;
			try {
				
				sc = new Scanner(new BufferedReader(new FileReader(file)));
			//	System.out.println(file+" "+ sc.hasNext());
				while(sc.hasNextLine()){
					
					parse(sc, teamNaam);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
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
	//	System.out.println("\tNaam: "+naam);
		writer.add("team", teamNaam, "speler", naam);
	//	System.out.println("\t\tPositie: "+pos);		
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
       
	//	System.out.println("\t\tUithouding: "+uith);
		writer.updaten("speler" , naam, "uithouding", uith);
	//	System.out.println("\t\tAanval: "+aanv);
		writer.updaten("speler" , naam, "offense", aanv);
	//	System.out.println("\t\tVerdediging: "+verd);
		writer.updaten("speler" , naam, "defence", verd);
	}
	
	public static void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        	
	        //	teams.add(fileEntry.getName());
	            
	            String teamFile = fileEntry.getName();
	            String teamNaam = teamFile.replaceAll(".txt", "");
	            //toevoegen aan XML
	            writer.add("divisie", "Eredivisie", "team", teamNaam);
	      //    System.out.println("========================\n"+"Team: "+teamNaam);
	            read(teamFile, teamNaam);
	        }
	    }
	    System.out.println(teams.toString());
	}

	
}
