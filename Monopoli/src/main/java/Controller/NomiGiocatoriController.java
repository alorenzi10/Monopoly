package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import View.NomiGiocatoriView;
import View.NuovaPartitaView;
import View.SceltaPedineView;
import View.SchermataDiGioco;

/**
 * Controller responsabile della gestione dei nomi dei giocatori.
 * Questa classe coordina l'inserimento dei nomi, il controllo degli input
 * e il passaggio alla fase successiva di scelta delle pedine.
 */
public class NomiGiocatoriController {

	private static NomiGiocatoriController nomiGiocatoriController;
	
	private int numGiocatori; // Valore passato dal bottone scelto
	private String[] nomiGiocatori; // Array per memorizzare i nomi dei giocatori
	private JTextField[] playerNames;  // Campi di testo per l'inserimento dei nomi

    /**
     * Costruttore privato per implementare il pattern Singleton.
     * Configura la vista e associa i listener ai pulsanti.
     */
	private NomiGiocatoriController() {
		// Aggiunge la vista NomiGiocatoriView alla schermata principale
		SchermataDiGioco.getSchermataDiGioco().add(NomiGiocatoriView.getNomiGiocatoriView());
		SchermataDiGioco.getSchermataDiGioco().revalidate();
		SchermataDiGioco.getSchermataDiGioco().repaint();
		// Configura i pulsanti "Indietro" e "Conferma"
		NomiGiocatoriView.getNomiGiocatoriView().addBtnIndietro(new BtnIndietro());
		NomiGiocatoriView.getNomiGiocatoriView().addBtnConferma(new BtnConferma());

	}
	 /**
     * Metodo per ottenere l'istanza singleton del controller.
     * Se non esiste, viene creata una nuova istanza.
     * @return istanza di NomiGiocatoriController
     */
	public synchronized static NomiGiocatoriController getNomiGiocatoriController() {
		if(nomiGiocatoriController==null) {
			nomiGiocatoriController=new NomiGiocatoriController();
		}
		return nomiGiocatoriController;
	}
	
	 /**
     * Listener per il pulsante "Indietro".
     * Ritorna alla schermata di selezione del numero di giocatori.
     */
	private class BtnIndietro implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			NomiGiocatoriView.getNomiGiocatoriView().setVisible(false);
			NuovaPartitaView.getNuovaPartitaView().setVisible(true);
		}
	}

	/**
     * Listener per il pulsante "Conferma".
     * Salva i nomi dei giocatori, controlla che tutti i campi siano compilati
     * e passa alla schermata di scelta delle pedine.
     */
	private class BtnConferma implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (controlloNomeGiocatori()) {
				 // Inizializza l'array dei nomi e li memorizza
				nomiGiocatori = new String[numGiocatori];
				for (int i = 0; i < numGiocatori; i++) {
					nomiGiocatori[i] = playerNames[i].getText();
				}

				NomiGiocatoriView.getNomiGiocatoriView().setVisible(false);
				// L'inserimento dei nomi è completato, passa alla schermata di scelta delle pedine
				SceltaPedineController.getSceltaPedineController().inizializzaController();
				SceltaPedineView.getSceltaPedineView().setVisible(true);

			} 
			else { 
				// Mostra un messaggio di errore se i campi non sono completati. Bisogna finire l'inserimento dei nomi
				NomiGiocatoriView.getNomiGiocatoriView().completaInserimento();
			}
		}
	}

	 /**
     * Metodo per controllare che tutti i campi per l'inserimento dei nomi siano compilati.
     * @return true se tutti i campi sono compilati, altrimenti false.
     */
	private boolean controlloNomeGiocatori() {

		playerNames = NomiGiocatoriView.getNomiGiocatoriView().getCampoNomi();

		for (JTextField field : playerNames) {
			if (field.getText().isEmpty()) {
				return false;
			}
		}
		return true;
	}
    /**
     * Metodo per impostare il numero di giocatori.
     * @param i numero di giocatori selezionato
     */
	public void setNumGiocatori(int i) {
		numGiocatori=i;
		NomiGiocatoriView.getNomiGiocatoriView().creaJTextField(numGiocatori);
	}
    /**
     * Metodo per ottenere il numero di giocatori selezionato.
     * @return numero di giocatori
     */
	public int getNumGiocatori() {
		return numGiocatori;
	}
    /**
     * Metodo per ottenere l'array dei nomi dei giocatori.
     * @return array di stringhe con i nomi dei giocatori
     */
	public String[] getNomiGiocatori() {
		return nomiGiocatori;
	}
	/**
     * Metodo per ottenere il nome di un giocatore specifico.
     * @param i indice del giocatore
     * @return nome del giocatore
     */
	public String getNomiGiocatori(int i) {
		return nomiGiocatori[i];
	}
}
