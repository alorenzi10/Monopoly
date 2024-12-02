package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.SceltaPedineView;
import View.SchermataDiGioco;
/**
 * Controller per la gestione della scelta delle pedine.
 * Coordina la selezione delle pedine da parte dei giocatori e aggiorna il flusso di gioco.
 */
public class SceltaPedineController {
	// Singleton per il controller
	private static  SceltaPedineController  sceltaPedineController;
	// Numero di giocatori, indice corrente e pedine selezionate salvate come String
	private int numGiocatori;
	private int indice;
	private String[] pedineScelte;
	 /**
     * Costruttore privato per implementare il pattern Singleton.
     * Configura la vista e associa i listener ai bottoni delle pedine.
     */
	private SceltaPedineController() {
		// Aggiunge la vista alla schermata principale
		SchermataDiGioco.getSchermataDiGioco().add(SceltaPedineView.getSceltaPedineView());
		SchermataDiGioco.getSchermataDiGioco().revalidate();
		SchermataDiGioco.getSchermataDiGioco().repaint();
		// Associa i listener ai bottoni delle pedine
		SceltaPedineView.getSceltaPedineView().addBtnCane(new BtnCane());
		SceltaPedineView.getSceltaPedineView().addBtnCappello(new BtnCappello());
		SceltaPedineView.getSceltaPedineView().addBtnCariola(new BtnCariola());
		SceltaPedineView.getSceltaPedineView().addBtnDitale(new BtnDitale());
		SceltaPedineView.getSceltaPedineView().addBtnFerro(new BtnFerro());
		SceltaPedineView.getSceltaPedineView().addBtnMacchina(new BtnMacchina());
		SceltaPedineView.getSceltaPedineView().addBtnNave(new BtnNave());
		SceltaPedineView.getSceltaPedineView().addBtnStivale(new BtnStivale());

	}
	 /**
     * Metodo per ottenere l'istanza singleton del controller.
     * @return l'istanza di SceltaPedineController
     */
	public synchronized static SceltaPedineController getSceltaPedineController() {
		if(sceltaPedineController == null) {
			sceltaPedineController = new SceltaPedineController();
		}
		SceltaPedineView.getSceltaPedineView().aggiornaNomi(NomiGiocatoriController.getNomiGiocatoriController().getNomiGiocatori());
		return  sceltaPedineController;
	}
	 /**
     * Inizializza il controller per una nuova selezione di pedine.
     * Resetta i dati e i bottoni della vista.
     */
	public void inizializzaController() {
		sceltaPedineController.indice=0;
		sceltaPedineController.numGiocatori=NomiGiocatoriController.getNomiGiocatoriController().getNumGiocatori();
		sceltaPedineController.pedineScelte=new String[sceltaPedineController.numGiocatori];
		// Resetta lo stato dei bottoni nella vista
		SceltaPedineView.getSceltaPedineView().resetBottoni();
	}

	//I bottoni delle pedine una volta scelti diventano invisibili e aggiornano il turno
	private class BtnCane implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// Assegna la pedina al giocatore corrente
			pedineScelte[indice] = "Cane";
			// Nasconde il bottone della pedina selezionata
			SceltaPedineView.getSceltaPedineView().btnCane.setVisible(false);
			// Aggiorna il turno
			aggiornaTurno();
		}
	}
	
	private class BtnCappello implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			pedineScelte[indice] = "Cappello";
			SceltaPedineView.getSceltaPedineView().btnCappello.setVisible(false);
			aggiornaTurno();
		}
	}
	
	private class BtnCariola implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			pedineScelte[indice] = "Cariola";
			SceltaPedineView.getSceltaPedineView().btnCariola.setVisible(false);
			aggiornaTurno();
		}
	}
	
	private class BtnDitale implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			pedineScelte[indice] = "Ditale";
			SceltaPedineView.getSceltaPedineView().btnDitale.setVisible(false);
			aggiornaTurno();
		}
	}
	
	private class BtnFerro implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			pedineScelte[indice] = "Ferro";
			SceltaPedineView.getSceltaPedineView().btnFerro.setVisible(false);
			aggiornaTurno();
		}
	}
	
	private class BtnMacchina implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			pedineScelte[indice] = "Macchina";
			SceltaPedineView.getSceltaPedineView().btnMacchina.setVisible(false);
			aggiornaTurno();
		}
	}
	
	private class BtnNave implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			pedineScelte[indice] = "Nave";
			SceltaPedineView.getSceltaPedineView().btnNave.setVisible(false);
			aggiornaTurno();
		}
	}
	
	private class BtnStivale implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			pedineScelte[indice] = "Stivale";
			SceltaPedineView.getSceltaPedineView().btnStivale.setVisible(false);
			aggiornaTurno();
		}
	}
	
	/**
     * Aggiorna il turno di selezione.
     * Se tutti i giocatori hanno scelto, passa al controller principale del gioco.
     */
	public void aggiornaTurno() {

		indice++;  // Passa al giocatore successivo
		if (indice < numGiocatori) {
			// Aggiorna la label per il turno del prossimo giocatore
			SceltaPedineView.getSceltaPedineView().aggiornaTurno(indice);
		} 
		else {
			// Se tutti hanno scelto, passa alla fase successiva del gioco
			SceltaPedineView.getSceltaPedineView().setVisible(false);
			new MonopolyController(); // Inizializza il gioco
		}
	}
    /**
     * Ritorna l'array delle pedine selezionate dai giocatori.
     * @return array di pedine
     */
	public String[] getPedineScelte() {
		return pedineScelte;
	}

}
