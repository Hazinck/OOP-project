package oop.voetbalmanager.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.Dimension;
import java.util.ArrayList;

import oop.voetbalmanager.model.Divisie;
import oop.voetbalmanager.model.Speler;
import oop.voetbalmanager.model.Team;
import oop.voetbalmanager.model.Wedstrijdteam;
import oop.voetbalmanager.model.XMLreader;

import org.junit.Test;

public class DivisieTest {

	@Test
	public void testGetNaam() {
		ArrayList<Team> testing=new ArrayList<Team>();
		Divisie test=new Divisie("Eredivisie",testing,25,5);
		assertEquals("Eredivisie",test.getNaam());
		assertNotEquals("Champions League",test.getNaam());
	}
	
	@Test
	public void testGetTeamList(){
		ArrayList<Team> testing=new ArrayList<Team>();
		Divisie test=new Divisie("Eredivisie",testing,25,5);
		assertEquals(testing,Divisie.getTeamList());
		ArrayList<Team> fout=new ArrayList<Team>();
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team Ajax=new Team("Ajax",4,spelerlist,5,4,3,5,6,4,6, 564541);
		fout.add(Ajax);
		assertNotEquals(fout,Divisie.getTeamList());
	}
	
	@Test
	public void testGetSpeeldag(){
		ArrayList<Team> testing=new ArrayList<Team>();
		Divisie test=new Divisie("Eredivisie",testing,25,5);
		assertEquals(25,Divisie.getSpeeldag());
		assertNotEquals(10,Divisie.getSpeeldag());
	}
	
	@Test
	public void testGetStand(){
		ArrayList<Team> testing=new ArrayList<Team>();
		Divisie test=new Divisie("Eredivisie",testing,25,5);
		assertEquals(5,test.getStand());
		assertNotEquals(10,test.getStand());
	}
	
	@Test
	public void testSetNaam(){
		ArrayList<Team> testing=new ArrayList<Team>();
		Divisie test=new Divisie("Eredivisie",testing,25,5);
		test.setNaam("Champions League");
		assertEquals("Champions League",test.getNaam());
		assertNotEquals("Eredivisie",test.getNaam());
	}
	
	@Test
	public void testSetTeamList(){
		ArrayList<Team> testing=new ArrayList<Team>();
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team Feyenoord=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 7, 254563);
		testing.add(Feyenoord);
		Divisie test=new Divisie("Eredivisie",testing,25,5);
		ArrayList<Team> change=new ArrayList<Team>();
		
		Team Ajax=new Team("Ajax",4,spelerlist,5,4,3,5,6,4,6, 564541);
		change.add(Ajax);
		test.setTeamList(change);
		assertEquals(change,Divisie.getTeamList());
		assertNotEquals(testing,Divisie.getTeamList());
	}
	
	@Test
	public void testSetSpeeldag(){
		ArrayList<Team> testing=new ArrayList<Team>();
		Divisie test=new Divisie("Eredivisie",testing,25,5);
		Divisie.setSpeeldag(10);
		assertEquals(10,Divisie.getSpeeldag());
		assertNotEquals(25,Divisie.getSpeeldag());
		
	}
	
	@Test
	public void testSetStand(){
		ArrayList<Team> testing=new ArrayList<Team>();
		Divisie test=new Divisie("Eredivisie",testing,25,5);
		test.setStand(20);
		assertEquals(20,test.getStand());
		assertNotEquals(5,test.getStand());
	}
	
	@Test
	public void testToString(){
		ArrayList<Team> testing=new ArrayList<Team>();
		Divisie test=new Divisie("Eredivisie",testing,25,5);
		assertEquals("Divisie\nNaam:Eredivisie\nTeams:\n[]\nSpeeldag:25\nStand:5",test.toString());
	}
	
	@Test
	public void testGetTeamgespeeld(){
		ArrayList<Team> testing=new ArrayList<Team>();
		Divisie test=new Divisie("Eredivisie",testing,25,5);
		assertEquals("[]",Divisie.getTeamsGespeeld().toString());
	}
	
	@Test
	public void testGetSkipVerslag(){
		ArrayList<Team> testing=new ArrayList<Team>();
		Divisie test=new Divisie("Eredivisie",testing,25,5);
		assertEquals("Feyenoord heeft gewonnen met: 2-3",Divisie.getSkipVerslag());
	}
	
	@Test
	public void testRekenDoelpunten1(){
		XMLreader test=new XMLreader();
		Divisie divisie = test.readDivisie("database.xml");
		Team feyenoord = divisie.getTeamList().get(8);
		Wedstrijdteam fey=new Wedstrijdteam(feyenoord);
		Divisie.rekenDoelpunten(new Dimension(1,1),1,fey);
		assertEquals(77,fey.getDoelvoor());
		assertEquals(41,fey.getDoeltegen());
		assertEquals(36,fey.getDoelsaldo());
	}
	
	@Test
	public void testRekenDoelpunten2(){
		XMLreader test=new XMLreader();
		Divisie divisie = test.readDivisie("database.xml");
		Team feyenoord = divisie.getTeamList().get(8);
		Wedstrijdteam fey=new Wedstrijdteam(feyenoord);
		Divisie.rekenDoelpunten(new Dimension(2,1),2,fey);
		assertEquals(77,fey.getDoelvoor());
		assertEquals(42,fey.getDoeltegen());
		assertEquals(35,fey.getDoelsaldo());
	}
	
	@Test
	public void testRekenDoelpunten3(){
		XMLreader test=new XMLreader();
		Divisie divisie = test.readDivisie("database.xml");
		Team feyenoord = divisie.getTeamList().get(8);
		Wedstrijdteam fey=new Wedstrijdteam(feyenoord);
		Divisie.rekenDoelpunten(new Dimension(2,3),2,fey);
		assertEquals(79,fey.getDoelvoor());
		assertEquals(42,fey.getDoeltegen());
		assertEquals(37,fey.getDoelsaldo());
	}
	
	@Test
	public void testTeamsToDiv(){
		XMLreader test=new XMLreader();
		Divisie divisie = test.readDivisie("database.xml");
		Team feyenoord = divisie.getTeamList().get(8);
		Team ajax = divisie.getTeamList().get(0);
		Divisie.teamsToDiv(feyenoord,ajax);
		assertEquals(feyenoord,Divisie.getTeamList().get(8));
		assertEquals(ajax,Divisie.getTeamList().get(0));
	}
	
	@Test
	public void testRekenPunten(){
		XMLreader test=new XMLreader();
		Divisie divisie = test.readDivisie("database.xml");
		Team feyenoord = divisie.getTeamList().get(8);
		Divisie.rekenPunten(feyenoord);
		assertEquals(67,feyenoord.getScore());
	}

	@Test
	public void testFindTeamByName(){
		XMLreader test=new XMLreader();
		Divisie divisie = test.readDivisie("database.xml");
		Team feyenoord = divisie.getTeamList().get(8);
		assertEquals(feyenoord,divisie.findTeamByName("Feyenoord"));
	}
	
	@Test
	public void testRankTeams(){
		XMLreader test=new XMLreader();
		Divisie divisie=test.readDivisie("database.xml");
		Divisie.rankTeams();
		Divisie.getTeamList().get(1).setWinst(30);
		Divisie.rankTeams();
		assertEquals(71,Divisie.getTeamList().get(1).getScore());
	}
}
