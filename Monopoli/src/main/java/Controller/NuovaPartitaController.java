package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.MenuInizialeView;
import View.NomiGiocatoriView;
import View.NuovaPartitaView;
import View.SchermataDiGioco;
/**
 * Classe per controllare la scelta del numero di giocatori nella partita.
 * L'utente decide tra 2-3-4-5-6 giocatori
 */

public class NuovaPartitaController {
	
	private static NuovaPartitaController nuovaPartitaController;

	private NuovaPartitaController() {
		
		SchermataDiGioco.getSchermataDiGioco().add(NuovaPartitaView.getNuovaPartitaView());
		

		NuovaPartitaView.getNuovaPartitaView().getBtnIndietro().addActionListener(e->tornaMenuIniziale());

		NuovaPartitaView.getNuovaPartitaView().addBtn2(new Btn2());
		NuovaPartitaView.getNuovaPartitaView().addBtn3(new Btn3());
		NuovaPartitaView.getNuovaPartitaView().addBtn4(new Btn4());
		NuovaPartitaView.getNuovaPartitaView().addBtn5(new Btn5());
		NuovaPartitaView.getNuovaPartitaView().addBtn6(new Btn6());
		NuovaPartitaView.getNuovaPartitaView().addBtnEsci(new BtnEsci());
		
		SchermataDiGioco.getSchermataDiGioco().revalidate();
		SchermataDiGioco.getSchermataDiGioco().repaint();
	}
	
	public synchronized static NuovaPartitaController getNuovaPartitaController() {
		if(nuovaPartitaController==null) {
			nuovaPartitaController=new NuovaPartitaController();
		}
		NuovaPartitaView.getNuovaPartitaView().setVisible(true);
		return nuovaPartitaController;
	}
	
	// Metodo per tornare al menu Iniziale dopo aver premuto il tasto indietro
	public void tornaMenuIniziale() {
		NuovaPartitaView.getNuovaPartitaView().setVisible(false);
		MenuInizialeView.getMenuInizialeView().setVisible(true);
	}

	// Listener che in base alla scelta dell'utente, costruiscono l'interfaccia adatta per la scelta dei nomi dei giocatori
	private class Btn2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			NuovaPartitaView.getNuovaPartitaView().setVisible(false);
			NomiGiocatoriController.getNomiGiocatoriController().setNumGiocatori(2);
			NomiGiocatoriView.getNomiGiocatoriView().setVisible(true);
		}
	}

	private class Btn3 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			NuovaPartitaView.getNuovaPartitaView().setVisible(false);
			NomiGiocatoriController.getNomiGiocatoriController().setNumGiocatori(3);
			NomiGiocatoriView.getNomiGiocatoriView().setVisible(true);

		}
	}
	
	private class Btn4 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			NuovaPartitaView.getNuovaPartitaView().setVisible(false);
			NomiGiocatoriController.getNomiGiocatoriController().setNumGiocatori(4);
			NomiGiocatoriView.getNomiGiocatoriView().setVisible(true);
		}
	}
	
	private class Btn5 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			NuovaPartitaView.getNuovaPartitaView().setVisible(false);
			NomiGiocatoriController.getNomiGiocatoriController().setNumGiocatori(5);
			NomiGiocatoriView.getNomiGiocatoriView().setVisible(true);
		}
	}
	
	private class Btn6 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			NuovaPartitaView.getNuovaPartitaView().setVisible(false);
			NomiGiocatoriController.getNomiGiocatoriController().setNumGiocatori(6);
			NomiGiocatoriView.getNomiGiocatoriView().setVisible(true);
		}
	}
	
	private class BtnEsci implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

}
