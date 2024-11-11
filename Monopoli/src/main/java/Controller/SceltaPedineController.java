package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.MonopolyGUI;
import View.SceltaPedine;
import View.SchermataDiGioco;

public class SceltaPedineController {
	
	private SceltaPedine sceltaPedineView;
	private SchermataDiGioco frame;
	
	private static int numGiocatori;
	private int indice;
	public static String[] pedineScelte;
	
	public SceltaPedineController(int giocatori, SchermataDiGioco frame) {
		// TODO Auto-generated constructor stub
		indice=0;
		this.frame=frame;
		SceltaPedineController.numGiocatori=giocatori;
		pedineScelte = new String[numGiocatori];
		sceltaPedineView= new SceltaPedine(numGiocatori);
		
		frame.add(sceltaPedineView);
		frame.revalidate();
        frame.repaint();
        
        sceltaPedineView.addBtnCane(new BtnCane());
        sceltaPedineView.addBtnCappello(new BtnCappello());
        sceltaPedineView.addBtnCariola(new BtnCariola());
        sceltaPedineView.addBtnDitale(new BtnDitale());
        sceltaPedineView.addBtnFerro(new BtnFerro());
        sceltaPedineView.addBtnMacchina(new BtnMacchina());
        sceltaPedineView.addBtnNave(new BtnNave());
        sceltaPedineView.addBtnStivale(new BtnStivale());
        
	}
	
	private class BtnCane implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
    		pedineScelte[indice] = "Cane";
    		sceltaPedineView.btnCane.setVisible(false);
    		aggiornaTurno();
		}
    }
	private class BtnCappello implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
    		pedineScelte[indice] = "Cappello";
    		sceltaPedineView.btnCappello.setVisible(false);
    		aggiornaTurno();
		}
	}
	private class BtnCariola implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
    		pedineScelte[indice] = "Cariola";
    		sceltaPedineView.btnCariola.setVisible(false);
    		aggiornaTurno();
		}
	}
	private class BtnDitale implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
    		pedineScelte[indice] = "Ditale";
    		sceltaPedineView.btnDitale.setVisible(false);
    		aggiornaTurno();
		}
	}
	private class BtnFerro implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
    		pedineScelte[indice] = "Ferro";
    		sceltaPedineView.btnFerro.setVisible(false);
    		aggiornaTurno();
		}
	}
	private class BtnMacchina implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
    		pedineScelte[indice] = "Macchina";
    		sceltaPedineView.btnMacchina.setVisible(false);
    		aggiornaTurno();
		}
	}
	private class BtnNave implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
    		pedineScelte[indice] = "Nave";
    		sceltaPedineView.btnNave.setVisible(false);
    		aggiornaTurno();
		}
	}
	private class BtnStivale implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
    		pedineScelte[indice] = "Stivale";
    		sceltaPedineView.btnStivale.setVisible(false);
    		aggiornaTurno();
		}
	}

	 // Funzione per aggiornare il turno di scelta
    public void aggiornaTurno() {

    	indice++;  // Passa al giocatore successivo
        if (indice < numGiocatori) {
            // Aggiorna la label per il turno del prossimo giocatore
        	sceltaPedineView.aggiornaTurno(indice);
        	
        } 
        else {
        	sceltaPedineView.setVisible(false);
        	new MonopolyController(frame);
        }
    }
    
    public static String[] getPedineScelte() {
    	return pedineScelte;
    }
    
    public static int getNumGiocatori() {
    	return numGiocatori;
    }
}
