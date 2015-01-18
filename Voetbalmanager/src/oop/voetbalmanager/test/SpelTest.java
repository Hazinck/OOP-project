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
	
	@Test
	public void testVerslag(){
		XMLreader help=new XMLreader();
		Wedstrijdteam user=help.readWedstrijdteam(ajax, "database.xml");
		Wedstrijdteam bot=help.readWedstrijdteam(denHaag, "database.xml");
		User.setWteam(user);
		Bot.setWteam(bot);
		Spel test=new Spel(user, bot, 1);
		assertEquals("[Welkom bij het liveverslag van Ajax thuis tegen ADO Den Haag., De opstellingen van beide teams zijn bekend en volgen nu., Opstelling Ajax: Lucas Andersen, Lerin Duarte, Lasse Schøne, Kolbeinn Sigþórsson, Kenny Tete, Joël Veltman, Jaïro Riedewald, Davy Klaassen, Arkadiusz Milik, ﻿Anwar El Ghazi, Jasper Cillessen, , Opstelling ADO Den Haag: Mitchell Schet, Mitchell de Vlugt, Mike van Duinen, Michiel Kramer, Mathias Gehrt, Kevin Jansen, Gianni Zuiverloon, Dion Malone, Danny Bakker, ﻿Aaron Meijers, Robert Zwinkels, ]",test.verslag().toString());
	}
	
	@Test
	public void testWinner(){
		XMLreader help=new XMLreader();
		Wedstrijdteam user=help.readWedstrijdteam(ajax, "database.xml");
		Wedstrijdteam bot=help.readWedstrijdteam(denHaag, "database.xml");
		User.setWteam(user);
		Bot.setWteam(bot);
		Spel test=new Spel(user, bot, 1);
		String winner = test.winner(1,1).getNaam();		
		assertEquals(ajax.getNaam(), winner);
		
		Spel test2=new Spel(user, bot, 500);
		winner = test2.winner(1,1).getNaam();		
		assertEquals(denHaag.getNaam(), winner);
		
		Spel test3=new Spel(bot, user, 1);
		winner = test3.winner(1,1).getNaam();		
		assertEquals(ajax.getNaam(), winner);
		
		Spel test4=new Spel(user, user, 1);		
		assertEquals(null, test4.winner(1,1));
		
	}
	
}
