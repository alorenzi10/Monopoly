package Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CantiereTest {

	Cantiere site;
	GruppoColore rosa;
	
	@BeforeEach //Una volta, all'inizio
	void setUp() {
		rosa = new GruppoColore("Rosa");
		site = new Cantiere("Via Roma", 10, 150, 100, new int[] {1, 2, 3, 4, 5, 6}, rosa, 100);
	}
	
	@Test
	void testCostruisci() {
		site.costruisci();
		assertEquals(1, site.getNumCase());
	}
	
	@Test
	void testHaCase() {
		site.costruisci();
		assertTrue(site.haCase());
	}
	
	@Test
	void testNonHaCase() {
		site.costruisci();
		site.demolisci();
		assertFalse(site.haCase());
	}
	
	@Test
	void testQuantiAlberghi() {
		for (int i=0; i<site.NUM_MAX_UNITA; i++)
			site.costruisci();
		assertEquals(1, site.getNumAlberghi());
	}
	
	@Test
	void testAffitto() {
		site.costruisci();
		site.costruisci();
		assertEquals(3, site.getAffitto());
		site.demolisci();
		assertEquals(2, site.getAffitto());
	}

}
