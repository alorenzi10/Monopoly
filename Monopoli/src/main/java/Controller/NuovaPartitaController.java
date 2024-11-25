package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.NuovaPartitaView;
import View.SchermataDiGioco;
/**
 * Classe per controllare la scelta del numero di giocatori nella partita.
 * L'utente decide tra 2-3-4-5-6 giocatori
 */

public class NuovaPartitaController {

	private SchermataDiGioco frame;
	private static NuovaPartitaView nuovaPartita;

	public NuovaPartitaController(NuovaPartitaView nuovaPartita, SchermataDiGioco frame) {

		NuovaPartitaController.nuovaPartita = nuovaPartita;
		this.frame = frame;

		frame.add(nuovaPartita);
		frame.revalidate();
		frame.repaint();

		NuovaPartitaController.nuovaPartita.getBtnIndietro().addActionListener(e->tornaMenuIniziale());

		nuovaPartita.addBtn2(new Btn2());
		nuovaPartita.addBtn3(new Btn3());
		nuovaPartita.addBtn4(new Btn4());
		nuovaPartita.addBtn5(new Btn5());
		nuovaPartita.addBtn6(new Btn6());
	}
	/**
	 * Metodo per tornare al menu Iniziale dopo aver premuto il tasto indietro
	 */
	public void tornaMenuIniziale() {
		nuovaPartita.setVisible(false);
		MenuController.getMenuIniziale().setVisible(true);
	}

	/**
	 * Listener che in base alla scelta dell'utente, costruiscono 
	 * l'interfaccia adatta per la scelta dei nomi dei giocatori
	 */
	private class Btn2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			nuovaPartita.setVisible(false);
			new NomiGiocatoriController(2, frame);
		}
	}

	private class Btn3 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			nuovaPartita.setVisible(false);
			new NomiGiocatoriController(3, frame);

		}
	}
	private class Btn4 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			nuovaPartita.setVisible(false);
			new NomiGiocatoriController(4, frame);

		}
	}
	private class Btn5 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			nuovaPartita.setVisible(false);
			new NomiGiocatoriController(5, frame);

		}
	}
	private class Btn6 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			nuovaPartita.setVisible(false);
			new NomiGiocatoriController(6, frame);
		}
	}

	public static NuovaPartitaView getNuovaPartita() {

		return nuovaPartita;
	}
}
