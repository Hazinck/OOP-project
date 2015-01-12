package oop.voetbalmanager.test;

import static org.junit.Assert.*;

import java.awt.Dimension;

import oop.voetbalmanager.model.Bot;
import oop.voetbalmanager.model.Divisie;
import oop.voetbalmanager.model.Spel;
import oop.voetbalmanager.model.Speler;
import oop.voetbalmanager.model.Team;
import oop.voetbalmanager.model.User;
import oop.voetbalmanager.model.Wedstrijdteam;
import oop.voetbalmanager.model.XMLreader;

import org.junit.Test;

public class SpelTest {
	XMLreader reader = new XMLreader();
	Divisie divisie = reader.readDivisie("database.xml");
	Team ajax = divisie.getTeamList().get(1);
	Team denHaag = divisie.getTeamList().get(0);
	Wedstrijdteam feyenoord=new Wedstrijdteam(ajax);
	Wedstrijdteam ado=new Wedstrijdteam(denHaag);
	
	@Test
	public void testSpelerAnalyse() {
		Speler test=new Speler("Huntelaar",11,"aanvaller",90,70,80,"beschikbaar",9000);
		Spel nieuw= new Spel(feyenoord, ado, 5);
		assertEquals(118,nieuw.spelerAnalyse(test));
	}
	
	@Test
	public void testTeamAnalyse(){
		Spel nieuw= new Spel(feyenoord, ado, 5);
		assertEquals(21,nieuw.teamAnalyse(feyenoord, 1));
	}
	
	@Test
	public void testgetScore(){
		Spel nieuw= new Spel(feyenoord, ado, 5);
		Dimension test=new Dimension();
		assertEquals(test, nieuw.getScore());
	}
	



}
