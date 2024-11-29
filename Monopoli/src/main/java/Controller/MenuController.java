package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.MenuInizialeView;
import View.SchermataDiGioco;
/**
 * Classe per controllare il menu iniziale.
 * L'utente deciderà tra nuova partita, carica partita ed esci
 */

public class MenuController {

	private static MenuController menuController;

	private MenuController() {

		SchermataDiGioco.getSchermataDiGioco().add(MenuInizialeView.getMenuInizialeView() );  
		
		//Listener dei vari bottoni per la scelta dal menu
		MenuInizialeView.getMenuInizialeView().addNuovaPartitaListener(new NuovaPartitaListener());
		MenuInizialeView.getMenuInizialeView().addCaricaPartitaListener(new CaricaPartitaListener());
		MenuInizialeView.getMenuInizialeView().addEsciListener(new EsciListener());

		SchermataDiGioco.getSchermataDiGioco().revalidate();
		SchermataDiGioco.getSchermataDiGioco().repaint();
	}

	public synchronized static MenuController getMenuIniziale() {
		if(menuController == null) {
			menuController = new MenuController();
		}
		MenuInizialeView.getMenuInizialeView().setVisible(true);
		return menuController;
	}

	private class NuovaPartitaListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			MenuInizialeView.getMenuInizialeView().setVisible(false);
			NuovaPartitaController.getNuovaPartitaController();
		}
	}

	private class CaricaPartitaListener implements ActionListener{ //da fare
		@Override
		public void actionPerformed(ActionEvent e) {
			MenuInizialeView.getMenuInizialeView().setVisible(false);
			CaricaPartitaController.getCaricaPartitaController();
		}
	}

	private class EsciListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}