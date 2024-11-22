package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.GestioneDb2;
import View.CaricaPartitaView;
import View.NuovaPartitaView;
import View.SchermataDiGioco;
public class CaricaPartitaController {
	private SchermataDiGioco frame;
	private static CaricaPartitaView caricaPartita;
	
	public CaricaPartitaController(CaricaPartitaView caricaPartita, SchermataDiGioco frame) {
		CaricaPartitaController.caricaPartita = caricaPartita;
		this.frame = frame;
		
		frame.add(caricaPartita);
		frame.revalidate();
        frame.repaint();
        
        CaricaPartitaController.caricaPartita.getBtnIndietro().addActionListener(e->tornaMenuIniziale());
        caricaPartita.addBtnCarica(new BtnCarica());
        caricaPartita.addBtnElimina(new BtnElimina());
        
	}
	
	public void tornaMenuIniziale() {
		caricaPartita.setVisible(false);
		MenuController.getMenuIniziale().setVisible(true);
		
	}
	
	private class BtnCarica implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String id=caricaPartita.getCarica();
			
		}
	}
	private class BtnElimina implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String testo=caricaPartita.getElimina();
			try {
			int id=Integer.parseInt(testo);
			GestioneDb2.deleteRow(id);
			caricaPartita.aggiorna();
			}
			catch(NumberFormatException e1) {
				caricaPartita.Avviso("Inserisci l'id con numeri");
			}
			
	}
	}
}