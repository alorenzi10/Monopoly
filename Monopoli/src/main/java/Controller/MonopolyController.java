package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Monopoly;
import Model.Proprieta;
import View.MonopolyGUI;
import View.SchermataDiGioco;
import View.SchermataVincitoreView;

public class MonopolyController {

	private static SchermataDiGioco frame; //Per gestire il JFrame
	private static MonopolyGUI monopolyGUI;
	private Monopoly monopoly;
	private SchermataVincitoreView schermataVincitore;
	String index;
	String[] offerte, richieste;
	int contaOfferte, contaRichieste;
	
	public MonopolyController(SchermataDiGioco frame) {

		MonopolyController.frame = frame;
		monopolyGUI = new MonopolyGUI(frame);
		monopoly = new Monopoly(SceltaPedineController.getNumGiocatori(), NomiGiocatoriController.getNomiGiocatori(), monopolyGUI);
		monopolyGUI.setBounds(0, 0, 1920, 1080); 
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
		monopolyGUI.addBtnProprietaRichieste(new BtnProprietaRichieste());
		monopolyGUI.addBtnProprietaOfferte(new BtnProprietaOfferte());
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
			index=(e.getActionCommand());
			
			monopolyGUI.getPanelGestioneScambi().removeAll();
			monopolyGUI.contrattazioneScambi();
			
			monopolyGUI.elencaPropGiocatore(monopoly.getGiCorrente().getListaPropString(), monopolyGUI.getScrollPaneElencoPropGiCorrente(),true);
			
			monopolyGUI.elencaPropGiocatore(monopoly.getCorrispondenzaPlayer(index).getListaPropString(), monopolyGUI.getScrollPaneElencoPropRicevente(),false);
			
			offerte=new String[40];
			richieste=new String[40];
			contaOfferte=0;
			contaRichieste=0;
		}
	}
	
	public class BtnProprietaOfferte implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {	
			
			offerte[contaOfferte]=(e.getActionCommand());
			monopolyGUI.getBottonePropOfferte(e.getActionCommand()).setEnabled(false);
			contaOfferte++;
		}
	}
	
	public class BtnProprietaRichieste implements ActionListener{ //se qualcuno spamma il bottone rompe il gioco, serve controllo
		@Override
		public void actionPerformed(ActionEvent e) {	
			
			richieste[contaRichieste]=(e.getActionCommand());
			monopolyGUI.getBottonePropRichieste(e.getActionCommand()).setEnabled(false);
			contaRichieste++; 
		}
	}
	
	private class BtnAccettaOfferta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {	//da sposare nel model mi sa
			// Recupero i dati dalla GUI
			int denaroOfferto = 0;
			try {
				denaroOfferto = Integer.parseInt(monopolyGUI.getDenaroOfferto()); //controllo anche se negativi o fuori limite int?
			}catch (NumberFormatException exe) {
				
		    	monopolyGUI.stampa("Per scambiare riempi anche i campi denaro");
		    	annullaScambio();
		    	return;
			}
			
			int denaroRicevuto = 0;
			try {
				denaroRicevuto = Integer.parseInt(monopolyGUI.getDenaroRicevuto());
			}catch (NumberFormatException exe) {
				annullaScambio();
		    	monopolyGUI.stampa("Per scambiare riempi anche i campi denaro");
		    	return;
			}
			
		    Proprieta[] propOff = new Proprieta[40];
		    for(int i=0; i<40; i++) {
		    	if(offerte[i]!=null) {
		    		propOff[i] = monopoly.getCorrispondenzaProprieta(offerte[i], monopoly.getGiCorrente());
		    		
		    	}
		    }
		    
		    Proprieta[] propRic = new Proprieta[40];
		    for(int i=0; i<40; i++) {
		    	if(richieste[i]!=null) {
		    		propRic[i] = monopoly.getCorrispondenzaProprieta(richieste[i], monopoly.getCorrispondenzaPlayer(index));
		    	}
		    }

		    if(monopoly.getGiCorrente().controlloFondi(denaroOfferto) &&  monopoly.getCorrispondenzaPlayer(index).controlloFondi(denaroRicevuto)) {
		    	monopoly.getGiCorrente().doTransaction(-denaroOfferto);
		    	monopoly.getGiCorrente().doTransaction(denaroRicevuto);
		    	monopoly.getCorrispondenzaPlayer(index).doTransaction(denaroOfferto);
		    	monopoly.getCorrispondenzaPlayer(index).doTransaction(-denaroRicevuto);
		    	for(int i=0; i<40; i++) {
		    		if(propRic[i]!=null) {
		    			monopoly.getCorrispondenzaPlayer(index).rimuoviProprieta(propRic[i]);
		    			monopoly.getGiCorrente().aggiungiProprieta(propRic[i]);
		    		}
		    	}
		    	for(int i=0; i<40; i++) {
		    		if(propOff[i]!=null) {
		    			monopoly.getGiCorrente().rimuoviProprieta(propOff[i]);
		    			monopoly.getCorrispondenzaPlayer(index).aggiungiProprieta(propOff[i]);
		    		}
		    	}
		    	monopolyGUI.stampa("Scambio andato a buon fine");
		    	monopoly.aggiornaVisualizzazioneInfo();
		    	annullaScambio();
		    }else {
		    	monopolyGUI.stampa("Fondi insufficenti per questo scambio");
		    	annullaScambio();
		    	return;
		    }
		}
	}
	
	private void annullaScambio() {
		monopolyGUI.getPanelSfondo().remove(monopolyGUI.getPanelChiusuraAffare());
		monopolyGUI.mostraScambi(monopoly.getListaGiocatoriScambi(), monopoly.getGiCorrente().getName(), monopoly.getGiCorrente().getListaPropString());
		frame.revalidate();
		frame.repaint();
	}
	
	private class BtnAnnullaScambi implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {	
			annullaScambio();
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

			monopolyGUI.setDecisioneBancarotta();
			monopolyGUI.stampa("Sei andato in bancarotta");
			monopolyGUI.rimuoviPedina(monopoly.getGiCorrente().getLocation(), monopoly.getPlayers().indexOf(monopoly.getGiCorrente()));
			monopoly.setBancarotta();
			
			if(monopoly.getPlayers().size() == 1) {
				frame.remove(monopolyGUI.getPanelScelteTurno());
				frame.remove(monopolyGUI);
				frame.add(new SchermataVincitoreView(monopoly.getPlayers().get(0).getName())); // Creazione schermata vincitore con nome del giocatore
				frame.revalidate();
		        frame.repaint();
			}
			monopolyGUI.mostraInfoGiocatori(monopoly.getGiocatoriString());
			monopolyGUI.rimuoviAcquistoAsta();
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
			monopolyGUI.attivaUscitaConCauzione(false);
			
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
				monopolyGUI.getBtnRitirati().setVisible(false);
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
				monopolyGUI.getBtnRitirati().setVisible(false);
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
				monopolyGUI.getBtnRitirati().setVisible(false);
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
				monopolyGUI.getBtnRitirati().setVisible(false);
				monopolyGUI.aggiornaOfferta(monopoly.asta.getOfferta());
			}
		}
	}

	private class BtnConfermaOfferta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			monopolyGUI.getBtnRitirati().setVisible(true);
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
