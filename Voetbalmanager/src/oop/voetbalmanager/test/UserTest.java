package oop.voetbalmanager.test;

import static org.junit.Assert.*;
import oop.voetbalmanager.model.Divisie;
import oop.voetbalmanager.model.Team;
import oop.voetbalmanager.model.User;
import oop.voetbalmanager.model.Wedstrijdteam;
import oop.voetbalmanager.model.XMLreader;

import org.junit.Test;

public class UserTest {
	XMLreader reader = new XMLreader();
	Divisie divisie = reader.readDivisie("database.xml");
	Team ajax = divisie.getTeamList().get(1);
	Team denHaag = divisie.getTeamList().get(0);
	Wedstrijdteam feyenoord=new Wedstrijdteam(ajax);
	Wedstrijdteam ado=new Wedstrijdteam(denHaag);
	
	@Test
	public void testGetNaam(){
		User test=new User("Zaidman",ajax);//"PSV"
		assertEquals("Zaidman",User.getNaam());
		assertNotEquals(ajax,User.getNaam());
	}
	
	@Test
	public void testGetTeam(){
		User test=new User("Zaidman",ajax);
		assertEquals(ajax,User.getTeam());
		assertNotEquals("Zaidman",User.getTeam());
	}
	
	@Test
	public void testSetNaam(){
		User test=new User("Zaidman",ajax);
		User.setNaam("Iosup");
		assertEquals("Iosup",User.getNaam());
		assertNotEquals("Zaidman",User.getNaam());
	}
	
	@Test
	public void testSetTeam(){
		User test=new User("Zaidman",ajax);
		User.setTeam(denHaag);
		assertEquals(denHaag,User.getTeam());
		assertNotEquals(ajax,User.getTeam());
	}
	
	@Test
	public void testToString(){
		User test=new User("Zaidman",ajax);
		assertEquals("Naam:Zaidman/nTeam:Ajax",test.toString());
	}
	
	@Test
	public void testGetWteam(){
		User test=new User("Zaidman",ajax);
		User.setWteam(feyenoord);
		assertEquals(feyenoord,User.getWteam());
		assertNotEquals(ado,User.getWteam());
	}
	
	@Test
	public void testSetWteam(){
		User test=new User("Zaidman",ajax);
		User.setWteam(ado);
		assertEquals(ado,User.getWteam());
		assertNotEquals(feyenoord,User.getWteam());
	}
	

}
