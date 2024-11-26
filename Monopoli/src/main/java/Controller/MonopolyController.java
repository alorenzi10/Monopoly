package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import Model.Monopoly;
import Model.Proprieta;
import View.MonopolyGUI;
import View.SchermataDiGioco;

public class MonopolyController {

	private static SchermataDiGioco frame; //Per gestire il JFrame
	private static MonopolyGUI monopolyGUI;
	private Monopoly monopoly;
	private String index;
	private String[] offerte, richieste;
	private int contaOfferte, contaRichieste, carteOfferte, carteRichieste;
	private boolean baseAsta;


	public MonopolyController(SchermataDiGioco frame) {

		MonopolyController.frame = frame;
		monopolyGUI = new MonopolyGUI(frame, SceltaPedineController.getNumGiocatori(),  SceltaPedineController.getPedineScelte() );
		monopoly = new Monopoly(SceltaPedineController.getNumGiocatori(), NomiGiocatoriController.getNomiGiocatori(), monopolyGUI);
		monopolyGUI.setBounds(0, 0, 1920, 1080); 
		frame.add(monopolyGUI);
		monopolyGUI.mostraInfoGiocatori(monopoly.getGiocatoriString());
		aggiuntaListener();
		frame.revalidate();
		frame.repaint();
	}
	//da caricamento
	public MonopolyController(SchermataDiGioco frame, Monopoly monopoly, List<int[]> coppie) {

		MonopolyController.frame = frame;
		this.monopoly = monopoly;

		monopolyGUI = new MonopolyGUI(frame, monopoly.getNumGiocatori(), monopoly.getPedineSelezionate());
		monopolyGUI.setBounds(0, 0, 1920, 1080); 

		monopolyGUI.mostraInfoGiocatori(this.monopoly.getGiocatoriString());
		this.monopoly.caricamento(monopolyGUI, coppie); //per settare le proprieta del giocatore e le relative costruzioni
		frame.add(monopolyGUI);
		aggiuntaListener();
		frame.revalidate();
		frame.repaint(); 
	}

	private void aggiuntaListener() {
		//scelte 
		monopolyGUI.addBtnTiraDadi(new BtnTiraDadi());
		monopolyGUI.addBtnFineTurno(new BtnFineTurno());
		monopolyGUI.addBtnProprieta(new BtnProprieta());
		monopolyGUI.addBtnSalva(new BtnSalva());
		monopolyGUI.addBtnEsci(new BtnEsci());

		monopolyGUI.addBtnSalva1(new BtnSalva1());
		monopolyGUI.addBtnAnnulla(new BtnAnnulla());

		//bancarotta
		monopolyGUI.addBtnDichiaraBancarotta(new BtnDichiaraBancarotta());
		monopolyGUI.addBtnConfermaBancarotta(new BtnBancarotta());
		monopolyGUI.addBtnNoBancarotta(new BtnNoBancarotta());

		//scambi
		monopolyGUI.addBtnScambi(new BtnScambi());
		monopolyGUI.addBtnNomeGiocatoreScambi(new BtnNomeGiocatoreScambi());
		monopolyGUI.addBtnAnnullaScambi(new BtnAnnullaScambi());
		monopolyGUI.addBtnProprietaRichieste(new BtnProprietaRichieste());
		monopolyGUI.addBtnProprietaOfferte(new BtnProprietaOfferte());
		monopolyGUI.addBtnCarteOfferte(new BtnCarteOfferte());
		monopolyGUI.addBtnCarteRichieste(new BtnCarteRichieste());
		monopolyGUI.addBtnAccettaOfferta(new BtnAccettaOfferta());

		//giocatore atterra su proprietà vuota
		monopolyGUI.addBtnAcquista(new BtnAcquista());
		monopolyGUI.addBtnAsta(new BtnAsta());

		//prigione
		monopolyGUI.addBtnUsaCartaEsciDiPrigione(new BtnUsaCartaEsciDiPrigione());
		monopolyGUI.addBtnPagaCauzione(new BtnPagaCauzione());

		//asta
		monopolyGUI.addBtn1(new Btn1());
		monopolyGUI.addBtn5(new Btn5());
		monopolyGUI.addBtn10(new Btn10());
		monopolyGUI.addBtn50(new Btn50());
		monopolyGUI.addBtnConfermaOfferta(new BtnConfermaOfferta());
		monopolyGUI.addBtnRitirati(new BtnRitirati());
	}

	//SCELTE 
	private class BtnTiraDadi implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int arrivo, partenza;
			partenza = monopoly.getGiCorrente().getLocation();
			monopoly.tiraDadi();
			arrivo = monopoly.getGiCorrente().getLocation();
			monopolyGUI.muoviPedina(partenza, arrivo, monopoly.getPlayers().indexOf(monopoly.getGiCorrente()));
		}
	}

	private class BtnFineTurno implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			monopoly.setFineTurno();
		}
	}

	private class BtnProprieta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			monopolyGUI.buttonsState(false);
			new GestioneProprietaController(frame, monopolyGUI, monopoly);
		}
	}

	private class BtnSalva implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			monopolyGUI.salva();
		}
	}

	private class BtnSalva1 implements ActionListener{ //devo fare controllo no duplicati nel nome
		@Override
		public void actionPerformed(ActionEvent e) {

			monopoly.setPedineSelezionate(); //salva come stringa le pedine scelte. Utile per il caricamento della partita.
			String filePath="partiteMonopoli.json";
			String nome = monopolyGUI.getNomeSalvataggio(); //prende il nome del salvataggio scelto dall'utente
			if(nome.length() != 0) { 
				monopoly.setSalvaPartita(nome);  //da un nome all'oggetto monopoly e salva l'indice del giCorrente rispetto all'arraylist players.Utile per il caricamento della partita.
				monopoly.setTempo(); //salva la data del salvataggio

				File file = new File(filePath);
				Gson gson = new Gson();

				if(!file.exists()) { //crea il file se non esiste
					try {
						file.createNewFile();

						JsonArray jsonArray=new JsonArray();
						try(FileWriter writer=new FileWriter(filePath)){
							gson.toJson(jsonArray, writer);
						}
					}
					catch(IOException e1){
						e1.printStackTrace();
					}
				}

				try {
					JsonArray existingData=new JsonArray();

					try(FileReader reader=new FileReader(filePath)){
						existingData = JsonParser.parseReader(reader).getAsJsonArray();
					}

					for (JsonElement element : existingData) {// Iterazione su ciascun elemento (stringa JSON)
						String jsonString = element.getAsString();	
						JsonElement jsonObject = JsonParser.parseString(jsonString);
						String nomePartita = jsonObject.getAsJsonObject().get("nomePartita").getAsString();

						if (nomePartita.equals(nome)) { //controlla se esiste un altro salvataggio con lo stesso nome
							JOptionPane.showMessageDialog(monopolyGUI, "Questo nome è gia in uso");
							return;
						}
					}

					String json=gson.toJson(monopoly);
					existingData.add(json);

					try(FileWriter writer=new FileWriter(filePath)){
						gson.toJson(existingData,writer);
					}

				} catch(IOException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(monopolyGUI, "Partita salvata");
				monopolyGUI.rimuoviAcquistoAsta();
			}else {
				JOptionPane.showMessageDialog(monopolyGUI, "Per salvare inserisci un nome al salvataggio");
			}
		}
	}

	private class BtnAnnulla implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			monopolyGUI.rimuoviAcquistoAsta();
		}
	}

	private class BtnEsci implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			frame.remove(monopolyGUI.getPanelScelteTurno());
			frame.remove(monopolyGUI);
			new MenuController(frame);

		}
	}
	
	//BANCAROTTA
	private class BtnDichiaraBancarotta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			monopolyGUI.confermaBancarotta();
			////////////temporaneo
			//monopoly.stampaDati();
		}
	}

	private class BtnBancarotta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			monopolyGUI.setDecisioneBancarotta(true);
			monopolyGUI.stampa("Sei andato in bancarotta");
			monopolyGUI.rimuoviPedina(monopoly.getGiCorrente().getLocation(), monopoly.getPlayers().indexOf(monopoly.getGiCorrente()));
			monopoly.setBancarotta();

			if(monopoly.getPlayers().size() == 1) {
				frame.remove(monopolyGUI.getPanelScelteTurno());
				frame.remove(monopolyGUI);
				new SchermataVincitoreController(monopoly.getPlayers().get(0).getName(), frame); // Creazione schermata vincitore con nome del giocatore
				frame.revalidate();
				frame.repaint();
			}
			monopolyGUI.mostraInfoGiocatori(monopoly.getGiocatoriString());
			monopoly.aggiornaVisualizzazioneInfo();
		}
	}

	private class BtnNoBancarotta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			monopolyGUI.setDecisioneBancarotta(false);
			monopolyGUI.rimuoviAcquistoAsta();
		}
	}

	//SCAMBI
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

			monopolyGUI.elencaPropGiocatore(monopoly.getGiCorrente().getListaPropString(), monopolyGUI.getScrollPaneElencoPropGiCorrente(),true, monopoly.getGiCorrente().getNumCarte());

			monopolyGUI.elencaPropGiocatore(monopoly.getCorrispondenzaPlayer(index).getListaPropString(), monopolyGUI.getScrollPaneElencoPropRicevente(),false, monopoly.getCorrispondenzaPlayer(index).getNumCarte());

			offerte = new String[40];
			richieste = new String[40];
			contaOfferte = 0;
			contaRichieste = 0;
			carteRichieste = 0;
			carteOfferte = 0;

		}
	}

	public class BtnProprietaOfferte implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {	

			offerte[contaOfferte] = (e.getActionCommand());
			monopolyGUI.getBottonePropOfferte(e.getActionCommand()).setEnabled(false);
			contaOfferte++;
		}
	}

	public class BtnProprietaRichieste implements ActionListener{ //se qualcuno spamma il bottone rompe il gioco, serve controllo
		@Override
		public void actionPerformed(ActionEvent e) {	

			richieste[contaRichieste] = (e.getActionCommand());
			monopolyGUI.getBottonePropRichieste(e.getActionCommand()).setEnabled(false);
			contaRichieste++; 
		}
	}

	public class BtnCarteRichieste implements ActionListener{ //se qualcuno spamma il bottone rompe il gioco, serve controllo
		@Override
		public void actionPerformed(ActionEvent e) {	

			carteRichieste++;
			monopolyGUI.getBottoneCarteRichieste(e.getActionCommand()).setEnabled(false);
		}
	}

	public class BtnCarteOfferte implements ActionListener{ //se qualcuno spamma il bottone rompe il gioco, serve controllo
		@Override
		public void actionPerformed(ActionEvent e) {	

			carteOfferte++;
			monopolyGUI.getBottoneCarteOfferte(e.getActionCommand()).setEnabled(false);
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

				monopolyGUI.stampa("Per scambiare riempi anche i campi denaro"); // Non serve perchè c'è 0 di default (che però è eliminabile...)
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
				if(offerte[i] != null) {
					propOff[i] = monopoly.getCorrispondenzaProprieta(offerte[i], monopoly.getGiCorrente());

				}
			}

			Proprieta[] propRic = new Proprieta[40];
			for(int i=0; i<40; i++) {
				if(richieste[i] != null) {
					propRic[i] = monopoly.getCorrispondenzaProprieta(richieste[i], monopoly.getCorrispondenzaPlayer(index));
				}
			}

			if(monopoly.getGiCorrente().controlloFondi(denaroOfferto) &&  monopoly.getCorrispondenzaPlayer(index).controlloFondi(denaroRicevuto)) {
				monopoly.getGiCorrente().doTransaction(-denaroOfferto);
				monopoly.getGiCorrente().doTransaction(denaroRicevuto);
				monopoly.getCorrispondenzaPlayer(index).doTransaction(denaroOfferto);
				monopoly.getCorrispondenzaPlayer(index).doTransaction(-denaroRicevuto);
				for(int i=0; i<40; i++) {
					if(propRic[i] != null) {
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

				for(int i=0; i<carteOfferte; i++) {
					monopoly.getCorrispondenzaPlayer(index).addCarta(monopoly.getGiCorrente().getCarta());
				}
				for(int i=0; i<carteRichieste; i++) {
					monopoly.getGiCorrente().addCarta(monopoly.getCorrispondenzaPlayer(index).getCarta());
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

	private class BtnAnnullaScambi implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {	
			annullaScambio();
		}
	}

	private void annullaScambio() {
		monopolyGUI.getPanelSfondo().remove(monopolyGUI.getPanelChiusuraAffare());
		monopolyGUI.mostraScambi(monopoly.getListaGiocatoriScambi(), monopoly.getGiCorrente().getName(), monopoly.getGiCorrente().getListaPropString());
		frame.revalidate();
		frame.repaint();
	}

	//ATTERRAGGIO SU CASELLA VUOTA
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
			baseAsta=false;
			monopoly.iniziaAsta();
		}
	}

	//PRIGIONE
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

	///ASTA
	private class Btn1 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!monopoly.asta.aggiornaOfferta(1)) { //si potrebbe fare un metodo per queste azioni
				monopolyGUI.stampa("Non hai fondi a sufficenza per questa offerta");
			}
			else {
				monopolyGUI.getBtnRitirati().setVisible(false);
				monopolyGUI.getBtnConfermaOfferta().setVisible(true);
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
				monopolyGUI.getBtnConfermaOfferta().setVisible(true);
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
				monopolyGUI.getBtnConfermaOfferta().setVisible(true);
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
				monopolyGUI.getBtnConfermaOfferta().setVisible(true);
				monopolyGUI.aggiornaOfferta(monopoly.asta.getOfferta());
			}
		}
	}

	private class BtnConfermaOfferta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(monopoly.asta.controlloOfferta()) {
				baseAsta = false;
				monopolyGUI.getBtnRitirati().setVisible(true);
				monopolyGUI.getBtnConfermaOfferta().setVisible(false);
				monopoly.asta.prossimoGiocatore();
				monopolyGUI.aggiornaTurno(monopoly.asta.getName());
			} else {
				baseAsta = true;
				monopolyGUI.getBtnConfermaOfferta().setVisible(false);
				monopolyGUI.stampa("Non hai fondi a sufficenza per questa offerta, ritirati");
			}
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
				if(monopolyGUI.getDecisioneBancarotta()) {
					if(monopoly.getConta()) {
						monopoly.astaBancarotta();
					}
				}
			}
			else {
				if(baseAsta) {
					monopolyGUI.getBtnConfermaOfferta().setVisible(true);
				}
				monopolyGUI.aggiornaTurno(monopoly.asta.getName());
			}
		}
	}
}
