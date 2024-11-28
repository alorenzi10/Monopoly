package Model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CarteTest {

	    @Test
	    void testCreazione() { //carte e mazzi
	    	
	    	assertDoesNotThrow(() -> new MazzoImprevisti());
	    	assertDoesNotThrow(() -> new MazzoProbabilita());
	    }
	    
	    @Test
	    void testMazzo() {
	    	Mazzo mazzoImprevist=new MazzoImprevisti();
	    	assertFalse(mazzoImprevist.isEmpty());
	    	for(int i=0; i<15; i++) {
	    		Carta carta=mazzoImprevist.get();
	    	}
	    	assertTrue(mazzoImprevist.isEmpty());
	    	Carta prova=new Carta(0, "Fate 3 passi indietro (con tanti auguri!).", 1, 0, -3); 
	    	((Mazzo)mazzoImprevist).add(prova);
	    	assertFalse(mazzoImprevist.isEmpty());
	}
	    
	    @Test
	    void testCarte() {
	    	Carta prova=new Carta(0, "Fate 3 passi indietro (con tanti auguri!).", 1, 0, -3); 
	    	assertEquals(1, prova.getAzione());
	    	assertEquals(-3, prova.getDestinazione());
	    	assertEquals("Fate 3 passi indietro (con tanti auguri!).", prova.getMessaggio());
	    	assertEquals(0, prova.getQtaSoldi());
	    	assertEquals(0, prova.getTipo());
	    	
	    	Carta prova1= new Carta(1, "Pagate per contributi di miglioria stradale. 40€ per ogni casa e 115€ per ogni albergo che possedete.", 4, new int[] {40, 115});
	    	assertEquals(40, prova1.getCostoCase());
	    	assertEquals(115, prova1.getCostoAlberghi());
	}

}
