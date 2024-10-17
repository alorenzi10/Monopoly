package IngSftw.Monopoly;

public class MazzoImprevisti extends Mazzo {
	
    private final int IMPREVISTI = 0;

    MazzoImprevisti(){
    	
    	//Spostamenti
    	cards.add(new Carta(IMPREVISTI, "Advance to Go.", AZIONE_VAI_AVANTI, Tabellone.POS_VIA));
		cards.add(new Carta(IMPREVISTI, "Advance to Pall Mall. If you pass Go collect " + UI.CURRENCY_SYMBOL + "200.", AZIONE_VAI_AVANTI, Tabellone.POS_PALL_MALL));
		cards.add(new Carta(IMPREVISTI, "Take a trip to Marylebone Station and if you pass Go collect " + UI.CURRENCY_SYMBOL + "200.", AZIONE_VAI_AVANTI, Tabellone.POS_MARYLEBONE_STATION));
		cards.add(new Carta(IMPREVISTI, "Advance to Trafalgar Square. If you pass Go collect " + UI.CURRENCY_SYMBOL + "200.", AZIONE_VAI_AVANTI, Tabellone.POS_TRAFALGAR_SQ));
		cards.add(new Carta(IMPREVISTI, "Advance to Mayfair. If you pass Go collect " + UI.CURRENCY_SYMBOL + "200.", AZIONE_VAI_AVANTI,Tabellone.POS_MAYFAIR));
		cards.add(new Carta(IMPREVISTI, "Go back three sqaures", AZIONE_MUOVI, -3));
		
		// Tasse
		cards.add(new Carta(IMPREVISTI, "Make general repairs on all your houses. For each house pay " + UI.CURRENCY_SYMBOL + "25. For each hotel pay " + UI.CURRENCY_SYMBOL + "100.", AZIONE_PAGA_CASE, new int[] {25, 100}));
		cards.add(new Carta(IMPREVISTI, "You are assessed for street repairs: " + UI.CURRENCY_SYMBOL + " 40 per house, " + UI.CURRENCY_SYMBOL + "115 per hotel.", AZIONE_PAGA_CASE, new int[] {40, 115}));
		cards.add(new Carta(IMPREVISTI, "Pay school fees of " + UI.CURRENCY_SYMBOL + "150.", AZIONE_PAGA, 150));
		cards.add(new Carta(IMPREVISTI, "Drunk in charge fine. " + UI.CURRENCY_SYMBOL + "20.", AZIONE_PAGA, 20));
		cards.add(new Carta(IMPREVISTI, "Speeding fine. " + UI.CURRENCY_SYMBOL + "15.", AZIONE_PAGA, 15));
		
		// Pagamenti
		cards.add(new Carta(IMPREVISTI, "Your building loan matures. Receive " + UI.CURRENCY_SYMBOL + "150.", AZIONE_RICEVI, 150));
		cards.add(new Carta(IMPREVISTI, "You have won a crossword competition. Collect " + UI.CURRENCY_SYMBOL + "100.", AZIONE_RICEVI, 100));
		cards.add(new Carta(IMPREVISTI, "Bank pays you divided of " + UI.CURRENCY_SYMBOL + "50.", AZIONE_RICEVI, 50));

		// Prigione
		cards.add(new Carta(IMPREVISTI, "Go to jail. Move directly to jail. Do not pass Go. Do not collect " + UI.CURRENCY_SYMBOL + "200.", AZIONE_VAI_IN_CARCERE));
		cards.add(new Carta(IMPREVISTI, "Get out of jail free.", AZIONE_ESCI_DAL_CARCERE));

		shuffle();
    }
}