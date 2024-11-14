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
        monopolyGUI.addBtnConfermaBancarotta(new BtnBancarotta());
        monopolyGUI.addBtnNoBancarotta(new BtnNoBancarotta());
        
        monopolyGUI.addBtnScambi(new BtnScambi());
        monopolyGUI.addBtnProprieta(new BtnProprieta());
        monopolyGUI.addBtnFineTurno(new BtnFineTurno());
        
        monopolyGUI.addBtnAcquista(new BtnAcquista());
        monopolyGUI.addBtnAsta(new BtnAsta());
		monopolyGUI.addBtnUsaCartaEsciDiPrigione(new BtnUsaCartaEsciDiPrigione());
		monopolyGUI.addBtnPagaCauzione(new BtnPagaCauzione());
		monopolyGUI.addBtnMostraProprietaGiocatori(new BtnMostraProprieta());
		
		monopolyGUI.addBtn1(new Btn1());
		monopolyGUI.addBtn5(new Btn5());
		monopolyGUI.addBtn10(new Btn10());
		monopolyGUI.addBtnConfermaOfferta(new BtnConfermaOfferta());
		monopolyGUI.addBtnRitirati(new BtnRitirati());
		
		monopolyGUI.addBtnCostruisci(new BtnCostruisci());
		monopolyGUI.addBtnMarrone(new BtnMarrone());
		
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
			////////////temporaneo
			monopoly.stampaDati();
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

	private class BtnAcquista implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			monopolyGUI.rimuoviAcquistoAsta();
			monopoly.compraProprieta();
		}
	}
	private class BtnAsta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			monopolyGUI.rimuoviAcquistoAsta();
			monopoly.IniziaAsta();
			
		}
	}

	private class BtnBancarotta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			monopolyGUI.rimuoviAcquistoAsta();
		}
	}

	private class BtnNoBancarotta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			monopolyGUI.rimuoviAcquistoAsta();
		}
	}
	
	private class BtnCostruisci implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			monopolyGUI.scelteCostruzione();
		}
	}
	private class BtnMarrone implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			
		}
	}

	private class BtnUsaCartaEsciDiPrigione implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			monopoly.uscitaGratis();
			monopolyGUI.attivaUscitaConCarta(false);
		}
	}
	private class BtnPagaCauzione implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			monopoly.pagaUscitaPrigione();
			monopolyGUI.attivaUscitaConCauzione(false);
		}
	}

	private class BtnMostraProprieta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			//monopolyGUI.mostraProprieta();
		}
	}
	private class Btn1 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!monopoly.asta.aggiornaOfferta(1)) { //si potrebbe fare un metodo per queste azioni
				monopolyGUI.stampa("Non hai fondi a sufficenza per questa offerta");
			}
			else {
				monopolyGUI.aggiornaOfferta(monopoly.asta.getOfferta());
			}
		}
	}
	
	private class Btn5 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!monopoly.asta.aggiornaOfferta(5)) {
				monopolyGUI.stampa("Non hai fondi a sufficenza per questa offerta");
			}
			else {
				monopolyGUI.aggiornaOfferta(monopoly.asta.getOfferta());
			}
		}
	}
	
	private class Btn10 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!monopoly.asta.aggiornaOfferta(10)) {
				monopolyGUI.stampa("Non hai fondi a sufficenza per questa offerta");
			}
			else {
				monopolyGUI.aggiornaOfferta(monopoly.asta.getOfferta());
			}
		}
	}
	
	private class BtnConfermaOfferta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			monopoly.asta.prossimoGiocatore();
			monopolyGUI.aggiornaTurno(monopoly.asta.getName());
		}
	}
	
	private class BtnRitirati implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			monopoly.asta.ritirati();
			if(monopoly.asta.ControlloFineAsta()) {
				monopolyGUI.rimuoviAcquistoAsta();
				monopoly.asta.fineAsta();
			}
			else {
				monopolyGUI.aggiornaTurno(monopoly.asta.getName());
			}
		}
	}
	
	private class BtnMostraProprietaGiocatori implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			monopolyGUI.mostraInfoGiocatori(monopoly.getPlayers().size());
		}
	}
	
}
