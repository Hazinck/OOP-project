import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XMLreader {
	
	public XMLreader(){
		
	}
	
	public Divisie readDivisie(){
		 SAXBuilder builder = new SAXBuilder();
		 File xmlFile = new File(Driver.path);
		 
		 ArrayList<Team> teamList = new ArrayList<Team>();
		 String divisieNaam = "";
		 
		 try {
			//open xml
			Document document = (Document) builder.build(xmlFile);
			//maak element van <divisie>
			Element divisieEl = document.getRootElement();
			//parse naam en get teamlist
			divisieNaam = divisieEl.getChildText("naam");
			teamList = readTeam(divisieEl);
			
		 } catch (IOException io) {
			 System.out.println(io.getMessage());
		 } catch (JDOMException jdomex) {
			 System.out.println(jdomex.getMessage());
		 }
		 Divisie divisie = new Divisie(divisieNaam, teamList);
		 
		 return divisie;
	}
	
	public ArrayList<Team> readTeam(Element divisie){
		ArrayList<Team> teamList = new ArrayList<Team>();

		//maak lijst van alle <team>
		List<Element> teamElementList = divisie.getChildren("team");
			
		//<team> als element in arrraylist toevoegen
		for (int i = 0; i < teamElementList.size(); i++) {
			//parse teamNaam, rank en get spelerList
			Element teamEl = (Element) teamElementList.get(i);
			String teamNaam = teamEl.getChildText("naam");
			int rank = Integer.parseInt(teamEl.getChildText("rank"));
			ArrayList<Speler> spelerList = readSpeler(teamNaam, teamEl);
			
			Team team = new Team(teamNaam, rank, spelerList);
			teamList.add(team);
		}
		  
		return teamList;
	}
	
	public ArrayList<Speler> readSpeler(String teamNaam, Element team){
		ArrayList<Speler> spelerList = new ArrayList<Speler>();
		
		//maak lijst van alle <team>
		List<Element> spelerElementList = team.getChildren("speler");
		//<team> als element in arrraylist toevoegen
		for (int i = 0; i < spelerElementList.size(); i++) {
			//parse teamNaam, rank en get spelerList
			Element spelerEl = (Element) spelerElementList.get(i);
			String spelerNaam = spelerEl.getChildText("naam");
			int nummer = Integer.parseInt(spelerEl.getChildText("nummer"));
			
			Speler speler = new Speler(spelerNaam, nummer);
			spelerList.add(speler);
		}
		
		return spelerList;
	}

}
