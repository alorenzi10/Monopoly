package Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CantiereTest {

	Cantiere site;
	GruppoColore rosa;
	
	@BeforeEach 
	void setUp() {
		rosa = new GruppoColore("Rosa");
		site = new Cantiere("Via Roma", 10, 150, 100, new int[] {1, 2, 3, 4, 5, 6}, rosa, 100);
	}
	
	//Controlla che i metodi costruisci e demolisci esprimano un cambiamento nel numero di costruzioni
	@Test
	void testCostruisci() {
		site.costruisci();
		assertEquals(1, site.getNumCostruzioni());
		site.costruisci();
		site.costruisci();
		site.costruisci();
		site.costruisci();
		assertEquals(5, site.getNumCostruzioni());
		site.demolisci();
		assertEquals(4, site.getNumCostruzioni());
	}
	
	//Controlla del metodo boolean haCase()
	@Test
	void testHaCase() {
		site.costruisci();
		assertTrue(site.haCase());
		site.demolisci();
		assertFalse(site.haCase());
	}
	
	//Controlla che da 1-4 costruzioni corrispondono 1-4 case e a 5 costruzioni corrispondano 0 case
	@Test
	void testQuanteCase() {
		assertEquals(0, site.getNumCase());
		site.costruisci();
		site.costruisci();
		assertEquals(2, site.getNumCase());
		site.costruisci();
		site.costruisci();
		site.costruisci();
		assertEquals(0, site.getNumCase());	
	}
	
	//Controlla che a 5 costruzioni corrisponde un albergo
	@Test
	void testQuantiAlberghi() {
		assertEquals(0, site.getNumAlberghi());
		for (int i=0; i<site.NUM_MAX_UNITA; i++)
			site.costruisci();
		assertEquals(1, site.getNumAlberghi());
		assertEquals(0, site.getNumCase());
	}
	
	//Controlla che l'affitto sia corretto in base al possesso o meno dell'intero gruppo colore o al numero di costruzioni
	@Test
	void testAffitto() {
		GruppoColore marrone= new GruppoColore("marrone");
		Proprieta prop1=new Cantiere("Vicolo Corto",1, 60, 30, new int[] {2,10,30,90,160,250}, marrone, 50);
		Proprieta prop2=new Cantiere("Vicolo Stretto",2, 60, 30, new int[] {4,20,60,180,320,450}, marrone, 50);
		Player tester1 = new Player(1, "Mario", 1500, false, 0);
		
		tester1.aggiungiProprieta(prop1);
		assertEquals(2, prop1.getAffitto());
		
		tester1.aggiungiProprieta(prop2);
		assertEquals(4, prop1.getAffitto());
		
		((Cantiere) prop1).costruisci();
		assertEquals(10, prop1.getAffitto());
		
		((Cantiere) prop2).costruisci();
		((Cantiere) prop1).costruisci();
		assertEquals(30, prop1.getAffitto());
	}
	
	@Test
	void testGetter() {
		assertEquals(0, site.getNumCostruzioni());
		assertEquals(10, site.getId());
		assertEquals(100, site.getCostoCasa());
		assertEquals(100 ,site.getPrezzoIpoteca());
		site.setNumCostruzioni(2);
		assertEquals(2, site.getNumCostruzioni());
	}

}
