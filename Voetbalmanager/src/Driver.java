
public class Driver {
	public static String path = System.getProperty("user.dir") + "/database.xml";

	public static void main(String[] args) {
		
/*		XMLreader reader = new XMLreader();
		Divisie divisie = reader.readDivisie();
		
		System.out.println(divisie.toString());
*/		
		XMLwriter writer = new XMLwriter();
		writer.updaten("team" , "Ajax", "type", "nieuwe waarde");
		writer.updaten("speler" , "Arkadiusz Milik", "nummer", "42");
		
	}

}
