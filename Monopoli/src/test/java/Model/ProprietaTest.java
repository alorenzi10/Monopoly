package Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProprietaTest {

	Proprieta property = new Proprieta("Via Roma", 300, 200);
	Player tester = new Player(10, "Mario", 1500, false, 0);
	
	@BeforeEach // Eseguito prima di ciascun test
	void setUp() {
		tester.aggiungiProprieta(property);
	}
	
	@Test
	void testProprietaIsPosseduta() {
		assertTrue(property.posseduta());
	}
	
	@Test
	void testGetter() {
		property.setPosseduta(true);
		assertTrue(property.posseduta());
		assertEquals(300, property.getCosto());
		assertEquals(200, property.getPrezzoIpoteca());
		int disipoteca=(int) (200*1.1);
		assertEquals(disipoteca, property.getCostoDisipoteca());
		assertEquals(0, property.getAffitto());
	}

	@Test
	void testPossessoreHaProprieta() {
		assertTrue(tester.getListaProprieta().contains(property));
	}
	
	@Test
	void testControllaPossessore() {
		assertEquals(tester, property.getPossessore());
	}
	
	@Test
	void testIpotecata() {
		property.setIpotecata(true);
		assertTrue(property.isIpotecata());
	}
	
	@Test
	void testDispotecata() {
		property.setIpotecata(true);
		property.setIpotecata(false);
		assertFalse(property.isIpotecata());
	}
}
