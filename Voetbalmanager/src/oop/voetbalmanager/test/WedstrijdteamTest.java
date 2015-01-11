package oop.voetbalmanager.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import oop.voetbalmanager.model.Divisie;
import oop.voetbalmanager.model.Opstelling;
import oop.voetbalmanager.model.Positie;
import oop.voetbalmanager.model.Speler;
import oop.voetbalmanager.model.Team;
import oop.voetbalmanager.model.Wedstrijdteam;
import oop.voetbalmanager.model.XMLreader;

import org.junit.Test;

public class WedstrijdteamTest {
	XMLreader reader = new XMLreader();
	Divisie divisie = reader.readDivisie("database.xml");
	Team amsterdam = divisie.getTeamList().get(1);
	ArrayList<Positie> posities=new ArrayList<Positie>();
	Opstelling testing=new Opstelling("opstelling",posities);
	Opstelling opstelling=new Opstelling("cheats",posities);
	Speler huntelaar=new Speler("Huntelaar",11,"aanvaller",90,70,80,"beschikbaar",9000);
	
	@Test
	public void testTeamoffense() {
		Wedstrijdteam test=new Wedstrijdteam(amsterdam);
		test.teamOffence();
		assertEquals(50,test.getOff());
		assertNotEquals(45,test.getOff());
	}
	
	@Test
	public void testTeamDefence(){
		Wedstrijdteam test=new Wedstrijdteam(amsterdam);
		test.teamDefence();
		assertEquals(55,test.getDef());
		assertNotEquals(42,test.getDef());
	}
	
	@Test
	public void testTeamUithouding(){
		Wedstrijdteam test=new Wedstrijdteam(amsterdam);
		test.teamUithouding();
		assertEquals(66,test.getUith());
		assertNotEquals(42,test.getUith());
	}
	
	@Test
	public void testGetOff(){
		Wedstrijdteam test=new Wedstrijdteam(amsterdam);
		assertEquals(50, test.getOff());
	}
	
	@Test
	public void testGetDef(){
		Wedstrijdteam test=new Wedstrijdteam(amsterdam);
		assertEquals(55,test.getDef());
	}
	
	@Test
	public void testGetUith(){
		Wedstrijdteam test=new Wedstrijdteam(amsterdam);
		assertEquals(66,test.getUith());
	}
	
	@Test
	public void testSetOff(){
		Wedstrijdteam test=new Wedstrijdteam(amsterdam);
		test.setOff(42);
		assertEquals(42,test.getOff());
		assertNotEquals(50,test.getOff());
	}
	
	@Test
	public void testSetDef(){
		Wedstrijdteam test=new Wedstrijdteam(amsterdam);
		test.setDef(43);
		assertEquals(43,test.getDef());
		assertNotEquals(55,test.getDef());
	}
	
	@Test
	public void testSetUith(){
		Wedstrijdteam test=new Wedstrijdteam(amsterdam);
		test.setUith(100);
		assertEquals(100,test.getUith());
		assertNotEquals(66,test.getUith());
	}
	
	@Test
	public void testSetOpstelling(){
		Wedstrijdteam test=new Wedstrijdteam(amsterdam);
		test.setOpstelling(testing);
		assertEquals(testing,test.getOpstelling());
	}
	
	@Test
	public void testGetOpstelling(){
		Wedstrijdteam test=new Wedstrijdteam(amsterdam);
		test.setOpstelling(opstelling);
		assertEquals(opstelling,test.getOpstelling());
	}
	
	@Test
	public void testGetTactiek(){
		Wedstrijdteam test=new Wedstrijdteam(amsterdam);
		assertEquals(0,test.getTactiek());
		assertNotEquals(5,test.getTactiek());
	}
	
	@Test
	public void testSetTactiek(){
		Wedstrijdteam test=new Wedstrijdteam(amsterdam);
		test.setTactiek(20);
		assertEquals(20,test.getTactiek());
		assertNotEquals(0,test.getTactiek());
	}
	
	@Test
	public void testGetWSpeler(){
		Wedstrijdteam test=new Wedstrijdteam(amsterdam);
		Speler[] spelers= new Speler[3];
		test.setWSpelers(spelers);
		assertEquals(spelers.toString(),test.getWSpelers().toString());
	}
	
	@Test
	public void testSetWSpeler(){
		Wedstrijdteam test=new Wedstrijdteam(amsterdam);
		Speler[] spelers= new Speler[1];
		test.setWSpelers(spelers);
		assertEquals(spelers.toString(),test.getWSpelers().toString());
	}
	
	@Test
	public void testToString(){
		Wedstrijdteam test=new Wedstrijdteam(amsterdam);
		assertEquals("Wedstrijdteam: Ajax\nOffensieve kracht: 50\nDefensieve kracht: 55\nUithoudingsvermogen: 66\nnull\nTactiek: 0\nSpelers:\n[null, null, null, null, null, null, null, null, null, null, null]",test.toString());
	}

}
