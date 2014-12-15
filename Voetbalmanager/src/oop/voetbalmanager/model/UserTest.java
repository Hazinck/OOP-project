package oop.voetbalmanager.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {
	XMLreader reader = new XMLreader();
	Divisie divisie = reader.readDivisie();
	Team ajax = divisie.getTeamList().get(1);
	Team denHaag = divisie.getTeamList().get(0);
	
	@Test
	public void testGetNaam(){
		
		User test=new User("Zaidman",ajax);//"PSV"
		assertEquals("Zaidman",test.getNaam());
		assertNotEquals(ajax,test.getNaam());
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
