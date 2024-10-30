package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import View.CaricaPartita;
import View.MenuIniziale;
import View.NuovaPartita;
import View.SchermataDiGioco;

public class MenuController {
	
	private static SchermataDiGioco frame;
	private static MenuIniziale menuIniziale;
	
	public MenuController(SchermataDiGioco frame) {
		
		MenuController.frame=frame;
		menuIniziale = new MenuIniziale();
		menuIniziale.setBounds(0, 0, 1920, 1080);
		frame.add(menuIniziale);  
		frame.setVisible(true);
		
		menuIniziale.addNuovaPartitaListener(new NuovaPartitaListener());
		menuIniziale.addCaricaPartitaListener(new CaricaPartitaListener()); //da risolvere
		menuIniziale.addEsciListener(new EsciListener());
	}
	
	public static void RitoroMenu() {
		frame.add(menuIniziale);
		frame.revalidate();
        frame.repaint();
	}
	
	private class NuovaPartitaListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			 creaNuovaPartita();
		}
    }
	
	private class CaricaPartitaListener implements ActionListener{
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
    	frame.remove(menuIniziale);
    	new NuovaPartitaController(new NuovaPartita(), frame);
    }
    
    public void caricaPartita() throws SQLException{
    	
    	frame.remove(menuIniziale);
    	
    	CaricaPartita caricaPartita = new CaricaPartita(); //da finire
        caricaPartita.setBounds(0, 0, 1920, 1080);
        
        frame.add(caricaPartita);
        frame.repaint();
    }

}

