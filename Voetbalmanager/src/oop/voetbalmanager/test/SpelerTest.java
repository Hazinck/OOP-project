package oop.voetbalmanager.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import oop.voetbalmanager.model.Speler;

import org.junit.Test;

public class SpelerTest {

	@Test
	public void testGetNaam() {
		Speler test=new Speler("Huntelaar",11,"aanvaller",90,70,80,"beschikbaar",9000);
		assertEquals("Huntelaar",test.getNaam());
		assertNotEquals("Van Persie",test.getNaam());
	}
	
	@Test
	public void testGetNummer(){
		Speler test=new Speler("Huntelaar",11,"aanvaller",90,70,80,"beschikbaar",9000);
		assertEquals(11,test.getNummer());
		assertNotEquals(12,test.getNummer());
	}
	
	@Test
	public void testGetType(){
		Speler test=new Speler("Huntelaar",11,"aanvaller",90,70,80,"beschikbaar",9000);
		assertEquals("aanvaller",test.getType());
		assertNotEquals("keeper",test.getType());
	}
	
	@Test
	public void testGetOffense(){
		Speler test=new Speler("Huntelaar",11,"aanvaller",90,70,80,"beschikbaar",9000);
		assertEquals(90,test.getOffense());
		assertNotEquals(80,test.getOffense());
	}
	
	@Test
	public void testGetDefence(){
		Speler test=new Speler("Huntelaar",11,"aanvaller",90,70,80,"beschikbaar",9000);
		assertEquals(70,test.getDefence());
		assertNotEquals(80,test.getDefence());
	}
	
	@Test
	public void testGetUithouding(){
		Speler test=new Speler("Huntelaar",11,"aanvaller",90,70,80,"beschikbaar",9000);
		assertEquals(80,test.getUithouding());
		assertNotEquals(70,test.getUithouding());
	}
	
	@Test
	public void testGetBeschikbaarheid(){
		Speler test=new Speler("Huntelaar",11,"aanvaller",90,70,80,"beschikbaar",9000);
		assertEquals("beschikbaar",test.getBeschikbaarheid());
		assertNotEquals("niet beschikbaar",test.getBeschikbaarheid());
	}
	
	@Test
	public void testGetPrijs(){
		Speler test=new Speler("Huntelaar",11,"aanvaller",90,70,80,"beschikbaar",9000);
		assertEquals(9000,test.getPrijs());
		assertNotEquals(8000,test.getPrijs());
	}
	
	@Test
	public void testSetnaam(){
		Speler test=new Speler("Huntelaar",11,"aanvaller",90,70,80,"beschikbaar",9000);
		test.setNaam("Robben");
		assertEquals("Robben",test.getNaam());
		assertNotEquals("Huntelaar",test.getNaam());
	}
	
	@Test
	public void testSetNummer(){
		Speler test=new Speler("Huntelaar",11,"aanvaller",90,70,80,"beschikbaar",9000);
		test.setNummer(9);
		assertEquals(9,test.getNummer());
		assertNotEquals(11,test.getNummer());
	}
	
	@Test
	public void testSetType(){
		Speler test=new Speler("Huntelaar",11,"aanvaller",90,70,80,"beschikbaar",9000);
		test.setType("keeper");
		assertEquals("keeper",test.getType());
		assertNotEquals("aanvaller",test.getType());
	}
	
	@Test
	public void testSetOffense(){
		Speler test=new Speler("Huntelaar",11,"aanvaller",90,70,80,"beschikbaar",9000);
		test.setOffense(100);
		assertEquals(100,test.getOffense());
		assertNotEquals(90,test.getOffense());
	}
	
	@Test
	public void testSetDefence(){
		Speler test=new Speler("Huntelaar",11,"aanvaller",90,70,80,"beschikbaar",9000);
		test.setDefence(50);
		assertEquals(50,test.getDefence());
		assertNotEquals(70,test.getDefence());
	}
	
	@Test
	public void testSetUithouding(){
		Speler test=new Speler("Huntelaar",11,"aanvaller",90,70,80,"beschikbaar",9000);
		test.setUithouding(40);
		assertEquals(40,test.getUithouding());
		assertNotEquals(80,test.getUithouding());
	}
	
	@Test
	public void testSetBeschikbaarheid(){
		Speler test=new Speler("Huntelaar",11,"aanvaller",90,70,80,"beschikbaar",9000);
		test.setBeschikbaarheid("niet beschikbaar");
		assertEquals("niet beschikbaar",test.getBeschikbaarheid());
		assertNotEquals("beschikbaar",test.getBeschikbaarheid());
	}
	
	@Test
	public void testSetPrijs(){
		Speler test=new Speler("Huntelaar",11,"aanvaller",90,70,80,"beschikbaar",9000);
		test.setPrijs(8000);
		assertEquals(8000,test.getPrijs());
		assertNotEquals(9000,test.getPrijs());
	}
	
	@Test
	public void testToString(){
		Speler test=new Speler("Huntelaar",11,"aanvaller",90,70,80,"beschikbaar",9000);
		assertEquals("Naam:Huntelaar - Nummer:11\nType:aanvaller\nOffense:90\nDefence:70\nConditie:80\nBeschikbaarheid:beschikbaar\nPrijs:9000\n",test.toString());
	}

}
