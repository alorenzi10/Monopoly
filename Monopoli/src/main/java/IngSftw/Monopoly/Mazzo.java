package IngSftw.Monopoly;

import java.util.*;

public class Mazzo {
	
    public final int AZIONE_VAI_AVANTI = 0;
    public final int AZIONE_VAI_INDIETRO = 1;
    public final int AZIONE_MUOVI = 2;
    public final int AZIONE_VAI_IN_CARCERE = 3;
    public final int AZIONE_ESCI_DAL_CARCERE = 4;
    public final int AZIONE_PAGA_CASE = 5;
    public final int AZIONE_PAGA = 6;
    public final int AZIONE_RICEVI = 7;
    public final int REGALI = 8;
    
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