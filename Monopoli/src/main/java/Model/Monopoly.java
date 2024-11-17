package Model;

import java.util.*;

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
    public Asta asta;

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
    	tabellone = new Tabellone(dice);
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
						tiroDadiFatto = true;
						print.stampa(giCorrente.getName() + " tentativo fallito, ne hai fatti: "+ giCorrente.getTentativi());
						
						if (giCorrente.tentativiTerminati() == true) {
							print.stampa(giCorrente.getName() + " Paga 50 di cauzione");
							giCorrente.doTransaction(-CAUZIONE_PRIGIONE); //controllo su fondi giocatore
							giCorrente.liberaDaPrigione();
							giCorrente.muovi(dice.getTotal());
							
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
        
        aggiornaVisualizzazioneInfo();
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
		} else aggiornaVisualizzazioneInfo();
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
			print.stampa("Il giocatore non è in prigione");}
		aggiornaVisualizzazioneInfo();
	}
	
	public void setFineTurno() { //da rivedere
		if (tiroDadiFatto) {
			setProssimoGiocatore();
			print.stampa("tocca a " + giCorrente.getName());
		}
		else{
			print.stampa(giCorrente.getName()+ " devi ancora tirare");
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
			if(( mazzoImprevisti).isEmpty()){
				print.stampa("Carte Imprevisti esaurite, rimescolo" );
				mazzoImprevisti = new MazzoImprevisti();
			}
			Carta carta = mazzoImprevisti.get();
			print.stampa("Imprevisto");
			print.stampa(carta.getMessaggio());
			azioneCarta(carta);
		} else if (casella instanceof Probabilita) {
			if(mazzoProbabilita.isEmpty() ){
				print.stampa("Carte Probabilità esaurite, rimescolo" );
				mazzoProbabilita = new MazzoProbabilita();
			}
			Carta carta = mazzoProbabilita.get();
			print.stampa("Probabilità");
			print.stampa(carta.getMessaggio());
			azioneCarta(carta);

		} else if (casella instanceof Tassa) {
			int totale = ((Tassa) casella).getTotale();
			giCorrente.doTransaction(-totale);
			print.stampa("Paga la tassa patrimoniale: " + ((Tassa) casella).getTotale() + "€. Saldo: " + giCorrente.getWallet() + "€.");
		} 

		else if (casella instanceof Proprieta) {
			print.stampa(giCorrente.getName() + " è atterrato sulla proprietà " + 
					tabellone.getSquare(giCorrente.getLocation()).getNome());

			if (((Proprieta) casella).posseduta() && !((Proprieta) casella).getPossessore().equals(giCorrente)){

				int totale = ((Proprieta) casella).getAffitto(); //da fare moltiplicatore x gruppo
				Player possessore = ((Proprieta) casella).getPossessore();
				print.stampa("Dai " + totale + " a " + possessore.getName() );

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
			else if(((Proprieta) casella).posseduta() == false) {
				print.stampa(tabellone.getSquare(giCorrente.getLocation()).getNome() + " non ha nessun proprietario" );
				if(giCorrente.getWallet()<((Proprieta) casella).getCosto()) {
					iniziaAsta();
				}else {
					print.atterraggioSuProprietaVuota(); //decide lui se asta o compra
				}
			}
		}
		else if (casella instanceof VaiInPrigione) {

			int arrivo, partenza;
			partenza = giCorrente.getLocation();
			giCorrente.vaiInPrigione();	
			print.stampa(giCorrente.getName() + " va in prigione.");
			arrivo = giCorrente.getLocation();
			print.muoviPedina(partenza, arrivo, giCorrente.getId());

		} 
		aggiornaVisualizzazioneInfo();
	}

	private void azioneCarta(Carta carta) {
		int partenza, pos;

		switch (carta.getAction()) {
		case Mazzo.AZIONE_VAI_AVANTI:
			int diff;
			int destinazione=carta.getDestination();
			partenza=giCorrente.getLocation();
			if(giCorrente.getLocation()<destinazione) {
				diff=destinazione-giCorrente.getLocation();
				giCorrente.muovi(diff);
			}
			else {
				diff=40-giCorrente.getLocation();
				diff=diff+destinazione;
				giCorrente.muovi(diff);
				controlloPassaggioVia();
			}
			arrivoCasella();
			break;

		case Mazzo.AZIONE_VAI_INDIETRO:
			partenza=giCorrente.getLocation();
			giCorrente.muovi(carta.getDestination());
			pos=giCorrente.getLocation();
			print.muoviPedina(partenza, pos, giCorrente.getId());
			arrivoCasella();
			break;

		case Mazzo.AZIONE_VAI_IN_CARCERE: 
			giCorrente.vaiInPrigione();
			tiroDadiFatto = true;
			break;
		case Mazzo.AZIONE_ESCI_DAL_CARCERE: //da aggiungere opzione di usare la carta per uscire
			giCorrente.addCarta(carta);
			break;
		case Mazzo.AZIONE_PAGA_CASE: //DA FARE
			int totale = giCorrente.getNumCasePossedute()*carta.getCostoCase() + giCorrente.getNumAlberghiPosseduti()*carta.getCostoAlberghi();
			giCorrente.doTransaction(-totale);
			break;
		case Mazzo.AZIONE_PAGA:
			giCorrente.doTransaction(-carta.getQtaSoldi());
			print.stampa("Paga: " + carta.getQtaSoldi() + "€. Saldo: " + giCorrente.getWallet() + "€");
			break;
		case Mazzo.AZIONE_RICEVI: 
			giCorrente.doTransaction(carta.getQtaSoldi());
			print.stampa("Ricevi: " + carta.getQtaSoldi() + "€. Saldo: " + giCorrente.getWallet() + "€");
			break;
		case Mazzo.REGALI:  

			if(carta.getTipo()==0) {
				for (Player p : players) {
					if(p.getName() != giCorrente.getName()) {
						if(p.controlloFondi(carta.getQtaSoldi())){
							p.doTransaction(carta.getQtaSoldi());
							print.stampa(giCorrente.getName() + " ha regalato 50€ a " + p.getName() + ".");
						}
					}
				}
				giCorrente.doTransaction(-carta.getQtaSoldi()*(players.size()-1));
			}else {
				for (Player p : players) {
					if(p.getName() != giCorrente.getName()) {
						if(p.controlloFondi(-carta.getQtaSoldi())){
							p.doTransaction(-carta.getQtaSoldi());
							print.stampa(p.getName() + " ha regalato 10€ a " + giCorrente.getName() + ".");
						}
					}
				}
				giCorrente.doTransaction(carta.getQtaSoldi()*(players.size()-1));
			}

			break;

		case Mazzo.AZIONE_SOCIETA_VICINA:
			partenza=giCorrente.getLocation();
			if(giCorrente.getLocation()==7) {
				giCorrente.muovi(5);
			}
			else if(giCorrente.getLocation()==36) {
				giCorrente.muovi(15);

			}
			else if(giCorrente.getLocation()==22) {
				giCorrente.muovi(6);
			}
			pos=giCorrente.getLocation();

			print.muoviPedina(partenza, pos, giCorrente.getId() );
			controlloPassaggioVia();

			if(((Proprieta) tabellone.getSquare(pos)).posseduta()) {
				if(((Proprieta) tabellone.getSquare(pos)).getPossessore().getName() !=giCorrente.getName()){
					dice.roll();
					print.stampa("Dado 1: " + dice.getDado1()); //lancio dei dadi per calcolare il coeffx10
					print.stampa("Dado 2: " + dice.getDado2());
					print.stampa("Devi pagare "+ dice.getTotal()+"0 volte il totale dovuto");
					giCorrente.doTransaction(((Proprieta) tabellone.getSquare(pos)).getAffitto()*dice.getTotal()*-10);
					((Proprieta) tabellone.getSquare(pos)).getPossessore()
					.doTransaction(((Proprieta) tabellone.getSquare(pos)).getAffitto()*dice.getTotal()*10);
				}
			}else {
				print.atterraggioSuProprietaVuota();
			}
			break;

		case Mazzo.AZIONE_FERROVIA_VICINA:
			partenza=giCorrente.getLocation();
			if(giCorrente.getLocation()==7) {
				giCorrente.muovi(8);
			}
			else if(giCorrente.getLocation()==36) {
				giCorrente.muovi(9);
			}
			else if(giCorrente.getLocation()==22) {
				giCorrente.muovi(3);
			}
			pos=giCorrente.getLocation();
			print.muoviPedina(partenza, pos, giCorrente.getId() );
			controlloPassaggioVia();

			if(((Proprieta) tabellone.getSquare(pos)).posseduta()) {
				if(((Proprieta) tabellone.getSquare(pos)).getPossessore().getName() !=giCorrente.getName()){

					print.stampa("Devi pagare il doppio del totale dovuto");
					giCorrente.doTransaction(((Proprieta) tabellone.getSquare(pos)).getAffitto()*-2);
					((Proprieta) tabellone.getSquare(pos)).getPossessore()
					.doTransaction(((Proprieta) tabellone.getSquare(pos)).getAffitto()*2);
				}
			}else {
				print.atterraggioSuProprietaVuota();
			}

		}

		aggiornaVisualizzazioneInfo();
	}
	
	public void stampaDati() {
		for(Player p: players) {
			print.stampa("id=" + p.getId() + ", nome=" +p.getName() + ", saldo=" + p.getWallet() + ", posizione=" + p.getLocation());
			if(p.getInPrigione()) {
				print.stampa("è in galera");
			}
			else {
				print.stampa("non è in galera");
			}
			if(p.haUscitaGratis()) {
				print.stampa("ha carta uscita di prigione");
			}
			ArrayList<Proprieta> proprieta = p.getListaProprieta();
			for(Proprieta prop: proprieta) {
				print.stampa(prop.getNome()+ " ");
			}
		}
	}
	
	public void aggiornaVisualizzazioneInfo() {
		//Aggiornamento nel pannello delle info dei giocatori (saldo)
		int[] valoriSaldo = new int[getNumGiocatori()];
		for(int i = 0; i < getNumGiocatori(); i++) 
			valoriSaldo[i] = getPlayers().get(i).getWallet();
		print.aggiornaVisSaldoGiocatori(valoriSaldo); // Aggiorna la visualizzazione del saldo dei giocatori
		
		//Aggiornamento nel pannello delle info dei giocatori (elenco proprietà)
		ArrayList<ArrayList<String>> elencoProp = new ArrayList<>();
		for(Player player: getPlayers()) {
			ArrayList<String> proprietaGiocatore = new ArrayList<>();
			for (Proprieta prop: player.getListaProprieta())
				proprietaGiocatore.add(prop.getNome());
			elencoProp.add(proprietaGiocatore);
		}
		print.aggiornaVisProprietaGiocatori(elencoProp); // Aggiorna la visualizzazione delle proprietà dei giocatori
	}
	
	public void iniziaAsta() {
		//passaggio dei dati utili per l'asta
		asta = new Asta(giCorrente.getId(), players, (Proprieta) tabellone.getSquare(giCorrente.getLocation()), print);
		asta.inizio(); 
	}

	public void compraProprieta() {
		
		Proprieta proprieta = (Proprieta) tabellone.getSquare(giCorrente.getLocation());
		if(giCorrente.controlloFondi(proprieta.getCosto())) {
		giCorrente.doTransaction(-proprieta.getCosto());
		giCorrente.aggiungiProprieta(proprieta);
		print.stampa(giCorrente.getName() + " ha acquistato "+ tabellone.getSquare(giCorrente.getLocation()).getNome()+
				" pagando: " + proprieta.getCosto() + "€." );
		}
		else {
			print.stampa(giCorrente.getName() + " hai fondi insufficenti, " + tabellone.getSquare(giCorrente.getLocation()).getNome()+
					" Va all'asta" );
			iniziaAsta();
		}
		 
	}
	
	private void costruisci(Proprieta prop) {
		//Proprieta prop; //ricevuta da input
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
								} 
								else {print.stampa("Non hai abbastanza soldi per costruire.");}
								
							} else {print.stampa("Hai già l'albergo su questa proprietà.");}
						} else {print.stampa("Non puoi costruire, la differenza tra le case non può essere maggiore di uno.");}
					}else {print.stampa("Non puoi costruire, la proprietà è ipotecata.");}
				}else {print.stampa("Non possiedi tutto il gruppo.");}
			}else {print.stampa("Non puoi costruire su questa proprietà.");}
		}else {print.stampa("Non puoi costruire su questa proprietà perchè non la possiedi.");}
	}
	/*
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
	public int getNumGiCorrente() {
		return giCorrente.getId();
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public int getNumGiocatori() {
		return getPlayers().size();
	}

	public Tabellone getTabellone() {
		return tabellone;
	}
}
  