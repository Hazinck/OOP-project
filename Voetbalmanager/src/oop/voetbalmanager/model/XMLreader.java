package oop.voetbalmanager.model;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XMLreader {
	
	public XMLreader(){
		
	}
	
	public Divisie readDivisie(String infile){
		 SAXBuilder builder = new SAXBuilder();
		 File xmlFile = new File(infile);
		 
		 ArrayList<Team> teamList = new ArrayList<Team>();
		 String divisieNaam = "";
		 int speeldag = -1;
		 int stand = -1;
		 try {
			//open xml
			Document document = (Document) builder.build(xmlFile);
			//maak element van <divisie>
			Element divisieEl = document.getRootElement();
			//parse naam en get teamlist
			divisieNaam = divisieEl.getChildText("naam");
			teamList = readTeamList(divisieEl);
			//parse speeldag
			speeldag = Integer.parseInt(divisieEl.getChildText("speeldag"));
			
			stand = Integer.parseInt(divisieEl.getChildText("stand"));
			
		 } catch (IOException io) {
			 System.out.println(io.getMessage());
		 } catch (JDOMException jdomex) {
			 System.out.println(jdomex.getMessage());
		 }
		//maak divisie
		 Divisie divisie = new Divisie(divisieNaam, teamList, speeldag, stand);
		 
		 
		 return divisie;
	}
	
	public ArrayList<Team> readTeamList(Element divisie){
		ArrayList<Team> teamList = new ArrayList<Team>();

		//maak lijst van alle <team>
		List<Element> teamElementList = divisie.getChildren("team");
			
		//<team> als element in arrraylist toevoegen
		for (int i = 0; i < teamElementList.size(); i++) {
			//parse teamNaam, rank en get spelerList
			Element teamEl = (Element) teamElementList.get(i);
			//parse naam, rank, spelerslijst, winst ....
			
			teamList.add(readTeam(teamEl));
		}
		  
		return teamList;
	}
	
	public Team readTeam(Element teamEl){

			//parse naam, rank, spelerslijst, winst ....
			String teamNaam = teamEl.getChildText("naam");
			int rank = Integer.parseInt(teamEl.getChildText("rank"));
			ArrayList<Speler> spelerList = readSpelerList(teamNaam, teamEl);
			int winst  = Integer.parseInt(teamEl.getChildText("winst"));
			int verlies = Integer.parseInt(teamEl.getChildText("verlies"));
			int gelijkspel = Integer.parseInt(teamEl.getChildText("gelijkspel"));
			int doelsaldo = Integer.parseInt(teamEl.getChildText("doelsaldo"));
			int doeltegen = Integer.parseInt(teamEl.getChildText("doeltegen"));
			int doelvoor = Integer.parseInt(teamEl.getChildText("doelvoor"));
			double budget = Double.parseDouble(teamEl.getChildText("budget"));
			int score = Integer.parseInt(teamEl.getChildText("score"));
			Team team = new Team(teamNaam, rank, spelerList, winst, verlies, 
										gelijkspel, doelsaldo, doeltegen, doelvoor, budget, score);
		  
		return team;
	}
	
	
	
	public ArrayList<Speler> readSpelerList(String teamNaam, Element team){
		ArrayList<Speler> spelerList = new ArrayList<Speler>();
		
		//maak lijst van alle <team>
		List<Element> spelerElementList = team.getChildren("speler");
		//<team> als element in arrraylist toevoegen
		for (int i = 0; i < spelerElementList.size(); i++) {
			//parse teamNaam, rank, get spelerList ...
			Element spelerEl = (Element) spelerElementList.get(i);
			spelerList.add(readSpeler(spelerEl));
		}
		
		return spelerList;
	}
	
	public Speler readSpeler(Element spelerEl){
			String spelerNaam = spelerEl.getChildText("naam");
		//	System.out.println(spelerEl.getAttribute("id") + " "+ spelerNaam+" "+team.getChildText("naam"));
			int nummer=-1;
			try {
				nummer = spelerEl.getAttribute("id").getIntValue();
			} catch (DataConversionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//Integer.parseInt(spelerEl.getChildText("nummer"));
			String type = spelerEl.getChildText("type");
			int offense = Integer.parseInt(spelerEl.getChildText("offense"));			
			int defence = Integer.parseInt(spelerEl.getChildText("defence"));
			int uithouding = Integer.parseInt(spelerEl.getChildText("uithouding"));
			String beschikbaarheid = spelerEl.getChildText("beschikbaarheid");
	//		int prijs = Integer.parseInt(spelerEl.getChildText("prijs"));
			int prijs = 10000;
			
			Speler speler = new Speler(spelerNaam, nummer, type, offense, defence, uithouding, beschikbaarheid, prijs);

		
		return speler;
	}

	
	public ArrayList<Opstelling> readOpstellingList(String infile){
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(infile);
		
		ArrayList<Opstelling> opstellingen = new  ArrayList<Opstelling>();
		
		try {
			//open xml
			Document document = (Document) builder.build(xmlFile);
			//maak element van <divisie>
			Element divisieEl = document.getRootElement();
			Element opsteling = divisieEl.getChild("opstellingen");
			//maak lijst van alle opstellingen
			List<Element> opstElementList = opsteling.getChildren("opstelling_posities");
			//<opstelling> als element in arrraylist toevoegen
			for (int i = 0; i < opstElementList.size(); i++) {
				//parse teamNaam, rank, get spelerList ...
				Element opstEl = (Element) opstElementList.get(i);
				opstellingen.add(readOpstelling(opstEl));
			}
		 } catch (IOException io) {
			 System.out.println(io.getMessage());
		 } catch (JDOMException jdomex) {
			 System.out.println(jdomex.getMessage());
		 }
		//parse naam, rank, spelerslijst, winst ....
		
	  
		return opstellingen;
	}
	
	public Opstelling readOpstelling(Element opstEl){
		
//		Element keeper = opstEl.getChild("doelman0");
//		//maak lijst van alle opstellingen
//		List<Element> midList = opstEl.getChildren("middenvelder");
//		List<Element> verdList = opstEl.getChildren("verdediger");
//		List<Element> aanvList = opstEl.getChildren("aanvaller");
		
		List<Element> positiesList = opstEl.getChildren();
		
//		System.out.println(positiesList.get(1).getName());
		ArrayList<Positie> posities = new ArrayList<Positie>();
//		int xk = Integer.parseInt(keeper.getText().split(",")[0]);
//		int yk = Integer.parseInt(keeper.getText().split(",")[1]);
//		Positie doelman = new Positie(xk, yk, "doelman");
//		posities.add(doelman);
		//<opstelling> als element in arrraylist toevoegen
//		for (int i = 0; i < midList.size(); i++) {
//			int x = Integer.parseInt(midList.get(i).getText().split(",")[0]);
//			int y = Integer.parseInt(midList.get(i).getText().split(",")[1]);
//			Positie middenvelder = new Positie(x, y, "middenvelder");
//			posities.add(middenvelder);
//		}
//		for (int i = 0; i < verdList.size(); i++) {
//			int x = Integer.parseInt(verdList.get(i).getText().split(",")[0]);
//			int y = Integer.parseInt(verdList.get(i).getText().split(",")[1]);
//			Positie verdediger = new Positie(x, y, "verdediger");
//			posities.add(verdediger);
//		}
		for (int i = 1; i < positiesList.size(); i++) {
			int x = Integer.parseInt(positiesList.get(i).getText().split(",")[0]);
			int y = Integer.parseInt(positiesList.get(i).getText().split(",")[1]);
			String type = positiesList.get(i).getName();
			type = type.substring(0, type.length()-1);
			Positie speler = new Positie(x, y, type);
			posities.add(speler);
		}
		Collections.sort(posities, new Comparator<Positie>() {
		    @Override
		    public int compare(Positie p1, Positie p2) {
		        return Integer.compare(p2.getY(), p1.getY());
		    }
		});
		//parse naam, spelerslijst ....
		Opstelling opstelling = new  Opstelling(opstEl.getChildText("naam"), posities);
	  
		return opstelling;
	}
	
	public Wedstrijdteam readWedstrijdteam(Team userteam, String infile){
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(infile);
		Wedstrijdteam wteam = null;
		try {
			//open xml
			Document document = (Document) builder.build(xmlFile);
			//maak element van <divisie>
			Element divisieEl = document.getRootElement();
			Element wteamElement = divisieEl.getChild("Wedstrijdteam");
			//parse naam, rank, spelerslijst, winst ....
			String opstelling = wteamElement.getChildText("opstelling");
			int tactiek = Integer.parseInt(wteamElement.getChildText("tactiek"));
			String spelers = wteamElement.getChildText("spelers");
			if(userteam==null){
				String teamNaam = wteamElement.getChildText("TeamNaam");
				Team team = Divisie.findTeamByName(teamNaam);
				User.setTeam(team);
				wteam = new Wedstrijdteam(team);
			}else{
				wteam = new Wedstrijdteam(userteam);
			}
			ArrayList<Speler> spelerList = new ArrayList<Speler>();
			for(Speler s: wteam.getSpelerList()){
				if(spelers.contains(s.getNaam())){
					spelerList.add(s);
				}
			}
			if(spelerList.size()<1){
				for(Speler s: wteam.getSpelerList()){
					if(s.getType().contains("doelman")){
						spelerList.add(s);
						break;
					}
				}
				for(Speler s: wteam.getSpelerList()){
					if(!s.getType().contains("doelman")){
						spelerList.add(s);
					}
					if(spelerList.size()==11){
						break;
					}
				}
			//	System.out.println("XMLReader: readWteam " + spelerList.size());
			}
			Collections.reverse(spelerList);
			Speler[] spelersArray = new Speler[11];
			spelerList.toArray(spelersArray);
			
			ArrayList<Opstelling> opstellingen = readOpstellingList(infile);
			for(Opstelling op: opstellingen){
				if(op.getNaam().equals(opstelling)){
					wteam.setOpstelling(op);
				}
			}
			wteam.setTactiek(tactiek);
			wteam.setWSpelers(spelersArray);
			
			
		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			 System.out.println(jdomex.getMessage());
		}
		return wteam;
	}
}
