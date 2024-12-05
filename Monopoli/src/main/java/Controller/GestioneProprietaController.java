package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Model.Cantiere;
import Model.GruppoColore;
import Model.Monopoly;
import Model.Proprieta;
import Model.Societa;
import Model.Stazione;
import Model.Tabellone;
import View.GestioneProprietaView;
import View.MonopolyGUI;
import View.SchermataDiGioco;
/**
 * Controller per la gestione delle proprietà nel gioco Monopoly.
 * Gestisce le interazioni tra l'utente e le viste relative alla gestione delle proprietà.
 * Consente operazioni come costruzione, demolizione, ipoteca e disipoteca sulle proprietà.
 */
public class GestioneProprietaController {
	
	// Riferimenti ai modelli e alle viste
	GestioneProprietaView gestioneProprieta;
	Monopoly monopoly;
	MonopolyGUI monopolyGUI;
	Tabellone tabellone;
	SchermataDiGioco frame;
	// Variabili per la gestione delle azioni e dei tipi di proprietà
	GruppoColore colore;
	ArrayList<Cantiere> prova;
	Stazione[] stazioni;
	Societa[] societa;
	int scelta; //1 costruisci, 2 demolisci, 3 ipoteca, 4 dispoteca
	int tipo; //1 cantiere, 2 stazioni, 3 societa
	
	/**
     * Costruttore del controller.
     * @param frame finestra principale del gioco.
     * @param monopolyGUI oggetto per la gestione della GUI principale.
     * @param monopoly modello principale del gioco.
     */
	public GestioneProprietaController(SchermataDiGioco frame, MonopolyGUI monopolyGUI, Monopoly monopoly) {
		this.monopolyGUI = monopolyGUI;
		this.monopoly = monopoly;
		this.tabellone = monopoly.getTabellone();
		this.frame = frame;
		
		// Inizializza la vista di gestione proprietà e configura i pulsanti
		gestioneProprieta = new GestioneProprietaView(frame);
		gestioneProprieta.scelte();
		
		// Aggiunta dei listener ai pulsanti principali
		gestioneProprieta.addBtnCostruisci(new BtnCostruisci());
		gestioneProprieta.addBtnDemolisci(new BtnDemolisci());
		gestioneProprieta.addBtnIpoteca(new BtnIpoteca());
		gestioneProprieta.addBtnDisipoteca(new BtnDisipoteca());
		gestioneProprieta.addBtnIndietro(new BtnIndietro());
		gestioneProprieta.addBtnIndietro1(new BtnIndietro1());
		
		// Aggiunta dei listener ai pulsanti per i gruppi di colore
		gestioneProprieta.addBtnFine(new BtnFine());
		gestioneProprieta.addBtnMarrone(new BtnMarrone());
		gestioneProprieta.addBtnAzzurro(new BtnAzzurro());
		gestioneProprieta.addBtnViola(new BtnViola());
		gestioneProprieta.addBtnArancio(new BtnArancione());
		gestioneProprieta.addBtnRosso(new BtnRosso());
		gestioneProprieta.addBtnGiallo(new BtnGiallo());
		gestioneProprieta.addBtnVerde(new BtnVerde());
		gestioneProprieta.addBtnBlu(new BtnBlu());
		 // Aggiunta dei listener ai pulsanti per stazioni e società
		gestioneProprieta.addBtnSocieta(new BtnSocieta());
		gestioneProprieta.addBtnStazione(new BtnStazione());

		 // Aggiunta dei listener ai pulsanti numerici che possono rappresentare vari cantieri/società/stazioni
		gestioneProprieta.addBtn1(new Btn1());
		gestioneProprieta.addBtn2(new Btn2());
		gestioneProprieta.addBtn3(new Btn3());
		gestioneProprieta.addBtn4(new Btn4());
	}	

	private class BtnCostruisci implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			scelta = 1;
			gestioneProprieta.costruisci(scelta);
		}
	}

	private class BtnDemolisci implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			scelta = 2;
			gestioneProprieta.costruisci(scelta);	
		}
	}

	private class BtnIpoteca implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			scelta = 3;
			gestioneProprieta.costruisci(scelta);	
		}
	}

	private class BtnDisipoteca implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			scelta = 4;
			gestioneProprieta.costruisci(scelta);	
		}
	}

	private class BtnMarrone implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Colore("marrone", scelta);
			tipo = 1;
			colore = tabellone.getMarrone();
			prova = colore.getMembri();
			for(Cantiere p: prova) {
				gestioneProprieta.aggiungiBottone(p.getNome());
				if(scelta == 3) {
					gestioneProprieta.valoriIpoteche(p.getPrezzoIpoteca());
				}
				if(scelta == 4) {
					gestioneProprieta.valoriIpoteche(p.getPrezzoIpoteca() * 1.1);
				}
			}
		}
	}
	private class BtnAzzurro implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Colore("azzurro", scelta);
			colore = tabellone.getAzzurro();
			tipo = 1;
			prova = colore.getMembri();
			for(Cantiere p: prova) {
				gestioneProprieta.aggiungiBottone(p.getNome());
				if(scelta == 3) {
					gestioneProprieta.valoriIpoteche(p.getPrezzoIpoteca());
				}
				if(scelta == 4) {
					gestioneProprieta.valoriIpoteche(p.getPrezzoIpoteca() * 1.1);
				}
			}
		}
	}
	private class BtnViola implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Colore("viola", scelta);
			colore = tabellone.getViola();
			tipo = 1;
			prova = colore.getMembri();
			for(Cantiere p: prova) {
				gestioneProprieta.aggiungiBottone(p.getNome());
				if(scelta == 3) {
					gestioneProprieta.valoriIpoteche(p.getPrezzoIpoteca());
				}
				if(scelta == 4) {
					gestioneProprieta.valoriIpoteche(p.getPrezzoIpoteca() * 1.1);
				}
			}
		}
	}

	private class BtnArancione implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Colore("arancione", scelta);
			colore = tabellone.getArancione();
			tipo = 1;
			prova = colore.getMembri();
			for(Cantiere p: prova) {
				gestioneProprieta.aggiungiBottone(p.getNome());
				if(scelta == 3) {
					gestioneProprieta.valoriIpoteche(p.getPrezzoIpoteca());
				}
				if(scelta == 4) {
					gestioneProprieta.valoriIpoteche(p.getPrezzoIpoteca() * 1.1);
				}
			}
		}
	}
	
	private class BtnRosso implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Colore("rosso", scelta);
			colore = tabellone.getRosso();
			tipo = 1;
			prova = colore.getMembri();
			for(Cantiere p: prova) {
				gestioneProprieta.aggiungiBottone(p.getNome());
				if(scelta == 3) {
					gestioneProprieta.valoriIpoteche(p.getPrezzoIpoteca());
				}
				if(scelta == 4) {
					gestioneProprieta.valoriIpoteche(p.getPrezzoIpoteca() * 1.1);
				}
			}
		}
	}
	
	private class BtnGiallo implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Colore("giallo", scelta);
			tipo = 1;
			colore = tabellone.getGiallo();
			prova = colore.getMembri();
			for(Cantiere p: prova) {
				gestioneProprieta.aggiungiBottone(p.getNome());
				if(scelta == 3) {
					gestioneProprieta.valoriIpoteche(p.getPrezzoIpoteca());
				}
				if(scelta == 4) {
					gestioneProprieta.valoriIpoteche(p.getPrezzoIpoteca() * 1.1);
				}
			}
		}
	}
	
	private class BtnVerde implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Colore("verde", scelta);
			colore = tabellone.getVerde();
			tipo = 1;
			prova = colore.getMembri();
			for(Cantiere p: prova) {
				gestioneProprieta.aggiungiBottone(p.getNome());
				if(scelta == 3) {
					gestioneProprieta.valoriIpoteche(p.getPrezzoIpoteca());
				}
				if(scelta == 4) {
					gestioneProprieta.valoriIpoteche(p.getPrezzoIpoteca() * 1.1);
				}
			}
		}
	}
	
	private class BtnBlu implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Colore("blu", scelta);
			colore = tabellone.getBlu();
			prova = colore.getMembri();
			tipo = 1;
			for(Cantiere p: prova) {
				gestioneProprieta.aggiungiBottone(p.getNome());
				if(scelta == 3) {
					gestioneProprieta.valoriIpoteche(p.getPrezzoIpoteca());
				}
				if(scelta == 4) {
					gestioneProprieta.valoriIpoteche(p.getPrezzoIpoteca() * 1.1);
				}
			}
		}
	}

	private class BtnStazione implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Colore("Stazione", scelta);
			tipo = 2;
			stazioni = tabellone.getStazioni();

			for(Stazione p: stazioni) {
				gestioneProprieta.aggiungiBottone( p.getNome());

				if(scelta == 3) {
					gestioneProprieta.valoriIpoteche(p.getPrezzoIpoteca());

				}
				if(scelta == 4) {
					gestioneProprieta.valoriIpoteche(p.getPrezzoIpoteca() * 1.1);

				}
			}
		}
	}

	private class BtnSocieta implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.Colore("Società", scelta);
			tipo = 3;
			societa = tabellone.getSocieta();
			for(Societa p: societa) {
				gestioneProprieta.aggiungiBottone( p.getNome());

				if(scelta == 3) {
					gestioneProprieta.valoriIpoteche(p.getPrezzoIpoteca());

				}
				if(scelta == 4) {
					gestioneProprieta.valoriIpoteche(p.getPrezzoIpoteca() * 1.1);

				}
			}
		}
	}

	private class BtnIndietro implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.fine();
			gestioneProprieta.scelte();
		}
	}

	private class BtnIndietro1 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			gestioneProprieta.costruisci(scelta);
		}
	}
	
	private class BtnFine implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestioneProprieta.fine();
			monopolyGUI.buttonsState(true);
		}
	}

	private class Btn1 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Cantiere p = null;
			Proprieta pr = null;
			if(tipo == 1) {
				pr = prova.get(0);
			}
			if(tipo == 2) {
				pr = stazioni[0];
			}
			if(tipo == 3) {	
				pr = societa[0];
			}

			switch (scelta) {
			case 1:
				p = prova.get(0);
				monopoly.costruisci(p);
				break;
			case 2:
				p = prova.get(0);
				monopoly.demolisci(p);
				break;
			case 3:
				monopoly.ipoteca(pr);
				break;
			case 4:
				monopoly.disipoteca(pr);
				break;
			}
		}
	}

	private class Btn2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Cantiere p = null;
			Proprieta pr = null;
			if(tipo == 1) {
				pr=prova.get(1);
			}
			if(tipo == 2) {
				pr = stazioni[1];
			}
			if(tipo == 3) {	
				pr = societa[1];
			}


			switch (scelta) {
			case 1:
				p = prova.get(1);
				monopoly.costruisci(p);
				break;
			case 2:
				p = prova.get(1);
				monopoly.demolisci(p);
				break;
			case 3:
				monopoly.ipoteca(pr);
				break;
			case 4:
				monopoly.disipoteca(pr);
				break;
			}
		}
	}

	private class Btn3 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Cantiere p = null;
			Proprieta pr = null;
			if(tipo == 1) {
				pr = prova.get(2);
			}
			if(tipo == 2) {
				pr = stazioni[2];
			}

			switch (scelta) {
			case 1:
				p = prova.get(2);
				monopoly.costruisci(p);
				break;
			case 2:
				p = prova.get(2);
				monopoly.demolisci(p);
				break;
			case 3:
				monopoly.ipoteca(pr);
				break;
			case 4:
				monopoly.disipoteca(pr);
				break;
			}
		}
	}

	private class Btn4 implements ActionListener{ // Solo in caso di stazione
		@Override
		public void actionPerformed(ActionEvent e) {
			Proprieta pr = stazioni[3];
			switch (scelta) {
			case 3:
				monopoly.ipoteca(pr);
				break;
			case 4:
				monopoly.disipoteca(pr);
				break;
			}
		}
	}

}
