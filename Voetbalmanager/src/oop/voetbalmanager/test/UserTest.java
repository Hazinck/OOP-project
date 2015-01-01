package oop.voetbalmanager.test;

import static org.junit.Assert.*;
import oop.voetbalmanager.model.Divisie;
import oop.voetbalmanager.model.Team;
import oop.voetbalmanager.model.User;
import oop.voetbalmanager.model.XMLreader;

import org.junit.Test;

public class UserTest {
	XMLreader reader = new XMLreader();
	Divisie divisie = reader.readDivisie("database.xml");
	Team ajax = divisie.getTeamList().get(1);
	Team denHaag = divisie.getTeamList().get(0);
	
	@Test
	public void testGetNaam(){
		
		User test=new User("Zaidman",ajax);//"PSV"
		assertEquals("Zaidman",User.getNaam());
		assertNotEquals(ajax,User.getNaam());
	}
	
	@Test
	public void testGetTeam(){
		User test=new User("Zaidman",ajax);
		assertEquals(ajax,test.getTeam());
		assertNotEquals("Zaidman",test.getTeam());
	}
	
	@Test
	public void testSetNaam(){
		User test=new User("Zaidman",ajax);
		test.setNaam("Iosup");
		assertEquals("Iosup",test.getNaam());
		assertNotEquals("Zaidman",test.getNaam());
	}
	
	@Test
	public void testSetTeam(){
		User test=new User("Zaidman",ajax);
		test.setTeam(denHaag);
		assertEquals(denHaag,test.getTeam());
		assertNotEquals(ajax,test.getTeam());
	}
	
	@Test
	public void testToString(){
		User test=new User("Zaidman",ajax);
		assertEquals("Naam:Zaidman/nTeam:Ajax",test.toString());
	}

}
