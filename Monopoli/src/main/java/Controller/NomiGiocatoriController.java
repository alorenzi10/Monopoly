package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import View.NomiGiocatoriView;
import View.SchermataDiGioco;

/**
 * Classe per salvare i nomi scelti dai giocatori
 */

public class NomiGiocatoriController {

	private NomiGiocatoriView nomiGiocatoriView;
	private SchermataDiGioco frame;
	private int numGiocatori; //valore passato dal bottone scelto
	private static String[] nomiGiocatori;

	public NomiGiocatoriController(int giocatori, SchermataDiGioco frame) {

		this.frame = frame;
		this.numGiocatori = giocatori;
		nomiGiocatoriView = new NomiGiocatoriView(giocatori);

		frame.add(nomiGiocatoriView);
		frame.revalidate();
		frame.repaint();

		nomiGiocatoriView.addBtnIndietro(new BtnIndietro());
		nomiGiocatoriView.addBtnConferma(new BtnConferma());

	}

	private class BtnIndietro implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			nomiGiocatoriView.setVisible(false);
			NuovaPartitaController.getNuovaPartita().setVisible(true);
		}
	}

	// Salviamo i nomi solo dopo aver premuto il tasto conferma
	private class BtnConferma implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (controlloNomeGiocatori()) {

				JTextField[] playerNames=nomiGiocatoriView.getCampoNomi();
				nomiGiocatori = new String[numGiocatori];
				// Memorizza i nomi dei giocatori
				for (int i = 0; i < numGiocatori; i++) {
					nomiGiocatori[i] = playerNames[i].getText();
				}
				nomiGiocatoriView.setVisible(false);
				// L'inserimento dei nomi è completato, bisogna scegliere le pedine
				new SceltaPedineController(numGiocatori, frame); 
			} 
			else { 
				// Bisogna finire l'inserimento dei nomi
				nomiGiocatoriView.completaInserimento();
			}
		}
	}

	// Metodo per controllare che non manchino nomi ai giocatori
	private boolean controlloNomeGiocatori() {

		JTextField[] playerNames = nomiGiocatoriView.getCampoNomi();

		for (JTextField field : playerNames) {
			if (field.getText().isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public static String[] getNomiGiocatori() {
		return nomiGiocatori;
	}

	public static String getNomiGiocatori(int i) {
		return nomiGiocatori[i];
	}
}
