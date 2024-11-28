package Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProprietaTest {

	Proprieta property = new Proprieta("Via Roma", 300, 200);
	Player tester = new Player(10, "Mario", 1500, false, 0);
	
	@BeforeEach 
	void setUp() {
		tester.aggiungiProprieta(property); //nel metodo c'è anche incluso setProprietario (metodo di proprietà)
	}
	
	//Controlla che la proprietà risulti posseduta
	@Test
	void testProprietaIsPosseduta() {
		assertTrue(property.posseduta());
		Proprieta property2 = new Proprieta("Via Bergamo", 300, 200);
		assertFalse(property2.posseduta());
	}
	
	
	@Test
	void testGetter() { //e il setter setPosseduta.
		Proprieta property2 = new Proprieta("Via Bergamo", 300, 200);
		property2.setPosseduta(true);
		assertTrue(property2.posseduta());
		assertTrue(property.posseduta());
		assertEquals(300, property.getCosto());
		assertEquals(200, property.getPrezzoIpoteca());
		int disipoteca=(int) (200*1.1);
		assertEquals(disipoteca, property.getCostoDisipoteca());
		assertEquals(0, property.getAffitto());
	}
	//Controlla la corrispondenza tra il giocatore e la proprietà posseduta
	@Test
	void testControllaPossessore() {
		assertEquals(tester, property.getPossessore());
		Player tester2 = new Player(10, "Marco", 1500, false, 0);
		assertNotEquals(tester2, property.getPossessore());
	}
	//Controlla quando si ipoteca e disipoteca
	@Test
	void testIpoteca() {
		property.setIpotecata(true);
		assertTrue(property.isIpotecata());
		
		property.setIpotecata(false);
		assertFalse(property.isIpotecata());
	} 
}
