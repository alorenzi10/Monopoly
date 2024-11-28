package Model;

import java.util.*;

public class Mazzo {
	
    public final static int AZIONE_VAI_AVANTI = 0; //quando la pedina avanza 
    public final static int AZIONE_VAI_INDIETRO = 1; //quando la pedina va indietro
    public final static int AZIONE_VAI_IN_CARCERE = 2; //quando il giocatore viene spostato in carcere
    public final static int AZIONE_ESCI_DAL_CARCERE = 3; //quando il giocatore riceve la carta per uscire dal carcere
    public final static int AZIONE_PAGA_CASE = 4; //quando il giocatore paga la banca in base alle case e agli hotel
    public final static int AZIONE_PAGA = 5; //quando il giocatore paga la banca
    public final static int AZIONE_RICEVI = 6; //quando il giocatore riceve dalla banca
    public final static int REGALI = 7; //quando il giocatore riceve o da a tutti gli altri giocatori
    public final static int AZIONE_SOCIETA_VICINA=8; //quando la pedina avanza alla società piu vicina
    public final static int AZIONE_FERROVIA_VICINA=9; //quando la pedina avanza alla ferrovia piu vicina
    
    ArrayList<Carta> cards = new ArrayList<Carta>();

    public void add(Carta card) {
    	this.cards.add(card);
    }
    
    public void shuffle() {
    	Collections.shuffle(cards);
    }
    
    public Carta get() {//prende la carta in cima al mazzo
    	
    	Carta card = cards.get(0);
    	cards.remove(0);
    	return card;
    }

    public boolean isEmpty() {
    	return cards.isEmpty();
    }
}