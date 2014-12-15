package oop.voetbalmanager.model;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Test;

public class XMLwriterTest {

	@Test
	public void testUpdatenBranch1() {
		XMLwriter test=new XMLwriter("testUpdatenBranch1.xml");
		test.updaten("speler", "Aefano Denswil", "type", "aanvaller");
		XMLreader testing=new XMLreader();
		SAXBuilder builder = new SAXBuilder();
		ArrayList<Speler> spelerList=new ArrayList<Speler>();
		try {
			Document document = (Document) builder.build(new File("testUpdatenBranch1.xml"));
			Element divisieEl = document.getRootElement();
			List<Element> teamElementList = divisieEl.getChildren("team");
			Element teamEl = (Element) teamElementList.get(0);
			spelerList=testing.readSpeler("Ajax",teamEl);
		} catch (IOException io) {
			 System.out.println(io.getMessage());
		 } catch (JDOMException jdomex) {
			 System.out.println(jdomex.getMessage());
		 }
		assertEquals("[Naam:Aefano Denswil - Nummer:222\nType:aanvaller\nOffense:56\nDefence:70\nConditie:64\nBeschikbaarheid:beschikbaar\nPrijs:200\n, Naam:Thulani Serero - Nummer:223\nType:middenvelder\nOffense:59\nDefence:48\nConditie:85\nBeschikbaarheid:niet beschikbaar\nPrijs:250\n]",spelerList.toString());
	}
	
	@Test
	public void testUpdatenBranch2(){
		XMLwriter test=new XMLwriter("testUpdatenBranch2.xml");
		test.updaten("divisie", null, "speeldag", "35");
		XMLreader testing=new XMLreader();
		Divisie tested=testing.readDivisie("testUpdatenBranch2.xml");
		assertEquals("Divisie\nNaam:Eredivisie\nTeams:\n[]\nSpeeldag:35\nStand:0",tested.toString());
	}
	
	@Test
	public void testUpdatenBranch3(){
		XMLwriter test=new XMLwriter("testUpdatenBranch3.xml");
		test.updaten("divisie", null, "populariteit", "5");
		SAXBuilder builder = new SAXBuilder();
		 File xmlFile = new File("testUpdatenBranch3.xml");
		String populariteit="";
		 try {
			Document document = (Document) builder.build(xmlFile);
			Element divisieEl = document.getRootElement();
			populariteit = divisieEl.getChildText("populariteit");
		} catch (IOException io) {
			 System.out.println(io.getMessage());
		 } catch (JDOMException jdomex) {
			 System.out.println(jdomex.getMessage());
		 }
		assertEquals("5",populariteit);
	}
	
	@Test
	public void testUpdatenBranch4(){
		XMLwriter test=new XMLwriter("testUpdatenBranch4.xml");
		test.updaten("speler", "Aefano Denswil", "salaris", "9000");
		SAXBuilder builder = new SAXBuilder();
		String salaris="";
		try {
			Document document = (Document) builder.build(new File("testUpdatenBranch4.xml"));
			Element divisieEl = document.getRootElement();
			List<Element> teamElementList = divisieEl.getChildren("team");
			Element teamEl = (Element) teamElementList.get(0);
			List<Element> spelerElementList = teamEl.getChildren("speler");
			Element spelerEl = (Element) spelerElementList.get(0);
			salaris = spelerEl.getChildText("salaris");
		} catch (IOException io) {
			 System.out.println(io.getMessage());
		 } catch (JDOMException jdomex) {
			 System.out.println(jdomex.getMessage());
		 }
		assertEquals("9000",salaris);
	}
	
	@Test
	public void testAddBranch1(){
		XMLwriter test=new XMLwriter("testAddBranch1.xml");
		test.add("divisie", null, "something", "test");
		
	}

}
