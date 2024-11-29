package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.MenuInizialeView;
import View.NomiGiocatoriView;
import View.NuovaPartitaView;
import View.SchermataDiGioco;

/**
 * Controller per gestire la logica della schermata di selezione del numero di giocatori
 * e l'interazione con l'utente. L'utente può scegliere tra 2, 3, 4, 5 o 6 giocatori,
 * tornare al menu iniziale o uscire dal gioco.
 */
	
public class NuovaPartitaController {
	 // Istanza singleton di NuovaPartitaController
	private static NuovaPartitaController nuovaPartitaController;
	 /**
     * Costruttore privato per implementare il pattern Singleton.
     * Configura la vista e associa i listener ai bottoni delle pedine.
     */
	private NuovaPartitaController() {
		 // Aggiunge la vista della nuova partita alla schermata di gioco
        SchermataDiGioco.getSchermataDiGioco().add(NuovaPartitaView.getNuovaPartitaView());

        // Configura il pulsante "Indietro" per tornare al menu iniziale
        NuovaPartitaView.getNuovaPartitaView().getBtnIndietro().addActionListener(e -> tornaMenuIniziale());

        // Aggiunge i listener per i pulsanti di selezione del numero di giocatori
        NuovaPartitaView.getNuovaPartitaView().addBtn2(new Btn2());
        NuovaPartitaView.getNuovaPartitaView().addBtn3(new Btn3());
        NuovaPartitaView.getNuovaPartitaView().addBtn4(new Btn4());
        NuovaPartitaView.getNuovaPartitaView().addBtn5(new Btn5());
        NuovaPartitaView.getNuovaPartitaView().addBtn6(new Btn6());

        // Configura il pulsante "Esci" per chiudere l'applicazione
        NuovaPartitaView.getNuovaPartitaView().addBtnEsci(new BtnEsci());

        // Aggiorna la schermata di gioco
        SchermataDiGioco.getSchermataDiGioco().revalidate();
        SchermataDiGioco.getSchermataDiGioco().repaint();
	}
	
	 /**
     * Metodo per ottenere l'istanza singleton del controller.
     * Se non esiste, viene creata una nuova istanza.
     * @return istanza di NuovaPartitaController
     */
	public synchronized static NuovaPartitaController getNuovaPartitaController() {
		if(nuovaPartitaController==null) {
			nuovaPartitaController=new NuovaPartitaController();
		}
		// Rende visibile la vista di selezione per la nuova partita
		NuovaPartitaView.getNuovaPartitaView().setVisible(true);
		return nuovaPartitaController;
	}
	
	// Metodo per tornare al menu Iniziale dopo aver premuto il tasto indietro
	public void tornaMenuIniziale() {
		NuovaPartitaView.getNuovaPartitaView().setVisible(false);
		MenuInizialeView.getMenuInizialeView().setVisible(true);
	}
	// Listener per il pulsante "Esci", chiude l'applicazione
	private class BtnEsci implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	/////// Listener che in base alla scelta dell'utente, costruiscono l'interfaccia adatta per la scelta dei nomi dei giocatori
	// Listener per la scelta di 2 giocatori
	private class Btn2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			NuovaPartitaView.getNuovaPartitaView().setVisible(false);
			NomiGiocatoriController.getNomiGiocatoriController().setNumGiocatori(2);
			NomiGiocatoriView.getNomiGiocatoriView().setVisible(true);
		}
	}
	// Listener per la scelta di 3 giocatori
	private class Btn3 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			NuovaPartitaView.getNuovaPartitaView().setVisible(false);
			NomiGiocatoriController.getNomiGiocatoriController().setNumGiocatori(3);
			NomiGiocatoriView.getNomiGiocatoriView().setVisible(true);

		}
	}
	// Listener per la scelta di 4 giocatori
	private class Btn4 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			NuovaPartitaView.getNuovaPartitaView().setVisible(false);
			NomiGiocatoriController.getNomiGiocatoriController().setNumGiocatori(4);
			NomiGiocatoriView.getNomiGiocatoriView().setVisible(true);
		}
	}
	// Listener per la scelta di 5 giocatori
	private class Btn5 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			NuovaPartitaView.getNuovaPartitaView().setVisible(false);
			NomiGiocatoriController.getNomiGiocatoriController().setNumGiocatori(5);
			NomiGiocatoriView.getNomiGiocatoriView().setVisible(true);
		}
	}
	// Listener per la scelta di 6 giocatori
	private class Btn6 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			NuovaPartitaView.getNuovaPartitaView().setVisible(false);
			NomiGiocatoriController.getNomiGiocatoriController().setNumGiocatori(6);
			NomiGiocatoriView.getNomiGiocatoriView().setVisible(true);
		}
	}


}
