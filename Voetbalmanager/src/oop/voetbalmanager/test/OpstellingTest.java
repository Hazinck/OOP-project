package oop.voetbalmanager.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import oop.voetbalmanager.model.Opstelling;
import oop.voetbalmanager.model.Positie;

import org.junit.Test;

public class OpstellingTest {
ArrayList<Positie> posities = new ArrayList<Positie>();
ArrayList<Positie> positions=new ArrayList<Positie>();
Positie positie=new Positie(5, 3, "daboyz");

	@Test
	public void testToString() {
		Opstelling test=new Opstelling("test",posities);
		assertEquals("Opstelling: test:\n[]",test.toString());
	}
	
	@Test
	public void testGetNaam(){
		Opstelling test=new Opstelling("test",posities);
		assertEquals("test",test.getNaam());
		assertNotEquals("opstelling",test.getNaam());
	}
	
	@Test
	public void testSetNaam(){
		Opstelling test=new Opstelling("test",posities);
		test.setNaam("opstelling");
		assertEquals("opstelling",test.getNaam());
		assertNotEquals("test",test.getNaam());
	}
	
	@Test
	public void testGetPosities(){
		Opstelling test=new Opstelling("test",posities);
		assertEquals(posities,test.getPosities());
	}
	
	@Test
	public void testSetPosities(){
		Opstelling test=new Opstelling("test",posities);
		positions.add(positie);
		test.setPosities(positions);
		assertEquals(positions,test.getPosities());
		assertNotEquals(posities,test.getPosities());
	}

}
