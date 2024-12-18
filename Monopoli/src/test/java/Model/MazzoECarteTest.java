package Model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
//Testa la classe Mazzo e la classe Carte
class MazzoECarteTest {

		//Controlla che la creazione dei mazzi e il mescolamento non dia eccezioni
	    @Test
	    void testCreazione() { //carte e mazzi
	    	assertDoesNotThrow(() -> new MazzoImprevisti());
	    	assertDoesNotThrow(() -> new MazzoProbabilita());
	    }
	    
	    //Controlla che una volta pescate tutte le carte del mazzo risulti vuoto e che l'aggiunta di una carta non lo renda più vuoto
	    @Test
	    void testRimozioneEAggiuntaCarte() {
	    	Mazzo mazzoImprevist=new MazzoImprevisti();
	    	assertFalse(mazzoImprevist.isEmpty());
	    	for(int i=0; i<15; i++) {
	    		mazzoImprevist.get();
	    	}
	    	assertTrue(mazzoImprevist.isEmpty());
	    	Carta prova=new Carta(0, "Fate 3 passi indietro (con tanti auguri!).", 1, 0, -3); 
	    	((Mazzo)mazzoImprevist).add(prova);
	    	assertFalse(mazzoImprevist.isEmpty());
	    }
	    //Controlla i getter della classe Carta
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
