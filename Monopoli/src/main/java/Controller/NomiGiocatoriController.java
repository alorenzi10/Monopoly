package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import View.NomiGiocatori;
import View.SchermataDiGioco;

public class NomiGiocatoriController {
	
	private NomiGiocatori nomiGiocatoriView;
	private SchermataDiGioco frame;
	private int numGiocatori;
	private static String[] nomiGiocatori;
	
	public NomiGiocatoriController(int giocatori, SchermataDiGioco frame) {
		
		this.frame = frame;
		this.numGiocatori = giocatori;
		nomiGiocatoriView = new NomiGiocatori(giocatori);
		
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
	
	private class BtnConferma implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (controlloNomeGiocatori()) {
				JTextField[] playerNames=nomiGiocatoriView.getCampoNomi();
				nomiGiocatori= new String[numGiocatori];
        		// Memorizza i nomi dei giocatori
                for (int i = 0; i < numGiocatori; i++) {
                    nomiGiocatori[i] = playerNames[i].getText();
                }
                nomiGiocatoriView.setVisible(false);
                new SceltaPedineController(numGiocatori, frame);
            } else {
                nomiGiocatoriView.completaInserimento();
            }
		}
    }
	
	private boolean controlloNomeGiocatori() {

		JTextField[] playerNames=nomiGiocatoriView.getCampoNomi();
		
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
