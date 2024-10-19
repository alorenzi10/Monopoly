package Model;

public class Menu {
	
	public Monopoly gioco;
	public boolean statoGioco;
	public int scelta;
	public int numero_giocatori;
	public String[] nomi = new String [6];
	
	//scelta nuova partita dal menu con passaggio del numero e dei nomi dati dal controller
	public void nuovaPartita(int numero_giocatori, String[] nomi) {
		this.numero_giocatori = numero_giocatori;
		this.nomi = nomi;
		gioco = new Monopoly(numero_giocatori, nomi);
		gioco.gioca();

	}
	
	public void caricaPartita(String id_partita) {
		
	}
}

