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
        monopolyGUI.mostraInfoGiocatori(monopoly.getGiocatoriString());
    }
    
    //Controlla che la creazione non generi eccezzioni
    @Test
    void testCreazione() {
    	assertDoesNotThrow(()-> new Monopoly(3, nomiGiocatori, monopolyGUI));
    }

    //Controlla che i giocatori ciclino correttamente
    @Test
    void testSetProssimoGiocatore() {
    	
        Player giocatoreCorrente = monopoly.getGiCorrente();
        monopoly.setProssimoGiocatore();
        assertNotEquals(giocatoreCorrente, monopoly.getGiCorrente());
        monopoly.setProssimoGiocatore();
        monopoly.setProssimoGiocatore();
        assertSame(giocatoreCorrente, monopoly.getGiCorrente());
        
    }
    //Controlla che dopo aver tirato i dadi il giocatore si sposti effettivamente
    @Test
    void testTiraDadi() {
    	Player provaPlayer=monopoly.getGiCorrente();
    	int iniziale=provaPlayer.getLocation();
        monopoly.tiraDadi();
        int arrivo=provaPlayer.getLocation();
        assertTrue(iniziale!=arrivo);
    }

    //Controllo del metodo tiraDadi se ci si trova in prigione
    @Test //ogni tanto sembra bloccarsi, ma non capisco il perché (statistica?)
    void testUscitaPrigione() {

    	Player provaPlayer=monopoly.getGiCorrente();
    	
    	//uscita di prigione con dadi
        boolean uscitaConDadi=false;
        boolean uscitaConTenttivi=false;
        int iniziale=0;
        int arrivo=0;
        
        do{//ciclo do while finche non si verificano entrambi i casi di uscita
        int tentativi=0;
        provaPlayer.vaiInPrigione();
        iniziale=provaPlayer.getLocation(); //posizione della prigione
        
        for(int b=0; b<3; b++) {//dopo 3 turni l'uscità dalla prigione è forzata
        	
        	monopoly.tiraDadi();

        	if(monopoly.getDadi().isDouble()) { //dadi doppi allora esce di prigione senza pagare
        		uscitaConDadi=true;
        		arrivo=provaPlayer.getLocation();  //spostamento dopo l'uscita
        		break;
        		
        	}else {	//se non è doppio è un tentativo fallito fino ad un massimo di 3
        		
        		tentativi++;
        		
        		if(tentativi==3) {   
        			
            		uscitaConTenttivi=true;
            		arrivo=provaPlayer.getLocation();
            		break;
        		}
        	}//essendo 1 tiro a turno passiamo al giocatore successivo
        	monopoly.setProssimoGiocatore();//tocca al secondo
        	monopoly.setProssimoGiocatore();//tocca al terzo
        	monopoly.setProssimoGiocatore();//tocca di nuovo al nostro giCorrente
        	}
        }while(uscitaConDadi==false || uscitaConTenttivi==false);
        
        assertFalse(provaPlayer.getInPrigione());
        assertFalse(iniziale==arrivo);
    }
    
    //Controllo del metodo tiraDadi se non ci si trova in prigione
    @Test
    void testTreDadiDoppi() {

    	Player provaPlayer=monopoly.getGiCorrente();
    	boolean treDoppi=false;
    	
        do{ //do while finche non escondo tre dadi doppi di fila

        	provaPlayer.liberaDaPrigione();//con tira dadi potrebbe finire in prigione
        	monopoly.tiraDadi();

        	if(monopoly.getDadi().isDouble() && !provaPlayer.getInPrigione()) { //se finisse in prigione i dadi non vengono ritirati e darebbe un falso triplo doppio dato
        		monopoly.tiraDadi();

        		if(monopoly.getDadi().isDouble() && !provaPlayer.getInPrigione()) {
        				monopoly.tiraDadi();
        				if(monopoly.getDadi().isDouble()) {
        						treDoppi=true;
        				
        				}
        			
        		}
        	}
        	
        	monopoly.setFineTurno(); //dentro fine turno è incluso setProssimoGiocatore() se non si è in debito e si ha tirato
        	monopoly.setProssimoGiocatore();
        	monopoly.setProssimoGiocatore();
        }while(treDoppi==false);
        
        assertTrue(provaPlayer.getInPrigione()); 
       
    }
    //Controlla l'atterraggio su una proprietà e il relativo tentativo di acquisto con e senza fondi adeguati
    @Test
    void testCompraProprieta() {
    	
    	monopolyGUI.mostraInfoGiocatori(monopoly.getGiocatoriString());
    	Player provaPlayer=monopoly.getGiCorrente();
    	provaPlayer.muovi(1); //su Vicolo Corto
    	monopoly.compraProprieta();
    	assertEquals(provaPlayer, ((Proprieta) monopoly.getTabellone().getCasella(1)).getPossessore());
    	
    	provaPlayer.doTransazione(-1439);
    	provaPlayer.muovi(2); //su Vicolo Stretto
    	assertDoesNotThrow(()-> monopoly.compraProprieta()); //controlla che il lancio dell'asta non causi eccezzioni
    }
    
    //Controllo dei metodi getGiocatoriString, getNumGiocatori, setPedineSelezionate,getPedineSelezionate, getCorrispondenzaPlayer
    //getListaGiocatoriScambi, getCorrispondenzaProprieta
    @Test
    void testGetterSetterMonopoly() { 
    	int i=0;
    	for(String p: monopoly.getGiocatoriString()) {
    		assertEquals(nomiGiocatori[i], p);
    		i++;
    	}
    	assertEquals(3, monopoly.getNumGiocatori());
    	
    	monopoly.setPedineSelezionate();
    	assertEquals(pedine, monopoly.getPedineSelezionate());
       
    	assertNull( monopoly.getCorrispondenzaPlayer("monopoli"));
    	assertEquals("Mario", monopoly.getCorrispondenzaPlayer("Mario").getName());
    	assertNull( monopoly.getCorrispondenzaPlayer("monopoli"));
                
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
        
       }
    
        //Controllo su costruisci, demolisci, ipoteca e disipoteca
        @Test
        void testProprieta(){
        	
        Player provaPlayer=monopoly.getGiCorrente();
        
        //controlla se il giocatore possiede una proprietà a partire da una stringa 
        assertNull(monopoly.getCorrispondenzaProprieta("Vicolo Corto", provaPlayer));
        provaPlayer.aggiungiProprieta((Proprieta) monopoly.getTabellone().getCasella(1));
        assertEquals((Proprieta) monopoly.getTabellone().getCasella(1), monopoly.getCorrispondenzaProprieta("Vicolo Corto", provaPlayer));

        
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(3)); //non la possiede->non puo costruire
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(1)); //non possiede il gruppo->non puo costruire
        
        monopoly.ipoteca((Proprieta) monopoly.getTabellone().getCasella(3));//prova a ipotecara casa non posseduta->non possibile
        monopoly.disipoteca((Proprieta) monopoly.getTabellone().getCasella(3)); //prova a disipotecara casa non posseduta->non possibile
        
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
        provaPlayer.doTransazione(-1500); //per prova costruisci senza fondi
        monopoly.costruisci((Proprieta) monopoly.getTabellone().getCasella(3)); 
        provaPlayer.doTransazione(1500);
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
        }
        
        @Test
        void testPazielGioco() {
        	for(int i=0; i<20; i++) {
        		assertDoesNotThrow(()->monopoly.tiraDadi());

            	if(monopoly.getDadi().isDouble() && !monopoly.getGiCorrente().getInPrigione()) { 
            		assertDoesNotThrow(()->monopoly.tiraDadi());

            		if(monopoly.getDadi().isDouble() && !monopoly.getGiCorrente().getInPrigione()) {
            			assertDoesNotThrow(()->	monopoly.tiraDadi());

            		}
            	}
            	assertDoesNotThrow(()->monopoly.setFineTurno());
        	}
        }
        

        //test Metodi uscita di prigione
        @Test
        void testMetodiPrigione(){
        Player provaPlayer=monopoly.getGiCorrente();
        
        provaPlayer.addCarta(new Carta(0, "Uscite gratis di prigione, se ci siete. Potete conservare questa carta fino al momento di servirvene, oppure venderla.", 3));
        provaPlayer.vaiInPrigione();
        assertTrue(provaPlayer.getInPrigione());
        monopoly.uscitaGratis();
        assertFalse(provaPlayer.getInPrigione());
        
        provaPlayer.vaiInPrigione();
        monopoly.pagaUscitaPrigione();
        assertFalse(provaPlayer.getInPrigione());
        assertEquals(1450, provaPlayer.getWallet());
        assertDoesNotThrow( ()->monopoly.pagaUscitaPrigione());
        
        provaPlayer.doTransazione(-1449); 
        provaPlayer.vaiInPrigione();
        monopoly.pagaUscitaPrigione(); //prova a pagare cauzione ma non ha i fondi sufficenti->resta in prigione
        assertTrue(provaPlayer.getInPrigione());
        }

        @Test
        void testBancarotta(){
        
        monopolyGUI.setDecisioneBancarotta(true); //preparazione dei metodi di MonopolyGUi che richiederà monpoly.setBancarotta()
        monopolyGUI.confermaBancarotta();
        
        monopoly.setBancarotta();
        assertTrue(monopoly.getPlayers().size() == 2); //restano 2 giocatori
        
        Player prova=monopoly.getGiCorrente();
        prova.aggiungiProprieta((Proprieta) monopoly.getTabellone().getCasella(1));
        monopolyGUI.setDecisioneBancarotta(true);
        monopolyGUI.confermaBancarotta();
        monopoly.setBancarotta();
        assertTrue(monopoly.getPlayers().size() == 1); //il gioco finisce 
        }
   
    
    @Test
    void testCaricamentoMonopoly() {
    	
    	Player provaPlayer=monopoly.getGiCorrente();

        provaPlayer.aggiungiProprieta((Proprieta) monopoly.getTabellone().getCasella(1));
        provaPlayer.aggiungiProprieta((Proprieta) monopoly.getTabellone().getCasella(3));
        provaPlayer.aggiungiProprieta((Proprieta) monopoly.getTabellone().getCasella(12));
        provaPlayer.aggiungiProprieta((Proprieta) monopoly.getTabellone().getCasella(15));
        
        monopoly.ipoteca((Proprieta) monopoly.getTabellone().getCasella(15));
        
        List<int[]> coppie = new ArrayList<>();
        coppie.add(new int[] { 1, 1 }); //la casella con id 1, ha 1 costruzione
        coppie.add(new int[] { 2, 5 });//la casella con id 2, ha 5 costruzioni
        
        assertNotEquals(1, ((Cantiere) monopoly.getTabellone().getCasella(1)).getNumCostruzioni());
        assertNotEquals(5, ((Cantiere) monopoly.getTabellone().getCasella(3)).getNumCostruzioni());
        
    	monopoly.caricamento(monopolyGUI,coppie );
    	
    	assertEquals(provaPlayer.getName(),((Proprieta) monopoly.getTabellone().getCasella(1)).getPossessore().getName());
    	assertEquals(1, ((Cantiere) monopoly.getTabellone().getCasella(1)).getNumCostruzioni());
        assertEquals(5, ((Cantiere) monopoly.getTabellone().getCasella(3)).getNumCostruzioni());
    	
    }
  
} 
