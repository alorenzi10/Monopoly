package Model;

import java.util.*;

public class Mazzo {
	
    public final int AZIONE_VAI_AVANTI = 0; //quando la pedina avanza 
    public final int AZIONE_VAI_INDIETRO = 1; //quando la pedina va indietro 
    public final int AZIONE_MUOVI = 2; //??????
    public final int AZIONE_VAI_IN_CARCERE = 3; //quando il giocatore viene spostato in carcere
    public final int AZIONE_ESCI_DAL_CARCERE = 4; //quando il giocatore riceve la carta per uscire dal carcere
    public final int AZIONE_PAGA_CASE = 5; //quando il giocatore paga la banca in base alle case e agli hotel
    public final int AZIONE_PAGA = 6; //quando il giocatore paga la banca
    public final int AZIONE_RICEVI = 7; //quando il giocatore riceve dalla banca
    public final int REGALI = 8; //quando il giocatore riceve o da a tutti gli altri giocatori
    
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

    public Carta put(Carta card) {
    	cards.add(card);
		return card;
    }
}