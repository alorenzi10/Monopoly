package Model;

import java.util.ArrayList;
import View.MonopolyGUI;

public class Asta {

	private int offerta_corrente;
	private String nome;
	private Proprieta proprieta;
	private ArrayList<Player> partecipanti;
	private Player giCorrente;
	private MonopolyGUI monopolyGUI;
	
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

	public void inizio() {
		monopolyGUI.asta(giCorrente.getName(), proprieta.getNome());
	}

	public boolean aggiornaOfferta(int i) {

		boolean risultato = false;
		risultato = giCorrente.controlloFondi(offerta_corrente + i);	

		if(risultato) {
			offerta_corrente += i;
		}
		return risultato;
	}
	
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

	public boolean controlloFineAsta() {
		if(partecipanti.size() == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	public void fineAsta() { // Visualizzazione vincitore asta

		giCorrente.doTransaction(-offerta_corrente);
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
