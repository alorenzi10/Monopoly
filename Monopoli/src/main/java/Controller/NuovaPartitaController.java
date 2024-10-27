package Controller;

import View.MenuIniziale;
import View.NuovaPartita;
import View.SchermataDiGioco;

public class NuovaPartitaController {
	private SchermataDiGioco frame;
	private NuovaPartita nuovaPartita;
	
	public NuovaPartitaController(NuovaPartita nuovaPartita, SchermataDiGioco frame) {
		this.nuovaPartita=nuovaPartita;
		this.frame=frame;
		frame.add(nuovaPartita);
        frame.repaint();
		
	}
}
