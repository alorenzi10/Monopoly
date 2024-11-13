package Model;

import java.util.*;

import Controller.MonopolyController;
import View.*;

public class Monopoly {
	
    private final int MONEY_START = 1500; // Denaro iniziale
    private final int MONEY_VIA = 200; // Denaro ricevuto quando si passa/transita dal via
    private final int CAUZIONE_PRIGIONE = 50; // Costo per uscire dalla prigione
    public int numero_giocatori; 
    private ArrayList<Player> players;
    private Player giCorrente; // Giocatore del turno corrente
    private Dadi dice;
    private Tabellone tabellone;
    private MazzoProbabilita mazzoProbabilita;
    private MazzoImprevisti mazzoImprevisti;
    private boolean gameOver;
    private boolean fineTurno;
    private boolean tiroDadiFatto;
    private int nDadiDoppi;
    private MonopolyGUI print;

    // Crea nuova partita
    public Monopoly(int numero_giocatori, String[] nomi, MonopolyGUI monopolyGUI){
    	
    	this.print = monopolyGUI;
    	this.numero_giocatori = numero_giocatori;
    	players = new ArrayList<Player>();
    	
    	for(int i=0; i<numero_giocatori; i++) {
    		Player newPlayer = new Player(i, nomi[i], MONEY_START, false, 0);
    		players.add(newPlayer);
    		
    		
    	}
    	Random random = new Random();
    	giCorrente=players.get(random.nextInt(numero_giocatori));
    	print.stampa("tocca a "+giCorrente.getName());
    	dice = new Dadi();
    	tabellone = new Tabellone();
    	mazzoProbabilita = new MazzoProbabilita();
    	mazzoImprevisti = new MazzoImprevisti();
    	gameOver = false;
    	inizioTurno();
    }
		
	public void inizioTurno() { 
		if(giCorrente.getInPrigione() && giCorrente.haUscitaGratis()){
			print.attivaUscitaConCarta(true);
		}
		else{
			print.attivaUscitaConCarta(false);
		}
		if(giCorrente.getInPrigione()){
			print.attivaUscitaConCauzione(true);
		}
		else{
			print.attivaUscitaConCauzione(false);
		}
		fineTurno = false;
		tiroDadiFatto = false;
		nDadiDoppi = 0;
	}

	public void tiraDadi() {
		if(!tiroDadiFatto) {
			if(giCorrente.getWallet() >= 0) {
				dice.roll();
				print.stampa("Dado 1: " + dice.getDado1());
				print.stampa("Dado 2: " + dice.getDado2());
				if(giCorrente.getInPrigione() == false) {
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
						giCorrente.muovi(dice.getTotal());
						tiroDadiFatto = true;
						arrivoCasella();
					}
					else {
						giCorrente.fallitoTentativo();
						print.stampa(giCorrente.getName() + " tentativo fallito, ne hai fatti: "+ giCorrente.getTentativi());
						
						if (giCorrente.tentativiTerminati() == true) {
							print.stampa(giCorrente.getName() + " Paga 50 di cauzione");
							giCorrente.doTransaction(-CAUZIONE_PRIGIONE); //controllo su fondi giocatore
							giCorrente.liberaDaPrigione();
							giCorrente.muovi(dice.getTotal());
							tiroDadiFatto = true;
							arrivoCasella();
							
						}
					}
					
				}
			}
		} 
		else {
			print.stampa("Hai già tirato! Scegli un'altra azione.");
			}
	}
	
	// da sistemare
	public void scambia(Proprieta proprietaOfferta, Player ricevente, int denaro) {
        // Controlla se il ricevente ha abbastanza denaro per pagare l'offerente
        if (ricevente.getWallet() < denaro) {
            print.stampa("Saldo insufficiente per completare lo scambio.");
            return;
        }
        
        // Effettua lo scambio
        giCorrente.doTransaction(denaro);
        ricevente.doTransaction(-denaro);
        
        // Trasferisci la proprietà
        giCorrente.getListaProprieta().remove(proprietaOfferta);
        ricevente.aggiungiProprieta(proprietaOfferta);
        proprietaOfferta.setProprietario(ricevente);
	}
	
	public void setBancarotta(){
		// Chiedo al giocatore se è sicuro
		print.confermaBancarotta();//vedo che viene eseguito perche proviene dal event listener del button
		if(print.getDecisioneBancarotta()) {
			Player giocTemp = players.get(players.indexOf(giCorrente) + 1); // giocTemp è il giocatore successivo a quello corrente
			players.remove(giCorrente);
			giCorrente = giocTemp;
			inizioTurno();
			// Controllo eventuale vittoria
			if(players.size() == 1) {
				gameOver = true;
				print.stampa(players.get(0).getName() + " ha vinto!!");
			}
		} else return;
	}
	
	public void uscitaGratis() {
		if (giCorrente.getInPrigione()) {
			if (giCorrente.haUscitaGratis()) {
				Carta c = giCorrente.getCarta(); //che carta?
				/*if (c.getTipo() == MazzoImprevisti.IMPREVISTI) { //rimuoverei e una volta finito il mazzo lo ricreiamo
					mazzoImprevisti.add(c);
				}
				else {
					mazzoProbabilita.add(c);
				} */
				giCorrente.liberaDaPrigione();
			} else {print.stampa("Non hai carte uscite gratis di prigione!");}
		} else {print.stampa("Il giocatatore non è i prigione");}
	}
	
	public void pagaUscitaPrigione() {
		if (giCorrente.getInPrigione()) {
			if (giCorrente.getWallet() >= CAUZIONE_PRIGIONE) {
				giCorrente.doTransaction(-CAUZIONE_PRIGIONE);
				giCorrente.liberaDaPrigione();
				print.stampa("Il giocatore " + giCorrente.getName() + " ha pagato 50€ ed è uscito dalla prigione.");
			} else {
				print.stampa("Non hai abbastanza soldi!");}
		} else {
			print.stampa("Il giocatatore non è i prigione");}
	}
	
	public void setFineTurno() { //da rivedere
		if (tiroDadiFatto) {
			if (giCorrente.getWallet() > 0) {
				fineTurno = true;
			}
		}
		setProssimoGiocatore();
		print.stampa("tocca a "+giCorrente.getName());
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
		} 


		else if (casella instanceof Proprieta) {
			print.stampa(giCorrente.getName() + " è atterrato sulla proprietà " + 
					tabellone.getSquare(giCorrente.getLocation()).getNome());

			if (((Proprieta) casella).posseduta() && !((Proprieta) casella).getPossessore().equals(giCorrente)){

				int totale = ((Proprieta) casella).getAffito(); //da fare moltiplicatore x gruppo
				Player possessore = ((Proprieta) casella).getPossessore();
				print.stampa("Dai "+totale+" a "+ possessore.getName() );

				if(giCorrente.controlloFondi(totale)) {
					giCorrente.doTransaction(-totale);
					possessore.doTransaction(totale);
				}else {
					//da gestire banca rotta o ipoteca)
				}
			} 
			else if(((Proprieta) casella).posseduta() && ((Proprieta) casella).getPossessore().equals(giCorrente)){

				print.stampa("Il giocatore: " + giCorrente.getName() + " è atterrato sulla sua proprietà" );
				return;
			}
			else if(((Proprieta) casella).posseduta()==false) {
				print.stampa(tabellone.getSquare(giCorrente.getLocation()).getNome()+ " non ha nessun proprietario" );
				if(giCorrente.getWallet()<((Proprieta) casella).getCosto()) {
					//vai diretto all'asta
				}else {
					print.atterraggioSuProprietaVuota(); //decide lui se asta o compra
				}
			}
		}
		else if (casella instanceof VaiInPrigione) {

			int arrivo, partenza;
			partenza=giCorrente.getLocation();
			giCorrente.vaiInPrigione();	
			print.stampa(giCorrente.getName() + " va in prigione.");
			arrivo=giCorrente.getLocation();
			print.muoviPedina(partenza, arrivo, giCorrente.getId());

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
		case Mazzo.AZIONE_VAI_IN_CARCERE: //fatto
			giCorrente.vaiInPrigione();
			tiroDadiFatto = true;
			break;
		case Mazzo.AZIONE_ESCI_DAL_CARCERE: //da aggiungere opzione di usare la carta per uscire
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
		case Mazzo.AZIONE_RICEVI: //fatto
			giCorrente.doTransaction(carta.getQtaSoldi());
			print.stampa("Ricevi: " + carta.getQtaSoldi() + "€. Saldo: " + giCorrente.getWallet() + "€");
			break;
		case Mazzo.REGALI:  //da aggiungere bancarotta, fatto.
			for (Player p : players) {
				if(p.getName() != giCorrente.getName()) {
					if(p.controlloFondi(carta.getQtaSoldi())){
					p.doTransaction(carta.getQtaSoldi());
					print.stampa(p.getName() + " ha regalato 50€ a " + giCorrente.getName() + ".");
					}
				}
			}
			giCorrente.doTransaction(-carta.getQtaSoldi()*(players.size()-1));
			break;
		}
	}
	
	public void stampaDati() {
		for(Player p: players) {
			print.stampa("id=" + p.getId() +", nome=" +p.getName() + ", saldo=" + p.getWallet() + ", posizione=" + p.getLocation());
			if(p.getInPrigione()) {
				print.stampa("è in galera");
			}
			else {
				print.stampa("non è in galera");
			}
			if(p.haUscitaGratis()) {
				print.stampa("ha carta uscita di prigione");
			}
			ArrayList<Proprieta> proprieta=p.getListaProprieta();
			for(Proprieta prop: proprieta) {
				print.stampa(prop.getNome()+ " ");
			}
		}
	}
	
	/*private void strutturaAsta() {
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
	}*/

	public void compraProprieta() {
		
		Proprieta proprieta = (Proprieta) tabellone.getSquare(giCorrente.getLocation());
		if(giCorrente.controlloFondi(proprieta.getCosto())) {
		giCorrente.doTransaction(-proprieta.getCosto());
		giCorrente.aggiungiProprieta(proprieta);
		print.stampa(giCorrente.getName() + " ha acquistato "+ tabellone.getSquare(giCorrente.getLocation()).getNome()+
				" pagando: " + proprieta.getCosto() + "€." );
		}
		else {
			print.stampa(giCorrente.getName() + " hai fondi insufficenti, "+tabellone.getSquare(giCorrente.getLocation()).getNome()+
					" Va all'asta" );
			
		}
		 
	}
	
	/*private void costruisci() {
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
	 */

	private void setProssimoGiocatore() {
		
		if(players.indexOf(giCorrente) + 1<numero_giocatori) {
			giCorrente = players.get(players.indexOf(giCorrente) + 1);
		}
		else {
			giCorrente = players.get(0);
		}
		inizioTurno();
	}

	public boolean isGameOver() {
		return gameOver;
	}
	
	public Player getGiCorrente() {
		return giCorrente;
	}
}
  