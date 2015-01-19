package oop.voetbalmanager.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import oop.voetbalmanager.model.Divisie;
import oop.voetbalmanager.model.TXTreader;
import oop.voetbalmanager.model.XMLreader;

import org.junit.Test;

public class TXTreaderTest {

	@Test
	public void testParseInfo() {
		Scanner sc=null;
		try{
			sc=new Scanner(new File("teamInfo.txt"));
		}catch(Exception e){
			System.out.println("Something went wrong");
		}
		TXTreader.setWriter("TXTreaderTest.xml");
		TXTreader.parseInfo(sc);
		XMLreader testing=new XMLreader();
		assertEquals("[Teamnaam:Ajax\nRank:1\nSpelers:\n[]\nWinst:20\nVerlies:3\nGelijkspel:11\nDoelsaldo:41\nDoelpunten tegen:28\nDoelpunten voor:69\nBudget:65.0\nScore:71\n]",testing.readDivisie("TXTreaderTest.xml").getTeamList().toString());
	}
	
	@Test
	public void testParse1(){
		Scanner sc=null;
		try{
			sc=new Scanner("Artour Babaev,A,80,90,70,");
		}catch(Exception e){
			System.out.println("Something went wrongk");
		}
		TXTreader.setWriter("TXTParseTest.xml");
		TXTreader.parse(sc, "Feyenoord");
		XMLreader testing=new XMLreader();
		Divisie nieuw=testing.readDivisie("TXTParseTest.xml");
		assertEquals("Naam:Artour Babaev - Nummer:929\nType:aanvaller\nOffense:90\nDefence:70\nConditie:80\nBeschikbaarheid:null\nPrijs:10000\n",nieuw.getTeamList().get(8).getSpelerList().get(28).toString());
		
	}
	
	@Test
	public void testParse2(){
		Scanner sc=null;
		try{
			sc=new Scanner("Singsing Yuen,V,80,90,70,");
		}catch(Exception e){
			System.out.println("Something went wrongk");
		}
		TXTreader.setWriter("TXTParseTest.xml");
		TXTreader.parse(sc, "ADO Den Haag");
		XMLreader testing=new XMLreader();
		Divisie nieuw=testing.readDivisie("TXTParseTest.xml");
		assertEquals("Naam:Singsing Yuen - Nummer:123\nType:verdediger\nOffense:90\nDefence:70\nConditie:80\nBeschikbaarheid:null\nPrijs:10000\n",nieuw.getTeamList().get(0).getSpelerList().get(22).toString());
		
	}
	
	@Test
	public void testParse3(){
		Scanner sc=null;
		try{
			sc=new Scanner("Andy Zaidman,M,80,90,70,");
		}catch(Exception e){
			System.out.println("Something went wrongk");
		}
		TXTreader.setWriter("TXTParseTest.xml");
		TXTreader.parse(sc, "Ajax");
		XMLreader testing=new XMLreader();
		Divisie nieuw=testing.readDivisie("TXTParseTest.xml");
		assertEquals("Naam:Andy Zaidman - Nummer:229\nType:middenvelder\nOffense:90\nDefence:70\nConditie:80\nBeschikbaarheid:null\nPrijs:10000\n",nieuw.getTeamList().get(1).getSpelerList().get(28).toString());
		
	}
	
	@Test
	public void testParse4(){
		Scanner sc=null;
		try{
			sc=new Scanner("Alexandru Iosup,K,80,90,70,");
		}catch(Exception e){
			System.out.println("Something went wrongk");
		}
		TXTreader.setWriter("TXTParseTest.xml");
		TXTreader.parse(sc, "Willem II");
		XMLreader testing=new XMLreader();
		Divisie nieuw=testing.readDivisie("TXTParseTest.xml");
		assertEquals("Naam:Alexandru Iosup - Nummer:1825\nType:doelman\nOffense:90\nDefence:70\nConditie:80\nBeschikbaarheid:null\nPrijs:10000\n",nieuw.getTeamList().get(17).getSpelerList().get(24).toString());
		
	}
	
	@Test
	public void testReadTeam(){
		TXTreader.setWriter("TXTReadTeam.xml");
		TXTreader.readTeam("TXTTest.txt", "ADO Den Haag");
		XMLreader testing=new XMLreader();
		Divisie nieuw=testing.readDivisie("TXTReadTeam.xml");
		assertEquals("Teamnaam:ADO Den Haag\nRank:9\nSpelers:\n[Naam:Artour Babaev - Nummer:11\nType:aanvaller\nOffense:90\nDefence:70\nConditie:80\nBeschikbaarheid:null\nPrijs:10000\n, Naam:Alexandru Iosup - Nummer:12\nType:verdediger\nOffense:80\nDefence:80\nConditie:80\nBeschikbaarheid:null\nPrijs:10000\n]\nWinst:12\nVerlies:15\nGelijkspel:7\nDoelsaldo:-19\nDoelpunten tegen:64\nDoelpunten voor:45\nBudget:13.6\nScore:43\n",nieuw.getTeamList().get(0).toString());
	}
	
	@Test
	public void testAddTeamInfo(){
		TXTreader.setWriter("TXTAddInfo.xml");
		TXTreader.addTeamInfo("TXTTeamInfo.txt");
		XMLreader test=new XMLreader();
		assertEquals("[Teamnaam:Evil Geniuses\nRank:1\nSpelers:\n[]\nWinst:20\nVerlies:3\nGelijkspel:11\nDoelsaldo:41\nDoelpunten tegen:28\nDoelpunten voor:69\nBudget:65.0\nScore:71\n, Teamnaam:Team Secret\nRank:2\nSpelers:\n[]\nWinst:20\nVerlies:7\nGelijkspel:7\nDoelsaldo:36\nDoelpunten tegen:40\nDoelpunten voor:76\nBudget:35.0\nScore:67\n]",test.readDivisie("TXTAddInfo.xml").getTeamList().toString());
	}
	
	@Test
	public void testListFilesForFolder(){
		TXTreader.setWriter("ListFilesForFolder.xml");
		assertEquals("ADO Den Haag.txt\nAjax.txt\nAZ.txt\nExcelsior.txt\nFC Dordrecht.txt\nFC Groningen.txt\nFC Twente.txt\nFC Utrecht.txt\nFeyenoord.txt\nGo Ahead Eagles.txt\nHeracles Almelo.txt\nNAC Breda.txt\nPEC Zwolle.txt\nPSV.txt\nSC Cambuur.txt\nSC Heerenveen.txt\nTXTTest.txt\nVitesse.txt\nWillem II.txt\n",TXTreader.listFilesForFolder(new File("teams-txt")));
	}

}
