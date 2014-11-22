
public class Driver {
	public static String path = System.getProperty("user.dir") + "/database.xml";

	public static void main(String[] args) {
		
/*		XMLreader reader = new XMLreader();
		Divisie divisie = reader.readDivisie();
		
		System.out.println(divisie.toString());
*/		
		XMLwriter writer = new XMLwriter();
/*		writer.updaten("team" , "Feyenoord", "winst", "0");
		writer.updaten("team" , "Feyenoord", "verlies", "0");
		writer.updaten("team" , "Feyenoord", "gelijkspel", "0");
		writer.updaten("team" , "Feyenoord", "doelsaldo", "0");
		writer.updaten("team" , "Feyenoord", "doeltegen", "0");
		writer.updaten("team" , "Feyenoord", "doelvoor", "0");
		
		writer.updaten("speler" , "Jordy Clasie", "type", "Middenvelder");
		writer.updaten("speler" , "Jordy Clasie", "offense", "0");
		writer.updaten("speler" , "Jordy Clasie", "defence", "0");
		writer.updaten("speler" , "Jordy Clasie", "uithouding", "0");
		writer.updaten("speler" , "Jordy Clasie", "beschikbaarheid", "ja");
		writer.updaten("speler" , "Jordy Clasie", "prijs", "4500000");*/
	
		writer.add("divisie", "Eredivisie", "team", "PSV");
		writer.add("divisie", "Eredivisie", "team", "FC Groningen");
		writer.add("team", "PSV", "speler", "Memphis Depay");
		writer.add("team", "PSV", "speler", "Andrés Guardado");
		writer.add("team", "FC Groningen", "speler", "Danny Hoesen");
		writer.add("team", "Ajax", "speler", "Lasse Schöne");
	}

}
