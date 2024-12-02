package Model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.Test;

class TabelloneTest {
	
	//Controlla i metodi della classe Tabellone
    @Test 
    void testTabellone() {
    	Dadi dadi=new Dadi();
    	assertDoesNotThrow(()->new Tabellone(dadi)); //non causa eccezioni
    	
    	Tabellone tabellone=new Tabellone(dadi);
    	
    	//test dei vari get
    	assertEquals("marrone", tabellone.getMarrone().getNome());
    	assertEquals("azzurro", tabellone.getAzzurro().getNome());
    	assertEquals("viola", tabellone.getViola().getNome());
    	assertEquals("arancio", tabellone.getArancione().getNome());
    	assertEquals("rosso", tabellone.getRosso().getNome());
    	assertEquals("giallo", tabellone.getGiallo().getNome());
    	assertEquals("verde", tabellone.getVerde().getNome());
    	assertEquals("blu", tabellone.getBlu().getNome());
    	
    	//controlla che non ritorni caselle nulle
    	for(int i=0; i<40; i++) {
    		assertNotNull(tabellone.getCasella(i));
    	}
    	
    	assertNotNull(tabellone.getSocieta());
    	assertEquals(2,tabellone.getSocieta().length); //ci sono solo 2 società
    	assertNotEquals(tabellone.getSocieta()[0], tabellone.getSocieta()[1]);
    	
    	assertNotNull(tabellone.getStazioni());
    	assertEquals(4,tabellone.getStazioni().length); //ci sono solo 4 stazioni
    	assertNotEquals(tabellone.getStazioni()[0], tabellone.getStazioni()[1]);
    }
}
