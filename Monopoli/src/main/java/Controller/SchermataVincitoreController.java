package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.SchermataDiGioco;
import View.SchermataVincitoreView;

public class SchermataVincitoreController {

	// Per fine partita
	private static SchermataVincitoreView schermataVincitore;
	private String vincitore;
	private static SchermataDiGioco frame;
	
	
	public SchermataVincitoreController (SchermataDiGioco frame) {
		schermataVincitore = new SchermataVincitoreView(vincitore);
		SchermataVincitoreController.frame=frame;
		frame.add(schermataVincitore);
		frame.revalidate();
		frame.repaint();
		
		schermataVincitore.addBtnEsci(new BtnEsciDalGioco());
		schermataVincitore.addBtnMenuPrincipale(new BtnMenuPrincipale());
	}

	private class BtnEsciDalGioco implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	private class BtnMenuPrincipale implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			new MenuController(frame);
		}
	}

}
