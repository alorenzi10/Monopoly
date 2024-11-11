package Model;

import java.util.*;
import View.*;

public class Monopoly {
	
    private final int MONEY_START = 1500; // Denaro iniziale
    private final int MONEY_VIA = 200; // Denaro ricevuto quando si passa/transita dal via
    private final int CAUZIONE_PRIGIONE = 50; // Costo per uscire dalla prigione
    public int numero_giocatori; 
    private ArrayList<Player> players = new ArrayList<Player>();
    private Player giCorrente; // Giocatore del turno corrente
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
    		if (!isGameOver()) {
    			setProssimoGiocatore();
    		}
    	}while(!isGameOver());
    	
    }

	public void daiSoldiIniziali() {
		for (int i=0; i<numero_giocatori; i++) {
			players.get(i).doTransaction(MONEY_START); 
		}
	}
	
	
	public void strutturaTurno() {//al momento non riceve i valori, bisogna usare i listener
		fineTurno = false;
		tiroDadiFatto = false;
		nDadiDoppi = 0;
		do {
			switch(print.getComando()) {
			case MonopolyGUI.COMANDO_TIRA_DADI: tiraDadi();		break;
			case MonopolyGUI.COMANDO_SCAMBI: scambi();		break;
			case MonopolyGUI.COMANDO_GESTIONE_PROPRIETA: gestioneProprieta();	break;
			case MonopolyGUI.COMANDO_BANCAROTTA: setBancarotta();	break;
			case MonopolyGUI.COMANDO_USCITA_GRATIS: uscitaGratis();	break;
			case MonopolyGUI.COMANDO_USCITA_PAGANDO: pagaUscitaPrigione();	break;
			case MonopolyGUI.COMANDO_FINE_TURNO: setFineTurno();	break;
			}		
		}while(!fineTurno);
	}

	public void tiraDadi() {
		if(!tiroDadiFatto) {
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
						print.stampa(giCorrente.getName() + " è uscito dalla prigione.");
					}
					else {
						giCorrente.fallitoTentativo();
						if (giCorrente.tentativiTerminati() == true) {
							giCorrente.doTransaction(-CAUZIONE_PRIGIONE);
							giCorrente.liberaDaPrigione();
							print.stampa(giCorrente.getName() + " è uscito dalla prigione pagando 50€.");
						}
					}
					giCorrente.muovi(dice.getTotal());
					tiroDadiFatto = true;
				}
			}
		} else {print.stampa("Hai già tirato! Scegli un'altra azione.");}
	}
	
	// da sistemare
	public void scambia(Player offerente, Proprieta proprietaOfferta, Player ricevente, int denaro) {
        // Controlla se il ricevente ha abbastanza denaro per pagare l'offerente
        if (ricevente.getWallet() < denaro) {
            print.stampa("Saldo insufficiente per completare lo scambio.");
            return;
        }
        
        // Effettua lo scambio
        offerente.doTransaction(denaro);
        ricevente.doTransaction(-denaro);
        
        // Trasferisci la proprietà
        offerente.getListaProprieta().remove(proprietaOfferta);
        ricevente.aggiungiProprieta(proprietaOfferta);
        proprietaOfferta.setProprietario(ricevente);
	}
	
	public void gestioneProprieta() {
		switch (print.getComando()) {
		case MonopolyGUI.COMANDO_COSTRUSCI: costruisci();	break;
		case MonopolyGUI.COMANDO_DEMOLISCI: demolisci();	break;
		case MonopolyGUI.COMANDO_IPOTECA: ipoteca();	break;
		case MonopolyGUI.COMANDO_DISIPOTECA: disipoteca();	break;
		}
	}
	
	public void setBancarotta(){
		// Chiedo al giocatore se è sicuro
		print.confermaBancarotta();//vedo che viene eseguito perche proviene dal event listener del button
		if(print.getDecisioneBancarotta()) {
			Player giocTemp = players.get(players.indexOf(giCorrente) + 1); // giocTemp è il giocatore successivo a quello corrente
			players.remove(giCorrente);
			giCorrente = giocTemp;
			// Controllo eventuale vittoria
			if(players.size() == 1) {
				gameOver = true;
				print.stampa(players.get(0).getName() + " ha vinto!!");
			}
		} else return;
	}
	
	private void uscitaGratis() {
		if (giCorrente.eInPrigione()) {
			if (giCorrente.haUscitaGratis()) {
				Carta c = giCorrente.getCarta();
				if (c.getTipo() == MazzoImprevisti.IMPREVISTI) {
					mazzoImprevisti.add(c);
				}
				else {
					mazzoProbabilita.add(c);
				}
				giCorrente.liberaDaPrigione();
			} else {print.stampa("Non hai carte uscite gratis di prigione!");}
		} else {print.stampa("Il giocatatore non è i prigione");}
	}
	
	private void pagaUscitaPrigione() {
		if (giCorrente.eInPrigione()) {
			if (giCorrente.getWallet() >= CAUZIONE_PRIGIONE) {
				giCorrente.doTransaction(-CAUZIONE_PRIGIONE);
				giCorrente.liberaDaPrigione();
				print.stampa("Il giocaotre " + giCorrente.getName() + " ha pagato 50€ ed è uscito dalla prigione.");
			} else {print.stampa("Non hai abbastanza soldi!");}
		} else {print.stampa("Il giocatatore non è i prigione");}
	}
	
	public void setFineTurno() {
		if (tiroDadiFatto) {
			if (giCorrente.getWallet() > 0) {
				fineTurno = true;
			}
		}
	}
	
	public void controlloPassaggioVia() {
		if (giCorrente.passaggioVia()) {
			giCorrente.doTransaction(MONEY_VIA);
			print.stampa("Il giocatore: " + giCorrente.getName() + " è passato dal via, riceve 200€." );
		}
	}
	
	public void arrivoCasella() {
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
			print.stampa("Paga: " + ((Tassa) casella).getTotale() + "€. Saldo: " + giCorrente.getWallet() + "€.");
		} else if (casella instanceof VaiInPrigione) {
			giCorrente.vaiInPrigione();	
			print.stampa(giCorrente.getName() + " va in prigione.");
		} else if (casella instanceof Proprieta) {
			if (((Proprieta) casella).posseduta() && !((Proprieta) casella).getPossessore().equals(giCorrente)){
				int totale = ((Proprieta) casella).getAffito();
				Player possessore = ((Proprieta) casella).getPossessore();
				giCorrente.doTransaction(-totale);
				possessore.doTransaction(totale);
			} else if (giCorrente.getWallet()<((Proprieta) casella).getCosto()){
				strutturaAsta();
			}else {
				if (print.getComando() == MonopolyGUI.COMANDO_ASTA) {
					strutturaAsta();
				}else if (print.getComando() == MonopolyGUI.COMANDO_COMPRA) {
					compraProprieta();
				}
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
			for (Player p : players) {
				if(p.getName() != giCorrente.getName()) {
					p.doTransaction(-carta.getQtaSoldi());
					print.stampa(p.getName() + " ha regalato 10€ a " + giCorrente.getName() + ".");
				}
			}
			giCorrente.doTransaction(carta.getQtaSoldi()*(players.size()-1));
			break;
		}
	}
	
	private void strutturaAsta() {
		int offerta_corrente = 0;
		Proprieta proprieta = (Proprieta) tabellone.getSquare(giCorrente.getLocation());
		ArrayList<Player> playersAsta = players;
		do {
			for (Player p : playersAsta) {
				boolean fineTurnoAsta = false;
				do {
					switch(print.getComando()) {
					case MonopolyGUI.COMANDO_RILANCIA_1:
						if(p.getWallet() < offerta_corrente+1) {
							print.stampa("Non hai abbastanza soldi, rinuncia all'asta.");
						} else {
							offerta_corrente+=1;
							fineTurnoAsta = true;
						}		
						break;
					case MonopolyGUI.COMANDO_RILANCIA_10:
						if(p.getWallet() < offerta_corrente+10) {
							print.stampa("Non hai abbastanza soldi, fai un'offerta minore o rinuncia.");
						} else {
							offerta_corrente+=10;
							fineTurnoAsta = true;
							}		
						break;
					case MonopolyGUI.COMANDO_RILANCIA_50: 
						if(p.getWallet() < offerta_corrente+50) {
							print.stampa("Non hai abbastanza soldi, fai un'offerta minore o rinuncia.");
						} else {
							offerta_corrente+=50;
							fineTurnoAsta = true;
							}		
						break;
					case MonopolyGUI.COMANDO_RINUNCIA:		
						playersAsta.remove(p);
						fineTurnoAsta = true;
						break;
					}
				} while (!fineTurnoAsta);
			}
		}while(playersAsta.size() > 1);
		playersAsta.get(0).doTransaction(-offerta_corrente);
		playersAsta.get(0).aggiungiProprieta(proprieta);
	}

	private void compraProprieta() {
		Proprieta proprieta = (Proprieta) tabellone.getSquare(giCorrente.getLocation());
		giCorrente.doTransaction(-proprieta.getCosto());
		giCorrente.aggiungiProprieta(proprieta);
		print.stampa(giCorrente.getName() + " ha acquistato una proprietà pagando: " + proprieta.getCosto() + "€." );
	}
	
	private void costruisci() {
		Proprieta prop; //ricevuta da input
		if (prop.posseduta() && prop.getPossessore().equals(giCorrente)) {
			if (prop instanceof Cantiere) {
				Cantiere cant = (Cantiere) prop;
				if (giCorrente.possessoreGruppo(cant)) {
					if (!cant.isIpotecata()) {
						GruppoColore gc = cant.getGruppoColore();
						int min = Integer.MAX_VALUE;
						int max = Integer.MIN_VALUE;
						
						for (Cantiere c: gc.getMembri()) {
							if (c.isIpotecata()) {
	                            print.stampa("Non puoi costruire: una proprietà del gruppo è ipotecata.");
	                            return;
	                        }
							int nCase = c.getNumCase();
							min = Math.min(min, nCase);
							max = Math.max(max, nCase);
						}
						if(cant.getNumCostruzioni() == min || min == max) {
							if(cant.getNumCostruzioni() != 5) {
								if(giCorrente.getWallet() >= cant.getCostoCasa()) {
									cant.costruisci();
									giCorrente.doTransaction(-cant.getCostoCasa());
								} else {print.stampa("Non hai abbastanza soldi per costruire.");}
							} else {print.stampa("Hai già l'albergo su questa proprietà.");}
						} else {print.stampa("Non puoi costruire, la differenza tra le case non può essere maggiore di uno.");}
					}else {print.stampa("Non puoi costruire, la proprietà è ipotecata.");}
				}else {print.stampa("Non possiedi tutto il gruppo.");}
			}else {print.stampa("Non puoi costruire su questa proprietà.");}
		}else {print.stampa("Non puoi costruire su questa proprietà perchè non la possiedi.");}
	}
	
	private void demolisci() {
		Proprieta prop; //ricevuta da input
		if (prop.posseduta() && prop.getPossessore().equals(giCorrente)) {
			if (prop instanceof Cantiere) {
				Cantiere cant = (Cantiere) prop;
				GruppoColore gc = cant.getGruppoColore();
				int min = Integer.MAX_VALUE;
				int max = Integer.MIN_VALUE;
				
				for (Cantiere c: gc.getMembri()) {
					int nCase = c.getNumCase();
					min = Math.min(min, nCase);
					max = Math.max(max, nCase);
				}
				if (cant.getNumCostruzioni() == max || max==min) {
					if (cant.getNumCostruzioni()>0) {
						cant.demolisci();
						giCorrente.doTransaction(cant.getCostoCasa()/2);
					}else {print.stampa("Non hai case su questa proprietà");}
				}else {print.stampa("Distruzione non valida, la differenza tra le case non può essere maggiore di uno.");}
			}else {print.stampa("Non puoi costruire su questa proprietà.");}
		}else {print.stampa("Non puoi demolire se una proprietà che non possiedi");}
	}
	
	private void ipoteca() {
		Proprieta prop;
		int valoreCase=0;
		if(prop.posseduta() && prop.getPossessore().equals(giCorrente)) {
			if ((prop instanceof Cantiere) || (prop instanceof Stazione) || (prop instanceof Societa)){
				if (prop.isIpotecata() == false) {
					if (((Cantiere) prop).haCase()) {
						Cantiere cant = (Cantiere) prop;
		                GruppoColore gc = cant.getGruppoColore();
		                for (Cantiere c : gc.getMembri()) {
		                	if (c.haCase()) {
		                            valoreCase += c.getNumCase() * c.getCostoCasa()/2;
		                	}
		                }
					}
					giCorrente.doTransaction(valoreCase+prop.getPrezzoIpoteca());
				}
			}
		}
	}
	
	private void disipoteca() {
		Proprieta prop;
		if (prop.posseduta() && prop.getPossessore().equals(giCorrente)) {
			if ((prop instanceof Cantiere) && !((Cantiere) prop).haCase() || (prop instanceof Stazione) || (prop instanceof Societa)) {
				if (prop.isIpotecata() && giCorrente.getWallet()>=prop.getCostoDisipoteca()) {
					giCorrente.doTransaction(-prop.getCostoDisipoteca());					
				}
			}
		}
	}

	private void setProssimoGiocatore() {
		giCorrente = players.get(players.indexOf(giCorrente) + 1);
	}

	public boolean isGameOver() {
		return gameOver;
	}
	
	public Player getGiCorrente() {
		return giCorrente;
	}
}
  