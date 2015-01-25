package oop.voetbalmanager.model;
import java.io.File;


public class Driver {
	public static String path = System.getProperty("user.dir") + "/database.xml";

	final static File folder = new File(TXTreader.pathTxt);
	
	public static void main(String[] args) {
	
//		writer.updaten("divisie" , "Eredivisie", "stand", "0");
//		writer.updaten("team" , "Feyenoord", "doelvoor", "0");
//		writer.updaten("speler" , "Jordy Clasie", "prijs", "4500000");
//		writer.add("team", "Ajax", "speler", "Lasse Schöne");


		
	/*	
		XMLreader reader = new XMLreader();
		Divisie divisie = reader.readDivisie(path);
		XMLwriter writer = new XMLwriter(path);
		
		double prijs;
		for(Team t: Divisie.getTeamList()){
			for(Speler s: t.getSpelerList()){
				
				if(!s.getType().equals("doelman")){
					prijs = s.getDefence()*s.getOffense()*s.getUithouding();
					prijs/=80000;
				}else{
					prijs =  s.getDefence();
					prijs /= 40;
				}
				writer.updaten("speler" , s.getNaam(), "prijs", prijs+"");
			}
		}
		*/
	}
	

	

}
