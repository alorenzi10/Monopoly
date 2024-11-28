package Model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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
        assertFalse(player.tentativiTerminati());

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
        Proprieta societa = new Societa("Società elettrica", 150, 75, new int[] {4,10}, null);
        player.aggiungiProprieta(societa);
        assertEquals(1, player.getNumSocietaPossedute());
        
    }
    
    @Test
    void testGestioneCostruzioni() {
    	GruppoColore marrone = new GruppoColore("marrone");
        Proprieta cantiere= new Cantiere("Vicolo Corto",1, 60, 30, new int[] {2,10,30,90,160,250}, marrone, 50);
        player.aggiungiProprieta(property);
        player.aggiungiProprieta(cantiere);
        assertEquals(0,player.getNumCasePossedute());
        assertEquals(0,player.getNumAlberghiPosseduti());
        
        ((Cantiere) cantiere).costruisci();
        ((Cantiere) cantiere).costruisci();
        ((Cantiere) cantiere).costruisci();
        assertEquals(3,player.getNumCasePossedute());
        assertEquals(0, player.getNumAlberghiPosseduti());
        
        ((Cantiere) cantiere).costruisci();
        ((Cantiere) cantiere).costruisci();
        assertEquals(0,player.getNumCasePossedute());
        assertEquals(1, player.getNumAlberghiPosseduti());
        
    }
    
   @Test
    void testToStringListaProprieta() {
	   assertEquals("", player.toStringListaProprieta());
	   
	    player.aggiungiProprieta(property);
	    assertEquals("Via Roma", player.toStringListaProprieta());
	    
    	GruppoColore marrone = new GruppoColore("marrone");
   		Proprieta cantiere= new Cantiere("Vicolo Corto",1, 60, 30, new int[] {2,10,30,90,160,250}, marrone, 50);
    	player.aggiungiProprieta(cantiere);
    	assertEquals("Via Roma, Vicolo Corto", player.toStringListaProprieta());
    } 
   
   @Test
   void testGetListaPropString() {
   		GruppoColore marrone = new GruppoColore("marrone");
   		Proprieta cantiere= new Cantiere("Vicolo Corto",1, 60, 30, new int[] {2,10,30,90,160,250}, marrone, 50);
   		Proprieta cantiere2= new Cantiere("Vicolo lungo",1, 60, 30, new int[] {2,10,30,90,160,250}, marrone, 50);
   		
   		((Cantiere) cantiere2).costruisci();
   		player.aggiungiProprieta(property);
   		player.aggiungiProprieta(cantiere);
   		player.aggiungiProprieta(cantiere2);
   		
   		ArrayList<String> listaPropString=player.getListaPropString();
   		assertEquals("Via Roma", listaPropString.get(0));
   		assertEquals("Vicolo Corto", listaPropString.get(1));
   		assertThrows(IndexOutOfBoundsException.class, () -> listaPropString.get(2));
   		
   }
    
    @Test
    void testGestioneCarte() {
        Carta cartaEsciGratisI = new Carta(0, "Esci gratis dal carcere", Mazzo.AZIONE_ESCI_DAL_CARCERE);
        Carta nonCartaEsciGratis=new Carta(0, "prova", Mazzo.AZIONE_VAI_AVANTI);
        player.addCarta(cartaEsciGratisI);
        player.addCarta(nonCartaEsciGratis);
        assertEquals(2, player.getNumCarte());
        
        assertTrue(player.haUscitaGratis());
        assertEquals(cartaEsciGratisI, player.getCarta());
        assertFalse(player.haUscitaGratis()); // Dopo averla usata non c'è più
        
        assertFalse(player.haUscitaGratis());
        assertEquals(nonCartaEsciGratis, player.getCarta());
        assertFalse(player.haUscitaGratis());
    }
    
    @Test
    void testPossessoreGruppo() {
    	Player player2 = new Player(2, "Fabio", 1500, false, 0);
    	
    	GruppoColore marrone= new GruppoColore("marrone");
    	Proprieta cantiere = new Cantiere("Vicolo Corto",1, 60, 30, new int[] {2,10,30,90,160,250}, marrone, 50);
    	Proprieta cantiere1= new Cantiere("Vicolo Stretto",2, 60, 30, new int[] {4,20,60,180,320,450}, marrone, 50);
    	player2.aggiungiProprieta(cantiere1);
    	player.aggiungiProprieta(cantiere);
    	
    	assertFalse(player.possessoreGruppo((Cantiere) cantiere));
    	
    	player2.rimuoviProprieta(cantiere1);
    	player.aggiungiProprieta(cantiere1);
    	assertTrue(player.possessoreGruppo((Cantiere) cantiere));
    }
    
    
    @Test
    void testgetDati() {
    	 assertEquals(1 ,player.getId());
    	 assertEquals("Mario" ,player.getName());
    }
}

