package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import View.MonopolyGUI;

public class Monopoly {
	
	public String nomePartita; //campi per json
	private String salvataggioDateTime;
	
    private final int MONEY_START = 1500; // Denaro iniziale
    private final int MONEY_VIA = 200; // Denaro ricevuto quando si passa/transita dal via
    private final int CAUZIONE_PRIGIONE = 50; // Costo per uscire dalla prigione
    private int indexCorrente; //campi per json
    private ArrayList<Player> players;
    private transient Player giCorrente; // Giocatore del turno corrente
    private transient Dadi dadi;
    private boolean tiroDadiFatto;
    private int nDadiDoppi;
    private transient Tabellone tabellone;	
    private MazzoProbabilita mazzoProbabilita;
    private MazzoImprevisti mazzoImprevisti;
    private boolean gameOver;
    private transient MonopolyGUI monopolyGUI;
    public  transient Asta asta; //da sistemare
    private transient Random random = new Random();
    
    private transient ArrayList<Proprieta> listaPropBancarotta;
    private transient int conta;
    private String[] pedineSelezionate;

    // Crea nuova partita
    public Monopoly(int numero_giocatori, String[] nomi, MonopolyGUI monopolyGUI){
    	
    	this.monopolyGUI = monopolyGUI;
    	players = new ArrayList<Player>();
    	
    	//Inizializzazione dei giocatori
    	for(int i=0; i<numero_giocatori; i++) {
    		Player newPlayer = new Player(i, nomi[i], MONEY_START, false, 0);
    		players.add(newPlayer);
    	}
    	//scelta casuale di chi inizia a giocare
    	giCorrente = players.get(random.nextInt(numero_giocatori));
    	
    	dadi = new Dadi();
    	tabellone = new Tabellone(dadi);
    	mazzoProbabilita = new MazzoProbabilita();
    	mazzoImprevisti = new MazzoImprevisti();
    	gameOver = false;
    	
    	inizioTurno();
    }

	public void inizioTurno() { 
		
		if(giCorrente.getInPrigione() && giCorrente.haUscitaGratis()){
			monopolyGUI.attivaUscitaConCarta(true); //carta per uscire dei prigione
		} else{
			monopolyGUI.attivaUscitaConCarta(false);
		}
		
		if(giCorrente.getInPrigione() && giCorrente.controlloFondi(CAUZIONE_PRIGIONE)){ //se è in prigione attiva la possibilità di pagare la cauzione
			monopolyGUI.attivaUscitaConCauzione(true); 
		} else{
			monopolyGUI.attivaUscitaConCauzione(false);
		}
		
		tiroDadiFatto = false;
		nDadiDoppi = 0;
		monopolyGUI.stampa("tocca a " + giCorrente.getName());
	}

	public void tiraDadi() {
		
		//questo metodo viene chiamato dopo che il giocatore preme il bottone tira dadi
		if(!tiroDadiFatto) { //controlla se ha tiri disponibili
			if(giCorrente.getWallet() >= 0) { //il giocatore deve risolvere i suoi debiti prima di continuare
				dadi.roll();
				monopolyGUI.stampa("Dado 1: " + dadi.getDado1());
				monopolyGUI.stampa("Dado 2: " + dadi.getDado2());
				if(giCorrente.getInPrigione() == false) { //controlla se il giocatore è in prigione o meno
					
					giCorrente.muovi(dadi.getTotal()); 
					controlloPassaggioVia(); //se con il metodo muovi() il giocatore raggiunge/supera il via, viene inizializzata una variabile di giocatore a true.
					arrivoCasella();
					if (dadi.isDouble()) { //se ha fatto un dado doppio ha diritto ad un altro tiro
						nDadiDoppi++;
						if(nDadiDoppi == 3) { //fino ad un massimo di 2 dadi doppi, dopo di che viene trasferito in prigione
							giCorrente.vaiInPrigione();
							tiroDadiFatto = true;
						}
					}
					else {  tiroDadiFatto = true; 
					}
				}
				else {  //Se il giocatore si trova in prigione
					if (dadi.isDouble()) { //se fa dadi doppi viene liberato e mosso in base al risultato
						monopolyGUI.attivaUscitaConCarta(false);
						monopolyGUI.attivaUscitaConCauzione(false);
						giCorrente.liberaDaPrigione();
						monopolyGUI.stampa(giCorrente.getName() + " è uscito dalla prigione.");
						giCorrente.muovi(dadi.getTotal());
						tiroDadiFatto = true;
						arrivoCasella();
					}
					else {
						giCorrente.fallitoTentativo();
						tiroDadiFatto = true;
						monopolyGUI.stampa(giCorrente.getName() + " tentativo fallito, ne hai fatti: "+ giCorrente.getTentativi());
						
						if (giCorrente.tentativiTerminati() == true) { //se il giocatore è al terzo tentativo fallito è costretto a pagare
							monopolyGUI.stampa(giCorrente.getName() + " Paga 50 di cauzione");
							giCorrente.doTransazione(-CAUZIONE_PRIGIONE); //controllo su fondi giocatore
							giCorrente.liberaDaPrigione();
							giCorrente.muovi(dadi.getTotal());
							
							arrivoCasella();
						}
					}
				}
			} else {monopolyGUI.stampa("Sei in debito, trova i soldi o vai in bancarotta");}
		} 
		else {
			monopolyGUI.stampa("Hai già tirato! Scegli un'altra azione.");
			}
		
		aggiornaVisualizzazioneInfo();
	}
	
	public void setBancarotta(){
		// Chiedo al giocatore se è sicuro
		if(monopolyGUI.getDecisioneBancarotta()) {
			
			monopolyGUI.mostraScelteTurno();
			// Gestione proprietà del giocatore in bancarotta, vanno all'asta
			// Set prossimo giocatore			
			Player giTemp = giCorrente;
			if(players.indexOf(giCorrente) + 1<getPlayers().size()) {
				giCorrente = players.get(players.indexOf(giCorrente) + 1);
			}
			else {
				giCorrente = players.get(0);
			}
			
			players.remove(giTemp);
			
			listaPropBancarotta = giTemp.getListaProprieta();
			conta = 0;
			if(!listaPropBancarotta.isEmpty()){
				astaBancarotta(); }
			else {
				attivitaPostBancarotta();
			}
		}
	}
	
	public void attivitaPostBancarotta() {
		inizioTurno();
		aggiornaVisualizzazioneInfo(); 
	}
	
	public boolean getConta() {
		if(conta < listaPropBancarotta.size()) {
			return true;
		}
		else {
			monopolyGUI.stampa("Le prorpietà all'asta sono finite");
			attivitaPostBancarotta();
			return false;
		}
	}
	
	public void astaBancarotta() {
		
		asta = new Asta(giCorrente, players, listaPropBancarotta.get(conta), monopolyGUI);
		asta.inizio(); 
		conta++;
	}
	
	public void uscitaGratis() {
		if (giCorrente.getInPrigione()) {
			if (giCorrente.haUscitaGratis()) {
				giCorrente.getCarta();
				giCorrente.liberaDaPrigione();
			} else {monopolyGUI.stampa("Non hai carte uscite gratis di prigione!");} // qua non entra mai dato che disabilitiamo il bottone
		}
	}

	public void pagaUscitaPrigione() {
		if (giCorrente.getInPrigione()) { // inutile perché disattiviamo i bottoni quando non è in prigione
			if (giCorrente.getWallet() >= CAUZIONE_PRIGIONE) {
				giCorrente.doTransazione(-CAUZIONE_PRIGIONE);
				giCorrente.liberaDaPrigione();
				monopolyGUI.stampa("Il giocatore " + giCorrente.getName() + " ha pagato 50€ ed è uscito dalla prigione.");
			} else {
				monopolyGUI.stampa("Non hai abbastanza soldi!");
			}
		} else {
			monopolyGUI.stampa("Il giocatore non è in prigione");
		}
		aggiornaVisualizzazioneInfo();
	}
	
	public void setFineTurno() { //il metodo viene chiamato quando il giocatore preme il bottone per finire il turno
		if (tiroDadiFatto) //non si puo finire il turno se non si finiscono i tiri disponibili
			if(giCorrente.getWallet()<0) {
				monopolyGUI.stampa(giCorrente.getName()+ " devi risolvere i debiti se possibile");
			}else {
				setProssimoGiocatore();
			}
		else{
			monopolyGUI.stampa(giCorrente.getName()+ " devi ancora tirare");
		}
	}
	
	public void setSalvaPartita(String nome) {
		nomePartita = nome;
		indexCorrente = getPlayers().indexOf(getGiCorrente());
	}
	
	public void setTempo() {
		LocalDateTime salvataggioData = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		setSalvataggioDateTime(salvataggioData.format(formatter));
	}
	
	public void setSalvataggioDateTime(String salvataggioDateTime) {
		this.salvataggioDateTime = salvataggioDateTime;
	}

	public String getSalvataggioDateTime() {
		return salvataggioDateTime;
	}

	public void controlloPassaggioVia() {
		
		if (giCorrente.passaggioVia()) {
			giCorrente.doTransazione(MONEY_VIA);
			monopolyGUI.stampa("Il giocatore: " + giCorrente.getName() + " è passato dal via, riceve 200€." );
		}
	}
	
	public void arrivoCasella() {
		Casella casella = tabellone.getCasella(giCorrente.getLocation());

		if (casella instanceof Imprevisti) {
			if(( mazzoImprevisti).isEmpty()){
				monopolyGUI.stampa("Carte Imprevisti esaurite, rimescolo" );
				mazzoImprevisti = new MazzoImprevisti();
			}
			Carta carta = mazzoImprevisti.get();
			monopolyGUI.stampa("Sei atterrato su un imprevisto");
			monopolyGUI.stampa(carta.getMessaggio());
			azioneCarta(carta);
		} else if (casella instanceof Probabilita) {
			if(mazzoProbabilita.isEmpty() ){
				monopolyGUI.stampa("Carte Probabilità esaurite, rimescolo" );
				mazzoProbabilita = new MazzoProbabilita();
			}
			Carta carta = mazzoProbabilita.get();
			monopolyGUI.stampa("Sei atterrato su una probabilità");
			monopolyGUI.stampa(carta.getMessaggio());
			azioneCarta(carta);

		} else if (casella instanceof Tassa) {
			int totale = ((Tassa) casella).getTotale();
			giCorrente.doTransazione(-totale);
			monopolyGUI.stampa("Paga la tassa patrimoniale: " + ((Tassa) casella).getTotale() + "€. Saldo: " + giCorrente.getWallet() + "€.");
		} 

		else if (casella instanceof Proprieta) {
			monopolyGUI.stampa(giCorrente.getName() + " è atterrato sulla proprietà " + 
					tabellone.getCasella(giCorrente.getLocation()).getNome());

			if (((Proprieta) casella).posseduta() && !((Proprieta) casella).getPossessore().equals(giCorrente)){ //Proprietà di un altro giocatore
				
				if(((Proprieta) casella).isIpotecata()) {
					monopolyGUI.stampa("Non paghi l'affitto perché è ipotecata ");
				}else {
					int totale = ((Proprieta) casella).getAffitto(); 
					Player possessore = ((Proprieta) casella).getPossessore();
					monopolyGUI.stampa("Dai " + totale + "€ a " + possessore.getName() );

					giCorrente.doTransazione(-totale);
					possessore.doTransazione(totale);
				}

			} 
			else if(((Proprieta) casella).posseduta() && ((Proprieta) casella).getPossessore().equals(giCorrente)){ //Proprietà del giocatore

				monopolyGUI.stampa("Il giocatore " + giCorrente.getName() + " è atterrato sulla sua proprietà" );
				return;
			}
			else if(((Proprieta) casella).posseduta() == false) { //Proprietà non posseduta
				monopolyGUI.stampa(tabellone.getCasella(giCorrente.getLocation()).getNome() + " non ha nessun proprietario" );
				if(giCorrente.getWallet()<((Proprieta) casella).getCosto()) { //se il giocatore non ha i fondi sufficenti va direttamente all'asta
					iniziaAsta();
				}else {
					monopolyGUI.atterraggioSuProprietaVuota(); //decide il giocatore se comprarla direttamente o metterla all'asta
				}
			}
		}
		else if (casella instanceof VaiInPrigione) {

			int arrivo, partenza;
			partenza = giCorrente.getLocation();
			giCorrente.vaiInPrigione();	
			monopolyGUI.stampa(giCorrente.getName() + " va in prigione.");
			arrivo = giCorrente.getLocation();
			tiroDadiFatto = true;
			monopolyGUI.muoviPedina(partenza, arrivo, players.indexOf(giCorrente));

		} 
		aggiornaVisualizzazioneInfo();
	}

	private void azioneCarta(Carta carta) {
		int partenza, pos;

		switch (carta.getAzione()) {
		case Mazzo.AZIONE_VAI_AVANTI:
			int diff;
			int destinazione=carta.getDestinazione();
			partenza=giCorrente.getLocation();
			if(giCorrente.getLocation()<destinazione) {
				diff=destinazione-giCorrente.getLocation();
				giCorrente.muovi(diff);
			}
			else { //nel caso il giocatore sia oltre alla destinazione
				diff=40-giCorrente.getLocation();
				diff=diff+destinazione;
				giCorrente.muovi(diff);
				controlloPassaggioVia();
			}
			arrivoCasella();
			break;

		case Mazzo.AZIONE_VAI_INDIETRO:
			partenza=giCorrente.getLocation();
			giCorrente.muovi(carta.getDestinazione());
			pos=giCorrente.getLocation();
			monopolyGUI.muoviPedina(partenza, pos, players.indexOf(giCorrente));
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
			giCorrente.doTransazione(-totale);
			break;
		case Mazzo.AZIONE_PAGA:
			giCorrente.doTransazione(-carta.getQtaSoldi());
			break;
		case Mazzo.AZIONE_RICEVI: 
			giCorrente.doTransazione(carta.getQtaSoldi());
			break;
		case Mazzo.REGALI:  

			if(carta.getTipo()==0) {
				for (Player p : players) {
					if(!p.getName().equals(giCorrente.getName())) {
						if(p.controlloFondi(carta.getQtaSoldi())){
							p.doTransazione(carta.getQtaSoldi());
							monopolyGUI.stampa(giCorrente.getName() + " ha regalato 50€ a " + p.getName() + ".");
						}
					}
				}
				giCorrente.doTransazione(-carta.getQtaSoldi()*(players.size()-1));
			}else {
				for (Player p : players) {
					if(!p.getName().equals(giCorrente.getName()) ) {
						if(p.controlloFondi(-carta.getQtaSoldi())){
							p.doTransazione(-carta.getQtaSoldi());
							monopolyGUI.stampa(p.getName() + " ha regalato 10€ a " + giCorrente.getName() + ".");
						}
					}
				}
				giCorrente.doTransazione(carta.getQtaSoldi()*(players.size()-1));
			}

			break;

		case Mazzo.AZIONE_SOCIETA_VICINA: //avviene solo sugli imprevisti e quindi le caselle di destinazione sono note
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

			monopolyGUI.muoviPedina(partenza, pos, players.indexOf(giCorrente) );
			controlloPassaggioVia();

			if(((Proprieta) tabellone.getCasella(pos)).posseduta()) {
				if(!((Proprieta) tabellone.getCasella(pos)).getPossessore().getName().equals(giCorrente.getName())){
					dadi.roll();
					monopolyGUI.stampa("Dado 1: " + dadi.getDado1()); //lancio dei dadi per calcolare il coeffx10
					monopolyGUI.stampa("Dado 2: " + dadi.getDado2());
					monopolyGUI.stampa("Devi pagare "+ dadi.getTotal()+"0 volte il totale dovuto");
					giCorrente.doTransazione(((Proprieta) tabellone.getCasella(pos)).getAffitto()*dadi.getTotal()*-10);
					((Proprieta) tabellone.getCasella(pos)).getPossessore()
					.doTransazione(((Proprieta) tabellone.getCasella(pos)).getAffitto()*dadi.getTotal()*10);
				}
			}else {
				monopolyGUI.atterraggioSuProprietaVuota();
			}
			break;

		case Mazzo.AZIONE_FERROVIA_VICINA: //avviene solo sugli imprevisti e quindi le caselle di destinazione sono note
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
			monopolyGUI.muoviPedina(partenza, pos, players.indexOf(giCorrente) );
			controlloPassaggioVia();

			if(((Proprieta) tabellone.getCasella(pos)).posseduta()) {
				if(!((Proprieta) tabellone.getCasella(pos)).getPossessore().getName().equals(giCorrente.getName())){

					monopolyGUI.stampa("Devi pagare il doppio del totale dovuto");
					giCorrente.doTransazione(((Proprieta) tabellone.getCasella(pos)).getAffitto()*-2);
					((Proprieta) tabellone.getCasella(pos)).getPossessore()
					.doTransazione(((Proprieta) tabellone.getCasella(pos)).getAffitto()*2);
				}
			}else {
				monopolyGUI.atterraggioSuProprietaVuota();
			}

		}

		aggiornaVisualizzazioneInfo();
	}
	
	public void aggiornaVisualizzazioneInfo() { 
		//Aggiornamento nel pannello delle info dei giocatori (saldo)
		ArrayList<Integer> valoriSaldo = new ArrayList<>();
		for(Player p: players) 
			valoriSaldo.add(p.getWallet());
		monopolyGUI.aggiornaVisSaldoGiocatori(valoriSaldo, getGiocatoriString()); // Aggiorna la visualizzazione del saldo dei giocatori
		
		//Aggiornamento nel pannello delle info dei giocatori (elenco proprietà)
		ArrayList<ArrayList<String>> elencoProp = new ArrayList<>();
		for(Player player: players) {
			ArrayList<String> proprietaGiocatore = new ArrayList<>();
			for (Proprieta prop: player.getListaProprieta())
				if(prop.isIpotecata()) {
					proprietaGiocatore.add(prop.getNome()+ " (ipotecata)");
				}
				else {
					proprietaGiocatore.add(prop.getNome());
				}
			elencoProp.add(proprietaGiocatore);
		}
		monopolyGUI.aggiornaVisProprietaGiocatori(elencoProp, getGiocatoriString()); // Aggiorna la visualizzazione delle proprietà dei giocatori
		
		ArrayList<Integer> valoriCarte = new ArrayList<>();
		for(Player p: players) 
			valoriCarte.add(p.getNumCarte());
		monopolyGUI.aggiornaVisCarte(valoriCarte, getGiocatoriString());
		
		
	}
	
	public void iniziaAsta() {
		//passaggio dei dati utili per l'asta
		asta = new Asta(giCorrente, players, (Proprieta) tabellone.getCasella(giCorrente.getLocation()), monopolyGUI);
		asta.inizio(); 
	}

	public void compraProprieta() {
		
		if(tabellone.getCasella(giCorrente.getLocation()) instanceof Proprieta){
			Proprieta proprieta = (Proprieta) tabellone.getCasella(giCorrente.getLocation());
		
		if(giCorrente.controlloFondi(proprieta.getCosto())) {
		giCorrente.doTransazione(-proprieta.getCosto());
		
		giCorrente.aggiungiProprieta(proprieta);
		monopolyGUI.stampa(giCorrente.getName() + " ha acquistato " + tabellone.getCasella(giCorrente.getLocation()).getNome()+
				" pagando: " + proprieta.getCosto() + "€." );
		}
		else {
			monopolyGUI.stampa(giCorrente.getName() + " hai fondi insufficenti, " + tabellone.getCasella(giCorrente.getLocation()).getNome()+
					" Va all'asta" );
			iniziaAsta();
			}
		}
		 
	}
	
	public void costruisci(Proprieta prop) {
		
		if (prop.posseduta() && prop.getPossessore().equals(giCorrente)) {
			if (prop instanceof Cantiere) { //in teoria non entrera mai in questo ciclo salvo spostiamo l'if sopra
				Cantiere cant = (Cantiere) prop;
				if (giCorrente.possessoreGruppo(cant)) {
					
					
					if (!cant.isIpotecata()) {
						GruppoColore gc = cant.getGruppoColore();
						int min = Integer.MAX_VALUE;
						int max = Integer.MIN_VALUE;
						
						for (Cantiere c: gc.getMembri()) {
							if (c.isIpotecata()) {
	                            monopolyGUI.stampa("Non puoi costruire: una proprietà del gruppo è ipotecata.");
	                            return;
	                        }
							int nCost = c.getNumCostruzioni();
							min = Math.min(min, nCost);
							max = Math.max(max, nCost);
							
						}
						
						if(cant.getNumCostruzioni() < 5) {
							
							if(cant.getNumCostruzioni() == min || min==max){
								
								if(giCorrente.getWallet() >= cant.getCostoCasa()) {
									
									monopolyGUI.stampa("Costruito su "+cant.getNome());
									cant.costruisci();
									
									giCorrente.doTransazione(-cant.getCostoCasa());
									int num, id;
									num=cant.getNumCostruzioni();
									id=cant.getId();
									
									if(cant.getNumCostruzioni()<5) {
										monopolyGUI.getCase().mostraCasa((id*4)-4+num-1);
									}
									else {
										
										for(int i=0; i<4; i++ ){
											monopolyGUI.getCase().rimuoviCasa((id*4)-4+i);
										}
										monopolyGUI.getCase().mostraAlbergho(id-1);
									}
									aggiornaVisualizzazioneInfo();
									
								} 
								else {monopolyGUI.stampa("Non hai abbastanza soldi per costruire.");}
								
							} else {monopolyGUI.stampa("Non puoi costruire, la differenza tra le case non può essere maggiore di uno.");}
							
						} else {monopolyGUI.stampa("Hai già l'albergo su questa proprietà.");}
					}else {monopolyGUI.stampa("Non puoi costruire, la proprietà è ipotecata.");}
				}else {monopolyGUI.stampa("Non possiedi tutto il gruppo.");}
			}else {monopolyGUI.stampa("Non puoi costruire su questa proprietà.");}
		}else {monopolyGUI.stampa("Non puoi costruire su questa proprietà perchè non la possiedi.");}
		
	}
	
	public void demolisci(Proprieta prop) {
		
		if (prop.posseduta() && prop.getPossessore().equals(giCorrente)) {
			if (prop instanceof Cantiere) {
				Cantiere cant = (Cantiere) prop;
				GruppoColore gc = cant.getGruppoColore();
				int min = Integer.MAX_VALUE;
				int max = Integer.MIN_VALUE;
				
				for (Cantiere c: gc.getMembri()) {
					int nCost = c.getNumCostruzioni();
					min = Math.min(min, nCost);
					max = Math.max(max, nCost);
				}
				if (cant.getNumCostruzioni()>0) {
					if (cant.getNumCostruzioni() == max || max==min){
						
						int num, id;
						num=cant.getNumCostruzioni();
						id=cant.getId();
						
						cant.demolisci();
						giCorrente.doTransazione(cant.getCostoCasa()/2);
						if(cant.getNumCostruzioni()==4) {
						
							monopolyGUI.getCase().rimuoviAlbergho(id-1);
							
							for(int i=0; i<4; i++ ){
								monopolyGUI.getCase().mostraCasa((id*4)-4+i);
							}
						}
						else {
							
							monopolyGUI.getCase().rimuoviCasa((id*4)-1-(4-num));
					
						}
						aggiornaVisualizzazioneInfo();
					}else {monopolyGUI.stampa("Distruzione non valida, la differenza tra le case non può essere maggiore di uno.");}
				}else {monopolyGUI.stampa("Non hai case su questa proprietà");}
			}else {monopolyGUI.stampa("Non puoi demolire su questa proprietà.");}
		}else {monopolyGUI.stampa("Non puoi demolire se una proprietà che non possiedi");}
	}
	
	public void ipoteca(Proprieta prop) {
	
		if(prop.posseduta() && prop.getPossessore().equals(giCorrente)) {
			if ((prop instanceof Cantiere) || (prop instanceof Stazione) || (prop instanceof Societa)){
				if (prop.isIpotecata() == false) {
					if (prop instanceof Cantiere) {
						if(!((Cantiere) prop).haCase()) {
						Cantiere cant = (Cantiere) prop;
		                GruppoColore gc = cant.getGruppoColore();
		                
		                for (Cantiere c : gc.getMembri()) {
		                	if (c.haCase()) {
		                		monopolyGUI.stampa("Non puoi ipotecare se ci sono edifici sulla proprietà");
		                            return;
		                	}	
		                }
		              
					} else {
						monopolyGUI.stampa("Non puoi ipotecare se ci sono edifici sulla proprietà");
						return;
						}}
					giCorrente.doTransazione(prop.getPrezzoIpoteca());
					prop.setIpotecata(true);	
					monopolyGUI.stampa(prop.getNome()+" ipotecata ");
				}else{ monopolyGUI.stampa("La proprietà è gia ipotecata");}
			}
	}else { monopolyGUI.stampa("Non puoi ipotecare proprietà altrui o non possedute"); }
		aggiornaVisualizzazioneInfo();
	}
	
	public void disipoteca(Proprieta prop) {
		
		if (prop.posseduta() && prop.getPossessore().equals(giCorrente)) {
				if (prop.isIpotecata() && giCorrente.getWallet()>=prop.getCostoDisipoteca()) {
					prop.setIpotecata(false);
					giCorrente.doTransazione(-prop.getCostoDisipoteca());		
					 monopolyGUI.stampa(prop.getNome()+" disipotecata ");
				}
		} else { monopolyGUI.stampa("Non puoi disipotecare proprietà altrui o non possedute"); }
		aggiornaVisualizzazioneInfo();
	}
	 
	public void setProssimoGiocatore() {
		
		if(players.indexOf(giCorrente) + 1<getPlayers().size()) {
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
	
	public ArrayList<String> getListaGiocatoriScambi(){
		ArrayList<Player> acquirenti = new ArrayList<>(getPlayers());
		acquirenti.remove(giCorrente);
		ArrayList<String> acquirentiString = new ArrayList<>();
		for(Player p: acquirenti)
			acquirentiString.add(p.getName());
		return acquirentiString;
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public Player getCorrispondenzaPlayer(String nome) {
		for(Player p: getPlayers()){
			if(p.getName().equals(nome)){
				return p;
			}
		}
		return null;
	}
	
	public Proprieta getCorrispondenzaProprieta(String nomeProp, Player nomeGioc) {
		for(Proprieta p: nomeGioc.getListaProprieta())
			if(p.getNome().equals(nomeProp))
				return p;				
		return null;
	}
	
	public ArrayList<String> getGiocatoriString(){
		ArrayList<String> stringheNomiGiocatori = new ArrayList<>();
		for(Player p: players) {
			
			stringheNomiGiocatori.add(p.getName());}
		return stringheNomiGiocatori;
	}
	
	public int getNumGiocatori() {
		return players.size();
	}
	
	public void setPedineSelezionate() {
		pedineSelezionate=monopolyGUI.getPedine();
	}
	public Tabellone getTabellone() {
		return tabellone;
	}
	
	public void caricamento(MonopolyGUI monopolyGUI, List<int[]> coppie) {
		
		giCorrente = players.get(indexCorrente); 
		dadi = new Dadi();
    	tabellone = new Tabellone(dadi);

    	for(Player p: players) {
    		
    		ArrayList<Proprieta> daRimuovere=new ArrayList<>();
    		ArrayList<Proprieta> daAggiungere=new ArrayList<>();
    		monopolyGUI.muoviPedina(0, p.getLocation(), players.indexOf(p));
    		
    		for(Casella c: p.getListaProprieta()) {
    			
    			for(int i=0; i<40; i++) {
    				
    				Casella casella=tabellone.getCasella(i);
    				
    				if(casella.getNome().equals(c.getNome())) { //passa salvataggi genereli proprietà
    					
    					
    					if(((Proprieta)c).isIpotecata()) {
    						((Proprieta)casella).setIpotecata(true);
    					}
    					if(((Proprieta)c).posseduta()) {
    						((Proprieta)casella).setProprietario(p);
    					}
    					
    					if(casella instanceof Cantiere) {//passa salvataggi cantieri
    						
    						for(int [] coppia: coppie) {
    							if(coppia[0]==((Cantiere) casella).getId())
    							{
    								 if (coppia[1] > 0) {
    									 ((Cantiere) casella).setNumCostruzioni(coppia[1]);
    	    						        if (((Cantiere) casella).getNumAlberghi() == 1) {
    	    						            monopolyGUI.getCase().mostraAlbergho(((Cantiere) casella).getId() - 1);
    	    						        } else if (((Cantiere) casella).getNumCase() > 0) {
    	    						            for (int z = 1; z <= ((Cantiere) casella).getNumCase(); z++) {
    	    						                monopolyGUI.getCase().mostraCasa((((Cantiere) casella).getId() * 4) - 4 + z - 1);
    	    						                
    	    						            }
    	    						        }
    	    						    }
    							}
    						}
    					}
    					if(c instanceof Proprieta && casella instanceof Proprieta){
        					
        					daRimuovere.add((Proprieta) c);
        					daAggiungere.add((Proprieta) casella);
        				}
    				}
    			}
    		}
    		for(Proprieta a: daRimuovere) {
    			
    			p.rimuoviProprieta(a);
    		}for(Proprieta a: daAggiungere) {
    			
    			p.aggiungiProprieta(a);
    		}

    	}
    	
    	aggiornaVisualizzazioneInfo();
    	inizioTurno();
	}
	
	public String[] getPedineSelezionate(){
		return pedineSelezionate;
	}

	public Dadi getDadi() {
		return dadi;
	}
}
  