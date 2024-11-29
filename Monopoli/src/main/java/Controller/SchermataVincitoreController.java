package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.SchermataDiGioco;
import View.SchermataVincitoreView;

/**
 * Controller per la gestione della schermata di fine partita.
 * Mostra il vincitore e consente di scegliere tra uscire dal gioco o tornare al menu principale.
 * Implementa il pattern Singleton per garantire che una sola istanza del controller sia attiva.
 */
public class SchermataVincitoreController {

	// Istanza Singleton del controller
	private static SchermataVincitoreController schermataVincitoreController;
	/**
     * Costruttore privato per il pattern Singleton.
     * Associa i listener ai bottoni della schermata del vincitore.
     */
	private SchermataVincitoreController () {
		// Associa i listener ai bottoni nella vista
		SchermataVincitoreView.getSchermataVincitoreView().addBtnEsci(new BtnEsciDalGioco());
		SchermataVincitoreView.getSchermataVincitoreView().addBtnMenuPrincipale(new BtnMenuPrincipale());
	}
	 /**
     * Metodo statico per ottenere l'istanza Singleton del controller.
     * Inizializza la schermata del vincitore e la rende visibile.
     * @param nomeVincitore è il nome del giocatore vincitore da visualizzare nella schermata.
     * @return L'istanza del controller.
     */
	public synchronized static SchermataVincitoreController getSchermataVincitoreController(String nomeVincitore) {
		// Crea il controller se non è già stato istanziato
		if(schermataVincitoreController == null) {
			schermataVincitoreController = new SchermataVincitoreController();
		}
	    // Aggiorna la vista con il nome del vincitore
		SchermataVincitoreView.getSchermataVincitoreView().aggiornaVincitore(nomeVincitore);
		
		// Aggiunge la vista al contenitore principale
		SchermataDiGioco.getSchermataDiGioco().add(SchermataVincitoreView.getSchermataVincitoreView());
		SchermataDiGioco.getSchermataDiGioco().revalidate();
		SchermataDiGioco.getSchermataDiGioco().repaint();
		
		// Rende visibile la schermata del vincitore
		SchermataVincitoreView.getSchermataVincitoreView().setVisible(true);
		
		return schermataVincitoreController;
	}
	/**
     * Listener per il bottone "Esci".
     * Termina l'applicazione quando viene cliccato.
     */
	private class BtnEsciDalGioco implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	 /**
     * Listener per il bottone "Menu Principale".
     * Rimuove la schermata del vincitore e torna al menu principale.
     */
	private class BtnMenuPrincipale implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// Rimuove la vista della schermata del vincitore
			SchermataDiGioco.getSchermataDiGioco().remove(SchermataVincitoreView.getSchermataVincitoreView());
			SchermataDiGioco.getSchermataDiGioco().revalidate();
			SchermataDiGioco.getSchermataDiGioco().repaint();
			
			 // Mostra il menu principale
			MenuController.getMenuIniziale();
		}
	}
}
