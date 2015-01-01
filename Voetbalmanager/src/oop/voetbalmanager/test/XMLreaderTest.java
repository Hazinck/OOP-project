
package oop.voetbalmanager.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import oop.voetbalmanager.model.Speler;
import oop.voetbalmanager.model.Team;
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
		ArrayList<Team> teamList = new ArrayList<Team>();
		try {
		Document document = (Document) builder.build(new File("ReadTeamTest.xml"));
		Element divisieEl = document.getRootElement();
		teamList = test.readTeam(divisieEl);
		} catch (IOException io) {
			 System.out.println(io.getMessage());
		 } catch (JDOMException jdomex) {
			 System.out.println(jdomex.getMessage());
		 }
		assertEquals("[Teamnaam:Ajax\nRank:1\nSpelers:\n[]\nWinst:20\nVerlies:3\nGelijkspel:11\nDoelsaldo:41\nDoelpunten tegen:28\nDoelpunten voor:69\nBudget:65.0\nScore:71\n]",teamList.toString());
	}
	
	@Test
	public void testReadSpeler(){
		XMLreader test=new XMLreader();
		SAXBuilder builder = new SAXBuilder();
		ArrayList<Speler> spelerList=new ArrayList<Speler>();
		try {
			Document document = (Document) builder.build(new File("ReadSpelerTest.xml"));
			Element divisieEl = document.getRootElement();
			spelerList=test.readSpeler("Ajax",divisieEl);
		} catch (IOException io) {
			 System.out.println(io.getMessage());
		 } catch (JDOMException jdomex) {
			 System.out.println(jdomex.getMessage());
		 }
		assertEquals("[Naam:Aefano Denswil - Nummer:222\nType:verdediger\nOffense:56\nDefence:70\nConditie:64\nBeschikbaarheid:beschikbaar\nPrijs:200\n, Naam:Thulani Serero - Nummer:223\nType:middenvelder\nOffense:59\nDefence:48\nConditie:85\nBeschikbaarheid:niet beschikbaar\nPrijs:250\n]",spelerList.toString());
	}

}
