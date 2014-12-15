package oop.voetbalmanager.model;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;
public class TeamTest {

	@Test
	public void testGetNaam() {
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		assertEquals("Feyenoord",test.getNaam());
		assertNotEquals("Ajax",test.getNaam());
	}
	
	@Test
	public void testGetRank(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 9, 4, 6, 7, 254563);
		assertEquals(5,test.getRank());
		assertNotEquals(4,test.getRank());
	}
	
	@Test
	public void testGetSpelerlist(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		assertEquals(spelerlist,test.getSpelerList());
		assertNotEquals(null,test.getSpelerList());
	}
	
	@Test
	public void testGetWinst(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		assertEquals(3,test.getWinst());
		assertNotEquals(5,test.getWinst());
	}
	
	@Test
	public void testGetVerlies(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		assertEquals(2,test.getVerlies());
		assertNotEquals(1,test.getVerlies());
	}
	
	@Test
	public void testGetGelijkspel(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		assertEquals(1,test.getGelijkspel());
		assertNotEquals(5,test.getGelijkspel());
	}
	
	@Test
	public void testGetDoelsaldo(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		assertEquals(2,test.getDoelsaldo());
		assertNotEquals(8,test.getDoelsaldo());
	}
	
	@Test
	public void testGetDoeltegen(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		assertEquals(4,test.getDoeltegen());
		assertNotEquals(5,test.getDoeltegen());
	}
	
	@Test
	public void testGetDoelvoor(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		assertEquals(6,test.getDoelvoor());
		assertNotEquals(8,test.getDoelvoor());
	}
	
	@Test
	public void testGetScore(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		assertEquals(7,test.getScore());
		assertNotEquals(8,test.getScore());
	}
	
	
	@Test
	public void testGetBudget(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		assertEquals(254563.0,test.getBudget(),1e-10);
		assertNotEquals(4421485.0,test.getBudget(),1e-10);
	}
	
	@Test
	public void testSetNaam(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		test.setNaam("Ajax");
		assertEquals("Ajax",test.getNaam());
		assertNotEquals("Feyenoord",test.getNaam());
	}
	
	@Test
	public void testSetRank(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		test.setRank(1);
		assertEquals(1,test.getRank());
		assertNotEquals(5,test.getRank());
	}
	
	@Test
	public void testSetSpelerList(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		ArrayList<Speler> changelist=new ArrayList<Speler>();
		Speler Huntelaar=new Speler("Huntelaar",11,"aanvaller",90,70,80,"beschikbaar",9000);
		changelist.add(Huntelaar);
		test.setSpelerList(changelist);
		assertEquals(changelist,test.getSpelerList());
		assertNotEquals(spelerlist,test.getSpelerList());
	}
	
	@Test
	public void testSetWinst(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		test.setWinst(9);
		assertEquals(9,test.getWinst());
		assertNotEquals(3,test.getWinst());
	}
	
	@Test
	public void testSetVerlies(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		test.setVerlies(5);
		assertEquals(5,test.getVerlies());
		assertNotEquals(2,test.getVerlies());
	}
	
	@Test
	public void testSetGelijkpsel(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		test.setGelijkspel(7);
		assertEquals(7,test.getGelijkspel());
		assertNotEquals(1,test.getGelijkspel());
	}
	
	@Test
	public void testSetDoelsaldo(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		test.setDoelsaldo(10);
		assertEquals(10,test.getDoelsaldo());
		assertNotEquals(2,test.getDoelsaldo());
	}
	
	@Test
	public void testSetDoeltegen(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		test.setDoeltegen(20);
		assertEquals(20,test.getDoeltegen());
		assertNotEquals(4,test.getDoeltegen());
	}
	
	@Test
	public void testSetDoelvoor(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		test.setDoelvoor(50);
		assertEquals(50,test.getDoelvoor());
		assertNotEquals(6,test.getDoelvoor());
	}
	
	@Test
	public void testSetScore(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		test.setScore(20);
		assertEquals(20,test.getScore());
		assertNotEquals(7,test.getScore());
	}
	
	
	@Test
	public void testSetBudget(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		test.setBudget(500.0);
		assertEquals(500.0,test.getBudget(),1e-10);
		assertNotEquals(254563.0,test.getBudget(),1e-10);
	}
	
	@Test
	public void testToString(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		assertEquals("Teamnaam:Feyenoord\nRank:5\nSpelers:\n[]\nWinst:3\nVerlies:2\nGelijkspel:1\nDoelsaldo:2\nDoelpunten tegen:4\nDoelpunten voor:6\nBudget:254563.0\nScore:7\n",test.toString());
	}

}
