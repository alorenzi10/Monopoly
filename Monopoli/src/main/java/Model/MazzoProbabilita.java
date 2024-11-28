package Model;

public class MazzoProbabilita extends Mazzo {
	
    private final int PROBABILITA = 1;

    MazzoProbabilita() {
    	
    	// Movimento
    	carte.add(new Carta(PROBABILITA, "Andate avanti fino al via e ritirate 200€.", AZIONE_VAI_AVANTI, 0, Tabellone.POS_VIA));

    	// Tasse, parcelle
    	carte.add(new Carta(PROBABILITA, "Pagate per contributi di miglioria stradale. 40€ per ogni casa e 115€ per ogni albergo che possedete.", AZIONE_PAGA_CASE, new int[] {40, 115}));
    	carte.add(new Carta(PROBABILITA, "Pagate la retta ospedaliera di 100€.", AZIONE_PAGA, 100));
    	carte.add(new Carta(PROBABILITA, "Ricevete la parcella del dottore: pagate 50€.", AZIONE_PAGA, 50));
    	carte.add(new Carta(PROBABILITA, "Pagate le rette scolastiche dei vostri figli: versate 50€.", AZIONE_PAGA, 50));

    	// Ricezione denaro
    	carte.add(new Carta(PROBABILITA, "Maturano gli interessi della vostra assicurazione sulla vita: incassate 100€.", AZIONE_RICEVI, 100));
    	carte.add(new Carta(PROBABILITA, "La banca riconosce un errore nel vostro estratto conto: incassate 200€.", AZIONE_RICEVI, 200));
    	carte.add(new Carta(PROBABILITA, "Maturano le cedole delle vostre azioni: ricevete 100€.", AZIONE_RICEVI, 100));
    	carte.add(new Carta(PROBABILITA, "Ereditate 100€ da un lontano zio.", AZIONE_RICEVI, 100));
    	carte.add(new Carta(PROBABILITA, "Dalla vendita di uno stock di merci ricavate 50€.", AZIONE_RICEVI, 50));
    	carte.add(new Carta(PROBABILITA, "Ricevete 25€ per la vostra consulenza.", AZIONE_RICEVI, 25));
    	carte.add(new Carta(PROBABILITA, "Vi viene rimborsata la tassa sui redditi: incassate 20€.", AZIONE_RICEVI, 20));
    	carte.add(new Carta(PROBABILITA, "Avete vinto il secondo premio in un concorso di bellezza! Incassate 10€.", AZIONE_RICEVI, 10));
    	carte.add(new Carta(PROBABILITA, "È il vostro compleanno: ogni giocatore vi regala 10€.", REGALI, 10));

    	// Prigione
    	carte.add(new Carta(PROBABILITA, "Andate in prigione direttamente e senza passare dal via!", AZIONE_VAI_IN_CARCERE));
    	carte.add(new Carta(PROBABILITA, "Uscite gratis di prigione, se ci siete potete conservare questa carta fino al momento di servirvene, oppure venderla.", AZIONE_ESCI_DAL_CARCERE));

		shuffle();
    }
}