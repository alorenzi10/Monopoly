package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.SceltaPedineView;
import View.SchermataDiGioco;

public class SceltaPedineController {

	private static  SceltaPedineController  sceltaPedineController;

	private int numGiocatori;
	private int indice;
	private String[] pedineScelte;

	private SceltaPedineController() {

		SchermataDiGioco.getSchermataDiGioco().add(SceltaPedineView.getSceltaPedineView());
		SchermataDiGioco.getSchermataDiGioco().revalidate();
		SchermataDiGioco.getSchermataDiGioco().repaint();

		SceltaPedineView.getSceltaPedineView().addBtnCane(new BtnCane());
		SceltaPedineView.getSceltaPedineView().addBtnCappello(new BtnCappello());
		SceltaPedineView.getSceltaPedineView().addBtnCariola(new BtnCariola());
		SceltaPedineView.getSceltaPedineView().addBtnDitale(new BtnDitale());
		SceltaPedineView.getSceltaPedineView().addBtnFerro(new BtnFerro());
		SceltaPedineView.getSceltaPedineView().addBtnMacchina(new BtnMacchina());
		SceltaPedineView.getSceltaPedineView().addBtnNave(new BtnNave());
		SceltaPedineView.getSceltaPedineView().addBtnStivale(new BtnStivale());

	}
	
	public synchronized static SceltaPedineController getSceltaPedineController() {
		if(sceltaPedineController ==null) {
			sceltaPedineController=new SceltaPedineController();
		}
		sceltaPedineController.indice=0;
		sceltaPedineController.numGiocatori=NomiGiocatoriController.getNomiGiocatoriController().getNumGiocatori();
		sceltaPedineController.pedineScelte=new String[sceltaPedineController.numGiocatori];
		
		SceltaPedineView.getSceltaPedineView().resetBottoni();
		return  sceltaPedineController;
		
	}

	//I bottoni delle pedine una volta scelti diventano invisibili e aggiornano il turno
	private class BtnCane implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			pedineScelte[indice] = "Cane";
			SceltaPedineView.getSceltaPedineView().btnCane.setVisible(false);
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

	// Funzione per aggiornare il turno di scelta
	public void aggiornaTurno() {

		indice++;  // Passa al giocatore successivo
		if (indice < numGiocatori) {
			// Aggiorna la label per il turno del prossimo giocatore
			SceltaPedineView.getSceltaPedineView().aggiornaTurno(indice);

		} 
		else {
			// Tutti i giocatori hanno scelto la pedina e possiamo iniziare il gioco
			SceltaPedineView.getSceltaPedineView().setVisible(false);
			//new MonopolyController(frame);
		}
	}

	public String[] getPedineScelte() {
		return pedineScelte;
	}

}
