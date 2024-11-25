package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.SchermataDiGioco;
import View.SchermataVincitoreView;

public class SchermataVincitoreController {

	// Per fine partita
	private static SchermataVincitoreView schermataVincitore;
	private static SchermataDiGioco frame;


	public SchermataVincitoreController (String vincitore, SchermataDiGioco frame) {

		SchermataVincitoreController.frame = frame;
		schermataVincitore = new SchermataVincitoreView(vincitore, frame);
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
			frame.remove(schermataVincitore);
			frame.revalidate();
			frame.repaint();
			new MenuController(frame);
		}
	}
}
