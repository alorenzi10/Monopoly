package Model;

import java.util.ArrayList;

import View.MonopolyGUI;

public class Asta {
	
	private int offerta_corrente;
	private Proprieta proprieta;
	private ArrayList<Player> partecipanti;
	private int indice;
	private MonopolyGUI monopolyGUI;
	private Player giCorrente;
	private String nome;
	
	public Asta(int indice, ArrayList<Player> players, Proprieta proprieta,MonopolyGUI print) {
		this.indice=indice;
		this.partecipanti=new ArrayList<>();
		for(Player p: players) {
			this.partecipanti.add(p);
		}
		
		this.proprieta=proprieta;
		monopolyGUI=print;
		for(Player p: partecipanti) {
			monopolyGUI.stampa(p.getName());
		}
		offerta_corrente=10;
		giCorrente=partecipanti.get(indice);
		nome=giCorrente.getName();

	}
	
	public void inizio() {
		monopolyGUI.asta(indice);
		
	}
	
	public boolean aggiornaOfferta(int i) {
		
		boolean risultato=false;
		risultato=giCorrente.controlloFondi(offerta_corrente+i);	
		
		if(risultato) {
			offerta_corrente+=i;
			return risultato;
		}else {
			
			return risultato;
		}
	}
	
	public String getName() {
		return giCorrente.getName();
	}
	
	public void prossimoGiocatore() {
		
		if(partecipanti.indexOf(giCorrente) + 1<partecipanti.size()) {
			giCorrente = partecipanti.get(partecipanti.indexOf(giCorrente) + 1);
		}
		else {
			giCorrente= partecipanti.get(0);
		}
		
	}
	
	public void fineAsta() {
		
			giCorrente.doTransaction(-offerta_corrente);
			giCorrente.aggiungiProprieta(proprieta);
			monopolyGUI.stampa(giCorrente.getName() + " ha acquistato "+ proprieta.getNome()+
					" pagando: " + offerta_corrente + "€." );
			monopolyGUI.stampa(nome+ " tocca ancora a te" );

	}
	
	public void ritirati() {
		Player rimozione=giCorrente;
		if(partecipanti.indexOf(giCorrente)==partecipanti.size()-1) {
			giCorrente=partecipanti.get(0);
		}
		else if(partecipanti.indexOf(giCorrente)==0) {
			giCorrente=partecipanti.get(1);
		}
		else {
			giCorrente=partecipanti.get(partecipanti.indexOf(giCorrente)+1);
		}
		partecipanti.remove(rimozione);
	}
	
	public boolean ControlloFineAsta() {
		if(partecipanti.size()==1) {
			return true;
		}
		else {
			return false;
		}
	}
	public int getOfferta() {
		return offerta_corrente;
	}
	
	
	
	
	
}
