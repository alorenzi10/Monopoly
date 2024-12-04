package Model;

import java.util.ArrayList;
import View.MonopolyGUI;
/**
 * La classe Asta rappresenta un'asta che si svolge nel gioco del Monopoly.
 * Gestisce il processo dell'asta, inclusi l'aggiornamento delle offerte, 
 * il cambio dei turni dei giocatori e la determinazione del vincitore dell'asta.
 */
public class Asta {

	private int offerta_corrente;
	private String nome;
	private Proprieta proprieta;
	private ArrayList<Player> partecipanti;
	private Player giCorrente;
	private MonopolyGUI monopolyGUI;
	 /**
     * Costruttore della classe Asta.
     * 
     * @param giCorrente Il giocatore il cui turno è quello di fare l'offerta all'inizio dell'asta.
     * @param players Lista di tutti i giocatori che partecipano all'asta.
     * @param proprieta La proprietà che viene messa all'asta.
     * @param print Riferimento all'interfaccia grafica di Monopoly per aggiornare l'interfaccia del gioco.
     */
	public Asta(Player giCorrente, ArrayList<Player> players, Proprieta proprieta, MonopolyGUI print) {
		this.giCorrente = giCorrente;
		this.partecipanti = new ArrayList<>();
		for(Player p: players) { //crea l'arraylist partecipanti
			this.partecipanti.add(p);
		}

		this.proprieta = proprieta; // La proprietà che è all'asta
		monopolyGUI = print; // Riferimento all'interfaccia di gioco
		
		offerta_corrente = 10; // L'offerta base parte da 10 secondo le regole del monopoly
		nome = giCorrente.getName();

	}
	 /**
     * Inizia l'asta mostrando il nome del giocatore corrente e la proprietà all'asta.
     */
	public void inizio() {
		monopolyGUI.asta(giCorrente.getName(), proprieta.getNome());
	}
	 /**
     * Aggiorna l'offerta corrente durante l'asta.
     * 
     * @param i L'importo che viene aggiunto all'offerta corrente.
     * @return true se l'offerta è valida (cioè il giocatore ha fondi sufficienti), altrimenti false.
     */
	public boolean aggiornaOfferta(int i) {

		boolean risultato = false;
		risultato = giCorrente.controlloFondi(offerta_corrente + i);	

		if(risultato) {
			offerta_corrente += i; // Aggiorna l'offerta se il giocatore ha fondi sufficienti
		}
		return risultato;
	}
	/**
     * Controlla se il giocatore corrente ha abbastanza fondi per coprire l'offerta attuale.
     * @return true se il giocatore ha fondi sufficienti, altrimenti false.
     */
	public boolean controlloOfferta() {

		boolean risultato = false;
		risultato = giCorrente.controlloFondi(offerta_corrente);	
		return risultato;
	}

	public void prossimoGiocatore() {

		if(partecipanti.indexOf(giCorrente) + 1 <partecipanti.size()) {
			giCorrente = partecipanti.get(partecipanti.indexOf(giCorrente) + 1);
		}
		else {
			giCorrente = partecipanti.get(0);
		}

	}
	/**
     * Rimuove il giocatore corrente dalla lista dei partecipanti all'asta.
     */
	public void ritirati() { // Rimuove il giocatore dai partecipanti all'asta
		Player rimozione = giCorrente;
		if(partecipanti.indexOf(giCorrente) == partecipanti.size() -1) {
			// Se è l'ultimo della lista allora tocca al primo
			giCorrente = partecipanti.get(0);
		}
		else {
			// Se non è l'ultimo prende il successivo (+1)
			giCorrente = partecipanti.get(partecipanti.indexOf(giCorrente) +1);
		}
		partecipanti.remove(rimozione);
	}
	 /**
     * Controlla se l'asta è terminata (quando rimane un solo partecipante).
     * @return true se l'asta è finita, altrimenti false.
     */
	public boolean controlloFineAsta() {
		if(partecipanti.size() == 1) {
			return true;
		}
			return false;
	}
	 /**
     * Conclude l'asta, aggiornando il bilancio del giocatore vincitore e assegnando la proprietà.
     * Viene visualizzato il messaggio con il vincitore e l'importo pagato.
     */
	public void fineAsta() { // Visualizzazione vincitore asta

		giCorrente.doTransazione(-offerta_corrente);
		giCorrente.aggiungiProprieta(proprieta);
		monopolyGUI.stampa(giCorrente.getName() + " ha acquistato " + proprieta.getNome()+
				" pagando: " + offerta_corrente + "€." );
		monopolyGUI.stampa(nome + " tocca ancora a te" );

	}
	
	public String getName() {
		return giCorrente.getName();
	}

	public int getOfferta() {
		return offerta_corrente;
	}
}
