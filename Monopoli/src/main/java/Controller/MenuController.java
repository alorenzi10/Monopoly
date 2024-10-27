package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import View.CaricaPartita;
import View.MenuIniziale;
import View.NuovaPartita;
import View.SchermataDiGioco;

public class MenuController {
	private SchermataDiGioco frame;
	private MenuIniziale menuIniziale;
	
	public MenuController() {
		frame = new SchermataDiGioco();
		menuIniziale = new MenuIniziale();
		menuIniziale.setBounds(0, 0, 1920, 1080);
		frame.getContentPane().add(menuIniziale);  
		frame.setVisible(true);
		
		menuIniziale.addNuovaPartitaListener(new NuovaPartitaListener());
		menuIniziale.addCaricaPartitaListener(new CaricaPartitaListener()); //da risolvere
		menuIniziale.addEsciListener(new EsciListener());
	}
	
	private class NuovaPartitaListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			 creaNuovaPartita();

		}
    }
	
	private class CaricaPartitaListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				caricaPartita();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
    }
	
	private class EsciListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
            System.exit(0);

		}
    }
	
    public void creaNuovaPartita() {    	
    	menuIniziale.removeAll();
    	menuIniziale.setLayout(null);
    	new NuovaPartitaController(new NuovaPartita(), frame);
    }
    
    public void caricaPartita() throws SQLException{
    	menuIniziale.removeAll();
    	menuIniziale.setLayout(null);
    	CaricaPartita caricaPartita = new CaricaPartita();
        caricaPartita.setBounds(0, 0, 1920, 1080);
        frame.add(caricaPartita);
        frame.repaint();
    }

}

