package Model;

import java.util.*;

import View.*;

public class Monopoly {
	
    private final int MONEY_START = 1500; // Denaro iniziale
    private final int MONEY_VIA = 200; // Denaro ricevuto quando si passa/transita dal via
    private final int CAUZIONE_PRIGIONE = 50; // Costo per uscire dalla prigione
    public int numero_giocatori; 
    private ArrayList<Player> players = new ArrayList<Player>();
    private Player giCorrente; // Turno del giocatore nella arraylist
    private Dadi dice = new Dadi();
    private Tabellone tabellone = new Tabellone(dice);
    private MazzoProbabilita mazzoProbabilita = new MazzoProbabilita();
    private MazzoImprevisti mazzoImprevisti = new MazzoImprevisti();
    private boolean gameOver = false;
    private boolean fineTurno;
    private boolean tiroDadiFatto;
    private int nDadiDoppi;
    private MonopolyGUI print = new MonopolyGUI();
    
    
    
    // Crea nuova partita
    public Monopoly(int numero_giocatori, String[] nomi){
    	
    	this.numero_giocatori = numero_giocatori;
    	for(int i=0; i<numero_giocatori; i++) {
    		Player newPlayer = new Player(i, nomi[i], MONEY_START, false, 0);
    		players.add(newPlayer);
    	}
    }
    
    // Struttura generale del gioco
    public void gioca() {
    	daiSoldiIniziali();
    	do {
    		strutturaTurno();
    	}while(!isGameOver());
    }

	
	public void daiSoldiIniziali() {
		for (int i=0; i<numero_giocatori; i++) {
			players.get(i).doTransaction(MONEY_START); 
		}
	}
	
	
	public void strutturaTurno() {
		fineTurno = false;
		tiroDadiFatto = false;
		nDadiDoppi = 0;
		do {
			switch(print.getComando()) {
			case print.COMANDO_TIRA_DADI:
				tiraDadi();		break;
			case print.COMANDO_SCAMBI:
			}
			
			//input da pulsanti
			//Proprieta
			//Scambi
			tiraDadi();
			setFineTurno();
			
		}while(!fineTurno);
	}
	
	private void tiraDadi() {
		if(tiroDadiFatto!=true) {
			if(giCorrente.getWallet() >= 0) {
				dice.roll();
				print.stampa("Dado 1: " + dice.getDado1());
				print.stampa("Dado 2: " + dice.getDado2());
				if(giCorrente.eInPrigione() == false) {
					giCorrente.muovi(dice.getTotal());
					controlloPassaggioVia();
					arrivoCasella();
					if (dice.isDouble() == true) {
						nDadiDoppi++;
						if(nDadiDoppi == 3) {
							giCorrente.vaiInPrigione();
							tiroDadiFatto = true;
						}
					}
					else {tiroDadiFatto = true;}
				}
				else {
					if (dice.isDouble()) {
						giCorrente.liberaDaPrigione();
						print.stampa(giCorrente.getName() + " è uscito dalla prigione");
					}
					else {
						giCorrente.fallitoTentativo();
						if (giCorrente.tentativiTerminati() == true) {
							giCorrente.doTransaction(-CAUZIONE_PRIGIONE);
							giCorrente.liberaDaPrigione();
							print.stampa(giCorrente.getName() + " è uscito dalla prigione pagando 50€");
						}
					}
					giCorrente.muovi(dice.getTotal());
					tiroDadiFatto = true;
				}
			}
		}
	}
	
	private void setFineTurno() {
		if (tiroDadiFatto) {
			if (giCorrente.getWallet() > 0) {
				fineTurno = true;
			}
		}
	}
	
	
	
	
	public void controlloPassaggioVia() {
		if (giCorrente.passaggioVia()) {
			giCorrente.doTransaction(MONEY_VIA);
			print.stampa("Il giocatore: " + giCorrente.getName() + " è passato dal via, riceve 200€" );
		}
	}
	
	private void arrivoCasella() {
		Casella casella = tabellone.getSquare(giCorrente.getLocation());
		if (casella instanceof Imprevisti) {
			Carta carta = mazzoImprevisti.get();
			print.stampa(carta.getMessaggio());
			azioneCarta(carta);
		} else if (casella instanceof Probabilita) {
			Carta carta = mazzoProbabilita.get();
			print.stampa(carta.getMessaggio());
			azioneCarta(carta);
		} else if (casella instanceof Tassa) {
			int totale = ((Tassa) casella).getTotale();
			giCorrente.doTransaction(totale);
			print.stampa("Paga: " + ((Tassa) casella).getTotale() + "€. Saldo: " + giCorrente.getWallet() + "€");
		} else if (casella instanceof VaiInPrigione) {
			giCorrente.vaiInPrigione();	
			print.stampa(giCorrente.getName() + "va in prigione");
		} else if (casella instanceof Proprieta) {
			if (((Proprieta) casella).posseduta() && !((Proprieta) casella).getPossessore().equals(giCorrente)){
				int totale = ((Proprieta) casella).getAffito();
				Player possessore = ((Proprieta) casella).getPossessore();
				giCorrente.doTransaction(-totale);
				possessore.doTransaction(totale);
			} else if (giCorrente.getWallet()<((Proprieta) casella).getCosto()){
				strutturaAsta();
			}
			else {
				//scelta tra comprare o mettere all'asta
				//far vedere la 
				strutturaAsta();
				compraProprieta();
			}
		}
		
	}
	
	
	
	private void azioneCarta(Carta carta) {
		switch (carta.getAction()) {
		case Mazzo.AZIONE_VAI_AVANTI:
			giCorrente.muovi(carta.getDestination());
			controlloPassaggioVia();
			arrivoCasella();
			break;
		case Mazzo.AZIONE_VAI_INDIETRO:
			giCorrente.muovi(carta.getDestination());
			arrivoCasella();
			break;
		case Mazzo.AZIONE_VAI_IN_CARCERE:
			giCorrente.vaiInPrigione();
			tiroDadiFatto = true;
			break;
		case Mazzo.AZIONE_ESCI_DAL_CARCERE:
			giCorrente.addCarta(carta);
			break;
		case Mazzo.AZIONE_PAGA_CASE:
			int totale = giCorrente.getNumCasePossedute()*carta.getCostoCase() + giCorrente.getNumAlberghiPosseduti()*carta.getCostoAlberghi();
			giCorrente.doTransaction(-totale);
			break;
		case Mazzo.AZIONE_PAGA:
			giCorrente.doTransaction(-carta.getQtaSoldi());
			print.stampa("Paga: " + carta.getQtaSoldi() + "€. Saldo: " + giCorrente.getWallet() + "€");
			break;
		case Mazzo.AZIONE_RICEVI:
			giCorrente.doTransaction(-carta.getQtaSoldi());
			print.stampa("Ricevi: " + carta.getQtaSoldi() + "€. Saldo: " + giCorrente.getWallet() + "€");
			break;
		case Mazzo.REGALI:
			/*for (Player altriGiocatori : players.getAltriGiocatori(giCorrente)) {
				//da fare
			}*/
			break;
		}
	}
	
	private void strutturaAsta() {
		//da fare		
	}
	
	private void compraProprieta() {
		Proprieta proprieta = (Proprieta) tabellone.getSquare(giCorrente.getLocation());
		giCorrente.doTransaction(-proprieta.getCosto());
		print.stampa(giCorrente.getName() + " ha acquistato una proprietà pagando: " + proprieta.getCosto() + "€" );
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
}
  