package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataVincitoreController {

	// Per fine partita
	schermataVincitore.addBtnEsci(new BtnEsciDalGioco());
	schermataVincitore.addBtnMenuPrincipale(new BtnMenuPrincipale());

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
