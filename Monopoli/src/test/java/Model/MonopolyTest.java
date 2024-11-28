package Model;

import org.junit.jupiter.api.*;

import View.MonopolyGUI;
import View.SchermataDiGioco;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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
    void testMonopoly() { //da dividere in test per argomento/metodo
    	int i=0;
    	for(String p: monopoly.getGiocatoriString()) {
    		assertEquals(nomiGiocatori[i], p);
    		i++;
    	}
    	assertEquals(3, monopoly.getNumGiocatori());
    	
    	assertTrue(monopoly.getTabellone() instanceof Tabellone);
    	assertEquals("marrone", monopoly.getTabellone().getMarrone().getName());
    	assertEquals("azzurro", monopoly.getTabellone().getAzzurro().getName());
    	assertEquals("viola", monopoly.getTabellone().getViola().getName());
    	assertEquals("arancio", monopoly.getTabellone().getArancione().getName());
    	assertEquals("rosso", monopoly.getTabellone().getRosso().getName());
    	assertEquals("giallo", monopoly.getTabellone().getGiallo().getName());
    	assertEquals("verde", monopoly.getTabellone().getVerde().getName());
    	assertEquals("blu", monopoly.getTabellone().getBlu().getName());
    	
    	monopoly.setPedineSelezionate();
    	assertEquals(pedine, monopoly.getPedineSelezionate());
       
    	assertNull( monopoly.getCorrispondenzaPlayer("monopoli"));
    	assertEquals("Mario", monopoly.getCorrispondenzaPlayer("Mario").getName());
    	assertNull( monopoly.getCorrispondenzaPlayer("monopoli"));
        assertFalse(monopoly.isGameOver());
        
        
        ArrayList<String> giocatori=new ArrayList<>();
        for(String p: nomiGiocatori) {
        	if(!p.equals(monopoly.getGiCorrente().getName())) {
        		giocatori.add(p);
        	}
        }
        assertEquals(giocatori, monopoly.getListaGiocatoriScambi());
        
        Player provaPlayer=monopoly.getGiCorrente();
        assertNull(monopoly.getCorrispondenzaProprieta("Vicolo Corto", provaPlayer));
        provaPlayer.aggiungiProprieta((Proprieta) monopoly.getTabellone().getCasella(1));
        assertEquals((Proprieta) monopoly.getTabellone().getCasella(1), monopoly.getCorrispondenzaProprieta("Vicolo Corto", provaPlayer));
        
        
        //test costruisci, ipoteca, disipoteca
        monopolyGUI.mostraInfoGiocatori(monopoly.getGiocatoriString());
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(3)); //non la possiede
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(1)); //non possiede il gruppo
        
        monopoly.ipoteca((Proprieta) monopoly.getTabellone().getCasella(3));//ipotecara casa non posseduta
        monopoly.disipoteca((Proprieta) monopoly.getTabellone().getCasella(3)); //disipotecara casa non posseduta
        
        provaPlayer.aggiungiProprieta((Proprieta) monopoly.getTabellone().getCasella(3)); //ora possiede il gruppo
        
        monopoly.ipoteca((Proprieta) monopoly.getTabellone().getCasella(3));
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(1)); //una proprietà del gruppo colore è ipotecata
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(3)); //è ipotecata
        monopoly.disipoteca((Proprieta) monopoly.getTabellone().getCasella(3)); 
        
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(3)); //una casa
        monopoly.ipoteca((Proprieta) monopoly.getTabellone().getCasella(3)); //non puoi edificare se sulla proprieta ci sono case
        monopoly.ipoteca((Proprieta) monopoly.getTabellone().getCasella(1)); //non puoi edificare se su altre proprieta del gruppo ci sono case
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(3)); //non puoi avere una differenza maggiore di uno tra varie proprietà
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(1));
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(3));	//due case
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(1));
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(3)); //tre case
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(1));
        provaPlayer.doTransaction(-1500); //per prova costruisci senza fondi
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(3)); 
        provaPlayer.doTransaction(1500);
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(3)); //quattro case
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(1));
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(3)); //Albergo
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(1));
        
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(3)); //Ramo hai gia un albergo 
        assertEquals(5, ((Cantiere) monopoly.getTabellone().getCasella(3)).getNumCostruzioni());
        assertEquals(5, ((Cantiere) monopoly.getTabellone().getCasella(3)).getNumCostruzioni());
        //test demolisci,
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(11)); //non la possiedi
        
        monopoly.demolisci((Proprieta) monopoly.getTabellone().getCasella(3)); //una casa
        assertEquals(4, ((Cantiere) monopoly.getTabellone().getCasella(3)).getNumCostruzioni());
        monopoly.demolisci((Proprieta) monopoly.getTabellone().getCasella(3)); //non puoi avere una differenza maggiore di uno tra varie proprietà
        monopoly.demolisci((Proprieta) monopoly.getTabellone().getCasella(1)); 
        monopoly.demolisci((Proprieta) monopoly.getTabellone().getCasella(3));
        monopoly.demolisci((Proprieta) monopoly.getTabellone().getCasella(1));
        monopoly.demolisci((Proprieta) monopoly.getTabellone().getCasella(3));
        monopoly.demolisci((Proprieta) monopoly.getTabellone().getCasella(1));
        monopoly.demolisci((Proprieta) monopoly.getTabellone().getCasella(3));
        monopoly.demolisci((Proprieta) monopoly.getTabellone().getCasella(1));
        monopoly.demolisci((Proprieta) monopoly.getTabellone().getCasella(3));
        monopoly.demolisci((Proprieta) monopoly.getTabellone().getCasella(1));
        monopoly.demolisci((Proprieta) monopoly.getTabellone().getCasella(3));
        assertEquals(0, ((Cantiere) monopoly.getTabellone().getCasella(3)).getNumCostruzioni());
        assertEquals(0, ((Cantiere) monopoly.getTabellone().getCasella(3)).getNumCostruzioni());
        
        //test tira dadi
        int iniziale=provaPlayer.getLocation();
        monopoly.tiraDadi();
        int arrivo=provaPlayer.getLocation();
        assertTrue(iniziale!=arrivo);
           
        //test Metodi uscita di prigione
        provaPlayer.liberaDaPrigione();
        provaPlayer.addCarta(new Carta(0, "Uscite gratis di prigione, se ci siete. Potete conservare questa carta fino al momento di servirvene, oppure venderla.", 3));
        provaPlayer.vaiInPrigione();
        assertTrue(provaPlayer.getInPrigione());
        monopoly.uscitaGratis();
        assertFalse(provaPlayer.getInPrigione());
        
        provaPlayer.vaiInPrigione();
        monopoly.pagaUscitaPrigione();
        assertFalse(provaPlayer.getInPrigione());

        //test bancarotta e fine gioco
        monopolyGUI.setDecisioneBancarotta(true);
        monopolyGUI.confermaBancarotta();
        monopoly.setBancarotta();
        assertTrue(monopoly.getPlayers().size() == 2);
        
        monopolyGUI.setDecisioneBancarotta(true);
        monopolyGUI.confermaBancarotta();
        monopoly.setBancarotta();
        assertTrue(monopoly.getPlayers().size() == 1); 
        
    }
    
    @Test 
    void testUscitaPrigione() {

    	monopolyGUI.mostraInfoGiocatori(monopoly.getGiocatoriString());

    	Player provaPlayer=monopoly.getGiCorrente();
    	
    	//uscita di prigione con dadi
        boolean uscitaConDadi=false;
        boolean uscitaConTenttivi=false;
        provaPlayer=monopoly.getGiCorrente();
        int iniziale=0;
        int arrivo=0;
        
        do {
        	
        int tentativi=0;
        provaPlayer.vaiInPrigione();
        iniziale=provaPlayer.getLocation();
        
        for(int b=0; b<3; b++) {
        	
        	monopoly.tiraDadi();
        	Dadi dice=monopoly.getDice();
        	if(dice.isDouble()) {
        		
        		uscitaConDadi=true;
        		arrivo=provaPlayer.getLocation();
        		break;
        		
        	}else {	
        		tentativi++;
        		
        		if(tentativi==3) {   
        			
            		uscitaConTenttivi=true;
            		arrivo=provaPlayer.getLocation();
            		break;
        		}
        	}
        	monopoly.setProssimoGiocatore();
        	monopoly.setProssimoGiocatore();
        	monopoly.setProssimoGiocatore();
        }
        }while(uscitaConDadi==false || uscitaConTenttivi==false);
        
        assertFalse(provaPlayer.getInPrigione());
        assertFalse(iniziale==arrivo);
    }
    
    
    @Test
    void testTreDadiDoppi() { //da rincontrolalre
    	
    	monopolyGUI.mostraInfoGiocatori(monopoly.getGiocatoriString());

    	Player provaPlayer=monopoly.getGiCorrente();
    	boolean treDoppi=false;
    	
        do{
        	provaPlayer.liberaDaPrigione();

        	monopoly.tiraDadi();
        	
        	if(monopoly.getDice().isDouble()) {
        		monopoly.tiraDadi();
        		
        		if(monopoly.getDice().isDouble()) {
        			if(provaPlayer.getInPrigione()==false) {
        				monopoly.tiraDadi();
        			}
        			
        			if(monopoly.getDice().isDouble()) {
        				
        					treDoppi=true;
        				
        			}
        		}
        	}
        	monopoly.setFineTurno();
        	monopoly.setProssimoGiocatore();
        	monopoly.setProssimoGiocatore();
        }while(treDoppi==false);
        assertTrue(provaPlayer.getInPrigione()); 
        provaPlayer.liberaDaPrigione();
        
      
    }
    
    @Test
    void testCompraProprieta() {
    	
    	monopolyGUI.mostraInfoGiocatori(monopoly.getGiocatoriString());
    	Player provaPlayer=monopoly.getGiCorrente();
    	provaPlayer.muovi(1);
    	monopoly.compraProprieta();
    	assertEquals(provaPlayer, ((Proprieta) monopoly.getTabellone().getCasella(1)).getPossessore());
    }
    
    @Test
    void testCaricamentoMonopoly() {
    	
    	Player provaPlayer=monopoly.getGiCorrente();
        assertNull(monopoly.getCorrispondenzaProprieta("Vicolo Corto", provaPlayer));
        provaPlayer.aggiungiProprieta((Proprieta) monopoly.getTabellone().getCasella(1));
        provaPlayer.aggiungiProprieta((Proprieta) monopoly.getTabellone().getCasella(3));
        provaPlayer.aggiungiProprieta((Proprieta) monopoly.getTabellone().getCasella(12));
        
        provaPlayer.aggiungiProprieta((Proprieta) monopoly.getTabellone().getCasella(15));
        monopolyGUI.mostraInfoGiocatori(monopoly.getGiocatoriString());
        monopoly.ipoteca((Proprieta) monopoly.getTabellone().getCasella(15));
        
        List<int[]> coppie = new ArrayList<>();
        coppie.add(new int[] { 1, 1 });
        coppie.add(new int[] { 2, 5 });
    	monopolyGUI.mostraInfoGiocatori(monopoly.getGiocatoriString());
    	monopoly.caricamento(monopolyGUI,coppie );
    	
    	assertEquals(provaPlayer.getName(),((Proprieta) monopoly.getTabellone().getCasella(1)).getPossessore().getName());
    	assertEquals(1, ((Cantiere) monopoly.getTabellone().getCasella(1)).getNumCostruzioni());
        assertEquals(5, ((Cantiere) monopoly.getTabellone().getCasella(3)).getNumCostruzioni());
    	
    }
  
}
