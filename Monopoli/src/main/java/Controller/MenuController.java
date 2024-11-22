package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import View.CaricaPartitaView;
import View.MenuInizialeView;
import View.NuovaPartitaView;
import View.SchermataDiGioco;
/**
 * Classe per controllare il menu iniziale.
 * L'utente deciderà tra nuova partita, carica partita ed esci
 */

public class MenuController {
	
	private static SchermataDiGioco frame; //Per gestire il JFrame
	private static MenuInizialeView menuIniziale; //JPannel inserito nel JFrame per la visualizazione del menu iniziale 
	
	public MenuController(SchermataDiGioco frame) {
		
		MenuController.frame=frame;
		menuIniziale = new MenuInizialeView();
		menuIniziale.setBounds(0, 0, 1920, 1080);
		frame.add(menuIniziale);  
		frame.setVisible(true);
		
		//Listener dei vari bottoni per la scelta dal menu
		menuIniziale.addNuovaPartitaListener(new NuovaPartitaListener());
		menuIniziale.addCaricaPartitaListener(new CaricaPartitaListener()); //da fare
		menuIniziale.addEsciListener(new EsciListener());
		
		frame.revalidate();
		frame.repaint();
	}
	
	public static MenuInizialeView getMenuIniziale() {
		return menuIniziale;
	}
	
	private class NuovaPartitaListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			 creaNuovaPartita();
		}
    }
	
	private class CaricaPartitaListener implements ActionListener{ //da fare
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				caricaPartita();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
    }
	
	private class EsciListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
            System.exit(0);
		}
    }
	
    public void creaNuovaPartita() { 
    	menuIniziale.setVisible(false);
    	new NuovaPartitaController(new NuovaPartitaView(), frame);
    }
    
    public void caricaPartita() throws SQLException{  //da fare
    	
    	menuIniziale.setVisible(false);
    	new CaricaPartitaController(new CaricaPartitaView(), frame); //da finire

    }

}

