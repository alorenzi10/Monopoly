package Model;

import org.junit.jupiter.api.*;

import View.MonopolyGUI;
import View.SchermataDiGioco;

import static org.junit.jupiter.api.Assertions.*;

class MonopolyTest {

    private Monopoly monopoly;
    private  MonopolyGUI monopolyGUI;
    private String[] nomiGiocatori = {"Mario", "Luigi", "Marco"};
    private String[] pedine= {"Cane", "Nave", "Cappello"};

    @BeforeEach
    void setUp() {
    	monopolyGUI=new MonopolyGUI(SchermataDiGioco.getSchermataDiGioco(), 3, pedine);
        monopoly = new Monopoly(3, nomiGiocatori, monopolyGUI);
    }

    @Test
    void testSetProssimoGiocatore() {
    	
        Player giocatoreCorrente = monopoly.getGiCorrente();
        monopoly.setProssimoGiocatore();
        assertNotEquals(giocatoreCorrente, monopoly.getGiCorrente());
        monopoly.setProssimoGiocatore();
        monopoly.setProssimoGiocatore();
        assertEquals(giocatoreCorrente, monopoly.getGiCorrente());
        
    }
    
    @Test
    void testMonopoly() {
    	int i=0;
    	for(String p: monopoly.getGiocatoriString()) {
    		assertEquals(nomiGiocatori[i], p);
    		i++;
    	}
    	assertEquals(3, monopoly.getNumGiocatori());
    	assertTrue(monopoly.getTabellone() instanceof Tabellone);
    	
    	monopoly.setPedineSelezionate();
    	assertEquals(pedine, monopoly.getPedineSelezionate());
       
    	

    	assertNull( monopoly.getCorrispondenzaPlayer("monopoli"));
    	assertEquals("Mario", monopoly.getCorrispondenzaPlayer("Mario").getName());
    	assertNull( monopoly.getCorrispondenzaPlayer("monopoli"));
        assertFalse(monopoly.isGameOver());
        
        /*assertDoesNotThrow(()->monopoly.tiraDadi());
        
        monopolyGUI.setDecisioneBancarotta(true);
        monopoly.setBancarotta();
        
        monopolyGUI.setDecisioneBancarotta(true);
        monopoly.setBancarotta();
        
        assertTrue(monopoly.isGameOver());*/
        
    }
}
