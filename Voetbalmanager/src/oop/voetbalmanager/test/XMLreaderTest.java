
package oop.voetbalmanager.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import oop.voetbalmanager.model.Divisie;
import oop.voetbalmanager.model.Opstelling;
import oop.voetbalmanager.model.Speler;
import oop.voetbalmanager.model.Team;
import oop.voetbalmanager.model.Wedstrijdteam;
import oop.voetbalmanager.model.XMLreader;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Test;

public class XMLreaderTest {

	@Test
	public void testReadDivisie() {
		XMLreader test=new XMLreader();
		assertEquals("Divisie\nNaam:Champions League\nTeams:\n[]\nSpeeldag:10\nStand:4",test.readDivisie("ReadDivisieTest.xml").toString());
	}
	
	@Test
	public void testReadTeam(){
		XMLreader test=new XMLreader();
		SAXBuilder builder = new SAXBuilder();
		Team team = null;
		try {
		Document document = (Document) builder.build(new File("ReadTeamTest.xml"));
		Element divisieEl = document.getRootElement();
		team = test.readTeam(divisieEl);
		} catch (IOException io) {
			 System.out.println(io.getMessage());
		 } catch (JDOMException jdomex) {
			 System.out.println(jdomex.getMessage());
		 }
		assertEquals("Teamnaam:Ajax\nRank:1\nSpelers:\n[]\nWinst:20\nVerlies:3\nGelijkspel:11\nDoelsaldo:41\nDoelpunten tegen:28\nDoelpunten voor:69\nBudget:65.0\nScore:71\n",team.toString());
	}
	
	@Test
	public void testReadSpeler(){
		XMLreader test=new XMLreader();
		SAXBuilder builder = new SAXBuilder();
		Speler speler=null;
		try {
			Document document = (Document) builder.build(new File("ReadSpelerTest.xml"));
			Element divisieEl = document.getRootElement();
			speler=test.readSpeler(divisieEl);
		} catch (IOException io) {
			 System.out.println(io.getMessage());
		 } catch (JDOMException jdomex) {
			 System.out.println(jdomex.getMessage());
		 }
		assertEquals("Naam:Aefano Denswil - Nummer:222\nType:verdediger\nOffense:56\nDefence:70\nConditie:64\nBeschikbaarheid:beschikbaar\nPrijs:10000\n",speler.toString());
	}
	
	@Test
	public void testReadWedstrijdteam(){
		XMLreader test=new XMLreader();
		Divisie divisie = test.readDivisie("database.xml");
		Team feyenoord = divisie.getTeamList().get(8);
		Wedstrijdteam fey=new Wedstrijdteam(feyenoord);
		assertEquals(fey,test.readWedstrijdteam(feyenoord, "database.xml"));
	}
	
	@Test
	public void testReadOpstelling(){
		XMLreader test=new XMLreader();
		SAXBuilder builder = new SAXBuilder();
		Opstelling opstelling=null;
		try {
			Document document = (Document) builder.build(new File("ReadOpstellingTest.xml"));
			Element divisieEl = document.getRootElement();
			opstelling=test.readOpstelling(divisieEl);
			} catch (IOException io) {
				 System.out.println(io.getMessage());
			 } catch (JDOMException jdomex) {
				 System.out.println(jdomex.getMessage());
			 }
		
		assertEquals("Opstelling: 433:\n[doelman op: 145,395, aanvaller op: 75,355, aanvaller op: 220,355, aanvaller op: 35,330, aanvaller op: 250,330, aanvaller op: 150,240, middenvelder op: 50,210, middenvelder op: 240,210, verdediger op: 35,130, middenvelder1 op: 270,130, middenvelder op: 150,80]",opstelling.toString());
	}
	
	@Test
	public void testReadOpstellingList(){
		XMLreader test=new XMLreader();
		assertEquals("[Opstelling: 433:\n[doelman op: 145,395, aanvaller op: 75,355, aanvaller op: 220,355, aanvaller op: 35,330, aanvaller op: 250,330, aanvaller op: 150,240, middenvelder op: 50,210, middenvelder op: 240,210, verdediger op: 35,130, middenvelder1 op: 270,130, middenvelder op: 150,80], Opstelling: 442:\n[doelman op: 145,395, verdediger op: 75,355, verdediger op: 220,355, verdediger op: 35,330, verdediger op: 250,330, middenvelder op: 79,242, middenvelder op: 223,229, middenvelder op: 31,202, middenvelder op: 265,191, aanvaller1 op: 208,94, aanvaller op: 98,92]]",test.readOpstellingList("database.xml").toString());
	}

}
