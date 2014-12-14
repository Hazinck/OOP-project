package src.oop.voetbalmanager.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void testGetNaam(){
		User test=new User("Zaidman","PSV");
		assertEquals("Zaidman",test.getNaam());
		assertNotEquals("PSV",test.getNaam());
	}
	
	@Test
	public void testGetTeam(){
		User test=new User("Zaidman","PSV");
		assertEquals("PSV",test.getTeam());
		assertNotEquals("Zaidman",test.getTeam());
	}
	
	@Test
	public void testSetNaam(){
		User test=new User("Zaidman","PSV");
		test.setNaam("Iosup");
		assertEquals("Iosup",test.getNaam());
		assertNotEquals("Zaidman",test.getNaam());
	}
	
	@Test
	public void testSetTeam(){
		User test=new User("Zaidman","PSV");
		test.setTeam("Ajax");
		assertEquals("Ajax",test.getTeam());
		assertNotEquals("PSV",test.getTeam());
	}
	
	@Test
	public void testToString(){
		User test=new User("Zaidman","PSV");
		assertEquals("Naam:Zaidman/nTeam:PSV",test.toString());
	}

}
