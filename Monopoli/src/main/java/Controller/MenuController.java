package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.MenuInizialeView;
import View.SchermataDiGioco;
/**
 * Classe per controllare il menu iniziale.
 * L'utente deciderà tra nuova partita, carica partita ed esci
 * Implementa il pattern Singleton per garantire una sola istanza del controller.
 */

public class MenuController {
	// Istanza Singleton della classe
	private static MenuController menuController;
	/**
     * Costruttore privato (Singleton).
     * Inizializza il menu iniziale e associa i listener ai bottoni.
     */
	private MenuController() {
		// Aggiunge la vista del menu iniziale al contenitore principale
		SchermataDiGioco.getSchermataDiGioco().add(MenuInizialeView.getMenuInizialeView() );  
		
		// Associa i listener ai bottoni del menu
		MenuInizialeView.getMenuInizialeView().addNuovaPartitaListener(new NuovaPartitaListener());
		MenuInizialeView.getMenuInizialeView().addCaricaPartitaListener(new CaricaPartitaListener());
		MenuInizialeView.getMenuInizialeView().addEsciListener(new EsciListener());
		// Aggiorna il contenitore principale
		SchermataDiGioco.getSchermataDiGioco().revalidate();
		SchermataDiGioco.getSchermataDiGioco().repaint();
	}
	
	 /**
     * Metodo statico per ottenere l'istanza Singleton del controller e
     * rende visibile la vista del menu iniziale.
     * @return L'istanza di MenuController
     */
	public synchronized static MenuController getMenuIniziale() {
		if(menuController == null) {
			menuController = new MenuController();
		}
		// Rende visibile la vista del menu iniziale
		MenuInizialeView.getMenuInizialeView().setVisible(true);
		return menuController;
	}
	
	/**
     * Listener per il bottone "Nuova Partita".
     * Nasconde il menu iniziale e avvia il controller per la nuova partita.
     */
	private class NuovaPartitaListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			MenuInizialeView.getMenuInizialeView().setVisible(false);
			NuovaPartitaController.getNuovaPartitaController();
		}
	}
	
	/**
     * Listener per il bottone "Carica Partita".
     * Nasconde il menu iniziale e avvia il controller per il caricamento di una partita salvata.
     */
	private class CaricaPartitaListener implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			MenuInizialeView.getMenuInizialeView().setVisible(false);  // Nasconde il menu iniziale
			CaricaPartitaController.getCaricaPartitaController();	 // Avvia il controller del caricamento partita
		}
	}
	
	/**
     * Listener per il bottone "Esci".
     * Termina l'applicazione quando l'utente seleziona "Esci".
     */
	private class EsciListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);  // Chiude l'applicazione
		}
	}
}