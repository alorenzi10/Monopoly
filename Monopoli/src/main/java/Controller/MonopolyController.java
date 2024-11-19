package Controller;

import Model.*;
import View.MonopolyGUI;
import View.SchermataDiGioco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonopolyController {

	private static SchermataDiGioco frame; //Per gestire il JFrame
	private static MonopolyGUI monopolyGUI;
	private Monopoly monopoly;
	public MonopolyController(SchermataDiGioco frame) {

		MonopolyController.frame = frame;
		monopolyGUI = new MonopolyGUI(frame);
		monopoly = new Monopoly(SceltaPedineController.getNumGiocatori(), NomiGiocatoriController.getNomiGiocatori(), monopolyGUI);
		monopolyGUI.setBounds(0, 0,  1920, 1080); 
		frame.add(monopolyGUI);
		monopolyGUI.mostraInfoGiocatori(monopoly.getGiocatoriString());
		frame.revalidate();
		frame.repaint();

		monopolyGUI.addBtnTiraDadi(new BtnTiraDadi());

		monopolyGUI.addBtnDichiaraBancarotta(new BtnDichiaraBancarotta());
		monopolyGUI.addBtnConfermaBancarotta(new BtnBancarotta());
		monopolyGUI.addBtnNoBancarotta(new BtnNoBancarotta());

		monopolyGUI.addBtnScambi(new BtnScambi());
		monopolyGUI.addBtnAnnullaScambi(new BtnAnnullaScambi());
		monopolyGUI.addBtnProprieta(new BtnProprieta());
		monopolyGUI.addBtnFineTurno(new BtnFineTurno());

		monopolyGUI.addBtnAcquista(new BtnAcquista());
		monopolyGUI.addBtnAsta(new BtnAsta());
		monopolyGUI.addBtnUsaCartaEsciDiPrigione(new BtnUsaCartaEsciDiPrigione());
		monopolyGUI.addBtnPagaCauzione(new BtnPagaCauzione());
		
		monopolyGUI.addBtnAccettaOfferta(new BtnAccettaOfferta());
		
		monopolyGUI.addBtnNomeGiocatoreScambi(new BtnNomeGiocatoreScambi());
		
		monopolyGUI.addBtn1(new Btn1());
		monopolyGUI.addBtn5(new Btn5());
		monopolyGUI.addBtn10(new Btn10());
		monopolyGUI.addBtn50(new Btn50());
		monopolyGUI.addBtnConfermaOfferta(new BtnConfermaOfferta());
		monopolyGUI.addBtnRitirati(new BtnRitirati());
	}

	private class BtnTiraDadi implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int arrivo, partenza;
			partenza = monopoly.getGiCorrente().getLocation();
			monopoly.tiraDadi();
			arrivo = monopoly.getGiCorrente().getLocation();
			monopolyGUI.muoviPedina(partenza, arrivo, monopoly.getGiCorrente().getId() );
		}
	}

	private class BtnDichiaraBancarotta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			monopolyGUI.confermaBancarotta();
			////////////temporaneo
			//monopoly.stampaDati();
		}
	}

	private class BtnProprieta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			monopolyGUI.buttonsState(false);
			new GestioneProprietaController(frame, monopolyGUI, monopoly);
		}
	}

	private class BtnScambi implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {	
			monopolyGUI.mostraScambi(monopoly.getListaGiocatoriScambi(), monopoly.getGiCorrente().getName(), monopoly.getGiCorrente().getListaPropString());
			monopoly.aggiornaVisualizzazioneInfo();
		}
	}
	
	public class BtnNomeGiocatoreScambi implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {	
			monopolyGUI.getPanelGestioneScambi().removeAll();
			monopolyGUI.contrattazioneScambi();
	
			monopolyGUI.elencaPropGiocatore(monopoly.getGiCorrente().getListaPropString(), 1);
			monopolyGUI.elencaPropGiocatore(monopoly.getCorrispondenzaPlayer(monopolyGUI.getRicevente()).getListaPropString(), 2);
		}
	}
	
	private class BtnAccettaOfferta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {	
			// Recupero i dati dalla GUI
			int denaroOfferto = monopolyGUI.getDenaroOfferto();
		    int denaroRicevuto = monopolyGUI.getDenaroRicevuto();
		    Proprieta propOff = monopoly.getCorrispondenzaProprieta(null, monopoly.getGiCorrente());
		    Proprieta propRic = monopoly.getCorrispondenzaProprieta(null, monopoly.getCorrispondenzaPlayer(monopolyGUI.getRicevente()));
		    monopoly.scambia(monopoly.getCorrispondenzaPlayer(monopolyGUI.getRicevente()), propOff, propRic, denaroOfferto, denaroRicevuto);
			
		}
	}
	
	private class BtnAnnullaScambi implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {	
			monopolyGUI.getPanelSfondo().remove(monopolyGUI.getPanelChiusuraAffare());
			monopolyGUI.mostraScambi(monopoly.getListaGiocatoriScambi(), monopoly.getGiCorrente().getName(), monopoly.getGiCorrente().getListaPropString());
			frame.revalidate();
			frame.repaint();
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
			monopoly.aggiornaVisualizzazioneInfo();
		}
	}
	
	private class BtnAsta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			monopolyGUI.rimuoviAcquistoAsta();
			monopoly.iniziaAsta();
		}
	}

	private class BtnBancarotta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			monopolyGUI.rimuoviAcquistoAsta();
		
			
			monopoly.aggiornaVisualizzazioneInfo();
		}
	}

	private class BtnNoBancarotta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			monopolyGUI.rimuoviAcquistoAsta();
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

	private class Btn1 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!monopoly.asta.aggiornaOfferta(1)) { //si potrebbe fare un metodo per queste azioni
				monopolyGUI.stampa("Non hai fondi a sufficenza per questa offerta");
			}
			else {
				monopolyGUI.btnRitirati.setVisible(false);
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
				monopolyGUI.btnRitirati.setVisible(false);
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
				monopolyGUI.btnRitirati.setVisible(false);
				monopolyGUI.aggiornaOfferta(monopoly.asta.getOfferta());
			}
		}
	}
	
	private class Btn50 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!monopoly.asta.aggiornaOfferta(50)) {
				monopolyGUI.stampa("Non hai fondi a sufficenza per questa offerta");
			}
			else {
				monopolyGUI.btnRitirati.setVisible(false);
				monopolyGUI.aggiornaOfferta(monopoly.asta.getOfferta());
			}
		}
	}

	private class BtnConfermaOfferta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			monopolyGUI.btnRitirati.setVisible(true);
			monopoly.asta.prossimoGiocatore();
			monopolyGUI.aggiornaTurno(monopoly.asta.getName());
		}
	}

	private class BtnRitirati implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			monopoly.asta.ritirati();
			if(monopoly.asta.controlloFineAsta()) {
				monopolyGUI.rimuoviAcquistoAsta();
				monopoly.asta.fineAsta();
				monopoly.aggiornaVisualizzazioneInfo();
			}
			else {
				monopolyGUI.aggiornaTurno(monopoly.asta.getName());
			}
		}
	}

	

}
