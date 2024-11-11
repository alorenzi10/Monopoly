package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Monopoly;
import View.MonopolyGUI;
import View.SchermataDiGioco;

public class MonopolyController {
	
	private static SchermataDiGioco frame; //Per gestire il JFrame
	private static MonopolyGUI monopolyGUI;
	private Monopoly monopoly;
	
	public MonopolyController(SchermataDiGioco frame) {
		
		MonopolyController.frame = frame;
		monopolyGUI= new MonopolyGUI(frame);
		monopoly=new Monopoly(SceltaPedineController.getNumGiocatori(), NomiGiocatoriController.getNomiGiocatori(), monopolyGUI);
		monopolyGUI.setBounds(0, 0,  1920, 1080); 
		
		frame.add(monopolyGUI);
		frame.revalidate();
        frame.repaint();
        
        monopolyGUI.addBtnTiraDadi(new BtnTiraDadi());
        monopolyGUI.addBtnDichiaraBancarotta(new BtnDichiaraBancarotta());
        monopolyGUI.addBtnScambi(new BtnScambi());
        monopolyGUI.addbtnProprieta(new BtnProprieta());
        monopolyGUI.addbtnFineTurno(new BtnFineTurno());
	}
	
	private class BtnTiraDadi implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
				int arrivo, partenza;
				partenza=monopoly.getGiCorrente().getLocation();
				monopoly.tiraDadi();
				arrivo=monopoly.getGiCorrente().getLocation();
				monopolyGUI.muoviPedina(partenza, arrivo, monopoly.getGiCorrente().getId() );
			}
	}
	private class BtnDichiaraBancarotta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
				monopolyGUI.confermaBancarotta();
			}
	}
	private class BtnProprieta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
				monopolyGUI.mostraGestioneProprieta();//da rivedere
			}
	}
	private class BtnScambi implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
				monopolyGUI.mostraScambi();
			}
	}
	private class BtnFineTurno implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
				monopoly.setFineTurno();
			}
	}
}