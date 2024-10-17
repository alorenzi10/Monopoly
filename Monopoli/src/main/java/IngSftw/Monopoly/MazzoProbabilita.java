package IngSftw.Monopoly;

public class MazzoProbabilita extends Mazzo {
	
    private final int PROBABILITA = 1;

    MazzoProbabilita() {
    	
    	// Movimento
    	cards.add(new Carta(PROBABILITA, "Andate avanti fino al via e ritirate M200.", AZIONE_VAI_AVANTI, Tabellone.POS_VIA));//da sistemare i 200

    	// Tasse, parcelle
    	//da sistemare in base al numero di case/hotel
    	cards.add(new Carta(PROBABILITA, "Pagate per contributi di miglioria stradale. M40 per ogni casa e M115 per ogni albergo che possedete.", AZIONE_PAGA_CASE, 200));
    	cards.add(new Carta(PROBABILITA, "Pagate la retta ospedaliera di M100.", AZIONE_PAGA, 100));
    	cards.add(new Carta(PROBABILITA, "Ricevete la parcella del dottore: pagate M50.", AZIONE_PAGA, 50));
    	cards.add(new Carta(PROBABILITA, "Pagate le rette scolastiche dei vostri figli: versate M50.", AZIONE_PAGA, 50));

    	// Ricezione denaro
    	cards.add(new Carta(PROBABILITA, "Maturano gli interessi della vostra assicurazione sulla vita: incassate M100.", AZIONE_RICEVI, 100));
    	cards.add(new Carta(PROBABILITA, "La banca riconosce un errore nel vostro estratto conto: incassate M200.", AZIONE_RICEVI, 200));
    	cards.add(new Carta(PROBABILITA, "Maturano le cedole delle vostre azioni: ricevete M100.", AZIONE_RICEVI, 100));
    	cards.add(new Carta(PROBABILITA, "Ereditate M100 da un lontano zio.", AZIONE_RICEVI, 100));
    	cards.add(new Carta(PROBABILITA, "Dalla vendita di uno stock di merci ricavate M50.", AZIONE_RICEVI, 50));
    	cards.add(new Carta(PROBABILITA, "Ricevete M25 per la vostra consulenza.", AZIONE_RICEVI, 25));
    	cards.add(new Carta(PROBABILITA, "Vi viene rimborsata la tassa sui redditi: incassate M20.", AZIONE_RICEVI, 20));
    	cards.add(new Carta(PROBABILITA, "Avete vinto il secondo premio in un concorso di bellezza! Incassate M10.", AZIONE_RICEVI, 10));
    	cards.add(new Carta(PROBABILITA, "È il vostro compleanno: ogni giocatore vi regala M10.", REGALI, 10));

    	// Prigione
    	cards.add(new Carta(PROBABILITA, "Andate in prigione direttamente e senza passare dal via!", AZIONE_VAI_IN_CARCERE));
    	cards.add(new Carta(PROBABILITA, "Uscite gratis di prigione, se ci siete potete conservare questa carta fino al momento di servirvene, oppure venderla.", AZIONE_ESCI_DAL_CARCERE));

    }
}