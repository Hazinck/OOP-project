package oop.voetbalmanager.test;

import static org.junit.Assert.*;
import oop.voetbalmanager.model.Positie;

import org.junit.Test;

public class PositieTest {

	@Test
	public void testToString() {
		Positie test=new Positie(5,3,"test");
		assertEquals("test op: 5,3",test.toString());
	}
	
	@Test
	public void testGetX(){
		Positie test=new Positie(5,3,"test");
		assertEquals(5,test.getX());
		assertNotEquals(3,test.getX());
	}
	
	@Test
	public void testGetY(){
		Positie test=new Positie(5,3,"test");
		assertEquals(3,test.getY());
		assertNotEquals(5,test.getY());
	}
	
	@Test
	public void testGetType(){
		Positie test=new Positie(5,3,"test");
		assertEquals("test",test.getType());
	}
	
	@Test
	public void testSetX(){
		Positie test=new Positie(5,3,"test");
		test.setX(10);
		assertEquals(10,test.getX());
		assertNotEquals(5,test.getX());
	}
	
	@Test
	public void testSetY(){
		Positie test=new Positie(5,3,"test");
		test.setY(7);
		assertEquals(7,test.getY());
		assertNotEquals(3,test.getY());
	}
	
	@Test
	public void testSetType(){
		Positie test=new Positie(5,3,"test");
		test.setType("gg");
		assertEquals("gg",test.getType());
		assertNotEquals("test",test.getType());
	}
	

}
