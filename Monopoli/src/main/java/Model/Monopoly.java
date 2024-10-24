/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package Model;


import java.util.*;

import View.*;

public class Monopoly {
	
    private final int MONEY_START=1500; //soldi quando si crea una partita
    private final int MONEY_VIA=200; //soldi che si ricevono quando si passa/transita dal via
    private final int CAUZIONE_PRIGIONE=50; //costo per uscire dalla prigione
    public int numero_giocatori; 
    ArrayList<Player> players = new ArrayList<Player>();
    private Player giCorrente; //turno del giocatore nella arraylist
    private Dadi dice =new Dadi();
    private Tabellone tabellone=new Tabellone(dice);
    private MazzoProbabilita mazzoProbabilita=new MazzoProbabilita();;
    private MazzoImprevisti mazzoImprevisti=new MazzoImprevisti();;
    private boolean gameOver=false;
    private boolean fineTurno;
    private boolean tiroDadi;
    private int nDadiDoppi;
    private MonopolyGUI print = new MonopolyGUI();
    
    
    
    //crea nuova partita
    public Monopoly(int numero_giocatori, String[] nomi){
    	//crea i giocatori e assegna loro i soldi iniziali, crea il tabllone, che inizializza a sua vola le caselle
    	//crea i mazzi di probabilità e imprevisti
    	Player newPlayer;
    	this.numero_giocatori=numero_giocatori;
    	for(int i=0; i<numero_giocatori; i++) {
    		newPlayer= new Player(i, nomi[i], MONEY_START, false, 0);
    		players.add(newPlayer);
    	}
    	
    	
    	//decidi il primo giocatore
    	
    }
    //Struttura generale del gioco
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
		fineTurno=false;
		tiroDadi=false;
		nDadiDoppi=0;
		do {
			//input da pulsanti
			switch () {
			case 1:
				tiraDadi();
				break;
			}
		}while(fineTurno!=true);
		
	}
	private void tiraDadi() {
		if(tiroDadi!=true) {
			if(giCorrente.getWallet() >= 0) {
				dice.roll();
				print.stampa("Dado 1: " + dice.getDado1());
				print.stampa("Dado 1: " + dice.getDado1());
				giCorrente.muovi(dice.getTotal());
				controlloPassaggioVia();
				arrivoCasella();
			}
		}
	}
	
	public void controlloPassaggioVia() {
		if (giCorrente.passaggioVia()) {
			giCorrente.doTransaction(200);
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
			tiroDadi = true;
			break;
		case Mazzo.AZIONE_ESCI_DAL_CARCERE:
			giCorrente.addCarta(carta);
			break;
		case Mazzo.AZIONE_PAGA_CASE:
			int totale = giCorrente.getNumCasePossesso()*carta.getCostoCase() + giCorrente.getNumAlberghiPossesso()*carta.getCostoAlberghi();
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
			for (Player altriGiocatori : players.getAltriGiocatori(giCorrente)) {
				//da fare
			}
			break;
		}
	}
	public boolean isGameOver() {
		return gameOver;
	}
}
  