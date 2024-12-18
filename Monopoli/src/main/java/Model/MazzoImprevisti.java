package Model;

public class MazzoImprevisti extends Mazzo {
	
    public final int IMPREVISTI = 0;

    MazzoImprevisti(){
    	
    	carte.add(new Carta(IMPREVISTI, "Avanzate fino alla società più vicina; Se è libera potete acquistarla dalla banca; Se è posseduta da un altro giocatore lanciate i dadi e pagate al proprietario dieci volte il numero uscito sui dadi.", AZIONE_SOCIETA_VICINA)); 
    	
    	carte.add(new Carta(IMPREVISTI, "Avanzate fino alla stazione ferroviaria più vicina; Se è libera potete acquistarla dalla banca; Se è posseduta da un altro giocatore pagate al proprietario il doppio dell’affitto che gli spetta normalmente.", AZIONE_FERROVIA_VICINA));
    	
    	carte.add(new Carta(IMPREVISTI, "Avanzate fino alla stazione ferroviaria più vicina; Se è libera potete acquistarla dalla banca; Se è posseduta da un altro giocatore pagate al proprietario il doppio dell’affitto che gli spetta normalmente.", AZIONE_FERROVIA_VICINA)); 
    	carte.add(new Carta(IMPREVISTI, "Andate fino a Largo Colombo: Se passate dal “Via!” ritirate 200€.", AZIONE_VAI_AVANTI, 0, Tabellone.POS_LARGO_COLOMBO));
    	carte.add(new Carta(IMPREVISTI, "Andate fino a Parco della Vittoria: Se passate dal “Via!” ritirate 200€.", AZIONE_VAI_AVANTI, 0, Tabellone.POS_PARCO_DELLA_VITTORIA));
    	carte.add(new Carta(IMPREVISTI, "Andate alla Stazione Nord; Se passate dal “Via!” ritirate 200€.", AZIONE_VAI_AVANTI, 0, Tabellone.POS_STAZIONE_NORD));
    	carte.add(new Carta(IMPREVISTI, "Andate sino a Corso Ateneo; Se passate dal “Via!” ritirate 200€.", AZIONE_VAI_AVANTI, 0, Tabellone.POS_CORSO_ATENEO)); 
    	carte.add(new Carta(IMPREVISTI, "Fate 3 passi indietro (con tanti auguri!).", AZIONE_VAI_INDIETRO, 0, -3)); 
    	
		// Tasse
		carte.add(new Carta(IMPREVISTI, "Multa per eccesso di velocità: pagate 15€.", AZIONE_PAGA, 15)); 
		carte.add(new Carta(IMPREVISTI, "Eseguite lavori di manutenzione su tutti i vostri edifici: pagate 25€ per ogni casa e 100€ per ogni albergo che possedete.", AZIONE_PAGA_CASE, new int[] {25, 100})); 
		
		// Pagamenti
		 carte.add(new Carta(IMPREVISTI, "Siete stati promossi alla presidenza del consiglio di amministrazione: pagate 50€ ad ogni giocatore.", REGALI, 50));
		carte.add(new Carta(IMPREVISTI, "Maturano le vostre cedole dei vostri fondi immobiliari: incassate 150€.", AZIONE_RICEVI, 150));
		carte.add(new Carta(IMPREVISTI, "La banca vi paga un dividendo di 50€.", AZIONE_RICEVI, 50));

		// Prigione
		carte.add(new Carta(IMPREVISTI, "Andate in prigione direttamente e senza passare dal “Via!”." , AZIONE_VAI_IN_CARCERE));
		carte.add(new Carta(IMPREVISTI, "Uscite gratis di prigione, se ci siete. Potete conservare questa carta fino al momento di servirvene, oppure venderla.", AZIONE_ESCI_DAL_CARCERE));
		
		shuffle();
    }
}