package src.oop.voetbalmanager.model;

import static org.junit.Assert.*;
import Speler;
import Team;

import java.util.ArrayList;

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
		assertEquals(testing,test.getTeamList());
		ArrayList<Team> fout=new ArrayList<Team>();
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team Ajax=new Team("Ajax",4,spelerlist,5,4,3,5,6,4,6, 564541);
		fout.add(Ajax);
		assertNotEquals(fout,test.getTeamList());
	}
	
	@Test
	public void testGetSpeeldag(){
		ArrayList<Team> testing=new ArrayList<Team>();
		Divisie test=new Divisie("Eredivisie",testing,25,5);
		assertEquals(25,test.getSpeeldag());
		assertNotEquals(10,test.getSpeeldag());
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
		assertEquals(change,test.getTeamList());
		assertNotEquals(testing,test.getTeamList());
	}
	
	@Test
	public void testSetSpeeldag(){
		ArrayList<Team> testing=new ArrayList<Team>();
		Divisie test=new Divisie("Eredivisie",testing,25,5);
		test.setSpeeldag(10);
		assertEquals(10,test.getSpeeldag());
		assertNotEquals(25,test.getSpeeldag());
		
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

}
