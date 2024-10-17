package IngSftw.Monopoly;

public class MazzoImprevisti extends Mazzo {
	
    private final int IMPREVISTI = 0;

    MazzoImprevisti(){
    	//Ho inserito solo i testi, dobbiamo riflettere sui parametri
    	//Spostamenti
    	cards.add(new Carta(IMPREVISTI, "Avanzate fino alla società più vicina; "
    			+ "Se è libera potete acquistarla dalla banca; Se è posseduta da un altro giocatore lanciate i dadi"
    			+ " e pagate al proprietario dieci volte il numero uscito sui dadi.", AZIONE_VAI_AVANTI));
    	cards.add(new Carta(IMPREVISTI, "Avanzate fino alla stazione ferroviaria più vicina;"
    			+ " Se è libera potete acquistarla dalla banca; Se è posseduta da un altro giocatore"
    			+ " pagate al proprietario il doppio dell’affitto che gli spetta normalmente.", AZIONE_VAI_AVANTI));
    	cards.add(new Carta(IMPREVISTI, "Avanzate fino alla stazione ferroviaria più vicina;"
    			+ " Se è libera potete acquistarla dalla banca; Se è posseduta da un altro giocatore"
    			+ " pagate al proprietario il doppio dell’affitto che gli spetta normalmente.", AZIONE_VAI_AVANTI));
    	cards.add(new Carta(IMPREVISTI, "Andate fino a largo colombo: Se passate dal “via” ritirate M200.", AZIONE_VAI_AVANTI));
    	
    	cards.add(new Carta(IMPREVISTI, "Andate fino a parco della vittoria: Se passate dal “via” ritirate M200.", AZIONE_VAI_AVANTI));
    	
    	cards.add(new Carta(IMPREVISTI, "Andate alla stazione nord: se passate dal via ritirate M200.", AZIONE_VAI_AVANTI));
    	cards.add(new Carta(IMPREVISTI, "Andate sino a corso ateneo: se passate dal via ritirate M200.", AZIONE_VAI_AVANTI));
    	cards.add(new Carta(IMPREVISTI, "Fate 3 passi indietro (con tanti auguri!);", AZIONE_VAI_INDIETRO));
    	
		// Tasse
		cards.add(new Carta(IMPREVISTI, "Multa per eccesso di velocità: pagate M15.", AZIONE_PAGA, 15));
		cards.add(new Carta(IMPREVISTI, "Eseguite lavori di manutenzione su tutti i vostri edifici: Pagte M25 per ogni casa e 100M per ogni albergo che possedete.", AZIONE_PAGA_CASE, 25));
		
		// Pagamenti
		cards.add(new Carta(IMPREVISTI, "Siete stati promossi alla presidenza del consiglio di amministrazione pagate M50 ad ogni giocatore", REGALI, 50));
		cards.add(new Carta(IMPREVISTI, "Maturano le vostre cedole dei vostri fondi immobiliari: incassate M150.", AZIONE_RICEVI, 150));
		cards.add(new Carta(IMPREVISTI, "La banca vi paga un dividendo di M50.", AZIONE_RICEVI, 50));

		// Prigione
		cards.add(new Carta(IMPREVISTI, "Andate in prigione direttamente e senza passare dal “via”." , AZIONE_VAI_IN_CARCERE));
		cards.add(new Carta(IMPREVISTI, "Uscite gratis di prigione. Se ci siete. Potete conservare questa carta fino al momento di servirvene, oppure venderla.", AZIONE_ESCI_DAL_CARCERE));

		shuffle();
    }
}