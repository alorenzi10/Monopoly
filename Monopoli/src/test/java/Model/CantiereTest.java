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
		site = new Cantiere("Via Roma", 10, 150, 100, new int[] {1, 2, 3}, rosa, 100);
	}
	
	@Test
	void testCostruisci() {
		site.costruisci();
		assertEquals(site.getNumCostruzioni(), site.getNumCase());
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

}
