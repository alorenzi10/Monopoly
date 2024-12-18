package Model;

import java.util.*;

public abstract class Mazzo {
	
    public static final int AZIONE_VAI_AVANTI = 0; //quando la pedina avanza 
    public static final int AZIONE_VAI_INDIETRO = 1; //quando la pedina va indietro
    public static final int AZIONE_VAI_IN_CARCERE = 2; //quando il giocatore viene spostato in carcere
    public static final int AZIONE_ESCI_DAL_CARCERE = 3; //quando il giocatore riceve la carta per uscire dal carcere
    public static final int AZIONE_PAGA_CASE = 4; //quando il giocatore paga la banca in base alle case e agli hotel
    public static final int AZIONE_PAGA = 5; //quando il giocatore paga la banca
    public static final int AZIONE_RICEVI = 6; //quando il giocatore riceve dalla banca
    public static final int REGALI = 7; //quando il giocatore riceve o da a tutti gli altri giocatori
    public static final int AZIONE_SOCIETA_VICINA = 8; //quando la pedina avanza alla società piu vicina
    public static final int AZIONE_FERROVIA_VICINA = 9; //quando la pedina avanza alla ferrovia piu vicina
    
    ArrayList<Carta> carte = new ArrayList<Carta>();

    public void add(Carta card) {
    	this.carte.add(card);
    }
    
    public void shuffle() {
    	Collections.shuffle(carte);
    }
    
    public Carta get() {//prende la carta in cima al mazzo
    	
    	Carta card = carte.get(0);
    	carte.remove(0);
    	return card;
    }

    public boolean isEmpty() {
    	return carte.isEmpty();
    }
}