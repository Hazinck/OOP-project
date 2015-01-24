package oop.voetbalmanager.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import oop.voetbalmanager.model.Bot;
import oop.voetbalmanager.model.Divisie;
import oop.voetbalmanager.model.Opstelling;
import oop.voetbalmanager.model.RNG;
import oop.voetbalmanager.model.Speler;
import oop.voetbalmanager.model.Team;
import oop.voetbalmanager.model.Wedstrijdteam;
import oop.voetbalmanager.model.XMLreader;

import org.junit.Test;

public class BotTest {
ArrayList<Opstelling> opstellingen=new ArrayList<Opstelling>();

	@Test
	public void testGetDivisie() {
		ArrayList<Team> testing=new ArrayList<Team>();
		Divisie eredivisie=new Divisie("Eredivisie",testing,25,5, "images/user_default.png");
		Bot.setDivisie(eredivisie);
		assertEquals(eredivisie,Bot.getDivisie());
	}
	
	@Test
	public void testSetDivisie(){
		ArrayList<Team> testing=new ArrayList<Team>();
		Divisie eredivisie=new Divisie("Champions league",testing,25,5, "images/user_default.png");
		Bot.setDivisie(eredivisie);
		assertEquals(eredivisie,Bot.getDivisie());
	}
	
	@Test
	public void testSetUserTeam(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		Bot.setUserTeam(test);
		assertEquals(test,Bot.getUserTeam());
	}
	
	@Test
	public void testSetBotteam(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		Bot.setBotTeam(test);
		assertEquals(test,Bot.getBotTeam());
	}
	
	@Test
	public void testGetBotteam(){
		ArrayList<Speler> spelerlist=new ArrayList<Speler>();
		Team test=new Team("Feyenoord", 5, spelerlist, 3, 2, 1, 2, 4, 6, 254563, 7);
		Bot.setBotTeam(test);
		assertEquals(test,Bot.getBotTeam());
	}
	
	@Test
	public void testSetWteam(){
		XMLreader reader = new XMLreader();
		Divisie divisie = reader.readDivisie("database.xml");
		Team ajax = divisie.getTeamList().get(1);
		Wedstrijdteam feyenoord=new Wedstrijdteam(ajax);
		Bot.setWteam(feyenoord);
		assertEquals(feyenoord,Bot.getWteam());
	}
	
	@Test
	public void testGetWteam(){
		XMLreader reader = new XMLreader();
		Divisie divisie = reader.readDivisie("database.xml");
		Team ajax = divisie.getTeamList().get(1);
		Wedstrijdteam feyenoord=new Wedstrijdteam(ajax);
		Bot.setWteam(feyenoord);
		assertEquals(feyenoord,Bot.getWteam());
	}
	
	@Test
	public void testTeamToWteam(){
		XMLreader reader = new XMLreader();
		Divisie divisie = reader.readDivisie("database.xml");
		Team ajax = divisie.getTeamList().get(1);
		Bot.setBotTeam(ajax);
		
		ArrayList <Opstelling> opstellingen = reader.readOpstellingList("database.xml");
		int opIdx = RNG.getalTot(opstellingen.size());
		int tactiek = RNG.getalTot(101);
		Bot.teamToWTeam(opstellingen, 0, 20);
		//assertEquals(Bot.getWteam(), Bot.getWteam());
		
		Wedstrijdteam ajaxWT=new Wedstrijdteam(ajax);
		ajaxWT.getWSpelers()[0] = ajax.getSpelerList().get(25);
		for(int i=1; i<11; i++){
			ajaxWT.getWSpelers()[0] = ajax.getSpelerList().get(i);
		//	System.out.println(ajax.getSpelerList().get(i).getNaam());
		}
		ajaxWT.setOpstelling(opstellingen.get(0));
		ajaxWT.setTactiek(20);
		assertEquals(Bot.getWteam(), ajaxWT);
	}

}
