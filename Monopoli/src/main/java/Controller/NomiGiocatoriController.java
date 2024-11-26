package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import View.MenuInizialeView;
import View.NomiGiocatoriView;
import View.NuovaPartitaView;
import View.SceltaPedineView;
import View.SchermataDiGioco;

/**
 * Classe per salvare i nomi scelti dai giocatori
 */

public class NomiGiocatoriController {

	private static NomiGiocatoriController nomiGiocatoriController;
	
	private int numGiocatori; //valore passato dal bottone scelto
	private String[] nomiGiocatori;
	private JTextField[] playerNames;

	private NomiGiocatoriController() {

		
		SchermataDiGioco.getSchermataDiGioco().add(NomiGiocatoriView.getNomiGiocatoriView());
		SchermataDiGioco.getSchermataDiGioco().revalidate();
		SchermataDiGioco.getSchermataDiGioco().repaint();

		NomiGiocatoriView.getNomiGiocatoriView().addBtnIndietro(new BtnIndietro());
		NomiGiocatoriView.getNomiGiocatoriView().addBtnConferma(new BtnConferma());

	}
	
	public synchronized static NomiGiocatoriController getNomiGiocatoriController() {
		if(nomiGiocatoriController==null) {
			nomiGiocatoriController=new NomiGiocatoriController();
		}
		return nomiGiocatoriController;
	}

	private class BtnIndietro implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			NomiGiocatoriView.getNomiGiocatoriView().setVisible(false);
			NuovaPartitaView.getNuovaPartitaView().setVisible(true);
		}
	}

	// Salviamo i nomi solo dopo aver premuto il tasto conferma
	private class BtnConferma implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (controlloNomeGiocatori()) {

				
				nomiGiocatori = new String[numGiocatori];
				// Memorizza i nomi dei giocatori
				for (int i = 0; i < numGiocatori; i++) {
					nomiGiocatori[i] = playerNames[i].getText();
				}
				
				NomiGiocatoriView.getNomiGiocatoriView().setVisible(false);
				// L'inserimento dei nomi è completato, bisogna scegliere le pedine
				SceltaPedineController.getSceltaPedineController();
				SceltaPedineView.getSceltaPedineView().setVisible(true);

			} 
			else { 
				// Bisogna finire l'inserimento dei nomi
				NomiGiocatoriView.getNomiGiocatoriView().completaInserimento();
			}
		}
	}

	// Metodo per controllare che non manchino nomi ai giocatori
	private boolean controlloNomeGiocatori() {

		playerNames = NomiGiocatoriView.getNomiGiocatoriView().getCampoNomi();

		for (JTextField field : playerNames) {
			if (field.getText().isEmpty()) {
				return false;
			}
		}
		return true;
	}
	public void setNumGiocatori(int i) {
		numGiocatori=i;
		NomiGiocatoriView.getNomiGiocatoriView().creaJTextField(numGiocatori);
	}
	
	public int getNumGiocatori() {
		return numGiocatori;
	}

	public String[] getNomiGiocatori() {
		return nomiGiocatori;
	}

	public String getNomiGiocatori(int i) {
		return nomiGiocatori[i];
	}
}
