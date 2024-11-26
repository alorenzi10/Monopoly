package Model;

import org.junit.jupiter.api.*;

import View.MonopolyGUI;
import View.SchermataDiGioco;

import static org.junit.jupiter.api.Assertions.*;

class MonopolyTest {

    private Monopoly monopoly;
    private String[] nomiGiocatori = {"Mario", "Luigi", "Marco"};

    @BeforeEach
    void setUp() {
        
        monopoly = new Monopoly(3, nomiGiocatori, new MonopolyGUI(SchermataDiGioco.getSchermataDiGioco(), 3, nomiGiocatori));
    }

    @Test
    void testSetProssimoGiocatore() {
        Player giocatoreCorrente = monopoly.getGiCorrente();
        monopoly.setProssimoGiocatore();
        assertNotEquals(giocatoreCorrente, monopoly.getGiCorrente());
    }
}
