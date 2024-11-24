package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player player;
    private Proprieta property;

    @BeforeEach
    void setUp() {
        player = new Player(1, "Mario", 1500, false, 0);
        property = new Proprieta("Via Roma", 300, 200);
    }

    @Test
    void testControlloFondi() {
        assertTrue(player.controlloFondi(500)); // Ha fondi sufficienti
        assertFalse(player.controlloFondi(2000)); // Non ha fondi sufficienti
    }

    @Test
    void testDoTransaction() {
        player.doTransaction(-500); // Sottrae 500
        assertEquals(1000, player.getWallet());

        player.doTransaction(300); // Aggiunge 300
        assertEquals(1300, player.getWallet());
    }

    @Test
    void testMuovi() {
        player.muovi(10); // Si sposta di 10
        assertEquals(10, player.getLocation());

        player.muovi(35); // Supera il limite del tabellone
        assertEquals(5, player.getLocation());
        assertTrue(player.passaggioVia());

        player.muovi(-10); // Movimento indietro
        assertEquals(35, player.getLocation());
    }

    @Test
    void testAggiungiProprieta() {
        player.aggiungiProprieta(property);
        assertEquals(1, player.getListaProprieta().size());
        assertTrue(player.getListaProprieta().contains(property));
        assertEquals(player, property.getPossessore());
    }

    @Test
    void testRimuoviProprieta() {
        player.aggiungiProprieta(property);
        player.rimuoviProprieta(property);
        assertEquals(0, player.getListaProprieta().size());
        assertFalse(player.getListaProprieta().contains(property));
    }

    @Test
    void testVaiInPrigione() {
        player.vaiInPrigione();
        assertEquals(Tabellone.POS_PRIGIONE, player.getLocation());
        assertTrue(player.getInPrigione());
    }

    @Test
    void testLiberaDaPrigione() {
        player.vaiInPrigione();
        player.liberaDaPrigione();
        assertFalse(player.getInPrigione());
    }

    @Test
    void testFallitoTentativo() {
        player.vaiInPrigione();
        player.fallitoTentativo();
        assertEquals(1, player.getTentativi());

        player.fallitoTentativo();
        player.fallitoTentativo();
        assertTrue(player.tentativiTerminati());
    }

    @Test
    void testGetNumProprieta() {
        Proprieta stazione = new Stazione("Stazione Termini", 200, 100, new int[] {25, 50, 100, 200});
        player.aggiungiProprieta(property);
        player.aggiungiProprieta(stazione);

        assertEquals(1, player.getNumStazioniPossedute());
        assertEquals(0, player.getNumSocietaPossedute());
    }

    @Test
    void testGestioneCarte() {
        Carta cartaEsciGratis = new Carta(0, "Esci gratis dal carcere", Mazzo.AZIONE_ESCI_DAL_CARCERE);
        player.addCarta(cartaEsciGratis);

        assertTrue(player.haUscitaGratis());
        assertEquals(cartaEsciGratis, player.getCarta());
        assertFalse(player.haUscitaGratis()); // Dopo averla usata non c'è più
    }
}
