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
		 int speeldag = -1;
		 try {
			//open xml
			Document document = (Document) builder.build(xmlFile);
			//maak element van <divisie>
			Element divisieEl = document.getRootElement();
			//parse naam en get teamlist
			divisieNaam = divisieEl.getChildText("naam");
			teamList = readTeam(divisieEl);
			//parse speeldag
			speeldag = Integer.parseInt(divisieEl.getChildText("speeldag"));
			
			
		 } catch (IOException io) {
			 System.out.println(io.getMessage());
		 } catch (JDOMException jdomex) {
			 System.out.println(jdomex.getMessage());
		 }
		//maak divisie
		 Divisie divisie = new Divisie(divisieNaam, teamList, speeldag);
		 
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
			//parse naam, rank, spelerslijst, winst ....
			String teamNaam = teamEl.getChildText("naam");
			int rank = Integer.parseInt(teamEl.getChildText("rank"));
			ArrayList<Speler> spelerList = readSpeler(teamNaam, teamEl);
			int winst  = Integer.parseInt(teamEl.getChildText("winst"));
			int verlies = Integer.parseInt(teamEl.getChildText("verlies"));
			int gelijkspel = Integer.parseInt(teamEl.getChildText("gelijkspel"));
			int doelsaldo = Integer.parseInt(teamEl.getChildText("doelsaldo"));
			int doeltegen = Integer.parseInt(teamEl.getChildText("doeltegen"));
			int doelvoor = Integer.parseInt(teamEl.getChildText("doelvoor"));
			Team team = new Team(teamNaam, rank, spelerList, winst, verlies, 
										gelijkspel, doelsaldo, doeltegen, doelvoor);
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
			//parse teamNaam, rank, get spelerList ...
			Element spelerEl = (Element) spelerElementList.get(i);
			String spelerNaam = spelerEl.getChildText("naam");
			int nummer = Integer.parseInt(spelerEl.getChildText("nummer"));
			String type = spelerEl.getChildText("type");
			int offense = Integer.parseInt(spelerEl.getChildText("offense"));
			int defence = Integer.parseInt(spelerEl.getChildText("defence"));
			int uithouding = Integer.parseInt(spelerEl.getChildText("uithouding"));
			String beschikbaarheid = spelerEl.getChildText("beschikbaarheid");
			int prijs = Integer.parseInt(spelerEl.getChildText("prijs"));
			
			Speler speler = new Speler(spelerNaam, nummer, type, offense, defence, uithouding, beschikbaarheid, prijs);
			spelerList.add(speler);
		}
		
		return spelerList;
	}

}
