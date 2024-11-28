package Model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import View.MonopolyGUI;
import View.SchermataDiGioco;

class AstaTest {

	private Player tester1;
    private Player tester2;
    private Player tester3;
    private ArrayList<Player> players;
    private Proprieta proprieta;
    private Asta asta;

    @BeforeEach
    void setUp() {
    	
        tester1 = new Player(1, "Mario", 1500, false, 0);
        tester2 = new Player(2, "Luigi", 1500, false, 0);
        tester3 = new Player(3, "Marco", 1500, false, 0);

        players = new ArrayList<>();
        players.add(tester1);
        players.add(tester2);
        players.add(tester3);

        proprieta = new Proprieta("Via Roma", 300, 200);

        asta = new Asta(tester1, players, proprieta, null);
    }

    @Test
    void testInizioAsta() {
        assertEquals("Mario", asta.getName());
    }

	@Test
	void testControllaOfferta() {
		asta.aggiornaOfferta(10);
		assertTrue(asta.controlloOfferta());
	}
	
    @Test
    void testAggiornaOfferta() {
        assertTrue(asta.aggiornaOfferta(20));
        assertEquals(30, asta.getOfferta());

        assertFalse(asta.aggiornaOfferta(2000));
        assertEquals(30, asta.getOfferta());
    }

    @Test
    void testProssimoGiocatore() {
        assertEquals("Mario", asta.getName());
        asta.prossimoGiocatore();
        assertEquals("Luigi", asta.getName());
        asta.prossimoGiocatore();
        assertEquals("Marco", asta.getName());
        asta.prossimoGiocatore();
        assertEquals("Mario", asta.getName());
    }

    @Test
    void testRitirati() {
    	Player tester4 = new Player(4, "Mauro", 1500, false, 0);
    	 players.add(tester4);
    	asta = new Asta(tester4, players, proprieta, null);
        asta.ritirati();
        assertEquals("Mario", asta.getName());
        asta.ritirati();
        assertEquals("Luigi", asta.getName());
        
    }

    @Test
    void testControlloFineAsta() {
        assertFalse(asta.controlloFineAsta());

        asta.ritirati();
        asta.ritirati();
        assertEquals(true, asta.controlloFineAsta());
    }
    
    @Test
    void testConGUI() {
    	String[] prova=new String[2];
    	prova[0]="Cane";
    	prova[1]="Nave";
        MonopolyGUI monopolyGUI=new MonopolyGUI(SchermataDiGioco.getSchermataDiGioco(), 2, prova );
        asta = new Asta(tester1, players, proprieta, monopolyGUI);
        assertDoesNotThrow(() -> asta.inizio());
        assertDoesNotThrow(() -> asta.fineAsta());
        assertEquals(1490, tester1.getWallet());
        assertTrue(tester1.getListaProprieta().contains(proprieta));
    }
}
